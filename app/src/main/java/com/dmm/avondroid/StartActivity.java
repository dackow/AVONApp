package com.dmm.avondroid;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;
import com.dmm.avondroid.database.dao.OrderItem;
import com.dmm.avondroid.database.dao.Product;
import com.dmm.avondroid.clients.ClientList;
import com.dmm.avondroid.orders.OrderList;
import com.dmm.avondroid.database.DataBaseHelper;
import com.dmm.avondroid.products.ProductList;


public class StartActivity extends Activity {

    public DataBaseHelper db_helper;
    protected Button btnGenerateFakeData;
    protected GlobalApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        application = (GlobalApplication)getApplication();

        if(!application.isDebugMode()) {
            btnGenerateFakeData = (Button) findViewById(R.id.btnGenerateFakeData);
            btnGenerateFakeData.setEnabled(false);
        }



        db_helper = new DataBaseHelper(this);//only one Db helper shall exists for the application
        application.setDb_helper(db_helper);//store db_helper in the global-wide place :)
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
                i = new Intent(this, ProductList.class);
                break;
            case R.id.btnClients:
                i = new Intent(this, ClientList.class);
                break;
            case R.id.btnGenerateFakeData:
                generateFakeData();
                break;
            case R.id.btnCleanData:
                cleanData();
                break;
            case R.id.btnExit:
                finish();
                break;
        }

        if(i != null){
            startActivity(i);
        }
    }

    private void cleanData() {
        boolean isError = false;
        try {
            SQLiteDatabase db = db_helper.db.getWritableDatabase();
            Order.deleteAllOrders(db);
            Client.deleteAllClients(db);
            Product.deleteAllProducts(db);
            db_helper.db.restoreDBSequences(db);
            db.close();
        }catch(Exception e){
            isError = true;
            Log.e(GlobalApplication.TAG, e.getMessage());
        }

        showToast(isError ? R.string.msgDataDeletedFail : R.string.msgDataDeletedSuccessfully);
    }

    private void showToast(int msgID, String... add_params){
        String msg_string = getResources().getString(msgID);
        if(add_params != null && add_params.length > 0 && add_params[0].length() > 0){
            msg_string += add_params[0];
        }
        Toast.makeText(StartActivity.this, msg_string, Toast.LENGTH_SHORT).show();
    }

    private void generateFakeData() {
        Client client1 = new Client("Waldemar Dacko",0.0);
        Client client2 = new Client("Monika Mączka",10.0);
        Client client3 = new Client("Agata popek",20.0);
        Client[] clients = new Client[]{client1, client2, client3};

        for(Client client : clients){
            if(Client.getClientByName(db_helper.db, client) == null){
                Client.addClient(db_helper.db, client);
            }
        }

        Product product1 = new Product("Perfuma1", 27.0);
        Product product2 = new Product("Perfuma2", 119.90);
        Product product3 = new Product("Krem", 46.50);

        Product[] products = new Product[]{product1, product2, product3};
        for(Product product : products){
            if(Product.getProductByName(db_helper.db, product) == null){
                Product.addProduct(db_helper.db, product);
            }
        }

        Order order1 = new Order(1,0.0,"N");
        Order order2 = new Order(1,0.0,"N");
        Order order3 = new Order(2,0.0,"N");

        Order[] orders  =new Order[]{order1, order2, order3};
        for(Order order : orders){
            if(Order.getOrderByClientIdStatusLastModification(db_helper.db, order) == null){
                Order.addOrder(db_helper.db, order);
            }
        }

        OrderItem orderItem1 = new OrderItem(1,1,1);
        OrderItem orderItem2 = new OrderItem(2,1,2);
        OrderItem orderItem3 = new OrderItem(2,2,10);

        OrderItem[] orderItems = new OrderItem[]{orderItem1, orderItem2, orderItem3};
        for(OrderItem orderItem : orderItems){
            if(OrderItem.getOrderItemByOrderIdAndProductId(db_helper.db, orderItem) == null){
                OrderItem.addOrderItem(db_helper.db, orderItem);
            }
        }

        int orders_count = Order.getOrdersCount(db_helper.db);
        int clients_count = Client.getClientsCount(db_helper.db);
        int products_count = Product.getProductsCount(db_helper.db);
        int order_items_count = OrderItem.getOrderItemsCount(db_helper.db);

        boolean isError = !(orders_count > 0 && clients_count > 0 && products_count > 0 && order_items_count > 0);

        StringBuilder msg = new StringBuilder();
        if(!isError){
            msg.append("\norders(");
            msg.append(orders_count);
            msg.append(")");
            msg.append("\norder_items(");
            msg.append(order_items_count);
            msg.append(")");

            msg.append("\nclients(");
            msg.append(clients_count);
            msg.append(")");
            msg.append("\nproducts(");
            msg.append(products_count);
            msg.append(")");
        }
        showToast(!isError ? R.string.msgDataGeneratedSuccessfully : R.string.msgDataGeneratedFail, msg.toString());
    }
}
