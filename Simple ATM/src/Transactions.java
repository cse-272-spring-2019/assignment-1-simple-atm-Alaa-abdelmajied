

public class Transactions {
	public static final int DEPOSITE_TRANSACTION = 1;
	public static final int WITHDRAW_TRANSACTION = 2;
	public static final int BALANCE_INQUIRY = 3;
	private String type;
	private double transactionAmount;
	
	
	
	public Transactions(String type, double transactionAmount) {
		
		this.type = type;
		this.transactionAmount = transactionAmount;
		
	}
	
	public String toString() {
		return type+"\n"+String.valueOf(transactionAmount);
	}

}
