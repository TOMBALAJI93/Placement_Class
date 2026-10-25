
package com.rest.repository;

import com.rest.models.Bill;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {

    private List<Bill> bills = new ArrayList<>();

    // Save a bill
    public void save(Bill bill) {
        bills.add(bill);
    }

    // Get all bills
    public List<Bill> getAll() {
        return bills;
    }

    // Find a bill using its ID
    public Bill getById(int billId) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                return bill;
            }
        }

        return null;
    }
}
