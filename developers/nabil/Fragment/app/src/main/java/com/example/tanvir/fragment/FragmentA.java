package com.example.tanvir.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.zip.Inflater;

public class FragmentA extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    Communicator communicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
        return inflater.inflate(R.layout.fragment_a,container,false);
    }

    public void onActivityCreate(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        communicator= (Communicator) getActivity();
        list= (ListView) getActivity().findViewById(R.id.listView);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(getActivity(),R.array.titles,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> adapterView, View view. int i, long 1){
        communicator.respond(i);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}