public class MyAtm {

	private String name;
	private double balance;
	private int cardNumber;
	private int password;
	private User user = new User();
	private int historyCounter = 0;
	private Transactions transaction;

	UserManger userManger = new UserManger("Alaa", 54312, 1234, 0);

	public MyAtm() {
		cardNumber = 0;
		password = 0;
	}

	public boolean validate() {

		if (cardNumber == userManger.getCardNumber()) {
			if (password == userManger.getPassword()) {
				user.setName(userManger.getName());
				user.setCardNumber(userManger.getCardNumber());
				user.setBalance(userManger.getBalance());
				balance = user.getBalance();
				name = user.getName();
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void history() {
		if (historyCounter < 5) {
			userManger.transaction[historyCounter] = transaction.toString();
			historyCounter++;
		} else {
			for (int i = 0; i < 4; i++) {
				userManger.transaction[i] = userManger.transaction[i + 1];
			}
			userManger.transaction[4] = transaction.toString();
		}
	}

	public boolean deposite(double transactionAmount) {
		if (transactionAmount > 0) {
			transaction = new Transactions("Deposite Transaction", transactionAmount);
			user.commitTransaction(Transactions.DEPOSITE_TRANSACTION, transactionAmount);
			balance = user.getBalance();
			history();
			return true;
		} else
			return false;
	}

	public boolean withdraw(double transactionAmount) {
		if (balance >= transactionAmount && transactionAmount >= 0) {
			transaction = new Transactions("Withdraw Transaction", transactionAmount);
			user.commitTransaction(Transactions.WITHDRAW_TRANSACTION, transactionAmount);
			balance = user.getBalance();
			history();
			return true;
		} else
			return false;
	}

	public double balanceInquiry() {

		transaction = new Transactions("Balance Inquiry", balance);
		history();
		return balance;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

}
