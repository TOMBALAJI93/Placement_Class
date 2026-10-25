
package com.rest.service;

import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.repository.BillRepository;

import java.util.List;

public class BillServices {

    private BillRepository billRepository;
    private int nextBillId = 1;

    // Example tax rate: 5%
    private static final double TAX_RATE = 0.05;

    public BillServices() {
        billRepository = new BillRepository();
    }

    // Generate and save a bill for an order
    public Bill generateBill(Order order) {

        if (order == null) {
            throw new IllegalArgumentException(
                "Order cannot be null."
            );
        }

        // Prevent duplicate bills for the same order
        for (Bill existingBill : billRepository.getAll()) {
            if (existingBill.getOrderId() == order.getOrderId()) {
                return existingBill;
            }
        }

        double subtotal = order.getTotal();
        double tax = subtotal * TAX_RATE;

        Bill bill = new Bill(
            nextBillId++,
            order.getOrderId(),
            order.getTableNo(),
            subtotal,
            tax
        );

        billRepository.save(bill);

        return bill;
    }

    // Get all bills
    public List<Bill> getAllBills() {
        return billRepository.getAll();
    }

    // Find a bill by ID
    public Bill getBillById(int billId) {
        return billRepository.getById(billId);
    }
}
