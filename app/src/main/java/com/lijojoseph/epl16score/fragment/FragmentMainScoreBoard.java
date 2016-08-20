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

    final private static  String INNINGS_TAG = "INNINGS_TAG";
    private TextView runs;
    private TextView wickets;
    private TextView over;
    private TextView balls;
    private PopupWindow popupWindow;


    public static FragmentMainScoreBoard newInstance(MatchInnings innings){
        FragmentMainScoreBoard fragment = new FragmentMainScoreBoard();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INNINGS_TAG,innings);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_score_board,container,false);
        final MatchInnings innings = (MatchInnings) getArguments().getSerializable(INNINGS_TAG);

        final TextView teamName = (TextView) root.findViewById(R.id.teamname_txt);
         runs= (TextView) root.findViewById(R.id.totalrun_txt);
         wickets= (TextView) root.findViewById(R.id.totalwkt_txt);
         over= (TextView) root.findViewById(R.id.totalover_txt);
         balls= (TextView) root.findViewById(R.id.totalball_txt);
        popupWindow = new PopupWindow(getContext());

        final Button zeroRun = (Button) root.findViewById(R.id.run0_btn);
        final Button oneRun = (Button) root.findViewById(R.id.run1_btn);
        final Button twoRun = (Button) root.findViewById(R.id.run2_btn);
        final Button fourRun = (Button) root.findViewById(R.id.run4_btn);

        final Button wideButton = (Button) root.findViewById(R.id.wide_btn);
        final Button wicketButton = (Button) root.findViewById(R.id.wicket_btn);
//add phase,noball,net over buttons :bibin
        final Button noballButton=(Button)root.findViewById(R.id.noball_btn) ;
        final Button nextoverButton=(Button)root.findViewById(R.id.overchange_btn) ;
        final Button phaseButton=(Button)root.findViewById(R.id.phase_btn) ;

//add waring alert sound for phase ball:bibin
       final MediaPlayer mediaPlayer=MediaPlayer.create(getContext(),R.raw.sound);


        zeroRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){
                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });
        oneRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){
                    EPLConstants.innings_01_currentTotalRuns+=1;
                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentTotalRuns+=1;
                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });
        twoRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){
                    EPLConstants.innings_01_currentTotalRuns+=2;
                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentTotalRuns+=2;
                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });
        fourRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){
                    EPLConstants.innings_01_currentTotalRuns+=4;
                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentTotalRuns+=4;
                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });

        wideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){
                    EPLConstants.innings_01_currentTotalRuns+=1;
//                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentTotalRuns+=1;
//                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });

        wicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//add wicket popup :bibin
//                android.support.v4.app.FragmentManager wicketfragmentmgr=getFragmentManager();
//                FragmentWicket fragmentWicket=new FragmentWicket();
                FragmentWicket.newInstance().show(getChildFragmentManager(),null);


 //end
                if(innings.getInningsNumber()== EPLConstants.FIRST_INNINGS){

                    EPLConstants.innings_01_currentTotalWickets+=1;
                    EPLConstants.innings_01_currentBall++;
                    if(EPLConstants.innings_01_currentBall==6){
                        EPLConstants.innings_01_currentBall =0;
                        EPLConstants.innings_01_currentOver++;
                    }
                }else if(innings.getInningsNumber()== EPLConstants.SECOND_INNINGS){
                    EPLConstants.innings_02_currentTotalWickets+=1;
                    EPLConstants.innings_02_currentBall++;
                    if(EPLConstants.innings_02_currentBall==6){
                        EPLConstants.innings_02_currentBall =0;
                        EPLConstants.innings_02_currentOver++;
                    }
                }
                updateScore(innings.getInningsNumber());
            }
        });

//Noball button click :Bibin Babu
        noballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager noballfragmentmgr=getFragmentManager();
                FragmentNoball fragmentNoball=new FragmentNoball();
                fragmentNoball.show(noballfragmentmgr,null);
            }
        });


//Create new Button Next Over and click :Bibin
        nextoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                FragmentOverChange fragmentOverChange=new FragmentOverChange();
                fragmentOverChange.show(fragmentManager,null);
            }
        });

//Phase Button click:bibin
        phaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//phase ball warnning:bibin
                mediaPlayer.start();
                Toast.makeText(getContext(),"Phase ball",Toast.LENGTH_SHORT).show();
            }
        });
//end
        teamName.setText(innings.getTeam01());
        return root;
    }

    public void updateScore(int innings){
        if(innings== EPLConstants.FIRST_INNINGS){
            runs.setText(""+EPLConstants.innings_01_currentTotalRuns);
            over.setText(""+EPLConstants.innings_01_currentOver);
            balls.setText(""+EPLConstants.innings_01_currentBall);
            wickets.setText(""+EPLConstants.innings_01_currentTotalWickets);
        }else if(innings== EPLConstants.SECOND_INNINGS){
            runs.setText(""+EPLConstants.innings_02_currentTotalRuns);
            over.setText(""+EPLConstants.innings_02_currentOver);
            balls.setText(""+EPLConstants.innings_02_currentBall);
            wickets.setText(""+EPLConstants.innings_02_currentTotalWickets);
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
