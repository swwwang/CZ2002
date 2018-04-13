package Entity;

enum BedType{SINGLE,DOUBLE,MASTER}
enum Smoke{SMOKING,NONSMOKING}
enum Facing{SEAVIEW,OPENVIEW,CITYVIEW}
enum RoomStatus{VACANT,OCCUPIED,RESERVED,UNDERMAINTENANCE}

public class Room {
	
	private RoomType type;
	private String roomNumber;
	private BedType bedType;
	private boolean wifiEnabled;
	private Facing facing;
	private Smoke smoke;
	private RoomStatus status;
	
	public Room(RoomType type, String roomNumber, String bedType, boolean wifiEnabled, String facing,
			String smoke, String status) {
		this.type=type;
		this.roomNumber=roomNumber;
		this.bedType=BedType.valueOf(bedType.toUpperCase());
		this.wifiEnabled=wifiEnabled;
		this.facing=Facing.valueOf(facing.toUpperCase());
		this.smoke=Smoke.valueOf(smoke.toUpperCase());
		this.status=RoomStatus.valueOf(status.toUpperCase());
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
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public BedType getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = BedType.valueOf(bedType);
	}
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	public Facing getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = Facing.valueOf(facing.toUpperCase());
	}
	public Smoke getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = Smoke.valueOf(smoke);
	}
	public String getStatus() {
		return status.toString(); 
	}
	public void setStatus(String status) {
		this.status = RoomStatus.valueOf(status.toUpperCase());
	}
	

}
