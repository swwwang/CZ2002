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

public class MenuController {
	
	public static final String FILENAME = "menu.txt";
	public static final String SEPARATOR = "|";
	
	public static ArrayList readMenu() throws IOException
	{
		Menu menu1 = new Menu();
		return menu1.getMenu();
	}
	public static MenuItem searchMenu(String name) throws IOException {
		MenuItem m = new MenuItem();
		ArrayList menu1 = readMenu();

		for(int i=0;i<menu1.size();i++) {
			m = (MenuItem)menu1.get(i);
			if(m.getName().equals(name)) {
				break;
			}
		}
		return m;
	}
	public void createItem(String name, String description, float price) throws IOException
	{
		FileWriter out = new FileWriter(FILENAME,true);
		StringBuilder st =  new StringBuilder() ;
		MenuItem item = new MenuItem(name, description,price); //create new object from user input
		st.append(item.getName().trim());
		st.append(SEPARATOR);
		st.append(item.getDescription().trim());
		st.append(SEPARATOR);
		st.append(item.getPrice()); //create string to write to file
		try {
			out.write(st.toString() + "\n"); //append the string to the end of file
		}
		finally {
			out.close();
		}
	}
	public void updateMenu() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		Menu menu1 = new Menu();
		for (int i = 0 ; i < menu1.getNoOfItems() ; i++) {
			MenuItem item = (MenuItem)menu1.getItem(i);
			System.out.println((i+1) + ".) " + item.getName());
		}
		System.out.print("Choose item to edit: ");
		int itemChoice = scan.nextInt();
		MenuItem item = (MenuItem)menu1.getItem(itemChoice-1);
		System.out.println("1.) Name: " + item.getName());
		System.out.println("2.) Description: " + item.getDescription());
		System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
		System.out.println("4.) Remove item");
		System.out.println("5.) Back");
		int choice = scan.nextInt();
		switch(choice)
		{
		case 1://editing name 
			System.out.println("Current Name: " + item.getName());
			System.out.print("Input New Name: ");
			String name = scan.next(); //getting new name
			item.setName(name); //editing the name of the object
			break;
		case 2://editing description
			System.out.println("Current Description: " + item.getDescription());
			System.out.print("Input New Description: ");
			String description = scan.next(); //getting new description
			item.setDescription(description); //editing the description of the object
			break;
		case 3://editing price
			System.out.println("Current Price: S$" + item.getPrice());
			System.out.print("Input New Price: ");
			float price = scan.nextFloat(); //getting the new price
			item.setPrice(price); //editing the price of the object
			break;
		case 4:
			menu1.removeItem(itemChoice-1);
			menu1.saveMenu();
			break;
		default:
			break;
		}
		if (choice!=4)
		{
			menu1.updateItem(item.getName(), item.getDescription(), item.getPrice(), itemChoice-1);
			menu1.saveMenu();
		}
		
	}
	
}
