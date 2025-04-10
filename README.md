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
