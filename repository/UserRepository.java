package repository;

import model.Order;
import model.Restaurant;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    public static Map<String, User> users = new HashMap<>();
    public static Map<String, Restaurant> restaurants = new HashMap<>();
    public static Map<Integer, Order> orders = new HashMap<>();
}