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
	public void manageMenu() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		Menu menu1 = new Menu();
		System.out.println("1) Create new menu item");
		System.out.println("2) Update/remove menu item");
		int choice = scan.nextInt();
		switch(choice)
		{
		case 1:
			System.out.print("Name: ");
			String name = scan.next();
			System.out.print("Description: ");
			String description = scan.next();
			System.out.print("Price: "); //get input from user
			float price = scan.nextFloat();
			menu1.createItem(name, description,price);
			break;
		case 2:
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
			choice = scan.nextInt();
			switch(choice)
			{
			case 1://editing name 
				System.out.println("Current Name: " + item.getName());
				System.out.print("Input New Name: ");
				name = scan.next(); //getting new name
				item.setName(name); //editing the name of the object
				break;
			case 2://editing description
				System.out.println("Current Description: " + item.getDescription());
				System.out.print("Input New Description: ");
				description = scan.next(); //getting new description
				item.setDescription(description); //editing the description of the object
				break;
			case 3://editing price
				System.out.println("Current Price: S$" + item.getPrice());
				System.out.print("Input New Price: ");
				price = scan.nextFloat(); //getting the new price
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
			
			break;
		}
		
	}
	
}
