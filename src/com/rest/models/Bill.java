package com.rest.models;

public class Bill {

    private int billId;
    private int orderId;
    private int tableNo;
    private double subtotal;
    private double tax;
    private double totalAmount;

    public Bill(int billId, int orderId, int tableNo,
                double subtotal, double tax) {

        this.billId = billId;
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.subtotal = subtotal;
        this.tax = tax;
        this.totalAmount = subtotal + tax;
    }

    public int getBillId() {
        return billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayBill() {
        System.out.println("\n========== RESTAURANT BILL ==========");
        System.out.println("Bill ID      : " + billId);
        System.out.println("Order ID     : " + orderId);
        System.out.println("Table Number : " + tableNo);

        System.out.printf("Subtotal     : Rs. %.2f%n", subtotal);
        System.out.printf("Tax          : Rs. %.2f%n", tax);
        System.out.printf("Total Amount : Rs. %.2f%n", totalAmount);

        System.out.println("=====================================");
    }
}
