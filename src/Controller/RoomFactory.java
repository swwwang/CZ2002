package Controller;

import Entity.*;
import RoomTypes.DeluxeRoom;
import RoomTypes.DoubleRoom;
import RoomTypes.SingleRoom;
import RoomTypes.VIPRoom;

public class RoomFactory {

	public static RoomType getRoomType(String roomType){
		if(roomType == null){
			return null;
		}		
		if(roomType.equalsIgnoreCase("SINGLE")){
			return new SingleRoom();

		} else if(roomType.equalsIgnoreCase("DOUBLE")){
			return new DoubleRoom();

		} else if(roomType.equalsIgnoreCase("DELUXE")){
			return new DeluxeRoom();
		}
		else if(roomType.equalsIgnoreCase("VIPSUITE")){
			return new VIPRoom();
		}

		return null;
	}
}
