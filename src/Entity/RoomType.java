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
     */
	double getRate();
	
	/**
	 * Get the weekend rate of this RoomType
	 */
	double getWeekendRate();
	
	/**
	 * Change the rate of this RoomType
	 */
	void setRate(double rate);
	
	/**
	 * Change the rate of this RoomType
	 */
	void setWeekendRate(double weekendRate);
	
	/**
	 * Get the name of this RoomType
	 */
	String getType();
}
