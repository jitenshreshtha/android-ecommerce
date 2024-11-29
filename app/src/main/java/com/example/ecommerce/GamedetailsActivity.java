package com.example.ecommerce;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GamedetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private Button addToCartButton;
    private String imageUrl;
    private String title;
    private String description;
    private Long price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedetails);

        // Find views
        imageView = findViewById(R.id.game_image);
        titleTextView = findViewById(R.id.game_title);
        descriptionTextView = findViewById(R.id.game_description);
        priceTextView = findViewById(R.id.game_price);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        // Disable button initially
        addToCartButton.setEnabled(false);

        // Get the game ID from the intent
        int gameId = getIntent().getIntExtra("gameId", -1);
        if (gameId >= 0) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("cards").child(String.valueOf(gameId));

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Fetch data
                        title = snapshot.child("title").getValue(String.class);
                        description = snapshot.child("description").getValue(String.class);
                        imageUrl = snapshot.child("imageUrl").getValue(String.class);
                        price = snapshot.child("price").getValue(Long.class);

                        // Log data for debugging
                        Log.d("Gamedetails", "Title: " + title);
                        Log.d("Gamedetails", "Description: " + description);
                        Log.d("Gamedetails", "Image URL: " + imageUrl);
                        Log.d("Gamedetails", "Price: " + price);

                        // Display data
                        titleTextView.setText(title != null ? title : "Title not available");
                        descriptionTextView.setText(description != null ? description : "Description not available");
                        priceTextView.setText(price != null ? "Price: $" + price : "Price not available");

                        // Load image
                        Glide.with(GamedetailsActivity.this)
                                .load(imageUrl)
                                .into(imageView);

                        // Setup add to cart button after data is loaded
                        setupAddToCartButton();
                    } else {
                        Toast.makeText(GamedetailsActivity.this, "Game details not found!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(GamedetailsActivity.this, "Failed to load data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Invalid game ID!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupAddToCartButton() {
        addToCartButton.setEnabled(true);
        addToCartButton.setOnClickListener(v -> {
            addToCart(title, imageUrl, price);
            Toast.makeText(GamedetailsActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
        });
    }

    private void addToCart(String title, String imageUrl, Long price) {
        CartManager.addItemToCart(this, title, imageUrl, description , price);
    }
}