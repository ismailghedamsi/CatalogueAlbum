package com.example.tpfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tpfinal.domain.Album;
import com.example.tpfinal.domain.RegisterUser;
import com.example.tpfinal.model.AlbumRecyclerAdapter;
import com.example.tpfinal.model.AlbumSaverLoaderModel;
import com.example.tpfinal.model.ExampleItem;
import com.example.tpfinal.model.RandomAlbumActivity;
import com.example.tpfinal.model.SignInModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SignInFragment.PasswordAuthentificationInterface
, SignInFragment.FragmentSwitchable,SignUpFragment.FragmentSwitchable{
    private String pwd = "pwd";
    private SignInFragment headlinesFragment;
    private Handler mainHandler = new Handler();
    private Handler mainHandler2 = new Handler();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManagerGrid;
    private RegisterUser user;
    private static final String KEY_EMAIL= "email_key";
    private static final String KEY_PASSWORD = "password_key";
    private final String SIGN_IN_FRAGMENT = "SignUpFragment";
    private  SignInFragment signInFragment = null;

    private static int PICK_IMAGE = 1;
    private static int RANDOM_IMAGE = 5;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        SignInFragment dialog = new SignInFragment();
        dialog.setCancelable(false);
        dialog.show(fragmentManager,"Tag");

        /*mainHandler.postDelayed(new Runnable(){
            public void run(){
                  Toast.makeText(MainActivity.this,"Ads",Toast.LENGTH_LONG).show();
                mainHandler.postDelayed(this, 10000);
            }
        }, 5000);*/

              /*  mainHandler2.postDelayed(new Runnable(){
            public void run(){
                  Toast.makeText(MainActivity.this,"Ads",Toast.LENGTH_LONG).show();
                List<Album> albums = AlbumSaverLoaderModel.retrieveAlbums(MainActivity.this);
                Log.d(MainActivity.class.getSimpleName(), "onCreate: "+(albums.size()));
            }
        }, 5000);*/


        List<Album> albums = AlbumSaverLoaderModel.retrieveAlbums(this);
        albums.add(new Album("title","genre","artist",0));
        Log.d(MainActivity.class.getSimpleName(), "onCreate: "+(albums.size()));
        ExampleItem exampleItem = null;
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        for (Album curr_album: albums) {
            exampleItem = new ExampleItem(curr_album.getImage(),curr_album.getTitle(),curr_album.getGenre(),curr_album.getArtist(),curr_album.getNbTrack());
            Log.d(MainActivity.class.getSimpleName(), "title: "+curr_album.getTitle());
            exampleList.add(exampleItem);
        }


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManagerGrid = new LinearLayoutManager(this);
        mAdapter = new AlbumRecyclerAdapter(exampleList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }



    @Override
    public void onAuthneticate(String email,String password) {
        Log.d(MainActivity.class.getSimpleName(),"onAuthenticate");
        Toast.makeText(this,"password "+password,Toast.LENGTH_LONG).show();
        //if(SignInModel.authenticate(this,email,password)){
            if(SignInModel.getAuthenticateUser(this,email,password) != null){
                Toast.makeText(this,"password "+password,Toast.LENGTH_LONG).show();
            headlinesFragment.dismiss();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Invalid Password"+password);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);

                viewSwitcher.setDisplayedChild(1);

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof SignInFragment) {
            headlinesFragment = (SignInFragment) fragment;
            headlinesFragment.setPasswordAuthentificationInterface(this);
        }
    }

    @Override
    public void onSwitchToSignUp() {
        SignUpFragment frag = new SignUpFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SignInFragment fragmentToRemove = (SignInFragment)getSupportFragmentManager().getFragments().get(0);
        //fragmentToRemove.dismiss();
        if (fragmentToRemove != null) {
            ft.remove(fragmentToRemove);
        }
        ft.addToBackStack(null);
        ft.commit(); // or ft.commitAllowingStateLoss()
        frag.show(getSupportFragmentManager(), "your_dialog_fragment");
    }

    @Override
    public void onSwitchToSignIn() {
        SignInFragment frag = new SignInFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SignUpFragment fragmentToRemove = (SignUpFragment) getSupportFragmentManager().getFragments().get(0);
        //fragmentToRemove.dismiss();
        if (fragmentToRemove != null) {
            ft.remove(fragmentToRemove);
           // ft.hide(fragmentToRemove);
        }
        ft.addToBackStack(null);
        ft.commit(); // or ft.commitAllowingStateLoss()
        frag.show(getSupportFragmentManager(), "your_dialog_fragment");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        switch (item.getItemId()){
                case R.id.addAlbumToBd:
                    Toast.makeText(this,"Ajout album a la bd",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this,AddAlbumBdActivity.class);
                    startActivity(intent);
                    List<Album> albums = AlbumSaverLoaderModel.retrieveAlbums(this);
                    mRecyclerView.removeAllViewsInLayout();
                    for (Album album:albums) {
                        ExampleItem exampleItem = new ExampleItem(album.getImage(),album.getTitle(),album.getGenre(),album.getArtist(),album.getNbTrack());
                        exampleList = new ArrayList<>();
                        exampleList.add(exampleItem);
                    }
                    mAdapter = new AlbumRecyclerAdapter(exampleList);
                    mRecyclerView.setAdapter(mAdapter);
                    break;
                case   R.id.seeAllAlbum :
                    Intent intent2 = new Intent(this,SeeAllAlbumActivity.class);
                    startActivity(intent2);


                    break;
                case R.id.random_album :
                    DatabaseHelper databaseHelper = new DatabaseHelper(this);
                    SQLiteDatabase dbReadable =  databaseHelper.getReadableDatabase();
                    Album album = databaseHelper.loadRandomAlbum(dbReadable);

                    Log.d(MainActivity.class.getSimpleName(),"zzz"+album.getArtist());
                    if(album!= null){
                        Intent intentRandomAlbum = new Intent(this, RandomAlbumActivity.class);
                        intentRandomAlbum.putExtra("title",album.getTitle());
                        intentRandomAlbum.putExtra("genre",album.getGenre());
                        intentRandomAlbum.putExtra("artist",album.getArtist());
                        intentRandomAlbum.putExtra("nbTrack",album.getNbTrack());
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap cover =album.getImage();
                        cover.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        cover.recycle();
                        byte[] byteArray = stream.toByteArray();
                        intentRandomAlbum.putExtra("cover",byteArray);
                        startActivityForResult(intentRandomAlbum,RANDOM_IMAGE);

                    }

                    break;
            }
        return super.onOptionsItemSelected(item);
        }




}


