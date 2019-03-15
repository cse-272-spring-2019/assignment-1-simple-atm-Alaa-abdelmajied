
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AtmViewer extends Application {

	MyAtm client = new MyAtm();
	Scene loginForm, mainMenu, transactionHistory, balanceInquiry, deposite, withdraw;
	boolean isInteger;
	int startHistoryCounter = -1;
	int historyCounter;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		// login scene

		Label cardNumberLabel = new Label("Card Number: ");
		Label passwordLabel = new Label("Password:");
		TextField cardNumberField = new TextField();
		PasswordField passwordField = new PasswordField();
		Button Login = new Button("Login");

		GridPane loginGrid = new GridPane();
		loginGrid.add(cardNumberLabel, 0, 0);
		loginGrid.add(passwordLabel, 0, 1);
		loginGrid.add(cardNumberField, 1, 0);
		loginGrid.add(passwordField, 1, 1);
		loginGrid.add(Login, 1, 2);

		loginForm = new Scene(loginGrid, 600, 400);

		// Main menu scene

		Label userName = new Label();
		Button balanceInquiry = new Button("Check Balance");
		Button depositeMainMenu = new Button("Deposite");
		Button withdrawMainMenu = new Button("Withdraw");
		Button history = new Button("Transaction History");
		Button logout = new Button("Logout");

		GridPane mainMenuGrid = new GridPane();
		mainMenuGrid.add(userName, 1, 0);
		mainMenuGrid.add(depositeMainMenu, 0, 2);
		mainMenuGrid.add(withdrawMainMenu, 2, 2);
		mainMenuGrid.add(balanceInquiry, 0, 3);
		mainMenuGrid.add(history, 2, 3);
		mainMenuGrid.add(logout, 1, 4);

		mainMenu = new Scene(mainMenuGrid, 600, 400);

		// Balance Inquiry Scene

		Label messageBalance = new Label("Current Balance :    ");
		Label valueBalance = new Label();
		Button backBalance = new Button("Back");

		GridPane balanceInquiryGrid = new GridPane();
		balanceInquiryGrid.add(messageBalance, 0, 0);
		balanceInquiryGrid.add(valueBalance, 1, 0);
		balanceInquiryGrid.add(backBalance, 1, 2);

		this.balanceInquiry = new Scene(balanceInquiryGrid, 600, 400);

		// History scene

		Label viewTransaction = new Label();
		Button backHistory = new Button("Back");
		Button prev = new Button("Prev");
		Button next = new Button("Next");

		GridPane historyGrid = new GridPane();
		historyGrid.add(viewTransaction, 1, 0);
		historyGrid.add(backHistory, 0, 2);
		historyGrid.add(prev, 1, 2);
		historyGrid.add(next, 2, 2);

		transactionHistory = new Scene(historyGrid, 600, 400);

		// Deposite Scene

		Label messageDeposite = new Label("Enter the money");
		TextField valueDeposite = new TextField();
		Button backDeposite = new Button("Back");
		Button deposite = new Button("Depostie");

		GridPane depositeGrid = new GridPane();
		depositeGrid.add(messageDeposite, 0, 0);
		depositeGrid.add(valueDeposite, 1, 0);
		depositeGrid.add(backDeposite, 0, 2);
		depositeGrid.add(deposite, 1, 2);

		this.deposite = new Scene(depositeGrid, 600, 400);

		// Withdraw Scene

		Label messageWithdraw = new Label("Enter the money");
		TextField valueWithdraw = new TextField();
		Button backWithdraw = new Button("Back");
		Button withdraw = new Button("Withdraw");

		GridPane withdrawGrid = new GridPane();
		withdrawGrid.add(messageWithdraw, 0, 0);
		withdrawGrid.add(valueWithdraw, 1, 0);
		withdrawGrid.add(backWithdraw, 0, 2);
		withdrawGrid.add(withdraw, 1, 2);

		this.withdraw = new Scene(withdrawGrid, 600, 400);

		// login event

		Login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					Integer.parseInt(cardNumberField.getText());
					Integer.parseInt(passwordField.getText());
					isInteger = true;
				} catch (Exception e) {
					Alert alertLogin = new Alert(AlertType.ERROR);
					alertLogin.setTitle("Login");
					alertLogin.setHeaderText("Login Error");
					alertLogin.setContentText("Wrong card number or password!");
					alertLogin.showAndWait();
					isInteger = false;
				}

				if (isInteger) {
					int cardNumber = Integer.parseInt(cardNumberField.getText());
					int password = Integer.parseInt(passwordField.getText());

					client.setCardNumber(cardNumber);
					client.setPassword(password);

					boolean valid = client.validate();
					if (valid) {
						primaryStage.setScene(mainMenu);
						userName.setText("Welcome " + client.getName());
					} else {
						Alert alertLogin = new Alert(AlertType.ERROR);
						alertLogin.setTitle("Login");
						alertLogin.setHeaderText("Login Error");
						alertLogin.setContentText("Wrong card number or password!");
						alertLogin.showAndWait();
					}
				}
			}
		});

		// Main Menu events

		depositeMainMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(AtmViewer.this.deposite);
				valueDeposite.setText("");

			}
		});

		withdrawMainMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(AtmViewer.this.withdraw);
				valueWithdraw.setText("");

			}
		});

		balanceInquiry.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(AtmViewer.this.balanceInquiry);
				valueBalance.setText(String.valueOf(client.balanceInquiry()));

			}
		});

		history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (startHistoryCounter >= 0) {
					if (startHistoryCounter > 5) {
						startHistoryCounter = 4;
					}
					primaryStage.setScene(transactionHistory);
					viewTransaction.setText(client.userManger.transaction[startHistoryCounter]);
					historyCounter = startHistoryCounter;
				} else {
					Alert alertHistory = new Alert(AlertType.ERROR);
					alertHistory.setTitle("Menu");
					alertHistory.setHeaderText("Menu Error");
					alertHistory.setContentText("There is no actions done yet!");
					alertHistory.showAndWait();
				}
			}
		});

		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(loginForm);
				cardNumberField.setText("");
				passwordField.setText("");

			}
		});

		// balance Inquiry events

		backBalance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(mainMenu);
				startHistoryCounter++;

			}
		});

		// history events

		backHistory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(mainMenu);

			}
		});

		prev.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (historyCounter > 0) {
					viewTransaction.setText(client.userManger.transaction[--historyCounter]);
				} else {
					Alert alertPrevHistory = new Alert(AlertType.ERROR);
					alertPrevHistory.setTitle("History");
					alertPrevHistory.setHeaderText("History Error");
					alertPrevHistory.setContentText("There is no previous history!");
					alertPrevHistory.showAndWait();
				}
			}
		});

		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (historyCounter < startHistoryCounter) {
					viewTransaction.setText(client.userManger.transaction[++historyCounter]);
				} else {
					Alert alertNextHistory = new Alert(AlertType.ERROR);
					alertNextHistory.setTitle("History");
					alertNextHistory.setHeaderText("History Error");
					alertNextHistory.setContentText("There is no next history!");
					alertNextHistory.showAndWait();
				}

			}
		});

		// deposite events

		backDeposite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(mainMenu);

			}
		});

		deposite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					Double.parseDouble(valueDeposite.getText());
					isInteger = true;
				} catch (Exception e) {
					Alert alertDepositeError = new Alert(AlertType.ERROR);
					alertDepositeError.setTitle("Transaction");
					alertDepositeError.setHeaderText("Unsuccessful Transaction");
					alertDepositeError.setContentText("Please enter a valid number!");
					alertDepositeError.showAndWait();
					isInteger = false;
				}
				if (isInteger) {
					double value = Double.parseDouble(valueDeposite.getText());
					boolean validDeposite = client.deposite(value);
					if (validDeposite) {

						Alert alertDeposite = new Alert(AlertType.INFORMATION);
						alertDeposite.setTitle("Transaction");
						alertDeposite.setHeaderText("Successful Transaction");
						alertDeposite.setContentText("The money has entered your account!");
						alertDeposite.showAndWait();
						primaryStage.setScene(mainMenu);
						startHistoryCounter++;
					} else {
						Alert alertDepositeError = new Alert(AlertType.ERROR);
						alertDepositeError.setTitle("Transaction");
						alertDepositeError.setHeaderText("Unsuccessful Transaction");
						alertDepositeError.setContentText("Please enter a valid number!");
						alertDepositeError.showAndWait();
					}
				}
			}
		});

		// withdraw events

		backWithdraw.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(mainMenu);

			}
		});

		withdraw.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					Double.parseDouble(valueWithdraw.getText());
					isInteger = true;
				} catch (Exception e) {
					Alert alertWithdrawError = new Alert(AlertType.ERROR);
					alertWithdrawError.setTitle("Transaction");
					alertWithdrawError.setHeaderText("Unsuccessful Transaction");
					alertWithdrawError.setContentText("Please enter a number within your balance!");
					alertWithdrawError.showAndWait();
					isInteger = false;
				}
				if (isInteger) {
					double withdrawValue = Double.parseDouble(valueWithdraw.getText());
					boolean validWithdraw = client.withdraw(withdrawValue);
					if (validWithdraw) {
						Alert alertWithdraw = new Alert(AlertType.INFORMATION);
						alertWithdraw.setTitle("Transaction");
						alertWithdraw.setHeaderText("Successful Transaction");
						alertWithdraw.setContentText("Wait for the money!");
						alertWithdraw.showAndWait();
						primaryStage.setScene(mainMenu);
						startHistoryCounter++;
					} else {
						Alert alertWithdrawError = new Alert(AlertType.ERROR);
						alertWithdrawError.setTitle("Transaction");
						alertWithdrawError.setHeaderText("Unsuccessful Transaction");
						alertWithdrawError.setContentText("Please enter a number within your balance!");
						alertWithdrawError.showAndWait();
					}
				}
			}
		});

		primaryStage.setScene(loginForm);
		primaryStage.setTitle("ATM");
		primaryStage.show();

	}

}
