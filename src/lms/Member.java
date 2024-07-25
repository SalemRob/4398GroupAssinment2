package lms;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<Item> checkedOutItems;

    public Member() {
        this.checkedOutItems = new ArrayList<>();
    }

    // Getters and Setters
    public List<Item> getCheckedOutItems() { return checkedOutItems; }

    public boolean checkoutItem(Item item) {
        int maxItems = this.getAge() <= 12 ? 5 : Integer.MAX_VALUE;
        if (checkedOutItems.size() < maxItems) {
            checkedOutItems.add(item);
            item.setStatus("Checked Out");
            return true;
        } else {
            System.out.println("Cannot checkout more than " + maxItems + " items.");
            return false;
        }
    }

    public boolean returnItem(Item item) {
        if (checkedOutItems.remove(item)) {
            item.setStatus("Available");
            return true;
        }
        return false;
    }

    public boolean renewItem(Item item) {
        if (checkedOutItems.contains(item) && !item.isRenewed()) {
            item.setRenewed(true);
            return true;
        }
        return false;
    }
}
