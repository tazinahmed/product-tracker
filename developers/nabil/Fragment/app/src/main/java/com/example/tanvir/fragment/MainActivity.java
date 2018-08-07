package com.example.tanvir.fragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public void respond(int i){
        android.app.FragmentManager manager=getFragmentManager();
        FragmentB f2=(FragmentB) manager.findFragmentById(R.id.fragment2);
        f2.changeData(i);

    }
}
