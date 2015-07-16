package com.dmm.avondroid.products;


import android.os.Bundle;
import android.widget.ListView;

import com.dmm.avondroid.R;
import com.dmm.avondroid.adapters.ClientListDataAdapter;
import com.dmm.avondroid.adapters.ProductListDataAdapter;
import com.dmm.avondroid.base.BaseList;
import com.dmm.avondroid.clients.ClientListDisplayObject;
import com.dmm.avondroid.database.dao.Client;
import com.dmm.avondroid.database.dao.Order;
import com.dmm.avondroid.database.dao.Product;
import com.dmm.avondroid.products.ProductListDisplayObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waldekd on 2015-07-15.
 */
public class ProductList extends BaseList<ProductListDisplayObject, ProductListDataAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_productlist);

        lvListView = (ListView) findViewById(R.id.lvProductList);

        generateDisplayObjects();

        if (displayObjects != null) {
            adapter = new ProductListDataAdapter(getApplicationContext(), displayObjects);
            lvListView.setAdapter(adapter);
        }

    }

    @Override
    protected void generateDisplayObjects() {
        List<ProductListDisplayObject> displayObjectList = new ArrayList<>();

        List<Product> all_products = Product.getAllProducts(db_helper.db);
        for(Product single_product : all_products){
            ProductListDisplayObject productListDisplayObject = new ProductListDisplayObject();
            productListDisplayObject.setId(single_product.getId());
            productListDisplayObject.setActive(single_product.isActive());
            productListDisplayObject.setName(single_product.getName());
            productListDisplayObject.setPrice(single_product.getPrice());
            displayObjectList.add(productListDisplayObject);
        }

        displayObjects = displayObjectList.toArray(new ProductListDisplayObject[displayObjectList.size()]);


    }
}
