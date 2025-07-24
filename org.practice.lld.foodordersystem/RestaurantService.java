package org.practice.lld.foodordersystem;

import java.util.List;

public interface RestaurantService {

    boolean addRestaurant(Restaurant restaurant, List<MenuItem> menuItems, int processingCapacity);
    boolean updateRestaurantMenuItem(int restaurantId, String updateMenuItemName, int price, boolean isAvailable);

    boolean updateProcessingCapacity(int restaurantId, int newCapacity);

    List<Restaurant> getAllRestaurants();
}
