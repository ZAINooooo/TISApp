package com.example.tisapp.Splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.BaseActivity;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.PrefManager;
import com.example.tisapp.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {


    private PrefManager prefManager;

    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    MaterialStyledDialog.Builder dialogHeader_3;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_splash);

            progressBar = (ProgressBar) findViewById(R.id.progressBar);

            progressBar.getProgressDrawable().setColorFilter(Color.MAGENTA ,PorterDuff.Mode.SRC_IN);
            progressBar.setProgress(0);

            final long period = 50;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //this repeats every 100 ms
                    if (i < 100) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                textView.setText(String.valueOf(i) + "%");
                            }
                        });
                        progressBar.setProgress(i);
                        i++;
                    } else {
                        //closing the timer
                        timer.cancel();
//                        Intent intent =new Intent(Splash.this,MainActivity.class);
//                        startActivity(intent);
//                        // close this activity

       startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
                    }
                }
            }, 0, period);
        }

//        }


    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }
}

