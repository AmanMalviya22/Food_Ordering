package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private String name;
    private double rating;
    private int maxOrders;
    private AtomicInteger activeOrders;
    private final Map<String, Integer> menu;

    public Restaurant(String name, double rating, int maxOrders) {
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
        this.activeOrders = new AtomicInteger(0);
        this.menu = new HashMap<>();
    }

    public void addOrUpdateMenuItem(String item, int price) {
        menu.put(item, price);
        System.out.println("Menu item '" + item + "' added/updated with price INR " + price + " in restaurant " + name);
    }

    public boolean canFulfillOrder(Map<String, Integer> items) {
        for (String item : items.keySet()) {
            if (!menu.containsKey(item)) return false;
        }
        return activeOrders.get() < maxOrders;
    }

    public int calculateTotal(Map<String, Integer> items) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            total += menu.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    public void acceptOrder() {
        activeOrders.incrementAndGet();
    }

    public void completeOrder() {
        activeOrders.decrementAndGet();
    }

    public String getName() { return name; }
    public double getRating() { return rating; }
    public Map<String, Integer> getMenu() { return menu; }
}