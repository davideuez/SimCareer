package com.example.simcareer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void skip_intro(View v) {
        Intent intent = new Intent(MainActivity.this, Registrazione.class);
        startActivity(intent);
    }

    public void vedi_intro(View v) {
        Intent intent = new Intent(MainActivity.this, Walkthrough_1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
    }

}