package Entity;

public class CreditCard {
	private String cardName;
	private int cardNumber;
	private String billingAddress;
	
	public CreditCard() {
		
	}
	public CreditCard(String cardName, int cardNumber, String billingAddress) {
		this.cardName=cardName;
		this.cardNumber=cardNumber;
		this.billingAddress=billingAddress;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNo(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	

}
