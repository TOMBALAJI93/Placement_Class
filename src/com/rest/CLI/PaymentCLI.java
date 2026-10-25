
package com.rest.CLI;

import com.rest.models.Bill;
import com.rest.models.Payment;
import com.rest.payment.*;
import com.rest.service.BillServices;
import com.rest.service.PaymentServices;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {

    private final PaymentServices service;
    private final BillServices billService;
    private final Scanner scanner;

    public PaymentCLI(PaymentServices service,
                      BillServices billService,
                      Scanner scanner) {
        this.service = service;
        this.billService = billService;
        this.scanner = scanner;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n=== RESTAURANT PAYMENT SYSTEM ===");
            System.out.println("1. Make Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Find Payment by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        makePayment();
                        break;

                    case 2:
                        viewAllPayments();
                        break;

                    case 3:
                        findPayment();
                        break;

                    case 0:
                        System.out.println("Returning to Main Menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void makePayment() {
        List<Bill> bills = billService.getAllBills();

        if (bills.isEmpty()) {
            System.out.println("No bills available. Generate a bill first.");
            return;
        }

        System.out.println("\n=== AVAILABLE BILLS ===");

        for (Bill bill : bills) {
            System.out.printf(
                "Bill ID: %d | Order ID: %d | Table: %d | Total: Rs. %.2f%n",
                bill.getBillId(),
                bill.getOrderId(),
                bill.getTableNo(),
                bill.getTotalAmount()
            );
        }

        System.out.print("\nEnter Bill ID: ");
        int billId = scanner.nextInt();

        Bill bill = billService.getBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        System.out.printf(
            "Amount to pay: Rs. %.2f%n",
            bill.getTotalAmount()
        );

        System.out.println("\nSelect Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        PaymentMethod method;

        switch (choice) {
            case 1:
                method = new CashPayment();
                break;

            case 2:
                method = new CardPayment();
                break;

            case 3:
                method = new UPIPayment();
                break;

            default:
                System.out.println("Invalid payment method.");
                return;
        }

        // Demonstrate the selected payment method
        method.pay(bill.getTotalAmount());

        Payment payment = service.makePayment(
            billId,
            method.getMethodName()
        );

        System.out.println("\nPayment record saved successfully.");
        payment.displayPayment();
    }

    private void viewAllPayments() {
        List<Payment> payments = service.getAllPayments();

        if (payments.isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }

        System.out.println("\n=== ALL PAYMENTS ===");

        for (Payment payment : payments) {
            payment.displayPayment();
        }
    }

    private void findPayment() {
        System.out.print("Enter Payment ID: ");
        int id = scanner.nextInt();

        Payment payment = service.getPaymentById(id);

        if (payment == null) {
            System.out.println("Payment not found.");
        } else {
            payment.displayPayment();
        }
    }
}
