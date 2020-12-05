package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GalleriaFotografica extends AppCompatActivity {

    private final int image_ids[] = {
            R.drawable.gte1,
            R.drawable.gte2,
            R.drawable.gte3,
            R.drawable.gte4,
            R.drawable.gte5,
            R.drawable.gte6,
            R.drawable.gte7,
            R.drawable.gte8,
            R.drawable.gte9,
            R.drawable.gte10,
            R.drawable.gte11,
            R.drawable.gte12,
            R.drawable.gte13,
            R.drawable.gte14,
            R.drawable.gte15,
    };

    Gallery simpleGallery;
    GalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galleria_fotografica);

        Gallery simpleGallery = (Gallery) findViewById(R.id.simpleGallery);
        selectedImageView = (ImageView) findViewById(R.id.selectedImageView); // get the reference of ImageView
        customGalleryAdapter = new GalleryAdapter(getApplicationContext(), image_ids); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter); // set the adapter
        simpleGallery.setSpacing(10);
        // perform setOnItemClickListener event on the Gallery
        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                selectedImageView.setImageResource(image_ids[position]);

            }
        });

        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        menu.setSelectedItemId(R.id.galleria_menu);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.galleria_menu:
                        return true;
                    case R.id.iscrizioni_menu:
                        Intent a = new Intent(getApplicationContext(), IscrizioniEffettuate.class);
                        startActivity(a);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.campionati_menu:
                        Intent b = new Intent(getApplicationContext(), Home_Campionati.class);
                        startActivity(b);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.novita_menu:
                        Intent c = new Intent(getApplicationContext(), NovitaCorse.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}