package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import android.content.Intent;

public class SearchResults extends AppCompatActivity {
    protected ArrayList<String> searchResults = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent searchResultsView = getIntent();
        this.searchResults = (ArrayList<String>) searchResultsView.getExtras().get("results");
        System.out.println(searchResults.toString());
    }
}