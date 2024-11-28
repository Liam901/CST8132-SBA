package amazonsystem;

import java.util.ArrayList;

public class AmazonCart implements AmazonPayable{
	private AmazonCustomer customer;
	private ArrayList<AmazonCartItem> items = new ArrayList<AmazonCartItem>();
	private float totalValue;
	
	public AmazonCart(AmazonCustomer customer) {
		this.customer = customer;
	}
	
	public float calcSubTotal() {
		float sum = 0;
		for(AmazonCartItem i:items) {
			sum += i.calcSubtotal();
		}
		return sum;
	}
	
	public void addItem(AmazonCartItem item) {
		items.add(item);
	}
	
	public boolean pay(float payment) {
		if(payment >= calcSubTotal() ) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeItem(AmazonProduct product) {
		for(AmazonCartItem i:items) {
			if(product == i.getAmazonProduct()) {
				items.remove(i);
			}
		}
	}
	
	public String getCartDetails() {
		String line;
		line = "Customer: " + customer.toString() + ", Total: " + calcSubTotal();
		return line;
	}
	
	public void showCart() {
		if(items.isEmpty()) {
			System.out.println("Cart is empty");
		} else {
		for(AmazonCartItem i:items) {
			System.out.println(i.toString());
		}
		}
	}
	
	public ArrayList<AmazonProduct> getList(){
		ArrayList<AmazonProduct> temp = new ArrayList<AmazonProduct>();
		for(AmazonCartItem i:items) {
			temp.add(i.getAmazonProduct());
		}
		return temp;
	}
	
	public int getSize() {
		return items.size();
	}
	
	public void clear() {
		items.clear();
	}
	
	
}
