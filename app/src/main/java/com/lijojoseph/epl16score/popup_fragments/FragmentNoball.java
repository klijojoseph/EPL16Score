package com.lijojoseph.epl16score.popup_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.fragment.FragmentMainScoreBoard;
import com.lijojoseph.epl16score.utils.EPLConstants;

/**
 * Created by bibin.b on 8/20/2016.
 */
public class FragmentNoball extends DialogFragment {

    private FragmentMainScoreBoard mainScoreCard;

    private FragmentNoball(){

    }

    public static FragmentNoball newInstance(FragmentMainScoreBoard mainScoreCard){
        FragmentNoball fragment = new FragmentNoball();
        fragment.setMainScoreCard(mainScoreCard);

        return fragment;
    }

    public FragmentMainScoreBoard getMainScoreCard() {
        return mainScoreCard;
    }

    public void setMainScoreCard(FragmentMainScoreBoard mainScoreCard) {
        this.mainScoreCard = mainScoreCard;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("No Ball");
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View root = inflater.inflate(R.layout.popup_no_ball, container);

        final Button zeroRun = (Button) root.findViewById(R.id.noball_run0);
        final Button oneRun = (Button) root.findViewById(R.id.noball_run1);
        final Button twoRun = (Button) root.findViewById(R.id.noball_run2);
        final Button fourRun = (Button) root.findViewById(R.id.noball_run4);

        zeroRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainScoreCard().updateScore((FragmentMainScoreBoard.MatchInnings)
                        FragmentNoball.this.getParentFragment().getArguments().getSerializable(FragmentMainScoreBoard.INNINGS_TAG),
                        0,false,true);
                getMainScoreCard().insertExtraScoreIntoDB(getMainScoreCard().getInnings(),0,1,false,true,false,false,false);
                FragmentNoball.this.dismiss();
            }
        });

        oneRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainScoreCard().updateScore((FragmentMainScoreBoard.MatchInnings)
                                FragmentNoball.this.getParentFragment().getArguments().getSerializable(FragmentMainScoreBoard.INNINGS_TAG),
                                1,false,true);
                getMainScoreCard().insertExtraScoreIntoDB(getMainScoreCard().getInnings(),1,1,false,true,false,false,false);
                FragmentNoball.this.dismiss();
            }
        });

        twoRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainScoreCard().updateScore((FragmentMainScoreBoard.MatchInnings)
                                FragmentNoball.this.getParentFragment().getArguments().getSerializable(FragmentMainScoreBoard.INNINGS_TAG),
                                2,false,true);
                getMainScoreCard().insertExtraScoreIntoDB(getMainScoreCard().getInnings(),2,1,false,true,false,false,false);
                FragmentNoball.this.dismiss();
            }
        });

        fourRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainScoreCard().updateScore((FragmentMainScoreBoard.MatchInnings)
                                FragmentNoball.this.getParentFragment().getArguments().getSerializable(FragmentMainScoreBoard.INNINGS_TAG),
                                4,false,true);
                getMainScoreCard().insertExtraScoreIntoDB(getMainScoreCard().getInnings(),3,1,false,true,false,false,false);
                FragmentNoball.this.dismiss();
            }
        });


        return root;

    }
}
