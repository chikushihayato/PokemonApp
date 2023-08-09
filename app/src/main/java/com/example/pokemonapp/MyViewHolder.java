package com.example.pokemonapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView idView,nameView;
    ImageView pokemonView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        idView = itemView.findViewById(R.id.idView);
        pokemonView = itemView.findViewById(R.id.pokemonView);
        nameView = itemView.findViewById(R.id.nameView);

//        idView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
//        nameView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
    }
}
