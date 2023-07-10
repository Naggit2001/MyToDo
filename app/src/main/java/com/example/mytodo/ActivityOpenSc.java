package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivityOpenSc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_sc);

        // hide  the  action bar  from  the  lunching   screen
        getSupportActionBar().hide();

        //   to  create  flash screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(ActivityOpenSc.this ,MainActivity.class));
                finish();

            }
        },3000);



    }
}