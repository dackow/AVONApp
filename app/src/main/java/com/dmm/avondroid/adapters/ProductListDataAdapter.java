package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.base.BaseAdapter;
import com.dmm.avondroid.clients.ClientListDisplayObject;
import com.dmm.avondroid.products.ProductListDisplayObject;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ProductListDataAdapter extends BaseAdapter<ProductListDisplayObject> {
    int color_total_price = getContext().getResources().getColor(R.color.colors_red);

    public ProductListDataAdapter(Context context, ProductListDisplayObject[] orders) {
        super(context, R.layout.item_product_list, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        TextView teProductName;
        TextView tePrice;

        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            teProductName = (TextView) convertView.findViewById(R.id.teProductName);
            tePrice = (TextView) convertView.findViewById(R.id.tePrice);

            holder = new ViewHolder();
            holder.product_name = teProductName;
            holder.price = tePrice;
            convertView.setTag(holder);
        } else {
            teProductName = holder.product_name;
            tePrice = holder.price;
        }

        teProductName.setTextColor((position % 2 == 0) ? color_client_name_even : color_client_name_odd);
        teProductName.setText(currentElement.getName());

        tePrice.setBackgroundColor(color_total_price);
        tePrice.setText(numberFormat.format(Double.valueOf(currentElement.getPrice())));

        return convertView;
    }

    private static class ViewHolder {
        TextView product_name;
        TextView price;
    }
}
