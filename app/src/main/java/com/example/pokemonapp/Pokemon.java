package com.example.pokemonapp;

public class Pokemon {
    String name;
    int id;
    String imageUrl;

    public Pokemon(String name, int id, String imageUrl) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
