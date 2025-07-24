package org.practice.lld.foodordersystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodKartApplication {

    public static void main(String[] args) {

        RestaurantService restaurantService = new RestaurantServiceImpl();

        Restaurant restaurant1 = new Restaurant(1, "Haldiram", "location1", 100);
        Restaurant restaurant2 = new Restaurant(2, "Bikanervala", "location2", 200);
        Restaurant restaurant3 = new Restaurant(3, "Mcdonald", "location3", 300);

        List<MenuItem> itemList1 = new ArrayList<>();
        List<MenuItem> itemList2 = new ArrayList<>();

        MenuItem item1 = new MenuItem(1, "Paneer Tikka", 200, true);
        MenuItem item2 = new MenuItem(2, "Veg Biryani", 150, true);
        MenuItem item3 = new MenuItem(3, "Chole Bhature", 200, true);
        MenuItem item4 = new MenuItem(4, "Paneer Butter Masala", 250, true);
        MenuItem item5 = new MenuItem(5, "Veg Pulao", 180, true);
        itemList1.add(item1);
        itemList1.add(item2);
        itemList1.add(item3);
        itemList1.add(item4);

        itemList2.add(item5);
        itemList2.add(item1);
        itemList2.add(item2);
        itemList2.add(item3);

        restaurantService.addRestaurant(restaurant1,itemList1,100);
        restaurantService.addRestaurant(restaurant2,itemList2,200);
        restaurantService.addRestaurant(restaurant3, itemList1, 300);

        RestaurantStrategy restaurantStrategy = new ProcessingCapacityRestaurantStrategy();
        OrderService orderService = new OrderServiceImpl(restaurantService, restaurantStrategy);
        CommandProcessor commandProcessor = new CommandProcessor(restaurantService, orderService);

        List<String> inputCommands = Arrays.asList(
                "5, place-order, order2, Paneer Tikka, Veg Biryani, Chole Bhature",
                "2, update-price, restaurant3, Paneer Tikka, 50",
                "8, dispatch-order, order2",
                "5, place-order, order3, Veg Biryani, Chole Bhature",
                "3, place-order, order1, Paneer Tikka"
        );

        commandProcessor.processInputCommands(inputCommands);
    }
}

