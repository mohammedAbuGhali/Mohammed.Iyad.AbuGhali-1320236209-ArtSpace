package com.example.myapplicationexam1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "KEY_CURRENT_INDEX";

    private TextView tvCounter;
    private ImageView imgArtwork;
    private TextView tvArtworkTitle;
    private TextView tvArtistAndYear;
    private Button btnPrevious;
    private Button btnNext;

    private List<Artwork> artworkList;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        tvCounter = findViewById(R.id.tvCounter);
        imgArtwork = findViewById(R.id.imgArtwork);
        tvArtworkTitle = findViewById(R.id.tvArtworkTitle);
        tvArtistAndYear = findViewById(R.id.tvArtistAndYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Initialize Artwork Gallery Collection
        initArtworkList();

        // Restore State on Configuration Change
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
        }

        // Display current artwork
        displayArtwork(currentIndex);

        // Previous Button Click Listener
        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                displayArtwork(currentIndex);
            }
        });

        // Next Button Click Listener
        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworkList.size() - 1) {
                currentIndex++;
                displayArtwork(currentIndex);
            }
        });

        // Click Artwork Image to Open ArtworkDetailsActivity (Explicit Intent)
        imgArtwork.setOnClickListener(v -> openArtworkDetails());
    }

    private void initArtworkList() {
        artworkList = new ArrayList<>();
        artworkList.add(new Artwork(
                "Mona Lisa",
                "Leonardo da Vinci",
                "1503",
                R.drawable.artwork1,
                "https://en.wikipedia.org/wiki/Mona_Lisa"
        ));
        artworkList.add(new Artwork(
                "The Starry Night",
                "Vincent van Gogh",
                "1889",
                R.drawable.artwork2,
                "https://en.wikipedia.org/wiki/The_Starry_Night"
        ));
        artworkList.add(new Artwork(
                "The Scream",
                "Edvard Munch",
                "1893",
                R.drawable.artwork3,
                "https://en.wikipedia.org/wiki/The_Scream"
        ));
        artworkList.add(new Artwork(
                "Girl with a Pearl Earring",
                "Johannes Vermeer",
                "1665",
                R.drawable.artwork4,
                "https://en.wikipedia.org/wiki/Girl_with_a_Pearl_Earring"
        ));
        artworkList.add(new Artwork(
                "The Persistence of Memory",
                "Salvador Dalí",
                "1931",
                R.drawable.artwork5,
                "https://en.wikipedia.org/wiki/The_Persistence_of_Memory"
        ));
        artworkList.add(new Artwork(
                "The Great Wave off Kanagawa",
                "Katsushika Hokusai",
                "1831",
                R.drawable.artwork6,
                "https://en.wikipedia.org/wiki/The_Great_Wave_off_Kanagawa"
        ));
    }

    private void displayArtwork(int index) {
        if (index < 0 || index >= artworkList.size()) return;

        Artwork current = artworkList.get(index);

        // Update Counter Position
        tvCounter.setText(getString(R.string.counter_format, index + 1, artworkList.size()));

        // Update Artwork Image
        imgArtwork.setImageResource(current.getImageResId());

        // Update Artwork Title
        tvArtworkTitle.setText(current.getTitle());

        // Update Artist Name and Year
        tvArtistAndYear.setText(getString(R.string.artist_and_year_format, current.getArtist(), current.getYear()));

        // Enable / Disable Navigation Buttons
        btnPrevious.setEnabled(index > 0);
        btnNext.setEnabled(index < artworkList.size() - 1);
    }

    private void openArtworkDetails() {
        Artwork current = artworkList.get(currentIndex);

        Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
        intent.putExtra(ArtworkDetailsActivity.EXTRA_TITLE, current.getTitle());
        intent.putExtra(ArtworkDetailsActivity.EXTRA_ARTIST, current.getArtist());
        intent.putExtra(ArtworkDetailsActivity.EXTRA_YEAR, current.getYear());
        intent.putExtra(ArtworkDetailsActivity.EXTRA_IMAGE_RES, current.getImageResId());
        intent.putExtra(ArtworkDetailsActivity.EXTRA_URL, current.getWebUrl());

        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }
}
