package org.practice.lld.foodordersystem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private int processingCapacity;
    private final Map<String, MenuItem> menuItems;

    List<Order> ordersCompleted;


    public Restaurant(int id, String name, String address, int processingCapacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.processingCapacity = processingCapacity;
        this.menuItems = new HashMap<>();
        this.ordersCompleted = new ArrayList<>();// Initialize the menu items map
    }
    public void addMenuItem(MenuItem item) {
        menuItems.put(item.getName(), item);
    }
    public void removeMenuItem(String itemName) {
        menuItems.remove(itemName);
    }
    public MenuItem getMenuItem(String itemName) {
        return menuItems.get(itemName);
    }
    public Map<String, MenuItem> getMenuItems() {
        return menuItems;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getProcessingCapacity() {
        return processingCapacity;
    }

    public void setProcessingCapacity(int processingCapacity) {
        this.processingCapacity = processingCapacity;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrdersCompleted() {
        return ordersCompleted;
    }
    public void addOrder(Order order) {
        if (ordersCompleted.size() < processingCapacity) {
            ordersCompleted.add(order);
        } else {
            throw new IllegalStateException("Processing capacity exceeded for restaurant: " + name);
        }
    }

}