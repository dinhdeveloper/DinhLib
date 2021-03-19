package com.project.dinhlib;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.library.tcd.extensions.alert.CustomAlertDialog;
import com.library.tcd.extensions.cookiebar.CookieBar;
import com.library.tcd.extensions.countdown.CountDownView;
import com.library.tcd.extensions.date_picker.DatePicker;
import com.library.tcd.extensions.date_picker.DatePickerDialog;
import com.library.tcd.extensions.date_picker.SpinnerDatePickerDialogBuilder;
import com.library.tcd.extensions.explosionfield.ExplosionField;
import com.library.tcd.extensions.format_editext.FormattedEditText;
import com.library.tcd.extensions.ksnack.KSnack;
import com.library.tcd.extensions.ksnack.KSnackBarEventListener;
import com.library.tcd.extensions.ksnack.Slide;
import com.library.tcd.extensions.proswipebutton.ProSwipeButton;
import com.library.tcd.extensions.toast.Toasty;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerDialog.OnDateCancelListener{

    CountDownView view_count_down;
    private KSnack kSnack;

    Button startcountdown;
    Button button_error_toast;
    Button button_success_toast;
    Button noty;
    Button open_dialog;
    Button btnTop;
    Button set_date_button;
    FormattedEditText formattedEditText;

    SimpleDateFormat simpleDateFormat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kSnack = new KSnack(MainActivity.this);

        startcountdown = findViewById(R.id.startcountdown);
        button_error_toast = findViewById(R.id.button_error_toast);
        button_success_toast = findViewById(R.id.button_success_toast);
        view_count_down = findViewById(R.id.view_count_down);
        noty = findViewById(R.id.noty);
        open_dialog = findViewById(R.id.open_dialog);
        set_date_button = findViewById(R.id.set_date_button);
        formattedEditText = findViewById(R.id.formattedEditText);
        btnTop = findViewById(R.id.btnTop);

        final ProSwipeButton proSwipeBtn = findViewById(R.id.proswipebutton_main);
        final ProSwipeButton proSwipeBtnError = findViewById(R.id.proswipebutton_main_error);
        proSwipeBtn.setSwipeDistance(0.5f);

        onClick();

        simpleDateFormat = new SimpleDateFormat("dd MM yyyy",Locale.US);

        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        proSwipeBtn.showResultIcon(true, true);
                        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        proSwipeBtnError.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        proSwipeBtnError.showResultIcon(false, false);
                    }
                }, 2000);
            }
        });
    }

    private void onClick() {
        //todo countdown
        startcountdown.setOnClickListener(v -> {
            view_count_down.setStartDuration(5000); // 1h =3600000
            view_count_down.start();
        });

        view_count_down.setListener(() -> {
            //Toast.makeText(this, ""+view_count_down.getTime(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        });

        //todo error toast
        button_error_toast.setOnClickListener(view -> Toasty.error(MainActivity.this, "Lỗi", Toasty.LENGTH_SHORT, true).show());
        //todo success toast
        button_success_toast.setOnClickListener(view -> Toasty.success(MainActivity.this, "Thành công", Toasty.LENGTH_SHORT, true).show());


        //todo toast bottom
        noty.setOnClickListener(v -> {
            kSnack.setListener(new KSnackBarEventListener() {
                @Override
                public void showedSnackBar() {
                    System.out.println("Showed");
                }

                @Override
                public void stoppedSnackBar() {
                    System.out.println("Stopped");
                }
            })
                    .setAction("Click", v1 -> {
                        kSnack.dismiss();
                    })
                    .setButtonTextColor(R.color.colorAccent)
                    .setMessage("This is KSnack !")
                    .setAnimation(Slide.Up.getAnimation(kSnack.getSnackView()), Slide.Down.getAnimation(kSnack.getSnackView()))
                    .show();
        });

        //todo show alert
        open_dialog.setOnClickListener(v -> {
            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("You won't be able to recover this file!")
                    .setConfirmText("Delete!")
                    .setConfirmClickListener(new CustomAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(CustomAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();

                            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.WARNING_TYPE)
                                    .setTitleText("Are you sure?")
                                    .setContentText("Won't be able to recover this file!")
                                    .setConfirmText("Yes,delete it!")
                                    .setConfirmClickListener(new CustomAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(CustomAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Deleted!")
                                                    .setContentText("Your imaginary file has been deleted!")
                                                    .setConfirmText("OK")
                                                    .setConfirmClickListener(null)
                                                    .changeAlertType(CustomAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();
                        }
                    })
                    .setCancelButton("Cancel", new CustomAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(CustomAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                        }
                    })
                    .show();
        });

        //todo show editext for phone number
        formattedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, ""+formattedEditText.getRealText(), Toast.LENGTH_SHORT).show();
            }
        });

        //todo show top alert
        btnTop.setOnClickListener(v -> {
            CookieBar.build(MainActivity.this)
                    .setTitle("Đơn hàng mới")
                    .setTitleColor(R.color.progressColor)
                    .setMessage("Bạn có đơn hàng mới")
                    .setIcon(R.drawable.ic_baseline_notifications_48)
                    .setCookiePosition(CookieBar.TOP)
                    .setBackgroundColor(R.color.color_info)
                    .setDuration(5000).show();
        });

        //todo set date
        set_date_button.setOnClickListener(v -> {
            showDate(1980, 0, 1, R.style.DatePickerSpinner);
        });
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(MainActivity.this)
                .callback(MainActivity.this)
                .onCancel( MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        Toast.makeText(this, ""+simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCancelled(DatePicker view) {
        //TODO hủy kết quả, xóa ngày
    }


}