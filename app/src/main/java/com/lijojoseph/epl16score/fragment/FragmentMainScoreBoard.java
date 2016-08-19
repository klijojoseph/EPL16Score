package com.lijojoseph.epl16score.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lijojoseph.epl16score.R;

import java.io.Serializable;

/**
 * Created by lijo.j on 19-Aug-2016.
 */
public class FragmentMainScoreBoard extends Fragment {

    final private static  String INNINGS_TAG = "INNINGS_TAG";

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
        MatchInnings innings = (MatchInnings) getArguments().getSerializable(INNINGS_TAG);
        final TextView teamName = (TextView) root.findViewById(R.id.teamname_txt);
        teamName.setText(innings.getTeam01());
        return root;
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
