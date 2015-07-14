package com.dmm.avondroid.clients;

import com.dmm.avondroid.GlobalApplication;
import com.dmm.avondroid.database.dao.Client;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ClientListDisplayObject extends Client {
    protected int numberOfOrders;

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public ClientListDisplayObject(int id, String name, boolean active, double discount, int numOfOrders) {
        super(id, name, active, discount);
        numberOfOrders = numOfOrders;

    }
}
