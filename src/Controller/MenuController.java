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
  @author TeamFour
  @version 1.0
  @since 2018-04-01
 */
public class MenuController {

	/**
	 * The filename that stores the entire room service Menu
	 */
	public static final String FILENAME = "menu.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Reads the entire menu from the file
	 * @return the entire room service Menu
	 * @throws IOException throw input/output exception
	 */
	public static ArrayList readMenu() throws IOException {
		Menu menu1 = new Menu();
		return menu1.getMenu();
	}

	/**
	 * Searches for the details of the menu item through the name
	 * @param name the name of the menu item
	 * @return the menu item object found through the name
	 * @throws IOException throw input/output exception
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
	 * @param m MenuItem
	 * @throws IOException throw input/output exception
	 */
	public static void createItem(MenuItem m) throws IOException {
		FileWriter out = new FileWriter(FILENAME, true);
		StringBuilder st = new StringBuilder();
		st.append(m.getName().trim());
		st.append(SEPARATOR);
		st.append(m.getDescription().trim());
		st.append(SEPARATOR);
		st.append(m.getPrice()); // create string to write to file
		try {
			out.write(st.toString() + "\n"); // append the string to the end of file
		} finally {
			out.close();
		}
	}
	
	/**
	 * Prints the name, price and description of a menu item
	 * @param m MenuItem
	 */
	public static void printMenuItem(MenuItem m) {
		System.out.println("-Printing Menu Item-");
		System.out.print("Menu Name: ");
		System.out.println(m.getName());
		System.out.print("Description: ");
		System.out.println(m.getDescription());
		System.out.print("Menu Price: ");
		System.out.printf("$%.2f\n", m.getPrice());
		System.out.println();
	}
	
	/**
	 * Removes an item from the menu at the index given
	 * @param index The index of the item to remove from the menu
	 * @throws IOException throw input/output exception
	 */
	public static void removeItem(int index) throws IOException {
		ArrayList list = readMenu();
		list.remove(index);
		saveMenu(list);
	}

	/**
	 * Saves the entire Menu into the file
	 * @param list The list of menu items to be saved into the file
	 * @throws IOException throw input/output exception
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
