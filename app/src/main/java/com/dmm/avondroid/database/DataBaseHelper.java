package com.dmm.avondroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;
import com.dmm.avondroid.database.dao.OrderItem;
import com.dmm.avondroid.database.dao.Product;

import java.io.Serializable;


/**
 * Created by waldekd on 2015-07-09.
 */
public class DataBaseHelper{

    public DataBaseHolder db;

    public DataBaseHelper(Context context) {
        this.db = new DataBaseHolder(context);
    }

    public class DataBaseHolder extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "AVON.db";

        private String[] SQL_CREATE_ALL = {Client.ClientTable.SQL_CREATE_TABLE, Product.ProductTable.SQL_CREATE_TABLE, Order.OrderTable.SQL_CREATE_TABLE, OrderItem.OrderItemTable.SQL_CREATE_TABLE};
        private String[] SQL_DROP_ALL = {Client.ClientTable.SQL_DROP_TABLE, Product.ProductTable.SQL_DROP_TABLE, Order.OrderTable.SQL_DROP_TABLE, OrderItem.OrderItemTable.SQL_DROP_TABLE};



        //constructors
        public DataBaseHolder(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //overrides
        @Override
        public void onCreate(SQLiteDatabase db) {
            for (String singleQuery : SQL_CREATE_ALL) {
                db.execSQL(singleQuery);
            }
        }


        //the system did not want to call the onCreate event so onOpen is being called instead
        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (String singleQuery : SQL_DROP_ALL) {
                db.execSQL(singleQuery);
            }
            onCreate(db);
        }

        public void restoreDBSequences(){

            /*
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ClientTable.NAME, client.getName());
            values.put(ClientTable.ACTIVE, client.isActive() ? "Y" : "N");
            values.put(ClientTable.DISCOUNT, client.getDiscount());

            int ret = db.update(ClientTable.TABLE_NAME, values, ClientTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(client.getId())});
            db.close();
            return ret;*/

        }

    }

}
