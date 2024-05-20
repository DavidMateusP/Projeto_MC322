package products;

import java.util.ArrayList;

import client.Rates;
import client.Rating;

public class Track {
    String name;
    ArrayList<Rating> ratings;

    
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

    public int getReviewCount() {
        return ratings.size();
    }
    
    public Rates getAverageRating() {
        double avarage =  ratings.stream().mapToDouble((Rating r) -> r.getRating().getValue()).sum()/getReviewCount();

        if (avarage > 8) {
            return Rates.VERY_GOOD;
        }
        else if (avarage > 6) {
            return Rates.GOOD;
            
        } else  if(avarage > 4){
            return Rates.OK;
            
        } else if(avarage > 2){
            return Rates.BAD;
        } else{
            return Rates.VERY_BAD;
        }

    }
}
