package com.lijojoseph.epl16score.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lijojoseph.epl16score.db.DbTable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lijo.j on 22-Aug-2016.
 */
public class TableBall extends DbTable {

    public static final String TABLE_NAME = "TABLE_BALL";
    public static final String COLUMN_ID = "BALL_ID";
    public static final String COLUMN_OVER = "BALL_OVER";
    public static final String COLUMN_BALLNO = "BALL_BALLNO";
    public static final String COLUMN_INNGSNO = "BALL_INNGSNO";
    public static final String COLUMN_BOWLER_NAME = "BALL_BOWLER_NAME";
    public static final String COLUMN_BATSMAN_NAME = "BALL_BATSMAN_NAME";
    public static final String COLUMN_WICKET = "BALL_WICKET";
    public static final String COLUMN_NOBALL = "BALL_NOBALL";
    public static final String COLUMN_PHASE = "BALL_PHASE";
    public static final String COLUMN_CHANCE = "BALL_CHANCE";
    public static final String COLUMN_WIDE = "BALL_WIDE";
    public static final String COLUMN_TAKENRUN = "BALL_TAKENRUN";
    public static final String COLUMN_EXTRA = "BALL_EXTRA";
    public static final String COLUMN_BOWLING_TEAM = "BALL_BOWLING_TEAM";
    public static final String COLUMN_BATTING_TEAM = "BALL_BATTING_TEAM";
    public static final String COLUMN_GAME_DATE = "BALL_GAME_DATE";

    public static void OnTableCreate(SQLiteDatabase sqLiteDatabase) {
        final String createQuery = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "( " + COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_OVER + " INTEGER,"
                + COLUMN_BALLNO + " INTEGER,"
                + COLUMN_INNGSNO + " INTEGER,"
                + COLUMN_BOWLER_NAME + " TEXT ,"
                + COLUMN_BATSMAN_NAME + " TEXT ,"
                + COLUMN_WICKET + " INTEGER,"
                + COLUMN_NOBALL + " INTEGER,"
                + COLUMN_PHASE + " INTEGER,"
                + COLUMN_CHANCE + " INTEGER,"
                + COLUMN_WIDE + " INTEGER,"
                + COLUMN_TAKENRUN + " INTEGER,"
                + COLUMN_EXTRA + " INTEGER,"
                + COLUMN_BOWLING_TEAM + " TEXT ,"
                + COLUMN_BATTING_TEAM + " TEXT ,"
                + COLUMN_GAME_DATE + " TEXT "
                +  ");";

        sqLiteDatabase.execSQL(createQuery);
    }

    public ArrayList<Ball> populateData(){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor      = getDB().rawQuery(selectQuery, null);
        ArrayList<Ball> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
               Ball ball = new Ball();
                ball.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                ball.setOver(cursor.getInt(cursor.getColumnIndex(COLUMN_OVER)));
                ball.setInngsNo(cursor.getInt(cursor.getColumnIndex(COLUMN_INNGSNO)));
                ball.setBallNo(cursor.getInt(cursor.getColumnIndex(COLUMN_BALLNO)));
                ball.setTakenRun(cursor.getInt(cursor.getColumnIndex(COLUMN_TAKENRUN)));
                ball.setExtra(cursor.getInt(cursor.getColumnIndex(COLUMN_EXTRA)));

                ball.setWicket((cursor.getInt(cursor.getColumnIndex(COLUMN_WICKET)))==1?true:false);
                ball.setNOBall((cursor.getInt(cursor.getColumnIndex(COLUMN_NOBALL)))==1?true:false);
                ball.setPhase((cursor.getInt(cursor.getColumnIndex(COLUMN_PHASE)))==1?true:false);
                ball.setChance((cursor.getInt(cursor.getColumnIndex(COLUMN_CHANCE)))==1?true:false);
                ball.setWide((cursor.getInt(cursor.getColumnIndex(COLUMN_WIDE)))==1?true:false);

                ball.setBowler(cursor.getString(cursor.getColumnIndex(COLUMN_BOWLER_NAME)));
                ball.setBatsman(cursor.getString(cursor.getColumnIndex(COLUMN_BATSMAN_NAME)));
                ball.setBowlerTeam(cursor.getString(cursor.getColumnIndex(COLUMN_BOWLING_TEAM)));
                ball.setBatsmenTeam(cursor.getString(cursor.getColumnIndex(COLUMN_BATTING_TEAM)));
                ball.setGameDtae(cursor.getString(cursor.getColumnIndex(COLUMN_GAME_DATE)));

                list.add(ball);
            } while (cursor.moveToNext());
        }
        System.out.println();
        cursor.close();
        return list;
    }

public void insertIntoDB(Ball ball){
    beingExecute();

    ContentValues values = new ContentValues();
    values.put(COLUMN_OVER, ball.getOver());
    values.put(COLUMN_INNGSNO, ball.getInngsNo());
    values.put(COLUMN_BALLNO, ball.getBallNo());
    values.put(COLUMN_TAKENRUN, ball.getTakenRun());
    values.put(COLUMN_EXTRA, ball.getExtra());
    values.put(COLUMN_WICKET, (ball.isWicket())?1:0);
    values.put(COLUMN_NOBALL, (ball.isNOBall())?1:0);
    values.put(COLUMN_PHASE, (ball.isPhase())?1:0);
    values.put(COLUMN_CHANCE, (ball.isChance())?1:0);
    values.put(COLUMN_WIDE, (ball.isWide())?1:0);
    values.put(COLUMN_BOWLER_NAME, ball.getBowler());
    values.put(COLUMN_BATSMAN_NAME, ball.getBatsman());
    values.put(COLUMN_BOWLING_TEAM, ball.getBowlerTeam());
    values.put(COLUMN_BATSMAN_NAME, ball.getBatsmenTeam());
    values.put(COLUMN_GAME_DATE, ball.getGameDtae());

    getDB().insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    stopExecute();
}

    public static class Ball{
        private int id,over,ballNo,takenRun,extra,inngsNo;
        private boolean isWicket, isNOBall, isPhase, isChance,isWide;
        private String bowler,batsman,gameDtae,bowlerTeam,batsmenTeam;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOver() {
            return over;
        }

        public int getInngsNo() {
            return inngsNo;
        }

        public void setInngsNo(int inngsNo) {
            this.inngsNo = inngsNo;
        }

        public void setOver(int over) {
            this.over = over;
        }

        public int getBallNo() {
            return ballNo;
        }

        public void setBallNo(int ballNo) {
            this.ballNo = ballNo;
        }

        public int getTakenRun() {
            return takenRun;
        }

        public void setTakenRun(int takenRun) {
            this.takenRun = takenRun;
        }

        public int getExtra() {
            return extra;
        }

        public void setExtra(int extra) {
            this.extra = extra;
        }

        public boolean isWicket() {
            return isWicket;
        }

        public void setWicket(boolean wicket) {
            isWicket = wicket;
        }

        public boolean isNOBall() {
            return isNOBall;
        }

        public void setNOBall(boolean NOBall) {
            isNOBall = NOBall;
        }

        public boolean isPhase() {
            return isPhase;
        }

        public void setPhase(boolean phase) {
            isPhase = phase;
        }

        public boolean isChance() {
            return isChance;
        }

        public void setChance(boolean chance) {
            isChance = chance;
        }

        public boolean isWide() {
            return isWide;
        }

        public void setWide(boolean wide) {
            isWide = wide;
        }

        public String getBowler() {
            return bowler;
        }

        public void setBowler(String bowler) {
            this.bowler = bowler;
        }

        public String getBatsman() {
            return batsman;
        }

        public void setBatsman(String batsman) {
            this.batsman = batsman;
        }

        public String getGameDtae() {
            return gameDtae;
        }

        public void setGameDtae(String gameDtae) {
            this.gameDtae = gameDtae;
        }

        public String getBowlerTeam() {
            return bowlerTeam;
        }

        public void setBowlerTeam(String bowlerTeam) {
            this.bowlerTeam = bowlerTeam;
        }

        public String getBatsmenTeam() {
            return batsmenTeam;
        }

        public void setBatsmenTeam(String batsmenTeam) {
            this.batsmenTeam = batsmenTeam;
        }
    }
}
