package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	PaymentType type = PaymentType.Check;
	private String accountNumber;
	
	private AmazonCheck(String accountNumber, float value) {
		this.value = value;
		this.accountNumber = accountNumber;
	}
	
	public static AmazonCredit createCheck(String[] data) {
		return new AmazonCheck(data[0], Float.parseFloat(data[1]));
	}
	
	public String toString() {
		return "Payment Type: " + type + ", Account Number: " + accountNumber + ", Value: " + value;
	}

}
