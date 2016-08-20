package com.lijojoseph.epl16score.popup_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.lijojoseph.epl16score.R;

/**
 * Created by bibin.b on 8/20/2016.
 */
public class FragmentWicket extends DialogFragment {
//add popup wicket fragment:bibin
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle("Wicket");
        View view = inflater.inflate(R.layout.popup_wicket, container);
//set Autocomplete textview
        String[] name=getResources().getStringArray(R.array.playerslist);
       final AutoCompleteTextView bowlerAutoTxt=(AutoCompleteTextView)view.findViewById(R.id.wkt_poptxt);
       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,name);
        bowlerAutoTxt.setThreshold(1);
        bowlerAutoTxt.setAdapter(adapter);
        return view;
//end
    }
}