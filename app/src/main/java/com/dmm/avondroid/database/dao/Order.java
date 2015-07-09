package com.dmm.avondroid.database.dao;

/**
 * Created by waldekd on 7/9/15.
 */
public class Order {
    private int id;
    private int client_id;
    private double total_cost;
    private String status;

    public Order(int id, int client_id, double total_cost, String status) {
        this.id = id;
        this.client_id = client_id;
        this.total_cost = total_cost;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
