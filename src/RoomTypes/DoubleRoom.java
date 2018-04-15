package RoomTypes;

import Entity.RoomType;

public class DoubleRoom  implements RoomType{
	private double rate;
	private double weekendRate;

	/**
     * Get the rate of Double RoomType
     * @return rate of Double RoomType
     */
	public double getRate() {
		return rate;
	}

	/**
	 * Get the weekend rate of Double RoomType
	 * @return weekend rate of Double RoomType
	 */
	public double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Change the weekend rate of Double RoomType
	 * @param weekendRate Double roomType's new weekend rate
	 */
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	/**
	 * Change the rate of Double RoomType
	 * @param Rate Double roomType's new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Get the name of Double RoomType
	 * @return name of Double RoomType
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DOUBLE";
	}
}
