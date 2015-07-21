package com.dmm.avondroid.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dmm.avondroid.R;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by waldekd on 2015-07-20.
 */
public class BaseAdapter<T> extends ArrayAdapter<T>{
    protected T[] elementsTodisplay;
    protected T currentElement;
    protected int resourceID;

    protected int color_client_name_even = getContext().getResources().getColor(R.color.even_colors_list);
    protected int color_client_name_odd = getContext().getResources().getColor(R.color.odd_colors_list);

    protected NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pl_PL"));

    public BaseAdapter(Context context, int resource, T[] elements){
        super(context, 0, elements);
        resourceID = resource;
        elementsTodisplay = elements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        currentElement = elementsTodisplay[position];

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
        }
        return convertView;
    }
}

