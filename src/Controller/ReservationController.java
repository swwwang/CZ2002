package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Reservation;;

public class ReservationController {
	public static final String FILENAME = "reservation.txt";
	public static final String SEPARATOR = "|";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

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
			int  roomNumber = Integer.parseInt(star.nextToken().trim());
			String  billing = star.nextToken().trim();
			LocalDate  checkIn = LocalDate.parse(star.nextToken().trim(), formatter);
			LocalDate  checkOut = LocalDate.parse(star.nextToken().trim(), formatter);
			int  noGuests = Integer.parseInt(star.nextToken().trim());
			String reservationStatus=star.nextToken().trim();
			LocalTime scheduled=LocalTime.parse(star.nextToken().trim());
			// create Professor object from file data
			Reservation r = new Reservation(reservationCode, guestName,roomNumber,billing,checkIn,checkOut,noGuests,reservationStatus,scheduled);
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
			st.append(r.getCheckIn());
			st.append(SEPARATOR);
			st.append(r.getCheckOut());
			st.append(SEPARATOR);
			st.append(r.getNoGuests());
			st.append(SEPARATOR);
			st.append(r.getStatus());
			st.append(SEPARATOR);
			st.append(r.getScheduled());
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
				//g.setAddress("SIT");
				//g.setCountry("USA");
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
				//g.setAddress("SIT");
				//g.setCountry("USA");
				reservations.remove(i);
				break;
			}
		}
		saveReservations(reservations);

		return result;
	}
	public static Reservation searchReservations(String name) throws IOException{
		Reservation r=new Reservation();
		ArrayList reservations=readReservations();

		for(int i=0;i<reservations.size();i++) {
			r = (Reservation)reservations.get(i);
			if(r.getGuest().getName().equals(name)) {
				/*System.out.println("Name " + g.getName() );
				System.out.println("Credit Card " + g.getCreditCard() );
				System.out.println("Address  " + g.getAddress() );
				System.out.println("Country " + g.getCountry() );
				System.out.println("Gender " + g.getGender() );
				System.out.println("Identity " + g.getIdentity() );
				System.out.println("Nationality " + g.getNationality() );
				System.out.println("Contact " + g.getContact() );*/
				break;
			}
		}
		return r;
	}

}
