package com.lijojoseph.epl16score.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lijojoseph.epl16score.MainActivity;
import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.utils.EPLConstants;

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
        final Button startGame = (Button) root.findViewById(R.id.submit_btn);

        final ArrayAdapter<String> battingTeamAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getTeamList());
        battingTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        battingTeam.setAdapter(battingTeamAdapter);

        final ArrayAdapter<String> bowlingTeamAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getTeamList());
        bowlingTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bowlingTeam.setAdapter(bowlingTeamAdapter);

        ArrayList<String> overList = new ArrayList<>();
        for(int i=5;i<=12;i++)
            overList.add(""+i);
        ArrayAdapter<String> overAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, overList);
        overAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        over.setAdapter(overAdapter);

        battingTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(battingTeam.getSelectedItem().toString()!=null) {
                    bowlingTeamAdapter.clear();
                    bowlingTeamAdapter.addAll(getTeamList());
                    bowlingTeamAdapter.remove(battingTeam.getSelectedItem().toString());
                    bowlingTeamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bowlingTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(bowlingTeam.getSelectedItem().toString()!=null) {
                    battingTeamAdapter.clear();
                    battingTeamAdapter.addAll(getTeamList());
                    battingTeamAdapter.remove(bowlingTeam.getSelectedItem().toString());
                    battingTeamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EPLConstants.innings_01_Batting = battingTeam.getSelectedItem().toString();
                EPLConstants.innings_02_Batting = bowlingTeam.getSelectedItem().toString();
                EPLConstants.innings_01_Bowling = bowlingTeam.getSelectedItem().toString();
                EPLConstants.innings_02_Bowling = battingTeam.getSelectedItem().toString();

             //   EPLConstants.clearAll();
                FragmentMainScoreBoard.MatchInnings innings = new FragmentMainScoreBoard.MatchInnings();
                innings.setInningsNumber(EPLConstants.FIRST_INNINGS);
                innings.setOvers(Integer.parseInt(over.getSelectedItem().toString()));
                innings.setTeam01(battingTeam.getSelectedItem().toString());
                innings.setTeam02(bowlingTeam.getSelectedItem().toString());
                ((MainActivity)getActivity()).replaceFragment(FragmentMainScoreBoard.newInstance(innings));

            }
        });


        return root;
    }

    public ArrayList<String> getTeamList(){
        final ArrayList<String> teamList = new ArrayList<>();
        teamList.add(getResources().getString(R.string.team_a));
        teamList.add(getResources().getString(R.string.team_b));
        teamList.add(getResources().getString(R.string.team_c));
        teamList.add(getResources().getString(R.string.team_d));
        return teamList;
    }
}
