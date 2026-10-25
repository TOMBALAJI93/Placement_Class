package com.rest.service;

import com.rest.models.Menu;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.Table;
import com.rest.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class OrderServices {

    private final OrderRepository orderRepository;
    private final MenuServices menuService;
    private final TableServices tableService;

    private int nextOrderId = 1;

    public OrderServices(MenuServices menuService,
                         TableServices tableService) {
        this.menuService = menuService;
        this.tableService = tableService;
        this.orderRepository = new OrderRepository();
    }

    // Return all tables
    public List<Table> getAllTables() {
        return tableService.getAll();
    }

    // Return only free tables
    public List<Table> getAvailableTables() {
        List<Table> available = new ArrayList<>();

        for (Table table : tableService.getAll()) {
            if (table.getStatus() == Table.Availability.FREE) {
                available.add(table);
            }
        }

        return available;
    }

    // Find a table only if it is free
    public Table getAvailableTable(int tableNo) {
        for (Table table : getAvailableTables()) {
            if (table.getId() == tableNo) {
                return table;
            }
        }

        return null;
    }

    // Create an order for a free table
    public Order createOrder(int tableNo) {
        Table table = getAvailableTable(tableNo);

        if (table == null) {
            throw new IllegalArgumentException(
                "Invalid table number or table is occupied."
            );
        }

        return new Order(nextOrderId++, tableNo);
    }

    // Add food to the order
    public void addItem(Order order, int foodId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        for (Menu menu : menuService.getAll()) {
            if (menu.getId() == foodId) {
                order.addItem(new OrderItem(menu, quantity));
                return;
            }
        }

        throw new IllegalArgumentException("Food ID not found.");
    }

    // Save the order and mark the table occupied
    public void saveOrder(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException(
                "Cannot save an empty order."
            );
        }

        Table table = getAvailableTable(order.getTableNo());

        if (table == null) {
            throw new IllegalArgumentException(
                "This table is no longer available."
            );
        }

        orderRepository.save(order);
        table.setStatus(Table.Availability.OCCUPIED);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }
}
