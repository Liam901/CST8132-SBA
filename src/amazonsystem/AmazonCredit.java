package amazonsystem;

public abstract class AmazonCredit {
	enum PaymentType{
		Cash,
		Check,
		Card
	}
	 float value;
	
	public abstract String toString();
	
	public void setValue(float value) throws AmazonException {
		if (value >= 0) {
			this.value = value;
		} else {
			throw new AmazonException("Please provide a minimum of 0 credit");
		}
	}
	
	public float getValue() {
		return value;
	}
}
