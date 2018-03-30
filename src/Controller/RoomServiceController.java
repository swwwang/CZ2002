package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Entity.CreditCard;
import Entity.Guest;
import Entity.Menu;
import Entity.Reservation;
import Entity.Room;
import Entity.RoomService;

public class RoomServiceController {
	public static final String FILENAME = "roomService.txt";
	public static final String FILENAME1 = "menu.txt";
	public static final String SEPARATOR = "|";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	public static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm a");
	
	public static ArrayList readRoomServices() throws IOException {
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);

			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
			
			String  orderedMenu = star.nextToken().trim();
			String guestName=star.nextToken().trim();
			String  roomNumber = star.nextToken().trim();
			LocalDate  orderDate = LocalDate.parse(star.nextToken().trim(), formatter);	
			LocalTime  orderTime = LocalTime.parse(star.nextToken().trim(), f2);
			String  remarks = star.nextToken().trim();
			String  status = star.nextToken().trim();

			RoomService rs = new RoomService(searchMenu(orderedMenu), guestName,RoomController.searchRoom(roomNumber), orderDate,orderTime,remarks,status);

			alr.add(rs) ;
		}
		return alr ;
	}

	public static void saveRoomsServices(List al) throws IOException {
		List alw = new ArrayList() ;

		for (int i = 0 ; i < al.size() ; i++) {
			RoomService rs = (RoomService)al.get(i);
			StringBuilder st =  new StringBuilder() ;

			st.append(rs.getOrderedMenu().getName());
			st.append(SEPARATOR);
			st.append(rs.getGuest().getName().trim());
			st.append(SEPARATOR);
			st.append(rs.getRoom().getRoomNumber());
			st.append(SEPARATOR);
			st.append(rs.getOrderDate().format(formatter));
			st.append(SEPARATOR);
			st.append(rs.getOrderTime().format(f2));
			st.append(SEPARATOR);
			st.append(rs.getRemarks());
			st.append(SEPARATOR);
			st.append(rs.getStatus());
			st.append(SEPARATOR);
			
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}
	public static int updateRoomService(String roomNo) throws IOException{
		int result=0;
		ArrayList roomServices = readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			RoomService rs = (RoomService)roomServices.get(i);
			if(rs.getRoom().getRoomNumber().equals(roomNo)) {
				rs.setRemarks("PAID");
				roomServices.set(i, rs);
			}
		}
		saveRoomsServices(roomServices);

		return result;
	}

	public static ArrayList searchRoomServices(String roomNo) throws IOException {
		RoomService rs = new RoomService();
		ArrayList<RoomService> rsReturn = new ArrayList<RoomService>();
		ArrayList<RoomService> roomServices=readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			rs = (RoomService)roomServices.get(i);
			if(rs.getRoom().getRoomNumber().equals(roomNo)) {
				rsReturn.add((RoomService) roomServices.get(i));
			}
		}
		return rsReturn;

	}
	
	public static void newRoomService() throws IOException {
		Guest g;
		Scanner sc=new Scanner(System.in);

		//display Order Menu
		ArrayList al = readMenus();
		
		System.out.println("No.	Name		Description		Price");
		System.out.println("===========================================");
		for (int i = 0 ; i < al.size() ; i++) {
			Menu m = (Menu)al.get(i);//got alignment issues here. next time then fix... - QJ
			System.out.println(i + 1 + ".	" + m.getName() + "		" + m.getDescription() + "	" + m.getPrice());
		}

		//get OrderMenu number
		int OrderMenuNum = 0;
		while(true) {
			System.out.println("\nPlease enter type of room service (1 to " + al.size() + "):  ");
			OrderMenuNum = sc.nextInt();
			if(OrderMenuNum < 1 || OrderMenuNum > al.size()) {
				System.out.println("Choose a room service between 1 to " + al.size() + "!");
			}
			else {
				break;
			}
		}
		
		//get orderedmenu (from OrderMenuNum)
		Menu orderedmenu = (Menu)al.get(OrderMenuNum - 1);
		
		//get room
		String roomNo;
		while(true) {
			System.out.println("Please enter Room Number: ");
			roomNo=sc.next();
			if(!RoomController.checkRoomExist(roomNo)) {
				System.out.println("Enter a valid room!");
			}
			else if(RoomController.checkRoomAvailability(roomNo)) {
				System.out.println("Room is vacant!");
			}
			else {
				break;
			}
		}
		Room room=RoomController.searchRoom(roomNo);
		
		//get guest (from roomNo)
		Reservation RS = ReservationController.searchReservations(roomNo);
		Guest guest = RS.getGuest();
		
		//get date and time
		System.out.println("Please enter date (dd/mm/yyyy): ");
		String dummy=sc.nextLine();
		LocalDate roomSerDate =LocalDate.parse(sc.nextLine(), formatter);
		
		System.out.println("Please enter time (hh:mm AM/PM): ");
		LocalTime roomSerTime =LocalTime.parse(sc.nextLine(),f2);
		

		RoomService r=new RoomService();
		r.setOrderedMenu(orderedmenu);
		r.setGuest(guest);
		r.setRoom(room);
		r.setOrderDate(roomSerDate);
		r.setOrderTime(roomSerTime);
		r.setRemarks("UNPAID");
		r.setStatus("CONFIRMED");

		ArrayList roomService= readRoomServices();
		roomService.add(r);
		saveRoomsServices(roomService);
		System.out.println("Room Service Added!");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static ArrayList readMenus() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME1);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  name = star.nextToken().trim();
			String description=star.nextToken().trim();
			float price=Float.parseFloat(star.nextToken().trim()); // third token
			// create Professor object from file data
			Menu m = new Menu(name, description,price);
			// add to Professors list
			alr.add(m) ;
		}
		return alr ;
	}
	// an example of saving
	public static void saveMenus(List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

		for (int i = 0 ; i < al.size() ; i++) {
			Menu m = (Menu)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(m.getName().trim());
			st.append(SEPARATOR);
			st.append(m.getDescription().trim());
			st.append(SEPARATOR);
			st.append(m.getPrice());
			st.append(SEPARATOR);
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME1,alw);
	}
	public static int updateMenu(String name) throws IOException{
		int result=0;
		ArrayList menus=readMenus();

		for(int i=0;i<menus.size();i++) {
			Menu m = (Menu)menus.get(i);
			if(m.getName().equals(name)) {
				//g.setAddress("SIT");
				//g.setCountry("USA");
				menus.set(i, m);
				break;
			}
		}
		saveMenus(menus);

		return result;
	}
	public static int removeMenu(String name) throws IOException{
		int result=0;
		ArrayList menus=readMenus();

		for(int i=0;i<menus.size();i++) {
			Menu m = (Menu)menus.get(i);
			if(m.getName().equals(name)) {
				//g.setAddress("SIT");
				//g.setCountry("USA");
				menus.remove(i);
				break;
			}
		}
		saveMenus(menus);

		return result;
	}
	public static Menu searchMenu(String name) throws IOException {
		Menu m = new Menu();
		ArrayList menus=readMenus();

		for(int i=0;i<menus.size();i++) {
			m = (Menu)menus.get(i);
			if(m.getName().equals(name)) {
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
		return m;

	}
}
