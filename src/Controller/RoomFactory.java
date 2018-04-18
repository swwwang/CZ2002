package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.*;
import RoomTypes.DeluxeRoom;
import RoomTypes.DoubleRoom;
import RoomTypes.SingleRoom;
import RoomTypes.VIPRoom;


/**
Represents the controller for types of Room
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class RoomFactory {
	/**
	 * The filename for file that have all the RoomTypes
	 */
	public static final String FILENAME = "roomType.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";
	
	
	/**
	 * Gets specific room type information
	 * @param roomType type of the room
	 * @return	type of the room
	 * @throws IOException throw input/output exception
	 */
	public static RoomType getRoomType(String roomType) throws IOException{
		ArrayList rooms;
		if(roomType == null){
			return null;
		}		
		else {
			rooms=readRoomRates();
		}
		if(roomType.equalsIgnoreCase("SINGLE")){
			return (RoomType) rooms.get(0);
			//return new SingleRoom();

		} else if(roomType.equalsIgnoreCase("DOUBLE")){
			return (RoomType) rooms.get(1);
			//return new DoubleRoom();

		} else if(roomType.equalsIgnoreCase("DELUXE")){
			return (RoomType) rooms.get(2);
			//return new DeluxeRoom();
		}
		else if(roomType.equalsIgnoreCase("VIPSUITE")){
			return (RoomType) rooms.get(3);
			//return new VIPRoom();
		}

		return null;
	}
	/**
	 * Gets rates of the rooms based on their type
	 * @return list of type of the rooms with rate included
	 * @throws IOException throw input/output exception
	 */
	public static ArrayList readRoomRates() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		ArrayList alr = new ArrayList() ;

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  type = star.nextToken().trim();	// first token
			double  rate = Double.parseDouble(star.nextToken().trim());
			double  weekendRate = Double.parseDouble(star.nextToken().trim());
			
			
			RoomType r = null;
			if(type.equalsIgnoreCase("SINGLE")){
				r= new SingleRoom();
				r.setRate(rate);
				r.setWeekendRate(weekendRate);

			} else if(type.equalsIgnoreCase("DOUBLE")){
				r= new DoubleRoom();
				r.setRate(rate);
				r.setWeekendRate(weekendRate);

			} else if(type.equalsIgnoreCase("DELUXE")){
				r= new DeluxeRoom();
				r.setRate(rate);
				r.setWeekendRate(weekendRate);
			}
			else if(type.equalsIgnoreCase("VIPSUITE")){
				r= new VIPRoom();
				r.setRate(rate);
				r.setWeekendRate(weekendRate);
			}
			
			alr.add(r) ;
		}
		return alr ;
	}
	/**
	 * Add a new room type and new room rate into the roomType.txt
	 * @param al list of types of new rooms with included rates
	 * @throws IOException throw input/output exception
	 */ 
		public static void saveRoomRates(List al) throws IOException {
			List alw = new ArrayList() ;// to store Guest data

			for (int i = 0 ; i < al.size() ; i++) {
				RoomType r = (RoomType)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(r.getType().trim());
				st.append(SEPARATOR);
				st.append(r.getRate());
				st.append(SEPARATOR);
				st.append(r.getWeekendRate());
				st.append(SEPARATOR);
				
				alw.add(st.toString()) ;
			}
			textDB.write(FILENAME,alw);
		}
		
		/**
		 * Update the rate of the existing room type.
		 * @param roomType    Type of the room
		 * @param rate 	      new rate of the room
		 * @param weekendRate new weekend rate of the room
		 * @throws IOException throw input/output exception
		 */
		public static void updateRoomRate(String roomType,double rate,double weekendRate) throws IOException{
			ArrayList rooms=readRoomRates();

			for(int i=0;i<rooms.size();i++) {
				RoomType r = (RoomType)rooms.get(i);
				if(r.getType().equals(roomType)) {
					r.setRate(rate);
					r.setWeekendRate(weekendRate);
					rooms.set(i, r);
					break;
				}
			}
			saveRoomRates(rooms);
		}
	
}
