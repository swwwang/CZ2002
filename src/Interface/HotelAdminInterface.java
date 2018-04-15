package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.MenuController;
import Controller.RoomController;
import Controller.RoomFactory;
import Controller.RoomServiceController;
import Entity.MenuItem;
import Entity.Room;

/**
Represents the interface for Hotel Administration System
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class HotelAdminInterface {
	/**
	 * The interface for hotel administration system
	 * @param args String Value
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		Scanner sc=new Scanner(System.in);
		int system_choice=0;
		do {
			System.out.println("Welcome to the Hotel Administration System");
			System.out.println("1. Create a new room");
			System.out.println("2. Update room information");
			System.out.println("3. Create a new room service menu item");
			System.out.println("4. Update a room service menu item");
			System.out.println("5. Delete a room service menu item");
			System.out.println("6. Change a room service status");
			System.out.println("7. Print Room Status Statistic");
			System.out.println("8. Exit");
			System.out.println("Please enter the number of the option that you want to select:");
			system_choice = sc.nextInt();

			switch (system_choice) {
			case 1:
				System.out.println("You have chosen 1. Create a new room. Please proceed>>>>>");
				String con = "Y";
				try {
					List rooms = RoomController.readRooms();
					do {
						rooms.add(createRoom());
						System.out.println("Do you want to create more rooms?(Y/N)");
						con = sc.next();
					} while (!con.toUpperCase().equals("N"));
					RoomController.saveRooms(rooms);

				} catch (Exception IOException) {
					System.out.println("System Error! Please Contact Admin");
				}
				break;

			case 2:
				System.out.println("You have chosen 2. Update room information. Please proceed>>>>>");
				String update_room = "Y";
				do {
					try {
						System.out
						.println("Please enter the room number of the room that you want to update: ");
						String room_number = sc.next();
						if (RoomController.checkRoomExist(room_number)) {
							updateRoom(room_number);
							System.out.println("Do you want to update Other Room?(Y/N):");
							update_room = sc.next();
						} // if room exist statement
						else {
							System.out.println("Invalid Room Number!!!!");
						}
					} catch (Exception IOException) {
						System.out.println("System Error! Please Contact Admin");
					}
				} while (!update_room.toUpperCase().equals("N"));
				break;
			case 3:
				System.out
				.println("You have chosen 3. Create a new room service menu item. Please proceed>>>>>");
				RoomServiceInterface.createMenu();
				break;
			case 4:
				System.out.println("You have chosen 4. Update a room service menu item. Please proceed>>>>>");
				String update_cont = "Y";
				do {
					RoomServiceInterface.updateMenu();
					System.out.println("Do you want to update other menu item? (Y/N)");
					update_cont = sc.next();
				} while (!update_cont.toUpperCase().equals("N"));
				break;
			case 5:
				System.out.println("You have chosen 5. Delete a room service menu item. Please proceed>>>>>");
				RoomServiceInterface.deleteMenu();
				break;
			case 6:
				String change_cont = "";
				System.out.println("You have chosen 6. Change a room service status. Please proceed>>>>>");
				do {
					RoomServiceInterface.changeRoomServiceStatus();
					System.out.println("Do you want to change other room service status?(Y/N)");
					change_cont = sc.next();
				} while (!change_cont.toUpperCase().equals("N"));
				break;
			case 7:
				System.out.println("You have chosen 7. Print Room Status Statistic. Please proceed>>>>>");
				RoomController.printAvailableRoom(true);
				RoomController.printAvailableRoom(false);
				System.out.println("");
				break;
			case 8:
				System.out.println("Back to the Main Menu");
				break;
			default:
				System.out.println("Invalid Choice! Please enter again!");
			}
		} while (system_choice != 8);
	}
	/**
	 * Creates a new Room.
	 * @return  A new room
	 * @throws IOException
	 */
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
	/**
	 * Interface for updating the properties of the selected room
	 * @param room_number RoomNumber of the selected room
	 * @throws IOException
	 */
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

}
