package com.example.myapplicationexam1;

public class Artwork {
    private final String title;
    private final String artist;
    private final String year;
    private final int imageResId;
    private final String webUrl;

    public Artwork(String title, String artist, String year, int imageResId, String webUrl) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.imageResId = imageResId;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getYear() {
        return year;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
