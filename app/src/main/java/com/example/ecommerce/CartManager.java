package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CartManager {

    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_KEY = "cart";

    // Add item to the cart
    public static void addItemToCart(Context context, String title, String imageUrl, String description) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        try {
            JSONArray cartArray = getCartArray(context);
            JSONObject newItem = new JSONObject();
            newItem.put("title", title);
            newItem.put("imageUrl", imageUrl);
            newItem.put("description", description);

            cartArray.put(newItem);
            editor.putString(CART_KEY, cartArray.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
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
