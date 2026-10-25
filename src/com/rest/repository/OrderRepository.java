
package com.rest.repository;

import com.rest.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order getById(int id) {
        for (Order order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }

        return null;
    }
}
