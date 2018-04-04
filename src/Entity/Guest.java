package Entity;

enum Identity{DRIVINGLICENSE,PASSPORT}

public class Guest {
	private String name;
	private CreditCard creditCard;
	private String address;
	private String country;
	private String gender;
	private Identity identity;
	private String idNo;
	private String nationality;
	private String contact;
	
	public Guest(String name, CreditCard creditCard, String address, String country, String gender, String identity,
			String idNo,String nationality, String contact) {
		super();
		this.name = name;
		this.creditCard = creditCard;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.identity = Identity.valueOf(identity);
		this.idNo=idNo;
		this.nationality = nationality;
		this.contact = contact;
	}
	public Guest() {
		creditCard=new CreditCard();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = Identity.valueOf(identity);
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

}
