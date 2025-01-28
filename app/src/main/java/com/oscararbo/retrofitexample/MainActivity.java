package com.oscararbo.retrofitexample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText ingredientInput;
    private Button searchButton;
    private RecyclerView recyclerView;
    private CocktailAdapter cocktailAdapter;
    private List<Cocktail> cocktails = new ArrayList<>();
    private GetDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        ingredientInput = findViewById(R.id.ingredientInput);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Set up RecyclerView with GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        cocktailAdapter = new CocktailAdapter(this, cocktails);
        recyclerView.setAdapter(cocktailAdapter);

        // Initialize Retrofit service
        service = ApiClient.getClient().create(GetDataService.class);

        // Set up the search button click listener
        searchButton.setOnClickListener(v -> searchCocktails());
    }

    private void searchCocktails() {
        String ingredient = ingredientInput.getText().toString().trim();

        if (TextUtils.isEmpty(ingredient)) {
            Toast.makeText(this, "Please enter an ingredient", Toast.LENGTH_SHORT).show();
            return;
        }

        // API call to fetch cocktails by ingredient
        service.getCocktailsByIngredient(ingredient).enqueue(new Callback<CocktailResponse>() {
            @Override
            public void onResponse(Call<CocktailResponse> call, Response<CocktailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cocktails.clear();
                    cocktails.addAll(response.body().getDrinks());
                    cocktailAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "No cocktails found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CocktailResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch cocktails", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCocktailDetails(Cocktail cocktail) {
        // Fetch detailed information for the selected cocktail
        service.getCocktailDetails(cocktail.getIdDrink()).enqueue(new Callback<CocktailDetailsResponse>() {
            @Override
            public void onResponse(Call<CocktailDetailsResponse> call, Response<CocktailDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getDrinks().isEmpty()) {
                    CocktailDetails details = response.body().getDrinks().get(0);

                    // Show details in a new activity
                    Intent intent = new Intent(MainActivity.this, CocktailDetailsActivity.class);
                    intent.putExtra("cocktailName", details.getStrDrink());
                    intent.putExtra("instructions", details.getStrInstructions());
                    intent.putExtra("imageUrl", details.getStrDrinkThumb());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Details not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CocktailDetailsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
