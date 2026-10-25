// package com.rest.models;

// import java.util.ArrayList;
// import java.util.List;

// public class Order {
//     private int orderId;
//     private List<OrderItem> items = new ArrayList<>();

//     public Order(int orderId) {
//         this.orderId = orderId;
//     }

//     public int getOrderId() {
//         return orderId;
//     }

//     public List<OrderItem> getItems() {
//         return items;
//     }

//     public void addItem(OrderItem item) {
//         items.add(item);
//     }

//     public float getTotal() {
//         float total = 0;

//         for (OrderItem item : items) {
//             total += item.getSubtotal();
//         }

//         return total;
//     }
// }


package com.rest.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int tableNo;
    private List<OrderItem> items;
    private double total;

    public Order(int orderId, int tableNo) {
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        total += item.getSubtotal();
    }
}