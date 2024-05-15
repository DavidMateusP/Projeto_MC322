
package client;

import java.time.LocalDate;
import java.time.Period;

import products.Item;

public class Loan {
    private Client client;
    private Item item;
    private LocalDate deadline;
    private boolean renewed;

    // Constructor
    private Loan(Client client, Item item, LocalDate deadline) {
        this.client = client;
        this.item = item;
        this.deadline = deadline;
        this.renewed = false;
    }

    public static boolean newLoan(Client client, Item item) {
        if (item.getAvailableQuantity() <= 0) {
            // System.err.println("Sorry! This item is out of stock.");
            return false;
        }

        if (client.getBalance() < 0) {
            // System.err.println("Sorry! You could not rent any new item, because you have
            // a debt of "+getBalance());
            return false;
        }

        if (client.hasLateItems()) {
            // System.err.println("Sorry! You could not rent any new item, because you
            // exceeded the return deadline of one of your items");
            return false;
        }

        // limits the amount of simultaneous loans to 5 items
        if (client.getLoans().size() >= 5) {
            // System.out.println("Sorry! You could not rent any new items, because you have
            // 5 items still rented.");
            return false;
        }

        if (client.getAge() < item.getRecommendedAge()) {
            // System.err.println("Sorry! You are not old enough.");
            return false;
        }

        // Sets the deadline of two weeks to the item rented
        LocalDate deadline = LocalDate.now().plusDays(15);

        // Initializes a loan object and adds it to the array of loans made by the
        // client and the item
        Loan loan = new Loan(client, item, deadline);
        client.addLoan(loan);
        item.addLoan(loan);

        // Charges the client for the loan;
        client.changeBalance(-item.getPrice());

        // System.out.println("Enjoy "+item.getName()+"! You can return it or renew it
        // until: "+loan.getDeadline());
        return true;
    }

    // Getters and Setters
    public Client getClient() {
        return client;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isRenewed() {
        return renewed;
    }

    // Object methods

    private double calcPenalty() {
        /*
         * Calculates the penalty based on the days of delay to return the item
         * and it's original cost of rent
         * Returns a double
         */

        // penalty of 10% on the original cost of the item
        final double PENALTYPERCENTAGE = 0.1;

        // Calculates how many days late the item is
        LocalDate currentDate = LocalDate.now();
        long lateDays = Period.between(deadline, currentDate).getDays();

        // Returns the penalty based on how many days are late and the original item's
        // value
        return item.getPrice() * PENALTYPERCENTAGE * lateDays;
    }

    public boolean isLate() {
        /* Verifies if the item is late */
        return deadline.isBefore(LocalDate.now());
    }

    private double calcRenewCost(int days) {
        /*
         * Calculates the cost to renew an item based on the days added
         * The loan should be renewed to 5, 10 or 15 days
         */
        double initialPrice = item.getPrice();
        switch (days) {
            case 5:
                return 0.3 * initialPrice;

            case 10:
                return 0.6 * initialPrice;

            case 15:
            default: // Defaults to 15
                return 0.8 * initialPrice;
        }
    }

    public boolean renewItem(int days) {
        // Verifies if the loan is late on the return deadline
        if (isLate()) {
            System.err.println("The item borrowed is late, it could not be renewed.");
            return false;
        }

        // This loan was already renewed
        if (isRenewed()) {
            System.err.println("Sorry! You can only renew this item once.");
            return false;
        }

        client.changeBalance(-calcRenewCost(days));
        deadline = deadline.plusDays(days);
        renewed = true;

        return true;
    }

    public boolean returnItem(Rating itemReview) {
        // Verifies if the loan is late on the return deadline
        if (isLate()) {
            // Change the clients balance
            Double penalty = calcPenalty();
            client.changeBalance(-penalty);

            System.err.println(
                    "The item is late, a penalty of " + penalty.toString() + " was deducted from your balance.");
        }
        // The item is not late -> implement
        client.removeLoan(this);
        item.removeLoan(this);
        item.addRating(itemReview);
        System.out.println("The item was successfully returned.");
        return true;
    }
}
