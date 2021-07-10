package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.amst_buscador_de_heroes.PowerStats;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

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
        this.idSuperhero = (String) HeroeProfileView.getExtras().get("id");//confirma :v
        System.out.println("------------------------------");
        System.out.println(idSuperhero);
        System.out.println("------------------------------");

        searchHeroProfile("70");

        for (int i =0; i < powerstatsArrayList.size();i++){
            String stat = powerstatsArrayList.get(i).getStat();
            int values = powerstatsArrayList.get(i).getValue();
            barEntriesArrayList.add(new BarEntry(i,values));
            labelName.add(stat);
        }

        BarDataSet barDataSet = new BarDataSet(barEntriesArrayList,"Power Stats");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("Stats");
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        XAxis xAxis =  barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelName));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelName.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();
    }

    public void toMPAndroid(JSONObject resultsJSON) throws JSONException {
        this.nameSuperHero = resultsJSON.getString("name");
        this.fullNameSuperHero = resultsJSON.getJSONObject("biography").getString("full-name");
        powerstatsSuperHero = resultsJSON.getJSONObject("powerstats");
        powerstatsArrayList.clear();
        powerstatsArrayList.add(new PowerStats("Intelligence",Integer.parseInt(powerstatsSuperHero.getString("intelligence"))));
        powerstatsArrayList.add(new PowerStats("Strength",Integer.parseInt(powerstatsSuperHero.getString("strength"))));
        powerstatsArrayList.add(new PowerStats("Durability",Integer.parseInt(powerstatsSuperHero.getString("durability"))));
        powerstatsArrayList.add(new PowerStats("Power",Integer.parseInt(powerstatsSuperHero.getString("power"))));
        powerstatsArrayList.add(new PowerStats("Combat",Integer.parseInt(powerstatsSuperHero.getString("combat"))));
    }

    public void searchHeroProfile(String idSuperhero){
        String url = "https://superheroapi.com/api/"+tokenAPI+"/"+idSuperhero;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    System.out.println(response);
                    try {
                        toMPAndroid(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error

                });
        mQueue.add(jsonObjectRequest);
    }

}