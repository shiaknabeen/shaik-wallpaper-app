package com.apps.razorwallpapers;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;


public class LoadingDialog2 {
    Activity activity;
    AlertDialog dialog;
    LoadingDialog2(Activity myActivity){
        activity=myActivity;
    }
    void LoadingAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater= activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progressbarforfull,null));
        builder.setIcon(R.drawable.razorwallpaper);
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }
    void deleteDialog(){
        dialog.dismiss();
    }
}
