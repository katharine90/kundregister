package Model;
import java.io.Serializable;

public class ShoppingCardBean implements Serializable {

	private String userId;
	private int productId;
	
	public void ShoppingCardBean() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}
