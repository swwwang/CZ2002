package RoomTypes;

import Entity.RoomType;

public class VIPRoom implements RoomType{
	private final double rate=5.00;

	public double getRate() {
		return rate;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "VIPSUITE";
	}
}
