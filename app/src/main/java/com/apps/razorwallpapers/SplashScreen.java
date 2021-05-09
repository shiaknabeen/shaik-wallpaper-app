package com.apps.razorwallpapers;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {
    ImageView splashscreen;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        splashscreen = (ImageView) findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        splashscreen.startAnimation(animation);
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        myThread.start();

    }
}
