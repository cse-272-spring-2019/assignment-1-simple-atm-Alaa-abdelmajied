
public class User {

	private String name;
	private int cardNumber;
	private double balance;
	private int transactionType;

	public User() {
		name = null;
		cardNumber = 0;
		balance = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	void commitTransaction(int type, double transactionAmount) {
		transactionType = type;
		if (transactionType == Transactions.DEPOSITE_TRANSACTION) {
			balance = balance + transactionAmount;
		} else if (transactionType == Transactions.WITHDRAW_TRANSACTION) {
			balance = balance - transactionAmount;
		} else if (transactionType == Transactions.BALANCE_INQUIRY) {

		}
	}

	public double getBalance() {
		return balance;
	}

}
