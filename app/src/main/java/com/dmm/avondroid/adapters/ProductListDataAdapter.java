package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.clients.ClientListDisplayObject;
import com.dmm.avondroid.products.ProductListDisplayObject;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ProductListDataAdapter extends ArrayAdapter<ProductListDisplayObject> {
    protected ProductListDisplayObject[] productsToDisplay;

    public ProductListDataAdapter(Context context, ProductListDisplayObject[] orders) {
        super(context, 0, orders);
        productsToDisplay = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListDisplayObject currentProduct = productsToDisplay[position];

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product_list, parent, false);
        }

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
        teProductName.setText(currentProduct.getName());
        tePrice.setText(String.valueOf(currentProduct.getPrice()));

        return convertView;
    }

    private static class ViewHolder {
        TextView product_name;
        TextView price;
    }
}
