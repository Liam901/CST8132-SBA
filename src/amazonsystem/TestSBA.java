package amazonsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestSBA {

	@Test
	void test() {
		// This test is divided into parts
		System.out.println("[SBA Test] :::::::::::::::::::::::::::::::::::::::::::::");
		// Change to put the name of your team
		System.out.println("[Team: Paulo Sousa] ....................................");
		AmazonProduct product;
		AmazonCustomer customer;
		AmazonCartItem item;
		List<AmazonProduct> products = new ArrayList<AmazonProduct>();
		List<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
		String[] data;
		String filename = "Amazon.test"; // Name of the file to be saved/loaded
		int index = -1;
		int size = 0;
		// PART 1 - Creating products (1pt)
		System.out.println("PART 1 - Creating products ........................(1pt)");
		data = new String[] { "1", "Prod1", "Cat1", "Subcat1", "Img1", "URL1", "1", "10", "1.1", "11.1" };
		product = AmazonProduct.createAmazonProduct(data);
		products.add(product);
		data = new String[] { "2", "Prod2", "Cat2", "Subcat2", "Img2", "URL2", "2", "20", "2.2", "22.2" };
		product = AmazonProduct.createAmazonProduct(data);
		products.add(product);
		data = new String[] { "3", "Prod3", "Cat3", "Subcat3", "Img3", "URL3", "3", "30", "3.3", "33.3" };
		product = AmazonProduct.createAmazonProduct(data);
		products.add(product);
		// PART 2 - Creating customers (1pt)
		System.out.println("PART 2 - Creating customers .......................(1pt)");
		data = new String[] { "1", "Paulo", "Ottawa" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNotNull(customer);
		customers.add(customer);
		data = new String[] { "2", "Lilia", "Halifax" };
		customer = AmazonCustomer.createAmazonCustomer(data);		
		assertNotNull(customer);
		customers.add(customer);
		// PART 3 - Creating credits (1pt)
		System.out.println("PART 3 - Creating credits .........................(1pt)");
		AmazonCredit credit;
		index = 0;
		customer = customers.get(index);
		data = new String[] {"100.0"};
		credit = AmazonCash.createCash(data);
		assertNotNull(credit);
		customer.addCredit(credit);
		customers.set(index, customer);
		index = 1;
		customer = customers.get(index);	
		data = new String[] {"MyBank", "50.0"};
		credit = AmazonCheck.createCheck(data);
		assertNotNull(credit);
		customer.addCredit(credit);
		customers.set(index, customer);		
		size = customer.getNumberOfCredits();
		assertEquals(1, size);
		// PART 4 - Creating cart / items (1pt)
		System.out.println("PART 4 - Creating cart / items ....................(1pt)");
		index = 0;
		customer = customers.get(index);
		product = products.get(0);
		size = 2;
		item = new AmazonCartItem(product, size);
		customer.addItemInCart(item);
		product = products.get(1);
		size = 1;
		item = new AmazonCartItem(product, size);
		customer.addItemInCart(item);
		size = customer.getCartSize();
		assertEquals(2, customer.getCartSize());
		customer.pay(0);
		size = customer.getCartSize();
		assertEquals(0, customer.getCartSize());
		// PART 5 - Creating comments (1pt)
		System.out.println("PART 5 - Creating comments ........................(1pt)");
		size = customer.getNumberOfComments();
		for (int i=0;i<size;i++) {
			product = customer.getComment(i).getProduct();
			customer.setComment(product, "Nice product", 5.0f);
		}
		assertEquals(2, customer.getNumberOfComments());
		customers.set(index, customer);
		// User 1
		index = 1;
		customer = customers.get(index);
		product = products.get(0);
		customer.addProductInWishList(product);
		product = products.get(1);
		customer.addProductInWishList(product);
		assertEquals(2, customer.getWishlistSize());
		customers.set(index, customer);
		product = products.get(2);
		size = 3;
		item = new AmazonCartItem(product, size);
		customer.addItemInCart(item);
		size = customer.getCartSize();
		assertEquals(1, customer.getCartSize());		
		customers.set(index, customer);
		// System data
		AmazonSystem system = new AmazonSystem();
		// PART 6 - Saving / Loading / Printing (5 pts)
		System.out.println("PART 6 - Saving file ..............................(1pt)");
		system.save(filename); 		// 1pt
		System.out.println("PART 7 - Loading file .............................(1pt)");
		system.load(filename); 		// 1pt
		System.out.println("PART 8 - Printing data ...........................(3pts)");
		system.show(customers); 	// 3pts
		System.out.println("[Test end] ::::::::::::::::::::::::::::::::::::::::::::");
	}

}
