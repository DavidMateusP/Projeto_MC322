
package client;


import java.time.LocalDate;
import java.util.ArrayList;

import products.Item;
import client.Loan;


public class Client {
    private double balance;
    private ArrayList<Loan> loans;

    // Constructor method
    public Client() {
        this.balance = 0;
        this.loans = new ArrayList<>();
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

    public boolean hasLateItems() {
        for (int i=0; i<loans.size(); i++) {
            if (loans.get(i).isLate()) {
                return true;
            }
        }
        return false;
    }
    // Object methods
    public boolean rentItem(Item item) {
        int nLoans = (item.getLoans()).size();
        if(nLoans == item.getQuantity()) {
            //System.err.println("Sorry! This item is out of stock.");
            return false;
        }

        if(balance < 0) {
            //System.err.println("Sorry! You could not rent any new item, because you have a debt of "+getBalance());
            return false;
        }

        if (hasLateItems()) {
            //System.err.println("Sorry! You could not rent any new item, because you exceeded the return deadline of one of your items");
            return false;
        }

        // limits the amount of simultaneous loans to 5 items
        if (loans.size() == 5) {
            // System.out.println("Sorry! You could not rent any new items, because you have 5 items still rented.");
            return false;
        }

        // Sets the deadline of two weeks to the item rented
        LocalDate deadline = LocalDate.now().plusDays(15);


        // Initializes a loan object and adds it to the array of loans made by the client
        Loan loan = new Loan(this, item, deadline);
        loans.add(loan);

        //System.out.println("Enjoy "+item.getName()+"! You can return it or renew it until: "+loan.getDeadline());
        return true;
    }
    
}
