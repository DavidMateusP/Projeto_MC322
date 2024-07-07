package products;

import java.util.ArrayList;

public class Movie extends Item {
    private String cast;
    private ArrayList<String> subtitleLanguages;

    public Movie(int quantity, String name, int releaseYear, int recommendedAge, double price, String cast,
            ArrayList<String> subtitleLanguages) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.cast = cast;
        this.subtitleLanguages = subtitleLanguages;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public ArrayList<String> getSubtitleLanguages() {
        return subtitleLanguages;
    }

    public void setSubtitleLanguages(ArrayList<String> subtitleLanguages) {
        this.subtitleLanguages = subtitleLanguages;
    }
}
