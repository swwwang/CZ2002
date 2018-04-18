package Interface;
//Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items
//and its total, tax and total amount)

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import Controller.GuestController;
import Controller.ReservationController;
import Controller.RoomController;
import Controller.RoomServiceController;
import Entity.Guest;
import Entity.Reservation;
import Entity.Room;
import Entity.RoomService;

/**
Represents the interface for Payment
@author TeamFour
@version 1.0
@since 2018-04-01
 */
public class PaymentInterface {

	/**
	 * The formatter for date
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	/**
	 * The formatter for time
	 */
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");
	/**
	 * Calculate the bill based on the number of stays by the guest, and ordered room services and 
	 * apply tax and promotion rate to total bill and print out the final bill
	 * @throws IOException throw input/output exception
	 */
	public static void printBill ()  throws IOException {
		Scanner sc = new Scanner(System.in);
		String roomNo;

		//get guest
		String guestID = new String();
		String guestName=new String();
		Reservation res = new Reservation();
		while(true)
		{
			System.out.println("Please enter Guest Name: ");
			guestName=sc.nextLine();
			System.out.println("Please enter Guest ID: ");
			guestID=sc.nextLine();
			if ((GuestController.searchGuest(guestName,guestID.toUpperCase())).getIdNo() == null)
				System.out.println("Enter a valid guest Name & ID!");
			else
			{
				ArrayList<Reservation> r=ReservationController.searchReservations(guestID);
				res = r.get(r.size()-1);
				if (res.getGuest() ==null) {
					System.out.println("No reservation found!");
					return;
				}
				else if(res.getStatus().equals("CHECKEDIN")) {
					System.out.println("Reservation found!");
					System.out.println();
					ReservationController.printReservationReceipt(res);
					break;
				}

			}
		}

		//get date
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

		//get number of weekends
		LocalDate startCal = res.getCheckIn();
		int weekendNum = 0;
		do {
			if (	 startCal.getDayOfWeek().toString() == "SUNDAY" || 
					startCal.getDayOfWeek().toString() == "SATURDAY") {
				weekendNum++;
			}
			startCal = startCal.plusDays(1);
		} while (startCal.until(roomOutDate, ChronoUnit.DAYS) >= 0);


		//get time
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
		System.out.println("\nType            Item            Price     Date & TimeStamp"); 
		System.out.println("============================================================="); 


		double totalPrice=0;
		for(int i=0;i<res.getReservationRoom().getNoOfRooms();i++) {
			//print room bill
			Room room = RoomController.searchRoom(res.getReservationRoom().getRooms().get(i).getRoomNumber());
			System.out.println("Room		" + 
					room.getType().getType() + " ROOM	"  +
					String.format( "%-10.2f", room.getType().getRate() * (days - weekendNum))+
					res.getCheckIn().format(formatter) + " " + res.getScheduledTime().format(f2));

			if(weekendNum != 0) {
				System.out.println("Room (Weekend)	" + 
						room.getType().getType() + " ROOM	"  +
						String.format( "%-10.2f", room.getType().getWeekendRate() * (weekendNum))+
						res.getCheckIn().format(formatter) + " " + res.getScheduledTime().format(f2));
			}

			totalPrice += room.getType().getRate() * (days - weekendNum) + room.getType().getWeekendRate() * weekendNum;

			//print room service bill
			ArrayList al = RoomServiceController.searchRoomServices(room.getRoomNumber());
			for(int s=0;s<al.size();s++) {
				RoomService rs = (RoomService)al.get(s);

				if(rs.getPaid().equals("UNPAID") && rs.getStatus().toString() == "DELIVERED")
				{
					System.out.printf("Room Service    %-16s%-10.2f%s %s\n", rs.getOrderedMenu().getName(), rs.getOrderedMenu().getPrice(), rs.getOrderDate().format(formatter), rs.getOrderTime().format(f2));
					totalPrice += rs.getOrderedMenu().getPrice();
				}
			}
		}

		//calculate tax and discount (taxable price is calculated after discount)
		double discountPrice = totalPrice * (discount/100);
		totalPrice = totalPrice - discountPrice;
		System.out.println(	"Discount	" + 
				"-" + discount + "%		" +
				"-" + String.format( "%-9.2f%19s", discountPrice, "Nil" )); 

		double taxPrice = totalPrice * (tax/100);
		totalPrice = totalPrice + taxPrice;
		System.out.println(	"tax		" + 
				tax + "%		"  +
				String.format( "%-10.2f%19s", taxPrice, "Nil" )); 

		System.out.println();
		System.out.println("Total: $" + String.format( "%.2f", totalPrice ));

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
		System.out.println();
	}
}
