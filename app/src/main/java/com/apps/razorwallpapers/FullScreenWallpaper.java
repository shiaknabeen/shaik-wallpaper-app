package com.apps.razorwallpapers;

import androidx.appcompat.app.AppCompatActivity;
import com.facebook.ads.*;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;


import com.facebook.ads.AudienceNetworkAds;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;


public class FullScreenWallpaper extends AppCompatActivity {
    String originalUrl = "";
    PhotoView photoView;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private final String TAG = FullScreenWallpaper.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_wallpaper);


        final LoadingDialog loadingDialog = new LoadingDialog(FullScreenWallpaper.this);
        loadingDialog.LoadingAlertDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.deleteDialog();
            }
        }, 15000);

///////////////////////////facebookBanner/////////////////////////////////////////////////////

        AudienceNetworkAds.initialize(this);

        adView = new AdView(this, "485558255780380_485559199113619", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
 //////////////////////////facebookBanner///////////////////////////////////////////////////////////////////


//////////////////////////facebookInterstitial///////////////////////////////////////////////////////////////////
        interstitialAd = new InterstitialAd(this, "485558255780380_485558402447032");
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown


//////////////////////////facebookInterstitial///////////////////////////////////////////////////////////////////

        getSupportActionBar().hide();
        Intent intent = getIntent();

        originalUrl = intent.getStringExtra("originalUrl");
        photoView = findViewById(R.id.photoView);

        Glide.with(this).load(originalUrl).into(photoView);


    }

    public void SetWallpaperEvent(View view) {

        final LoadingDialog2 loadingDialog = new LoadingDialog2(FullScreenWallpaper.this);
        loadingDialog.LoadingAlertDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.deleteDialog();
            }
        }, 5000);


        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Bitmap bitmap = ((BitmapDrawable) photoView.getDrawable()).getBitmap();


        try {


            wallpaperManager.setBitmap(bitmap);
            if (loadingDialog != null) {

                Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        @Override
        protected void onDestroy () {
            if (interstitialAd != null) {
                interstitialAd.destroy();
            }
            super.onDestroy();
        }
    public void Download(View view) {

        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(originalUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
        Toast.makeText(this, "Downloading Start", Toast.LENGTH_SHORT).show();
    }



}