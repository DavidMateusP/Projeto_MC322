package client;

import java.util.ArrayList;

public class Rating {

    private String comment;
    private Rates rating;

    public Rating(String comment, Rates rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public Rating(Rates rating) {
        this.comment = " ";
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Rates getRating() {
        return rating;
    }

    public void setRating(Rates rating) {
        this.rating = rating;
    }

    public static Rates averageRating(ArrayList<Rating> ratings) {
        double average = ratings.stream().mapToDouble((Rating r) -> r.getRating().getValue()).sum() / ratings.size();

        if (average > 8) {
            return Rates.VERY_GOOD;
        } else if (average > 6) {
            return Rates.GOOD;
        } else if (average > 4) {
            return Rates.OK;
        } else if (average > 2) {
            return Rates.BAD;
        } else {
            return Rates.VERY_BAD;
        }
    }

}
