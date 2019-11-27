package com.example.tpfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tpfinal.domain.Album;
import com.example.tpfinal.model.AlbumSaverLoaderModel;

import java.io.IOException;
import java.util.UUID;

public class AddAlbumBdActivity extends AppCompatActivity {
    private ImageView image;
    private EditText title;
    private EditText genre;
    private EditText artist;
    private EditText nbTrack;
    private static int PICK_IMAGE = 1;
    private static int SEE_NEW_ADDED_ALBUM = 2;
    private Button saveImageBtn;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album_bd);
        image = findViewById(R.id.test);
        title = findViewById(R.id.input_title);
        genre = findViewById(R.id.input_genre);
        artist = findViewById(R.id.input_artist);
        nbTrack = findViewById(R.id.input_nb_track);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallerie = new Intent();
                gallerie.setType("image/*");
                gallerie.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallerie,"Select Image"),PICK_IMAGE);

            }
        });

        saveImageBtn = findViewById(R.id.btn_save);
        saveImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = UUID.randomUUID().toString();
                int nbTrackInt = Integer.parseInt(nbTrack.getText().toString());
                BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Album album =
                        new Album(id, title.getText().toString(),  genre.getText().toString(),  artist.getText().toString(),nbTrackInt , bitmap);
                AlbumSaverLoaderModel.saveAlbum(AddAlbumBdActivity.this,bitmap,album);
                Intent intentSeeAlbum = new Intent(AddAlbumBdActivity.this,SeeAllAlbumActivity.class);
              //  intentSeeAlbum.putExtra("title",album.getTitle());
               // setResult(AddAlbumBdActivity.RESULT_OK, intentSeeAlbum);
               startActivityForResult(intentSeeAlbum,SEE_NEW_ADDED_ALBUM);
                //finish();
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                image.setImageBitmap(bitmap);
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
}
