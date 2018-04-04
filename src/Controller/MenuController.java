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
	public static void createItem(String name, String description, float price) throws IOException
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
	public static void updateMenu(Object change, int index) throws IOException
	{
		Menu menu1 = new Menu();
		menu1.updateItem(((MenuItem) change).getName(), ((MenuItem) change).getDescription(), ((MenuItem) change).getPrice(), index);
		ArrayList list = menu1.getMenu();
		saveMenu(list);
	}
	public static void removeItem(int index) throws IOException
	{
		ArrayList list = readMenu();
		list.remove(index);
		saveMenu(list);
	}
	public static void saveMenu(ArrayList list) throws IOException
	{
		FileWriter out = new FileWriter(FILENAME,false);
		try
		{
			for (int i = 0; i < list.size(); i++)
			{
				StringBuilder st =  new StringBuilder() ;
				MenuItem item = (MenuItem)list.get(i);
				st.append(item.getName().trim());
				st.append(SEPARATOR);
				st.append(item.getDescription().trim());
				st.append(SEPARATOR);
				st.append(item.getPrice()); //create string to write to file
				out.write(st.toString() + "\n");
			}
		}
		finally {
			out.close();
		}
	}
	
}
