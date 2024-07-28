package lms;

public class Books extends Item {
    private boolean isBestSeller;
    public Books(String title, int checkOutLength, double bookPrice, boolean canBeCheckedOut, int copyNumber, boolean isBestSeller) {
        super(title, checkOutLength, bookPrice, canBeCheckedOut, copyNumber);
        if (isBestSeller) {
        	checkOutLength = 14;
        }
        else 
        {
        	checkOutLength = 21;
        }
        this.isBestSeller = isBestSeller;
    }
    public boolean getIsBestSeller() {
    	return isBestSeller;
    }
    public void setIsBestSeller(boolean isBestSeller) {
        this.isBestSeller = isBestSeller;
    }
    public String toString() {
        return ("Title: " + getTitle() + "\n" +
                "Checkout Length: " + getCheckOutLength() + "\n" +
                "Price: " + getBookPrice() + "\n" +
                "Can be checked out: " + canBeCheckedOut() + "\n" +
                "Number of Copies: " + getCopyNumber() + "\n" +
                "Best seller: " + isBestSeller);
    }
}
    

  		 
