package amazonsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class AmazonManager {
	static Scanner scanner = new Scanner(System.in);
	public static ArrayList<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	public static ArrayList<AmazonProduct> products = new ArrayList<AmazonProduct>();
	public static final int NUMCOLS = 10;
	public static String title;
	
	public static void showMenu() {
		String divider = "-----------------------------------------";
		System.out.println(divider);
		System.out.println("ALGONQUIN COLLEGE | CST8215 | PROF: PAULO SOUSA | TERM: FALL 2024");
		System.out.println(divider);
		System.out.println("Amazon Manager Menu");
		System.out.println(divider);
		System.out.println("ADMIN			USER\n");	
		System.out.println("Product Options		Credit Options");
		System.out.println("[A] Load Product List	[F] Add credit to customer");
		System.out.println("[B] Show Product List	[G] Show credit from customer");
		System.out.println("[C] Search Products");
		System.out.println("			Wishlist Options");
		System.out.println("Customer Optinos	[H] Add product in wishlist");
		System.out.println("[D] Add customer	[I] Remove product in wishlist");
		System.out.println("[E] Show customers	[F] Show products in wishlist\n");
		System.out.println("			Cart options");
		System.out.println("			[K] Add product in cart");
		System.out.println("			[L] Remove product from cart");
		System.out.println("			[M] Show proucts from cart");
		System.out.println("			[N] Buy products from cart\n");
		System.out.println("			Comment options");
		System.out.println("			[O] Comment products bought");
		System.out.println("[Q] Exit		[P] List comments from products");
		System.out.println(divider);
		System.out.println("Select an option:");
	}
	
	public static void addCustomer() throws AmazonException {
		String[] data = new String[3];
		System.out.println("Input customer ID (unique integer):");
		data[0] = scanner.nextLine();
		System.out.println("Input customer name:");
		data[1] = scanner.nextLine();
		System.out.println("Input customer address:");
		data[2] = scanner.nextLine();
		try {
			AmazonCustomer c = AmazonCustomer.createAmazonCustomer(data);
			customers.add(c);
		} catch(AmazonException e) {}
	}
	
	public static void showCustomers() {
		if (customers.isEmpty()) {
			System.out.println("List of customers is empty");
		} else {
			System.out.println("Printing list of customers");
			for(AmazonCustomer i:customers) {
				System.out.println(i.toString());
			}
		}
	}
	
	public static void loadProductList() throws AmazonException {
		String fileName;
		String line;
		
		System.out.println("Input name of file to load products from:");
		fileName = scanner.nextLine();
		try {
		FileReader reader = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader(reader);
		title = buffer.readLine();
		while(buffer.ready()) {
			line = buffer.readLine();
			products.add(AmazonProduct.createAmazonProduct(AmazonUtil.lineReader(line, NUMCOLS)));
		}
		System.out.println("Loaded product list from " + fileName);
		}catch(FileNotFoundException e) {
			throw new AmazonException("File name not found");
		}
		catch(IOException e) {}										
	}
	
	public static void showProductList() {
		if(products.isEmpty()){
			System.out.println("Product list is empty");
		} else {
			System.out.println("Printing product list ...");
			for(AmazonProduct p:products) {
				System.out.println(p.getProductDetails());
			}
		}
	}
	
	public static void addProductInWishList() {
			System.out.println("Input ID of customer to get wishlist to add to:");
			String s = scanner.nextLine();
			if (AmazonUtil.isValidInt(s)) {
				try {
					int i = getCustomerByID(Integer.parseInt(s));
					System.out.println("Selected customer with ID:" + customers.get(i).getID());
					System.out.println("Input ID of product to add to wishlist");
					s = scanner.nextLine();
					if (AmazonUtil.isValidInt(s)) {
						int b = getProductByID(Integer.parseInt(s));
						customers.get(i).addProductInWishlist(products.get(b));
					}
				} catch (AmazonException e) {}
			}
		}
	
	public static void removeProductFromWishList() {
		    System.out.println("Input ID of customer to get wishlist to remove from:");
			String s = scanner.nextLine();
			if (AmazonUtil.isValidInt(s)) {
				try {
					int i = getCustomerByID(Integer.parseInt(s));
					System.out.println("Selected customer with ID:" + customers.get(i).getID());
					System.out.println("Input ID of product to remove from wishlist");
					s = scanner.nextLine();
					if (AmazonUtil.isValidInt(s)) {
						int b = getProductByID(Integer.parseInt(s));
						if (customers.get(i).isProductInWishlist(products.get(b))) {
							customers.get(i).removeProductFromWishlist(products.get(b));
						} else {
							System.out.println("Product selected is not in that customers wishlist");
						}
					}
					
				} catch (AmazonException e) {}
			}
		}
	
	public static void showWishList() {
	    System.out.println("Input ID of customer to show wishlsit from");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				customers.get(i).showWishList();
			} catch (AmazonException e ) {}
		}
	}
	
	public static void addProductInCart() {
		System.out.println("Input ID of customer to add items to: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				System.out.println("Selected customer with ID:" + customers.get(i).getID());
				System.out.println("Input ID of product to add to cart");
				s = scanner.nextLine();
				if (AmazonUtil.isValidInt(s)) {
					int b = getProductByID(Integer.parseInt(s));
					System.out.println("Input quantity of product to add to cart");
					s = scanner.nextLine();
					if (AmazonUtil.isValidInt(s)) {
						customers.get(i).addItemInCart(products.get(b), Integer.parseInt(s));
					}
				}
			} catch (AmazonException e) {}
		}
	}
	
	public static void removeProductFromCart() {
		System.out.println("Input ID of customer to remove items from: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				System.out.println("Selected customer with ID:" + customers.get(i).getID());
				System.out.println("Input ID of product to remove from cart");
				s = scanner.nextLine();
				if (AmazonUtil.isValidInt(s)) {
					int b = getProductByID(Integer.parseInt(s));
					customers.get(i).removeItemFromCart(products.get(b));
				}
			} catch (AmazonException e) {}
		}
	}
	
	public static void showProductsInCart() {
		System.out.println("Input ID of customer to show cart: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s)); 
				customers.get(i).showCart();
			} catch (AmazonException e) {}
		} else {
			System.out.println("Invalid input");
		}
	}
	
	public static void addCredit() {
		System.out.println("Input ID of customer to add credit to: ");
		String s = scanner.nextLine();
		String[] data = new String[3];
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				System.out.println("Input the type of credit: [1] Cash [2] Check [3] Card");
				s = scanner.nextLine();
				switch(s) {
				case "1":
					System.out.println("Input amount of cash:");
					data[0] = scanner.nextLine();
					if(AmazonUtil.isValidFloat(data[0])) {
						AmazonCredit credit = AmazonCash.createCash(data);
						customers.get(i).addCredit(credit);
					} else {
						System.out.println("Invalid amount of cash");
					}
					break;
				case "2":
					System.out.println("Input amount of cash:");
					data[0] = scanner.nextLine();
					if (!AmazonUtil.isValidFloat(data[0])) {
						System.out.println("Invalid amount of cash");
						break;
					}
					System.out.println("Input account number: ");
					data[1] = scanner.nextLine();
					if (!AmazonUtil.isValidString(data[1])) {
						System.out.println("Invalid account number");
						break;
					}
					AmazonCredit credit = AmazonCheck.createCheck(data);
					customers.get(i).addCredit(credit);
					break;
				case "3":
					System.out.println("Input amount of cash:");
					data[0] = scanner.nextLine();
					if (!AmazonUtil.isValidFloat(data[0])) {
						System.out.println("Invalid amount of cash");
						break;
					}
					System.out.println("Input card number:");
					data[1] = scanner.nextLine();
					if (!AmazonUtil.isValidString(data[1])) {
						System.out.println("Invalid card number");
						break;
					}
					System.out.println("Input card expiry date: ");
					data[2] = scanner.nextLine();
					if (!AmazonUtil.isValidString(data[2])) {
						System.out.println("Invalid expiry date");
						break;
					}
					AmazonCredit credit2 = AmazonCard.createCard(data);
					customers.get(i).addCredit(credit2);
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
			} catch (AmazonException e) {}
		}
	}
	
	public static void exit() {
		System.out.println("End of application. Authors: Liam McCrorie");
	}
	
	public static void showCredits() {
		System.out.println("Input ID of customer to show credit from: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				customers.get(i).showCredits();
			} catch (AmazonException e) {}
		}
	}
	
	public static void payCart() {
		System.out.println("Input ID of customer to pay: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				customers.get(i).pay();
			} catch (AmazonException e) {}
		}
	}
	
	public static void addCommentToProduct() {
		System.out.println("Input ID of customer to add comment: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				customers.get(i).addComment();
			} catch (AmazonException e) {}
		}
	}
	
	public static void showComments() {
		System.out.println("Input ID of customer to show comments from: ");
		String s = scanner.nextLine();
		if (AmazonUtil.isValidInt(s)) {
			try {
				int i = getCustomerByID(Integer.parseInt(s));
				customers.get(i).showComments();
			} catch (AmazonException e) {}
		}
	}
	
	public static int getCustomerByID(int id) throws AmazonException {
		for(AmazonCustomer c:customers) {
			if(c.getID() == id) {
				return customers.indexOf(c);
			}
		}
		throw new AmazonException("Customer with given ID does not exist");
	}
	
	public static int getProductByID(int id) throws AmazonException {
		for(AmazonProduct p:products) {
			if(p.getID() == id) {
				return products.indexOf(p);
			}
		}
		throw new AmazonException("Product with given ID does not exist");
	}
	
	public static void searchProductList() {
		System.out.println("Input string to search product list for: ");
		String input = scanner.nextLine();
		
			System.out.println("Index(es) of Products which contain the string \"" + input + "\": ");
			int counts = 0;
			for(int i = 0; i < products.size(); i++) {
				if(products.get(i).toString().contains(input)) {
					System.out.print(i + ", ");
					counts += 1;
				}
			}
			System.out.println("");
			if (counts == 0) {
				System.out.println("None");
			}
	}
	
	public static void main(String[] args) throws AmazonException {
		String input = "";
		while(!input.equalsIgnoreCase("Q")) {
		showMenu();
		try {
			input = scanner.nextLine();
			input = input.toUpperCase();
				switch(input) {
				case "A": 
					loadProductList();
					break;
				case "B":
					showProductList();
					break;
				case "C":
					if(!products.isEmpty()) {
						searchProductList();
						break;
					} else {
						System.out.println("Product list is empty");
					}
					break;
				case "D":
					addCustomer();
					break;
				case "E":
					showCustomers();
					break;
				case "F":
					if(!customers.isEmpty()) {
						addCredit();
						break;
					} else {
						System.out.println("No customers to add credit to");
					}
					break;
				case "G":
					showCredits();
					break;
				case "H":
					addProductInWishList();
					break;
				case "I":
					removeProductFromWishList();
					break;
				case "J":
					if(!customers.isEmpty()) {
						showWishList();
						break;
					} else {
						System.out.println("No customers to add credit to");
					}
					break;
				case "K":
					if(!customers.isEmpty() || !products.isEmpty()) {
						addProductInCart();
						break;
					} else {
						System.out.println("No products/customers");
					}
					break;
				case "L":
					removeProductFromCart();
					break;
				case "M":
					showProductsInCart();
					break;
				case "N":
					payCart();
					break;
				case "O":
					addCommentToProduct();
					break;
				case "P":
					showComments();
					break;
				case "Q":
					exit();
					break;
				default: throw new AmazonException("Invalid Input: please input a valid character");
				}
			} catch (Exception e) {
				
			} 
		}
	}
}
