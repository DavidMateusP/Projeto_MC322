package products;
public class Book extends Item {
    private int pages;

    public Book(int quantity, String name, int releaseYear, int recommendedAge, double price, int pages) {
        super(quantity, name, releaseYear, recommendedAge, price);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    
}
