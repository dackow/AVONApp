package com.dmm.avondroid.orders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ListView;

import com.dmm.avondroid.GlobalApplication;
import com.dmm.avondroid.R;
import com.dmm.avondroid.adapters.OrderListDataAdapter;
import com.dmm.avondroid.database.DataBaseHelper;
import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waldekd on 2015-07-09.
 */
public class OrderList extends Activity {

    protected DataBaseHelper db_helper;
    protected OrderListDisplayObject[] displayObjects;
    protected OrderListDataAdapter adapter;
    protected ListView lvListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clientlist);

        lvListView = (ListView)findViewById(R.id.lvOrderList);

        //get reference to global db_helper
        db_helper = ((GlobalApplication)getApplication()).getDb_helper();
        generateDisplayObjects();

        if(displayObjects != null){
            adapter = new OrderListDataAdapter(getApplicationContext(), displayObjects);
            lvListView.setAdapter(adapter);
        }

    }

    private void generateDisplayObjects(){
        List<OrderListDisplayObject> displayObjectList = new ArrayList<>();
        List<Order> all_orders = Order.getAllOrders(db_helper.db);

        for(Order single_order : all_orders){

            //get client for order
            Client client = Client.getClient(db_helper.db, single_order.getClient_id());

            OrderListDisplayObject orderDisplayObject = new OrderListDisplayObject();
            orderDisplayObject.setClientName(client.getName());
            orderDisplayObject.setLastUpdateDate(single_order.getLast_update_date());
            orderDisplayObject.setTotalPrice(String.valueOf(single_order.getTotal_cost()));

            orderDisplayObject.setClient_id(client.getId());
            orderDisplayObject.setOrder_id(single_order.getId());


            displayObjectList.add(orderDisplayObject);
        }

        displayObjects = displayObjectList.toArray(new OrderListDisplayObject[displayObjectList.size()]);
    }


}
