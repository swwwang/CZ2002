package Interface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.GuestController;
import Controller.ReservationController;
import Controller.RoomController;
import Controller.RoomServiceController;
import Entity.Guest;
import Entity.Reservation;
import Entity.ReservationRoom;
import Entity.Room;

/**
Represents the interface for Guest Administration System
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class GuestAdminInterface {
	/**
	 * The formatter for date
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	/**
	 * The formatter for time
	 */
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");

	/**
	 * The interface for Guest Administration System
	 * @param args String Value
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		Scanner sc=new Scanner(System.in);
		int guest_choice=0;
		do {
			System.out.println("Welcome to the Guest Administration System");
			System.out.println("1. CheckIn: WalkIn");
			System.out.println("2. CheckIn: Reservation");
			System.out.println("3. Make a reservation");
			System.out.println("4. Order a room service");
			System.out.println("5. CheckOut");
			System.out.println("6. Search Guest");
			System.out.println("7. Update Guest Information");
			System.out.println("8. Remove a reservation");
			System.out.println("9. Print all reservation");
			System.out.println("10. Exit");
			System.out.println("Please enter the number of the option that you want to select:");
			guest_choice = sc.nextInt();
			String dummy="";
			switch (guest_choice) {
			case 1:
				createReservation(true);
				break;
			case 2:
				ReservationController.checkExpiredReservations();
				checkIn();
				break;
			case 3:
				createReservation(false);
				break;
			case 4:
				RoomServiceInterface.newRoomService();
				break;
			case 5:
				PaymentInterface.printBill();
				break;
			case 6:
				GuestInterface.searchGuest();
				break;
			case 7:
				GuestInterface.updateGuest();
				break;
			case 8:
				removeReservation();
				break;
			case 9:
				ReservationController.printAllReservations();
				break;

			case 10:
				System.out.println("Back to the Main Menu");
				break;
			default:
				System.out.println("Invalid Choice! Please enter again!");
			}
		} while (guest_choice != 10);
	}
	/**
	 * Check in method for non walk-in
	 * @throws IOException
	 */
	public static void checkIn() throws IOException {
		Scanner sc=new Scanner(System.in);

		while(true) {
			System.out.println("Please enter Guest ID: ");
			String guestID=sc.nextLine();

			ArrayList<Reservation> r1=ReservationController.searchReservations(guestID);
			Reservation r=r1.get(r1.size()-1);

			if(r.getReservationCode()<=0||r.getStatus().equals("CHECKEDIN")) {
				System.out.println("Reservation not found!");
				System.out.println("Try Again? (Y/N): ");
				String ans=sc.nextLine();
				if(ans.equalsIgnoreCase("N")){
					return;
				}
			}
			else {
				if(r.getStatus().equals("CONFIRMED")||r.getStatus().equals("INWAITLIST")) {
					ReservationController.updateReservation(guestID,"CHECKEDIN",null);
					for(int i=0;i<r.getReservationRoom().getRooms().size();i++) {
						RoomController.updateRoom(r.getReservationRoom().getRooms().get(i).getRoomNumber(),"STATUS","OCCUPIED");
					}
					System.out.println("Checked In Successfully!");
					System.out.println();
					break;
				}
				else if(r.getStatus().equals("EXPIRED")) {
					System.out.println("Reservation has Expired, Please make a new Reservation");
					System.out.println();
					break;
				}

			}
		}
	}
	/**
	 * Create new reservation for both walk-in and non walk-in
	 * @param isWalkIn  Boolean value true for walk-in and false for non walk-in
	 * @throws IOException
	 */
	public static void createReservation(boolean isWalkIn) throws IOException {
		Guest g=new Guest();
		Scanner sc=new Scanner(System.in);

		ArrayList<Room> rooms=new ArrayList<Room>();

		//String roomType="";
		String roomNo= "0";

		
		//Get number of rooms to be reserved
		System.out.println();
		System.out.println("Please enter number of Rooms: ");
		int noOfRooms=sc.nextInt();
		String dummy=sc.nextLine();
		
		//Print out available rooms
		RoomController.printAvailableRoom(Boolean.valueOf(true));


		//get the selected room and add it into arraylist
		while(noOfRooms>0){
			System.out.println("Please enter Room Number: ");
			roomNo=sc.nextLine();
			if(!RoomController.checkRoomAvailability(roomNo)) {
				System.out.println("Room not available!");
			}
			else {
				Room room=RoomController.searchRoom(roomNo);
				for(int i =0;i<rooms.size();i++)
				{
					Room r = (Room)rooms.get(i);
					if(room.getRoomNumber().equals(r.getRoomNumber()))
					{
						room = null;
						System.out.println("Room already selected!");
					}
				}
				if(room != null)
				{
					rooms.add(room);
					noOfRooms--;
				}
			}
		}

		//check for existing guest
		System.out.println("Guest stayed before? (Y/N): ");
		String choice=sc.nextLine();
		if(choice.equalsIgnoreCase("Y")) {
			while(true) {
				System.out.println("Please enter name:");
				String name=sc.nextLine();

				System.out.println("Please enter ID number:");
				String id=sc.nextLine();

				g=GuestController.searchGuest(name,id);
				if(g.getName()!=null) {
					if(g.getName().equals(name)) {
						System.out.println("Guest Exists!");
						System.out.println();
						break;
					}
				}
				else {
					//Create new guest if not found
					System.out.println("Guest not found!");
					System.out.println("Try Again? (Y/N): ");
					choice=sc.nextLine();
					if(choice.equalsIgnoreCase("N")) {
						g=GuestInterface.createGuest();
						break;
					}
				}
			}
		}
		else {
			g=GuestInterface.createGuest();
		}


		//Get number of adults
		System.out.println("Please enter Number of Adults: ");
		int noAdults=sc.nextInt();

		//Get number of children
		System.out.println("Please enter Number of Children: ");
		int noChildren=sc.nextInt();
		dummy=sc.nextLine();

		//set date and time to current date and time
		LocalDate scheduledDate=LocalDate.now();
		LocalTime scheduledTime=LocalTime.now();

		//if non walk-in ask for date and time
		if(!isWalkIn) {
			do {
				System.out.println("Please enter Scheduled Check In Date (dd/mm/yyyy): ");

				scheduledDate=LocalDate.parse(sc.nextLine(), formatter);

				if(scheduledDate.isBefore(LocalDate.now())) {
					System.out.println("Date cannot be before current date!");
					System.out.println();
				}
				else {
					System.out.println("Please enter Scheduled Check In Time (hh:mm AM/PM): ");
					scheduledTime=LocalTime.parse(sc.nextLine(),f2);

					if(scheduledDate.isEqual(LocalDate.now())) {
						if(scheduledTime.isBefore(LocalTime.now())) {
							System.out.println("Time cannot be before current time!");
							System.out.println();
						}
						else {
							break;
						}
					}
					if(scheduledDate.isAfter(LocalDate.now())) {
						break;
					}
				}

			}while(true);
		}

		System.out.println();

		//Get the payment method
		String billing="";
		do {
			System.out.println("Pay by: (Select a number)");
			System.out.println("1. Credit Card");
			System.out.println("2. Cash");
			billing=sc.nextLine();
			switch (billing) {
			case "1":
				billing = "Credit Card";
				if(g.getCreditCard().getCardName()==null) {
					System.out.println("No credit card available!");
					System.out.println("Add a credit card? (Y/N): ");
					choice=sc.nextLine();
					if(choice.equalsIgnoreCase("Y")) {
						GuestController.updateCreditCard(GuestInterface.createCreditCard(g.getIdNo()));
					}
					else {
						System.out.println("Paying by Cash");
						billing="Cash";
					}
				}
				break;
			case "2":
				billing="Cash";
				break;
			default:
				System.out.println("Invalid Choice! Try Again");
			}
			if(billing.equalsIgnoreCase("Credit Card")) {

			}
		}while(!billing.equals("Credit Card")&&!billing.equals("Cash"));


		//Create Reservation object from gathered data
		Reservation r=new Reservation();
		r.setGuest(g);
		r.setNoAdults(noAdults);
		r.setNoChildren(noChildren);
		r.setBilling(billing);
		if(!isWalkIn) {
			r.setCheckIn(scheduledDate);
			r.setStatus("Confirmed");
		}
		else {
			r.setCheckIn(LocalDate.now());
			r.setStatus("CheckedIn");
			r.setWalkIn(true);
		}
		r.setCheckOut(LocalDate.MAX);
		r.setScheduledTime(scheduledTime);


		ArrayList reservations=ReservationController.readReservations();
		ArrayList reservationRooms=ReservationController.readReservationRoom();

		//Generate new Reservation Code
		int newCode=0;
		if(reservations.size()!=0){
			newCode=((Reservation) reservations.get(reservations.size()-1)).getReservationCode();
		}

		//Saving new data into textfiles
		ReservationRoom rr=new ReservationRoom(newCode+1,rooms.size(),rooms);
		r.setReservationCode(newCode+1);
		r.setReservationRoom(rr);
		reservations.add(r);
		ReservationController.saveReservations(reservations);
		reservationRooms.add(rr);
		ReservationController.saveReservationRoom(reservationRooms);

		//Updating room status
		if(!isWalkIn) {
			for(int i=0;i<rooms.size();i++) {
				RoomController.updateRoom(rooms.get(i).getRoomNumber(),"STATUS","RESERVED");
			}
			System.out.println("Reservation created successfully!");
		}
		else {
			for(int i=0;i<rooms.size();i++) {
				RoomController.updateRoom(rooms.get(i).getRoomNumber(),"STATUS","OCCUPIED");
			}
			System.out.println("Checked in successfully!");
		}
		
		System.out.println();

		//print receipt
		ReservationController.printReservationReceipt(r);
	}
	/**
	 * Remove a reservation using Guest ID
	 * @throws IOException
	 */
	public static void removeReservation() throws IOException {
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter Guest ID: ");
		if(ReservationController.removeReservation(sc.nextLine())==1) {
			System.out.println("Reservation Removed!");
		}
		else {
			System.out.println("No Reservation Found!");
		}
	}


}
