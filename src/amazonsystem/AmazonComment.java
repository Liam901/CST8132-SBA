package amazonsystem;

public class AmazonComment {
	private String comment;
	private AmazonProduct product;
	private float rating;
	
	public AmazonComment(AmazonProduct product) {
		this.product = product;
	}
	
	public void setComment(String comment) throws AmazonException {
		if(AmazonUtil.isValidString(comment)) {
			this.comment = comment;
		} else {
			throw new AmazonException("Please provide a valid comment");
		}
	}
	
	public void setRating(float rating) throws AmazonException {
		if(rating >= 1.0 && rating <= 5.0) {
			this.rating = rating;
		} else {
			throw new AmazonException("Rating must be between 1.0 and 5.0");
		}
		
	}
	
	public String toString() {
		return "Product: " + product.getName() + ", Comment: \"" + comment + "\", Rating: " + rating;
	}
	
	public boolean hasComment() {
		if(comment.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public AmazonProduct getProduct() {
		return product;
	}
	
	public String getComment() {
		return comment;
	}
	
	public float getRating() {
		return rating;
	}
}
