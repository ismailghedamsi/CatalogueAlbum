package com.example.tpfinal.domain;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Album implements Serializable {
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    // id VARCHAR primary key,title VARCHAR,genre VARCHAR,artist VARCHAR,nbTrack NUMBER
    private Bitmap image;
    private String id;
    private String title;
    private String genre;
    private String artist;
    private int nbTrack;

    public Album(String id, String title, String genre, String artist, int nbTrack,Bitmap image) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.nbTrack = nbTrack;
        this.image = image;

    }

    public Album(String title, String genre, String artist, int nbTrack) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.nbTrack = nbTrack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
