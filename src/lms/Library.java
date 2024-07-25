package lms;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Item> items = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // Getters and Setters
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}
