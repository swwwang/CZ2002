package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Controller.GuestController;

enum RoomServiceStatus{Confirmed,Preparing,Delivered}

public class RoomService {
	private Menu orderedMenu;
	private Guest guest;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private String remarks;
	private /*RoomServiceStatus*/ String status;
	private Room room;
	
	public RoomService() {
		
	}
	public RoomService(Menu orderedMenu, String guestName,Room roomNumber,LocalDate orderDate, LocalTime orderTime, String remarks,
			String status) throws IOException {
		super();
		this.orderedMenu = orderedMenu;
		this.guest=GuestController.searchGuest(guestName);
		this.room = roomNumber;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.remarks = remarks;
		this.status = status; //RoomServiceStatus.valueOf(status);
	}
	public Menu getOrderedMenu() {
		return orderedMenu;
	}
	public void setOrderedMenu(Menu orderedmenu) {
		this.orderedMenu = orderedmenu;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public /*RoomServiceStatus*/String getStatus() {
		return status;
	}
	public void setStatus(/*RoomServiceStatus*/ String status) {
		this.status = status;
	}

}
