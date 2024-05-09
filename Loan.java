
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private Client client;
    private Item item;
    private LocalDate deadline;
    private double totalValue;
    private boolean renewed;

    // Constructor
    public Loan(Client client, Item item, LocalDate deadline) {
        this.client = client;
        this.item = item;
        this.deadline = deadline;
        this.totalValue = item.getPrice();
        this.renewed = false;
    }


    // Getters and Setters
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public boolean getRenewed() {
        return renewed;
    }
    

    // Object methods

    /*Calculates the penalty based on the days of delay to return the item
     * and it's original cost of rent
     * Returns a double
     */
    private double calcPenalty() {
        // penalty of 10% on the original cost of the item
        final double PENALTYPERCENTAGE = 0.1;

        // Calculates how many days late the item is
        LocalDate currentDate = LocalDate.now();
        long lateDays = ChronoUnit.DAYS.between(deadline, currentDate);

        // Returns the penalty based on how many days are late and the original item's value
        return (item.getPrice() * PENALTYPERCENTAGE) * lateDays;
    }

    /*
     * Verifies if the item is late 
     * if it is late: deducts the penalty cost from the clients balance and updates the loan's total cost
     * if it is not: returns false
     */
    public boolean isLate() {
        if(deadline.isBefore(LocalDate.now())) {
            double penalty = calcPenalty();

            // Substracts the value of the penalty from the client's balance
            client.setBalance(client.getBalance() - penalty);

            // Adds the penalty value to the total cost of the loan
            setTotalValue(totalValue + penalty);
            return true;
        }
        return false;
    }

    /*
    Calculates the cost to renew an item based on the days added 
     * The loan should be renewed to 5, 10 or 15 days
    */
    private double calcRenewCost(int days) {
        double initialPrice = item.getPrice();
        switch (days) {
            case 5:
                return 0.4 * initialPrice;
            
            case 10:
                return 0.6 * initialPrice;
            
            case 15:
                return 0.8 * initialPrice;
            
            // Days passed as an invalid argument (not 5, 10 or 15)
            default:
                return -1;
        }
    }

    
    public boolean renewItem(int days) {
        // Verifies if the loan is late on the return deadline
        if(isLate()) {
            System.err.println("The item borrowed is late, it could not be renewed.");
            return false;
        } 
        
        // This loan was already renewed
        if (getRenewed()) {
            System.err.println("Sorry! You can only renew this item once.");
            return false;
        }
        
        double newCost = calcRenewCost(days);
        if(newCost == -1) {
            System.err.println("Sorry! You can only rent it for 5, 10 or 15 days!");
            return false;
        }

        setDeadline(deadline.plusDays(days));
        setTotalValue(totalValue + newCost);
        return true;
    }

    public boolean returnItem() {
        // Verifies if the loan is late on the return deadline
        if(isLate()) {
            // Change the clients balance
            System.err.println("The item is late");
            return false;
        } 
        // The item is not late -> implement
        client.getLoans().remove(this);
        return true;
    }

    /* To implement */
    public int rateClient() {
        int rating = 0;
        return rating;
    }
}
