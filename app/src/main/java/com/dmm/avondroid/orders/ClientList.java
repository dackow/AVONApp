package com.dmm.avondroid.orders;


import android.os.Bundle;
import android.widget.ListView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.adapters.ClientListDataAdapter;
import com.dmm.avondroid.base.BaseList;
import com.dmm.avondroid.clients.ClientListDisplayObject;
import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waldekd on 2015-07-15.
 */
public class ClientList extends BaseList<ClientListDisplayObject, ClientListDataAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_clientlist);

        lvListView = (ListView) findViewById(R.id.lvClientList);

        generateDisplayObjects();

        if (displayObjects != null) {
            adapter = new ClientListDataAdapter(getApplicationContext(), displayObjects);
            lvListView.setAdapter(adapter);
        }

    }

    @Override
    protected void generateDisplayObjects() {
        List<ClientListDisplayObject> displayObjectList = new ArrayList<>();

        List<Client> all_orders = Client.getAllClients(db_helper.db);

        for (Client single_client : all_orders) {
            ClientListDisplayObject clientDisplayObject = new ClientListDisplayObject();
            clientDisplayObject.setClient_name(single_client.getName());
            clientDisplayObject.setNumberOfOrders(Order.getAllOrdersForClient(db_helper.db, single_client.getId(), true).size());

            displayObjectList.add(clientDisplayObject);
        }

        displayObjects = displayObjectList.toArray(new ClientListDisplayObject[displayObjectList.size()]);

    }
}
