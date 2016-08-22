package com.lijojoseph.epl16score.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import com.lijojoseph.epl16score.db.tables.TableBall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

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

    public static void exportDB(Context context) {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + context.getPackageName()
                        + "//databases//" + DATABASE_NAME;
                String backupDBPath  = DATABASE_NAME;
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                FileInputStream is = new FileInputStream(currentDB);
                FileOutputStream os = new FileOutputStream(backupDB);
                FileChannel src = is.getChannel();
                FileChannel dst = os.getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                is.close();
                os.close();
                Toast.makeText(context, backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}


