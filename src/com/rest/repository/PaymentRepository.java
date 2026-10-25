package com.rest.repository;

import com.rest.models.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    public void save(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }

    public Payment findById(int id) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == id) {
                return payment;
            }
        }

        return null;
    }
}