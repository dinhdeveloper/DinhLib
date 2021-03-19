package com.library.tcd.extensions.alert;

import android.content.Context;

public class AlertConfirm {
    private static ConfirmClickListener listener;
    private static ConfirmSuccessListener listener1;

    public interface ConfirmClickListener {
        void setConfirmClickListener(AlertDialog dialog);

        void setCancelButton(AlertDialog dialog);
    }

    public interface ConfirmSuccessListener {
        void setConfirmClickSuccessListener(AlertDialog dialog);
    }

    public void ConfirmClickListener(ConfirmSuccessListener listener) {
        this.listener1 = listener;
    }

    public void setConfirmClickListener(ConfirmClickListener listener) {
        this.listener = listener;
    }

    public static void showAlertConfirm(Context context, String titleText, String contentText, final ConfirmSuccessListener confirmSuccessListener) {
        new AlertDialog(context, AlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmClickListener(new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sweetAlertDialog) {
                        if (confirmSuccessListener != null)
                            confirmSuccessListener.setConfirmClickSuccessListener(sweetAlertDialog);
                    }
                });
    }

    public static void showAlertConfirm(Context context, int type, String titleText, String contentText, String confirmText, String cancelText, final ConfirmClickListener clicklistener) {
        new AlertDialog(context, type)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setCancelText(cancelText)
                .setConfirmClickListener(sDialog -> {
                    if (clicklistener != null) {
                        clicklistener.setConfirmClickListener(sDialog);
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelButton(cancelText, sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    public static void showAlertWarning(Context context, String titleText, String contentText, final ConfirmClickListener clicklistener) {
        new AlertDialog(context, AlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmClickListener(sweetAlertDialog -> {
                    if (clicklistener!=null)
                        clicklistener.setConfirmClickListener(sweetAlertDialog);
                })
                .setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                }).show();
    }
    public static void showAlertSuccess(Context context, String titleText, String contentText, final ConfirmClickListener clicklistener) {
        new AlertDialog(context, AlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmClickListener(sweetAlertDialog -> {
                    if (clicklistener!=null)
                        clicklistener.setConfirmClickListener(sweetAlertDialog);
                })
                .setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                }).show();
    }
}
