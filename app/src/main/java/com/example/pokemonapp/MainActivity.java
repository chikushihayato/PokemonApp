package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Pokemon> Pokemons = new ArrayList<Pokemon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        PokemonGetThread PokemonGet = new PokemonGetThread(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                PokemonGet.start();
                try {
                    PokemonGet.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // PokemonGetの処理が完了した後に実行される
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayout linearLayout = findViewById(R.id.Load);
                        linearLayout.setVisibility(View.GONE);

                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        recyclerView.setAdapter(new MyAdapter(Pokemons));
                    }
                });
            }
        });

        thread.start();

//        try {
//            PokemonGet.join();
//        } catch (InterruptedException e) {
//            // 例外処理
//            e.printStackTrace();
//        }

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), Pokemons));
    }

    public void setItems(List<Pokemon> pokemons) {
        this.Pokemons = pokemons;
    }

}