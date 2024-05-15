package products;

import java.util.ArrayList;

public class Album extends Item {
    private ArrayList<String> tracks;

    // Constructor method
    public Album(int quantity, String name, int releaseYear, int recommendedAge, int price) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.tracks = new ArrayList<>();
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<String> tracks) {
        this.tracks = tracks;
    }

}