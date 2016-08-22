package com.lijojoseph.epl16score.popup_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.utils.EPLConstants;

/**
 * Created by bibin.b on 8/20/2016.
 */
public class FragmentWicket extends DialogFragment {

    private FragmentWicket() {

    }

    public static FragmentWicket newInstance(){
        FragmentWicket fragment = new FragmentWicket();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.wicket));
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View root = inflater.inflate(R.layout.popup_wicket, container, false);

        final Button okButton = (Button) root.findViewById(R.id.wkt_ok);
        final AutoCompleteTextView bowlerAutoTxt = (AutoCompleteTextView) root.findViewById(R.id.wkt_poptxt);
        final String[] name = getResources().getStringArray(R.array.playerslist);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, name);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EPLConstants.currentBatsMan = bowlerAutoTxt.getText().toString();
                FragmentWicket.this.dismiss();
            }
        });
        bowlerAutoTxt.setThreshold(1);
        bowlerAutoTxt.setAdapter(adapter);

        return root;

    }
}