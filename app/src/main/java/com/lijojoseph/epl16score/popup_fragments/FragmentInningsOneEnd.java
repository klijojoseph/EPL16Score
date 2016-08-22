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

import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.utils.EPLConstants;

/**
 * Created by bibin.b on 8/22/2016.
 */
public class FragmentInningsOneEnd extends DialogFragment {

    private FragmentInningsOneEnd() {
    }
    public static FragmentInningsOneEnd newInstance(){
        FragmentInningsOneEnd fragment = new FragmentInningsOneEnd();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(getResources().getString(R.string.endinnings));
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View root = inflater.inflate(R.layout.popup_innings_one_end, container, false);
//textview for total runs per over :bibin
        final TextView targetrun=(TextView)root.findViewById(R.id.tragetruns);
        final TextView targetover=(TextView)root.findViewById(R.id.tragetover);
        final Button  okButton=(Button)root.findViewById(R.id.inningendok_btn);
        targetrun.setText("Target"+" "+(EPLConstants.innings_01_currentTotalRuns+1)+" "+"runs");
        targetover.setText("From"+" "+EPLConstants.innings_01_currentOver+" "+"Overs");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentInningsOneEnd.this.dismiss();
//add over change fragment
                FragmentOverChange.newInstace().show(getFragmentManager(), null);
            }
        });
        return  root;
    }
}
