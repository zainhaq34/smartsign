package com.glomaxsolutions.smartsign_player.Controller;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.glomaxsolutions.smartsign_player.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 5000;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar_circuler);
        SplashStartUp();
    }

    //TODO Splash Start up Method
    private void SplashStartUp() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(SplashActivity.this, CampaignActivity.class));
                progressBar.setVisibility(View.GONE);
                // close this activity
                finish();

            }

        }, SPLASH_TIME_OUT);

    }
}