package com.lijojoseph.epl16score.popup_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lijojoseph.epl16score.R;

/**
 * Created by bibin.b on 8/20/2016.
 */
public class FragmentNoball extends DialogFragment {
//bibin add popup noball  fragment:bibin
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("No Ball");
        View view = inflater.inflate(R.layout.popup_no_ball, container);
        return view;
//end
    }
}
