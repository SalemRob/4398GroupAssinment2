package lms;

public abstract class Item {
    private String title;
    private int checkOutLength;
    private double bookPrice;
    private boolean hasOutstandingRequest;
    private boolean canBeCheckedOut;
    private int copyNumber;
    private double audioVisualPrice;
    public Item(String title, int checkOutLength, double bookPrice,double audioVisualPrice, boolean canBeCheckedOut, int copyNumber) {
    	this.title = title;
    	this.checkOutLength = checkOutLength;
    	this.bookPrice = bookPrice;
    	this.canBeCheckedOut = canBeCheckedOut;
    	this.copyNumber = copyNumber;
    	this.hasOutstandingRequest = false;
    	this.audioVisualPrice = audioVisualPrice;
    }
    public String getTitle() {
    	return title;
    }
    public void settitle(String title) {
    	this.title = title;
    	
    }
    public int getCheckOutLength() {
        return checkOutLength;
    }

    public void setCheckOutLength(int checkOutLength) {
        this.checkOutLength = checkOutLength;
    }
    public double getBookPrice() {
    	return bookPrice;
    }
    public void setBookPrice(double bookPrice) {
    	this.bookPrice = bookPrice;
    }
    public double getAudioVisualPrice() {
    	return audioVisualPrice;
    }
    public void setAudioVisualPrice(double  audioVisualPrice) {
    	this. audioVisualPrice =  audioVisualPrice;
    }
    public void checkOut() {
    	copyNumber --;
    }
    public void checkIn() {
    	copyNumber++;
    }
    public boolean hasOutstandingRequest() {
    	return hasOutstandingRequest;
    }
    public void setHasOutstandingRequest(boolean hasOutstandingRequest) {
        this.hasOutstandingRequest = hasOutstandingRequest;
    }

    public boolean canBeCheckedOut() {
        return canBeCheckedOut;
    }

    public void setcanBeCheckedOut(boolean canBeCheckedOut) {
        this.canBeCheckedOut = canBeCheckedOut;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public String toString() {
        return ("Title: " + getTitle() + "\n" +
                "Checkout Length:" + getCheckOutLength() + "\n" +
                "Price: " + getBookPrice() + "\n" +
                Price: " + getAudioVisualrice() + "\n" +
                "Can be checked out: " + canBeCheckedOut() + "\n" +
                "Number of Copies: " + getCopyNumber());
    }

}

