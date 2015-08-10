package com.dmm.avondroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.dmm.avondroid.GlobalApplication;
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

        private String[] ALL_TABLES = {Client.ClientTable.TABLE_NAME, Product.ProductTable.TABLE_NAME, Order.OrderTable.TABLE_NAME, OrderItem.OrderItemTable.TABLE_NAME};
        private String[] SQL_CREATE_ALL = {Client.ClientTable.SQL_CREATE_TABLE, Product.ProductTable.SQL_CREATE_TABLE, Order.OrderTable.SQL_CREATE_TABLE, OrderItem.OrderItemTable.SQL_CREATE_TABLE};
        private String[] SQL_DROP_ALL = {Client.ClientTable.SQL_DROP_TABLE, Product.ProductTable.SQL_DROP_TABLE, Order.OrderTable.SQL_DROP_TABLE, OrderItem.OrderItemTable.SQL_DROP_TABLE};

        private final String SEQUENCES_TABLE = "sqlite_sequence";
        private final String SEQUENCES_TABLE_SEQ_COLUMN = "seq";
        private final String SEQUENCES_TABLE_TABLE_COLUMN = "name";

        private final String FIND_SEQUENCE_BY_TABLE_NAME_QUERY = SEQUENCES_TABLE_TABLE_COLUMN + "=?";


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

        public void restoreDBSequences(SQLiteDatabase db){
            ContentValues values = new ContentValues();
            for(String table_name : ALL_TABLES){
                values.clear();
                values.put(SEQUENCES_TABLE_SEQ_COLUMN, 0);
                if(db.update(SEQUENCES_TABLE, values, FIND_SEQUENCE_BY_TABLE_NAME_QUERY, new String[]{table_name}) == 0){
                    Log.e(GlobalApplication.TAG, "Problem with restoring sequences for table:" + table_name);
                }
            }

        }

    }

}
