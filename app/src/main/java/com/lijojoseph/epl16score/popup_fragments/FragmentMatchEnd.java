package com.lijojoseph.epl16score.popup_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lijojoseph.epl16score.MainActivity;
import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.fragment.FragmentGameSelector;
import com.lijojoseph.epl16score.utils.EPLConstants;

/**
 * Created by bibin.b on 8/22/2016.
 */
public class FragmentMatchEnd extends  DialogFragment {

    private FragmentMatchEnd() {

    }

    public static FragmentMatchEnd newInstance(){
        FragmentMatchEnd fragment = new FragmentMatchEnd();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.matchend));
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View root = inflater.inflate(R.layout.popup_match_end, container, false);
 final Button okButton=(Button)root.findViewById(R.id.endmatchok_btn);
        final TextView winteam=(TextView)root.findViewById(R.id.winning_team);
        final TextView team1=(TextView)root.findViewById(R.id.team1run_over);
        final TextView team2=(TextView)root.findViewById(R.id.team2run_over);
        String winner="";
        if(EPLConstants.innings_01_currentTotalRuns > EPLConstants.innings_02_currentTotalRuns) {
            winner = EPLConstants.innings_01_Batting;
        }
        else{
            winner = EPLConstants.innings_02_Batting;
        }
           winteam.setText(winner+" "+"WIN");

        team1.setText(EPLConstants.innings_01_Batting+"-"+EPLConstants.innings_01_currentTotalRuns+"/"+EPLConstants.innings_01_currentTotalWickets);
        team2.setText(EPLConstants.innings_02_Batting+"-"+EPLConstants.innings_02_currentTotalRuns+"/"+EPLConstants.innings_02_currentTotalWickets);
        okButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentMatchEnd.this.dismiss();
        ((MainActivity)getActivity()).replaceFragment(FragmentGameSelector.newInstance());

    }
});

        return root;

    }


}
