package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Entity.*;

/**
Represents the controller for using the Reservation class
@author TeamFour
@version 1.0
@since 2018-04-01
*/

public class ReservationController {
	/**
	 * The filename that stores the all reservations' information
	 */
	public static final String FILENAME = "reservation.txt";
	/**
	 * The filename that stores the number of rooms reserved and reserved room numbers
	 */
	public static final String FILENAME1 = "reservationRoom.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * The formatter for date
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	/**
	 * The formatter for time
	 */
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");

	/**
	 * Get all the reservations from reservation.txt and return a list of reservations
	 * @return List of reservations
	 * @throws IOException
	 */
	public static ArrayList readReservations() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			int  reservationCode = Integer.parseInt(star.nextToken().trim());	
			String  guestID = star.nextToken().trim();
			String  billing = star.nextToken().trim();
			LocalDate  checkIn = LocalDate.parse(star.nextToken().trim(), formatter);
			LocalDate  checkOut = LocalDate.parse(star.nextToken().trim(), formatter);
			int  noAdults = Integer.parseInt(star.nextToken().trim());
			int  noChildren= Integer.parseInt(star.nextToken().trim());
			LocalTime scheduledTime=LocalTime.parse(star.nextToken().trim(),f2);
			boolean  walkIn = Boolean.valueOf(star.nextToken().trim());
			String reservationStatus=star.nextToken().trim();

			// create Reservation object from file data
			Reservation r = new Reservation(reservationCode, GuestController.searchGuest(guestID),getReservationRoomDetails(reservationCode),billing,checkIn,checkOut,noAdults,noChildren,scheduledTime,walkIn,reservationStatus);
			// add to Reservation list
			alr.add(r) ;
		}
		return alr ;
	}

	/**
	 * Save the list of reservations in the reservation.txt
	 * @param al List of reservations
	 * @throws IOException
	 */
	public static void saveReservations(List al) throws IOException {
		List alw = new ArrayList() ;// to store Reservation data

		for (int i = 0 ; i < al.size() ; i++) {
			Reservation r = (Reservation)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(r.getReservationCode());
			st.append(SEPARATOR);
			st.append(r.getGuest().getIdNo().trim());
			st.append(SEPARATOR);
			st.append(r.getBilling().trim());
			st.append(SEPARATOR);
			st.append(r.getCheckIn().format(formatter));
			st.append(SEPARATOR);
			st.append(r.getCheckOut().format(formatter));
			st.append(SEPARATOR);
			st.append(r.getNoAdults());
			st.append(SEPARATOR);
			st.append(r.getNoChildren());
			st.append(SEPARATOR);
			st.append(r.getScheduledTime().format(f2));
			st.append(SEPARATOR);
			st.append(r.isWalkIn());
			st.append(SEPARATOR);
			st.append(r.getStatus());
			st.append(SEPARATOR);

			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}

	/**
	 * Get rooms under a reservation code from the reservationRoom.txt and return lists of reserved rooms
	 * @return Lists of reserved rooms
	 * @throws IOException
	 */
	public static ArrayList readReservationRoom() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME1);
		ArrayList alr = new ArrayList() ;// to store Reservation Room data
		

		for (int i = 0 ; i < stringArray.size() ; i++) {
			ArrayList<Room> rooms=new ArrayList<Room>();
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			int  reservationCode = Integer.parseInt(star.nextToken().trim());
			int  noOfRooms = Integer.parseInt(star.nextToken().trim());

			//get the room objects and store it into an arraylist
			for(int c=0;c<noOfRooms;c++) {
				String  roomNumber = star.nextToken().trim();	
				Room r=RoomController.searchRoom(roomNumber);
				rooms.add(r);
			}

			// create ReservationRoom object from file data
			ReservationRoom rr=new ReservationRoom(reservationCode,noOfRooms,rooms);
			// add to ReservationRoom list
			alr.add(rr) ;
		}
		return alr ;
	}

	/**
	 * Get specific ReservationRoom based on Reservation Code from reservationRoom.txt
	 * @param reservationCode Reservation Code
	 * @return 				  Reserved room
	 * @throws IOException
	 */
	public static ReservationRoom getReservationRoomDetails(int reservationCode) throws IOException {
		// TODO Auto-generated method stub
		ReservationRoom r=new ReservationRoom();
		ArrayList rr=readReservationRoom();

		for(int i=0;i<rr.size();i++) {
			r = (ReservationRoom)rr.get(i);
			if(r.getReservationCode()==reservationCode) {
				break;
			}
		}
		return r;

	}
	/**
	 * Get a reservation based on the room number from reservationRoom.txt
	 * @param roomNo  Room Number
	 * @return		  Reservation
	 * @throws IOException
	 */
	public static Reservation getGuestID(String roomNo) throws IOException {
		// TODO Auto-generated method stub
		ReservationRoom r=new ReservationRoom();
		ArrayList rr=readReservationRoom();

		for(int i=0;i<rr.size();i++) {
			r = (ReservationRoom)rr.get(i);
			Reservation res=searchReservationsByCode(r.getReservationCode());
			if(res.getGuest()!=null) {
				for(int c=0;c<r.getNoOfRooms();c++) {
					if(r.getRooms().get(c).getRoomNumber().equals(roomNo)) {
						return res;
					}
				}
			}

		}
		return null;

	}

	/**
	 * Save ReservationRoom to the reservationRoom.txt file
	 * @param al List of ReservationRooms
	 * @throws IOException
	 */
	public static void saveReservationRoom(List al) throws IOException {
		List alw = new ArrayList() ;// to store ReservationRoom data

		for (int i = 0 ; i < al.size() ; i++) {
			ReservationRoom rr = (ReservationRoom)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(rr.getReservationCode());
			st.append(SEPARATOR);
			st.append(rr.getNoOfRooms());
			st.append(SEPARATOR);
			for(int c=0;c<rr.getNoOfRooms();c++) {
				st.append(rr.getRooms().get(c).getRoomNumber());
				st.append(SEPARATOR);
			}


			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME1,alw);
	}

	/**
	 * Update Reservation Status of the selected reservation
	 * @param guestID ID of the guest
	 * @param status  Status of the reservation
	 * @param date    Current Date
	 * @return        If it is successfully updated, the method will return 1, else it will return 0
	 * @throws IOException
	 */
	public static int updateReservation(String guestID,String status,LocalDate date) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getGuest().getIdNo().equals(guestID)&&!r.getStatus().equals("CHECKEDOUT")) {
				r.setStatus(status);
				if(status.equals("CHECKEDIN")) {
					r.setCheckIn(LocalDate.now());
				}
				if(status.equals("CHECKEDOUT")) {
					r.setCheckOut(date);
				}
				reservations.set(i, r);
				result = 1;
				break;
			}
		}
		saveReservations(reservations);

		return result;
	}

	/**
	 * Remove the selected reservation from the reservation.txt file
	 * @param guestID ID of the Guest
	 * @return        If it is successfully updated, the method will return 1, else it will return 0
	 * @throws IOException
	 */
	public static int removeReservation(String guestID) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();
		ArrayList reservationRoom = readReservationRoom();
		ArrayList<Room> rooms=new ArrayList<Room>();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getGuest().getIdNo().equals(guestID)) {
				if(r.getStatus().equals("CONFIRMED")||r.getStatus().equals("INWAITLIST")) {
					reservations.remove(i);
					ReservationRoom rr = (ReservationRoom)reservationRoom.get(i);
					rooms = rr.getRooms();
					reservationRoom.remove(i);
					for(int j=0;j<rooms.size();j++) {
						RoomController.updateRoom(rooms.get(j).getRoomNumber(),"STATUS","VACANT");
					}
					result=1;
					break;
				}
			}
		}
		saveReservations(reservations);
		saveReservationRoom(reservationRoom);

		return result;
	}

	/**
	 * Search for reservation using guest ID from the reservation.txt file
	 * @param guestID ID of the Guest
	 * @return		  Reservation
	 * @throws IOException
	 */
	public static Reservation searchReservations(String guestID) throws IOException{
		Reservation r=new Reservation();
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getGuest().getIdNo().equals(guestID)) {
				if(r1.getStatus().equals("CONFIRMED")||r1.getStatus().equals("CHECKEDIN")) {
					r=r1;
					break;
				}
			}
		}
		return r;
	}
	/**
	 * Search for reservation using reservation code from the reservation.txt file
	 * @param code Reservation Code
	 * @return	   Reservation
	 * @throws IOException
	 */
	public static Reservation searchReservationsByCode(int code) throws IOException{
		Reservation r=new Reservation();
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getReservationCode()==code) {
				if(r1.getStatus().equals("CHECKEDIN")) {
					r=r1;
					break;
				}
			}
		}
		return r;
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

		String roomType="";
		String roomNo= "0";

		//Print out available rooms
		System.out.println("Show by Room Type? (True/False): ");
		roomType=sc.nextLine();
		RoomController.printAvailableRoom(Boolean.valueOf(roomType));

		//Get number of rooms to be reserved
		System.out.println();
		System.out.println("Please enter number of Rooms: ");
		int noOfRooms=sc.nextInt();
		String dummy=sc.nextLine();

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
						g=GuestController.createGuest();
						break;
					}
				}
			}
		}
		else {
			g=GuestController.createGuest();
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
			System.out.println("Please enter Scheduled Check In Date (dd/mm/yyyy): ");

			scheduledDate=LocalDate.parse(sc.nextLine(), formatter);

			System.out.println("Please enter Scheduled Check In Time (hh:mm AM/PM): ");
			scheduledTime=LocalTime.parse(sc.nextLine(),f2);
		}

		//Get the payment method
		System.out.println("Pay by (Credit Card/Cash)? ");
		String billing=sc.nextLine();
		if(billing.equalsIgnoreCase("Credit Card")) {
			billing = "Credit Card";
			if(g.getCreditCard().getCardName()==null) {
				System.out.println("No credit card available!");
				System.out.println("Add a credit card? (Y/N): ");
				choice=sc.nextLine();
				if(choice.equalsIgnoreCase("Y")) {
					GuestController.updateCreditCard(GuestController.createCreditCard(g.getIdNo()));
				}
				else {
					System.out.println("Paying by Cash");
					billing="Cash";
				}
			}
		}

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


		ArrayList reservations=readReservations();
		ArrayList reservationRooms=readReservationRoom();

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
		saveReservations(reservations);
		reservationRooms.add(rr);
		saveReservationRoom(reservationRooms);

		//Updating room status
		if(!isWalkIn) {
			for(int i=0;i<rooms.size();i++) {
				RoomController.updateRoom(rooms.get(i).getRoomNumber(),"STATUS","RESERVED");
			}
		}
		else {
			for(int i=0;i<rooms.size();i++) {
				RoomController.updateRoom(rooms.get(i).getRoomNumber(),"STATUS","OCCUPIED");
			}
		}
		System.out.println("Reservation Created!");
		System.out.println();

		//print receipt
		printReservationReceipt(r);
	}

	/**
	 * Print out reservation receipt
	 * @param r Reservation
	 */
	public static void printReservationReceipt(Reservation r) {
		System.out.print("Guest Name: ");
		System.out.println(r.getGuest().getName());
		System.out.print("Room Number: ");
		for(int i=0;i<r.getReservationRoom().getNoOfRooms();i++) {
			System.out.print("|");
			System.out.print(r.getReservationRoom().getRooms().get(i).getRoomNumber());
			System.out.print("|");
		}
		System.out.println();
		System.out.print("Check In Date: ");
		System.out.println(r.getCheckIn().format(formatter));
		System.out.print("Scheduled Check In Time: ");
		System.out.println(r.getScheduledTime().format(f2));
		System.out.print("Status: ");
		System.out.println(r.getStatus());
		System.out.println();
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

			Reservation r=searchReservations(guestID);

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
					updateReservation(guestID,"CHECKEDIN",null);
					for(int i=0;i<r.getReservationRoom().getRooms().size();i++) {
						RoomController.updateRoom(r.getReservationRoom().getRooms().get(i).getRoomNumber(),"STATUS","OCCUPIED");
					}
					System.out.println("Checked In Successfully!");
					System.out.println();
					break;
				}
				else if(r.getStatus().equals("EXPIRED")) {
					System.out.println("Reservation has Expired, Please make a new Reservation");
					break;
				}

			}
		}

	}
	/**
	 * Check out method to update status of reservation and room
	 * @param guestID      ID of the guest
	 * @param checkOutDate Local Date
	 * @throws IOException
	 */
	public static void checkOut(String guestID,LocalDate checkOutDate) throws IOException {
		Reservation r=searchReservations(guestID);

		updateReservation(guestID,"CHECKEDOUT",checkOutDate);
		for(int i=0;i<r.getReservationRoom().getRooms().size();i++) {
			RoomController.updateRoom(r.getReservationRoom().getRooms().get(i).getRoomNumber(),"STATUS","VACANT");
		}

	}

	/**
	 * Check if reservation is expired and update status accordingly
	 * @throws IOException
	 */
	public static void checkExpiredReservations() throws IOException {

		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getStatus().equals("CONFIRMED")||r1.getStatus().equals("INWAITLIST")) {
				LocalDate nowDate=LocalDate.now();
				LocalTime nowTime=LocalTime.now();

				LocalDate checkInDate=r1.getCheckIn();
				LocalTime scheduledTime=r1.getScheduledTime();

				if(checkInDate.isEqual(nowDate)&&scheduledTime.plusHours(1).isBefore(nowTime)) {
					r1.setStatus("EXPIRED");
					for(int c=0;c<r1.getReservationRoom().getRooms().size();c++) {
						RoomController.updateRoom(r1.getReservationRoom().getRooms().get(c).getRoomNumber(),"STATUS","VACANT");
					}
					reservations.set(i, r1);
				}

			}
		}
		saveReservations(reservations);
	}


}
