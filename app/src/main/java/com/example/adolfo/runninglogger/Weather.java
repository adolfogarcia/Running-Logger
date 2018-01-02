package com.example.adolfo.runninglogger;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Adolfo on 12/31/2017.
 * API KEY: 744153e88cacf84c33bb2252bd6514de
 */

public class Weather {
    Weather(Context currContext) {
        String url = "http://api.openweathermap.org/data/2.5/weather?id=5814921&APPID=744153e88cacf84c33bb2252bd6514de";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject mainObject = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject obj = array.getJSONObject(0);
                    temperature = String.valueOf(mainObject.getDouble("temp"));
                    description = obj.getString("description");
                    city = response.getString("name");
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
        );

        RequestQueue queue = Volley.newRequestQueue(currContext);
        queue.add(jor);
    }

    public String getTemperature()
    {
        return temperature;
    }

    public String getDescription()
    {
        return description;
    }

    public String getCity()
    {
        return city;
    }

    private String temperature;
    private String description;
    private String city;

}