package com.dmm.avondroid.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dmm.avondroid.GlobalApplication;
import com.dmm.avondroid.R;
import com.dmm.avondroid.adapters.ClientListDataAdapter;
import com.dmm.avondroid.database.DataBaseHelper;


/**
 * Created by waldekd on 2015-07-15.
 */
public abstract class BaseList<ItemToDisplayType, AdapterType extends ArrayAdapter> extends Activity {

    protected DataBaseHelper db_helper;
    protected ItemToDisplayType[] displayObjects;
    protected AdapterType adapter;
    protected ListView lvList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvList = (ListView) findViewById(R.id.lvList);
        generateDisplayObjects();
    }

    protected abstract void generateDisplayObjects();
}
