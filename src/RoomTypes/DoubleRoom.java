package RoomTypes;

import Entity.RoomType;

public class DoubleRoom implements RoomType{
	private double rate;
	private double weekendRate;

	public double getRate() {
		return rate;
	}

	public double getWeekendRate() {
		return weekendRate;
	}

	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DOUBLE";
	}
}
