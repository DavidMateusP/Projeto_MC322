package products;

public class BoardGame extends Item {
    private int minPlayers;
    private int maxPlayers;

    public BoardGame(int quantity, String name, int releaseYear, int recommendedAge, double price, int minPlayers,
            int maxPlayers) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

}
