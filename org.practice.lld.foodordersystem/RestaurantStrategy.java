package org.practice.lld.foodordersystem;

import java.util.List;
import java.util.Map;

public interface RestaurantStrategy {
    Restaurant findRestaurant(List<Restaurant> availableRestaurant, List<String> menuItemList);
}