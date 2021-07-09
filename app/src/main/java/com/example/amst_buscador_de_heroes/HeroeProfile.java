package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.amst_buscador_de_heroes.PowerStats.*;

public class HeroeProfile extends AppCompatActivity {
    private String idSuperhero = "";
    private RequestQueue mQueue = null;
    private JSONObject powerstatsSuperHero = null;
    private String nameSuperHero = "";
    private String fullNameSuperHero = "";

    private BarChart barChart;
    private ArrayList<BarEntry> barEntriesArrayList;
    private ArrayList<String > labelName;
    private ArrayList<PowerStats> powerstatsArrayList = new ArrayList<>();

    final String tokenAPI = "4159910954087857";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroe_profile);
        barChart = findViewById(R.id.barChart);
        barEntriesArrayList = new ArrayList<>();
        labelName  = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        Intent HeroeProfileView = getIntent();
        this.idSuperhero = (String) HeroeProfileView.getExtras().get("id");
        System.out.println(idSuperhero);

    }

    public void toMPAndroid(JSONObject resultsJSON) throws JSONException {
        this.nameSuperHero = resultsJSON.getString("name");
        this.fullNameSuperHero = resultsJSON.getJSONObject("biography").getString("full-name");
        this.powerstatsSuperHero = resultsJSON.getJSONObject("powerstats");

    }

    public void searchHeroProfile(String idSuperhero){
        String url = "https://superheroapi.com/api/"+tokenAPI+"/id/"+idSuperhero;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    System.out.println(response);
                    try {
                        JSONObject results = response;
                        toMPAndroid(results);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error

                });
        mQueue.add(jsonObjectRequest);
    }

}