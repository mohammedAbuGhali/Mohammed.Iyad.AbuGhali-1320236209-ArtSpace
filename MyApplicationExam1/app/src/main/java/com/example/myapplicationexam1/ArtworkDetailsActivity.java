package com.example.myapplicationexam1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_ARTIST = "EXTRA_ARTIST";
    public static final String EXTRA_YEAR = "EXTRA_YEAR";
    public static final String EXTRA_IMAGE_RES = "EXTRA_IMAGE_RES";
    public static final String EXTRA_URL = "EXTRA_URL";

    private ImageView imgDetailsArtwork;
    private TextView tvDetailsTitle;
    private TextView tvDetailsArtist;
    private TextView tvDetailsYear;
    private Button btnVisitWebsite;
    private Button btnBack;

    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollDetails), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        imgDetailsArtwork = findViewById(R.id.imgDetailsArtwork);
        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        tvDetailsArtist = findViewById(R.id.tvDetailsArtist);
        tvDetailsYear = findViewById(R.id.tvDetailsYear);
        btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        btnBack = findViewById(R.id.btnBack);

        // Receive Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(EXTRA_TITLE);
            String artist = intent.getStringExtra(EXTRA_ARTIST);
            String year = intent.getStringExtra(EXTRA_YEAR);
            int imageResId = intent.getIntExtra(EXTRA_IMAGE_RES, 0);
            webUrl = intent.getStringExtra(EXTRA_URL);

            if (title != null) tvDetailsTitle.setText(title);
            if (artist != null) tvDetailsArtist.setText(artist);
            if (year != null) tvDetailsYear.setText(year);
            if (imageResId != 0) imgDetailsArtwork.setImageResource(imageResId);
        }

        // Implicit Intent: Open website in browser
        btnVisitWebsite.setOnClickListener(v -> {
            if (webUrl != null && !webUrl.isEmpty()) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                startActivity(webIntent);
            }
        });

        // Return to MainActivity
        btnBack.setOnClickListener(v -> finish());
    }
}
