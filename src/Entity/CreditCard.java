package Entity;


/**
 * Represents the entity for credit card
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class CreditCard {
	/**
	 * The identity number of a guest
	 */
	private String guestID;
	
	/**
	 * The name of the credit card used by guest
	 */
	private String cardName;
	
	/**
	 * The number of the credit card used by guest
	 */
	private String cardNumber;
	
	/**
	 * The billing address of the guest 
	 */
	private String billingAddress;
	
	
	/**
	 * Constructor of CreditCard()
	 * Create a new empty credit card
	 */
	public CreditCard() {
		
	}
	
	/**
	 * Constructor of CreditCard()
	 * Create a new credit card with the given identity number, 
	 * name of card, number of card, and billing address
	 * 
	 * @param guestID Identity number of a guest
	 * @param cardName Name of the card used by guest
	 * @param cardNumber Number of the card used by guest
	 * @param billingAddress Billing address of the guest 
	 */
	public CreditCard(String guestID,String cardName, String cardNumber, String billingAddress) {
		this.guestID=guestID;
		this.cardName=cardName;
		this.cardNumber=cardNumber;
		this.billingAddress=billingAddress;
	}
	
    /**
     * Get the identity number of the owner (guest) of this credit card 
     * @return the identity number of the owner (guest) of this credit card 
     */
	public String getGuestID() {
		return guestID;
	}
	
    /**
     * Change the identity number of the owner (guest) of this credit card 
     * @param guestID New identity number of the owner (guest) of this credit card 
     */
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	
    /**
     * Get the name of this credit card
     * @return this credit card's name
     */
	public String getCardName() {
		return cardName;
	}
	
    /**
     * Change the name of this credit card
     * @param cardName This credit card's new name
     */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
    /**
     * Get the number of this credit card
     * @return this credit card's number
     */
	public String getCardNumber() {
		return cardNumber;
	}
	
    /**
     * Change the number of this credit card
     * @param cardNumber This credit card's new number
     */
	public void setCardNo(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
    /**
     * Get the billing address of the owner (guest) of this credit card 
     * @return billingAddress The billing address of the owner (guest) of this credit card 
     */
	public String getBillingAddress() {
		return billingAddress;
	}
	
    /**
     * Change the billing address of the owner (guest) of this credit card 
     * @param billingAddress New billing address of the owner (guest) of this credit card 
     */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
}
