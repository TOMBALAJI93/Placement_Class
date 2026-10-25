package com.rest.payment;

public class CardPayment implements PaymentMethod {

    
    public void pay(double amount) {
        System.out.printf(
                "Card payment of Rs. %.2f successful.%n", amount);
    }

    public String getMethodName() {
        return "Card";
    }
}