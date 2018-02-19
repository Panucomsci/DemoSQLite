package com.demosql.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demosql.demosqlite.MyApplications;

import java.security.PrivateKey;
import java.util.logging.StreamHandler;

/**
 * Created by lenovoPC on 2/19/2018.
 */

public class DBValues {

    private ValuesHelper myHelper;
    private SQLiteDatabase myDatabase;
    private static final String TAG = DBValues.class.getSimpleName();

    public DBValues(Context context) {
        myHelper = new ValuesHelper(context);
        myDatabase = myHelper.getWritableDatabase();
    }

    public long InsertNewMember(String fname ,String lname,String old) {
        long resultRow = 0;
        ContentValues cv = new ContentValues();
        try{
            cv.put(myHelper.COLUM_F_NAME, fname);
            cv.put(myHelper.COLUM_L_NAME, lname);
            cv.put(myHelper.COLUM_OLD, old);
            resultRow = myDatabase.insert(myHelper.TABLE_NAME_PROFILE,null,cv);
        }catch (SQLException e){
            Log.e(TAG, "Error InsertNewMember: " + e.getMessage() );
        }finally {
            cv.clear();
        }
        return resultRow;
    }

    private static class ValuesHelper extends SQLiteOpenHelper{

        private static final String TAG = "Valuehelper";
        private static final String DATABASE_NAME = "demo_db";
        private static final int CURRENT_VERSION = 8;

        public static final String TABLE_NAME_PROFILE = "my_profile";

        public static final String COLUM_ID = "id";
        public static final String COLUM_F_NAME = "f_name";
        public static final String COLUM_L_NAME = "l_name";
        public static final String COLUM_OLD = "old";


        private static final String CREATE_TABLE_PROFILE = "CREATE TABLE "+ TABLE_NAME_PROFILE+ " ("+
                COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUM_F_NAME + " TEXT(200), " +
                COLUM_L_NAME + " TEXT(200),"+ COLUM_OLD + " TEXT );";



        public ValuesHelper(Context context) {
            super(context, DATABASE_NAME, null, CURRENT_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG , "onCreate");
            try{
                db.execSQL(CREATE_TABLE_PROFILE);
            }catch (SQLException e){
                Log.e(TAG,"Error Create table : "+ e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "onUpgrade: oldVersion :" + oldVersion+ "newVersion :"+ newVersion);
        try{
            if (oldVersion <8){
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFILE);
                db.execSQL(CREATE_TABLE_PROFILE);
            }
        }catch (SQLException e){
            Log.e(TAG, "Error onUpgrade: "+ e.getMessage() );
        }
        }
    }

}
