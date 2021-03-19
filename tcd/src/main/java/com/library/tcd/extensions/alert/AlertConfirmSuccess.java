package com.library.tcd.extensions.alert;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertConfirmSuccess {

    private static ConfirmClickListener listener;
    public interface ConfirmClickListener {
        void setConfirmClickListener(AlertDialog dialog);
    }
    public void setConfirmClickListener(ConfirmClickListener listener) {
        this.listener = listener;
    }

    public static void showAlertConfirmSuccess(Context context, String titleText, String contentText, String confirmText) {
        new AlertDialog(context, AlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        if (listener!=null)
                        {
                            sDialog.dismissWithAnimation();
                            listener.setConfirmClickListener(sDialog);
                        }
                    }
                })
                .setCancelButton("Cancel", new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    public static void showAlertConfirmSuccess(Context context, String titleText, String contentText, String confirmText, final ConfirmClickListener clicklistener) {
        new AlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        clicklistener.setConfirmClickListener(sDialog);
//                        sDialog
//                                .setTitleText("Deleted!")
//                                .setContentText("Your imaginary file has been deleted!")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();
    }
}
