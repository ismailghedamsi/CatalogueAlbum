package com.example.tpfinal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.example.tpfinal.DatabaseHelper;
import com.example.tpfinal.domain.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumSaverLoaderModel {
    static DatabaseHelper databaseHelper;
    public static void saveAlbum(Context context, Bitmap bitmap, Album album){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbWritable =  databaseHelper.getWritableDatabase();
        databaseHelper.saveAlbum(dbWritable,album);

    }

    public static List<Album> retrieveAlbums(Context context){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReadable =  databaseHelper.getReadableDatabase();
       // return databaseHelper.retrieveAlbums(dbReadable,context);
        return databaseHelper.loadAlbum(dbReadable);
    }

    public static Album retrieveRandomAlbum(Context context){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReadable =  databaseHelper.getReadableDatabase();
        return databaseHelper.loadRandomAlbum(dbReadable);
    }


}
