package org.practice.lld.foodordersystem;

import java.util.List;
import java.util.Map;

public class ProcessingCapacityRestaurantStrategy implements RestaurantStrategy{
    @Override
    public Restaurant findRestaurant(List<Restaurant> availableRestaurants, List<String> menuItemList) {
        if (availableRestaurants == null || availableRestaurants.isEmpty()) {
            return null;
        }
        Restaurant selectedRestaurant = null;
        int maxCapacity = menuItemList.size();
        for (Restaurant restaurant : availableRestaurants) {
            if (restaurant.getProcessingCapacity() > maxCapacity) {
                maxCapacity = restaurant.getProcessingCapacity();
                // Check if the restaurant has all the required menu items
                boolean hasAllItems = true;
                for (String item : menuItemList) {
                    if (!restaurant.getMenuItems().containsKey(item)) {
                        hasAllItems = false;
                        break;
                    }
                    if(hasAllItems) selectedRestaurant = restaurant;
                }
            }
        }
        return selectedRestaurant;
    }
}