package lms;

public class AudioVisual extends Item {
    private String format;

    public AudioVisual() {
        super.setCheckoutLength(14); // default checkout length is 2 weeks
    }

    // Getters and Setters
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
