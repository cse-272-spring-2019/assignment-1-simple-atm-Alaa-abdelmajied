
public class UserManger {

	private String name;
	private int cardNumber;
	private int password;
	private double balance;
	String[] transaction = new String[5];

	public UserManger(String name, int cardNumber, int password, double balance) {

		this.name = name;
		this.cardNumber = cardNumber;
		this.password = password;
		this.balance = balance;

	}

	public String getName() {
		return name;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getPassword() {
		return password;
	}

	public double getBalance() {
		return balance;
	}

}
