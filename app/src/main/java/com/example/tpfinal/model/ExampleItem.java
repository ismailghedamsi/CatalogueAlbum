package com.example.tpfinal.model;

import android.graphics.Bitmap;

public class ExampleItem {

    private Bitmap mImageResource;
    private String title;
    private String genre;
    private String artist;
    private int nbTrack;

    public ExampleItem(Bitmap imageResource, String title, String genre,String artist,int nbTrack) {
        mImageResource = imageResource;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.nbTrack = nbTrack;
    }

    public Bitmap getImageResource() {
        return mImageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Bitmap getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(Bitmap mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNbTrack() {
        return nbTrack;
    }

    public void setNbTrack(int nbTrack) {
        this.nbTrack = nbTrack;
    }
}