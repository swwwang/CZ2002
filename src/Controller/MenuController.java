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

public class MenuController {
	public static final String FILENAME = "menu.txt";
	public static final String SEPARATOR = "|";
	FileWriter out = null;
	
	public static ArrayList readMenu() throws IOException {  
		ArrayList al = new ArrayList(); 
		ArrayList stringArray = (ArrayList)textDB.read(FILENAME);
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			String name = star.nextToken().trim();	// first token
			String description = star.nextToken().trim();	// second token
			float price = Float.parseFloat(star.nextToken().trim()); // third token
			// create object from file data
			Menu item = new Menu(name, description,price);
			// add to menuItems list
			al.add(item) ;
		}
		return al;
	}
	
	public void createItem() throws IOException{

		out = new FileWriter(FILENAME,true);
		StringBuilder st =  new StringBuilder() ;
		Scanner scan = new Scanner(System.in);
		System.out.print("Name: ");
		String name = scan.next();
		System.out.print("Description: ");
		String description = scan.next();
		System.out.print("Price: "); //get input from user
		float price = scan.nextFloat();
		Menu item = new Menu(name, description,price); //create new object from user input
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
	
	public void updateItem() throws IOException{
		Scanner scan = new Scanner(System.in);
		ArrayList al = readMenu(); //list in object format
		List alw = new ArrayList() ;// to store menu items in string format
		int choice = 0, itemChoice = 0, fieldChoice = 0;
		Menu item = null;
		do
		{
			do
			{
				for (int i = 0 ; i < al.size() ; i++) {
					item = (Menu)al.get(i);
					System.out.println((i+1) + ".) " + item.getName());
				}
				
				System.out.print("Choose item to update: ");
				itemChoice = scan.nextInt();
				item = (Menu)al.get(itemChoice-1);
				System.out.println("1.) Name: " + item.getName());
				System.out.println("2.) Description: " + item.getDescription());
				System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
				System.out.println("4.) Remove item");
				System.out.println("5.) Back");
				System.out.print("Choose field to update or remove item: ");
				fieldChoice = scan.nextInt(); //choosing which field to change
			}while(fieldChoice>=5 || fieldChoice < 1); //going back to display list of menu items if user input invalid choice or choose 5
			
			al.remove(itemChoice-1); //taking the chosen object out of object list
			
			do
			{
				switch(fieldChoice)
				{
				case 1: //editing name 
					System.out.println("Current Name: " + item.getName());
					System.out.print("Input New Name: ");
					String name = scan.next(); //getting new name
					item.setName(name); //editing the name of the object
					break;
				case 2: //editing description
					System.out.println("Current Description: " + item.getDescription());
					System.out.print("Input New Description: ");
					String description = scan.next(); //getting new description
					item.setDescription(description); //editing the description of the object
					break;
				case 3: //editing price
					System.out.println("Current Price: S$" + item.getPrice());
					System.out.print("Input New Price: ");
					float price = scan.nextFloat(); //getting the new price
					item.setPrice(price); //editing the price of the object
					break;
				case 4: //removing object
					break;
				}
				
				System.out.println("1.) Edit another item");
				System.out.println("2.) Edit another field");
				System.out.println("3.) End");
				choice = scan.nextInt();
				if (choice ==2)
				{
					System.out.println("1.) Name: " + item.getName());
					System.out.println("2.) Description: " + item.getDescription());
					System.out.printf("3.) Price: S$%.2f\n", item.getPrice());
					System.out.println("4.) Remove item");
					System.out.println("5.) Back");
					System.out.print("Choose field to update or remove item: ");
					fieldChoice = scan.nextInt(); //choosing which field to change
				}
			}while(choice==2); //going back to edit another field of the same item
			if (fieldChoice !=4)
				al.add(itemChoice-1, item); //adding the edited object back to object list
		}while(choice==1); //going back to select another item to edit
		
		for (int i = 0 ; i < al.size() ; i++) {
			item = (Menu)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(item.getName().trim());
			st.append(SEPARATOR);
			st.append(item.getDescription().trim());
			st.append(SEPARATOR);
			st.append(item.getPrice()); //create string to write to file
			alw.add(st.toString()); //adding string to the string list
			
		}
		
		textDB.write(FILENAME,alw); //write the list of string to file
	}

}
