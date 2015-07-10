package com.dmm.avondroid.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public static class OrderTable {
        public static final String TABLE_NAME = "Order";

        public static final String ID = "_id";
        public static final String CLIENT_ID = "client_id";
        public static final String TOTAL_COST = "total_cost";
        public static final String STATUS = "status";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OrderTable.TABLE_NAME + " ("
                + OrderTable.ID + " INTEGER PRIMART KEY AUTOINCREMENT, "
                + OrderTable.CLIENT_ID + " INTEGER,  "
                + OrderTable.TOTAL_COST + " REAL, "
                + OrderTable.STATUS + " TEXT)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + OrderTable.TABLE_NAME;

        public static final String FIND_BY_ID_QUERY = OrderTable.ID + " = ? ";
    }

    private final static String[] ALL_COLUMNS = new String[]{OrderTable.ID, OrderTable.CLIENT_ID, OrderTable.TOTAL_COST, OrderTable.STATUS};

    public void addOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderTable.CLIENT_ID, order.getClient_id());
        values.put(OrderTable.TOTAL_COST, order.getTotal_cost());
        values.put(OrderTable.STATUS, order.getStatus());

        db.insert(OrderTable.TABLE_NAME, null, values);
        db.close();
    }

    public Order getOrder(SQLiteOpenHelper helper, int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderTable.TABLE_NAME, ALL_COLUMNS, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(id)}, null, null, null, null);

        Order order = null;
        if(cursor.moveToFirst()){
            order =  cursorToObject(cursor);
    }
    return order;
}

    public int getOrdersCount(SQLiteOpenHelper helper){
        return getAllOrders(helper).size();
    }

    public List<Order> getAllOrders(SQLiteOpenHelper helper){
        List<Order> orders = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderTable.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                orders.add(cursorToObject(cursor));
            }while(cursor.moveToNext());
        }
        return orders;
    }

    public int updateOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderTable.CLIENT_ID, order.getClient_id());
        values.put(OrderTable.TOTAL_COST, order.getTotal_cost());
        values.put(OrderTable.STATUS, order.getStatus());

        int ret = db.update(OrderTable.TABLE_NAME, values, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(order.getId())});
        db.close();
        return ret;

    }

    public void deleteOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(OrderTable.TABLE_NAME, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(order.getId())});
        db.close();
    }

    private Order cursorToObject(Cursor cursor){
        return new Order(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getString(3));
    }
}
