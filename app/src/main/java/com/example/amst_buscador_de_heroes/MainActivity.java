package com.example.amst_buscador_de_heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue = null;
    final String tokenAPI = "4159910954087857";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);

    }

    public void toView_SearchResults(View v) {
        Intent search_result_activity = new Intent(getBaseContext(), SearchResults.class);
        String superhero = ((EditText)findViewById(R.id.editText_input_nombre)).getText().toString();
        search_result_activity.putExtra("superhero", superhero);
        startActivity(search_result_activity);
    }

}