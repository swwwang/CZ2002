package Controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
Represents the controller for read and write operation to textfiles
@author TeamFour
@version 1.0
@since 2018-04-01
*/
public class textDB {
	/**
	 * Write fixed content to the given file.
	 * @param fileName name of the file
	 * @param data list of data
	 * @throws IOException throw input/output exception
	 */
	public static void write(String fileName,List data) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i =0; i < data.size() ; i++) {
				out.println((String)data.get(i));
			}
		}
		finally {
			out.close();
		}
	}

	/**
	 * Read the contents of the given file. 
	 * @param fileName name of the file
	 * @return List list of data
 	 * @throws IOException throw input/output exception
	 */
	public static List read(String fileName) throws IOException {
		List data = new ArrayList() ;
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()){
				data.add(scanner.nextLine());
			}
		}
		finally{
			scanner.close();
		}
		return data;
	}
}
