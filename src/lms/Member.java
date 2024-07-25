package lms;

public class Member extends User {
    private int age;

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // Override checkoutItem method to enforce child item limit
    @Override
    public void checkoutItem(Item item) {
        if (this.age <= 12 && this.getLoans().size() >= 5) {
            System.out.println("Children can only check out up to 5 items at a time.");
        } else {
            super.checkoutItem(item);
        }
    }
}
