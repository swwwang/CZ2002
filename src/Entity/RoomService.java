package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Controller.GuestController;

/**
 * The status of this room service
 * Room status can be confirmed, preparing, or delivered
 */
enum RoomServiceStatus{CONFIRMED,PREPARING,DELIVERED}


/**
 * Represents the entity for room service
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class RoomService {
	
	/**
	 * The menu ordered by guest of this room service 
	 */
	private MenuItem orderedMenu;
	
	/**
	 * The order date of this room service 
	 */
	private LocalDate orderDate;
	
	/**
	 * The order time of this room service 
	 */
	private LocalTime orderTime;
	
	/**
	 * The remarks this room service 
	 */
	private String remarks;
	
	/**
	 * The payment status of this room service 
	 * Payment status can be "PAID" or "UNPAID"
	 */
	private String paid;
	
	/**
	 * The status of this room service 
	 */
	private RoomServiceStatus status;
	
	/**
	 * The room that ordered this room service 
	 */
	private Room room;
	
	/**
	 * Constructor of RoomService()
	 * Create a new empty room service
	 */
	public RoomService() {
		
	}
	
	/**
	 * Constructor of RoomService()
	 * Create a new room with the given ordered menu,roomN number,order date,order time, 
	 * remarks, payment status, and room service status) 
	 * 
	 * @param  orderedMenu The menu ordered by guest of this room service 
	 * @param  roomNumber The number of the room that ordered this room service 
	 * @param  orderDate The order date of this room service 
	 * @param  orderTime The order time of this room service 
	 * @param  remarks The remarks this room service 
	 * @param  paid	 The payment status of this room service , Payment status can be "PAID" or "UNPAID"
	 * @param  status The status of this room service 
	 */
	public RoomService(MenuItem orderedMenu,Room roomNumber,LocalDate orderDate, LocalTime orderTime, String remarks,String paid,
			String status) throws IOException {
		super();
		this.orderedMenu = orderedMenu;
		this.room = roomNumber;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.paid=paid;
		this.remarks = remarks;
		this.status = RoomServiceStatus.valueOf(status.toUpperCase()); //RoomServiceStatus.valueOf(status);
	}
	
	 /**
     * Get the ordered menu of this room service
     * @return ordered menu of this room service
     */
	public MenuItem getOrderedMenu() {
		return orderedMenu;
	}
	
    /**
     * Change the ordered menu of this room service
     * @param orderedMenu This room service's new ordered menu
     */
	public void setOrderedMenu(MenuItem orderedmenu) {
		this.orderedMenu = orderedmenu;
	}
	
	 /**
     * Get the room that ordered this room service
     * @return room that ordered this room service
     */
	public Room getRoom() {
		return room;
	}
	
    /**
     * Change the room that ordered this room service
     * @param room New room that ordered this room service
     */
	public void setRoom(Room room) {
		this.room = room;
	}
	
	 /**
     * Get the ordered date of this room service
     * @return ordered date of this room service
     */
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
    /**
     * Change the ordered date of this room service
     * @param orderedDate This room service's new ordered date
     */
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	 /**
     * Get the ordered time of this room service
     * @return ordered time of this room service
     */
	public LocalTime getOrderTime() {
		return orderTime;
	}
	
    /**
     * Change the ordered time of this room service
     * @param orderedTime This room service's new ordered time
     */
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}
	
	 /**
     * Get the remarks of this room service
     * @return remarks of this room service
     */
	public String getRemarks() {
		return remarks;
	}
	
    /**
     * Change the remarks of this room service
     * @param remarks This room service's new remarks
     */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	 /**
     * Get the payment status of this room service
     * @return payment status of this room service
     */
	public String getPaid() {
		return paid;
	}
	
    /**
     * Change the payment status of this room service
     * @param paid This room service's new payment status
     */
	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	 /**
     * Get the status of this room service
     * @return status of this room service
     */
	public String getStatus() {
		return status.toString();
	}
	
    /**
     * Change the status of this room service
     * @param status This room service's new status
     */
	public void setStatus(String status) {
		this.status = RoomServiceStatus.valueOf(status.toUpperCase());
	}

}
