package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Controller.GuestController;
import Controller.RoomController;

enum ReservationStatus{Confirmed,InWaitlist,CheckedIn,Expired}

public class Reservation {
	private int reservationCode;
	private Guest guest;
	private Room room;
	private String billing;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int noGuests;
	private ReservationStatus status;
	private LocalTime scheduled;
	
	public Reservation(int reservationCode, String guestName, int roomNumber, String billing, LocalDate checkIn,
			LocalDate checkOut, int noGuests, String reservationStatus, LocalTime scheduled) throws IOException {
		// TODO Auto-generated constructor stub
		this.reservationCode=reservationCode;
		this.guest=GuestController.searchGuest(guestName);
		this.room=RoomController.searchRoom(roomNumber);
		this.billing=billing;
		this.checkIn=checkIn;
		this.checkOut=checkOut;
		this.noGuests=noGuests;
		this.status=ReservationStatus.valueOf(reservationStatus.toUpperCase());
		this.scheduled=scheduled;
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
	public int getNoGuests() {
		return noGuests;
	}
	public void setNoGuests(int noGuests) {
		this.noGuests = noGuests;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	public LocalTime getScheduled() {
		return scheduled;
	}
	public void setScheduled(LocalTime scheduled) {
		this.scheduled = scheduled;
	}
	

}
