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
	 * @throws IOException throw input/output exception
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
	 * @throws IOException throw input/output exception
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
	 * @throws IOException throw input/output exception
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
	 * @throws IOException throw input/output exception
	 */
	public static ReservationRoom getReservationRoomDetails(int reservationCode) throws IOException {
		// TODO Auto-generated method stub
		ReservationRoom r=new ReservationRoom();
		ArrayList rr=readReservationRoom();

		for(int i=0;i<rr.size();i++) {
			ReservationRoom r1 = (ReservationRoom)rr.get(i);
			if(r1.getReservationCode()==reservationCode) {
				r=r1;
				break;
			}
		}
		return r;

	}

	/**
	 * Save ReservationRoom to the reservationRoom.txt file
	 * @param al List of ReservationRooms
	 * @throws IOException throw input/output exception
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
	 * @param reservationCode Code of the reservation
	 * @return        If it is successfully updated, the method will return 1, else it will return 0
	 * @throws IOException throw input/output exception
	 */
	public static int updateReservation(String guestID,String status,LocalDate date,int reservationCode) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getReservationCode()==reservationCode&&r.getGuest().getIdNo().equals(guestID)&&!r.getStatus().equals("CHECKEDOUT")) {
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
	 * @throws IOException throw input/output exception
	 */
	public static int removeReservation(String guestID) throws IOException{
		int result=0;
		ArrayList reservations=readReservations();
		ArrayList reservationRoom = readReservationRoom();
		ArrayList<Room> rooms=new ArrayList<Room>();

		for(int i=0;i<reservations.size();i++) {
			Reservation r = (Reservation)reservations.get(i);
			if(r.getGuest().getIdNo().equals(guestID)) { //finds the Reservation by the same guestID
				if(r.getStatus().equals("CONFIRMED")||r.getStatus().equals("INWAITLIST")) { //Reservation that is still in the system
					reservations.remove(i); //removes the reservation from the file
					ReservationRoom rr = (ReservationRoom)reservationRoom.get(i); //gets the list of rooms reserved in this reservations
					rooms = rr.getRooms();
					reservationRoom.remove(i); //remove the room list from the file
					for(int j=0;j<rooms.size();j++) {
						RoomController.updateRoom(rooms.get(j).getRoomNumber(),"STATUS","VACANT"); //changing room status from RESERVED back to VACANT
					}
					result=1;
					System.out.println();
					printReservationReceipt(r);
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
	 * @throws IOException throw input/output exception
	 */
	public static ArrayList<Reservation> searchReservations(String guestID) throws IOException{
		ArrayList<Reservation> guestReservation=new ArrayList<Reservation>();

		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getGuest().getIdNo().equals(guestID)) {
				guestReservation.add(r1);
			}
		}
		return guestReservation;
	}

	/**
	 * Print out reservation receipt
	 * @param r Reservation
	 */
	public static void printReservationReceipt(Reservation r) {
		System.out.print("Reservation Code: ");
		System.out.println(r.getReservationCode());
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
	 * Print all Non Expired Reservations in the text file
	 * @throws IOException throw input/output exception
	 */
	public static void printAllReservations() throws IOException {
		ArrayList reservations=readReservations();

		System.out.print("-Printing All Reservations-");
		System.out.println();

		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(!r1.getStatus().equals("EXPIRED")) {
				printReservationReceipt(r1);
			}
		}
	}

	/**
	 * Check out method to update status of reservation and room
	 * @param guestID      ID of the guest
	 * @param checkOutDate Local Date
	 * @throws IOException throw input/output exception
	 */
	public static void checkOut(String guestID,LocalDate checkOutDate) throws IOException {
		ArrayList<Reservation> r=searchReservations(guestID);
		Reservation r1=new Reservation();
		
		for(int i=0;i<r.size();i++) {
			if(r.get(i).getStatus().equals("CHECKEDIN")) {
				r1=r.get(i);
				break;
			}
		}

		updateReservation(guestID,"CHECKEDOUT",checkOutDate,r1.getReservationCode());
		for(int c=0;c<r1.getReservationRoom().getRooms().size();c++) {
			RoomController.updateRoom(r1.getReservationRoom().getRooms().get(c).getRoomNumber(),"STATUS","VACANT");
		}

	}

	/**
	 * Check if reservation is expired and update status accordingly
	 * @throws IOException throw input/output exception
	 */
	public static void checkExpiredReservations() throws IOException {
		int count=0;

		ArrayList reservations=readReservations();

		System.out.println("-Expired Reservations-");


		for(int i=0;i<reservations.size();i++) {
			Reservation r1 = (Reservation)reservations.get(i);
			if(r1.getStatus().equals("CONFIRMED")||r1.getStatus().equals("INWAITLIST")) {
				LocalDate nowDate=LocalDate.now();
				LocalTime nowTime=LocalTime.now();

				LocalDate checkInDate=r1.getCheckIn();
				LocalTime scheduledTime=r1.getScheduledTime();

				if(checkInDate.isEqual(nowDate)&&scheduledTime.plusHours(1).isBefore(nowTime)||checkInDate.isBefore(nowDate)) {
					r1.setStatus("EXPIRED");
					for(int c=0;c<r1.getReservationRoom().getRooms().size();c++) {
						RoomController.updateRoom(r1.getReservationRoom().getRooms().get(c).getRoomNumber(),"STATUS","VACANT");
					}
					reservations.set(i, r1);
					printReservationReceipt(r1);
					count++;
				}

			}
		}
		saveReservations(reservations);
		if(count==0) {
			System.out.println("None");
			System.out.println();
		}
	}


}
