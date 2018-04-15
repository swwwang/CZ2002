package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.GuestController;
import Entity.CreditCard;
import Entity.Guest;

/**
Represents the interface to accept user input for guest information
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class GuestInterface {

	/**
	 * Search for guest and print out the details
	 * @throws IOException
	 */
	public static void searchGuest() throws IOException {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Guest Name: ");
		String guestName=sc.nextLine();

		System.out.print("Enter Guest ID: ");
		String guestID=sc.nextLine();
		
		Guest g=GuestController.searchGuest(guestName, guestID);
		System.out.println();
		if((g.getName()!=null)) {
			System.out.println("Guest Exist!");
			System.out.println();
			GuestController.printGuest(g);
		}
		else {
			System.out.println("Guest does not Exist!");
		}
	}
	/**
	 * update information of guest based on the name and id
	 * @return 		  return result=1 if update is successful, or else return result=0
	 * @throws IOException
	 */
	public static int updateGuest() throws IOException{
		Scanner sc=new Scanner(System.in);
		
		int result=0;
		ArrayList guests=GuestController.readGuests();
		
		System.out.println("Enter Guest Name: ");
		String guestName=sc.nextLine();

		System.out.print("Enter Guest ID: ");
		String guestID=sc.nextLine();

		for(int i=0;i<guests.size();i++) {
			Guest g = (Guest)guests.get(i);
			String choice="";
			if(g.getName().equals(guestName)&&g.getIdNo().equals(guestID)) {
				do {
					System.out.println("What do you want to update?");
					System.out.println("1. Name");
					System.out.println("2. Credit Card");
					System.out.println("3. Address");
					System.out.println("4. Country");
					System.out.println("5. Gender");
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
						GuestController.updateCreditCard(g.getCreditCard());
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
		GuestController.saveGuests(guests);

		return result;
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

		String identity="";
		do {
			System.out.println("Please enter identity: (Select a number):");
			System.out.println("1. Driving License");
			System.out.println("2. Passport");
			identity=sc.nextLine();
			switch (identity) {
			case "1":
				identity="DrivingLicense";
				break;
			case "2":
				identity="Passport";
				break;
			default:
				System.out.println("Invalid Choice! Try Again");
			}
		}while(!identity.equals("DrivingLicense")&&!identity.equals("Passport"));

		System.out.println("Please enter ID No:");
		String idNo=sc.nextLine();

		System.out.println("Please enter Address:");
		String address=sc.nextLine();

		System.out.println("Please enter contact (8 digits):");
		String contact=sc.nextLine();

		System.out.println("Please enter Gender (M/F):");
		String gender=sc.nextLine();
		
		System.out.println("Please enter Country:");
		String country=sc.nextLine();

		System.out.println("Please enter Nationality:");
		String nationality=sc.nextLine();

		System.out.println("Add a credit card? Y/N");
		String yesNo=sc.nextLine();

		CreditCard c=new CreditCard();

		if(yesNo.toUpperCase().equals("Y")) {
			c=createCreditCard(idNo);
			ArrayList creditCards=GuestController.readCreditCards();
			creditCards.add(c);
			GuestController.saveCreditCards(creditCards);
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
		ArrayList guests=GuestController.readGuests();
		guests.add(g);

		GuestController.saveGuests(guests);
		
		System.out.println("Guest Created!");
		GuestController.printGuest(g);

		return g;
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

}
