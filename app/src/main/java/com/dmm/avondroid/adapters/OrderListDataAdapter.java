package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.database.DataBaseHelper;
import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;
import com.dmm.avondroid.database.dao.OrderItem;
import com.dmm.avondroid.database.dao.Product;
import com.dmm.avondroid.orders.OrderListDisplayObject;

import java.util.List;

/**
 * Created by waldekd on 2015-07-10.
 */
public class OrderListDataAdapter extends ArrayAdapter<OrderListDisplayObject> {
    private DataBaseHelper db_helper;
    protected OrderListDisplayObject[] ordersToDisplay;


    private static class ViewHolder{
        TextView client_name;
        TextView last_update_date;
        TextView total_price;

    }

    public OrderListDataAdapter(Context context, OrderListDisplayObject[] orders) {
        super(context, 0, orders);
        ordersToDisplay = orders;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        OrderListDisplayObject currentOrder = ordersToDisplay[position];

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order_list, parent, false);
        }

        TextView teClientName = (TextView)convertView.findViewById(R.id.teClientName);
        TextView teLastUpdateDate = (TextView)convertView.findViewById(R.id.teLastUpdateDate);
        TextView teTotalPrice = (TextView)convertView.findViewById(R.id.teTotalPrice);

        teClientName.setText(currentOrder.getClientName());
        teLastUpdateDate.setText(currentOrder.getLastUpdateDate());
        teTotalPrice.setText(currentOrder.getTotalPrice());

        return convertView;
    }


}
