package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Walkthrough_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkthrough_3);
    }

    public void register(View v) {
        Intent intent = new Intent(Walkthrough_3.this, Registrazione.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
    }

}