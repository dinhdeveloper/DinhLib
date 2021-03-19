package com.library.tcd.extensions.alert;

import android.content.Context;

public class AlertSuccess {

    public static void showAlertSuccess(Context context, String titleText, String contentText){
        new AlertDialog(context, AlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .show();
    }

    public static void showAlertSuccess(Context context, String titleText){
        new AlertDialog(context, AlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .show();
    }
}
