package com.demosql.demosqlite;


import android.app.Application;
import android.content.Context;

import com.demosql.database.DBValues;

/**
 * Created by lenovoPC on 2/19/2018.
 */

public class MyApplications extends Application {

    private static MyApplications sInstance;
    final static String TAG = MyApplications.class.getSimpleName();
    private static DBValues mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase = new DBValues(this);
    }


    public synchronized static DBValues getWritableDatabase() {
        if(mDatabase == null){
            mDatabase = new DBValues(getAppContext());
        }
        return mDatabase;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
