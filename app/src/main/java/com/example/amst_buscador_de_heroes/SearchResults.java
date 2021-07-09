package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResults extends AppCompatActivity {

    protected ArrayList<Map<String,String>> searchResults = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent searchResultsView = getIntent();

        this.searchResults = (ArrayList<Map<String, String>>) searchResultsView.getExtras().get("results");

        ArrayList<String> response_nombres = new ArrayList<>();
        for(int i = 0; i < response_nombres.size();i++){
            System.out.println(searchResults.get(i));
        }

        //System.out.println("Resultado: " + searchResults.toString());
       // llenarListView();

    }

    protected void llenarListView(){
        ListView list_heroes = (ListView) findViewById(R.id.list_view_heroes);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,this.searchResults);
        //list_heroes.setAdapter(adapter);

        list_heroes.setClickable(true);
        list_heroes.setOnItemClickListener(
                (adapterView, view, i, l) -> System.out.println("Click")

        );
    }

}