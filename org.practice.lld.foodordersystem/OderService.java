package org.practice.lld.foodordersystem;

import java.util.List;

public interface OrderService {
    Order createAndPlaceOrder(List<String> menuItemList, Integer orderId);

    Order dispatchOrder(Order order);
    /**
     * Retrieves a list of all dispatched orders.
     *
     * @return List of dispatched orders.
     */
    List<Order> showDispatchedOrders();

    Order getOrder(int orderId);

}
    