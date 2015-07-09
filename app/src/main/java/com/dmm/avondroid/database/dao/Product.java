package com.dmm.avondroid.database.dao;

/**
 * Created by waldekd on 7/9/15.
 */
public class Product {
    private int id;
    private String name;
    private boolean active;
    private double price;

    public Product(int id, String name, boolean active, double price) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
