package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Walkthrough_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkthrough_1);
    }

    public void next(View v) {
        Intent intent = new Intent(Walkthrough_1.this, Walkthrough_2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
    }

}