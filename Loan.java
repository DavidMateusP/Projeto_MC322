
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private Client client;
    private Item item;
    private LocalDate deadline;
    private double totalValue;

    // Constructor
    public Loan(Client client, Item item, LocalDate deadline, double totalValue) {
        this.client = client;
        this.item = item;
        this.deadline = deadline;
        this.totalValue = totalValue;
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
    

    // Object methods
    private double calcPenalty() {
        double penalty;
        // penalty of 2% on the original cost of the item
        final double PENALTYPERCENTAGE = 0.02;

        // Calculates how many days late the item is
        LocalDate currentDate = LocalDate.now();
        double lateDays = ChronoUnit.DAYS.between(deadline, currentDate);

        // Returns the penalty based on how many days are late and the original item's value
        return (item.getCost() * PENALTYPERCENTAGE) * lateDays;
    }

    /*
     * Verifies if the item is late 
     * if it is late: deducts the penalty cost from the clients balance
     * if it is not: returns false
     */
    public boolean isLate() {
        if(deadline.isBefore(LocalDate.now())) {
            double penalty = calcPenalty();
            client.setBalance(client.getBalance() - penalty);
            return true;
        }
        return false;
    }

    /*To consider: do we want a maximmum amount of days to renew? */
    public boolean renewItem(int days) {
        // Verifies if the loan is late on the return deadline
        if(isLate()) {
            System.err.println("The item borrowed is late, it could not be renewed.");
            return false;
        } 
        // Adds x days to the new deadline
        setDeadline(deadline.plusDays(days));
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
        return true;
    }

    /* To implement */
    public int rateClient() {
        int rating = 0;
        return rating;
    }
}
