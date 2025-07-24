package org.practice.lld.foodordersystem;

import java.util.List;

public interface OrderService {
    Order createAndPlaceOrder(List<String> menuItemList, Integer orderId);

    Order dispatchOrder(Order order);

    List<Order> showDispatchedOrders();

    Order getOrder(int orderId);

}
