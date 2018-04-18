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

/**
Represents the controller for using the RoomService class
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class RoomServiceController {
	/**
	 * The filename that stores the all room services
	 */
	public static final String FILENAME = "roomService.txt";
	/**
	 * The filename that stores the menu of available room services
	 */
	public static final String FILENAME1 = "menu.txt";
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
	 * Get all room services from roomService.txt and return a list of room services
	 * @return List of room services
	 * @throws IOException throw input/output exception
	 */
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
	
	/**
	 * Save the list of room services in the roomService.txt
	 * @param al List of room services
	 * @throws IOException throw input/output exception
	 */
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
	/**
	 * Update the list of Room Services ordered by a room based on the room number
	 * @param roomNo RoomNumber of the room
	 * @return       if it is successfully updated, it will return 1 else it will return 0
	 * @throws IOException throw input/output exception
	 */
	public static int updateRoomService(String roomNo) throws IOException{
		int result=0;
		ArrayList roomServices = readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			RoomService rs = (RoomService)roomServices.get(i);
			if(rs.getRoom().getRoomNumber().equals(roomNo)) {
				if(rs.getStatus() == "DELIVERED") {
					rs.setPaid("PAID");
				}
				else {
					rs.setPaid("EXPIRED");
				}
				roomServices.set(i, rs);
			}
		}
		saveRoomsServices(roomServices);

		return result;
	}

	/**
	 * Get the list of Room Services ordered by a room based on the room number
	 * @param roomNo RoomNumber of the room
	 * @return       List of Room Services
	 * @throws IOException throw input/output exception
	 */
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

	/**
	 * Display the lists of room services ordered by the a room based on the room number
	 * @param roomNo  Room Number
	 * @return		  Number of unpaid room services
	 * @throws IOException throw input/output exception
	 */
	public static int displayRoomsService(String roomNo) throws IOException {
		System.out.println("\nNo.  Item            Price     Date & TimeStamp      Status      Remarks"); 
		System.out.println("==================================================================================="); 
		
		ArrayList al = RoomServiceController.searchRoomServices(roomNo);
		int rsNum = 0;
		for(int i = 0; i < al.size(); i++) {
			RoomService rs = (RoomService)al.get(rsNum);

			if(rs.getPaid().equals("UNPAID"))
			{
				System.out.printf("%-5d%-16s%-10.2f%s %s   %-12s%-16s\n", (rsNum+1), rs.getOrderedMenu().getName(), 
						rs.getOrderedMenu().getPrice(), rs.getOrderDate().format(formatter), rs.getOrderTime().format(f2),
						rs.getStatus(), rs.getRemarks());
				rsNum++;
			}
		}
		
		return rsNum;
	}
}
