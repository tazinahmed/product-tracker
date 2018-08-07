package com.example.tanvir.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class FragmentB extends Fragment {


    public View onCreateView(LayoutInflater, ViewGroup container, Bundle SavedInstance, Inflater inflater){
        View view=inflater.inflate(R.layout.fragment_b,container,false);

        return view;
    }

    public void changeData(int i)
    {
        Resources res=getResources();
        String[] descriptions=res.getStringArray(R.array.descriptions);
        text.setText(descriptions[i]);
    }

}
