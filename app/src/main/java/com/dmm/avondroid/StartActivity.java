package com.dmm.avondroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dmm.avondroid.orders.OrderList;
import com.dmm.avondroid.database.DataBaseHelper;


public class StartActivity extends Activity {

    public DataBaseHelper db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        db_helper = new DataBaseHelper(this);//only one Db helper shall exists for the application
        ((GlobalApplication)getApplication()).setDb_helper(db_helper);//store db_helper in the global-wide place :)
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Intent i = null;
        switch(v.getId()){
            case R.id.btnOrders:
                i = new Intent(this, OrderList.class);
                break;
            case R.id.btnProducts:
                break;
            case R.id.btnClients:
                break;
            case R.id.btnExit:
                finish();
                break;
        }

        if(i != null){
            startActivity(i);
        }
    }


}
