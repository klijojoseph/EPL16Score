package com.lijojoseph.epl16score.utils;

/**
 * Created by lijo.j on 19-Aug-2016.
 */
public class EPLConstants {

    public static final int FIRST_INNINGS = 1;
    public static final int SECOND_INNINGS = 2;

    public static String innings_01_Batting ="";
    public static String innings_02_Batting ="";

    public static String innings_01_Bowling ="";
    public static String innings_02_Bowling ="";

    public static String currentBatsMan ="";
    public static String currentBowler ="";

    public static int innings_01_currentOver = 0;
    public static int innings_01_currentBall = 0;

    public static int innings_01_currentTotalRuns = 0;
    public static int innings_01_currentTotalWickets = 0;

    public static int innings_02_currentOver = 0;
    public static int innings_02_currentBall = 0;

    public static int innings_02_currentTotalRuns = 0;
    public static int innings_02_currentTotalWickets = 0;

    public static void clearAll(){

         innings_01_Batting ="";
         innings_02_Batting ="";

         innings_01_Bowling ="";
         innings_02_Bowling ="";

         currentBatsMan ="";
         currentBowler ="";

         innings_01_currentOver = 0;
         innings_01_currentBall = 0;

         innings_01_currentTotalRuns = 0;
         innings_01_currentTotalWickets = 0;

         innings_02_currentOver = 0;
         innings_02_currentBall = 0;

         innings_02_currentTotalRuns = 0;
         innings_02_currentTotalWickets = 0;

    }

}
