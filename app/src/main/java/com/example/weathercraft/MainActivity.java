package com.example.weathercraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

TextView locationTV,updateTimeTV, statusTV,tempTV,
        minTV,maxTV,feelTV,sunriseTV,sunsetTV,windSpeedTV,
    pressureTV, humidityTV,seaLvlTV;
ImageView iconIV;

androidx.appcompat.widget.SearchView searchSV;

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appID = "d533209fc83259f4973c327ff7dc5836";

    private String cityName = "muscat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTV = findViewById(R.id.tv_location);
        updateTimeTV = findViewById(R.id.tv_update_time);
        statusTV = findViewById(R.id.tv_status);
        tempTV = findViewById(R.id.tv_temp);
        minTV = findViewById(R.id.tv_min_temp);
        maxTV = findViewById(R.id.tv_max_temp);
        feelTV = findViewById(R.id.tv_feels_like);
        sunriseTV = findViewById(R.id.tvSunrise);
        sunsetTV = findViewById(R.id.tvSunset);
        windSpeedTV = findViewById(R.id.tvWind);
        pressureTV = findViewById(R.id.tvPressure);
        humidityTV = findViewById(R.id.tvHumidity);
        seaLvlTV = findViewById(R.id.tvSeaLvl);

        iconIV = findViewById(R.id.img_weather);

        searchSV = findViewById(R.id.searchView);

        searchSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=null){
                    cityName = query;
                }
                getWeatherDetails(cityName);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        getWeatherDetails(cityName);
    }

    private void getWeatherDetails(String city) {

        String tempURL = url + "?q=" + city + "&appid=" + appID;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempURL, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jRespond = new JSONObject(response);
                    JSONArray jArray =jRespond.getJSONArray("weather");
                    JSONObject jOWeather =jArray.getJSONObject(0);
                    JSONObject jsonOMain = jRespond.getJSONObject("main");
                    JSONObject jOWind = jRespond.getJSONObject("wind");
                    JSONObject jOSys = jRespond.getJSONObject("sys");

                    String loc = jRespond.getString("name");
                    locationTV.setText(loc);

                    long timestamp = jRespond.getLong("dt");
                    java.util.Date dt = new java.util.Date(timestamp * 1000); // Convert seconds to milliseconds
                    SimpleDateFormat sdd = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
                    String formattedTime = sdd.format(dt);
                    updateTimeTV.setText("Last Update, Time: " +formattedTime);

                    long sunriseTimestamp = jOSys.getLong("sunrise");
                    long sunsetTimestamp = jOSys.getLong("sunset");

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
                    String sunriseTime = sdf.format(new Date(sunriseTimestamp * 1000));
                    String sunsetTime = sdf.format(new Date(sunsetTimestamp * 1000));

                    sunriseTV.setText(sunriseTime);
                    sunsetTV.setText(sunsetTime);

                    double windy = jOWind.getDouble("speed");
                    windSpeedTV.setText((windy)+" KM/H");

                    String desc = jOWeather.getString("description");
                    String capitalizedDesc = desc.substring(0, 1).toUpperCase() + desc.substring(1);
                    statusTV.setText(capitalizedDesc);

                    String iconImg = jOWeather.getString("icon");
                    String imgURL = "https://openweathermap.org/img/w/" + iconImg + ".png";
                    Log.d("WeatherApp", "Image URL: " + imgURL);
                    Picasso.get().load(imgURL).into(iconIV);

                    double temp = jsonOMain.getDouble("temp") -273.15;
                    tempTV.setText(((int) temp) + "°C");
                    double minTemp = jsonOMain.getDouble("temp_min") -273.15;
                    minTV.setText("Min Temp: " +((int) minTemp) + "°C");
                    double maxTemp = jsonOMain.getDouble("temp_max") -273.15;
                    maxTV.setText("Max Temp: " +((int) maxTemp) + "°C");
                    double feelTemp = jsonOMain.getDouble("feels_like") -273.15;
                    feelTV.setText("Feel Like: " +((int) feelTemp) + "°C");
                    int pressure = jsonOMain.getInt("pressure");
                    pressureTV.setText((pressure)+"hPa");
                    int humidity = jsonOMain.getInt("humidity");
                    humidityTV.setText((humidity)+"%");
                    int seaLvl = 0;
                    if (jsonOMain.has("sea_level")) {
                        seaLvl = jsonOMain.getInt("sea_level");
                    }
                    seaLvlTV.setText((seaLvl) + "m");

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}