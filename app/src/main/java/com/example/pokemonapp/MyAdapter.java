package com.example.pokemonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Pokemon> Pokemons;

    public MyAdapter(Context context, List<Pokemon> Pokemons) {
        this.context = context;
        this.Pokemons = Pokemons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int id = Pokemons.get(position).getId();
        String numText = String.valueOf(id);
        holder.idView.setText("No. " + numText);
        String imageUrl = Pokemons.get(position).getImageUrl();
        Glide.with(holder.itemView).load(imageUrl).into(holder.pokemonView);
        holder.nameView.setText(Pokemons.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Pokemons.size();
    }
}
