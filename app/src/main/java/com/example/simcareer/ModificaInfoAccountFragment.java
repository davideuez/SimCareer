package com.example.simcareer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ModificaInfoAccountFragment extends Fragment {

    Button salvaDati;
    TextInputEditText nickname, email, psw;
    ImageView profilePicture, editProfileIcon;
    SharedPreferences datiUtente;
    String immagineCodificata;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modifica_info_account, container, false);

        salvaDati = (Button) v.findViewById(R.id.salva_modifiche2);
        profilePicture = (ImageView) v.findViewById(R.id.profile_image);
        nickname = (TextInputEditText) v.findViewById(R.id.modifica_nickname);
        email = (TextInputEditText) v.findViewById(R.id.modifica_email);
        psw = (TextInputEditText) v.findViewById(R.id.modifica_password);

        Context x = getContext();
        datiUtente = x.getSharedPreferences("Dati_Utente", Context.MODE_PRIVATE);

        String nick = datiUtente.getString("nickname", "");
        String mail = datiUtente.getString("email", "");
        String pass = datiUtente.getString("password", "");

        nickname.setText(nick);
        email.setText(mail);
        psw.setText(pass);

        if(datiUtente.contains("profile_picture")){

            String codifica = datiUtente.getString("profile_picture","");
            Bitmap immagineProfile = decodeBase64(codifica);
            Uri uri = getImageUri(this.getContext(), immagineProfile);
            profilePicture.setImageURI(uri);

        }

        editProfileIcon = v.findViewById(R.id.editprofile);

        editProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if(x.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permesso non garantito, lo richiedo
                        String[] permessi = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // mostra popup per permessi runtime
                        requestPermissions(permessi, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }


                } else {  // se sistema operativo è minore della versione Marshmello
                    pickImageFromGallery();
                }

            }

        });

        salvaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nNickname = nickname.getText().toString();
                String nEmail = email.getText().toString();
                String nPsw = psw.getText().toString();

                SharedPreferences.Editor editor = datiUtente.edit();

                editor.putString("nickname", nNickname);
                editor.putString("email", nEmail);
                editor.putString("password", nPsw);
                editor.putString("profile_picture", immagineCodificata);

                editor.commit();

                Toast.makeText(x, "Informazioni aggiornate", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void pickImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if(grantResults.length <0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this.getContext(), "Permesso alla fotocamera negato..", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            try {
                Context x = getContext();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(x.getContentResolver(), data.getData());
                immagineCodificata = encodeTobase64(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profilePicture.setImageURI(data.getData());
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}