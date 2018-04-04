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

public class RoomController {
	public static final String FILENAME = "room.txt";
	public static final String SEPARATOR = "|";

	// an example of reading
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

	// an example of saving
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

	public static Room createRoom() throws IOException {
		int type_sel, bed_sel, wifi_sel, facing_sel, smoke_sel, status_sel;
		Scanner sc = new Scanner(System.in);
		Room r = new Room();
		do {
			System.out.println("Room Types");
			System.out.println("1. SingleRoom");
			System.out.println("2. DoubleRoom ");
			System.out.println("3. DeluxeRoom");
			System.out.println("4. VIPRoom");
			System.out.println("Please enter the number of your selected room type:");
			type_sel = sc.nextInt();
			switch (type_sel) {
			case 1:
				r.setType(RoomFactory.getRoomType("SINGLE"));
				break;
			case 2:
				r.setType(RoomFactory.getRoomType("DOUBLE"));
				break;
			case 3:
				r.setType(RoomFactory.getRoomType("DELUXE"));
				break;
			case 4:
				r.setType(RoomFactory.getRoomType("VIPSUITE"));
				break;
			default:
				System.out.println("Please select the valid room type again!");
			}
		} while (type_sel != 1 && type_sel != 2 && type_sel != 3 && type_sel != 4);

		System.out.println("Please Enter the room number:");
		String number = sc.next();
		r.setRoomNumber(number);
		do {
			System.out.println("Bed Types");
			System.out.println("1. Single");
			System.out.println("2. Double");
			System.out.println("3. Master");
			System.out.println("Please enter the number of your selected bed type:");
			bed_sel = sc.nextInt();
			switch (bed_sel) {
			case 1:
				r.setBedType("SINGLE");
				break;
			case 2:
				r.setBedType("DOUBLE");
				break;
			case 3:
				r.setBedType("MASTER");
				break;
			default:
				System.out.println("Please select the valid bed type again!");
			}
		} while (bed_sel != 1 && bed_sel != 2 && bed_sel != 3);

		do {
			System.out.println("WIFI Enabled?");
			System.out.println("1. true");
			System.out.println("2. false");
			System.out.println("Please enter the number of your selected choice:");
			wifi_sel = sc.nextInt();
			switch (wifi_sel) {
			case 1:
				r.setWifiEnabled(true);
				break;
			case 2:
				r.setWifiEnabled(false);
				break;
			default:
				System.out.println("Please select the valid choice again!");
			}
		} while (wifi_sel != 1 && wifi_sel != 2);

		do {
			System.out.println("Facing Type:");
			System.out.println("1. Sea-View");
			System.out.println("2. Open-View");
			System.out.println("3. City-View");
			System.out.println("Please enter the number of your selected facing type:");
			facing_sel = sc.nextInt();
			switch (facing_sel) {
			case 1:
				r.setFacing("SEAVIEW");
				break;
			case 2:
				r.setFacing("OPENVIEW");
				break;
			case 3:
				r.setFacing("CITYVIEW");
				break;
			default:
				System.out.println("Please select the valid facing type again!");
			}
		} while (facing_sel != 1 && facing_sel != 2 && facing_sel != 3);

		do {
			System.out.println("Smoking Allowed?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("Please enter the number of your selected choice:");
			smoke_sel = sc.nextInt();
			switch (smoke_sel) {
			case 1:
				r.setSmoke("SMOKING");
				break;
			case 2:
				r.setSmoke("NONSMOKING");
				break;
			default:
				System.out.println("Please select the valid choice again!");
			}
		} while (smoke_sel != 1 && smoke_sel != 2);

		do {
			System.out.println("Room Satus:");
			System.out.println("1. Vacant");
			System.out.println("2. Occupied");
			System.out.println("3. Reserved");
			System.out.println("4. Under Maintence");
			System.out.println("Please enter the number of your selected Room Status:");
			status_sel = sc.nextInt();
			switch (status_sel) {
			case 1:
				r.setStatus("VACANT");
				break;
			case 2:
				r.setStatus("OCCUPIED");
				break;
			case 3:
				r.setStatus("RESERVED");
				break;
			case 4:
				r.setStatus("UNDERMAINTENANCE");
				break;
			default:
				System.out.println("Please select the valid Room Status again!");
			}
		} while (status_sel != 1 && status_sel != 2 && status_sel != 3 && status_sel != 4);

		return r;
	}

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

	public static void updateRoom(String room_number) throws IOException {
		Scanner sc = new Scanner(System.in);
		int cat_sel, bed_sel, wifi_sel, facing_sel, smoke_sel, status_sel;
		String other_cat = "Y";
		do {
			do {
				System.out.println("Categories");
				System.out.println("1. Room Status");
				System.out.println("2. Bed Type");
				System.out.println("3. Wifi Allowance");
				System.out.println("4. Facing");
				System.out.println("5. Smoking Allowance");
				System.out.println("Please enter the number of your category that you want to update:");
				cat_sel = sc.nextInt();
				switch (cat_sel) {
				case 1:
					do {
						System.out.println("Room Satus:");
						System.out.println("1. Vacant");
						System.out.println("2. Occupied");
						System.out.println("3. Reserved");
						System.out.println("4. Under Maintence");
						System.out.println("Please enter the number of your selected Room Status:");
						status_sel = sc.nextInt();
						switch (status_sel) {
						case 1:
							RoomController.updateRoom(room_number, "STATUS", "VACANT");
							break;
						case 2:
							RoomController.updateRoom(room_number, "STATUS", "OCCUPIED");
							break;
						case 3:
							RoomController.updateRoom(room_number, "STATUS", "RESERVED");
							break;
						case 4:
							RoomController.updateRoom(room_number, "STATUS", "UNDERMAINTENANCE");
							break;
						default:
							System.out.println("Please select the valid Room Status again!");
						}
					} while (status_sel != 1 && status_sel != 2 && status_sel != 3 && status_sel != 4);
					break;
				case 2:
					do {
						System.out.println("Bed Types");
						System.out.println("1. Single");
						System.out.println("2. Double");
						System.out.println("3. Master");
						System.out.println("Please enter the number of your selected bed type:");
						bed_sel = sc.nextInt();
						switch (cat_sel) {
						case 1:
							RoomController.updateRoom(room_number, "BEDTYPE", "SINGLE");
							break;
						case 2:
							RoomController.updateRoom(room_number, "BEDTYPE", "DOUBLE");
							break;
						case 3:
							RoomController.updateRoom(room_number, "BEDTYPE", "MASTER");
							break;
						default:
							System.out.println("Please select the valid bed type again!");
						}
					} while (bed_sel != 1 && bed_sel != 2 && bed_sel != 3);
					break;
				case 3:
					do {
						System.out.println("WIFI Enabled?");
						System.out.println("1. true");
						System.out.println("2. false");
						System.out.println("Please enter the number of your selected choice:");
						wifi_sel = sc.nextInt();
						switch (wifi_sel) {
						case 1:
							RoomController.updateRoom(room_number, "WIFI", "true");
							break;
						case 2:
							RoomController.updateRoom(room_number, "WIFI", "false");
							break;
						default:
							System.out.println("Please select the valid choice again!");
						}
					} while (wifi_sel != 1 && wifi_sel != 2);
					break;
				case 4:
					do {
						System.out.println("Facing Type:");
						System.out.println("1. Sea-View");
						System.out.println("2. Open-View");
						System.out.println("3. City-View");
						System.out.println("Please enter the number of your selected facing type:");
						facing_sel = sc.nextInt();
						switch (facing_sel) {
						case 1:
							RoomController.updateRoom(room_number, "FACING", "SEAVIEW");
							break;
						case 2:
							RoomController.updateRoom(room_number, "FACING", "OPENVIEW");
							break;
						case 3:
							RoomController.updateRoom(room_number, "FACING", "CITYVIEW");
							break;
						default:
							System.out.println("Please select the valid facing type again!");
						}
					} while (facing_sel != 1 && facing_sel != 2 && facing_sel != 3);
					break;
				case 5:
					do {
						System.out.println("Smoking Allowed?");
						System.out.println("1. Yes");
						System.out.println("2. No");
						System.out.println("Please enter the number of your selected choice:");
						smoke_sel = sc.nextInt();
						switch (smoke_sel) {
						case 1:
							RoomController.updateRoom(room_number, "SMOKE", "SMOKING");
							break;
						case 2:
							RoomController.updateRoom(room_number, "SMOKE", "NONSMOKING");
							break;
						default:
							System.out.println("Please select the valid choice again!");
						}
					} while (smoke_sel != 1 && smoke_sel != 2);
					break;
				default:
					System.out.println("Please select the valid category again!");
				}
			} while (cat_sel != 1 && cat_sel != 2 && cat_sel != 3 && cat_sel != 4 && cat_sel != 5);
			System.out.println("You have successfully updated the room information!!!!");
			System.out.println("Do you want to update Other Catergory?(Y/N):");
			other_cat = sc.next();
		} while (!other_cat.toUpperCase().equals("N"));
	}

	public static Room searchRoom(String roomNumber) throws IOException {
		Room r = new Room();
		ArrayList<Room> rooms = readRooms();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			if (r.getRoomNumber().equals(roomNumber)) {
				// System.out.println(r.getType().getType());
				// System.out.println(r.getType().getRate());
				break;
			}
		}
		return r;

	}

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

	public static List checkRoom(String type) throws IOException {

		Room r = new Room();
		ArrayList<Room> rooms = readRooms();
		List<String> avail_room = new ArrayList<String>();

		for (int i = 0; i < rooms.size(); i++) {
			r = (Room) rooms.get(i);
			Object o = r.getStatus();
			switch (type.toUpperCase()) {
			case "SINGLE":
				if (/* o.toString().equals("VACANT") && */ r.getType().getType().equals("SINGLE")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "DOUBLE":
				if (/* o.toString().equals("VACANT") && */ r.getType().getType().equals("DOUBLE")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "DELUXE":
				if (/* o.toString().equals("VACANT") && */ r.getType().getType().equals("DELUXE")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "VIPSUITE":
				if (/* o.toString().equals("VACANT") && */ r.getType().getType().equals("VIPSUITE")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "VACANT":
				if (o.toString().equals("VACANT")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "OCCUPIED":
				if (o.toString().equals("OCCUPIED")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "RESERVED":
				if (o.toString().equals("RESERVED")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			case "UNDERMAINTENANCE":
				if (o.toString().equals("UNDERMAINTENANCE")) {
					avail_room.add(r.getRoomNumber());
				}
				break;
			}
		}

		return avail_room;
	}

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
						print += roomList.get(total) + " ";
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
				System.out.print(" " + vacant.get(i));
			}

			System.out.println("\n\nOccupied:");
			System.out.print("	Rooms:");
			for (int i = 0; i < occupied.size(); i++) {
				System.out.print(" " + occupied.get(i));
			}
			
			System.out.println("\n\nReserved:");
			System.out.print("	Rooms:");
			for (int i = 0; i < reserved.size(); i++) {
				System.out.print(" " + occupied.get(i));
			}
			
			System.out.println("\n\n UnderMaintenance:");
			System.out.print("	Rooms:");
			for (int i = 0; i < undermaintenance.size(); i++) {
				System.out.print(" " + occupied.get(i));
			}
			
			System.out.println();
		}
	}

	// public static void main(String[] args) throws IOException {
	// int r = updateRoom("02-03","smoke","NONSMOKING");
	// if(r == 1) {
	// System.out.println("Updated!");
	// }
	// }

}
