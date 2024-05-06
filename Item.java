import java.util.ArrayList;

public abstract class Item {
    private ArrayList<Loan> loans;
    private int quantity;
    private String name;
    private int releaseYear;
    private int recommendedAge;
    private double price;
    private int rating[] = new int[5];

    private boolean validateRating(int rating) {
        return false;
    }

    private int getReviewCount() {
        return 0;
    }

    private int getRating() {
        return 0;
    }
}