package amazonsystem;

public class AmazonException extends Exception {
	public AmazonException(String errorMessage) {
		System.err.println("AmazonProductException: " + errorMessage);
	}
}
