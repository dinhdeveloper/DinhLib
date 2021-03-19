package com.library.tcd.extensions.alert;

import android.content.Context;
import android.graphics.Color;


public class AlertLoading {
    public static void showAlertLoading(Context context, String colorLoading, String titleText){
        AlertDialog pDialog = new AlertDialog(context, AlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoading(Context context, String colorLoading){
        AlertDialog pDialog = new AlertDialog(context, AlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context, String colorLoading){
        AlertDialog pDialog = new AlertDialog(context, AlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context, String colorLoading, String titleText){
        AlertDialog pDialog = new AlertDialog(context, AlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}
