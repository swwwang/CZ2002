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

public class ReservationController {
	public static final String FILENAME = "reservation.txt";
	public static final String SEPARATOR = "|";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");

	// an example of reading
	public static ArrayList readReservations() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			int  reservationCode = Integer.parseInt(star.nextToken().trim());	// first token
			String  guestName = star.nextToken().trim();	// second token
			String  roomNumber = star.nextToken().trim();
			String  billing = star.nextToken().trim();
			LocalDate  checkIn = LocalDate.parse(star.nextToken().trim(), formatter);
			LocalDate  checkOut = LocalDate.parse(star.nextToken().trim(), formatter);
			int  noAdults = Integer.parseInt(star.nextToken().trim());
			int  noChildren= Integer.parseInt(star.nextToken().trim());
			LocalTime scheduled=LocalTime.parse(star.nextToken().trim(),f2);
			boolean  walkIn = Boolean.valueOf(star.nextToken().trim());
			String reservationStatus=star.nextToken().trim();
			
			// create Professor object from file data
			Reservation r = new Reservation(reservationCode, GuestController.searchGuest(guestName),RoomController.searchRoom(roomNumber),billing,checkIn,checkOut,noAdults,noChildren,scheduled,walkIn,reservationStatus);
			// add to Professors list
			alr.add(r) ;
		}
		return alr ;
	}

	// an example of saving
	public static void saveReservations(List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

		for (int i = 0 ; i < al.size() ; i++) {
			Reservation r = (Reservation)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(r.getReservationCode());
			st.append(SEPARATOR);
			st.append(r.getGuest().getName().trim());
			st.append(SEPARATOR);
			st.append(r.getRoom().getRoomNumber());
			st.append(SEPARATOR);
			st.append(r.getBilling().trim());
			st.append(SEPARATOR);
			st.append(r.getCheckIn().format(formatter));
			st.append(SEPARATOR);
			if(r.getCheckOut()!=null) {
				st.append(r.getCheckOut().format(formatter));
				st.append(SEPARATOR);
			}
			else {
				st.append(r.getCheckOut());
				st.append(SEPARATOR);
			}
			st.append(r.getNoAdults());
			st.append(SEPARATOR);
			st.append(r.getNoChildren());
			st.append(SEPARATOR);
			st.append(r.getScheduled().format(f2));
			st.append(SEPARATOR);
			st.append(r.isWalkIn());
			st.append(SEPARATOR);
			st.append(r.getStatus());
			st.append(SEPARATOR);
			
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}
	public static int updateReservation(String name) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getGuest().getName().equals(name)) {
				reservations.set(i, r);
				break;
			}
		}
		saveReservations(reservations);

		return result;
	}
	public static int removeReservation(String name) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getGuest().getName().equals(name)) {
				reservations.remove(i);
				break;
			}
		}
		saveReservations(reservations);

		return result;
	}
	public static Reservation searchReservations(String roomNo) throws IOException{
		Reservation r=new Reservation();
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getRoom().getRoomNumber().equals(roomNo)) {
				r=r1;
				break;
			}
		}
		return r;
	}
	public static void createReservation() throws IOException {
		Guest g;
		Scanner sc=new Scanner(System.in);
		
		boolean check=true;
		String roomNo= "0";
		while(check) {
			System.out.println("Please enter Room Number: ");
			roomNo=sc.next();
			if(!RoomController.checkRoomAvailability(roomNo)) {
				System.out.println("Room not available!");
			}
			else {
				break;
			}
		}
		
		Room room=RoomController.searchRoom(roomNo);
		System.out.println("Please enter name:");
		String dummy=sc.nextLine();
		String name=sc.nextLine();
		
		g=GuestController.searchGuest(name);
		if(g.getName()!=null) {
			System.out.println("Guest Exists");
		}
		else {
			System.out.println("Guest not found");
			System.out.println("Please enter identity (Driving License/Passport):");
			String identity=sc.nextLine();
			if(identity.equals("Driving License")) {
				identity="DrivingLicense";
			}
			System.out.println("Please enter ID No:");
			String idNo=sc.nextLine();
			System.out.println("Please enter Address:");
			String address=sc.nextLine();
			System.out.println("Please enter contact (8 digits):");
			String contact=sc.nextLine();
			System.out.println("Please enter Gender (M/F):");
			String gender=sc.nextLine();
			System.out.println("Please enter Nationality:");
			String nationality=sc.nextLine();
			System.out.println("Please enter Country:");
			String country=sc.nextLine();
			System.out.println("Add a credit card? Y/N");
			String yesNo=sc.nextLine();
			CreditCard c=new CreditCard();
			if(yesNo.toUpperCase().equals("Y")) {
				System.out.println("Please enter Credit Card Name:");
				String creditName=sc.nextLine();
				System.out.println("Please enter Credit Card Number (16 digits):");
				String creditNumber=sc.nextLine();
				System.out.println("Please enter Credit Card Billing Address:");
				String creditAddress=sc.nextLine();
				c=new CreditCard(creditName,creditNumber,creditAddress);
				ArrayList creditCards=GuestController.readCreditCards();
				creditCards.add(c);
				GuestController.saveCreditCards(creditCards);
			}
			g=new Guest();
			g.setName(name);
			g.setIdentity(identity.toUpperCase());
			g.setIdNo(idNo);
			g.setAddress(address);
			g.setContact(contact);
			g.setCountry(country);
			g.setGender(gender);
			g.setNationality(nationality);
			g.setCreditCard(c);
			ArrayList guests=GuestController.readGuests();
			guests.add(g);
			GuestController.saveGuests(guests);

		}
		
		System.out.println("Please enter Number of Adults: ");
		int noAdults=sc.nextInt();
		
		System.out.println("Please enter Number of Children: ");
		int noChildren=sc.nextInt();
		
		System.out.println("Please enter Check In Date (dd/mm/yyyy): ");
		dummy=sc.nextLine();
		LocalDate checkIn=LocalDate.parse(sc.nextLine(), formatter);
		
		System.out.println("Please enter Scheduled Check In Time (hh:mm AM/PM): ");
		LocalTime scheduled=LocalTime.parse(sc.nextLine(),f2);
		
		System.out.println("Pay by (Credit Card/Cash)? ");
		String billing=sc.nextLine();
		
		Reservation r=new Reservation();
		r.setGuest(g);
		r.setNoAdults(noAdults);
		r.setNoChildren(noChildren);
		r.setRoom(room);
		r.setBilling(billing);
		r.setCheckIn(checkIn);
		r.setCheckOut(LocalDate.MAX);
		r.setScheduled(scheduled);
		r.setStatus("Confirmed");

		ArrayList reservations=readReservations();
		int newCode=0;
		if(reservations.size()!=0){
			newCode=((Reservation) reservations.get(reservations.size()-1)).getReservationCode();
			
		}
		r.setReservationCode(newCode+1);
		reservations.add(r);
		saveReservations(reservations);
		RoomController.updateRoom(roomNo,"Status", "RESERVED");
		System.out.println("Reservation Created!");
	}

}
