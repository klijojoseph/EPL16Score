package com.lijojoseph.epl16score.db;

import android.database.sqlite.SQLiteDatabase;

import com.lijojoseph.epl16score.AppClass;


/**
 * Created by Lijo Joseph & Shagin TV on 19-Mar-2016.
 */
public  abstract class DbTable {

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB() {
        if (db == null)
            db = AppClass.getDB();
        return db;
    }

    public static void beingExecute(){
        getDB().beginTransactionNonExclusive();
    }

    public static void stopExecute(){
        getDB().setTransactionSuccessful();
        getDB().endTransaction();
    }




}
