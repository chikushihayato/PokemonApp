package com.example.pokemonapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends Thread{

    private MainActivity mainActivity;

    public ThreadTest(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

            @Override
            public void run() {

                List<Pokemon> pokemons = new ArrayList<Pokemon>();

                for (int i = 1; i <= 151; i++) {
                    int pokemonId = i; // ポケモンのID

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

                            JSONObject jsonObject = new JSONObject(response.toString());

                            String name = jsonObject.getString("name");
                            int id = jsonObject.getInt("id");
                            String imageUrl = jsonObject.getJSONObject("sprites").getString("front_default");
                            Log.d("PokemonData","image" + jsonObject.getJSONObject("sprites"));

                        pokemons.add(new Pokemon(name,id,imageUrl));

                        } else {
                            Log.d("PokemonData", "API communication error: " + responseCode);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                mainActivity.setItems(pokemons);
            }
}
