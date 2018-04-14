package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.GuestController;
import Controller.MenuController;
import Controller.ReservationController;
import Controller.RoomController;
import Controller.RoomFactory;
import Controller.RoomServiceController;
import Entity.MenuItem;

/**
Represents the main user interface
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class HotelApplication {
	/**
	 * The main user interface of the Hotel Reservation and Payment System
	 * @param args String arguments
	 * @throws IOException
	 */
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
				RoomInterface.main(null);
				break;

			case 2:
				ReservationInterface.main(null);
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