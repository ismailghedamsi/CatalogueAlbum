package com.example.tpfinal;

import android.os.Bundle;

import com.example.tpfinal.domain.Album;
import com.example.tpfinal.domain.RegisterUser;
import com.example.tpfinal.model.AlbumRecyclerAdapter;
import com.example.tpfinal.model.AlbumSaverLoaderModel;
import com.example.tpfinal.model.ExampleItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SeeAllAlbumActivity extends AppCompatActivity {
    private String pwd = "pwd";
    private SignInFragment headlinesFragment;
    private Handler mainHandler = new Handler();
    private Handler mainHandler2 = new Handler();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManagerGrid;
    private RegisterUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_album);
        List<Album> albums = AlbumSaverLoaderModel.retrieveAlbums(this);
        Log.d(MainActivity.class.getSimpleName(), "onCreate: "+(albums.size()));
        ExampleItem exampleItem = null;
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        for (Album curr_album: albums) {
             exampleItem = new ExampleItem(curr_album.getImage(),curr_album.getTitle(),curr_album.getGenre(),curr_album.getArtist(),curr_album.getNbTrack());
            Log.d(MainActivity.class.getSimpleName(), "title: "+curr_album.getTitle());
            exampleList.add(exampleItem);
        }


        /*exampleList.add(exampleItem);
        exampleList.add(exampleItem);
        exampleList.add(exampleItem);
        exampleList.add(exampleItem);
        exampleList.add(exampleItem);*/


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManagerGrid = new LinearLayoutManager(this);
        mAdapter = new AlbumRecyclerAdapter(exampleList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

}
