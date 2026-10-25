
package com.rest.models;

public class Menu {
    private int food_id;
    private String name;
    private float price;

    public Menu(int food_id, String name, float price) {
        this.food_id = food_id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return food_id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
