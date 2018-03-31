package Interface;

import java.io.IOException;
import java.util.ArrayList;

import Controller.GuestController;
import Controller.PaymentController;
import Controller.RoomController;
import Controller.RoomServiceController;
import Entity.Guest;

public class HotelApplication {
	public static void main(String args[]) {
		
		try {PaymentController.printBill();
			/*// read file containing Professor records.
			ArrayList al = GuestController.readGuests() ;
			
			for (int i = 0 ; i < al.size() ; i++) {
					Guest g = (Guest)al.get(i);
					System.out.println("Name " + g.getName() );
					System.out.println("Credit Card " + g.getCreditCard() );
					System.out.println("Address  " + g.getAddress() );
					System.out.println("Country " + g.getCountry() );
					System.out.println("Gender " + g.getGender() );
					System.out.println("Identity " + g.getIdentity() );
					System.out.println("Nationality " + g.getNationality() );
					System.out.println("Contact " + g.getContact() );
			}*/
			//GuestController.updateGuest("Tom");
			//GuestController.searchGuest("Harry");
			//Guest g1 = new Guest("Joseph","MasterCard","NTU", "Singapore", "Male", "Passport", "Singaporean", 67909999);
			// al is an array list containing Professor objs
			//al.add(g1);
			// write Professor record/s to file.
			//GuestController.saveGuests(filename, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

}
