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

    public OrderItem(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
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

    public static class OrderItemTable {
        public static final String TABLE_NAME = "OrderItem";

        public static final String ID = "_id";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String QUANTITY = "quantity";
        public static final String SUM_AT_POSITION = "sum_at_position";  /*quantity x price */

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OrderItemTable.TABLE_NAME + " ("
                + OrderItemTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderItemTable.ORDER_ID + " INTEGER,  "
                + OrderItemTable.PRODUCT_ID + " INTEGER, "
                + OrderItemTable.QUANTITY + " INTEGER, "
                + OrderItemTable.SUM_AT_POSITION + " REAL)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + OrderItemTable.TABLE_NAME;

        public static final String FIND_BY_ID_QUERY = OrderItemTable.ID + " = ? ";
        public static final String FIND_BY_ORDER_ID_AND_PRODUCT_ID_QUERY = OrderItemTable.ORDER_ID + " = ? AND " + OrderItemTable.PRODUCT_ID + " = ? ";
    }

    private final static String[] ALL_COLUMNS = new String[]{OrderItemTable.ID, OrderItemTable.ORDER_ID,OrderItemTable.PRODUCT_ID, OrderItemTable.QUANTITY, OrderItemTable.SUM_AT_POSITION};

    public static void addOrderItem(SQLiteOpenHelper helper, OrderItem orderItem){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderItemTable.ORDER_ID, orderItem.getOrder_id());
        values.put(OrderItemTable.PRODUCT_ID, orderItem.getProduct_id());
        values.put(OrderItemTable.QUANTITY, orderItem.getQuantity());
        values.put(OrderItemTable.SUM_AT_POSITION, orderItem.getSum_at_position());

        db.insert(OrderItemTable.TABLE_NAME, null, values);
        db.close();
    }


    public static OrderItem getOrderItem(SQLiteOpenHelper helper, int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderItemTable.TABLE_NAME, ALL_COLUMNS, OrderItemTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(id)}, null, null, null, null);

        OrderItem orderItem = null;
        if(cursor.moveToFirst()) {
            orderItem = cursorToObject(cursor);
        }

        return orderItem;
    }

    public static OrderItem getOrderItemByOrderIdAndProductId(SQLiteOpenHelper helper, OrderItem item){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderItemTable.TABLE_NAME, ALL_COLUMNS, OrderItemTable.FIND_BY_ORDER_ID_AND_PRODUCT_ID_QUERY, new String[]{String.valueOf(item.getOrder_id()), String.valueOf(item.getProduct_id())}, null, null, null, null);

        OrderItem orderItem = null;
        if(cursor.moveToFirst()) {
            orderItem = cursorToObject(cursor);
        }

        return orderItem;
    }


    public static int getOrderItemsCount(SQLiteOpenHelper helper){
        return getAllOrderItems(helper).size();
    }

    public static List<OrderItem> getAllOrderItems(SQLiteOpenHelper helper){
        List<OrderItem> orderItems = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(OrderItemTable.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                orderItems.add(cursorToObject(cursor));
            }while(cursor.moveToNext());
        }
        return orderItems;
    }

    public static int updateOrderItem(SQLiteOpenHelper helper, OrderItem orderItem){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderItemTable.PRODUCT_ID, orderItem.getProduct_id());
        values.put(OrderItemTable.QUANTITY, orderItem.getQuantity());
        values.put(OrderItemTable.SUM_AT_POSITION, orderItem.getSum_at_position());

        int ret = db.update(OrderItemTable.TABLE_NAME, values, OrderItemTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(orderItem.getId())});
        db.close();
        return ret;

    }

    public static void deleteOrderItem(SQLiteOpenHelper helper, OrderItem orderItem){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(OrderItemTable.TABLE_NAME, OrderItemTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(orderItem.getId())});
        db.close();
    }

    private static OrderItem cursorToObject(Cursor cursor){
        return new OrderItem(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getDouble(4));
    }
}
