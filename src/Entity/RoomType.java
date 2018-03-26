package Entity;

public interface RoomType {
	double getRate();
	double getWeekendRate();
	void setRate(double rate);
	void setWeekendRate(double weekendRate);
	String getType();
}
