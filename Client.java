import java.util.ArrayList;

public class Client {
    private double balance;
    private ArrayList<Loan> loans;

    // Constructor method
    public Client(double balance) {
        this.balance = balance;
        this.loans = null;
    }

    // Getters and Setters
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public ArrayList<Loan> getLoans() {
        return loans;
    }
    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }
    
}
