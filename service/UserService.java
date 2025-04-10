package service;

import java.util.Map;

public interface UserService {
    void onboardRestaurant(String name, int maxOrders, double rating, Map<String, Integer> menu);
    void updateRestaurantMenu(String name, Map<String, Integer> menu);
    void markOrderComplete(int orderId);
    void placeOrder(String userName, Map<String, Integer> items, String strategy);
}