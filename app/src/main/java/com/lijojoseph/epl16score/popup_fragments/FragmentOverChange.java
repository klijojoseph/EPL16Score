package com.lijojoseph.epl16score.popup_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.lijojoseph.epl16score.R;
import com.lijojoseph.epl16score.utils.EPLConstants;

/**
 * Created by bibin.b on 8/20/2016.
 */
public class FragmentOverChange extends DialogFragment {

    private FragmentOverChange(){

    }

    public static FragmentOverChange newInstace(){
        FragmentOverChange fragment = new FragmentOverChange();

        return fragment;
    }

    //Bibin add auto complete textview in popup
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setTitle("Over Change");

        final View root = inflater.inflate(R.layout.popup_over_change, container);
        final InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        final AutoCompleteTextView bowlerAutoTxt=(AutoCompleteTextView)root.findViewById(R.id.bowchange_poptxt);
        final AutoCompleteTextView batmanAutoTxt=(AutoCompleteTextView)root.findViewById(R.id.batchange_poptxt);
        final Button okButton = (Button)root.findViewById(R.id.change_okbtn);
        final String[] name=getResources().getStringArray(R.array.playerslist);
//add adapter for autocomplete :bibin
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,name);
        bowlerAutoTxt.setThreshold(1);
        batmanAutoTxt.setThreshold(1);
        bowlerAutoTxt.setAdapter(adapter);
        batmanAutoTxt.setAdapter(adapter);
        batmanAutoTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
                EPLConstants.currentBatsMan = batmanAutoTxt.getText().toString();
            }
        });
        bowlerAutoTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
                EPLConstants.currentBowler = bowlerAutoTxt.getText().toString();
            }
        });


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(batmanAutoTxt.getText())&&!TextUtils.isEmpty(bowlerAutoTxt.getText())){
                    FragmentOverChange.this.dismiss();
                }
            }
        });
        return root;
//end
    }
}