package com.example.tanvir.firebase_cloud_messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonRegister;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        buttonRegister =(Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendTokenToServer();
            }
        });



        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));

    }

    private void sendTokenToServer(){
        String email = editTextEmail.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter your email please", Toast.LENGTH_LONG).show();
    }else{
            if(SharedPrefManager.getInstance(this).getToken() !=null){
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"",
                         new Response.Listener<String>(){
                             @Override
                             public void onResponse(String response){

                             }
                         },
                         new Response.ErrorListener(){
                             @Override
                             public void onErrorResponse(VolleyError error){

                              }
                         });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }else{
                Toast.makeText(this, "Token not generated",Toast.LENGTH_LONG).show();
            }
        }
}
}