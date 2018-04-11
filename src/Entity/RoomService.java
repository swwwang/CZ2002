package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Controller.GuestController;

enum RoomServiceStatus{CONFIRMED,PREPARING,DELIVERED}

public class RoomService {
	private MenuItem orderedMenu;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private String remarks;
	private String paid;
	private RoomServiceStatus status;
	private Room room;
	
	public RoomService() {
		
	}
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
	public MenuItem getOrderedMenu() {
		return orderedMenu;
	}
	public void setOrderedMenu(MenuItem orderedmenu) {
		this.orderedMenu = orderedmenu;
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
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getStatus() {
		return status.toString();
	}
	public void setStatus(String status) {
		this.status = RoomServiceStatus.valueOf(status.toUpperCase());
	}

}
