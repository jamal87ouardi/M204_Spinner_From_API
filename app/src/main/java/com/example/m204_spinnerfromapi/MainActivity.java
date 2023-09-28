package com.example.m204_spinnerfromapi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    //JSONArray sa;

    JSONArray sa = new JSONArray();


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








        String url="https://run.mocky.io/v3/7d5252d4-baae-4133-9af2-60820273fa0b";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest joRequest=new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        sa = response;

                        ArrayList<String> listCars = new ArrayList<String>();

                        try {
                            for (int i = 0; i < sa.length(); i++) {


                                    String name = sa.getJSONObject(i).getString("make")+" "+sa.getJSONObject(i).getString("model");
                                    listCars.add(name);

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        Spinner sp = findViewById(R.id.cars);
                        ArrayAdapter<String> AA = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item,listCars);
                        sp.setAdapter(AA);

                        sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                try {
                                            String name = sa.getJSONObject(position).getString("make")+" "+sa.getJSONObject(position).getString("model");
                                            String year = sa.getJSONObject(position).getString("year");
                                            String price = sa.getJSONObject(position).getString("price");
                                            String url = sa.getJSONObject(position).getString("image");

                                            TextView textname = findViewById(R.id.name);
                                            textname.setText(name);

                                            TextView textyear = findViewById(R.id.year);
                                            textyear.setText(year);

                                            TextView textPrice = findViewById(R.id.price);
                                            textPrice.setText(price);

                                            ImageView imageView = findViewById(R.id.image);

                                            Picasso.get().load(url).into(imageView);



                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        }));



                    }},

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(joRequest);

        //JSONArray data = ;














    }
}