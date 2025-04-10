import java.util.*;

enum OrderStatus {
    ACCEPTED,
    COMPLETED
}

class MenuItem {
    String name;
    int price;

    MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    String name;
    int quantity;

    OrderItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

class Order {
    static int idCounter = 1;
    int id;
    String user;
    List<OrderItem> items;
    OrderStatus status;
    Restaurant assignedRestaurant;

    Order(String user, List<OrderItem> items) {
        this.id = idCounter++;
        this.user = user;
        this.items = items;
        this.status = OrderStatus.ACCEPTED;
    }
}

class Restaurant {
    String name;
    double rating;
    int maxOrders;
    int currentOrders;
    Map<String, MenuItem> menu;

    Restaurant(String name, double rating, int maxOrders) {
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
        this.currentOrders = 0;
        this.menu = new HashMap<>();
    }

    void addMenuItem(String name, int price) {
        if (!menu.containsKey(name)) {
            menu.put(name, new MenuItem(name, price));
            System.out.println("[INFO] Added menu item to " + this.name + ": " + name + " - INR " + price);
        }
    }

    void updateMenuItem(String name, int price) {
        if (menu.containsKey(name)) {
            menu.put(name, new MenuItem(name, price));
            System.out.println("[INFO] Updated menu item in " + this.name + ": " + name + " - INR " + price);
        }
    }

    boolean canFulfillOrder(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            if (!menu.containsKey(item.name)) {
                return false;
            }
        }
        return currentOrders < maxOrders;
    }

    int calculateTotalCost(List<OrderItem> orderItems) {
        int total = 0;
        for (OrderItem item : orderItems) {
            total += menu.get(item.name).price * item.quantity;
        }
        return total;
    }

    void acceptOrder() {
        currentOrders++;
    }

    void completeOrder() {
        currentOrders--;
    }
}

interface SelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderItem> orderItems);
}

class LowestCostStrategy implements SelectionStrategy {
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderItem> orderItems) {
        Restaurant selected = null;
        int lowestCost = Integer.MAX_VALUE;
        for (Restaurant r : restaurants) {
            if (r.canFulfillOrder(orderItems)) {
                int cost = r.calculateTotalCost(orderItems);
                if (cost < lowestCost) {
                    lowestCost = cost;
                    selected = r;
                }
            }
        }
        return selected;
    }
}

class HighestRatingStrategy implements SelectionStrategy {
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderItem> orderItems) {
        Restaurant selected = null;
        double highestRating = -1;
        for (Restaurant r : restaurants) {
            if (r.canFulfillOrder(orderItems)) {
                if (r.rating > highestRating) {
                    highestRating = r.rating;
                    selected = r;
                }
            }
        }
        return selected;
    }
}

class FoodOrderingSystem {
    List<Restaurant> restaurants = new ArrayList<>();
    Map<Integer, Order> orders = new HashMap<>();

    void onboardRestaurant(String name, double rating, int maxOrders) {
        Restaurant r = new Restaurant(name, rating, maxOrders);
        restaurants.add(r);
        System.out.println("[INFO] Onboarded Restaurant: " + name + " | Rating: " + rating + " | Max Orders: " + maxOrders);
    }

    void addMenuItem(String restaurantName, String itemName, int price) {
        for (Restaurant r : restaurants) {
            if (r.name.equals(restaurantName)) {
                r.addMenuItem(itemName, price);
            }
        }
    }

    void updateMenuItem(String restaurantName, String itemName, int price) {
        for (Restaurant r : restaurants) {
            if (r.name.equals(restaurantName)) {
                r.updateMenuItem(itemName, price);
            }
        }
    }

    void placeOrder(String user, List<OrderItem> items, SelectionStrategy strategy) {
        System.out.println("\n[ORDER PLACED] User: " + user);
        for (OrderItem item : items) {
            System.out.println(" - " + item.name + " x " + item.quantity);
        }

        Restaurant selected = strategy.selectRestaurant(restaurants, items);
        if (selected == null) {
            System.out.println("[FAILED] No restaurant can fulfill the order");
            return;
        }

        Order order = new Order(user, items);
        order.assignedRestaurant = selected;
        selected.acceptOrder();
        orders.put(order.id, order);
        System.out.println("[SUCCESS] Order ID: " + order.id + " assigned to Restaurant: " + selected.name);
    }

    void completeOrder(int orderId) {
        if (orders.containsKey(orderId)) {
            Order order = orders.get(orderId);
            if (order.status == OrderStatus.ACCEPTED) {
                order.status = OrderStatus.COMPLETED;
                order.assignedRestaurant.completeOrder();
                System.out.println("[INFO] Order ID: " + orderId + " completed by Restaurant: " + order.assignedRestaurant.name);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FoodOrderingSystem system = new FoodOrderingSystem();

        // Onboard Restaurants
        system.onboardRestaurant("R1", 4.5, 5);
        system.addMenuItem("R1", "Veg Biryani", 100);
        system.addMenuItem("R1", "Chicken Biryani", 150);

        system.onboardRestaurant("R2", 4.0, 5);
        system.addMenuItem("R2", "Idli", 10);
        system.addMenuItem("R2", "Dosa", 50);
        system.addMenuItem("R2", "Veg Biryani", 80);
        system.addMenuItem("R2", "Chicken Biryani", 175);

        system.onboardRestaurant("R3", 4.9, 1);
        system.addMenuItem("R3", "Idli", 15);
        system.addMenuItem("R3", "Dosa", 30);
        system.addMenuItem("R3", "Gobi Manchurian", 150);
        system.addMenuItem("R3", "Chicken Biryani", 175);

        // Update Menu
        system.addMenuItem("R1", "Chicken65", 250);
        system.updateMenuItem("R2", "Chicken Biryani", 150);

        // Place Orders
        List<OrderItem> order1 = Arrays.asList(new OrderItem("Idli", 3), new OrderItem("Dosa", 1));
        system.placeOrder("Ashwin", order1, new LowestCostStrategy());

        List<OrderItem> order2 = Arrays.asList(new OrderItem("Idli", 3), new OrderItem("Dosa", 1));
        system.placeOrder("Harish", order2, new LowestCostStrategy());

        List<OrderItem> order3 = Arrays.asList(new OrderItem("Veg Biryani", 3), new OrderItem("Dosa", 1));
        system.placeOrder("Shruthi", order3, new HighestRatingStrategy());

        // Complete an order
        system.completeOrder(1);

        List<OrderItem> order4 = Arrays.asList(new OrderItem("Idli", 3), new OrderItem("Dosa", 1));
        system.placeOrder("Harish", order4, new LowestCostStrategy());

        List<OrderItem> order5 = Arrays.asList(new OrderItem("Idli", 3), new OrderItem("Paneer Tikka", 1));
        system.placeOrder("Diya", order5, new LowestCostStrategy());
    }
}