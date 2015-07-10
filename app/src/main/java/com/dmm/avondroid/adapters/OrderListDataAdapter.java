package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;
import com.dmm.avondroid.database.dao.OrderItem;
import com.dmm.avondroid.database.dao.Product;

import java.util.List;

/**
 * Created by waldekd on 2015-07-10.
 */
public class OrderListDataAdapter extends ArrayAdapter<Order> {
    private List<Order> orders;
    private List<Client> clients;
    private List<OrderItem> orderItems;
    private List<Product> products;

    private static class ViewHolder{
        TextView client_name;
        TextView last_update_date;
        TextView total_price;

    }

    public OrderListDataAdapter(Context context, List<Order> orders, List<Client> clients, List<OrderItem> orderItems, List<Product> products) {
        super(context, 0, orders);
        this.orders = orders;
        this.clients = clients;
        this.orderItems = orderItems;
        this.products = products;
    }

    /*
    *  <TextView
        android:id="@+id/teClientName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/teLastUpdateDate"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"/>

        <TextView
            android:id="@+id/teTotalPrice"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"/>*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order currentOrder = orders.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order_list, parent, false);
        }

        TextView teClientName = (TextView)convertView.findViewById(R.id.teClientName);
        TextView teLastUpdateDate = (TextView)convertView.findViewById(R.id.teLastUpdateDate);
        TextView teTotalPrice = (TextView)convertView.findViewById(R.id.teTotalPrice);



        //teClientName.setText();
        //return super.getView(position, convertView, parent);
    }
}
