package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Menu;
import Entity.Room;
import Entity.RoomService;

public class RoomServiceController {
	public static final String FILENAME = "roomService.txt";
	public static final String FILENAME1 = "menu.txt";
	public static final String SEPARATOR = "|";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

	// an example of reading
	public static ArrayList readRoomServices() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  orderedMenu = star.nextToken().trim();
			String guestName=star.nextToken().trim();
			LocalDate  orderDate = LocalDate.parse(star.nextToken().trim(), formatter);	// first token
			LocalTime  orderTime = LocalTime.parse(star.nextToken().trim());	// second token
			String  remarks = star.nextToken().trim();
			String  status = star.nextToken().trim(); // third token
			// create Professor object from file data
			RoomService rs = new RoomService(orderedMenu, guestName,orderDate,orderTime,remarks,status);
			// add to Professors list
			alr.add(rs) ;
		}
		return alr ;
	}
	// an example of saving
	public static void saveRoomsServices(List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

		for (int i = 0 ; i < al.size() ; i++) {
			RoomService rs = (RoomService)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			/*st.append(r.getType());
				st.append(SEPARATOR);
				st.append(r.getRate());
				st.append(SEPARATOR);
				st.append(r.getRoomNumber());
				st.append(SEPARATOR);
				st.append(r.getBedType());
				st.append(SEPARATOR);
				st.append(r.isWifiEnabled());
				st.append(SEPARATOR);
				st.append(r.isFacing());
				st.append(SEPARATOR);
				st.append(r.getSmoke());
				st.append(SEPARATOR);
				st.append(r.getStatus());
				st.append(SEPARATOR);*/
			alw.add(st.toString()) ;
		}
		textDB.write(FILENAME,alw);
	}
	public static int updateRoomService(String guestName) throws IOException{
		int result=0;
		ArrayList roomServices=readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			RoomService rs = (RoomService)roomServices.get(i);
			if(rs.getGuest().getName().equals(guestName)) {
				//g.setAddress("SIT");
				//g.setCountry("USA");
				roomServices.set(i, rs);
				break;
			}
		}
		saveRoomsServices(roomServices);

		return result;
	}
	public static RoomService searchRoomServices(String guestName) throws IOException {
		RoomService rs = new RoomService();
		ArrayList roomServices=readRoomServices();

		for(int i=0;i<roomServices.size();i++) {
			rs = (RoomService)roomServices.get(i);
			if(rs.getGuest().getName().equals(guestName)) {
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
		return rs;

	}
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
