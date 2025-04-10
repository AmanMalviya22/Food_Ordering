# 🍽️ Food Ordering System (Java)

A simple Java-based simulation of a Food Ordering System that enables onboarding restaurants, adding menus, placing orders, and selecting restaurants based on customizable strategies.

---

## 🚀 Features

- ✅ Onboard new restaurants with rating and max concurrent orders
- ✅ Add or update menu items for restaurants
- ✅ Place orders using:
  - Lowest cost strategy
  - Highest rating strategy
- ✅ Track order status (Accepted, Completed)
- ✅ Automatically manage restaurant load

---

## 🧱 Class Overview

| Class                  | Description                                                              |
|------------------------|--------------------------------------------------------------------------|
| `MenuItem`             | Represents an item with name and price                                   |
| `OrderItem`            | Represents an item in a customer's order                                 |
| `Order`                | Represents an order with status and assigned restaurant                  |
| `Restaurant`           | Holds menu, rating, max and current order capacity                       |
| `LowestCostStrategy`   | Chooses the restaurant with the cheapest price for the order             |
| `HighestRatingStrategy`| Chooses the highest-rated restaurant that can fulfill the order          |
| `FoodOrderingSystem`   | Main system managing restaurants, menu items, and customer orders        |

---

## 🛠️ Usage

### ▶️ Run Locally

1. Clone the repository:
```bash
git clone https://github.com/<your-username>/food-ordering-system-java.git
cd food-ordering-system-java





### Demo

# 🍽️ Food Ordering System

This is a basic in-memory restaurant onboarding and food ordering system. It simulates onboarding restaurants, adding menu items, placing and fulfilling orders, and choosing the most suitable restaurant based on ratings and availability.

---

## ✅ Features

- Onboard restaurants with rating and order capacity
- Add or update menu items for restaurants
- Place orders by matching restaurant menus
- Assign order to the highest-rated available restaurant
- Mark orders as completed to free restaurant capacity
- Reject orders if no restaurant can fulfill them

---

## 📦 Sample Workflow (with Logs)

```bash
[INFO] Onboarded Restaurant: R1 | Rating: 4.5 | Max Orders: 5
[INFO] Added menu item to R1: Veg Biryani - INR 100
[INFO] Added menu item to R1: Chicken Biryani - INR 150

[INFO] Onboarded Restaurant: R2 | Rating: 4.0 | Max Orders: 5
[INFO] Added menu item to R2: Idli - INR 10
[INFO] Added menu item to R2: Dosa - INR 50
[INFO] Added menu item to R2: Veg Biryani - INR 80
[INFO] Added menu item to R2: Chicken Biryani - INR 175

[INFO] Onboarded Restaurant: R3 | Rating: 4.9 | Max Orders: 1
[INFO] Added menu item to R3: Idli - INR 15
[INFO] Added menu item to R3: Dosa - INR 30
[INFO] Added menu item to R3: Gobi Manchurian - INR 150
[INFO] Added menu item to R3: Chicken Biryani - INR 175

[INFO] Added menu item to R1: Chicken65 - INR 250
[INFO] Updated menu item in R2: Chicken Biryani - INR 150

## sample orders

[ORDER PLACED] User: Ashwin
 - Idli x 3
 - Dosa x 1
[SUCCESS] Order ID: 1 assigned to Restaurant: R3

[ORDER PLACED] User: Harish
 - Idli x 3
 - Dosa x 1
[SUCCESS] Order ID: 2 assigned to Restaurant: R2

[ORDER PLACED] User: Shruthi
 - Veg Biryani x 3
 - Dosa x 1
[SUCCESS] Order ID: 3 assigned to Restaurant: R2

[INFO] Order ID: 1 completed by Restaurant: R3

[ORDER PLACED] User: Harish
 - Idli x 3
 - Dosa x 1
[SUCCESS] Order ID: 4 assigned to Restaurant: R3

[ORDER PLACED] User: Diya
 - Idli x 3
 - Paneer Tikka x 1
[FAILED] No restaurant can fulfill the order

