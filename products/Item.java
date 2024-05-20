package products;

import java.util.ArrayList;

import client.Loan;
import client.Rates;
import client.Rating;

public abstract class Item {
    private ArrayList<Loan> loans;
    private int quantity;
    private String name;
    private int releaseYear;
    private int recommendedAge;
    private double price;
    private ArrayList<Rating> ratings;

    // Constructor method
    protected Item(int quantity, String name, int releaseYear, int recommendedAge, double price) {
        loans = new ArrayList<>();
        ratings = new ArrayList<>();
        this.quantity = quantity;
        this.name = name;
        this.releaseYear = releaseYear;
        this.recommendedAge = recommendedAge;
        this.price = price;
    }

    public int getReviewCount() {
        return ratings.size();
    }

    public Rates getAverageRating() {
        return Rating.avarageRating(this.ratings);

    }

    // Getters and Setters
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return quantity - loans.size();
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

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }
}