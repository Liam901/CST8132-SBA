package amazonsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonSystem {
	List<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	public void save(String fileName) throws IOException {
		this.customers = AmazonManager.customers;
		try {
		FileWriter writer = new FileWriter(fileName);
		for(AmazonCustomer c: customers) {
			writer.write("customer," + c.getID() + "," + c.getName() + "," + c.getAddress() + "\n");
			ArrayList<AmazonProduct> wishlist = c.getWishList();
			ArrayList<AmazonComment> comments = c.getComments();
			ArrayList<AmazonCredit> credits = c.getCredits(); 
			ArrayList<AmazonCartItem> cart = c.getCart();
			for(AmazonProduct p: wishlist) {
				writer.write("wishlist," + p.getID() + "\n");
			}
			
			for(AmazonComment d:comments) {
				writer.write("comment," + d.getProduct().getID() + "," + d.getComment() + "," + d.getRating() + "\n");
			}
			
			for (AmazonCredit r:credits) {
				writer.write("credit," + r.getType() + "," + r.getData() + "\n");
			}
			
			for (AmazonCartItem i:cart) {
				writer.write("cart," + i.getAmazonProduct().getID() + "," + i.getQuantity() + "\n" );
			}
		
		}
		writer.close();
		} catch (Exception e) {}
		
	}
	
	public void load(String filename) {
	/*	whenever it sees a  customer: create a customer and assign it to variable "c"
		then whenever it sees a line that is data it adds it to customer c
		whenever it sees a new customer, first add customer c to arraylist of customers then load next customer
		saving and loading products will pass them by product id */
	}
	
	public void show(List<AmazonCustomer> customers) {
		for(AmazonCustomer c: customers) {
			ArrayList<AmazonProduct> wishlist = c.getWishList();
			ArrayList<AmazonComment> comments = c.getComments();
			ArrayList<AmazonCredit> credits = c.getCredits(); 
			ArrayList<AmazonCartItem> cart = c.getCart();
			System.out.println("Customer - " + c.toString());
			System.out.println("Credit list: ");
				if(credits.isEmpty()) {
					System.out.println("No credits");
					
				} else {
					for (AmazonCredit r:credits) {
					System.out.println(r.toString());
					}
				}
			System.out.println("Wish list:");
				if(wishlist.isEmpty()) {
					System.out.println("Wishlist empty");
				} else {
					for(AmazonProduct p: wishlist) {
					System.out.println(p.getProductDetails());
					}
				}
			System.out.println("Cart:");
				if(cart.isEmpty()) {
					System.out.println("Cart empty");
				} else {
					for (AmazonCartItem i:cart) {
					System.out.println(i.toString());
					}
				}
				
			System.out.println("Comments:");
			if(comments.isEmpty()) {
				System.out.println("No comments");
			} else {
			for(AmazonComment d:comments) {
				System.out.println(d.toString());
			}
			}
			
			
		
		}
	}
}
