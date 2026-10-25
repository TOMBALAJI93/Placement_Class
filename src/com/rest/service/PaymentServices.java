
package com.rest.service;

import com.rest.models.Bill;
import com.rest.models.Payment;
import com.rest.repository.PaymentRepository;
import java.util.List;

public class PaymentServices {

    private final PaymentRepository paymentRepository;
    private final BillServices billService;
    private int nextPaymentId = 1;

    public PaymentServices(BillServices billService) {
        this.billService = billService;
        this.paymentRepository = new PaymentRepository();
    }

    public Payment makePayment(int billId, String method) {

        Bill bill = billService.getBillById(billId);

        if (bill == null) {
            throw new IllegalArgumentException("Bill not found.");
        }

        // Prevent duplicate payment for the same bill
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.getBillId() == billId
                    && payment.getStatus() == Payment.Status.PAID) {
                throw new IllegalArgumentException(
                    "This bill has already been paid."
                );
            }
        }

        if (method == null || method.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Payment method cannot be empty."
            );
        }

        Payment payment = new Payment(
            nextPaymentId++,
            billId,
            bill.getTotalAmount(),
            method
        );

        paymentRepository.save(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId);
    }

    
}
