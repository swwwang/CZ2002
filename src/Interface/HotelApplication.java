package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.GuestController;
import Controller.MenuController;
import Controller.PaymentController;
import Controller.ReservationController;
import Controller.RoomController;
import Controller.RoomFactory;
import Controller.RoomServiceController;
import Entity.MenuItem;

public class HotelApplication {
	public static void main(String args[]) throws IOException {
		// System.out.println("");
		Scanner sc = new Scanner(System.in);
		int admin_choice, system_choice, guest_choice;
		ReservationController.checkExpiredReservations();
		do {
			System.out.println("Hotel Reservation and Payment System");
			System.out.println("**************************************");
			System.out.println("1.Hotel Administration");
			System.out.println("2.Guest Administration");
			System.out.println("3.Exit");
			System.out.println("Please enter the number of the option that you want to select:");
			admin_choice = sc.nextInt();
			switch (admin_choice) {
			case 1:
				do {
					System.out.println("Welcome to the Hotel Administration System");
					System.out.println("1. Create a new room");
					System.out.println("2. Update room information");
					System.out.println("3. Create a new room service menu item");
					System.out.println("4. Update a room service menu item");
					System.out.println("5. Delete a room service menu item");
					System.out.println("6. Print Room Status Statistic");
					System.out.println("7. Exit");
					System.out.println("Please enter the number of the option that you want to select:");
					system_choice = sc.nextInt();

					switch (system_choice) {
					case 1:
						System.out.println("You have chosen 1. Create a new room. Please proceed>>>>>");
						String con = "Y";
						try {
							List rooms = RoomController.readRooms();
							do {
								rooms.add(RoomController.createRoom());
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
									RoomController.updateRoom(room_number);
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
						String item_cont = "Y";
						System.out
								.println("You have chosen 3. Create a new room service menu item. Please proceed>>>>>");
						do {
							System.out.print("Please enter the name of the new item: ");
							String name = sc.next(); // getting name
							sc.nextLine(); // dummy
							System.out.print("Please enter the description of the new item: ");
							String description = sc.nextLine(); // getting description
							System.out.print("Please enter the price of the new item: ");
							float price = sc.nextFloat(); // getting the price
							MenuController.createItem(name, description, price);
							System.out.println("Do you want to create more room service menu?(Y/N)");
							item_cont = sc.next();
						} while (!item_cont.toUpperCase().equals("N"));
						break;
					case 4:
						System.out.println("You have chosen 4. Update a room service menu item. Please proceed>>>>>");
						String update_cont = "Y";
						do {
							MenuController.updateMenu();
							System.out.println("Do you want to update other menu item?");
							update_cont = sc.next();
						} while (!update_cont.toUpperCase().equals("N"));
						break;
					case 5:
						int delete;
						String delete_cont;
						System.out.println("You have chosen 5. Delete a room service menu item. Please proceed>>>>>");
						do {
							ArrayList menu1 = MenuController.readMenu();
							for (int i = 0; i < menu1.size(); i++) {
								MenuItem item = (MenuItem) menu1.get(i);
								System.out.println((i + 1) + ".) " + item.getName());
							}
							System.out.println("Please enter the number of the menu item that you want to delete:");
							delete = sc.nextInt();
							MenuController.removeItem(delete - 1);
							System.out.println("Do you want to delete another menu item?(Y/N)");
							delete_cont = sc.next();
						} while (!delete_cont.toUpperCase().equals("N"));
						break;
					case 6:
						System.out.println("You have chosen 6. Print Room Status Statistic. Please proceed>>>>>");
						RoomController.printAvailableRoom(true);
						RoomController.printAvailableRoom(false);
						System.out.println("");
						break;
					case 7:
						System.out.println("Back to the Main Menu");
						break;
					default:
						System.out.println("Invalid Choice! Please enter again!");
					}
				} while (system_choice != 7);
				break;

			case 2:
				do {
					System.out.println("Welcome to the Guest Administration System");
					System.out.println("1. CheckIn: WalkIn");
					System.out.println("2. CheckIn: Reservation");
					System.out.println("3. Make a reservation");
					System.out.println("4. Order a room service");
					System.out.println("5. CheckOut");
					System.out.println("6.Exit");
					System.out.println("Please enter the number of the option that you want to select:");
					guest_choice = sc.nextInt();

					switch (guest_choice) {
					case 1:
						ReservationController.createReservation(true);
						break;
					case 2:
						ReservationController.checkIn();
						break;
					case 3:
						ReservationController.createReservation(false);
						break;
					case 4:
						RoomServiceController.newRoomService();
						break;
					case 5:
						PaymentController.printBill();
						break;
					case 6:
						System.out.println("Back to the Main Menu");
						break;
					default:
						System.out.println("Invalid Choice! Please enter again!");
					}
				} while (guest_choice != 6);
				break;

			case 3:
				System.out.println("Thank you for using our system! Have a good day!");
				break;
			default:
				System.out.println("Invalid Choice! Please try again!");
			}
		} while (admin_choice != 3);
	}
}