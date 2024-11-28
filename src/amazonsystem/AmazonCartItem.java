package amazonsystem;

public class AmazonCartItem {
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public float calcSubtotal() {
		float subtotal = quantity * product.getDiscountPrice();
		return subtotal;
	}
	
	public String toString() {
		String line = product.getName() + ", Quantity: " + quantity + ", Subtotal: " + calcSubtotal();
		return line;
	}
	
	public AmazonProduct getAmazonProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}
