package model;

import java.util.Map;

public class Order {
    private static int counter = 0;
    private int orderId;
    private String userName;
    private Map<String, Integer> items;
    private String assignedRestaurant;
    private boolean completed;

    public Order(String userName, Map<String, Integer> items) {
        this.orderId = ++counter;
        this.userName = userName;
        this.items = items;
        this.completed = false;
        System.out.println("Order created: ID = " + orderId + ", User = " + userName + ", Items = " + items);
    }

    public int getOrderId() { return orderId; }
    public Map<String, Integer> getItems() { return items; }
    public void assignRestaurant(String name) { this.assignedRestaurant = name; }
    public String getAssignedRestaurant() { return assignedRestaurant; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }
}