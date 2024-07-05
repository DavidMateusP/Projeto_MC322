package client;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Client implements Serializable {
    private static final long serialVersionUID = 302L;
    private double balance;
    private ArrayList<Loan> loans;
    private final String name;
    private final LocalDate birthDate;
    private final String cpf;

    // Constructor method
    public Client(String name, LocalDate birthDate, String cpf) {
        this.name = name;
        this.birthDate = birthDate;
        this.balance = 0;
        this.loans = new ArrayList<>();
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
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
        if (loans.contains(loan))
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
