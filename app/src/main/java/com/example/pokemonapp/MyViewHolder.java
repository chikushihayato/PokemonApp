package com.example.pokemonapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView idView,nameView;
    ImageView pokemonView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        idView = itemView.findViewById(R.id.idView);
        pokemonView = itemView.findViewById(R.id.pokemonView);
        nameView = itemView.findViewById(R.id.nameView);

        itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = getAdapterPosition(); // クリックされたアイテムの位置を取得
                if (position != RecyclerView.NO_POSITION) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, transitionActivity.class);

                    MainActivity mainActivity = (MainActivity) v.getContext();
                    Pokemon selectedPokemon = mainActivity.Pokemons.get(position);

                    // Intent に値を渡す
                    intent.putExtra("pokemonName", selectedPokemon.getName());
                    intent.putExtra("pokemonId", selectedPokemon.getId());
                    intent.putExtra("pokemonImageUrl", selectedPokemon.getImageUrl());

                context.startActivity(intent);
            }
        }
        });
    }
}
