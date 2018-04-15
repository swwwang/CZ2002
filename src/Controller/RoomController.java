package Controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Scanner;

import Entity.Guest;
import Entity.Reservation;
import Entity.Room;

/**
Represents the controller for using the Room class
@author TeamFour
@version 1.0
@since 2018-04-01
*/

public class RoomController {
	/**
	 * The filename that stores all the created rooms
	 */
	public static final String FILENAME = "room.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";

	// an example of reading
	/**
	 * Gets all the rooms from room.txt files and stores them into an ArrayList and return array of rooms.
	 * @return ArrayList of rooms
	 * @throws IOException
	 */
	public static ArrayList readRooms() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) textDB.read(FILENAME);
		ArrayList alr = new ArrayList();// to store Professors data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String type = star.nextToken().trim(); // first token
			// second token
			String roomNumber = star.nextToken().trim();
			String bedType = star.nextToken().trim();
			boolean wifiEnabled = Boolean.valueOf(star.nextToken().trim());
			String facing = star.nextToken().trim();
			String smoke = star.nextToken().trim();
			String status = star.nextToken().trim(); // third token
			// create Professor object from file data
			Room r = new Room(RoomFactory.getRoomType(type), roomNumber, bedType, wifiEnabled, facing, smoke, status);
			// add to Professors list
			alr.add(r);
		}
		return alr;
	}

	/**
	 * Saves the list of rooms in the room.txt files.
	 * @param al  list of created rooms
	 * @throws IOException
	 */
	public static void saveRooms(List al) throws IOException {
		List alw = new ArrayList();// to store Guest data

		for (int i = 0; i < al.size(); i++) {
			Room r = (Room) al.get(i);
			StringBuilder st = new StringBuilder();
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
			alw.add(st.toString());
		}
		textDB.write(FILENAME, alw);
	}
	
	

	/**
	 * Update the properties of the room
	 * @param roomNumber RoomNumber of the room
	 * @param catagory   Category of the room
	 * @param value 	 The new value that will replace the existing value
	 * @return 			 If it is successfully updated, this method will return 1 else it will return 0
	 * @throws IOException
	 */
	public static int updateRoom(String roomNumber, String catagory, String value) throws IOException {
		int result = 0;
		ArrayList<Room> rooms = readRooms();

		for (int i = 0; i < rooms.size(); i++) {
			Room r = (Room) rooms.get(i);
			if (r.getRoomNumber().equals(roomNumber)) {

				switch (catagory.toUpperCase()) {
				case "STATUS":
					r.setStatus(value.toUpperCase());
					rooms.set(i, r);
					result = 1;
					break;
				case "BEDTYPE":
					r.setBedType(value.toUpperCase());
					rooms.set(i, r);
					result = 1;
					break;
				case "WIFI":
					r.setWifiEnabled(Boolean.valueOf(value));
					rooms.set(i, r);
					result = 1;
					break;
				case "FACING":
					r.setFacing(value);
					rooms.set(i, r);
					result = 1;
					break;
				case "SMOKE":
					r.setSmoke(value);
					;
					rooms.set(i, r);
					result = 1;
					break;

				}
				saveRooms(rooms);
			}
		}
		return result;
	}

	

	/**
	 * Searches the specific room inside the room.txt via RoomNumber of the room
	 * @param roomNumber RoomNumber of the room
	 * @return 			 Searched Room
	 * @throws IOException
	 */
	public static Room searchRoom(String roomNumber) throws IOException {
		Room r = new Room();
		ArrayList<Room> rooms = readRooms();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			if (r.getRoomNumber().equals(roomNumber)) {
				break;
			}
		}
		return r;

	}
	
	
	/**
	 * Checks whether the room with given RoomNumber is available
	 * @param roomNumber RoomNumber of the room
	 * @return 			 This method returns true if room is available, else false
	 * @throws IOException
	 */
	public static boolean checkRoomAvailability(String roomNumber) throws IOException {
		boolean result = false;

		Room r = new Room();
		ArrayList<Room> rooms = readRooms();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			if (r.getRoomNumber().equals(roomNumber)) {
				Object o = r.getStatus();
				if (o.toString().equals("VACANT")) {
					result = true;
					break;
				}
			}
		}
		return result;

	}

	/**
	 * Checks whether the room with given RoomNumber exists inside the room.txt
	 * @param roomNumber RoomNumber of the room
	 * @return 			 This method returns true if room exists, else false
	 * @throws IOException
	 */
	public static boolean checkRoomExist(String roomNumber) throws IOException {
		boolean result = false;

		Room r = new Room();
		ArrayList<Room> rooms = readRooms();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			if (r.getRoomNumber().equals(roomNumber)) {
				result = true;
				break;
			}
		}
		return result;

	}
	
	/**
	 * Lists all the rooms of the same condition
	 * @param type Condition of the room
	 * @return     Lists of rooms with same condition      
	 * @throws IOException
	 */
	public static List checkRoom(String type) throws IOException {

		Room r = new Room();
		ArrayList<Room> rooms = readRooms();
		List<String> avail_room = new ArrayList<String>();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			Object o = r.getStatus();
			if (r.getType().getType().equals(type.toUpperCase())||o.toString().equals(type.toUpperCase()))
				avail_room.add(r.getRoomNumber());
		}

		return avail_room;
	}
	
	/**
	 * Prints all the vacant rooms based on the type of the room or prints all the rooms according to their room status
	 * @param ByRoomType if true, this makes methods to print rooms by type of the room. Else, this makes methods to print rooms by room status
	 * @throws IOException
	 */
	public static void printAvailableRoom(Boolean ByRoomType) throws IOException {
		
		// print by room type occupancy rate
		if (ByRoomType) {
			System.out.println("List of vacant room:");
			List<String> roomType = new ArrayList();
			roomType.addAll(Arrays.asList("SINGLE", "DOUBLE", "DELUXE", "VIPSUITE"));
			List vacant = checkRoom("VACANT");

			for (int i = 0; i < roomType.size(); i++) {
				int num = 0;
				int total = 0;
				String print = "";
				List roomList = checkRoom(roomType.get(i));
				for (total = 0; total < roomList.size(); total++) {
					if (vacant.contains(roomList.get(total))) {
						print += roomList.get(total) + " | ";
						num++;
					}
				}
				System.out.println(roomType.get(i) + ": Number" + ": " + num + " out of " + total);
				System.out.println("Rooms: " + print);
			}
		}
		// print by room status
		else {
			System.out.println("List of rooms by room status:");
			List vacant = checkRoom("VACANT");
			List occupied = checkRoom("OCCUPIED");
			List reserved = checkRoom("RESERVED");
			List undermaintenance = checkRoom("UNDERMAINTENANCE");

			System.out.println("Vacant:");
			System.out.print("	Rooms:");
			for (int i = 0; i < vacant.size(); i++) {
				System.out.print(" | " + vacant.get(i));
			}

			System.out.println("\n\nOccupied:");
			System.out.print("	Rooms:");
			for (int i = 0; i < occupied.size(); i++) {
				System.out.print(" | " + occupied.get(i));
			}
			
			System.out.println("\n\nReserved:");
			System.out.print("	Rooms:");
			for (int i = 0; i < reserved.size(); i++) {
				System.out.print(" | " + reserved.get(i));
			}
			
			System.out.println("\n\n UnderMaintenance:");
			System.out.print("	Rooms:");
			for (int i = 0; i < undermaintenance.size(); i++) {
				System.out.print(" | " + undermaintenance.get(i));
			}
			
			System.out.println();
		}
	}


}
