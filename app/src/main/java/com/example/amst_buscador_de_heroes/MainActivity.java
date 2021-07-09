package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue = null;
    final String tokenAPI = "4159910954087857";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);

    }

    public void goToSearchResults(View v){
        final EditText superhero = (EditText) findViewById(R.id.editText_input_nombre);
        String strSuperhero = superhero.getText().toString();
        buscarHeroe(strSuperhero);
    }

    public void toView_SearchResults(JSONArray resultsJSON) throws JSONException {
        //Enviando el token a través de las vistas Main->Search Results
        ArrayList<String> results = new ArrayList<>();
        Intent searchResultsView = new Intent(getBaseContext(), SearchResults.class);
        for (int i = 0; i < resultsJSON.length(); i++)
        {
            String nameSuperHero = resultsJSON.getJSONObject(i).getString("name");
            results.add(nameSuperHero);
        }
        searchResultsView.putStringArrayListExtra("results", results);
        startActivity(searchResultsView);
    }

    public void buscarHeroe(String superhero){
        String url = "https://superheroapi.com/api/"+tokenAPI+"/search/"+superhero;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            JSONArray results = response.getJSONArray("results");
                            toView_SearchResults(results);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        mQueue.add(jsonObjectRequest);
    }
}