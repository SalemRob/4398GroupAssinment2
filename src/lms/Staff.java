package lms;

public class Staff extends User {
    // Methods
    public void addItem(Item item, Library library) {
        library.getItems().add(item);
    }

    public void updateItem(Item item) {
        // Update item logic
    }

    public void calcFine(User user, String itemID, java.util.Date returnDate) {
        // Calculate fine logic
        for (Loans loan : user.getLoans()) {
            if (loan.getItemID().equals(itemID)) {
                long overdueDays = (returnDate.getTime() - loan.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
                if (overdueDays > 0) {
                    double fineAmount = Math.min(overdueDays * 0.10, loan.getItemPrice());
                    Fines fine = new Fines();
                    fine.setUserID(user.getUserID());
                    fine.setItemID(itemID);
                    fine.setAmount(fineAmount);
                    fine.setDateIssued(new java.util.Date());
                    user.getFines().add(fine);
                }
                break;
            }
        }
    }
}
