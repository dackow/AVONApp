package com.dmm.avondroid;

import android.app.Application;

import com.dmm.avondroid.database.DataBaseHelper;

/**
 * Created by waldekd on 7/11/15.
 */

//This class is used to store all the application-wide variables only !!!!!!
public class GlobalApplication extends Application {
    public DataBaseHelper db_helper;

    public DataBaseHelper getDb_helper() {
        return db_helper;
    }

    public void setDb_helper(DataBaseHelper db_helper) {
        this.db_helper = db_helper;
    }
}
