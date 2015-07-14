package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.clients.ClientListDisplayObject;
import com.dmm.avondroid.orders.OrderListDisplayObject;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ClientListDataAdapter extends ArrayAdapter<ClientListDisplayObject>{
    protected ClientListDisplayObject[] clientsToDisplay;

    public ClientListDataAdapter(Context context, ClientListDisplayObject[] orders) {
        super(context, 0, orders);
        clientsToDisplay = orders;
    }

    private static class ViewHolder{
        TextView client_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClientListDisplayObject currentClient = clientsToDisplay[position];

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_item_list, parent, false);
        }

        TextView teClientName = (TextView)convertView.findViewById(R.id.teClientName);

        teClientName.setText(currentClient.getName());


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
