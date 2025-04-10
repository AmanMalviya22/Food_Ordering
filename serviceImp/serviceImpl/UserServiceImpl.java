package serviceImp.serviceImpl;

import constant.Constant;
import model.Order;
import model.Restaurant;
import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Override
    public void onboardRestaurant(String name, int maxOrders, double rating, Map<String, Integer> menu) {
        Restaurant restaurant = new Restaurant(name, rating, maxOrders);
        menu.forEach(restaurant::addOrUpdateMenuItem);
        UserRepository.restaurants.put(name, restaurant);
        System.out.println("Restaurant onboarded: " + name);
    }

    @Override
    public void updateRestaurantMenu(String name, Map<String, Integer> menu) {
        Restaurant restaurant = UserRepository.restaurants.get(name);
        if (restaurant != null) {
            menu.forEach(restaurant::addOrUpdateMenuItem);
        }
    }

    @Override
    public void markOrderComplete(int orderId) {
        Order order = UserRepository.orders.get(orderId);
        if (order != null && !order.isCompleted()) {
            Restaurant restaurant = UserRepository.restaurants.get(order.getAssignedRestaurant());
            if (restaurant != null) {
                restaurant.completeOrder();
                order.markCompleted();
                System.out.println("Order " + orderId + " marked as COMPLETED by " + restaurant.getName());
            }
        } else {
            System.out.println("Invalid order completion attempt.");
        }
    }

    @Override
    public void placeOrder(String userName, Map<String, Integer> items, String strategy) {
        System.out.println("Placing order for user: " + userName + ", strategy: " + strategy);

        if (!UserRepository.users.containsKey(userName)) {
            UserRepository.users.put(userName, new User(userName));
            System.out.println("New user created: " + userName);
        }

        Order order = new Order(userName, items);
        List<Restaurant> eligibleRestaurants = UserRepository.restaurants.values().stream()
                .filter(r -> r.canFulfillOrder(items))
                .collect(Collectors.toList());

        if (eligibleRestaurants.isEmpty()) {
            System.out.println("Cannot assign the order. No restaurant can fulfill the items.");
            return;
        }

        Restaurant selected = null;

        if (strategy.equals(Constant.LOWEST_COST)) {
            selected = eligibleRestaurants.stream()
                    .min(Comparator.comparingInt(r -> r.calculateTotal(items)))
                    .orElse(null);
        } else if (strategy.equals(Constant.HIGHEST_RATING)) {
            selected = eligibleRestaurants.stream()
                    .max(Comparator.comparingDouble(Restaurant::getRating))
                    .orElse(null);
        }

        if (selected != null) {
            selected.acceptOrder();
            order.assignRestaurant(selected.getName());
            UserRepository.orders.put(order.getOrderId(), order);
            System.out.println("Order assigned to " + selected.getName());
        }
    }
}