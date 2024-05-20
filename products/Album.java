package products;

import java.util.ArrayList;

import client.Rating;

public class Album extends Item {
    private ArrayList<Track> tracks;

    // Constructor method
    public Album(int quantity, String name, int releaseYear, int recommendedAge, double price) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.tracks = new ArrayList<>();
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public void addRating(ArrayList<Rating> ratings) {
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.get(i).addRating(ratings.get(i));
        }
        this.addRating(new Rating(Rating.averageRating(ratings)));

    }

}