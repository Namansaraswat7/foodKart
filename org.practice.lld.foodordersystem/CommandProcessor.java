package org.practice.lld.foodordersystem;

import java.util.*;

public class CommandProcessor {
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    public CommandProcessor(RestaurantService restaurantService, OrderService orderManager) {
        this.restaurantService = restaurantService;
        this.orderService = orderManager;
    }

    public void processInputCommands(List<String> inputCommands) {
        // Validate input
        if (inputCommands == null || inputCommands.isEmpty()) {
            System.out.println("No commands to process.");
            return;
        }
        List<CommandWithTimeStamp> commands = new ArrayList<>();


        // Parse and store commands
        for (String command : inputCommands) {
            String[] parts = command.split(",\\s*");
            int timestamp = Integer.parseInt(parts[0]);
            commands.add(new CommandWithTimeStamp(timestamp, command));
        }


        // Sort commands by timestamp
        commands.sort(Comparator.comparingInt(CommandWithTimeStamp::getTimestamp));


        // Process sorted commands
        for (CommandWithTimeStamp commandWithTimestamp : commands) {
            String[] parts = commandWithTimestamp.getCommand().split(",\\s*");
            String commandType = parts[1];

            switch (commandType) {
                case "place-order":
                    Integer orderId = Integer.parseInt(parts[2].replace("order", ""));
                    List<String> items = Arrays.asList(parts).subList(3, parts.length);
                    // Logic to place order
                    Order order = orderService.createAndPlaceOrder(items,orderId);
                    if (order == null) {
                        System.out.println("Failed to place order: " + orderId);
                        break;
                    }
                    System.out.println("Placing order: " + orderId + " with items: " + items);
                    break;

                case "update-price":
                    int restaurantId = Integer.parseInt(parts[2].replace("restaurant", ""));
                    String itemName = parts[3];
                    int newPrice = Integer.parseInt(parts[4]);
                    restaurantService.updateRestaurantMenuItem(restaurantId, itemName, newPrice, true);
                    System.out.println("Updated price for " + itemName + " in restaurant " + restaurantId);
                    break;

                case "dispatch-order":
                    Integer dispatchOrderId = Integer.parseInt(parts[2].replace("order", ""));
                    Order orderToBeDispatched = orderService.getOrder(dispatchOrderId);
                    if (orderToBeDispatched == null) {
                        System.out.println("Order with ID " + dispatchOrderId + " not found.");
                        break;
                    }
                    if (orderToBeDispatched.getStatus() == OrderStatus.DISPATCHED) {
                        System.out.println("Order with ID " + dispatchOrderId + " is already dispatched.");
                        break;
                    }

                    orderService.dispatchOrder(orderToBeDispatched);
                    System.out.println("Dispatching order: " + dispatchOrderId);
                    break;

                default:
                    System.out.println("Unknown command: " + commandType);
            }
        }

}

}
