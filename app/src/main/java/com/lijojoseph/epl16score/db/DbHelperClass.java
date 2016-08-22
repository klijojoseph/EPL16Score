package com.lijojoseph.epl16score.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lijojoseph.epl16score.db.tables.TableBall;

/**
 * Created by Lijo Joseph & Shagin TV on 17-Mar-2016.
 */
public class DbHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "epl2016.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        TableBall.OnTableCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


