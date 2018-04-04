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

public class Menu {
	private int noOfItems;
	private ArrayList menuItems;
	public static final String FILENAME = "menu.txt";
	public static final String SEPARATOR = "|";
	
	public Menu() throws IOException
	{
		menuItems = new ArrayList();
		ArrayList al = (ArrayList)textDB.read(FILENAME);
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
		}
		noOfItems = menuItems.size();
	}
	
	public int getNoOfItems()
	{
		return noOfItems;
	}
	public Object getItem(int index)
	{
		return menuItems.get(index);
	}
	public ArrayList getMenu()
	{
		return menuItems;
	}
	public void updateItem(String name, String description, float price, int index)
	{
		MenuItem item = new MenuItem(name, description,price);
		menuItems.set(index, item);
	}
	public void removeItem(int index)
	{
		menuItems.remove(index);
		noOfItems--;
	}

	
}
