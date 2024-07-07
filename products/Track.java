package products;

import java.io.Serializable;
import java.util.ArrayList;

import client.Rating;

public class Track implements Serializable{
    private static final long serialVersionUID = 302l;
    private String name;
    private ArrayList<Rating> ratings;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }
}
