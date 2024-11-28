package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	PaymentType type = PaymentType.Check;
	private String accountNumber;
	
	private AmazonCheck(float value, String accountNumber) {
		this.value = value;
		this.accountNumber = accountNumber;
	}
	
	public static AmazonCredit createCheck(String[] data) {
		return new AmazonCheck(Float.parseFloat(data[0]), data[1]);
	}
	
	public String toString() {
		return "Payment Type: " + type + ", Account Number: " + accountNumber + ", Value: " + value;
	}

}
