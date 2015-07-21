package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.base.BaseAdapter;
import com.dmm.avondroid.clients.ClientListDisplayObject;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ClientListDataAdapter extends BaseAdapter<ClientListDisplayObject> {

    public ClientListDataAdapter(Context context, ClientListDisplayObject[] orders) {
        super(context, R.layout.item_client_list, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        TextView teClientName;
        TextView teNumberOfOrders;

        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            teClientName = (TextView) convertView.findViewById(R.id.teClientName);
            teNumberOfOrders = (TextView) convertView.findViewById(R.id.teNumberOfOrders);

            holder = new ViewHolder();
            holder.client_name = teClientName;
            holder.number_of_order = teNumberOfOrders;
            convertView.setTag(holder);
        } else {
            teClientName = holder.client_name;
            teNumberOfOrders = holder.number_of_order;
        }

        teClientName.setTextColor((position % 2 == 0) ? color_client_name_even : color_client_name_odd);

        teClientName.setText(currentElement.getClient_name());
        teNumberOfOrders.setText(String.valueOf(currentElement.getNumberOfOrders()));

        return convertView;
    }

    private static class ViewHolder {
        TextView client_name;
        TextView number_of_order;
    }
}
