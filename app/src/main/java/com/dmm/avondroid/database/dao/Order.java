package com.dmm.avondroid.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by waldekd on 7/9/15.
 */
public class Order {
    private int id;
    private int client_id;
    private double total_cost;
    private String status;
    private String last_update_date;

    public String getLast_update_date() {
        return last_update_date;
    }

    public Order(int id, int client_id, double total_cost, String status, String last_update_date) {
        this.id = id;
        this.client_id = client_id;
        this.total_cost = total_cost;
        this.status = status;
        this.last_update_date = last_update_date == null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : last_update_date;
    }

    public Order(int client_id, double total_cost, String status) {
        this.client_id = client_id;
        this.total_cost = total_cost;
        this.status = status;
        this.last_update_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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
        public static final String TABLE_NAME = "Orders";

        public static final String ID = "_id";
        public static final String CLIENT_ID = "client_id";
        public static final String TOTAL_COST = "total_cost";
        public static final String STATUS = "status";
        public static final String LAST_UPDATE_DATE = "last_update_date";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OrderTable.TABLE_NAME + " ("
                + OrderTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderTable.CLIENT_ID + " INTEGER,  "
                + OrderTable.TOTAL_COST + " REAL, "
                + OrderTable.STATUS + " TEXT, "
                + OrderTable.LAST_UPDATE_DATE + " TEXT)";


        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + OrderTable.TABLE_NAME;

        public static final String FIND_BY_ID_QUERY = OrderTable.ID + " = ? ";
        public static final String FIND_BY_CLIENT_AND_STATUS_AND_MODIF_DATE_QUERY = OrderTable.CLIENT_ID + " = ? AND " + OrderTable.STATUS + " = ? AND " + OrderTable.LAST_UPDATE_DATE + " = ? ";
        public static final String FIND_BY_CLIENT =  OrderTable.CLIENT_ID + " = ? ";
        public static final String FIND_ACTIVE_BY_CLIENT =  OrderTable.CLIENT_ID + " = ? AND " + OrderTable.STATUS + " = 'A'";
    }

    private final static String[] ALL_COLUMNS = new String[]{OrderTable.ID, OrderTable.CLIENT_ID, OrderTable.TOTAL_COST, OrderTable.STATUS, OrderTable.LAST_UPDATE_DATE};

    public static void addOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderTable.CLIENT_ID, order.getClient_id());
        values.put(OrderTable.TOTAL_COST, order.getTotal_cost());
        values.put(OrderTable.STATUS, order.getStatus());
        values.put(OrderTable.LAST_UPDATE_DATE, order.getLast_update_date());

        db.insert(OrderTable.TABLE_NAME, null, values);
        db.close();
    }

    public static Order getOrder(SQLiteOpenHelper helper, int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderTable.TABLE_NAME, ALL_COLUMNS, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(id)}, null, null, null, null);

        Order order = null;
        if(cursor.moveToFirst()){
            order =  cursorToObject(cursor);
    }
    return order;
}


    public static Order getOrderByClientIdStatusLastModification(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderTable.TABLE_NAME, ALL_COLUMNS, OrderTable.FIND_BY_CLIENT_AND_STATUS_AND_MODIF_DATE_QUERY, new String[]{String.valueOf(order.getClient_id()), order.getStatus(), order.getStatus()}, null, null, null, null);

        Order existing_order = null;
        if(cursor.moveToFirst()){
            existing_order =  cursorToObject(cursor);
        }
        return existing_order;
    }

    public static int getOrdersCount(SQLiteOpenHelper helper){
        return getAllOrders(helper).size();
    }

    public static List<Order> getAllOrders(SQLiteOpenHelper helper){
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

    public static List<Order> getAllOrdersForClient(SQLiteOpenHelper helper, int client_id, boolean onlyActive){
        List<Order> orders = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        String query = onlyActive ? OrderTable.FIND_ACTIVE_BY_CLIENT : OrderTable.FIND_BY_CLIENT;
        Cursor cursor = db.query(OrderTable.TABLE_NAME, ALL_COLUMNS, query, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                orders.add(cursorToObject(cursor));
            }while(cursor.moveToNext());
        }
        return orders;
    }


    public static int updateOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderTable.CLIENT_ID, order.getClient_id());
        values.put(OrderTable.TOTAL_COST, order.getTotal_cost());
        values.put(OrderTable.STATUS, order.getStatus());
        values.put(OrderTable.LAST_UPDATE_DATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        int ret = db.update(OrderTable.TABLE_NAME, values, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(order.getId())});
        db.close();
        return ret;

    }

    public static void deleteOrder(SQLiteOpenHelper helper, Order order){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(OrderTable.TABLE_NAME, OrderTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(order.getId())});
        db.close();
    }

    private static Order cursorToObject(Cursor cursor){
        return new Order(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getString(3), cursor.getString(4));
    }
}
