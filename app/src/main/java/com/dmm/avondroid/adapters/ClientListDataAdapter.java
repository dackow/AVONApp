package com.dmm.avondroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.clients.ClientListDisplayObject;

/**
 * Created by waldekd on 2015-07-14.
 */
public class ClientListDataAdapter extends ArrayAdapter<ClientListDisplayObject>{
    protected ClientListDisplayObject[] clientsToDisplay;

    public ClientListDataAdapter(Context context, ClientListDisplayObject[] orders) {
        super(context, 0, orders);
        clientsToDisplay = orders;
    }

//    private static class ViewHolder{
//        TextView client_name;
//        TextView number_of_order;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClientListDisplayObject currentClient = clientsToDisplay[position];

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_client_list, parent, false);
        }

        TextView teClientName = (TextView)convertView.findViewById(R.id.teClientName);
        TextView teNumberOfOrders = (TextView)convertView.findViewById(R.id.teNumberOfOrders);

        teClientName.setText(currentClient.getClient_name());
        teNumberOfOrders.setText(currentClient.getNumberOfOrders());


        return convertView;
    }
}
