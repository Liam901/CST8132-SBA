package amazonsystem;


public class AmazonCard extends AmazonCredit {
	private String cardNumber;
	private String expiration;
	PaymentType type = PaymentType.Card;
	
	private AmazonCard(float value, String cardNumber, String expiration) {
		this.value = value;
		this.cardNumber = cardNumber;
		this.expiration = expiration;
	}
	
	public static AmazonCredit createCard(String[] data) {
		return new AmazonCard(Float.parseFloat(data[0]), data[1], data[2]);
	}
	
	public String toString() {
		return "Payment Type: " + type + ", Card Number: " + cardNumber + ", Expiration Date: " + expiration + ", Value: " + value; 
	}
}
