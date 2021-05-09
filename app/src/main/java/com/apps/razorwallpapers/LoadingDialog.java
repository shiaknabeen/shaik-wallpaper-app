package com.apps.razorwallpapers;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;


public class LoadingDialog {
   Activity activity;
   AlertDialog dialog;
   LoadingDialog(Activity myActivity){
       activity=myActivity;
   }
   void LoadingAlertDialog(){
       AlertDialog.Builder builder=new AlertDialog.Builder(activity);
       LayoutInflater inflater= activity.getLayoutInflater();
       builder.setView(inflater.inflate(R.layout.progress_bar,null));
       builder.setIcon(R.drawable.razorwallpaper);
       builder.setCancelable(false);
       dialog=builder.create();
       dialog.show();
   }
   void deleteDialog(){
       dialog.dismiss();
   }
}
