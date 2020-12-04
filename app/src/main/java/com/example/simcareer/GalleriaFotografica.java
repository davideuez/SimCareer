package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class GalleriaFotografica extends AppCompatActivity {

    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12",
            "Img13",
            "Img14",
            "Img15",
            "Img16",
            "Img17",
            "Img18",
            "Img19",
            "Img20",
            "Img21",
            "Img22",
            "Img23",
            "Img24",
            "Img25",
            "Img26",
            "Img27",
            "Img28",
            "Img29",
            "Img30",
            "Img31",
            "Img32",
            "Img33",
    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
            R.drawable.img17,
            R.drawable.img18,
            R.drawable.img19,
            R.drawable.img20,
            R.drawable.img21,
            R.drawable.img22,
            R.drawable.img23,
            R.drawable.img24,
            R.drawable.img25,
            R.drawable.img26,
            R.drawable.img27,
            R.drawable.img28,
            R.drawable.img29,
            R.drawable.img30,
            R.drawable.img31,
            R.drawable.img32,
            R.drawable.img33
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galleria_fotografica);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreaLista> createLists = prepareData();
        GalleryAdapter adapter = new GalleryAdapter(getApplicationContext(), createLists);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<CreaLista> prepareData(){

        ArrayList<CreaLista> theimage = new ArrayList<>();
        for(int i = 0; i< image_titles.length; i++){
            CreaLista createList = new CreaLista();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }
        return theimage;
    }
}