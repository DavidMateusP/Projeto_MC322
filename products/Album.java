package products;

import java.util.ArrayList;

public class Album extends Item {
    private ArrayList<String> tracks;

    // Constructor method
    public Album(int quantity, String name, int releaseYear, int recommendedAge, double price) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.tracks = new ArrayList<>();
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void addTrack(String track) {
        this.tracks.add(track);
    }

}