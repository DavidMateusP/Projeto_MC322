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

    //Getters and Setters
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(int[] rating) {
        this.rating = rating;
    }
}