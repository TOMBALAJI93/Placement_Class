
package com.rest.CLI;

import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.service.BillServices;
import com.rest.service.OrderServices;

import java.util.List;
import java.util.Scanner;

public class BillCLI {

    private BillServices billService;
    private OrderServices orderService;
    private Scanner sc;

    public BillCLI(BillServices billService,
                   OrderServices orderService,
                   Scanner sc) {
        this.billService = billService;
        this.orderService = orderService;
        this.sc = sc;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n===== BILL MANAGEMENT =====");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. Search Bill by ID");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    generateBill();
                    break;

                case 2:
                    viewAllBills();
                    break;

                case 3:
                    searchBill();
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    private void generateBill() {
        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("\n===== AVAILABLE ORDERS =====");

        for (Order order : orders) {
            System.out.println(
                "Order ID: " + order.getOrderId()
                + " | Table: " + order.getTableNo()
                + " | Amount: Rs. " + order.getTotal()
            );
        }

        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();

        Order selectedOrder = null;

        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                selectedOrder = order;
                break;
            }
        }

        if (selectedOrder == null) {
            System.out.println("Order not found.");
            return;
        }

        try {
            Bill bill = billService.generateBill(selectedOrder);
            bill.displayBill();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllBills() {
        List<Bill> bills = billService.getAllBills();

        if (bills.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }

        System.out.println("\n===== ALL BILLS =====");

        for (Bill bill : bills) {
            bill.displayBill();
        }
    }

    private void searchBill() {
        System.out.print("Enter Bill ID: ");
        int billId = sc.nextInt();

        Bill bill = billService.getBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
        } else {
            bill.displayBill();
        }
    }
}
