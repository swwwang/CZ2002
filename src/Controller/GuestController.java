package Controller;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Entity.CreditCard;
import Entity.Guest;

public class GuestController {
	public static final String FILENAME = "guest.txt";
	public static final String FILENAME1 = "creditCard.txt";
	public static final String SEPARATOR = "|";

	// an example of reading
	public static ArrayList readGuests() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  name = star.nextToken().trim();	// first token
			String  creditCard = star.nextToken().trim();	// second token
			String  address = star.nextToken().trim();
			String  country = star.nextToken().trim();
			String  gender = star.nextToken().trim();
			String  identity = star.nextToken().trim();
			String  nationality = star.nextToken().trim();
			int  contact = Integer.parseInt(star.nextToken().trim()); // third token
			// create Professor object from file data
			
			Guest g = new Guest(name, getCreditCardDetails(creditCard),address,country,gender,identity,nationality,contact);
			// add to Professors list
			alr.add(g) ;
		}
		return alr ;
	}
	public static ArrayList readCreditCards() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME1);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  cardName = star.nextToken().trim();	// first token
			int  cardNumber = Integer.parseInt(star.nextToken().trim());	// second token
			String  billingAddress = star.nextToken().trim();// third token
			// create Professor object from file data
			CreditCard card=new CreditCard(cardName,cardNumber,billingAddress);
			// add to Professors list
			alr.add(card) ;
		}
		return alr ;
	}
	private static CreditCard getCreditCardDetails(String cardName) throws IOException {
		// TODO Auto-generated method stub
		CreditCard c=new CreditCard();
		ArrayList cards=readCreditCards();

		for(int i=0;i<cards.size();i++) {
			c = (CreditCard)cards.get(i);
			if(c.getCardName().equals(cardName)) {
				break;
			}
		}
		return c;
	}

	// an example of saving
	public static void saveGuests(List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

		for (int i = 0 ; i < al.size() ; i++) {
			Guest g = (Guest)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(g.getName().trim());
			st.append(SEPARATOR);
			st.append(g.getCreditCard().getCardName().trim());
			st.append(SEPARATOR);
			st.append(g.getAddress().trim());
			st.append(SEPARATOR);
			st.append(g.getCountry().trim());
			st.append(SEPARATOR);
			st.append(g.getGender().trim());
			st.append(SEPARATOR);
			st.append(g.getIdentity().trim());
			st.append(SEPARATOR);
			st.append(g.getNationality().trim());
			st.append(SEPARATOR);
			st.append(g.getContact());
			st.append(SEPARATOR);
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}
	public static int updateGuest(String name) throws IOException{
		int result=0;
		ArrayList guests=readGuests();

		for(int i=0;i<guests.size();i++) {
			Guest g = (Guest)guests.get(i);
			if(g.getName().equals(name)) {
				g.setAddress("SIT");
				g.setCountry("USA");
				guests.set(i, g);
				break;
			}
		}
		saveGuests(guests);

		return result;
	}
	public static Guest searchGuest(String name) throws IOException{
		Guest g = new Guest();
		ArrayList guests=readGuests();

		for(int i=0;i<guests.size();i++) {
			g = (Guest)guests.get(i);
			if(g.getName().equals(name)) {
				System.out.println("Name " + g.getName() );
				System.out.println("Credit Card " + g.getCreditCard() );
				System.out.println("Address  " + g.getAddress() );
				System.out.println("Country " + g.getCountry() );
				System.out.println("Gender " + g.getGender() );
				System.out.println("Identity " + g.getIdentity() );
				System.out.println("Nationality " + g.getNationality() );
				System.out.println("Contact " + g.getContact() );
				break;
			}
		}
		return g;
	}

}
