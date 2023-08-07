package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < 151; i++) {
            items.add(new Item(R.drawable.monster_ball));
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}