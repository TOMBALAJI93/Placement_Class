package com.rest.CLI;

import com.rest.models.Menu;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.Table;
import com.rest.service.MenuServices;
import com.rest.service.OrderServices;
import java.util.List;
import java.util.Scanner;

public class OrderCLI {

    private final OrderServices orderService;
    private final MenuServices menuService;
    private final Scanner sc;

    public OrderCLI(OrderServices orderService,
                    MenuServices menuService,
                    Scanner sc) {
        this.orderService = orderService;
        this.menuService = menuService;
        this.sc = sc;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Place Order");
            System.out.println("2. View All Orders");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    placeOrder();
                    break;

                case 2:
                    viewOrders();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    // Place a new order
    private void placeOrder() {

        System.out.println("\n========== TABLE STATUS ==========");

        List<Table> tables = orderService.getAllTables();

        boolean hasFreeTable = false;

        for (Table table : tables) {
            String status;

            if (table.getStatus() == Table.Availability.FREE) {
                status = "FREE";
                hasFreeTable = true;
            } else {
                status = "OCCUPIED - NOT AVAILABLE";
            }

            System.out.println(
                "Table " + table.getId()
                + " | Capacity: " + table.getCapasity()
                + " | Status: " + status
            );
        }

        System.out.println("==================================");

        if (!hasFreeTable) {
            System.out.println("Sorry! No tables are available.");
            return;
        }

        System.out.print("Enter a FREE table number: ");
        int tableNo = sc.nextInt();

        Order order;

        try {
            order = orderService.createOrder(tableNo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Display food menu
        System.out.println("\n========== FOOD MENU ==========");

        for (Menu menu : menuService.getAll()) {
            System.out.println(
                "Food ID: " + menu.getId()
                + " | " + menu.getName()
                + " | Rs. " + menu.getPrice()
            );
        }

        // Add items
        while (true) {
            System.out.print("\nEnter Food ID (0 to finish): ");
            int foodId = sc.nextInt();

            if (foodId == 0) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            try {
                orderService.addItem(order, foodId, quantity);
                System.out.println("Item added successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Save order and print bill
        try {
            orderService.saveOrder(order);

            System.out.println("\nOrder placed successfully!");
            displayOrder(order);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // View every saved order with full details
    private void viewOrders() {
        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("\nNo orders found.");
            return;
        }

        System.out.println("\n========== ALL ORDERS ==========");

        for (Order order : orders) {
            displayOrder(order);
        }
    }

    // Display one complete order
    private void displayOrder(Order order) {

        System.out.println("\n----------------------------------");
        System.out.println("Order ID     : " + order.getOrderId());
        System.out.println("Table Name   : Table " + order.getTableNo());
        System.out.println("----------------------------------");

        System.out.printf(
            "%-18s %-8s %-10s%n",
            "Food Item", "Qty", "Subtotal"
        );

        for (OrderItem item : order.getItems()) {
            System.out.printf(
                "%-18s %-8d Rs. %-7.2f%n",
                item.getMenu().getName(),
                item.getQuantity(),
                item.getSubtotal()
            );
        }

        System.out.println("----------------------------------");
        System.out.printf("Total Amount : Rs. %.2f%n", order.getTotal());
        System.out.println("----------------------------------");
    }
}
