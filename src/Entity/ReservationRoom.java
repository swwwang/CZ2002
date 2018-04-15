package Entity;

import java.util.ArrayList;


/**
 * Represents the entity for rooms in a reservation 
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class ReservationRoom {
	/**
	 * The reservation code of the guest made by a guest
	 */
	private int reservationCode;
	
	/**
	 * The number of rooms booked by guest in a reservation
	 */
	private int noOfRooms;
	
	/**
	 * The array list of room number booked by guest in a reservation
	 */
	private ArrayList<Room> rooms;
	
	/**
	 * Constructor of Guest()
	 * Create a new reservation room with the given reservation code,number of rooms,
	 * and the array list of rooms
	 * 
	 * @param  reservationCode The reservation code of the guest made by a guest
	 * @param  noOfRooms The number of rooms booked by guest in a reservation
	 * @param  rooms The array list of room number booked by guest in a reservation
	 */
	public ReservationRoom(int reservationCode,int noOfRooms,ArrayList<Room> rooms) {
		this.reservationCode=reservationCode;
		this.noOfRooms=noOfRooms;
		this.rooms=rooms;
	}
	
	/**
	 * Constructor of Guest()
	 * Create a new empty reservation room
	 */
	public ReservationRoom() {

	}
	
	/**
     * Get the reservation code of this reservationRoom
     * @return reservation code of this reservationRoom
     */
	public int getReservationCode() {
		return reservationCode;
	}
	
	 /**
     * Change the reservation code of this reservationRoom
     * @param reservationCode This reservationRoom's new reservation code
     */
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	/**
     * Get the number of rooms booked by guest in this reservationRoom
     * @return number of rooms booked by guest in this reservationRoom
     */
	public int getNoOfRooms() {
		return noOfRooms;
	}
	
	 /**
     * Change the number of rooms booked by guest in  reservationRoom
     * @param noOfRooms This reservationRoom's new number of rooms booked by guest
     */
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	
	/**
     * Get the reservation code of this reservationRoom
     * @return reservation code of this reservationRoom
     */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	 /**
     * Change the array list of room number booked by guest in this reservationRoom
     * @param rooms This reservationRoom's new array list of room number booked by guest
     */
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
}
