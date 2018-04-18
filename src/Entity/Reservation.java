package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Controller.GuestController;
import Controller.RoomController;


/**
 * The reservation status of a guest
 * Reservation status of a guest can be confirmed, in waiting list, checked in, or expired
 */
enum ReservationStatus{CONFIRMED,INWAITLIST,CHECKEDIN,CHECKEDOUT,EXPIRED}

/**
 * Represents the entity for reservation
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class Reservation {
	
	/**
	 * The reservation code of the reservation made by a guest
	 */
	private int reservationCode;
	
	/**
	 * The guest making the reservation
	 */
	private Guest guest;
	
	/**
	 * The room of the reservation
	 */
	private ReservationRoom reservationRoom;

	/**
	 * The bill of the reservation
	 */
	private String billing;

	/**
	 * The check-in date of the reservation
	 */
	private LocalDate checkIn;
	
	/**
	 * The check-out date of the reservation
	 */
	private LocalDate checkOut;
	
	/**
	 * The number of adults in the reservation
	 */
	private int noAdults;
	
	/**
	 * The number of children in the reservation
	 */
	private int noChildren;
	
	/**
	 * The scheduled check-in time of the reservation
	 */
	private LocalTime scheduledTime;
	
	/**
	 * The method used to make a reservation
	 * true means guest walked in to make a reservation
	 * false means guest booked online to make a reservation
	 */
	private boolean walkIn;
	
	/**
	 * The status of the reservation reservation
	 * Reservation status of a guest can be confirmed, in waiting list, checked in, or expired
	 */
	private ReservationStatus status;
	
	
	/**
	 * Constructor of Reservation()
	 * Create a new reservation with the given reservation code, guest name, reservation room, bill, 
	 * check-in date, check-out date, number of adults, number of children, scheduled check-in time, walk-in
	 * , and reservation status
	 * 
	 * @param  reservationCode The reservation code of the guest
	 * @param  guestName The guest making the reservation
	 * @param  reservationRoom The room of the reservation
	 * @param  billing The bill of the reservation
	 * @param  checkIn The check-in date of the reservation
	 * @param  checkOut The check-out date of the reservation
	 * @param  noAdults The number of adults in the reservation
	 * @param  noChildren The number of children in the reservation
	 * @param  scheduledTime The scheduled check-in time of the reservation
	 * @param  walkIn The method used to make a reservation
	 *			true means guest walked in to make a reservation
	 * 			false means guest booked online to make a reservation
	 * @param  reservationStatus The status of the reservation reservation
	 *			Reservation status of a guest can be confirmed, in waiting list, checked in, or expired
	 *@throws IOException throw input/output exception
	 */
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
	
	/**
	 * Constructor of Reservation()
	 * Create a new empty reservation
	 */
	public Reservation() {

	}
	
	/**
     * Get the reservation code of this reservation
     * @return reservation code of this reservation
     */
	public int getReservationCode() {
		return reservationCode;
	}
	
    /**
     * Change the reservation code of this reservation
     * @param reservationCode This reservation's new reservation code
     */
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	/**
     * Get the guest of this reservation
     * @return guest of this reservation
     */
	public Guest getGuest() {
		return guest;
	}
	
    /**
     * Change the guest of this reservation
     * @param guest This reservation's new guest
     */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	/**
     * Get the room of this reservation
     * @return room of this reservation
     */
	public ReservationRoom getReservationRoom() {
		return reservationRoom;
	}
	
    /**
     * Change the room of this reservation
     * @param reservationRoom This reservation's new room
     */
	public void setReservationRoom(ReservationRoom reservationRoom) {
		this.reservationRoom = reservationRoom;
	}
	
	/**
     * Get the bill of this reservation
     * @return bill of this reservation
     */
	public String getBilling() {
		return billing;
	}
	
    /**
     * Change the bill of this reservation
     * @param billing This reservation's new bill
     */
	public void setBilling(String billing) {
		this.billing = billing;
	}
	
	/**
     * Get the check-in date of this reservation
     * @return check-in date of this reservation
     */
	public LocalDate getCheckIn() {
		return checkIn;
	}
	
    /**
     * Change the check-in date of this reservation
     * @param checkIn This reservation's new check-in date
     */
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	
	/**
     * Get the check-out date of this reservation
     * @return check-out date of this reservation
     */
	public LocalDate getCheckOut() {
		return checkOut;
	}
	
    /**
     * Change the check-out date of this reservation
     * @param checkOut This reservation's new check-out date
     */
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	/**
     * Get the number of adult of this reservation
     * @return number of adult of this reservation
     */
	public int getNoAdults() {
		return noAdults;
	}
	
    /**
     * Change the number of adult of this reservation
     * @param noAdults This reservation's new number of adult
     */
	public void setNoAdults(int noAdults) {
		this.noAdults = noAdults;
	}
	
	/**
     * Get the number of children of this reservation
     * @return number of children of this reservation
     */
	public int getNoChildren() {
		return noChildren;
	}
	
    /**
     * Change the number of children of this reservation
     * @param noChildren this reservation's new number of children
     */
	public void setNoChildren(int noChildren) {
		this.noChildren = noChildren;
	}
	
	/**
     * Get the status of this reservation
     * @return status of this reservation
     */
	public String getStatus() {
		return status.toString();
	}
	
    /**
     * Change the status of this reservation
     * @param status This reservation's new status
     */
	public void setStatus(String status) {
		this.status = ReservationStatus.valueOf(status.toUpperCase());
	}
	
	/**
     * Get the scheduled check-in time of this reservation
     * @return scheduled check-in time of this reservation
     */
	public LocalTime getScheduledTime() {
		return scheduledTime;
	}
	
    /**
     * Change the scheduled check-in time of this reservation
     * @param scheduled this reservation's new scheduled check-in time
     */
	public void setScheduledTime(LocalTime scheduled) {
		this.scheduledTime = scheduled;
	}
	
	/**
     * Get method used to make a reservation
     * @return Method used to make a reservation
     * true means guest walked in to make a reservation
	 * false means guest booked online to make a reservation
     */
	public boolean isWalkIn() {
		return walkIn;
	}
	
    /**
     * Change the method used to make a reservation
     * @param walkIn Method used to make a reservation
     * true means guest walked in to make a reservation
	 * false means guest booked online to make a reservation
     */
	public void setWalkIn(boolean walkIn) {
		this.walkIn = walkIn;
	}
	

}
