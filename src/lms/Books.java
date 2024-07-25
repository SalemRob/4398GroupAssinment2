package lms;

public class Books extends Item {
    private String author;
    private String isbn;
    private boolean bestSeller;

    public Books() {
        super.setCheckoutLength(21); // default checkout length is 3 weeks
    }

    // Getters and Setters
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isBestSeller() { return bestSeller; }
    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
        if (bestSeller) {
            super.setCheckoutLength(14); // best sellers have 2-week checkout
        }
    }
}
