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

    public static class ProductTable {
        public static final String TABLE_NAME = "Product";

        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String ACTIVE = "act";
        public static final String PRICE = "price";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + ProductTable.TABLE_NAME + " ("
                + ProductTable.ID + " INTEGER PRIMART KEY AUTOINCREMENT, "
                + ProductTable.NAME + " TEXT,  "
                + ProductTable.ACTIVE + " TEXT, "
                + ProductTable.PRICE + " REAL)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ProductTable.TABLE_NAME;

        public static final String FIND_BY_ID_QUERY = ProductTable.ID + " = ? ";
    }

    private static final String[] ALL_COLUMNS = new String[]{ProductTable.ID, ProductTable.NAME, ProductTable.ACTIVE, ProductTable.PRICE};

    public void addProduct(SQLiteOpenHelper helper, Product client){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductTable.NAME, client.getName());
        values.put(ProductTable.ACTIVE, client.isActive() ? "Y" : "N");
        values.put(ProductTable.PRICE, client.getPrice());

        db.insert(ProductTable.TABLE_NAME, null, values);
        db.close();
    }

    public Product getProduct(SQLiteOpenHelper helper, int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ProductTable.TABLE_NAME, ALL_COLUMNS, ProductTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(id)}, null, null, null, null);

        Product product = null;
        if(cursor.moveToFirst()){
            product = cursorToObject(cursor);
        }
        return product;
    }

    public int getProductsCount(SQLiteOpenHelper helper){
        return getAllProducts(helper).size();
    }

    public List<Product> getAllProducts(SQLiteOpenHelper helper){
        List<Product> products = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ProductTable.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                products.add(cursorToObject(cursor));
            }while(cursor.moveToNext());
        }
        return products;
    }

    public int updateProduct(SQLiteOpenHelper helper, Product product){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductTable.NAME, product.getName());
        values.put(ProductTable.ACTIVE, product.isActive() ? "Y" : "N");
        values.put(ProductTable.PRICE, product.getPrice());

        int ret = db.update(ProductTable.TABLE_NAME, values, ProductTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(product.getId())});
        db.close();
        return ret;

    }

    public void deleteProduct(SQLiteOpenHelper helper, Product product){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(ProductTable.TABLE_NAME, ProductTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(product.getId())});
        db.close();
    }

    private Product cursorToObject(Cursor cursor){
        return new Product(cursor.getInt(0), cursor.getString(1), "Y".equals(cursor.getString(2)), cursor.getDouble(3));
    }
}
