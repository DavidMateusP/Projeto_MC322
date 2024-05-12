package products;
import java.util.ArrayList;

import client.Loan;
import client.Rating;

public abstract class Item {
    private ArrayList<Loan> loans;
    private int quantity;
    private String name;
    private int releaseYear;
    private int recommendedAge;
    private double price;
    private ArrayList<Rating> ratings;
    private int avarageRating;

    
    // Constructor method
    protected Item(int quantity, String name, int releaseYear, int recommendedAge, double price) {
        loans = new ArrayList<>();
        ratings = new ArrayList<>();
        this.quantity = quantity;
        this.name = name;
        this.releaseYear = releaseYear;
        this.recommendedAge = recommendedAge;
        this.price = price;
        avarageRating = -1; //Value for not yet evaluated
    }

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
        this.ratings = ratings;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getAvarageRating() {
        return avarageRating;
    }

    public void setAvarageRating(int avarageRating) {
        this.avarageRating = avarageRating;
    }


    // Methods

    /**
     * @param rating
     */
    public void addRating(Rating rating){
        ratings.add(rating);
        int numRates = ratings.size();
        for (Rating r : ratings) {
            avarageRating += r.getRate();
        }
    }
}