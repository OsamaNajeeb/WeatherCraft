package com.example.weathercraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

ImageView splashIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashIV = findViewById(R.id.ivSplash);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        splashIV.setAlpha(0f);
        splashIV.animate().setDuration(3000).alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                finish();
            }
        });

    }
}