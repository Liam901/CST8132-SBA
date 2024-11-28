package amazonsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AmazonCustomer {
	private int id;
	private String name;
	private String address;
	private AmazonCart cart;
	Scanner scanner = new Scanner(System.in);
	
	private ArrayList<AmazonComment> comments = new ArrayList<AmazonComment>();
	private ArrayList<AmazonProduct> wishList = new ArrayList<AmazonProduct>();
	private ArrayList<AmazonCredit> credits = new ArrayList<AmazonCredit>();
	
	private AmazonCustomer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
		cart = new AmazonCart(this);
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] data) throws AmazonException {
		int id;
		String name;
		String address;
		if (AmazonUtil.isValidInt(data[0])) {
			for(AmazonCustomer i:AmazonManager.customers) {
				if (i.getID() == Integer.parseInt(data[0])) {
					throw new AmazonException("ID already in use");
				}
			}
			id = Integer.parseInt(data[0]);
		} else {
			throw new AmazonException("Invalid data provided");
		}
		if (AmazonUtil.isValidString(data[1])) {
			name = data[1];
		} else {
			throw new AmazonException("Invalid data provided");
		}
		if (AmazonUtil.isValidString(data[2])) {
			address = data[2];
		} else {
			throw new AmazonException("Invalid data provided");
		}
		AmazonCustomer c = new AmazonCustomer(id, name, address);
		return c;
	}
	
	public void addProductInWishlist(AmazonProduct p) {
		wishList.add(p);
	}
	
	public void removeProductFromWishlist(AmazonProduct p) {
		wishList.remove(p);
	}
	
	public boolean isProductInWishlist(AmazonProduct p) throws AmazonException {
		if (!wishList.isEmpty()){
			return wishList.contains(p);
		} else {
			throw new AmazonException("Wishlist is empty");
		}
	}
	
	public String toString() {
		return ("ID:" + id + ", Name:" + name + ", Address:" + address);
	}
	
	public void addItemInCart(AmazonProduct product, int quantity) {
		AmazonCartItem item = new AmazonCartItem(product, quantity);
		cart.addItem(item);	
	}
	
	public void addItemInCart(AmazonCartItem item) {
		cart.addItem(item);
	}
	
	public void removeItemFromCart(AmazonProduct product) {
		cart.removeItem(product);
	}
	
	public void addCredit(AmazonCredit credit) {
		credits.add(credit);
	}
	
	public void showCredits() {
		if (!credits.isEmpty()) {
			System.out.println("Credit list for customer " + name);
			for(AmazonCredit c:credits) {
				System.out.println(credits.indexOf(c) + ": " + c.toString());
			}
		} else {
			System.out.println("Credit list for customer " + name + "is empty");
		}
	}
	
	public void showWishList() {
		if(!wishList.isEmpty()) {
		System.out.println("Wishlist for customer " + name);
		for(AmazonProduct p:wishList) {
			System.out.println(p.getProductDetails());
		}
		} else {
			System.out.println("Wishlist for customer " + name + " is empty");
		}
	}
	
	public void showCart() {
		System.out.println(cart.getCartDetails());
		cart.showCart();	
	}
	
	public void pay() throws AmazonException {
		showCart();
		System.out.println("Select credit to pay with: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			int i = Integer.parseInt(s);
			if (i > credits.size()) {
				System.out.println("Invalid selection");
			} else {
				if(cart.pay(credits.get(i).getValue())) {
					credits.get(i).setValue(credits.get(i).getValue() - cart.calcSubTotal());
					ArrayList<AmazonProduct> temp = cart.getList();
					for (AmazonProduct p: temp) {
						comments.add(new AmazonComment(p));
					}
					cart.clear();
					System.out.println("Successfully purchased cart - you can comment products now");
				} else {
					System.out.println("Insufficient credit to purchase");
				}
			}
		}
	}
	
	public void addComment() {
		boolean found = false;
		if(!comments.isEmpty()) {
			System.out.println("Input ID of product to comment: ");
			String s = scanner.nextLine();
			if(AmazonUtil.isValidInt(s)) {
				for(AmazonComment p: comments) {
					if (p.getProduct().getID() == Integer.parseInt(s)) {
						found = true;
						System.out.println("Input comment to add: ");
						try {
							p.setComment(scanner.nextLine());
							System.out.println("Input rating: ");
							s = scanner.nextLine();
							if (AmazonUtil.isValidFloat(s)) {
								p.setRating(Float.parseFloat(s));
							} else {
								System.out.println("Invalid input");
							}
						} catch (AmazonException e){}
					}
				}
				if (!found) {
					System.out.println("Product for that ID not availible to comment");
				}
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("No products availible to comment");
		}
	}
	
	public void showComments() {
		if(comments.isEmpty()) {
			System.out.println("No comments");
		} else {
			System.out.println("Comments from user " + name);
			for(AmazonComment c:comments) {
				if(c.hasComment()) {
					System.out.println(c.toString());
				}
			}
		}
	}
	
	public int getID() {
		return id;
	}
	
	
	

	
}
