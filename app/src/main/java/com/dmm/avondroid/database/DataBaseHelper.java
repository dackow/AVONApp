package com.dmm.avondroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmm.avondroid.database.dao.Client;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by waldekd on 2015-07-09.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AVON.db";

    //************************** CLILENT ***************************//
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
                + ClientTable.DISCOUNT + " REAL)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ClientTable.TABLE_NAME;
    }

    public void addClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClientTable.NAME, client.getName());
        values.put(ClientTable.ACTIVE, client.isActive() ? "Y" : "N");
        values.put(ClientTable.DISCOUNT, client.getDiscount());

        db.insert(ClientTable.TABLE_NAME, null, values);
        db.close();
    }

    public Client getClient(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ClientTable.TABLE_NAME, new String[]{ClientTable.ID, ClientTable.NAME, ClientTable.ACTIVE, ClientTable.DISCOUNT}, ClientTable.ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        Client client = null;
        if(cursor.moveToFirst()){
            client = new Client(cursor.getInt(0), cursor.getString(1), "Y".equals(cursor.getString(2)), cursor.getDouble(3));
        }
        return client;
    }

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ClientTable.TABLE_NAME, new String[]{ClientTable.ID, ClientTable.NAME, ClientTable.ACTIVE, ClientTable.DISCOUNT}, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                clients.add(new Client(cursor.getInt(0), cursor.getString(1), "Y".equals(cursor.getString(2)), cursor.getDouble(3)));
            }while(cursor.moveToNext());
        }
        return clients;
    }

    //************************** PRODUCT ***************************//
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
    }

    //************************** ORDER ***************************//
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
    }

    //************************** ORDERITEM ***************************//
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
                + OrderItemTable.SUM_AT_POSITION + " REAL)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + OrderItemTable.TABLE_NAME;
    }

    private static String[] SQL_CREATE_ALL = {ClientTable.SQL_CREATE_TABLE, ProductTable.SQL_CREATE_TABLE, OrderTable.SQL_CREATE_TABLE, OrderItemTable.SQL_CREATE_TABLE};
    private static String[] SQL_DROP_ALL = {ClientTable.SQL_DROP_TABLE, ProductTable.SQL_DROP_TABLE, OrderTable.SQL_DROP_TABLE, OrderItemTable.SQL_DROP_TABLE};


    //constructors
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //overrides
    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String singleQuery : SQL_CREATE_ALL) {
            db.execSQL(singleQuery);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String singleQuery : SQL_DROP_ALL) {
            db.execSQL(singleQuery);
        }
        onCreate(db);
    }



}
