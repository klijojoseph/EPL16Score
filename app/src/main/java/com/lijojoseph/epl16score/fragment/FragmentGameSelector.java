package com.lijojoseph.epl16score.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lijojoseph.epl16score.R;

import java.util.ArrayList;

/**
 * Created by lijo.j on 19-Aug-2016.
 */
public class FragmentGameSelector extends Fragment {

    public static FragmentGameSelector newInstance(){
        FragmentGameSelector fragment = new FragmentGameSelector();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game_selector,container,false);
        final Spinner battingTeam = (Spinner) root.findViewById(R.id.team1_spinner);
        final Spinner bowlingTeam = (Spinner) root.findViewById(R.id.team2_spinner);
        final Spinner over = (Spinner) root.findViewById(R.id.over_spinner);

        ArrayList<String> teamList = new ArrayList<>();
        teamList.add(getResources().getString(R.string.team_a));
        teamList.add(getResources().getString(R.string.team_b));
        teamList.add(getResources().getString(R.string.team_c));
        teamList.add(getResources().getString(R.string.team_d));


        ArrayAdapter<String> battingTeamAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, teamList);
        battingTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        battingTeam.setAdapter(battingTeamAdapter);

        ArrayAdapter<String> bowlingTeamAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, teamList);
        bowlingTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bowlingTeam.setAdapter(bowlingTeamAdapter);

        ArrayList<String> overList = new ArrayList<>();
        for(int i=5;i<=12;i++)
            overList.add(""+i);
        ArrayAdapter<String> overAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, overList);
        overAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        over.setAdapter(overAdapter);
        return root;
    }
}
