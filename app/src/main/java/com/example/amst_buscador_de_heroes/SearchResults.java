package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class SearchResults extends AppCompatActivity {

    private String superhero = "";
    final String tokenAPI = "4159910954087857";
    private ArrayList<String> searchResults;
    private Map<String,String> nombreToid;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mQueue = Volley.newRequestQueue(this);

        Intent searchResultsView = getIntent();
        this.superhero = (String) searchResultsView.getExtras().get("superhero");

        searchResults = new ArrayList<>();
        nombreToid = new HashMap<>();

        buscarHeroe(this.superhero);
    }

    protected void llenarArrayNombres(JSONArray resultsJSON) throws JSONException {
        for (int i = 0; i < resultsJSON.length(); i++)
        {
            String nombre = resultsJSON.getJSONObject(i).getString("name");
            String id = resultsJSON.getJSONObject(i).getString("id");
            this.searchResults.add(nombre);
            this.nombreToid.put(nombre,id);
        }
        presentarListaNombres(this.searchResults,this.nombreToid);
    }

    protected void presentarListaNombres(ArrayList<String> searchResults, Map<String,String> nombreToid){
        ListView list_heroes = (ListView) findViewById(R.id.list_view_heroes);
        TextView total_resultados = (TextView) findViewById(R.id.text_count_results);
        total_resultados.setText(Integer.toString(searchResults.size()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,searchResults);
        list_heroes.setAdapter(adapter);

        list_heroes.setClickable(true);

        list_heroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String nombre = (String) parent.getItemAtPosition(position);
                String ID = nombreToid.get(nombre);
                toView_HeroeProfile(ID);
                }
            });
        }

    public void buscarHeroe(String superhero){
        String url = "https://superheroapi.com/api/"+tokenAPI+"/search/"+superhero;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                    //System.out.println(response);
                    try {
                        JSONArray results = response.getJSONArray("results");
                        llenarArrayNombres(results);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error

                });
        mQueue.add(jsonObjectRequest);
    }

    public void toView_HeroeProfile(String ID) {
        Intent heroe_profile = new Intent(getBaseContext(),HeroeProfile.class);
        heroe_profile.putExtra("id", ID);
        startActivity(heroe_profile);
    }
}