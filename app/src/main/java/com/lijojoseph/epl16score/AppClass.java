package com.lijojoseph.epl16score;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lijojoseph.epl16score.db.DbHelperClass;

/**
 * Created by lijo.j on 22-Aug-2016.
 */
public class AppClass extends Application {

    private static SQLiteOpenHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DbHelperClass(getApplicationContext());
    }

    public static SQLiteDatabase getDB() {
        return dbHelper.getWritableDatabase();
    }
}
