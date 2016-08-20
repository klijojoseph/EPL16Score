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
public class FragmentOverChange extends DialogFragment {

    //Bibin add auto complete textview in popup
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle("Over Change");
        View view = inflater.inflate(R.layout.popup_over_change, container);
        final AutoCompleteTextView bowlerAutoTxt=(AutoCompleteTextView)view.findViewById(R.id.bowchange_poptxt);
        final AutoCompleteTextView batmanAutoTxt=(AutoCompleteTextView)view.findViewById(R.id.batchange_poptxt);
        final String[] name=getResources().getStringArray(R.array.playerslist);
//add adapter for autocomplete :bibin
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,name);
        bowlerAutoTxt.setThreshold(1);
        batmanAutoTxt.setThreshold(1);
        bowlerAutoTxt.setAdapter(adapter);
        batmanAutoTxt.setAdapter(adapter);
        return view;
//end
    }
}