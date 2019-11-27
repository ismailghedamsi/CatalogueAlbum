package com.example.tpfinal.model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpfinal.R;
import com.example.tpfinal.domain.Album;

public class RandomAlbumActivity extends AppCompatActivity {
    private static int RANDOM_IMAGE = 5;
    private Bitmap imageBit;
    private TextView title;
    private  TextView genre;
    private TextView nbTrack;
    private TextView artist;
    ImageView cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_album);
        Album random_album = AlbumSaverLoaderModel.retrieveRandomAlbum(this);
        title = findViewById(R.id.textView_title);
        cover = findViewById(R.id.cover);
        genre = findViewById(R.id.input_genre);
        artist = findViewById(R.id.input_artist);
        nbTrack = findViewById(R.id.input_nb_track);
        title.setText(random_album.getTitle());
        cover.setImageBitmap(random_album.getImage());
        genre.setText(random_album.getGenre());
        artist.setText(random_album.getArtist());
        nbTrack.setText(random_album.getNbTrack()+"");



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RANDOM_IMAGE){
            if(resultCode == RESULT_OK){
                String title = data.getStringExtra("title");
                String genre = data.getStringExtra("genre");
                int nbTrack = data.getIntExtra("nbTrack",0);
                String artist = data.getStringExtra("artist");
                byte[] byteArray = data.getByteArrayExtra("cover");
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                cover = findViewById(R.id.test);
                cover.setImageBitmap(bitmap);
            }

        }


    }
}
