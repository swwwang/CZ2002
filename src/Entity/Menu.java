package Entity;

public class Menu {
	private String name;
	private String description;
	private float price;
	
	public Menu(String name, String description, float price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
