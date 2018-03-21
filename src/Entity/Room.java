package Entity;

import Controller.RoomFactory;

//enum Type{Single,Double,Deluxe,VIPSuite}
enum BedType{Single,Double,Master}
enum Smoke{Smoking,NonSmoking}
enum RoomStatus{Vacant,Occupied,Reserved,UnderMaintenance}

public class Room {
	private RoomType type;
	private float rate;
	private int roomNumber;
	private BedType bedType;
	private boolean wifiEnabled;
	private boolean facing;
	private Smoke smoke;
	private RoomStatus status;
	
	public Room(RoomType type, float rate, int roomNumber, String bedType, boolean wifiEnabled, boolean facing,
			String smoke, String status) {
		this.type=type;
		this.rate=rate;
		this.roomNumber=roomNumber;
		this.bedType=BedType.valueOf(bedType);
		this.wifiEnabled=wifiEnabled;
		this.facing=facing;
		this.smoke=Smoke.valueOf(smoke);
		this.status=RoomStatus.valueOf(status);
	}
	public Room() {
		// TODO Auto-generated constructor stub
	}
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public BedType getBedType() {
		return bedType;
	}
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	public boolean isFacing() {
		return facing;
	}
	public void setFacing(boolean facing) {
		this.facing = facing;
	}
	public Smoke getSmoke() {
		return smoke;
	}
	public void setSmoke(Smoke smoke) {
		this.smoke = smoke;
	}
	public RoomStatus getStatus() {
		return status;
	}
	public void setStatus(RoomStatus status) {
		this.status = status;
	}
	

}
