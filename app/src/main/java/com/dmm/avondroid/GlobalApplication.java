package com.dmm.avondroid;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dmm.avondroid.database.DataBaseHelper;

/**
 * Created by waldekd on 7/11/15.
 */

//This class is used to store all the application-wide variables only !!!!!!
public class GlobalApplication extends Application {
    protected DataBaseHelper db_helper;
    protected Boolean isDebugMode = null;

    public DataBaseHelper getDb_helper() {
        return db_helper;
    }

    public void setDb_helper(DataBaseHelper db_helper) {
        this.db_helper = db_helper;
    }

    public boolean isDebugMode(){
        if(isDebugMode == null){
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                int flags = packageInfo.applicationInfo.flags;
                isDebugMode = (flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                    isDebugMode = false;
            }

        }
        return isDebugMode.booleanValue();
    }
}
