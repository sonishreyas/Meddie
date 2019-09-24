package com.example.forever.pid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.example.forever.pid.R;

public class splashscreen extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splashscreen.this, dashboard.class);
                splashscreen.this.startActivity(mainIntent);
                splashscreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
