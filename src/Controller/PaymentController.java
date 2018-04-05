package Controller;
//Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items
//and its total, tax and total amount)

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Guest;
import Entity.Reservation;
import Entity.Room;
import Entity.RoomService;

public class PaymentController {
	public static final String FILENAME = "roomService.txt";
	public static final String FILENAME1 = "reservation.txt";
	public static final String SEPARATOR = "|";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");

	public static void printBill ()  throws IOException {
		Scanner sc = new Scanner(System.in);
		String roomNo;

		//get room
		/*while(true) {
			System.out.println("Please enter Room Number: ");
			roomNo=sc.next();
			if(!RoomController.checkRoomExist(roomNo)) {
				System.out.println("Enter a valid room!");
			}
			else if(RoomController.checkRoomAvailability(roomNo)) {
				System.out.println("Room is vacant!");
			}
			else {
				break;
			}
		}*/

		//get date and time
		String guestID = new String();
		Reservation res = new Reservation();
		while(true)
		{
			System.out.println("Please enter Guest ID: ");
			guestID=sc.nextLine();
			if ((GuestController.searchGuest(guestID.toUpperCase())).getIdNo() == null)
				System.out.println("Enter a valid guest ID!");
			else
			{
					res = ReservationController.searchReservations(guestID);
					if (res.getCheckOut() ==null)
						System.out.println("No reservation found!");
					else
						break;
				
			}
		}
		
		long days = 0;
		LocalDate roomOutDate;
		
		while(true)
		{
			try {
				System.out.println("Please enter check out date (dd/mm/yyyy): ");
				
				roomOutDate =LocalDate.parse(sc.nextLine(), formatter);
				days = res.getCheckIn().until(roomOutDate, ChronoUnit.DAYS);//Calculate no. of days
				
				if(days == 0) {
					days = 1;
					break;
				}
				else if (days < 0) {
					System.out.println("Enter a date after " + res.getCheckIn() + "!");
				}
				else {
					break;
				}
			} catch(Exception e) {
				System.out.println("Enter a valid date!");
			}
		}

		LocalTime roomOutTime;
		while(true)
		{
			try {
				System.out.println("Please enter check out time (hh:mm AM/PM): ");
				roomOutTime =LocalTime.parse(sc.nextLine(),f2);
				break;
			} catch(Exception e) {
				System.out.println("Enter a valid time!");
			}
		}


		//get discount and tax
		System.out.println("Please enter discount rate: ");
		double discount = sc.nextDouble();

		System.out.println("Please enter tax rate: ");
		double tax = sc.nextDouble();

		System.out.println();
		//print reservation info
		System.out.println("Reservation Code: " + res.getReservationCode() );
		System.out.println();
		System.out.println("Guest Name: " + res.getGuest().getName() );
		System.out.println();
		System.out.println("Room Number: ");
		for(int i=0;i<res.getReservationRoom().getNoOfRooms();i++) {
			System.out.print("|");
			System.out.print(res.getReservationRoom().getRooms().get(i).getRoomNumber());
			System.out.print("|");
		}
		System.out.println();
		System.out.println();
		System.out.println("Pay by: " + res.getBilling() );
		System.out.println();
		System.out.println("Check in date: " + res.getCheckIn().format(formatter) );
		System.out.println("Check in time: " + res.getScheduledTime().format(f2) );
		System.out.println();
		System.out.println("Check out date: " + roomOutDate.format(formatter));
		System.out.println("Check out time: " + roomOutTime.format(f2));		
		System.out.println();
		System.out.println("No. of Adult: " + res.getNoAdults() );
		System.out.println("No. of Children: " + res.getNoChildren() );
		System.out.println();
		System.out.println("Walk in?: " + res.isWalkIn() );

		//print bill heading
		System.out.println("\nType		Item		Price	Date & TimeStamp"); 
		System.out.println("======================================================="); 

		//print room bill
		double totalPrice=0;
		for(int i=0;i<res.getReservationRoom().getNoOfRooms();i++) {
			Room room = RoomController.searchRoom(res.getReservationRoom().getRooms().get(i).getRoomNumber());
			System.out.println("Room		" + 
					room.getType().getType() + " ROOM	"  +
					room.getType().getRate() * days + "	"  +
					res.getCheckIn().format(formatter) + " " + res.getScheduledTime().format(f2));
			totalPrice = room.getType().getRate() * days;
			
			//print room service bill
			
			ArrayList al = RoomServiceController.searchRoomServices(room.getRoomNumber());
			for(int s=0;s<al.size();s++) {
				RoomService rs = (RoomService)al.get(s);

				if(rs.getRemarks().equals("UNPAID"))
				{
					System.out.println(	"Room Service	"+
							rs.getOrderedMenu().getName() + "		" + 
							rs.getOrderedMenu().getPrice() + "	" +
							rs.getOrderDate() + " " + rs.getOrderTime());
					totalPrice += rs.getOrderedMenu().getPrice();
				}
			}
		}
		
		//calculate tax and discount (taxable price is calculated after discount)
		double discountPrice = totalPrice * (discount/100);
		totalPrice = totalPrice - discountPrice;
		System.out.println(	"Discount	" + 
				"-" + discount + "%		" +
				"-" + discountPrice + "		" + 
				"nil" + " " +  "nil"); 

		double taxPrice = totalPrice * (tax/100);
		totalPrice = totalPrice + taxPrice;
		System.out.println(	"tax		" + 
				tax + "%		"  +
				taxPrice + "		" + 
				"nil" + " " +  "nil"); 

		System.out.println("Total: " + totalPrice);

		while(true)
		{
			System.out.println("\nConfirm checkout? (Y or N)");
			String confirm = sc.next();
			if(confirm.toUpperCase().equals("Y")) {
				break;
			}
			else if (confirm.toUpperCase().equals("N")) {
				System.out.println("Check out cancelled!");
				return;
			}
			else {
				System.out.println("Invalid Input!");
			}
		}
		for(int i=0;i<res.getReservationRoom().getNoOfRooms();i++) {
			RoomServiceController.updateRoomService(res.getReservationRoom().getRooms().get(i).getRoomNumber());
		}
		ReservationController.checkOut(guestID,roomOutDate);
		System.out.println("Checked Out!");

	}
}
