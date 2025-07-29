package org.practice.lld.foodordersystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private List<Order> dispatchedOrders; // List to keep track of dispatched orders
    private List<Order> orders; // List to keep track of all orders
    private RestaurantService restaurantService;
    private RestaurantStrategy restaurantStrategy;

    // Constructor to initialize the restaurant service

    public OrderServiceImpl(RestaurantService restaurantService, RestaurantStrategy restaurantSelectionStrategy) {
        this.restaurantService = restaurantService;
        this.restaurantStrategy = restaurantSelectionStrategy;
        this.orders = new ArrayList<>();
        this.dispatchedOrders = new ArrayList<>();
    }


    @Override
    public Order createAndPlaceOrder(List<String> menuItemList, Integer orderId) {
        if (menuItemList == null || menuItemList.isEmpty()) {
            throw new IllegalArgumentException("Menu item list cannot be null or empty");
        }
        Restaurant restaurant = restaurantStrategy.findRestaurant(restaurantService.getAllRestaurants(),menuItemList);
        if (restaurant == null) {
            throw new IllegalStateException("No restaurant available to place the order");
        }
        // Create a new order with the generated ID and the provided menu items

        Map<String, MenuItem> menuItemsInRestaurant = restaurant.getMenuItems();
        // create menuItemsList to be ordered from restaurant's menu
        // it should be a map of MenuItem and quantity
        Map<MenuItem, Integer> menuItemListToBeOrdered = new HashMap<>();
        for (String itemName : menuItemList) {
            MenuItem menuItem = menuItemsInRestaurant.get(itemName);
            if (menuItem == null) {
                throw new IllegalArgumentException("Menu item " + itemName + " not found in restaurant " + restaurant.getName());
            }
            if(menuItemListToBeOrdered.containsKey(menuItem)) {
                // If the item is already in the order, increment the quantity
                menuItemListToBeOrdered.put(menuItem, menuItemListToBeOrdered.get(menuItem) + 1);
            } else {
                // If the item is not in the order, add it with quantity 1
                menuItemListToBeOrdered.put(menuItem, 1);
            }
        }

        Order order = new Order(orderId,restaurant.getName(), restaurant.getId());
        order.setItemsList(menuItemListToBeOrdered);
        order.setStatus(OrderStatus.ACCEPTED);
        // Calculate the total price of the order
        double totalPrice = menuItemListToBeOrdered.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        order.setTotalPrice(totalPrice);
        // Update the restaurant's order list
        restaurant.addOrder(order);
        // Update the restaurant's processing capacity
        restaurantService.updateProcessingCapacity(restaurant.getId(), restaurant.getProcessingCapacity() - menuItemList.size());
        // Add the order to the orders list
        orders.add(order);
        return order;
    }

    @Override
    public Order dispatchOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        // Check if the order is already dispatched
        if (order.getStatus() == OrderStatus.DISPATCHED) {
            throw new IllegalStateException("Order is already dispatched");
        }
        // Update the order status to DISPATCHED
        order.setStatus(OrderStatus.DISPATCHED);
        int processingCapacityConsumed =  order.getItemsList().entrySet().stream().mapToInt(entry-> entry.getValue()).sum();
        int restaurantId = order.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        System.out.println(restaurant.getProcessingCapacity());
        System.out.println(processingCapacityConsumed);

        restaurantService.updateProcessingCapacity(restaurantId,restaurant.getProcessingCapacity() + processingCapacityConsumed);
        // Add the dispatched order to the list
        dispatchedOrders.add(order);
        // Return the updated order
        return order;
    }


    @Override
    public List<Order> showDispatchedOrders() {
        if (dispatchedOrders == null || dispatchedOrders.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no orders are dispatched
        }
        // Return a copy of the dispatched orders list to avoid external modification
        return dispatchedOrders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DISPATCHED)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrder(int orderId) {
        if (orders == null || orders.isEmpty()) {
            return null; // Return null if no orders exist
        }
        // Find the order with the given ID
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order; // Return the order if found
            }
        }
        return null;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // get Restaurant service
    public RestaurantService getRestaurantService() {
        return restaurantService;
    }
    public void setRestaurantStrategy(RestaurantStrategy restaurantStrategy) {
        this.restaurantStrategy = restaurantStrategy;
    }

    public List<Order> getDispatchedOrders() {
        return dispatchedOrders;
    }
    public void setDispatchedOrders(List<Order> dispatchedOrders) {
        this.dispatchedOrders = dispatchedOrders;}
}