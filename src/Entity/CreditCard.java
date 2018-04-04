package Entity;

public class CreditCard {
	private String guestID;
	private String cardName;
	private String cardNumber;
	private String billingAddress;
	
	public CreditCard() {
		
	}
	public CreditCard(String guestID,String cardName, String cardNumber, String billingAddress) {
		this.guestID=guestID;
		this.cardName=cardName;
		this.cardNumber=cardNumber;
		this.billingAddress=billingAddress;
	}
	
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNo(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	

}
