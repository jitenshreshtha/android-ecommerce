package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CartManager {

    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_KEY = "cart";

    // Add item to the cart
    public static void addItemToCart(Context context, String title, String imageUrl, String description, Long price) {
        // Get the current user's UID
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (userUID != null) {
            // Get the database reference for the user's products
            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                    .getReference("users").child(userUID).child("products");

            // Generate a unique key for the product
            String productId = databaseReference.push().getKey();

            if (productId != null) {
                // Create a product object
                Product product = new Product(productId, title, description, imageUrl, price);

                // Push the product into the user's node
                databaseReference.child(productId).setValue(product)
                        .addOnSuccessListener(aVoid -> {
                            System.out.println("Product added successfully under user: " + userUID);
                        })
                        .addOnFailureListener(e -> {
                            System.err.println("Failed to add product: " + e.getMessage());
                        });
            } else {
                System.err.println("Failed to generate unique product ID!");
            }
        } else {
            System.err.println("User is not logged in!");
        }
    }


    // Get cart items
    public static JSONArray getCartArray(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        String cartString = sharedPreferences.getString(CART_KEY, "[]");
        try {
            return new JSONArray(cartString);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}
