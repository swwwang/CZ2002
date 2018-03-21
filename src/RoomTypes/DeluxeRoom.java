package RoomTypes;

import Entity.RoomType;

public class DeluxeRoom  implements RoomType{
	private final double rate=3.00;

	public double getRate() {
		return rate;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DELUXE";
	}
}
