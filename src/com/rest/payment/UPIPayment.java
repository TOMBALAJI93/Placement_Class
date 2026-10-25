package com.rest.payment;

public class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.printf(
                "UPI payment of Rs. %.2f successful.%n", amount);
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}