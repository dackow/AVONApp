package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.orders.OrderListDisplayObject;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by waldekd on 2015-07-10.
 */
public class OrderListDataAdapter extends ArrayAdapter<OrderListDisplayObject> {
    protected OrderListDisplayObject[] ordersToDisplay;

    int color_client_name_even = getContext().getResources().getColor(android.R.color.holo_blue_dark);
    int color_client_name_odd = getContext().getResources().getColor(android.R.color.holo_green_dark);
    int color_last_update_date = getContext().getResources().getColor(android.R.color.black);
    int color_total_price = getContext().getResources().getColor(android.R.color.holo_red_dark);

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pl_PL"));

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

        TextView teClientName;
        TextView teLastUpdateDate;
        TextView teTotalPrice;

        ViewHolder holder = (ViewHolder)convertView.getTag();
        if(holder == null){
            teClientName = (TextView)convertView.findViewById(R.id.teClientName);
            teLastUpdateDate = (TextView)convertView.findViewById(R.id.teLastUpdateDate);
            teTotalPrice = (TextView)convertView.findViewById(R.id.teTotalPrice);

            holder = new ViewHolder();
            holder.client_name = teClientName;
            holder.last_update_date = teLastUpdateDate;
            holder.total_price = teTotalPrice;
            convertView.setTag(holder);
        }else{
            teClientName = holder.client_name;
            teLastUpdateDate = holder.last_update_date;
            teTotalPrice = holder.total_price;
        }

        teClientName.setText(currentOrder.getClientName());
        teLastUpdateDate.setText(currentOrder.getLastUpdateDate());
        teTotalPrice.setText(numberFormat.format(Double.valueOf(currentOrder.getTotalPrice())));

        if(position % 2 == 0){
            teClientName.setTextColor(color_client_name_even);
        }else{
            teClientName.setTextColor(color_client_name_odd);
        }

        teLastUpdateDate.setTextColor(color_last_update_date);

        teTotalPrice.setBackgroundColor(color_total_price);
        return convertView;
    }


}
