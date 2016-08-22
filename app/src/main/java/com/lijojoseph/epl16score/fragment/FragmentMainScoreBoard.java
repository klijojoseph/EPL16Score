package com.lijojoseph.epl16score.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lijojoseph.epl16score.MainActivity;
import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.popup_fragments.FragmentNoball;
import com.lijojoseph.epl16score.popup_fragments.FragmentOverChange;
import com.lijojoseph.epl16score.popup_fragments.FragmentWicket;
import com.lijojoseph.epl16score.utils.EPLConstants;

import java.io.Serializable;

/**
 * Created by lijo.j on 19-Aug-2016.
 */
public class FragmentMainScoreBoard extends Fragment {

    final public static String INNINGS_TAG = "INNINGS_TAG";
    private TextView runs;
    private TextView wickets;
    private TextView over;
    private TextView balls;
    private  TextView teamName;
    private PopupWindow popupWindow;


    public static FragmentMainScoreBoard newInstance(MatchInnings innings) {
        FragmentMainScoreBoard fragment = new FragmentMainScoreBoard();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INNINGS_TAG, innings);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_score_board, container, false);
        final MatchInnings innings = (MatchInnings) getArguments().getSerializable(INNINGS_TAG);

        teamName = (TextView) root.findViewById(R.id.teamname_txt);
        runs = (TextView) root.findViewById(R.id.totalrun_txt);
        wickets = (TextView) root.findViewById(R.id.totalwkt_txt);
        over = (TextView) root.findViewById(R.id.totalover_txt);
        balls = (TextView) root.findViewById(R.id.totalball_txt);
        popupWindow = new PopupWindow(getContext());

        final Button zeroRun = (Button) root.findViewById(R.id.run0_btn);
        final Button oneRun = (Button) root.findViewById(R.id.run1_btn);
        final Button twoRun = (Button) root.findViewById(R.id.run2_btn);
        final Button fourRun = (Button) root.findViewById(R.id.run4_btn);

        final Button wideButton = (Button) root.findViewById(R.id.wide_btn);
        final Button wicketButton = (Button) root.findViewById(R.id.wicket_btn);
//add phase,noball,net over buttons :bibin
        final Button noballButton = (Button) root.findViewById(R.id.noball_btn);
        final Button phaseButton = (Button) root.findViewById(R.id.phase_btn);
        final Button chanceButton = (Button) root.findViewById(R.id.chance_btn);

//add waring alert sound for phase ball:bibin
        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound);


        zeroRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(innings,0,false,false);
            }
        });
        oneRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateScore(innings,1,false,false);
            }
        });
        twoRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(innings,2,false,false);
            }
        });
        fourRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(innings,4,false,false);
            }
        });

        wideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore(innings,1,false,true);
            }
        });

        wicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateScore(innings,0,true,false);
                FragmentWicket.newInstance().show(getChildFragmentManager(), null);
            }
        });

//Noball button click :Bibin Babu
        noballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateScore(innings,1,false,true);
                FragmentNoball.newInstance(FragmentMainScoreBoard.this).show(getChildFragmentManager(), null);
            }
        });

        chanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EPLConstants.chanceList.contains(EPLConstants.currentBatsMan)){
                    Toast.makeText(getContext(),EPLConstants.currentBatsMan+" is Out ",Toast.LENGTH_SHORT).show();
                    wicketButton.performClick();
                }else{
                    EPLConstants.chanceList.add(EPLConstants.currentBatsMan);
                    updateScore(innings,0,false,false);
                }
            }
        });


//Phase Button click:bibin
        phaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//phase ball warnning:bibin
                mediaPlayer.start();
                Toast.makeText(getContext(), "Phase ball", Toast.LENGTH_SHORT).show();
            }
        });
        FragmentOverChange.newInstace().show(getFragmentManager(), null);
        teamName.setText(innings.getTeam01());
        return root;
    }

    public void updateScore(MatchInnings innings,int runsTaken,boolean wicketTaken,boolean extra) {

        if (innings.getInningsNumber() == EPLConstants.FIRST_INNINGS) {
            EPLConstants.innings_01_currentTotalRuns += runsTaken;
            if(!extra)
                EPLConstants.innings_01_currentBall++;
            if(wicketTaken)
                EPLConstants.innings_01_currentTotalWickets += 1;
            if (EPLConstants.innings_01_currentBall == 6) {
                EPLConstants.innings_01_currentBall = 0;
                EPLConstants.innings_01_currentOver++;
                if(EPLConstants.innings_01_currentOver != innings.getOvers())
                FragmentOverChange.newInstace().show(getFragmentManager(), null);
            }
            runs.setText("" + EPLConstants.innings_01_currentTotalRuns);
            over.setText("" + EPLConstants.innings_01_currentOver);
            balls.setText("" + EPLConstants.innings_01_currentBall);
            wickets.setText("" + EPLConstants.innings_01_currentTotalWickets);
            if(innings.getOvers()== EPLConstants.innings_01_currentOver
                    || EPLConstants.innings_01_currentTotalWickets == innings.getOvers()){
                runs.setText(""+0 );
                over.setText(""+0);
                balls.setText(""+0 );
                wickets.setText(""+0);
                EPLConstants.chanceList.clear();
                Toast.makeText(FragmentMainScoreBoard.this.getContext(),
                        "Innings End ,Target : "+(EPLConstants.innings_01_currentTotalRuns+1),
                        Toast.LENGTH_SHORT).show();

                innings.setInningsNumber(EPLConstants.SECOND_INNINGS);
                innings.setOvers(innings.getOvers());
                innings.setTeam01(EPLConstants.innings_02_Batting);
                innings.setTeam02(EPLConstants.innings_02_Bowling);

                teamName.setText(innings.getTeam01());
                FragmentOverChange.newInstace().show(getFragmentManager(), null);
            }
        } else if (innings.getInningsNumber() == EPLConstants.SECOND_INNINGS) {
            EPLConstants.innings_02_currentTotalRuns += runsTaken;
            if(!extra)
                EPLConstants.innings_02_currentBall++;
            if(wicketTaken)
                EPLConstants.innings_02_currentTotalWickets += 1;
            if (EPLConstants.innings_02_currentBall == 6) {
                EPLConstants.innings_02_currentBall = 0;
                EPLConstants.innings_02_currentOver++;
                if(EPLConstants.innings_02_currentOver != innings.getOvers())
                FragmentOverChange.newInstace().show(getFragmentManager(), null);
            }
            runs.setText("" + EPLConstants.innings_02_currentTotalRuns);
            over.setText("" + EPLConstants.innings_02_currentOver);
            balls.setText("" + EPLConstants.innings_02_currentBall);
            wickets.setText("" + EPLConstants.innings_02_currentTotalWickets);
            if(innings.getOvers()== EPLConstants.innings_02_currentOver
                    || EPLConstants.innings_02_currentTotalRuns > EPLConstants.innings_01_currentTotalRuns
                    || EPLConstants.innings_02_currentTotalWickets == innings.getOvers()){
                runs.setText("" );
                over.setText("");
                balls.setText("" );
                wickets.setText("");
                String winner="";
                if(EPLConstants.innings_01_currentTotalRuns > EPLConstants.innings_02_currentTotalRuns)
                    winner = EPLConstants.innings_01_Batting;
                else
                    winner = EPLConstants.innings_02_Batting;

                Toast.makeText(FragmentMainScoreBoard.this.getContext(),
                        "Match End ,Winner : "+winner,
                        Toast.LENGTH_SHORT).show();
                EPLConstants.clearAll();
                ((MainActivity)getActivity()).replaceFragment(FragmentGameSelector.newInstance());
            }
        }
    }

    public static class MatchInnings implements Serializable {
        int inningsNumber;
        String team01;
        String team02;
        int overs;

        public int getInningsNumber() {
            return inningsNumber;
        }

        public void setInningsNumber(int inningsNumber) {
            this.inningsNumber = inningsNumber;
        }

        public String getTeam01() {
            return team01;
        }

        public void setTeam01(String team01) {
            this.team01 = team01;
        }

        public String getTeam02() {
            return team02;
        }

        public void setTeam02(String team02) {
            this.team02 = team02;
        }

        public int getOvers() {
            return overs;
        }

        public void setOvers(int overs) {
            this.overs = overs;
        }
    }
}
