package RoomTypes;

import Entity.RoomType;

public class SingleRoom implements RoomType{
	private final double rate=1.00;
	
	public double getRate() {
		return rate;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "SINGLE";
	}

}
