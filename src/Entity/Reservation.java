package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Controller.GuestController;
import Controller.RoomController;

enum ReservationStatus{CONFIRMED,INWAITLIST,CHECKEDIN,EXPIRED}

public class Reservation {
	private int reservationCode;
	private Guest guest;
	private Room room;
	private String billing;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int noAdults;
	private int noChildren;
	private LocalTime scheduled;
	private boolean walkIn;
	private ReservationStatus status;
	
	public Reservation(int reservationCode, Guest guestName, Room roomNumber, String billing, LocalDate checkIn,
			LocalDate checkOut, int noAdults,int noChildren, LocalTime scheduled,boolean walkIn,String reservationStatus ) throws IOException {
		// TODO Auto-generated constructor stub
		this.reservationCode=reservationCode;
		this.guest=guestName;
		this.room=roomNumber;
		this.billing=billing;
		this.checkIn=checkIn;
		this.checkOut=checkOut;
		this.noAdults=noAdults;
		this.noChildren=noChildren;
		this.scheduled=scheduled;
		this.walkIn=walkIn;
		this.status=ReservationStatus.valueOf(reservationStatus.toUpperCase());
	}
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	public int getReservationCode() {
		return reservationCode;
	}
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
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
	public String getBilling() {
		return billing;
	}
	public void setBilling(String billing) {
		this.billing = billing;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public int getNoAdults() {
		return noAdults;
	}
	public void setNoAdults(int noAdults) {
		this.noAdults = noAdults;
	}
	public int getNoChildren() {
		return noChildren;
	}
	public void setNoChildren(int noChildren) {
		this.noChildren = noChildren;
	}
	public String getStatus() {
		return status.toString();
	}
	public void setStatus(String status) {
		this.status = ReservationStatus.valueOf(status.toUpperCase());
	}
	public LocalTime getScheduled() {
		return scheduled;
	}
	public void setScheduled(LocalTime scheduled) {
		this.scheduled = scheduled;
	}
	public boolean isWalkIn() {
		return walkIn;
	}
	public void setWalkIn(boolean walkIn) {
		this.walkIn = walkIn;
	}
	

}
