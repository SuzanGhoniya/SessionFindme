package com.android.findmyvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class start_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Handler handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(start_screen.this, dose_option.class);
            startActivity(intent);
        },800);
    }
}