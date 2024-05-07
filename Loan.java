
import java.time.LocalDate;

public class Loan {
    private Client client;
    private Item item;
    private LocalDate deadline;
    private double totalValue;

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
    public boolean renewItem(int days) {
        // Verifies if the loan is late on the return deadline
        if(deadline.isBefore(LocalDate.now())) {
            System.err.println("The item borrowed is late, it could not be renewed.");
            return false;
        } 
        // Adds x days to the new deadline
        setDeadline(deadline.plusDays(days));
        return true;
    }

    public boolean returnItem(Item item) {
        return false;
    }

    public int rateClient() {
        int rating = 0;
        return rating;
    }
}
