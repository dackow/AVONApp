package com.dmm.avondroid.database.dao;

/**
 * Created by waldekd on 7/9/15.
 */
public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double sum_at_position;

    public OrderItem(int id, int order_id, int product_id, int quantity, double sum_at_position) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.sum_at_position = sum_at_position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSum_at_position() {
        return sum_at_position;
    }

    public void setSum_at_position(double sum_at_position) {
        this.sum_at_position = sum_at_position;
    }
}
