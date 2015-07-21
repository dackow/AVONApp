package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.base.BaseAdapter;
import com.dmm.avondroid.orders.OrderListDisplayObject;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by waldekd on 2015-07-10.
 */
public class OrderListDataAdapter extends BaseAdapter<OrderListDisplayObject> {

    int color_last_update_date = getContext().getResources().getColor(R.color.colors_black);
    int color_total_price = getContext().getResources().getColor(R.color.colors_red);

    private static class ViewHolder{
        TextView client_name;
        TextView last_update_date;
        TextView total_price;

    }

    public OrderListDataAdapter(Context context, OrderListDisplayObject[] orders) {
        super(context, R.layout.item_order_list, orders);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

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

        teClientName.setText(currentElement.getClientName());
        teLastUpdateDate.setText(currentElement.getLastUpdateDate());
        teTotalPrice.setText(numberFormat.format(Double.valueOf(currentElement.getTotalPrice())));

        teClientName.setTextColor((position % 2 == 0) ? color_client_name_even : color_client_name_odd);
        teLastUpdateDate.setTextColor(color_last_update_date);

        teTotalPrice.setBackgroundColor(color_total_price);
        return convertView;
    }


}
