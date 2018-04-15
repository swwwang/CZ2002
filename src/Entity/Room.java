package Entity;

/**
 * The type of bed in a room
 * Room can have single, double, or master bed size
 */
enum BedType{SINGLE,DOUBLE,MASTER}

/**
 * The smoking rule of the room
 * Room can either allow smoking or don't allow smoking
 */
enum Smoke{SMOKING,NONSMOKING}

/**
 * The view of the place that the room is facing
 * Room can be facing the sea, an open area, or the city
 */
enum Facing{SEAVIEW,OPENVIEW,CITYVIEW}

/**
 * The status of a room
 * Room can be vacant, occupied, reserved, or under maintenance
 */
enum RoomStatus{VACANT,OCCUPIED,RESERVED,UNDERMAINTENANCE}


/**
 * Represents the entity for rooms
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class Room {
	
	/**
	 * The type of this room
	 */
	private RoomType type;
	
	/**
	 * The number of this room
	 */
	private String roomNumber;
	
	/**
	 * The bed type of this room
	 * Room can have single, double, or master bed size
	 */
	
	private BedType bedType;
	
	/**
	 * The WiFi option of this room
	 * true means there is WiFi in this room
	 * false means there is no WiFi in this room
	 */
	private boolean wifiEnabled;
	
	/**
	 * The view of the place the room is facing
	 * Room can be facing the sea, an open area, or the city
	 */
	private Facing facing;
	
	/**
	 * The smoking rule of this room
	 * Room can either allow smoking or don't allow smoking
	 */
	private Smoke smoke;
	
	/**
	 * The status of this room
	 * Room can be vacant, occupied, reserved, or under maintenance
	 */
	private RoomStatus status;
	
	
	/**
	 * Constructor of Room()
	 * Create a new room with the given room type, room number, bed type, WiFi option, 
	 * area that the room is facing, smoking option, and room status
	 * 
	 * @param  type The type of this room
	 * @param  roomNumber The number of this room
	 * @param  bedType The bed type of this room. 
	 * 			Room can have single, double, or master bed size
	 * @param  wifiEnabled The WiFi option of this room. 
	 * 			true means there is WiFi in this room. false means there is no WiFi in this room
	 * @param  facing The view of the place the room is facing. 
	 * 			Room can be facing the sea, an open area, or the city
	 * @param  smoke The smoking rule of this room
	 * 			Room can either allow smoking or don't allow smoking
	 * @param  status The status of this room
	 * 			Room can be vacant, occupied, reserved, or under maintenance
	 */
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
	
	/**
	 * Constructor of Room()
	 * Create a new empty room
	 */
	public Room() {

	}
	
	 /**
     * Get the type of this room
     * @return type of this room
     */
	public RoomType getType() {
		return type;
	}
	
    /**
     * Change the type of this room
     * @param type This room's new type
     */
	public void setType(RoomType type) {
		this.type = type;
	}
	
	 /**
     * Get the number of this room
     * @return number of this room
     */
	public String getRoomNumber() {
		return roomNumber;
	}
	
    /**
     * Change the number of this room
     * @param roomNumber This room's new number
     */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	 /**
     * Get the bed type of this room
     * @return bed type of this room
     */
	public BedType getBedType() {
		return bedType;
	}
	
    /**
     * Change the bed type of this room
     * @param bedType This room's new bed type
     */
	public void setBedType(String bedType) {
		this.bedType = BedType.valueOf(bedType);
	}
	
	 /**
     * Get the WiFi option of this room
     * @return WiFi option of this room
     */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	
    /**
     * Change the WiFi option of this room
     * @param wifiEnabled This room's new WiFi option
     */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	
	 /**
     * Get the view of the place this room is facing
     * @return view of the place this room is facing
     */
	public Facing getFacing() {
		return facing;
	}
	
    /**
     * Change the view of the place this room is facing
     * @param facing The new view of the place this room is facing
     */
	public void setFacing(String facing) {
		this.facing = Facing.valueOf(facing.toUpperCase());
	}
	
	 /**
     * Get the smoking option of this room
     * @return smoking option of this room
     */
	public Smoke getSmoke() {
		return smoke;
	}
	
    /**
     * Change the smoking option of this room
     * @param smoke This room's new smoking option
     */
	public void setSmoke(String smoke) {
		this.smoke = Smoke.valueOf(smoke);
	}
	
	 /**
     * Get the status of this room
     * @return status of this room
     */
	public String getStatus() {
		return status.toString(); 
	}
	
    /**
     * Change the status of this room
     * @param status This room's new status
     */
	public void setStatus(String status) {
		this.status = RoomStatus.valueOf(status.toUpperCase());
	}
	

}
