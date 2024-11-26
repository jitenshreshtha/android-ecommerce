package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class GamedetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedetails);

        // Find views
        ImageView imageView = findViewById(R.id.game_image);
        TextView titleTextView = findViewById(R.id.game_title);
        TextView descriptionTextView = findViewById(R.id.game_description);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        // Get data from intent
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");

        // Set data to views
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // Load image using Glide
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        // Handle Add to Cart button click
        addToCartButton.setOnClickListener(view -> {
            addToCart(title, imageUrl, description);
            Toast.makeText(GamedetailsActivity.this, title + " added to cart!", Toast.LENGTH_SHORT).show();

            Intent intent2 = new Intent(GamedetailsActivity.this, CartActivity.class);
            startActivity(intent2);
        });
    }
    private void addToCart(String title, String imageUrl, String description) {
        // Logic to add game to the cart (using SharedPreferences for simplicity)
        CartManager.addItemToCart(this, title, imageUrl, description);
    }
}