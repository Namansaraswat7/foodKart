# foodKart

**Problem Statement**

We need to implement a simplified food order management system, named FoodKart. It was a 120-minute round, with 30 minutes for problem briefing and 90 minutes for implementation.
The problem involved designing a system that simulates food ordering from restaurants with in-memory data structures. The challenge tested object-oriented design, modularity, clean coding, and extensibility. No UI or database, just plain backend logic.


**Key Features to Implement:**
1. Onboard new restaurants with menu and processing capacity.
2. Update restaurant menu/prices dynamically.
3. Place orders based on a restaurant selection strategy.
4. Orders must only be accepted if all items are available and capacity allows.
5. Dispatch orders, making room for new ones.
6. Keep track of items served per restaurant.
7. Process a set of commands sorted by timestamp (even if given in random order).
8. [Bonus] Show all dispatched orders.


**Input/Output Handling:**
Took commands as strings with timestamps.
Parsed and sorted based on timestamps before processing.
Used STDOUT for all outputs like:
Menu after updates
Order status and assigned restaurant

**Dispatch confirmations**

5, place-order, order2, item1, item2, item3

2, update-price, restaurant3, item1, 50

8, dispatch-order, order2 

5, place-order, order3, item2, item3

3, place-order, order1, item1

Implementation  
Entities ->

Restaurant
- Id
- processingCapacity
- Name
- Location
- List<MenuItems> or Map<id/name, MenuItems> itemsList (Will check)
- servedItems<id, MenuItem>

MenuItem
- Id
- Name
- Price
- isAvailable

Order
- orderId
- Restaurant Id 
- Item Count
- Total Price
- OrderStatus
- Map<MenuItem, Count>

OrderStatus
ACCEPTED
DISPACHED
PROCESSED
REJECTED

RestaurantService

Add/delete Raustrant
UpdateRestaurantMenuItems
UpdateProcessingCapacity

RestaurantStrategy
- findRestaurant()

LowestPriceStrategy 
NearestLocationStrategy
ProcessingCapacity()

OrderService
- PlaceOrder()
- showDispacthedOrders()



UML diagram

<img width="1338" height="831" alt="Pasted Graphic 5" src="https://github.com/user-attachments/assets/ea63dcb0-ba28-489f-af6c-c727d2226b32" />
