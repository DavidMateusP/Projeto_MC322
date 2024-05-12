
package client;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Client {
    private double balance;
    private ArrayList<Loan> loans;
    private String name;
    private LocalDate birthDate;

    // Constructor method
    public Client(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.balance = 0;
        this.loans = new ArrayList<>();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public int getAge() {
        return Period.between(LocalDate.now(), birthDate).getYears();
    }

    public double getBalance() {
        return balance;
    }

    public void changeBalance(double amount) {
        balance += amount;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    public boolean hasLateItems() {
        for (Loan l : loans) {
            if (l.isLate()) {
                return true;
            }
        }
        return false;
    }
}
