package com.example.tpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.tpfinal.domain.Album;
import com.example.tpfinal.domain.RegisterUser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Persistance";
    final static String SQL_CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS user (id VARCHAR primary key,firstName VARCHAR,lastName VARCHAR,email VARCHAR,password VARCHAR)";
    final static String SQL_CREATE_TABLE_ALBUM =
            "CREATE TABLE IF NOT EXISTS album (id VARCHAR primary key,title VARCHAR,genre VARCHAR,artist VARCHAR,nbTrack NUMBER,image BLOB)";

    final static String SQL_CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS image (imageName VARCHAR,image blob)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ALBUM);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_IMAGE);
        Log.e("TEST",SQL_CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void saveImage(SQLiteDatabase sqLiteDatabase, Context context,Bitmap bitmap){
        /*Resources res = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.album1);*/
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte imageInByte[] = stream.toByteArray();
        ContentValues objectContentValue = new ContentValues();
        objectContentValue.put("imageName","jmt");
        objectContentValue.put("image",imageInByte);
        sqLiteDatabase.insert("image",null,objectContentValue);
        Toast.makeText(context,"save image",Toast.LENGTH_LONG).show();
    }

    public void saveAlbum(SQLiteDatabase sqLiteDatabase, Album album){
        String id = UUID.randomUUID().toString();
        Bitmap bitmap = album.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte imageInByte[] = stream.toByteArray();
        ContentValues objectContentValue = new ContentValues();
        objectContentValue.put("id",album.getId());
        Log.e(DatabaseHelper.class.getSimpleName(), "album id"+album.getId());
        objectContentValue.put("title",album.getTitle());
        objectContentValue.put("genre",album.getGenre());
        objectContentValue.put("artist",album.getArtist());
        objectContentValue.put("nbTrack",album.getNbTrack());
        objectContentValue.put("image",imageInByte);
       long b = sqLiteDatabase.insertOrThrow("album",null,objectContentValue);
        Log.d(this.getClass().getSimpleName(), "saveAlbum: "+b);
        sqLiteDatabase.close();
    }

    public List<Album> loadAlbum(SQLiteDatabase sqLiteDatabase){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        List<Album> album = new ArrayList<>();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM  album",null);
        //id VARCHAR primary key,title VARCHAR,genre VARCHAR,artist VARCHAR,nbTrack NUMBER,image BLOB)";
        if(cursor != null) {
            while (cursor.moveToNext()) {
                String idFetch = cursor.getString(0);
                String titleFetch = cursor.getString(1);
                String genreFetch = cursor.getString(2);
                String artistFetch = cursor.getString(3);
                int nbTrackFetch = cursor.getInt(4);
                byte[] imageByte = cursor.getBlob(5);
                Bitmap imageBitMap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                album.add(new Album(idFetch,titleFetch,genreFetch,artistFetch,nbTrackFetch,imageBitMap));
            }
        }
        Log.d(this.getClass().getSimpleName(), "album size: "+album.size());
        cursor.close();
        sqLiteDatabase.close();
        return album;
    }


    public Album loadRandomAlbum(SQLiteDatabase sqLiteDatabase){
       /* Album album = null;
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM album ORDER BY RANDOM() LIMIT 1",null);
        //id VARCHAR primary key,title VARCHAR,genre VARCHAR,artist VARCHAR,nbTrack NUMBER,image BLOB)";
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                String idFetch = cursor.getString(0);
                String titleFetch = cursor.getString(1);
                String genreFetch = cursor.getString(2);
                String artistFetch = cursor.getString(3);
                int nbTrackFetch = cursor.getInt(4);
                byte[] imageByte = cursor.getBlob(5);
                Bitmap imageBitMap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                album = new Album(idFetch,titleFetch,genreFetch,artistFetch,nbTrackFetch,imageBitMap);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return   album;*/
       List<Album> albums = loadAlbum(sqLiteDatabase);
         Collections.shuffle(albums);
         return albums.get(0);


    }




    public void saveUser(SQLiteDatabase sqLiteDatabase, RegisterUser registerUser){
        String id = UUID.randomUUID().toString();
        sqLiteDatabase.execSQL("INSERT INTO user " +
                "VALUES('"+id+"','"+registerUser.getFirstName()+"','"+registerUser.getLastName()+"','"+registerUser.getEmail()+"','"+registerUser.getPassword()+"');");
    }

    public RegisterUser loadUser(SQLiteDatabase sqLiteDatabase,String email,String password){
        RegisterUser user = null;
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM  user WHERE email = ? AND password = ?",new String[]{email,password});
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                String idFetch = cursor.getString(0);
                String firstNameFetch = cursor.getString(1);
                String lastNameFetch = cursor.getString(2);
                String emailFetch = cursor.getString(3);
                String passwordFetch = cursor.getString(4);
                user = new RegisterUser(idFetch,firstNameFetch,lastNameFetch,emailFetch,passwordFetch);
            }
        }
        return user;
    }





}
