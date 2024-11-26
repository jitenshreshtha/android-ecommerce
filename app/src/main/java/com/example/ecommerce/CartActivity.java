package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.cart_recycler_view);
        Button checkoutButton = findViewById(R.id.checkout_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Card> cartItems = new ArrayList<>();
        try {
            JSONArray cartArray = CartManager.getCartArray(this);
            for (int i = 0; i < cartArray.length(); i++) {
                JSONObject item = cartArray.getJSONObject(i);
                cartItems.add(new Card(
                        item.getString("imageUrl"),
                        item.getString("title"),
                        item.getString("description"),
                        item.getString("longDescription"),
                        item.getDouble("rating"),
                        item.getDouble("price")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CardAdapter cardAdapter = new CardAdapter(cartItems, this);
        recyclerView.setAdapter(cardAdapter);

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
        }

        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Proceeding to checkout!", Toast.LENGTH_SHORT).show();
            Intent checkoutIntent = new Intent(CartActivity.this, Checkout.class);
            startActivity(checkoutIntent);
        });
    }
}
