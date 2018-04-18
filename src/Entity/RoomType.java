package Entity;


/**
 * Represents the interface for a room type 
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public interface RoomType {
	
	/**
	 * Get the rate of this RoomType
	 * @return rate
	 */
	double getRate();
	
	/**
	 * Get the weekend rate of this RoomType
	 * @return weekend rate
	 */
	double getWeekendRate();
	
	/**
	 * Change the rate of this RoomType
	 * @param rate rate of the room for weekday
	 */
	void setRate(double rate);
	
	/**
	 *  Change the rate of this RoomType
	 * @param weekendRate rate of the room for the weekend
	 */
	void setWeekendRate(double weekendRate);
	
	/**
	 * Get the name of this RoomType
	 * @return name of the room
	 */
	String getType();
}
