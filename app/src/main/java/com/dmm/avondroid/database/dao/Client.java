package com.dmm.avondroid.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmm.avondroid.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waldekd on 7/9/15.
 */
public class Client {
    private int id;
    private String name;
    private boolean active;
    private double discount;

    public Client(int id, String name, boolean active, double discount) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.discount = discount;
    }

    public Client(String name, double discount) {
        this.name = name;
        this.discount = discount;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static class ClientTable {
        public static final String TABLE_NAME = "Client";

        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String ACTIVE = "act";
        public static final String DISCOUNT = "disc";

        public static final String SQL_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + ClientTable.TABLE_NAME + " ("
                + ClientTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ClientTable.NAME + " TEXT COLLATE NOCASE,  "
                + ClientTable.ACTIVE + " TEXT, "
                + ClientTable.DISCOUNT + " REAL)";

        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ClientTable.TABLE_NAME;

        public static final String FIND_BY_ID_QUERY = ClientTable.ID + " = ? ";
        public static final String FIND_BY_NAME_QUERY = ClientTable.NAME + " = ? ";
    }

    private static final String[] ALL_COLUMNS = new String[]{ClientTable.ID, ClientTable.NAME, ClientTable.ACTIVE, ClientTable.DISCOUNT};

    public static void addClient(SQLiteOpenHelper helper, Client client){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClientTable.NAME, client.getName());
        values.put(ClientTable.ACTIVE, client.isActive() ? "Y" : "N");
        values.put(ClientTable.DISCOUNT, client.getDiscount());

        db.insert(ClientTable.TABLE_NAME, null, values);
        db.close();
    }

    public static Client getClient(SQLiteOpenHelper helper, int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ClientTable.TABLE_NAME, ALL_COLUMNS, ClientTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(id)}, null, null, null, null);

        Client client = null;
        if(cursor.moveToFirst()){
            client = cursorToObject(cursor);
        }
        return client;
    }

    public static Client getClientByName(SQLiteOpenHelper helper, Client client){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ClientTable.TABLE_NAME, ALL_COLUMNS, ClientTable.FIND_BY_NAME_QUERY, new String[]{client.getName()}, null, null, null, null);

        Client existing_client = null;
        if(cursor.moveToFirst()){
            existing_client = cursorToObject(cursor);
        }
        return existing_client;
    }

    public static int getClientsCount(DataBaseHelper.DataBaseHolder helper){
        return getAllClients(helper).size();
    }

    public static List<Client> getAllClients(DataBaseHelper.DataBaseHolder helper){
        List<Client> clients = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ClientTable.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                clients.add(cursorToObject(cursor));
            }while(cursor.moveToNext());
        }
        return clients;
    }

    public static int updateClient(SQLiteOpenHelper helper, Client client){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ClientTable.NAME, client.getName());
        values.put(ClientTable.ACTIVE, client.isActive() ? "Y" : "N");
        values.put(ClientTable.DISCOUNT, client.getDiscount());

        int ret = db.update(ClientTable.TABLE_NAME, values, ClientTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(client.getId())});
        db.close();
        return ret;

    }

    public static void deleteClient(SQLiteOpenHelper helper, Client client){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(ClientTable.TABLE_NAME, ClientTable.FIND_BY_ID_QUERY, new String[]{String.valueOf(client.getId())});
        db.close();
    }

    private static Client cursorToObject(Cursor cursor){
        return new Client(cursor.getInt(0), cursor.getString(1), "Y".equals(cursor.getString(2)), cursor.getDouble(3));
    }
}
