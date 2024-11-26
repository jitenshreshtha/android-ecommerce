package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Card> cardList;
    private Context context;

    public CardAdapter(List<Card> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.titleTextView.setText(card.getTitle());
        holder.descriptionTextView.setText(card.getDescription());

        // Use Glide to load the image
        Glide.with(context)
                .load(card.getImageUrl())
                .into(holder.imageView);
        // Set click listener
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, GamedetailsActivity.class);
            intent.putExtra("imageUrl", card.getImageUrl());
            intent.putExtra("title", card.getTitle());
            intent.putExtra("description", card.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        ImageView imageView;

        public CardViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.card_title);
            descriptionTextView = itemView.findViewById(R.id.card_description);
            imageView = itemView.findViewById(R.id.card_image);
        }
    }
}
