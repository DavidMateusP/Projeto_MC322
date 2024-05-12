package client;

public class Rating {

    private String comment;
    private int rating;

    public Rating(String comment, int rating) {
        this.comment = comment;
        setRating(rating);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0) {
            rating = 0;
        } else if (rating > 5) {
            rating = 5;
        }
        this.rating = rating;
    }

}
