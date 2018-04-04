package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Controller.GuestController;
import Controller.RoomController;

enum ReservationStatus{CONFIRMED,INWAITLIST,CHECKEDIN,CHECKEDOUT,EXPIRED}

public class Reservation {
	private int reservationCode;
	private Guest guest;
	private ReservationRoom reservationRoom;
	private String billing;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int noAdults;
	private int noChildren;
	private LocalTime scheduledTime;
	private boolean walkIn;
	private ReservationStatus status;
	
	public Reservation(int reservationCode, Guest guestName, ReservationRoom reservationRoom, String billing, LocalDate checkIn,
			LocalDate checkOut, int noAdults,int noChildren, LocalTime scheduledTime,boolean walkIn,String reservationStatus ) throws IOException {
		// TODO Auto-generated constructor stub
		this.reservationCode=reservationCode;
		this.guest=guestName;
		this.reservationRoom=reservationRoom;
		this.billing=billing;
		this.checkIn=checkIn;
		this.checkOut=checkOut;
		this.noAdults=noAdults;
		this.noChildren=noChildren;
		this.scheduledTime=scheduledTime;
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
	
	public ReservationRoom getReservationRoom() {
		return reservationRoom;
	}
	public void setReservationRoom(ReservationRoom reservationRoom) {
		this.reservationRoom = reservationRoom;
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
	public LocalTime getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(LocalTime scheduled) {
		this.scheduledTime = scheduled;
	}
	public boolean isWalkIn() {
		return walkIn;
	}
	public void setWalkIn(boolean walkIn) {
		this.walkIn = walkIn;
	}
	

}
