package com.dmm.avondroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by waldekd on 2015-07-09.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AVON.db";

    public static class ClientTable {
        public static final String TABLE_NAME = "Client";

        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String ACTIVE = "act";
        public static final String DISCOUNT = "disc";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + ClientTable.TABLE_NAME + " ("
                + ClientTable.ID + " INTEGER PRIMART KEY AUTOINCREMENT, "
                + ClientTable.NAME + " TEXT,  "
                + ClientTable.ACTIVE + " TEXT, "
                + ClientTable.DISCOUNT + " TEXT)";
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
                + ProductTable.PRICE + " TEXT)";
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
                + OrderTable.TOTAL_COST + " TEXT, "
                + OrderTable.STATUS + " TEXT)";
    }

    public static class OrderItemTable {
        public static final String TABLE_NAME = "OrderItem";

        public static final String ID = "_id";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String QUANTITY = "quantity";
        public static final String SUM_AT_POSITION = "sum_at_position";  /*quantity x price */

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OrderItemTable.TABLE_NAME + " ("
                + OrderItemTable.ID + " INTEGER PRIMART KEY AUTOINCREMENT, "
                + OrderItemTable.ORDER_ID + " INTEGER,  "
                + OrderItemTable.PRODUCT_ID + " INTEGER, "
                + OrderItemTable.QUANTITY + " INTEGER, "
                + OrderItemTable.SUM_AT_POSITION + " INTEGER)";
    }


    //constructors
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //overrides
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
