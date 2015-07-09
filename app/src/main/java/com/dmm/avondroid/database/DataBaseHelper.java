package com.dmm.avondroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*


Order
------
o_id
o_cl_id
o_cost
o_sts



Order_item
--------
oi_id
oi_o_id
oi_p_id
oi_quan - quantity
oi_sum - oi_quan * p_price
* */

/**
 * Created by waldekd on 2015-07-09.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AVON.db";

    /*
    cl_id
    cl_name
    cl_act
    cl_disc - discount
    */
    public static class ClientTable{
        public static final String TABLE_NAME = "Client";

        public static final String COLUMN_ID = "_id";
        public static final String NAME = "name";
        public static final String ACTIVE = "act";
        public static final String DISCOUNT = "disc";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + ClientTable.TABLE_NAME + " (" + ClientTable.COLUMN_ID + " INTEGER PRIMART KEY AUTOINCREMENT, " + ClientTable.NAME+ " TEXT,  " +ClientTable.ACTIVE + " TEXT, " + ClientTable.DISCOUNT + " TEXT)";
    }
    /*
    Products
    ------
    p_id
    p_name
    p_price
    */
    public static class ProductTable{
        private static final String TABLE_NAME = "Product";

        public static final String COLUMN_ID = "_id";
        public static final String NAME = "name";
        public static final String ACTIVE = "act";
        public static final String PRICE = "price";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + ProductTable.TABLE_NAME + " (" + ProductTable.COLUMN_ID + " INTEGER PRIMART KEY AUTOINCREMENT, " + ProductTable.NAME+ " TEXT,  " +ProductTable.ACTIVE + " TEXT, " + ProductTable.PRICE + " TEXT)";
    }



    private static final String ORDER_TABLE_NAME = "Order";

    private static final String ORDER_ITEM_TABLE_NAME = "OrderItem";

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
