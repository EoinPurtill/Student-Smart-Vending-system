package users;

import io.Writable;

public class User implements Writable {

	private String studentID;
	private String nfcHash;
	private double credit;
	private String salesHistory;

	public User(String id, String nfc, double credit_) {
		nfcHash = nfc;
		studentID = id;
		credit = credit_;
	}

	public void lowerBalance(double amount) {
		credit -= amount;
	}

	public void increaseBalance(double amount) {
		credit += amount;
	}

	public String getID() {
		return studentID;
	}

	public double getCredit() {
		return credit;
	}

	public boolean assertDetails(String id) {
		return (id.compareTo(studentID) == 0);
	}

	public String toCSV() {
		return (studentID + "," + nfcHash + "," + String.format("%.2f", credit));
	}
}