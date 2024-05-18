package client;


public class Rating {

    private String comment;
    private Rates rating;

    public Rating(String comment, Rates rating) {
        this.comment = comment;
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

}
