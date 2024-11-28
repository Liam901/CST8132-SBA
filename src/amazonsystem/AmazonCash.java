package amazonsystem;

public class AmazonCash extends AmazonCredit {
	PaymentType type = PaymentType.Cash;
	
	private AmazonCash(float value) {
		this.value = value;
	}
	
	public static AmazonCredit createCash(String[] data) {
		return new AmazonCash(Float.parseFloat(data[0]));
	}
	
	public String toString() {
		String line;
		line = "Payment Type: " + type + ", Value: " + value;
		return line;
	}
	
	
}
