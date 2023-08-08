package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("test", "log");

        // ポケモンデータを取得してコンソールに出力
        new FetchPokemonDataTask().execute();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < 151; i++) {
            items.add(new Item(R.drawable.monster_ball));
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }

    private class FetchPokemonDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            int pokemonId = 1; // ポケモンのID

            String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonId + "/";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    // JSONデータから名前とID、画像のURLを取得
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String name = jsonObject.getString("name");
                    int id = jsonObject.getInt("id");
                    String imageUrl = jsonObject.getJSONObject("sprites").getString("front_default");

                    // 名前、ID、画像URLを組み立てて返す
                    return "Name: " + name + ", ID: " + id + ", Image URL: " + imageUrl;
                } else {
                    return "API communication error: " + responseCode;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("PokemonData", result); // 結果をログに出力
        }
    }
}