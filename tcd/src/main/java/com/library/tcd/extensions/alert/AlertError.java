package com.library.tcd.extensions.alert;

import android.content.Context;

public class AlertError {
    public static void showAlertError(Context context, String titleText, String contentText){
        new AlertDialog(context, AlertDialog.ERROR_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .show();
    }

    public static void showAlertError(Context context, String titleText){
        new AlertDialog(context, AlertDialog.ERROR_TYPE)
                .setTitleText(titleText)
                .show();
    }
}
