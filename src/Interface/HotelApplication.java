package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.GuestController;
import Controller.MenuController;
import Controller.PaymentController;
import Controller.RoomController;
import Controller.RoomFactory;
import Controller.RoomServiceController;
import Entity.MenuItem;



public class HotelApplication {
	public static void main(String args[]) {
		//System.out.println("");
		Scanner sc = new Scanner(System.in);
		int admin_choice, system_choice, guest_choice;

		do {
			System.out.println("Hotel Reservation and Payment System");
			System.out.println("**************************************");
			System.out.println("1.Hotel Administration");
			System.out.println("2.Guest Administration");
			System.out.println("3.Exit");
			System.out.println("Please enter the number of the option that you want to select:");
			admin_choice = sc.nextInt();
			switch(admin_choice) {
				case 1: do {
						System.out.println("Welcome to the Hotel Administration System");
						System.out.println("1. Create a new room");
						System.out.println("2. Update room information");
						System.out.println("3. Create a new room service menu");
						System.out.println("4. Update a room service menu");
						System.out.println("5. Delete a room service menu");
						System.out.println("6. Print Room Status Statistic");
						System.out.println("7. Exit");
						System.out.println("Please enter the number of the option that you want to select:");
						system_choice = sc.nextInt();
						
							switch(system_choice) {
								case 1: 
										String con = "Y";
										try {
											List rooms = RoomController.readRooms();
											do {
												rooms.add(RoomController.createRoom());
												System.out.println("Do you want to create more rooms?(Y/N)");
												con = sc.next();
												}while(!con.toUpperCase().equals("N"));
												RoomController.saveRooms(rooms);
										
										}catch(Exception IOException) {
												System.out.println("System Error! Please Contact Admin");
										}
										break;
								
								case 2: int cat_sel,bed_sel,wifi_sel,facing_sel,smoke_sel,status_sel;
										String update_room = "Y";
										String other_cat = "Y";
										do {
										try {
										System.out.println("Please enter the room number of the room that you want to update: ");
										String room_number = sc.next();
										if(RoomController.checkRoomExist(room_number)) {
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
											   switch(cat_sel) {
											   		case 1: 
											   				 do {
											   						System.out.println("Room Satus:");
											   						System.out.println("1. Vacant");
											   						System.out.println("2. Occupied");
											   						System.out.println("3. Reserved");
											   						System.out.println("4. Under Maintence");
											   						System.out.println("Please enter the number of your selected Room Status:");
																	status_sel = sc.nextInt();
																	   switch(status_sel) {
																	   case 1: RoomController.updateRoom(room_number, "STATUS", "VACANT");
																	   		   break;
																	   case 2: RoomController.updateRoom(room_number, "STATUS", "OCCUPIED");
															   		   		   break;
																	   case 3: RoomController.updateRoom(room_number, "STATUS", "RESERVED");
															   		   		   break;
																	   case 4: RoomController.updateRoom(room_number, "STATUS", "UNDERMAINTENANCE");
													   		   		   			break;	   
																	   default: System.out.println("Please select the valid Room Status again!");
																	   }
																  }while(status_sel != 1 && status_sel != 2 && status_sel != 3 && status_sel != 4);
														   		break;
														   		case 2: do {
																	   System.out.println("Bed Types");
																	   System.out.println("1. Single");
																	   System.out.println("2. Double");
																	   System.out.println("3. Master");
																	   System.out.println("Please enter the number of your selected bed type:");
																	   bed_sel = sc.nextInt();
																	   switch(cat_sel) {
																	   case 1: RoomController.updateRoom(room_number, "BEDTYPE", "SINGLE");
																	   		   break;
																	   case 2: RoomController.updateRoom(room_number, "BEDTYPE", "DOUBLE");
															   		   		   break;
																	   case 3: RoomController.updateRoom(room_number, "BEDTYPE", "MASTER");
															   		   		   break;
																	   default: System.out.println("Please select the valid bed type again!");
																	   }
																  }while(bed_sel != 1 && bed_sel != 2 && bed_sel != 3);
											   		   		   	break;
														   		case 3:do {
																	   System.out.println("WIFI Enabled?");
																	   System.out.println("1. true");
																	   System.out.println("2. false");
																	   System.out.println("Please enter the number of your selected choice:");
																	   wifi_sel = sc.nextInt();
																	   switch(wifi_sel) {
																	   case 1: RoomController.updateRoom(room_number, "WIFI", "true");
																	   		   break;
																	   case 2: RoomController.updateRoom(room_number, "WIFI", "false");
															   		   		   break;
																	   default: System.out.println("Please select the valid choice again!");
																	   }
																   }while(wifi_sel != 1 && wifi_sel != 2);
											   		   		   	break;
														   		case 4: do {
																	   System.out.println("Facing Type:");
																	   System.out.println("1. Sea-View");
																	   System.out.println("2. Open-View");
																	   System.out.println("3. City-View");
																	   System.out.println("Please enter the number of your selected facing type:");
																	   facing_sel = sc.nextInt();
																	   switch(facing_sel) {
																	   case 1: RoomController.updateRoom(room_number, "FACING", "SEAVIEW");
																	   		   break;
																	   case 2: RoomController.updateRoom(room_number, "FACING", "OPENVIEW");
															   		   		   break;
																	   case 3: RoomController.updateRoom(room_number, "FACING", "CITYVIEW");
															   		   		   break;
																	   default: System.out.println("Please select the valid facing type again!");
																	   }
																  }while(facing_sel != 1 && facing_sel != 2 && facing_sel != 3);
														   		break;
														   		case 5:  do {
																	   System.out.println("Smoking Allowed?");
																	   System.out.println("1. Yes");
																	   System.out.println("2. No");
																	   System.out.println("Please enter the number of your selected choice:");
																	   smoke_sel = sc.nextInt();
																	   switch(smoke_sel) {
																	   case 1: RoomController.updateRoom(room_number, "SMOKE", "SMOKING");
																	   		   break;
																	   case 2: RoomController.updateRoom(room_number, "SMOKE", "NONSMOKING");
															   		   		   break;
																	   default: System.out.println("Please select the valid choice again!");
																	   }
																   }while(smoke_sel != 1 && smoke_sel != 2);
														   		break;
														   		default: System.out.println("Please select the valid category again!");
														   }
													   		}while(cat_sel != 1 && cat_sel != 2 && cat_sel != 3 && cat_sel != 4 && cat_sel != 5);
												System.out.println("Do you want to update Other Catergory?(Y/N):");
												other_cat = sc.next();
											}while(!other_cat.toUpperCase().equals("N"));
															System.out.println("Do you want to update Other Room?(Y/N):");
															update_room = sc.next();
										}// if room exist statement
										else {
											System.out.println("Invalid Room Number!!!!");
										}
										    } catch(Exception IOException) {
											System.out.println("System Error! Please Contact Admin");
											}
										}while(!update_room.toUpperCase().equals("N"));
										break;
								case 3:
									System.out.print("Input Name: ");
									String name = sc.next(); //getting name
									System.out.print("Input Description: ");
									String description = sc.next(); //getting description
									System.out.print("Input Price: ");
									float price = sc.nextFloat(); //getting the price
									MenuController.createItem(name, description, price);
									
								case 4:
									ArrayList menu1 = MenuController.readMenu();
									for (int i = 0 ; i < menu1.size() ; i++) 
									{
										MenuItem item = (MenuItem)menu1.get(i);
										System.out.println((i+1) + ".) " + item.getName());
									}
									int itemChoice = sc.nextInt();
									MenuItem item = (MenuItem)menu1.get(itemChoice-1);
									System.out.println("1.) Name: " + item.getName());
									System.out.println("2.) Description: " + item.getDescription());
									System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
									int fieldChoice = sc.nextInt();
									switch(fieldChoice)
									{
									case 1://editing name 
										System.out.println("Current Name: " + item.getName());
										System.out.print("Input New Name: ");
										name = sc.next(); //getting new name
										item.setName(name); //editing the name of the object
										break;
									case 2://editing description
										System.out.println("Current Description: " + item.getDescription());
										System.out.print("Input New Description: ");
										description = sc.next(); //getting new description
										item.setDescription(description); //editing the description of the object
										break;
									case 3://editing price
										System.out.println("Current Price: S$" + item.getPrice());
										System.out.print("Input New Price: ");
										price = sc.nextFloat(); //getting the new price
										item.setPrice(price); //editing the price of the object
										break;
									}
									
									break;
								case 5:
								case 6:
								case 7: System.out.println("Back to the Main Menu");
										break;
								default: System.out.println("Invalid Choice! Please enter again!");
							}
						}while(system_choice != 7);
						break;
				
				case 2: do {
						System.out.println("Welcome to the Guest Administration System");
						System.out.println("1. CheckIn: WalkIn");
						System.out.println("2. CheckIn: Reservation");
						System.out.println("3. Make a reservation");
						System.out.println("4. Order a room service");
						System.out.println("5. CheckOut");
						System.out.println("6.Exit");
						System.out.println("Please enter the number of the option that you want to select:");
						guest_choice = sc.nextInt();
						
						switch(guest_choice) {
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6: System.out.println("Back to the Main Menu");
									break;
							default: System.out.println("Invalid Choice! Please enter again!");
						}
						} while(guest_choice != 6);
						break;
				
				case 3: System.out.println("Thank you for using our system! Have a good day!");
						break;
				default:System.out.println("Invalid Choice! Please try again!");
			}
		}while(admin_choice != 3);
	}
}