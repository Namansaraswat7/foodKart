package org.practice.lld.foodordersystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantServiceImpl implements RestaurantService{

    private Map<Integer, Restaurant> restaurantMap = new HashMap<>();

    @Override
    public boolean addRestaurant(Restaurant restaurant, List<MenuItem> menuItems, int processingCapacity) {
        if (restaurant == null || menuItems == null || menuItems.isEmpty() || processingCapacity <= 0) {
            return false; // Invalid input
        }

        if (restaurantMap.containsKey(restaurant.getId())) {
            return false; // Restaurant already exists
        }
        restaurant.setProcessingCapacity(processingCapacity);
        restaurantMap.put(restaurant.getId(), restaurant);
        for (MenuItem item : menuItems) {
            if (item != null && item.getName() != null && !item.getName().isEmpty()) {
                restaurant.addMenuItem(item); // Add each menu item to the restaurant
            } else {
                return false; // Invalid menu item
            }
        }
        return true;
    }

    @Override
    public boolean updateRestaurantMenuItem(int restaurantId, String updateMenuItemName, int price, boolean isAvailable) {
        if (updateMenuItemName == null || updateMenuItemName.isEmpty() || price <= 0) {
            return false; // Invalid input
        }
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant == null) {
            return false; // Restaurant not found
        }
        MenuItem menuItem = restaurant.getMenuItem(updateMenuItemName);
        if (menuItem == null) {
            return false; // Menu item not found
        }
        menuItem.setPrice(price); // Update the price of the menu item
        menuItem.setAvailable(isAvailable); // Update the availability status
        restaurant.addMenuItem(menuItem); // Re-add the updated menu item to the restaurant
        return true; 
    }

    @Override
    public boolean updateProcessingCapacity(int restaurantId, int newCapacity) {
    if (newCapacity <= 0) {
        return false; // Invalid capacity
    }
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant != null) {
            restaurant.setProcessingCapacity(newCapacity); 
            return true; 
    }
        return false;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        if (restaurantMap.isEmpty()) {
            return null; // No restaurants available
        }
        return restaurantMap.values().stream().toList();
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        if (restaurantMap.isEmpty()) {
            return null; // No restaurants available
        }

        Restaurant restaurant = restaurantMap.get(restaurantId);

        return restaurant;
    }
}
