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


/**
Represents the controller for using the Guest class
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class GuestController {
	/**
	 * The filename that stores all the created guests
	 */
	public static final String FILENAME = "guest.txt";
	/**
	 * The filename that stores all the credit cards of guests
	 */
	public static final String FILENAME1 = "creditCard.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Read all the guests from guest lists
	 * @return the entire guest lists
	 * @throws IOException
	 */
	public static ArrayList readGuests() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String idNo=star.nextToken().trim();
			String  identity = star.nextToken().trim();
			String  name = star.nextToken().trim();
			String  address = star.nextToken().trim();
			String  country = star.nextToken().trim();
			String  gender = star.nextToken().trim();
			String  nationality = star.nextToken().trim();
			String  contact = star.nextToken().trim();

			// create Guest object from file data
			Guest g = new Guest(name, getCreditCardDetails(name,idNo),address,country,gender,identity,idNo,nationality,contact);

			// add to Guests list
			alr.add(g) ;
		}
		return alr ;
	}

	/**
	 * read all the credit cards from credit card lists
	 * @return all credit card details
	 * @throws IOException
	 */
	public static ArrayList readCreditCards() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME1);
		ArrayList alr = new ArrayList() ;// to store data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String guestID = star.nextToken().trim();
			String  cardName = star.nextToken().trim();
			String  cardNumber = star.nextToken().trim();	
			String  billingAddress = star.nextToken().trim();

			// create CreditCard object from file data
			CreditCard card=new CreditCard(guestID,cardName,cardNumber,billingAddress);

			// add to CreditCards list
			alr.add(card) ;
		}
		return alr ;
	}

	//get credit card object from cardName
	/**
	 * Get all the detailed information from the credit card
	 * @param cardName name on the card
	 * @param guestID  id of the guest 
	 * @return a credit card
	 * @throws IOException
	 */
	public static CreditCard getCreditCardDetails(String cardName,String guestID) throws IOException {
		// TODO Auto-generated method stub
		CreditCard c=new CreditCard();
		ArrayList cards=readCreditCards();

		for(int i=0;i<cards.size();i++) {
			c = (CreditCard)cards.get(i);
			if(c.getCardName().equals(cardName)&&c.getGuestID().equals(guestID)) {
				break;
			}
		}
		return c;
	}
	/**
	 * update the information on the credit card 
	 * @param c credit card
	 * @throws IOException
	 */
	public static void updateCreditCard(CreditCard c) throws IOException {
		CreditCard c1=new CreditCard();
		ArrayList cards=readCreditCards();
		
		for(int i=0;i<cards.size();i++) {
			c1=(CreditCard)cards.get(i);
			if(c1.getGuestID().equals(c.getGuestID())) {
				cards.set(i, c);
				printCreditCard(c);
				break;
			}
		}
		saveCreditCards(cards);
	}
	
	/**
	 * Save all the credit cards in the file
	 * @param al list of credit cards
	 * @throws IOException
	 */
	public static void saveCreditCards(List al) throws IOException {
		List alw = new ArrayList() ;// to store data

		for (int i = 0 ; i < al.size() ; i++) {
			CreditCard c = (CreditCard)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(c.getGuestID().trim());
			st.append(SEPARATOR);
			st.append(c.getCardName().trim());
			st.append(SEPARATOR);
			st.append(c.getCardNumber());
			st.append(SEPARATOR);
			st.append(c.getBillingAddress().trim());
			st.append(SEPARATOR);

			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME1,alw);
	}
	/**
	 * Print Credit Card details
	 * @param c Credit Card object to be printed
	 */
	public static void printCreditCard(CreditCard c) {
		System.out.println("-Printing Credit Card-");
		System.out.print("Credit Card Name: ");
		System.out.println(c.getCardName());
		System.out.print("Credit Card No : ");
		System.out.println(c.getCardNumber());
		System.out.print("Credit Card Biling Address: ");
		System.out.println(c.getBillingAddress());
		System.out.println();
	}

	/**
	 * Save all the guests in the file
	 * @param al list of guest lists
	 * @throws IOException
	 */
	public static void saveGuests(List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

		for (int i = 0 ; i < al.size() ; i++) {
			Guest g = (Guest)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(g.getIdNo());
			st.append(SEPARATOR);
			st.append(g.getIdentity());
			st.append(SEPARATOR);
			st.append(g.getName().trim());
			st.append(SEPARATOR);
			st.append(g.getAddress().trim());
			st.append(SEPARATOR);
			st.append(g.getCountry().trim());
			st.append(SEPARATOR);
			st.append(g.getGender().trim());
			st.append(SEPARATOR);
			st.append(g.getNationality().trim());
			st.append(SEPARATOR);
			st.append(g.getContact().trim());
			st.append(SEPARATOR);
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}

	/**
	 * Search for guest using name and guest id
	 * @param name name of the guest
	 * @param id   id of the guest
	 * @return 	   Guest
	 * @throws IOException
	 */
	public static Guest searchGuest(String name,String id) throws IOException{
		Guest g = new Guest();
		ArrayList guests=readGuests();

		for(int i=0;i<guests.size();i++) {
			Guest g1 = (Guest)guests.get(i);
			if(g1.getName().equals(name)&&g1.getIdNo().equals(id)) {
				g=g1;
				break;
			}
		}
		return g;
	}
	/**
	 * Search for guest using guest id
	 * @param id id of the guest
	 * @return   Guest
	 * @throws IOException
	 */
	public static Guest searchGuest(String id) throws IOException{
		Guest g = new Guest();
		ArrayList guests=readGuests();

		for(int i=0;i<guests.size();i++) {
			Guest g1 = (Guest)guests.get(i);
			if(g1.getIdNo().equals(id)) {
				g=g1;
				break;
			}
		}
		return g;
	}
	/**
	 * Print Guest details
	 * @param g Guest object to be printed
	 */
	public static void printGuest(Guest g) {
		
		System.out.print("Guest Name: ");
		System.out.println(g.getName());
		
		System.out.print("ID No: ");
		System.out.println(g.getIdNo());
		
		System.out.print("Identity Type: ");
		System.out.println(g.getIdentity());
		
		System.out.print("Gender: ");
		System.out.println(g.getGender());
		
		System.out.print("Contact No: ");
		System.out.println(g.getContact());
		
		System.out.print("Country: ");
		System.out.println(g.getCountry());
		
		System.out.print("Nationality: ");
		System.out.println(g.getNationality());
		
		System.out.print("Address: ");
		System.out.println(g.getAddress());
		
		System.out.println();
		printCreditCard(g.getCreditCard());
		
	}
}
