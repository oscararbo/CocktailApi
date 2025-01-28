package com.oscararbo.retrofitexample;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CocktailDetailsActivity extends AppCompatActivity {

    private TextView cocktailNameTextView;
    private TextView instructionsTextView;
    private ImageView cocktailImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_details);

        // Initialize views
        cocktailNameTextView = findViewById(R.id.cocktailName);
        instructionsTextView = findViewById(R.id.instructions);
        cocktailImageView = findViewById(R.id.cocktailImage);

        // Get data from the intent
        String cocktailName = getIntent().getStringExtra("cocktailName");
        String instructions = getIntent().getStringExtra("instructions");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Set data to views
        cocktailNameTextView.setText(cocktailName);
        instructionsTextView.setText(instructions);
        Glide.with(this).load(imageUrl).into(cocktailImageView);
    }
}
