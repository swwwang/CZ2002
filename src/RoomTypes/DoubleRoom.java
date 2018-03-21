package RoomTypes;

import Entity.RoomType;

public class DoubleRoom implements RoomType{
private final double rate=2.00;
	
	public double getRate() {
		return rate;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DOUBLE";
	}
}
