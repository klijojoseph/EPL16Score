package com.lijojoseph.epl16score.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lijojoseph.epl16score.db.tables.TableBall;
import com.lijojoseph.epl16score.fragment.FragmentMainScoreBoard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijo.j on 22-Aug-2016.
 */
public class MiscUtils {

    public static void setReportToMail(TableBall tableBall, FragmentMainScoreBoard.MatchInnings innings, Context context){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String filename= innings.getInningsNumber()+"_date_"
                +(simpleDateFormat.format(EPLConstants.calendar.getTime())+"_match_"+EPLConstants.matchNumber);
        EPLConstants.matchNumber++;
//        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), write(filename));
        Uri path = Uri.fromFile(write(filename,teamTotal(tableBall)));
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent .setType("vnd.android.cursor.dir/email");
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
        ResolveInfo best = null;
        for (final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm") ||
                    info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
        if (best != null)
            emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
        String to[] = {"lijo.j@eteamindia.com","rajeev.k@eteamindia.com"};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Match Report");
        context.startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }

    public static File write(String fname, String fcontent){
        try {

            String fpath = fname+".txt";

            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fcontent);
            bw.close();

            Log.d("Suceess","Sucess");
            return file;

        } catch (IOException e) {
            Log.d("Error","Error");
            e.printStackTrace();
            return null;
        }

    }

    public static String teamTotal(TableBall tableBall){
        String reportDetails = "";
        ArrayList<TableBall.Ball> list = tableBall.populatePlayerTeamData(TableBall.COLUMN_BATTING_TEAM,EPLConstants.innings_01_Batting);
        int totalRuns = 0;
        int totalWickets = 0;
        int totalExtras  = 0;
        int totalOvers   = 0;


        for(TableBall.Ball ball : list){
            totalRuns += ball.getTakenRun();
            totalOvers += ball.getOver();
            totalExtras += ball.getExtra();
            totalWickets += (ball.isWicket()?1:0);
        }

        System.out.println("Total Runs : "+totalRuns+" List Size : "+list.size()+" Column Batting Team : "+EPLConstants.innings_01_Batting);
        reportDetails = EPLConstants.innings_01_Batting+"  \n\r"+
                        "Score : "+(totalRuns+totalExtras)+"/"+totalWickets+"  \n\r"+
                        "Over : "+(list.size()/6.0)+"  \n\r"+
                        "Extras : "+totalExtras;

        return reportDetails;
    }

}
