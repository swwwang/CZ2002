package Entity;

/**
 * Represents an item in the room service menu
 * @author Kaw Teck Lin
 * @version 1.0
 * @since 2018-04-01
 */
public class MenuItem {
	/**
	 * The name of this item
	 */
	private String name;
	/**
	 * The description of how this item is prepared
	 */
	private String description;
	/**
	 * The price of this item
	 */
	private float price;
	
	/**
	 * Creates a new empty Menu Item
	 */
	public MenuItem()
	{
	}
	/**
	 * Creates a new Menu Item with the given name, description and price
	 * @param name This Menu Item's name
	 * @param description This Menu Item's description of how it is prepared
	 * @param price This Menu Item's price
	 */
	public MenuItem(String name, String description, float price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	/**
	 * Gets the name of this Menu Item
	 * @return this Menu Item's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Changes the name of this Menu Item
	 * @param name This Menu Item's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the description of this Menu Item
	 * @return this Menu Item's description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Changes the description of this Menu Item
	 * @param description This Menu Item's new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Gets the price of this Menu Item
	 * @return this Menu Item's price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Changes the price of this Menu Item
	 * @param price This Menu Item's new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
}
