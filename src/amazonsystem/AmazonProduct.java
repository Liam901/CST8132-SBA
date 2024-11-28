package amazonsystem;

public class AmazonProduct {
	private int id;
	private String name;
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	private String imageURL;
	private String link;
	private float rating;
	private int numRatings;
	private float discountPrice;
	private float actualPrice;
	
	public AmazonProduct(int id, String name, String category, String subCategory, String imageURL, String link, float rating, int numRatings, float discountPrice, float actualPrice) {
		this.id = id;
		this.name = name;
		this.category = new AmazonProductCategory(category);
		this.subCategory = new AmazonProductSubCategory(subCategory, this.category);
		this.imageURL = imageURL;
		this.link = link;
		this.rating = rating;
		this.numRatings = numRatings;
		this.discountPrice = discountPrice;
		this.actualPrice = actualPrice;
	}
	
	public static AmazonProduct createAmazonProduct(String[] values) throws AmazonException{
		int id = Integer.parseInt(values[0]);
		String name = values[1];
		String category = (values[2]);
		String subCategory = values[3];
		String imageURL = values[4];
		String link = values[5];
		float rating = Float.parseFloat(values[6]);
		int numRatings = Integer.parseInt(values[7].replace("," ,""));
		float discountPrice =  Float.parseFloat(values[8].replace("," ,"").replace("₹", ""));
		float actualPrice =  Float.parseFloat(values[7].replace("," ,"").replace("₹",""));
		AmazonProduct p = new AmazonProduct (id, name, category, subCategory, imageURL, link, rating, numRatings, discountPrice, actualPrice);
		return p;
	}
	
	public String toString() {
		String line;
		line = id + ",\"" + name + "\"," + category.getCategory() + "," 
		+ subCategory.getSubCategory() + "," + imageURL + "," + link + ","
		+ rating + "," + numRatings + ", \"" + discountPrice + "\", \"" + actualPrice + "\"";
		return line;
	}
	
	public String getProductDetails() {
		String line;
		line = "ID: " + id + ", Name: \"" + name + "\", Rating: " + rating + ", Discounted Price/Original Price: " + discountPrice + "/" + actualPrice;
		return line;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategoryName() {
		return category.getCategory();
	}
	
	public String getSubCategoryName() {
		return subCategory.getSubCategory();
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public String getLink() {
		return link;
	}
	
	public float getRating() {
		return rating;
	}	
	
	public int getNumRatings() {
		return numRatings;	
	}
	
	public float getDiscountPrice() {
		return discountPrice;
	}
	
	public float getActualPrice() {
		return actualPrice;
	}
}
