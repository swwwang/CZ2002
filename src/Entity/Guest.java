package Entity;

/**
 * The identity of a guest
 * Guest can choose to use either driving license or passport as his/ her identity
 */
enum Identity{DRIVINGLICENSE,PASSPORT}

/**
 * Represents the entity for guest
 * @author team four
 * @version 1.0
 * @since 2018-04-01
 */
public class Guest {
	
	/**
	 * The name of a guest
	 */
	private String name;
	
	/**
	 * The credit card detail of a guest
	 */
	private CreditCard creditCard;
	
	/**
	 * The address of a guest
	 */
	private String address;
	
	/**
	 * The country of a guest
	 */
	private String country;
	
	/**
	 * The gender of a guest
	 */
	private String gender;
	
	/**
	 * The identity (driving license or passport) of a guest
	 */
	private Identity identity;
	
	/**
	 * The identity number of a guest
	 */
	private String idNo;
	
	/**
	 * The nationality of a guest
	 */
	private String nationality;
	
	/**
	 * The contact number of a guest
	 */
	private String contact;
	
	/**
	 * Constructor of Guest()
	 * Create a new guest with the given name, credit card detail,address, country, gender,
	 * identity (driving license or passport), identity number , nationality, and contact
	 * 
	 * @param name Name of the guest
	 * @param creditCard Credit card detail of the guest
	 * @param address Address of the guest
	 * @param country Country of the guest
	 * @param gender Gender of the guest
	 * @param identity Identity (driving license or passport) of the guest
	 * @param idNo Identity number of the guest
	 * @param nationality
	 * @param contact 
	 */
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
	
	/**
	 * Constructor of Guest()
	 * Create a new empty guest with credit card
	 */
	public Guest() {
		creditCard=new CreditCard();
	}
	
	 /**
     * Get the name of this guest
     * @return Name of this guest
     */
	public String getName() {
		return name;
	}
	
    /**
     * Change the name of this guest
     * @param name This guest's new name
     */
	public void setName(String name) {
		this.name = name;
	}
	
	 /**
     * Get the credit card detail of this guest
     * @return Credit card detail of this guest
     */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
    /**
     * Change the credit card detail of this guest
     * @param creditCard This guest's new credit card detail
     */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	 /**
     * Get the address of this guest
     * @return Address of this guest
     */
	public String getAddress() {
		return address;
	}
	
    /**
     * Change the address of this guest
     * @param address this guest's new address
     */
	public void setAddress(String address) {
		this.address = address;
	}
	
	 /**
     * Get the country of this guest
     * @return Country of this guest
     */
	public String getCountry() {
		return country;
	}
	
    /**
     * Change the country of this guest
     * @param country This guest's new country
     */
	public void setCountry(String country) {
		this.country = country;
	}
	
	 /**
     * Get the gender of this guest
     * @return Gender of this guest
     */
	public String getGender() {
		return gender;
	}
	
    /**
     * Change the gender of this guest
     * @param gender This guest's new gender
     */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	 /**
     * Get the identity (driving license or passport) of this guest
     * @return Identity (driving license or passport) of this guest
     */
	public Identity getIdentity() {
		return identity;
	}
	
    /**
     * Change the identity (driving license or passport) of this guest
     * @param  identity This guest's new identity (driving license or passport)
     */
	public void setIdentity(String identity) {
		this.identity = Identity.valueOf(identity);
	}
	
	 /**
     * Get the identity number of this guest
     * @return Identity number of this guest
     */
	public String getIdNo() {
		return idNo;
	}
	
    /**
     * Change the identity number of this guest
     * @param idNo This guest's new identity number 
     */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	 /**
     * Get the nationality of this guest
     * @return Nationality of this guest
     */
	public String getNationality() {
		return nationality;
	}
	
    /**
     * Change the nationality of this guest
     * @param nationality This guest's new nationality
     */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	 /**
     * Get the contact number of this guest
     * @return Contact number of this guest
     */
	public String getContact() {
		return contact;
	}
	
    /**
     * Change the contact number of this guest
     * @param contact This guest's new contact number
     */
	public void setContact(String contact) {
		this.contact = contact;
	}

}
