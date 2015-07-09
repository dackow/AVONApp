package com.dmm.avondroid.database.dao;

/**
 * Created by waldekd on 7/9/15.
 */
public class Client {
    private int id;
    private String name;
    private boolean active;
    private double discount;

    public Client(int id, String name, boolean active, double discount) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.discount = discount;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
