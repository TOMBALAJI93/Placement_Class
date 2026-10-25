
package com.rest.models;

public class Payment {

    public enum Status {
        PAID,
        FAILED
    }

    private int paymentId;
    private int billId;
    private double amount;
    private String paymentMethod;
    private Status status;

    public Payment(int paymentId, int billId,
                   double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = Status.PAID;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getBillId() {
        return billId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void displayPayment() {
        System.out.println("\n========== PAYMENT RECEIPT ==========");
        System.out.println("Payment ID : " + paymentId);
        System.out.println("Bill ID    : " + billId);
        System.out.printf("Amount     : Rs. %.2f%n", amount);
        System.out.println("Method     : " + paymentMethod);
        System.out.println("Status     : " + status);
        System.out.println("=====================================");
    }
}
