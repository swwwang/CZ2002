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
Represents the controller for using the Room class
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
	private static CreditCard getCreditCardDetails(String cardName,String guestID) throws IOException {
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
				break;
			}
		}
		saveCreditCards(cards);
	}
	/**
	 * create a new credit card 
	 * @param guestID ID of the guest 
	 * @return Credit Card
	 * @throws IOException
	 */
	public static CreditCard createCreditCard(String guestID) throws IOException {
		Scanner sc=new Scanner(System.in);

		System.out.println("Please enter Credit Card Name:");
		String creditName=sc.nextLine();

		System.out.println("Please enter Credit Card Number (16 digits):");
		String creditNumber=sc.nextLine();

		System.out.println("Please enter Credit Card Billing Address:");
		String creditAddress=sc.nextLine();

		CreditCard c=new CreditCard(guestID,creditName,creditNumber,creditAddress);

		return c;
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
	 * update information of guest based on the name and id
	 * @param name 	  name of the guest
	 * @param guestID id of the guest
	 * @return 		  return result=1 if update is successful, or else return result=0
	 * @throws IOException
	 */
	public static int updateGuest(String name,String guestID) throws IOException{
		Scanner sc=new Scanner(System.in);
		int result=0;
		ArrayList guests=readGuests();

		for(int i=0;i<guests.size();i++) {
			Guest g = (Guest)guests.get(i);
			String choice="";
			if(g.getName().equals(name)&&g.getIdNo().equals(guestID)) {
				do {
					System.out.println("What do you want to update?");
					System.out.println("1. Name");
					System.out.println("2. Credit Card");
					System.out.println("3. Address");
					System.out.println("4. Country");
					System.out.println("5. Gender");
					//System.out.println("6. Identity Type");
					//System.out.println("7. Identity Number");
					System.out.println("6. Nationality");
					System.out.println("7. Contact");
					System.out.println("8. Exit");
					System.out.println("Please enter the number of the option that you want to select:");
					choice=sc.nextLine();
					switch (choice) {
					case "1":
						System.out.println("Enter new Name: ");
						g.setName(sc.nextLine());
						break;
					case "2":
						g.setCreditCard(createCreditCard(guestID));
						updateCreditCard(g.getCreditCard());
						break;
					case "3":
						System.out.println("Enter new Address: ");
						g.setAddress(sc.nextLine());
						break;
					case "4":
						System.out.println("Enter new Country: ");
						g.setCountry(sc.nextLine());
						break;
					case "5":
						System.out.println("Enter Gender (M/F): ");
						g.setGender(sc.nextLine());
						break;
					case "6":
						System.out.println("Enter new Nationality");
						g.setNationality(sc.nextLine());
						break;
					case "7":
						System.out.println("Enter new Contact No :");
						g.setContact(sc.nextLine());
						break;
					default:
						System.out.println("Invalid Choice!");
					}
				}while(!choice.equalsIgnoreCase("8"));
				guests.set(i, g);
				result=1;
				break;
			}
		}
		saveGuests(guests);

		return result;
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
	 * Create a guest if not found in the file
	 * @return Guest
	 * @throws IOException
	 */
	public static Guest createGuest() throws IOException {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("-Creating New Guest-");
		System.out.println("Please enter Name: ");
		String name=sc.nextLine();
		
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
			c=createCreditCard(idNo);
			ArrayList creditCards=readCreditCards();
			creditCards.add(c);
			saveCreditCards(creditCards);
		}

		Guest g=new Guest();
		g.setName(name);
		g.setIdentity(identity.toUpperCase());
		g.setIdNo(idNo);
		g.setAddress(address);
		g.setContact(contact);
		g.setCountry(country);
		g.setGender(gender);
		g.setNationality(nationality);
		g.setCreditCard(c);
		ArrayList guests=readGuests();
		guests.add(g);

		saveGuests(guests);

		return g;
	}



}
