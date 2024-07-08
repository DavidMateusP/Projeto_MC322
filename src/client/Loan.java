package client;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.WeekFields;
import java.util.Locale;
import products.Item;

public class Loan {
    private final Client client;
    private final Item item;
    private LocalDate deadline;
    private boolean renewed;

    // Constructor
    public Loan(Client client, Item item, LocalDate deadline) {
        this.client = client;
        this.item = item;
        this.deadline = deadline;
        this.renewed = false;
    }

    public static String newLoan(Client client, Item item) {
        if (item.getAvailableQuantity() <= 0)
            return "Item out of stock";

        if (client.getBalance() < 0)
            return "The client has a negative balance: " + Double.toString(client.getBalance());

        if (client.hasLateItems())
            return "The client has late items";

        if (client.getLoans().size() >= 5)
            return "The client already has 5 simultaneous loans";

        if (client.getAge() < item.getRecommendedAge())
            return "The client is not old enough to borrow this item";

        // Sets the deadline of two weeks to the item rented
        LocalDate deadline = LocalDate.now().plusDays(15);

        // Initializes a loan object and adds it to the array of loans made by the
        // client and the item
        Loan loan = new Loan(client, item, deadline);
        client.addLoan(loan);
        item.addLoan(loan);

        // Charges the client for the loan;
        client.changeBalance(-item.getPrice());

        System.out.println("Enjoy " + item.getName() + "! You can return it or renew it until: " + loan.getDeadline());
        return "Success";
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

        if (!isLate())
            return 0;

        // penalty of 10% per day on the original cost of the item
        final double PENALTYPERCENTAGE = 0.1;

        // Calculates how many days late the item is
        long lateDays = Period.between(deadline, LocalDate.now()).getDays();

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

    public String renewItem(int days) {
        // Verifies if the loan is late on the return deadline
        if (isLate())
            return "The item is late and cannot be renewed";

        // This loan was already renewed
        if (isRenewed())
            return "The item can only be renewed once";

        client.changeBalance(-calcRenewCost(days));
        deadline = deadline.plusDays(days);
        renewed = true;

        return "Success";
    }

    public String returnItem(Rating itemReview) {
        // Verifies if the loan is late on the return deadline
        final boolean late = isLate();
        // The item is not late -> implement
        client.removeLoan(this);
        item.removeLoan(this);
        item.addRating(itemReview);

        if (late) {
            // Change the clients balance
            final Double penalty = calcPenalty();
            client.changeBalance(-penalty);
            if (late) {
                // Change the client's balance
                client.changeBalance(-penalty);
                return "The item was late. A penalty of R$" + penalty.toString() + " was charged";
            }
        }
            return "The item has been returned";
    }

    public boolean isCurrentWeek(){
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        
        int currentWeek = today.get(weekFields.weekOfWeekBasedYear());
        int givenWeek = deadline.get(weekFields.weekOfWeekBasedYear());
        
        return currentWeek == givenWeek && today.getYear() == deadline.getYear();
    }
}
