package Model;
import java.io.Serializable;

public class ProductBean implements Serializable {

	private int id;
	private String productName;
	private int price;
	private int customerPoints;
	
	public void ProductBean() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCustomerPoints() {
		return customerPoints;
	}

	public void setCustomerPoints(int customerPoints) {
		this.customerPoints = customerPoints;
	}
	
	
}
