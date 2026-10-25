package com.rest.models;

public class OrderItem {
    private Menu menu;
    private int quantity;
    private float price;

    public OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = menu.getPrice();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getSubtotal() {
        return price * quantity;
    }
}
