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
			String  roomNumber = star.nextToken().trim();
			LocalDate  orderDate = LocalDate.parse(star.nextToken().trim(), formatter);	
			LocalTime  orderTime = LocalTime.parse(star.nextToken().trim(), f2);
			String  remarks = star.nextToken().trim();
			String paid= star.nextToken().trim();
			String  status = star.nextToken().trim();

			RoomService rs = new RoomService(MenuController.searchMenu(orderedMenu), RoomController.searchRoom(roomNumber), orderDate,orderTime,remarks,paid,status);

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
			st.append(rs.getRoom().getRoomNumber().trim());
			st.append(SEPARATOR);
			st.append(rs.getOrderDate().format(formatter));
			st.append(SEPARATOR);
			st.append(rs.getOrderTime().format(f2));
			st.append(SEPARATOR);
			st.append(rs.getRemarks().trim());
			st.append(SEPARATOR);
			st.append(rs.getPaid().trim());
			st.append(SEPARATOR);
			st.append(rs.getStatus().trim());
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
						
		//get guest (from roomNo)
		Reservation RS = ReservationController.getGuestID(roomNo);		
		
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

		ArrayList roomService= readRoomServices();
		roomService.add(r);
		saveRoomsServices(roomService);
		System.out.println("Room Service Added!");
	}

	
	public static int displayRoomsService(String roomNo) throws IOException {
		System.out.println("\nNo.	Item            Price     Date & TimeStamp	Status		Remarks"); 
		System.out.println("================================================================================="); 
		
		ArrayList al = RoomServiceController.searchRoomServices(roomNo);
		int rsNum = 0;
		for(rsNum=0;rsNum<al.size();rsNum++) {
			RoomService rs = (RoomService)al.get(rsNum);

			if(rs.getPaid().equals("UNPAID"))
			{
				System.out.println(	(rsNum+1) + ".	"+
						rs.getOrderedMenu().getName() + "		" + 
						String.format("%-10.2f",rs.getOrderedMenu().getPrice()) +
						rs.getOrderDate().format(formatter) + " " + rs.getOrderTime().format(f2) + "	" +
						rs.getStatus() + "	" +
						rs.getRemarks()
						);
			}
		}
		
		return rsNum;
	}
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
		int rsNum = displayRoomsService(roomNo);
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
		ArrayList roomServices = readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			RoomService rs = (RoomService)roomServices.get(i);
			if(rs.getPaid().equals("UNPAID")){
				rsRoom--;
			}
			
			if(rsRoom == 0 ) {
				rs.setStatus(rsStatus);
				roomServices.set(i, rs);
				rsRoom--;
			}
		}
		saveRoomsServices(roomServices);
		System.out.println("Room Service Status updated to '" +rsStatus +"'!");
	}
}
