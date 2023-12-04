package com.android.findmyvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dose_option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose_option);
    }
    public void dose_1(View view){
        Intent intent = new Intent(dose_option.this , MainActivity.class );
        startActivity(intent);
    }

    public void dose_2(View view){
        Intent intent = new Intent(dose_option.this , MainActivity2.class );
        startActivity(intent);
    }

    private static long back_pressed;
    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}