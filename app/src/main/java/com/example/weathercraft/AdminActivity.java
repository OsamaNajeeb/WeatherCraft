package com.example.weathercraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weathercraft.data.APIData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    EditText locationET,updateTimeET, statusET, apiIDET,tempET,
            minET,maxET,feelET,sunriseET,sunsetET,windSpeedET,
            pressureET, humidityET,seaLvlET;

    Button insertBTN,updateBTN,getBTN,deleteBTN,clearBTN,closeBTN;

    FirebaseDatabase fbDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        FirebaseApp.initializeApp(this);

        locationET = findViewById(R.id.etLocation);
        updateTimeET = findViewById(R.id.etUpdateTime);
        statusET = findViewById(R.id.etStatus);
        apiIDET = findViewById(R.id.etID);

        tempET = findViewById(R.id.etTemp);
        minET = findViewById(R.id.etMinTemp);
        maxET = findViewById(R.id.etMaxTemp);
        feelET = findViewById(R.id.etFeel);
        sunriseET = findViewById(R.id.etSunrise);
        sunsetET = findViewById(R.id.etSunset);
        windSpeedET = findViewById(R.id.etWind);
        pressureET = findViewById(R.id.etPressure);
        seaLvlET = findViewById(R.id.etSea);
        humidityET = findViewById(R.id.etHumidity);


        insertBTN = findViewById(R.id.btnInsert);
        getBTN = findViewById(R.id.btnGet);
        updateBTN = findViewById(R.id.btnUpdate);
        deleteBTN = findViewById(R.id.btnDelete);
        clearBTN = findViewById(R.id.btnReset);
        closeBTN = findViewById(R.id.btnClose);

        fbDB = FirebaseDatabase.getInstance();

        insertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationInput = locationET.getText().toString();
                String updateTimeInput = updateTimeET.getText().toString();
                String statusInput = statusET.getText().toString();
                String tempInput = tempET.getText().toString();
                String minInput = minET.getText().toString();
                String maxInput = maxET.getText().toString();
                String feelInput = feelET.getText().toString();
                String sunriseInput = sunriseET.getText().toString();
                String sunsetInput = sunsetET.getText().toString();
                String windInput = windSpeedET.getText().toString();
                String pressInput = pressureET.getText().toString();
                String humiInput = humidityET.getText().toString();
                String seaInput = seaLvlET.getText().toString();


                isValidInput(locationInput, updateTimeInput, statusInput,tempInput,minInput,
                        maxInput,feelInput,sunriseInput,sunsetInput,windInput,pressInput,humiInput,seaInput);
            }
        });

        getBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apiIDInput = apiIDET.getText().toString();

                DatabaseReference reff = fbDB.getReference("API").child(apiIDInput);

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            String location = dataSnapshot.child("location").getValue(String.class);
                            String updateTime = dataSnapshot.child("update_time").getValue(String.class);
                            String status = dataSnapshot.child("status").getValue(String.class);
                            String temp = dataSnapshot.child("temperature").getValue(String.class);
                            String minTemp = dataSnapshot.child("min_temp").getValue(String.class);
                            String maxTemp = dataSnapshot.child("max_temp").getValue(String.class);
                            String feelLike = dataSnapshot.child("feel_like").getValue(String.class);
                            String sunrise = dataSnapshot.child("sunrise").getValue(String.class);
                            String sunset = dataSnapshot.child("sunset").getValue(String.class);
                            String windy = dataSnapshot.child("wind_speed").getValue(String.class);
                            String press = dataSnapshot.child("pressure").getValue(String.class);
                            String humidity = dataSnapshot.child("humidity").getValue(String.class);
                            String sea = dataSnapshot.child("sea_level").getValue(String.class);


                            locationET.setText(location);
                            updateTimeET.setText(updateTime);
                            statusET.setText(status);
                            tempET.setText(temp);
                            minET.setText(minTemp);
                            maxET.setText(maxTemp);
                            feelET.setText(feelLike);
                            sunriseET.setText(sunrise);
                            sunsetET.setText(sunset);
                            windSpeedET.setText(windy);
                            pressureET.setText(press);
                            humidityET.setText(humidity);
                            seaLvlET.setText(sea);

                        } else {
                            locationET.setText("");
                            updateTimeET.setText("");
                            statusET.setText("");
                            tempET.setText("");
                            minET.setText("");
                            maxET.setText("");
                            feelET.setText("");
                            sunriseET.setText("");
                            sunsetET.setText("");
                            windSpeedET.setText("");
                            pressureET.setText("");
                            humidityET.setText("");
                            seaLvlET.setText("");

                            Toast.makeText(AdminActivity.this, "No API ID found\nPlease type again", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apiIDInput = apiIDET.getText().toString();
                DatabaseReference reff = fbDB.getReference("API").child(apiIDInput);

                String updatedLocation = locationET.getText().toString();
                String updatedUpdateTime = updateTimeET.getText().toString();
                String updatedStatus = statusET.getText().toString();
                String updatedTemp = tempET.getText().toString();
                String updatedMin = minET.getText().toString();
                String updatedMax = maxET.getText().toString();
                String updatedFeel = feelET.getText().toString();
                String updatedSunR = sunriseET.getText().toString();
                String updatedSunS = sunsetET.getText().toString();
                String updatedWind = windSpeedET.getText().toString();
                String updatedPress = pressureET.getText().toString();
                String updatedHum = humidityET.getText().toString();
                String updatedSea = seaLvlET.getText().toString();

                reff.child("location").setValue(updatedLocation);
                reff.child("update_time").setValue(updatedUpdateTime);
                reff.child("status").setValue(updatedStatus);
                reff.child("temperature").setValue(updatedTemp);
                reff.child("min_temp").setValue(updatedMin);
                reff.child("max_temp").setValue(updatedMax);
                reff.child("feel_like").setValue(updatedFeel);
                reff.child("sunrise").setValue(updatedSunR);
                reff.child("sunset").setValue(updatedSunS);
                reff.child("wind_speed").setValue(updatedWind);
                reff.child("pressure").setValue(updatedPress);
                reff.child("humidity").setValue(updatedHum);
                reff.child("sea_level").setValue(updatedSea);


                Toast.makeText(AdminActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apiIDInput = apiIDET.getText().toString();
                DatabaseReference reff = fbDB.getReference("API").child(apiIDInput);

                reff.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();

                            locationET.setText("");
                            updateTimeET.setText("");
                            statusET.setText("");
                            apiIDET.setText("");
                            tempET.setText("");
                            minET.setText("");
                            maxET.setText("");
                            feelET.setText("");
                            sunriseET.setText("");
                            sunsetET.setText("");
                            windSpeedET.setText("");
                            pressureET.setText("");
                            humidityET.setText("");
                            seaLvlET.setText("");

                        } else {
                            Toast.makeText(AdminActivity.this, "Failed to delete data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        clearBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationET.setText("");
                updateTimeET.setText("");
                statusET.setText("");
                tempET.setText("");
                minET.setText("");
                maxET.setText("");
                feelET.setText("");
                sunriseET.setText("");
                sunsetET.setText("");
                windSpeedET.setText("");
                pressureET.setText("");
                humidityET.setText("");
                seaLvlET.setText("");

                Toast.makeText(AdminActivity.this, "All textBox are cleared", Toast.LENGTH_LONG).show();
            }
        });

        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void isValidInput(String loc, String upti, String stats, String temp,
                              String mi, String ma, String felt, String sunrise,
                              String sunset, String windy, String press,
                              String homer, String sea) {
        final String apiID = loc + upti;
        DatabaseReference reff = fbDB.getReference("API").child(apiID);

        Task<DataSnapshot> task = reff.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    if (dataSnapshot.exists()) {
                        locationET.setText("");
                        updateTimeET.setText("");
                        statusET.setText("");
                        tempET.setText("");
                        minET.setText("");
                        maxET.setText("");
                        feelET.setText("");
                        sunriseET.setText("");
                        sunsetET.setText("");
                        windSpeedET.setText("");
                        pressureET.setText("");
                        humidityET.setText("");
                        seaLvlET.setText("");

                        Toast.makeText(AdminActivity.this, "Invalid input. API ID already exists.", Toast.LENGTH_SHORT).show();
                    } else {
                        getInsertData(loc, upti, stats, temp,
                                 mi, ma, felt, sunrise,
                                 sunset, windy, press,
                                 homer, sea);
                    }
                } else {

                    Toast.makeText(AdminActivity.this, "Error checking API ID existence.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getInsertData(String loc, String upti, String stats, String temp,
                               String mi, String ma, String felt, String sunrise,
                               String sunset, String windy, String press,
                               String homer, String sea) {
        DatabaseReference reff = fbDB.getReference("API");
        String apiID = loc + upti;
        DatabaseReference apiReff = reff.child(apiID);
        APIData newAPI = new APIData(loc, upti, stats,temp,mi,ma,felt,sunrise,
                sunset,windy,press,homer,sea);

        apiReff.child("location").setValue(newAPI.getLocationData());
        apiReff.child("update_time").setValue(newAPI.getUpdateTimeData());
        apiReff.child("status").setValue(newAPI.getStatusData());
        apiReff.child("temperature").setValue(newAPI.getTempData());
        apiReff.child("min_temp").setValue(newAPI.getMinData());
        apiReff.child("max_temp").setValue(newAPI.getMaxData());
        apiReff.child("feel_like").setValue(newAPI.getFeelData());
        apiReff.child("sunrise").setValue(newAPI.getSunriseData());
        apiReff.child("sunset").setValue(newAPI.getSunsetData());
        apiReff.child("wind_speed").setValue(newAPI.getWindSpeedData());
        apiReff.child("pressure").setValue(newAPI.getPressureData());
        apiReff.child("humidity").setValue(newAPI.getHumidityData());
        apiReff.child("sea_level").setValue(newAPI.getSeaLvlData());


        // Optionally, provide a success message
        Toast.makeText(AdminActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
    }
}