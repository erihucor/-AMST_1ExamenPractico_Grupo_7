package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchResults extends AppCompatActivity {

    protected ArrayList<String> searchResults = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent searchResultsView = getIntent();
        this.searchResults = (ArrayList<String>) searchResultsView.getExtras().get("results");
        //System.out.println("Resultado: " + searchResults.toString());
        llenarListView();

    }

    protected void llenarListView(){
        ListView list_heroes = (ListView) findViewById(R.id.list_view_heroes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,this.searchResults);
        list_heroes.setAdapter(adapter);

        list_heroes.setClickable(true);
        list_heroes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("Click");
                    }
                }

        );
    }

}