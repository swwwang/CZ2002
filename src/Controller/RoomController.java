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
			float  rate = Float.parseFloat(star.nextToken().trim());	// second token
			int  roomNumber = Integer.parseInt(star.nextToken().trim());
			String  bedType = star.nextToken().trim();
			boolean  wifiEnabled = Boolean.valueOf(star.nextToken().trim());
			boolean  facing = Boolean.valueOf(star.nextToken().trim());
			String  smoke = star.nextToken().trim();
			String  status = star.nextToken().trim(); // third token
			// create Professor object from file data
			Room r = new Room(RoomFactory.getRoomType(type), rate,roomNumber,bedType,wifiEnabled,facing,smoke,status);
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
				st.append(SEPARATOR);
				alw.add(st.toString()) ;
			}
			textDB.write(FILENAME,alw);
		}
		public static int updateRoom(int roomNumber) throws IOException{
			int result=0;
			ArrayList rooms=readRooms();

			for(int i=0;i<rooms.size();i++) {
				Room r = (Room)rooms.get(i);
				if(r.getRoomNumber()==roomNumber) {
					//g.setAddress("SIT");
					//g.setCountry("USA");
					rooms.set(i, r);
					break;
				}
			}
			saveRooms(rooms);

			return result;
		}
	public static Room searchRoom(int roomNumber) throws IOException {
		Room r = new Room();
		ArrayList rooms=readRooms();

		for(int i=0;i<rooms.size();i++) {
			r = (Room)rooms.get(i);
			if(r.getRoomNumber()==roomNumber) {
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
		return r;

	}
	public static Room searchRoomAvailability(int roomNumber) throws IOException {
		Room r = new Room();
		ArrayList rooms=readRooms();

		for(int i=0;i<rooms.size();i++) {
			r = (Room)rooms.get(i);
			if(r.getRoomNumber()==roomNumber) {
				Object o=r.getStatus();
				if(o.toString().equals("VACANT")) {
					break;
				}
			}
		}
		return r;

	}

}
