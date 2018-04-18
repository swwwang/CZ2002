package RoomTypes;

import Entity.RoomType;

/**
Represents the Entity Class for Single Room
@author TeamFour
@version 1.0
@since 2018-04-01
 */
public class SingleRoom  implements RoomType{
	private double rate;
	private double weekendRate;

	/**
     * Get the rate of Single RoomType
     * @return rate of Single RoomType
     */
	public double getRate() {
		return rate;
	}

	/**
	 * Get the weekend rate of Single RoomType
	 * @return weekend rate of Single RoomType
	 */
	public double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Change the weekend rate of Single RoomType
	 * @param weekendRate Single roomType's new weekend rate
	 */
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	/**
	 * Change the rate of Single RoomType
	 * @param rate Single roomType's new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Get the name of Single RoomType
	 * @return name of Single RoomType
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "SINGLE";
	}
}
