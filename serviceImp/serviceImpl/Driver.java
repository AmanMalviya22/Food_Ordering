package serviceImp.serviceImpl;

import constant.Constant;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // Onboard Restaurants
        System.out.println("=== Onboarding Restaurants ===");
        Map<String, Integer> menuR1 = new HashMap<>() {{
            put("Veg Biryani", 100);
            put("Chicken Biryani", 150);
        }};
        userService.onboardRestaurant("R1", 5, 4.5, menuR1);

        Map<String, Integer> menuR2 = new HashMap<>() {{
            put("Idli", 10);
            put("Dosa", 50);
            put("Veg Biryani", 80);
            put("Chicken Biryani", 175);
        }};
        userService.onboardRestaurant("R2", 5, 4.0, menuR2);

        Map<String, Integer> menuR3 = new HashMap<>() {{
            put("Idli", 15);
            put("Dosa", 30);
            put("Gobi Manchurian", 150);
            put("Chicken Biryani", 175);
        }};
        userService.onboardRestaurant("R3", 1, 4.9, menuR3);

        // Update Menus
        System.out.println("\n=== Updating Menus ===");
        userService.updateRestaurantMenu("R1", Map.of("Chicken65", 250));
        userService.updateRestaurantMenu("R2", Map.of("Chicken Biryani", 150));

        // Place Orders
        System.out.println("\n=== Placing Orders ===");
        userService.placeOrder("Ashwin", Map.of("Idli", 3, "Dosa", 1), Constant.LOWEST_COST);
        userService.placeOrder("Harish", Map.of("Idli", 3, "Dosa", 1), Constant.LOWEST_COST);
        userService.placeOrder("Shruthi", Map.of("Veg Biryani", 3, "Dosa", 1), Constant.HIGHEST_RATING);

        // Complete Order
        System.out.println("\n=== Completing Order ===");
        userService.markOrderComplete(1);

        // More Orders
        System.out.println("\n=== Placing More Orders ===");
        userService.placeOrder("Harish", Map.of("Idli", 3, "Dosa", 1), Constant.LOWEST_COST);
        userService.placeOrder("Diya", Map.of("Idli", 3, "Paneer Tikka", 1), Constant.LOWEST_COST);
    }
}