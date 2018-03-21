package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Controller.GuestController;

enum RoomServiceStatus{Confirmed,Preparing,Delivered}

public class RoomService {
	private ArrayList<Menu> orderedMenu;
	private Guest guest;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private String remarks;
	private RoomServiceStatus status;
	
	public RoomService() {
		
	}
	public RoomService(String orderedMenu, String guestName,LocalDate orderDate, LocalTime orderTime, String remarks,
			String status) throws IOException {
		super();
		//this.orderedMenu = orderedMenu;
		this.guest=GuestController.searchGuest(guestName);
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.remarks = remarks;
		this.status = RoomServiceStatus.valueOf(status);
	}
	public ArrayList<Menu> getOrderedMenu() {
		return orderedMenu;
	}
	public void setOrderedMenu(ArrayList<Menu> orderedMenu) {
		this.orderedMenu = orderedMenu;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
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
	public RoomServiceStatus getStatus() {
		return status;
	}
	public void setStatus(RoomServiceStatus status) {
		this.status = status;
	}

}
