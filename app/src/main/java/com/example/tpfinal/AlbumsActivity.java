package com.example.tpfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpfinal.domain.Album;
import com.example.tpfinal.model.AlbumSaverLoaderModel;

import java.util.List;

public class AlbumsActivity extends AppCompatActivity {
    ImageView image;
    TextView title;
    private static int SEE_NEW_ADDED_ALBUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        title = findViewById(R.id.textView_title);
        title.setText("random text");
       List<Album> albums =  AlbumSaverLoaderModel.retrieveAlbums(this);
       image = findViewById(R.id.test);
       image.setImageBitmap(albums.get(0).getImage());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        title = findViewById(R.id.textView_title);
        Toast.makeText(this,data.getStringExtra("title"),Toast.LENGTH_LONG).show();
        Log.d(AlbumsActivity.class.getSimpleName(), "zzzzzzzz"+data.getStringExtra("title"));
        if(requestCode == SEE_NEW_ADDED_ALBUM ){
            if(resultCode == 200){
                title.setText("zzzzz");
            }
        }
    }
}
