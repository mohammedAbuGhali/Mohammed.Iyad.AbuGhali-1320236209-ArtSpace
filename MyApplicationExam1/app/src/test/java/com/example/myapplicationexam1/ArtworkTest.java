package com.example.myapplicationexam1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtworkTest {

    @Test
    public void testArtworkGetters() {
        Artwork artwork = new Artwork(
                "Mona Lisa",
                "Leonardo da Vinci",
                "1503",
                12345,
                "https://en.wikipedia.org/wiki/Mona_Lisa"
        );

        assertEquals("Mona Lisa", artwork.getTitle());
        assertEquals("Leonardo da Vinci", artwork.getArtist());
        assertEquals("1503", artwork.getYear());
        assertEquals(12345, artwork.getImageResId());
        assertEquals("https://en.wikipedia.org/wiki/Mona_Lisa", artwork.getWebUrl());
    }
}
