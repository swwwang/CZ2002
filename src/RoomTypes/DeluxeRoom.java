package RoomTypes;

import Entity.RoomType;

/**
Represents the Entity Class for Deluxe Room
@author TeamFour
@version 1.0
@since 2018-04-01
 */

public class DeluxeRoom  implements RoomType{
	private double rate;
	private double weekendRate;

	/**
     * Get the rate of Deluxe RoomType
     * @return rate of Deluxe RoomType
     */
	public double getRate() {
		return rate;
	}

	/**
	 * Get the weekend rate of Deluxe RoomType
	 * @return weekend rate of Deluxe RoomType
	 */
	public double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Change the weekend rate of Deluxe RoomType
	 * @param weekendRate Deluxe roomType's new weekend rate
	 */
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	/**
	 * Change the rate of Deluxe RoomType
	 * @param rate Deluxe roomType's new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Get the name of Deluxe RoomType
	 * @return name of Deluxe RoomType
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DELUXE";
	}
}
