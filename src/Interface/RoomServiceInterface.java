package Interface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.MenuController;
import Controller.ReservationController;
import Controller.RoomController;
import Controller.RoomServiceController;
import Entity.MenuItem;
import Entity.Reservation;
import Entity.Room;
import Entity.RoomService;

/**
Represents the interface for Room Services
@author TeamFour
@version 1.0
@since 2018-04-01
 */

public class RoomServiceInterface {
	/**
	 * The formatter for date
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	/**
	 * The formatter for time
	 */
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");
	
	/**
	 * Create a new room service
	 * @throws IOException
	 */
	public static void newRoomService() throws IOException {
		Scanner sc=new Scanner(System.in);

		//get room
		String roomNo;
		Room room;
		while(true) {
			System.out.println("Please enter Room Number: ");
			roomNo=sc.next();
			if(!RoomController.checkRoomExist(roomNo)) {
				System.out.println("Enter a valid room!");
			}
			else {
				room=RoomController.searchRoom(roomNo);
				Object o = room.getStatus();
				if (!o.toString().equals("OCCUPIED"))
					System.out.println("Room is vacant!");
				else
					break;
			}
		}
		
		//display Order Menu
		ArrayList al = MenuController.readMenu();
		
		System.out.println("No.     Name            Description                   Price");
		System.out.println("==============================================================");
		String space = "";
		for (int i = 0 ; i < al.size() ; i++) {
			MenuItem m = (MenuItem)al.get(i);
			
			System.out.printf("%d.	%-16s%-30s%-8.2f\n",i+1, m.getName(), m.getDescription(), m.getPrice());
		}

		//get OrderMenu number
		int OrderMenuNum = 0;
		while(true) {
			System.out.println("\nPlease enter type of room service (1 to " + al.size() + "):  ");
			
			if(sc.hasNextInt() && (OrderMenuNum = sc.nextInt()) > 0 && OrderMenuNum < (al.size()+1)){
				break;
	        }
			else {
				sc.nextLine();
				System.out.println("Choose a room service between 1 to " + al.size()  + "!");
			}
		}
		
		//get orderedmenu (from OrderMenuNum)
		MenuItem orderedmenu = (MenuItem)al.get(OrderMenuNum - 1);
		
		//get date and time
		LocalDate roomSerDate;
		int dummyint = 0;
		while(true)
		{
			try {
				System.out.println("Please enter date (dd/mm/yyyy): ");
				if(dummyint == 0) {
					dummyint = 1;
					String dummy=sc.nextLine();
				}
				roomSerDate =LocalDate.parse(sc.nextLine(), formatter);
				break;
			} catch(Exception e) {
				System.out.println("Enter a valid date!");
			}
		}
		
		LocalTime roomSerTime;
		while(true)
		{
			try {
				System.out.println("Please enter time (hh:mm AM/PM): ");
				roomSerTime =LocalTime.parse(sc.nextLine(),f2);
				break;
			} catch(Exception e) {
				System.out.println("Enter a valid time!");
			}
		}
		String choice="";
		String request="Nil";
		System.out.println("Any special request? (Y/N)");
		choice=sc.nextLine();
		if(choice.equalsIgnoreCase("Y")) {
			System.out.println("Please enter request: ");
			request=sc.nextLine();
		}
		RoomService r=new RoomService();
		r.setOrderedMenu(orderedmenu);
		r.setRoom(room);
		r.setOrderDate(roomSerDate);
		r.setOrderTime(roomSerTime);
		r.setRemarks(request);
		r.setPaid("UNPAID");
		r.setStatus("CONFIRMED");

		ArrayList roomService= RoomServiceController.readRoomServices();
		roomService.add(r);
		RoomServiceController.saveRoomsServices(roomService);
		System.out.println("Room Service Added!");
		System.out.println();
	}
	/**
	 * Updated the status of the ordered room services
	 * @throws IOException
	 */
	public static void changeRoomServiceStatus() throws IOException{
		Scanner sc=new Scanner(System.in);

		//get room
		String roomNo;
		Room room;
		while(true) {
			System.out.println("Please enter Room Number(XX-XX): ");
			roomNo=sc.next();
			if(!RoomController.checkRoomExist(roomNo)) {
				System.out.println("Enter a valid room!");
			}
			else {
				room=RoomController.searchRoom(roomNo);
				Object o = room.getStatus();
				if (!o.toString().equals("OCCUPIED"))
					System.out.println("Room is vacant!");
				else
					break;
			}
		}
		//get rsRoom
		int rsNum = RoomServiceController.displayRoomsService(roomNo);
		int rsRoom = 0;
		while(true) {
			System.out.println("\nPlease enter which room service to change (1 to " + rsNum + "):  ");
			
			if(sc.hasNextInt() && (rsRoom = sc.nextInt()) > 0 && rsRoom < (rsNum+1)){
				break;
	        }
			else {
				sc.nextLine();
				System.out.println("Choose a room service between 1 to " + rsNum  + "!");
			}
		}
		
		//get rsStatus
		System.out.println("\nPlease enter which status to change to (1 to 3)");
		System.out.println("1. CONFIRMED\n2. PREPARING\n3. DELIVERED");
		
		int rsStatusChoice = 0;
		String rsStatus = "";
		while(true) {
			if(sc.hasNextInt() && (rsStatusChoice = sc.nextInt()) > 0 && rsStatusChoice < 4){
				switch(rsStatusChoice) {
				case 1:	rsStatus = "CONFIRMED";
					break;
				case 2: rsStatus = "PREPARING";
					break;
				case 3: rsStatus = "DELIVERED";
					break;
				}
				break;
	        }
			else {
				sc.nextLine();
				System.out.println("Choose a status between 1 to 3!");
				System.out.println("\nPlease enter which status to change to (1 to 3)");
			}
		}
		
		//change status based on rsRoom and rsStatus		
		ArrayList roomServices = RoomServiceController.readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			RoomService rs = (RoomService)roomServices.get(i);
			if(rs.getPaid().equals("UNPAID") && rs.getRoom().getRoomNumber().equals(roomNo)){
				rsRoom--;
			}
			
			if(rsRoom == 0 ) {
				rs.setStatus(rsStatus);
				roomServices.set(i, rs);
				rsRoom--;
			}
		}
		RoomServiceController.saveRoomsServices(roomServices);
		System.out.println("Room Service Status updated to '" +rsStatus +"'!");
	}
	/**
	 * Create a new Menu Item
	 * @throws IOException
	 */
	public static void createMenu() throws IOException {
		Scanner sc=new Scanner(System.in);
		String item_cont="";
		do {
			System.out.print("Please enter the name of the new item: ");
			String name = sc.next(); // getting name
			sc.nextLine(); // dummy
			System.out.print("Please enter the description of the new item: ");
			String description = sc.nextLine(); // getting description
			System.out.print("Please enter the price of the new item: ");
			float price = sc.nextFloat(); // getting the price
			MenuItem m=new MenuItem(name,description,price);
			MenuController.createItem(m);
			System.out.println("Item Created!");
			MenuController.printMenuItem(m);
			System.out.println("Do you want to create more room service menu?(Y/N)");
			item_cont = sc.next();
		} while (!item_cont.toUpperCase().equals("N"));
	}
	/**
	 * Updates the menu item
	 * @throws IOException
	 */
	public static void updateMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String cont;
		System.out.println("Menu item lists:");
		ArrayList menu1 = MenuController.readMenu();
		int itemChoice = 0;
		for (int i = 0; i < menu1.size(); i++) //displaying all the menu items for user to choose
		{ 
			MenuItem item = (MenuItem) menu1.get(i);
			System.out.println((i + 1) + ".) " + item.getName());
		}
		while(true)
		{
			System.out.println("Please enter the number of menu item that you want to update:");
			itemChoice = sc.nextInt(); //getting the item to update
			if (itemChoice <=0 || itemChoice > menu1.size())
				System.out.println("Please enter a valid choice!");
			else
				break;
		}
		do {
			MenuItem item = (MenuItem) menu1.get(itemChoice - 1);
			System.out.println("This is your selected menu item.");
			System.out.println("1.) Name: " + item.getName());
			System.out.println("2.) Description: " + item.getDescription());
			System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
			int fieldChoice;
			do {
				System.out.println("Please enter the number of category that you want to edit:");
				fieldChoice = sc.nextInt();
				switch (fieldChoice) {
				case 1:// editing name
					System.out.println("Current menu item name: " + item.getName());
					System.out.print("Please enter a new menu item name: ");
					String name = sc.next(); // getting new name
					item.setName(name); // editing the name of the object
					break;
				case 2:// editing description
					System.out.println("Current menu item description: " + item.getDescription());
					System.out.print("Please enter a new menu item description: ");
					String description = sc.next(); // getting new description
					item.setDescription(description); // editing the description of the object
					break;
				case 3:// editing price
					System.out.println("Current menu item price: S$" + item.getPrice());
					System.out.print("Please enter a new menu item price: ");
					float price = sc.nextFloat(); // getting the new price
					item.setPrice(price); // editing the price of the object
					break;
				default:
					System.out.println("Invalid Choice! Please Try again!");
				}
			} while (fieldChoice <= 0 || fieldChoice > 3);
			System.out.println("You have successfully updated the menu item!!!");
			MenuController.printMenuItem(item);
			System.out.println("Do you want to update other category?(Y/N)");
			cont = sc.next();
		} while (!cont.toUpperCase().equals("N"));

		MenuController.saveMenu(menu1);
	}
	/**
	 * Deleting a Menu Item
	 * @throws IOException
	 */
	public static void deleteMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		int delete;
		String delete_cont;
		do {
			ArrayList menu1 = MenuController.readMenu();
			for (int i = 0; i < menu1.size(); i++) {
				MenuItem item = (MenuItem) menu1.get(i);
				System.out.println((i + 1) + ".) " + item.getName());
			}
			while(true)
			{
				System.out.println("Please enter the number of the menu item that you want to delete:");
				delete = sc.nextInt();
				if (delete <=0 || delete > menu1.size())
					System.out.println("Please enter a valid choice!");
				else
					break;
			}
			MenuController.removeItem(delete - 1);
			System.out.println("Item Deleted!");
			System.out.println("Do you want to delete another menu item?(Y/N)");
			delete_cont = sc.next();
		} while (!delete_cont.toUpperCase().equals("N"));
	}

}
