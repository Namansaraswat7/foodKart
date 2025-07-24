package org.practice.lld.foodordersystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int id;
    private String restaurantName;
    private int restaurantId;
    private OrderStatus status;
    private Map<MenuItem, Integer> itemsList; // Map of MenuItem and their quantities
    private double totalPrice = 0;

    public Order(int id,  String restaurantName, int restaurantId) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.status = OrderStatus.PENDING; // Default status
        this.totalPrice = 0.0; // Default price
        this.itemsList = new HashMap<>();
    }

    // Getters and setters can be added here as needed
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    // getter and setter for itemsList
    public Map<MenuItem, Integer> getItemsList() {
        return itemsList;
    }

    public void setItemsList(Map<MenuItem, Integer> itemsList) {
        this.itemsList = itemsList;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
