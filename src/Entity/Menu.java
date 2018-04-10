package Entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Controller.textDB;

/**
 * Represents the room service menu of the hotel
 * The menu can have multiple items
 * @author Kaw Teck Lin
 * @version 1.0
 * @since 2018-04-01
 */
public class Menu {
	/**
	 * The number of items in the menu 
	 */
	private int noOfItems;
	/**
	 * The list of items in the menu
	 */
	private ArrayList menuItems;
	/**
	 * The filename for saving the entire room service Menu
	 */
	public static final String FILENAME = "menu.txt";
	/**
	 * The separator for separating fields to be saved into the file
	 */
	public static final String SEPARATOR = "|";
	
	/**
	 * Creates a new Menu
	 * The Menu is read from the file
	 * @throws IOException
	 */
	public Menu() throws IOException
	{
		menuItems = new ArrayList();
		noOfItems = 0;
		ArrayList al = (ArrayList)textDB.read(FILENAME); //list of string from file
		for (int i = 0 ; i < al.size() ; i++) {
			String st = (String)al.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			String name = star.nextToken().trim();	// first token
			String description = star.nextToken().trim();	// second token
			float price = Float.parseFloat(star.nextToken().trim()); // third token
			// create object from file data
			MenuItem item = new MenuItem(name, description,price);
			// add to menuItems list
			menuItems.add(item);
			noOfItems++;
		}
	}
	
	/**
	 * Gets the number of items in this Menu
	 * @return number of items in this Menu
	 */
	public int getNoOfItems()
	{
		return noOfItems;
	}
	/**
	 * Gets the item at the index given
	 * @param index the index of the item to get
	 * @return the item at the index given of this Menu
	 */
	public Object getItem(int index)
	{
		return menuItems.get(index);
	}
	/**
	 * Gets the list of items in this Menu
	 * @return the list of items in this Menu
	 */
	public ArrayList getMenu()
	{
		return menuItems;
	}
	/**
	 * Updates the item at the index given with new fields given
	 * @param name this Menu item's new name
	 * @param description this Menu item's new description
	 * @param price this Menu item's new price
	 * @param index index of item to update in this Menu
	 */
	public void updateItem(String name, String description, float price, int index)
	{
		MenuItem item = new MenuItem(name, description,price);
		menuItems.set(index, item);
	}
	/**
	 * Removes the item of this Menu at the index given
	 * @param index the index of the item to remove from this Menu
	 */
	public void removeItem(int index)
	{
		menuItems.remove(index);
		noOfItems--;
	}

	
}
