package com.example.tisapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//import com.example.tisapp.Receivers.NetworkChangeReceiver;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.google.firebase.iid.FirebaseInstanceId;
//import com.meezandev.uhfsolution.Utils.LogUtil;


public class BaseActivity extends AppCompatActivity {
//    private NetworkChangeReceiver networkChangeReceiver;
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected Context context;
    private ProgressDialog progressDialog;
    protected SweetAlertDialog pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

//        networkChangeReceiver = new NetworkChangeReceiver();

//        Util.e(TAG, "FCM ID " + FirebaseInstanceId.getInstance().getToken());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        LogUtil.e(TAG, "BaseActivity onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void showDialog() {
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        dismissDialog();

        super.onDestroy();
    }




//    private void registerNetworkBroadcastForNougat() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//    }

//    public void unregisterNetworkChanges() {
//        try {
//            unregisterReceiver(networkChangeReceiver);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }

}
