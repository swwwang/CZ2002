package Entity;

import java.util.ArrayList;

public class ReservationRoom {
	private int reservationCode;
	private int noOfRooms;
	private ArrayList<Room> rooms;
	
	public ReservationRoom(int reservationCode,int noOfRooms,ArrayList<Room> rooms) {
		this.reservationCode=reservationCode;
		this.noOfRooms=noOfRooms;
		this.rooms=rooms;
	}
	public ReservationRoom() {
		// TODO Auto-generated constructor stub
	}
	public int getReservationCode() {
		return reservationCode;
	}
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	

}
