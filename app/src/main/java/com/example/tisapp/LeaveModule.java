package com.example.tisapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.tisapp.Login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tisapp.Login.LoginActivity.IMEI;
import static com.example.tisapp.Login.LoginActivity.passwordsave;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;

//import static com.example.tisapp.Receivers.NetworkChangeReceiver.value345;
//import static com.example.tisapp.Receivers.NetworkChangeReceiver.value89;

public class LeaveModule extends AppCompatActivity {

    Button manual_entry,manual_time_out_is,manual_time_in_is,manual_time_out_iss , LEVESYNC;
    DatabaseHelper objDBHelper2;
    OkHttpClient client;

    ImageView iv_back;
    String value345,value89,responses,value2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_module);

        objDBHelper2 = new DatabaseHelper(this);


        manual_time_in_is = (Button) findViewById(R.id.btnDomestic);
        manual_time_out_is = (Button) findViewById(R.id.btnCommercial);
        manual_time_out_iss = (Button) findViewById(R.id.btnIndustrial);
//        LEVESYNC=(Button) findViewById(R.id.btnIndustrial2);


        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        if (isNetAvailable()) {
//            if (LoginActivity.value.equals("true"))
//            {
//
//                manual_time_out_iss.setVisibility(View.VISIBLE);
//            }
//            else
//            {
//                manual_time_out_iss.setVisibility(View.GONE);
//
//            }


            if (LoginActivity.value == null) {


//                SendTokenParamss();
                GetprofileParams();





            } else if (LoginActivity.value != null && LoginActivity.value.equals("true")) {
                manual_time_out_iss.setVisibility(View.VISIBLE);
            } else {
                manual_time_out_iss.setVisibility(View.GONE);
            }

////                value345
//                String token = "";
//                Log.d("TokenFromServer", "");
//
//                String isapprover = "";
//                Log.d("ApproverStatusServer", "");
//
//
//                if (isapprover != null) {
//
//                    if (isapprover.equals("")) {
//                        manual_approver_list.setVisibility(View.VISIBLE);
//                    } else {
//                        manual_approver_list.setVisibility(View.GONE);
//                    }
//
//
//                }
//
//                else
//                {
//                    manual_approver_list.setVisibility(View.GONE);
//                }
        } else {
            Cursor timeInRequestOfflineData = objDBHelper2.getDataProfile();

            if (timeInRequestOfflineData.moveToLast()) {
                String COL17 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL17));
                Log.d("IsApprover", "" + COL17);

                timeInRequestOfflineData.close();


                if (COL17.equals("true")) {
                    manual_time_out_iss.setVisibility(View.VISIBLE);
                } else {
//                    manual_time_out_iss.setVisibility(View.GONE);
                    manual_time_out_iss.setVisibility(View.GONE);

                }

            }
        }












//                if (isapprover != null) {
//
//                    if (isapprover.equals("true")) {
//                        manual_time_out_iss.setVisibility(View.VISIBLE);
//                    } else {
//                        manual_time_out_iss.setVisibility(View.GONE);
//                    }
//
//
//                }

//                else
//                {
//                    manual_time_out_iss.setVisibility(View.GONE);
//                }
           // }





































        manual_time_in_is.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(LeaveModule.this, fragment_leave_form.class));
                finish();

            }
        });


        manual_time_out_is.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(LeaveModule.this, fragment_manual_leave_request_list.class));
                finish();

            }

            ;

        });


//        LEVESYNC.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(LeaveModule.this, offlinesyncleave.class));
//                finish();
//            }
//
//            ;
//
//        });




//                manual_time_out_is.setOnClickListener(new View.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(View v) {
//
//                        startActivity(new Intent(ManualEntryModule.this, fragment_manual_Out_form.class));
//                        finish();
//
//                    };
//
//                                                      });


        manual_time_out_iss.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(LeaveModule.this, fragment_approved_leave_request_list.class));
                finish();

            }

            ;

        });










    }

    private void GetprofileParams() {

        if (urls2 == null) {

        } else {
            client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

            final String password = passwordsave;

            RequestBody formBody = new FormBody.Builder().add("IMEI", IMEI).add("Password", password).build();

            final Request request = new Request.Builder()
                    .url(urls + "register?IMEI=" + IMEI + "&Password=" + password)
                    .post(formBody).addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
//                        }
//                    });

                    Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                    e.printStackTrace();


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Cant Connect To Server1")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                            _passwordText.setText("");
//                                            _loginButton.setEnabled(true);
//                                        }
//                                    });
//                            dialogHeader_3.show();
                        }
                    });


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
//                        }
//                    });


                    String responses = response.body().string();
                    Log.e("response", "onResponse(): " + responses);


                    if (response.code() == 404) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Utilss.hideSweetLoader(pDialog);
//                            }
//                        });


//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                        .withDialogAnimation(true)
//                                        .setTitle("Error Message")
//                                        .setDescription("Incorrect Url")
//                                        .setPositiveText("OK")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                                _passwordText.setText("");
//
//                                            }
//                                        });
//                                dialogHeader_3.show();


                    } else if (response.code() == 500) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Utilss.hideSweetLoader(pDialog);
//                            }
//                        });

                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            value2 = jsonObject.getString("exceptionMessage");
                            Log.d("Exception_Message", value2);


                            if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

//                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


//                                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                                        .setHeaderDrawable(R.drawable.header)
//                                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                        .withDialogAnimation(true)
//                                                        .setTitle("Error Message")
//                                                        .setDescription(value2)
//                                                        .setPositiveText("OK")
//                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                            @Override
//                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                _passwordText.setText("");
//
//                                                            }
//                                                        });
//                                                dialogHeader_3.show();


                                            }
                                        });
                                    }
                                });
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                        try {
                            JSONObject jsonObject = new JSONObject(responses);

//                    String value6 = jsonObject.getString("imei");
                            value89 = jsonObject.getString("isApprover");



                            String token = value345;
                            Log.d("TokenFromServer", token);

                            String isapprover = value89;
                            Log.d("ApproverStatusServer", value89);


                            if (isapprover != null) {

                                if (isapprover.equals("")) {
                                    manual_time_out_iss.setVisibility(View.VISIBLE);
                                } else {
                                    manual_time_out_iss.setVisibility(View.GONE);
                                }
                            }




                            Log.d("Value123", value89);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                SendTokenParamss(context);


                    }
                    //  }


                }
            });
        }
    }



















//    private void SendTokenParamss() {
//
//        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
//
//        String password = passwordsave;
//        String IMEIS = IMEI;
//
//        if (password == null && IMEIS == null) {
////            Toast.makeText(, "", Toast.LENGTH_SHORT).show();
//
//            Log.d("ItsNull", "Its Null But Still MOving");
//        } else {
//            RequestBody formBody = new FormBody.Builder().add("UserName", IMEI).add("Password", password)
//                    .add("grant_type", "password").build();
//
//
//            if (urls == null) {
//
//
//                try {
//                    final DBHelper objDBHelper = new DBHelper(getApplicationContext());
////                    objDBHelper = new DBHelper(context);
//                    String query = "Select * from LogHistory";
//
//                    Cursor cursor = objDBHelper.getCursor(query);
//                    if (cursor.getCount() > 0) {
//                        if (cursor.moveToFirst()) {
//                            do {
//                                urls = cursor.getString(cursor.getColumnIndex("TISURL"));
//                                Log.d("TISURL", urls);
//
//
//                                if (urls != null) {
//                                    Request request = new Request.Builder()
//                                            .url(urls + "token")
//                                            .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                            .addHeader("Cache-Control", "no-cache")
//                                            .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
//                                            .build();
//
//                                    Call call = client.newCall(request);
//                                    call.enqueue(new Callback() {
//                                        @Override
//                                        public void onFailure(Call call, IOException e) {
//
//
//                                            Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
//                                            e.printStackTrace();
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//
//                                                }
//                                            });
//
//
//                                        }
//
//                                        @Override
//                                        public void onResponse(Call call, Response response) throws IOException {
//
//                                            responses = response.body().string();
//                                            Log.e("responsetoken", responses);
//
//
//                                            if (response.code() == 404) {
//
//
//                                            } else if (response.code() == 500) {
//
//
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(responses);
//                                                    value2 = jsonObject.getString("exceptionMessage");
//                                                    Log.d("Exception_Message", value2);
//
//
//                                                    if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                                                        runOnUiThread(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//
////                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
////                                        startActivity(intent);
//                                                            }
//                                                        });
//                                                    } else {
//
//                                                    }
//
//
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////
////                                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
////                                            .setHeaderDrawable(R.drawable.header)
////                                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                            .withDialogAnimation(true)
////                                            .setTitle("Error Message")
////                                            .setDescription(value2)
////                                            .setPositiveText("OK")
////                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                @Override
////                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
//////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
////
////                                                    _passwordText.setText("");
////                                                }
////                                            });
////                                    dialogHeader_3.show();
////                                }
////                            });
//
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            } else {
//
//
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(responses);
//                                                    value345 = jsonObject.getString("access_token");
//                                                    Log.d("token12345", value345);
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//
//
//                                        }
//
//
//                                    });
//
//
//                                }
//
//
//                            }
//
//                            while (cursor.moveToNext());
//                        }
//                    }
//                } catch (Exception ex)
//                {
////                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//    }





















    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }
}