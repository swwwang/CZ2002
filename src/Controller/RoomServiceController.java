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
import Entity.MenuItem;
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

			RoomService rs = new RoomService(MenuController.searchMenu(orderedMenu), guestName,RoomController.searchRoom(roomNumber), orderDate,orderTime,remarks,status);

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
		ArrayList al = MenuController.readMenu();
		
		System.out.println("No.	Name		Description		Price");
		System.out.println("=======================================================");
		String space = "";
		for (int i = 0 ; i < al.size() ; i++) {
			MenuItem m = (MenuItem)al.get(i);
			
			//to fix alignment. 
			if(m.getDescription().length() > 13) {
				space = "	";
			}
			else if (m.getDescription().length() < 9){
				space = "			";
			}
			else {
				space = "		";
			}
			
			System.out.println(i + 1 + ".	" + m.getName() + "		" + m.getDescription() + space + m.getPrice());
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

}
