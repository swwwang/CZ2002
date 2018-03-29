package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Guest;
import Entity.Reservation;
import Entity.Room;

public class RoomController {
	public static final String FILENAME = "room.txt";
	public static final String SEPARATOR = "|";
	// an example of reading
	public static ArrayList readRooms() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  type = star.nextToken().trim();	// first token
			// second token
			String  roomNumber = star.nextToken().trim();
			String  bedType = star.nextToken().trim();
			boolean  wifiEnabled = Boolean.valueOf(star.nextToken().trim());
			String  facing = star.nextToken().trim();
			String  smoke = star.nextToken().trim();
			String  status = star.nextToken().trim(); // third token
			// create Professor object from file data
			Room r = new Room(RoomFactory.getRoomType(type),roomNumber,bedType,wifiEnabled,facing,smoke,status);
			// add to Professors list
			alr.add(r) ;
		}
		return alr ;
	}
	// an example of saving
		public static void saveRooms(List al) throws IOException {
			List alw = new ArrayList() ;// to store Guest data

			for (int i = 0 ; i < al.size() ; i++) {
				Room r = (Room)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(r.getType().getType().trim());
				st.append(SEPARATOR);
				st.append(r.getRoomNumber());
				st.append(SEPARATOR);
				st.append(r.getBedType());
				st.append(SEPARATOR);
				st.append(r.isWifiEnabled());
				st.append(SEPARATOR);
				st.append(r.getFacing());
				st.append(SEPARATOR);
				st.append(r.getSmoke());
				st.append(SEPARATOR);
				st.append(r.getStatus());
				st.append(SEPARATOR);
				alw.add(st.toString()) ;
			}
			textDB.write(FILENAME,alw);
		}
		public static int updateRoom(String roomNumber,String status) throws IOException{
			int result=0;
			ArrayList<Room> rooms= readRooms();

			for(int i=0;i<rooms.size();i++) {
				Room r = (Room)rooms.get(i);
				if(r.getRoomNumber().equals(roomNumber)) {
					r.setStatus(status);
					rooms.set(i, r);
					break;
				}
			}
			saveRooms(rooms);

			return result;
		}
	public static Room searchRoom(String roomNumber) throws IOException {
		Room r = new Room();
		ArrayList<Room> rooms= readRooms();

		for(int i=0;i<rooms.size();i++) {
			r = (Room)rooms.get(i);
			if(r.getRoomNumber().equals(roomNumber)) {
				System.out.println(r.getType().getType());
				System.out.println(r.getType().getRate());
				break;
			}
		}
		return r;

	}
	public static boolean checkRoomAvailability(String roomNumber) throws IOException {
		boolean result=false;
		
		Room r = new Room();
		ArrayList<Room> rooms= readRooms();

		for(int i=0;i<rooms.size();i++) {
			r = (Room)rooms.get(i);
			if(r.getRoomNumber().equals(roomNumber)) {
				Object o=r.getStatus();
				if(o.toString().equals("VACANT")) {
					result=true;
					break;
				}
			}
		}
		return result;

	}
	
	public static List checkAvailableRoom() throws IOException {
			
		Room r = new Room();
		ArrayList<Room> rooms= readRooms();
		List<String> avail_room = new ArrayList<String>();
		
		for(int i = 0; i < rooms.size(); i++) {
			r = (Room)rooms.get(i);
			Object o = r.getStatus();
			if(o.toString().equals("VACANT")) {
				avail_room.add(r.getRoomNumber());
			}
		}
		
		return avail_room;
	}
	

}
