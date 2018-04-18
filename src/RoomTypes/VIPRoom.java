package RoomTypes;

import Entity.RoomType;

/**
Represents the Entity Class for VIP Room
@author TeamFour
@version 1.0
@since 2018-04-01
 */
public class VIPRoom  implements RoomType{
	private double rate;
	private double weekendRate;

	/**
     * Get the rate of VIP RoomType
     * @return rate of VIP RoomType
     */
	public double getRate() {
		return rate;
	}

	/**
	 * Get the weekend rate of VIP RoomType
	 * @return weekend rate of VIP RoomType
	 */
	public double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Change the weekend rate of VIP RoomType
	 * @param weekendRate VIP roomType's new weekend rate
	 */
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	/**
	 * Change the rate of VIP RoomType
	 * @param rate VIP roomType's new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Get the name of VIP RoomType
	 * @return name of VIP RoomType
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "VIPSUITE";
	}
}
