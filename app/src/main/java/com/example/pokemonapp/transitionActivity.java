package com.example.pokemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class transitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_pokemon_data);

        TextView idTextView = findViewById(R.id.transition_id);
        TextView nameTextView = findViewById(R.id.transition_name);
        ImageView imageView = findViewById(R.id.transition_image);

        // Intent から値を受け取る
        Intent intent = getIntent();
        if (intent != null) {
            String pokemonName = intent.getStringExtra("pokemonName");
            int pokemonId = intent.getIntExtra("pokemonId", -1);
            String pokemonImageUrl = intent.getStringExtra("pokemonImageUrl");

            // 値を TextView と ImageView にセット
            if (pokemonName != null) {
                nameTextView.setText(pokemonName);
            }
            if (pokemonId != -1) {
                String numText = "No. " + pokemonId;
                idTextView.setText(numText);
            }
            if (pokemonImageUrl != null) {
                Glide.with(this).load(pokemonImageUrl).into(imageView);
            }
        }
    }
}
