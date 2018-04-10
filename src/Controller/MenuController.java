package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Entity.Menu;
import Entity.MenuItem;
/**
  Represents the controller for using the Menu class
  @author Kaw Teck Lin
  @version 1.0
  @since 2018-04-01
 */
public class MenuController {

	/**
	 * The filename for saving the entire room service Menu
	 */
	public static final String FILENAME = "menu.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Reads the entire menu from the file
	 * @return the entire room service Menu
	 * @throws IOException
	 */
	public static ArrayList readMenu() throws IOException {
		Menu menu1 = new Menu();
		return menu1.getMenu();
	}

	/**
	 * Searches for the details of the menu item through the name
	 * @param name the name of the menu item
	 * @return the menu item object found through the name
	 * @throws IOException
	 */
	public static MenuItem searchMenu(String name) throws IOException {
		MenuItem m = new MenuItem();
		ArrayList menu1 = readMenu();

		for (int i = 0; i < menu1.size(); i++) {
			m = (MenuItem) menu1.get(i);
			if (m.getName().equals(name)) {
				break;
			}
		}
		return m;
	}

	/**
	 * Creates a new menu item with the name, description and price given
	 * @param name Name of the menu item created
	 * @param description Description of the menu item created
	 * @param price Price of the menu item created
	 * @throws IOException
	 */
	public static void createItem(String name, String description, float price) throws IOException {
		FileWriter out = new FileWriter(FILENAME, true);
		StringBuilder st = new StringBuilder();
		st.append(name.trim());
		st.append(SEPARATOR);
		st.append(description.trim());
		st.append(SEPARATOR);
		st.append(price); // create string to write to file
		try {
			out.write(st.toString() + "\n"); // append the string to the end of file
		} finally {
			out.close();
		}
	}
	
	/**
	 * Updates the menu item
	 * @throws IOException
	 */
	public static void updateMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		String cont;
		System.out.println("Menu item lists:");
		ArrayList menu1 = MenuController.readMenu();
		for (int i = 0; i < menu1.size(); i++) //displaying all the menu items for user to choose
		{ 
			MenuItem item = (MenuItem) menu1.get(i);
			System.out.println((i + 1) + ".) " + item.getName());
		}
		System.out.println("Please enter the number of menu item that you want to update:");
		int itemChoice = sc.nextInt(); //getting the item to update
		do {
			MenuItem item = (MenuItem) menu1.get(itemChoice - 1);
			System.out.println("This is your selected menu item.");
			System.out.println("1.) Name: " + item.getName());
			System.out.println("2.) Description: " + item.getDescription());
			System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
			int fieldChoice;
			do {
				System.out.println("Please enter the number of category that you want to edit:");
				fieldChoice = sc.nextInt();
				switch (fieldChoice) {
				case 1:// editing name
					System.out.println("Current menu item name: " + item.getName());
					System.out.print("Please enter a new menu item name: ");
					String name = sc.next(); // getting new name
					item.setName(name); // editing the name of the object
					break;
				case 2:// editing description
					System.out.println("Current menu item description: " + item.getDescription());
					System.out.print("Please enter a new menu item description: ");
					String description = sc.next(); // getting new description
					item.setDescription(description); // editing the description of the object
					break;
				case 3:// editing price
					System.out.println("Current menu item price: S$" + item.getPrice());
					System.out.print("Please enter a new menu item price: ");
					float price = sc.nextFloat(); // getting the new price
					item.setPrice(price); // editing the price of the object
					break;
				default:
					System.out.println("Invalid Choice! Please Try again!");
				}
			} while (fieldChoice != 1 && fieldChoice != 2 && fieldChoice != 3);
			System.out.println("You have successfully updated the menu item!!!");
			System.out.println("Do you want to update other category?(Y/N)");
			cont = sc.next();
		} while (!cont.toUpperCase().equals("N"));

		saveMenu(menu1);
	}

	/**
	 * Removes an item from the menu at the index given
	 * @param index The index of the item to remove from the menu
	 * @throws IOException
	 */
	public static void removeItem(int index) throws IOException {
		ArrayList list = readMenu();
		list.remove(index);
		saveMenu(list);
	}

	/**
	 * Saves the entire Menu into the file
	 * @param list The list of menu items to be saved into the file
	 * @throws IOException
	 */
	public static void saveMenu(ArrayList list) throws IOException {
		FileWriter out = new FileWriter(FILENAME, false);
		try {
			for (int i = 0; i < list.size(); i++) {
				StringBuilder st = new StringBuilder();
				MenuItem item = (MenuItem) list.get(i);
				st.append(item.getName().trim());
				st.append(SEPARATOR);
				st.append(item.getDescription().trim());
				st.append(SEPARATOR);
				st.append(item.getPrice()); // create string to write to file
				out.write(st.toString() + "\n");
			}
		} finally {
			out.close();
		}
	}

}
