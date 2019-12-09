package com.example.tisapp.Login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import com.example.tisapp.BaseActivity;
import com.example.tisapp.DBHelper;
import com.example.tisapp.DatabaseHelper;
import com.example.tisapp.Home_Screen;
import com.example.tisapp.R;
import com.example.tisapp.Receivers.NetworkChangeReceiver;
import com.example.tisapp.Settings.Settings_Activity;
import com.example.tisapp.Utilss;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.gson.JsonArray;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.wdullaer.materialdatetimepicker.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;


public class LoginActivity  extends BaseActivity {

    private NetworkChangeReceiver networkChangeReceiver;


    public static EditText _passwordText;
    Button _loginButton;
    Cursor cursor;
    String deviceName;

    TextInputLayout hide;

    LocationManager manager;
    DatabaseHelper objDBHelper2;

    TextView change_Imei;
    String password;
    public static  String passwordsave;
    public static String value,value2;
    String responses;

    MaterialStyledDialog.Builder dialogHeader_3;



    private TelephonyManager mTelephonyManager;
    public static String IMEI,IMEIS;
    String p;
    OkHttpClient client;
    public static String value8;

    //    public static String urls;
    public static EditText eturl;
    String abc;
    DBHelper objDBHelper;
    SharedPreferences sharedPreferences;
    static final Integer PHONESTATS = 0x1;
    ProgressDialog progressDialog;
    TextView change_settings;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("HardwareIds")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);


        networkChangeReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();


        getDeviceImei();

        statusCheck();


        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        passwordsave=sharedPreferences.getString("Pass","");

//        askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);
//        checkLocationPermission();

        objDBHelper=new DBHelper(this);
        objDBHelper2=new DatabaseHelper(this);


        change_settings = (TextView) findViewById(R.id.change_settings);

        change_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this , Settings_Activity.class);
                startActivity(intent);
            }
        });


        getUrl();
        getTimeOutTime();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);

        askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);

        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.singupbtn);
//        change_Imei = (TextView) findViewById(R.id.change_Imei);

        hide= (TextInputLayout) findViewById(R.id.hide);



        _passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (_passwordText.getText().toString().length() !=0)
                {
//                    Toast.makeText(context, "Clicked..!!", Toast.LENGTH_SHORT).show();
                    hide.setPasswordVisibilityToggleEnabled(true);
                }

                else
                {
//                    Toast.makeText(context, "Not Clicked..!!", Toast.LENGTH_SHORT).show();
                    hide.setPasswordVisibilityToggleEnabled(false);
                }
            }
        });




//        change_Imei.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                alert2();
//            }
//        });

        _loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                password = _passwordText.getText().toString();






//                if (passwordsave.equals(password))
//                {
//
//                    startActivity(new Intent(LoginActivity.this , Home_Screen.class));
//                }
//
//                else
//                {
//                    _passwordText.setText("");
//                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Error Message")
//                            .setDescription("Incorrect Password")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                    _passwordText.setText("");
//                                    _loginButton.setEnabled(true);
//                                }
//                            });
//                    dialogHeader_3.show();
//                }



                manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("GPS Is Required..!!")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    _passwordText.setEnabled(true);
                                    _loginButton.setEnabled(true);
                                }
                            });
                    dialogHeader_3.show();
                }





                else
                {

                    if (password.length() == 0) {

                        dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Error Message")
                                .setDescription("Password Is Required")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                        _passwordText.setText("");
                                        _loginButton.setEnabled(true);
                                    }
                                });
                        dialogHeader_3.show();
                    }


                    else if (passwordsave.equals(_passwordText.getText().toString()))
                    {
                        if (isNetAvailable())
                        {
                            PostParamsToServer();
                        }
                        else
                        {

                            _passwordText.setText("");
                            startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                        }




//                        dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Incorrect Password")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                        _passwordText.setText("");
//                                        _loginButton.setEnabled(true);
//                                    }
//                                });
//                        dialogHeader_3.show();

                    }




                    else {
                        if (urls == null && urls2 == null) {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Url Is Required")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                            _passwordText.setText("");
                                            _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();
                        } else {


                            if (isNetAvailable() && urls2 != null) {
                                client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                                        .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

//                                Intent intents = new Intent("android.location.GPS_ENABLED_CHANGE");
//                                intents.putExtra("enabled" , true);
//                                sendBroadcast(intents);


                                PostParamsToServer();
                            } else {

//                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                        .withDialogAnimation(true)
//                                        .setTitle("Error Message")
//                                        .setDescription("Network Required..!!")
//                                        .setPositiveText("OK")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                            }
//                                        });
//                                dialogHeader_3.show();


                                if (!isNetAvailable() && !(passwordsave.equals(_passwordText.getText().toString())))
                                {
                                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                            .setHeaderDrawable(R.drawable.header)
                                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                            .withDialogAnimation(true)
                                            .setTitle("Error Message")
                                            .setDescription("Incorrect Password")
                                            .setPositiveText("OK")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                                    _passwordText.setText("");
                                                    _loginButton.setEnabled(true);
                                                }
                                            });
                                    dialogHeader_3.show();
                                }

                                else
                                {
//                                    _passwordText.setText("");
//                                    startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                                }

                            }
                        }

                    }
                }
            }

        });

    }



    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            showSettingsAlert();
        }
    }

    public void showSettingsAlert()
    {
//        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
//        alertDialog.setTitle("GPS Status");
//        alertDialog.setMessage("GPS is not enabled . Enable GPS Please ");
//        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which)
//            {
//                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            }
//        });
//
//
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        alertDialog.show();

        dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                .setHeaderDrawable(R.drawable.header)
                .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                .withDialogAnimation(true)
                .setTitle("Error Message")
                .setDescription("GPS is not enabled . Enable GPS Please ")
                .setPositiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                    {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeText("Not now");
        dialogHeader_3.show();
    }



















//    @AfterPermissionGranted(1)
//    public void requestLocationPermission() {
//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
//        if(EasyPermissions.hasPermissions(this, perms)) {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            EasyPermissions.requestPermissions(this, "Please grant the location permission", 1, perms);
//        }
//    }



    //}



    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

    private void getUrl()
    {
        try
        {
            objDBHelper = new DBHelper(this);
            String query = "Select * from LogHistory";

            cursor = objDBHelper.getCursor(query);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    do
                    {
                        urls = cursor.getString(cursor.getColumnIndex("TISURL"));
                        Log.d("TISURL" , urls);
                    }

                    while (cursor.moveToNext());
                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "No Data To Show", Toast.LENGTH_LONG).show();
            }

        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void getTimeOutTime()
    {
        try
        {
            objDBHelper = new DBHelper(this);
            String query = "Select * from LogHistory2";

            cursor = objDBHelper.getCursor(query);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    do
                    {
//                        urls = cursor.getString(cursor.getColumnIndex("TISURL"));
                        urls2 = cursor.getString(cursor.getColumnIndex("TISTIMEOUT"));
                        Log.d("TISTIMEOutTime" , urls2);
                    }

                    while (cursor.moveToNext());
                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "No Data To Show", Toast.LENGTH_LONG).show();
            }

        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }










//    /* Database related methods to create license generator History  */
//    public void insertLog(String deviceName)
//    {
//        try
//        {
//            String query = "Insert into LogHistory(TISURL)values('"+deviceName+"')";
//            objDBHelper.command(query);
//            Toast.makeText(getApplicationContext(), "Log Created", Toast.LENGTH_LONG).show();
//        }
//        catch(Exception ex)
//        {
//            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }



    //IMEI com.example.zain.project.Settings
    public void alert2() {
        try {
            final Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.prompt2, (ViewGroup) findViewById(R.id.layout_root));
            final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
            Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
            Button btnOk = (Button) layout.findViewById(R.id.btnOk);

            final EditText ed_employee_number = layout.findViewById(R.id.ed_employee_number);
            ed_employee_number.setText(IMEI);

            Log.d("Imei" , IMEI);

            AlertDialog.Builder aDialog = new AlertDialog.Builder(LoginActivity.this);
            aDialog.setView(layout);
            final AlertDialog ad = aDialog.create();
            listOfDialogs.add(ad);
            ad.show();

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    abc = ed_employee_number.getText().toString();


                    if (abc.equals("") ) {
                        ed_employee_number.setError("IMEI Number Cannot Be Empty..!!");
                    }

                    else
                    {
                        ad.cancel();
                    }
                }
            });


            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ad.cancel();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void PostParamsToServer() {


        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

        final String password= _passwordText.getText().toString();

        if (abc == null || abc.equals(""))
        {
            pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                    e.printStackTrace();



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Cant Connect To Server1")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                            _passwordText.setText("");
                                            _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();
                        }
                    });



                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    String responses = response.body().string();
                    Log.e("response", "onResponse(): " + responses);


                    if (response.code()==404)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                        .setHeaderDrawable(R.drawable.header)
                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                        .withDialogAnimation(true)
                                        .setTitle("Error Message")
                                        .setDescription("Incorrect Url")
                                        .setPositiveText("OK")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                                                _passwordText.setText("");

                                            }
                                        });
                                dialogHeader_3.show();

                            }

                        });
                    }

                    else if (response.code() == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            value2 = jsonObject.getString("exceptionMessage");
                            Log.d("Exception_Message", value2);



                            if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run() {

//                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                            }


                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


                                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                                        .setHeaderDrawable(R.drawable.header)
                                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                        .withDialogAnimation(true)
                                                        .setTitle("Error Message")
                                                        .setDescription(value2)
                                                        .setPositiveText("OK")
                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                                                _passwordText.setText("");

                                                            }
                                                        });
                                                dialogHeader_3.show();



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
                            String value = jsonObject.getString("employeeNo");
                            String valu2 = jsonObject.getString("employeeName");
                            String value3 = jsonObject.getString("department");
                            String value4 = jsonObject.getString("designation");
                            String value5 = jsonObject.getString("active");
                            String value6 = jsonObject.getString("imei");
                            String value7 = jsonObject.getString("androidPwd");
                            value8 = jsonObject.getString("isApprover");


                            Log.d("Value123", value + valu2 + value3 + value4 + value5 + value6 + value7 + value8);


                            long id= objDBHelper2.insertProfileData(value,valu2,value4,value3,value8);
                            if(id<0) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Unsuccefull", Toast.LENGTH_SHORT).show();
                                        Log.e("UnSuccessfull", "unsuc");
                                    }
                                });

                            }else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("NSuccessfull", "succ");
                                    }
                                });
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(passwordsave.equals(""))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("IMEI", IMEI);
                            editor.putString("Pass", password);
                            editor.apply();

//                        Log.e("Password", "pehle se save nh tha");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Password Saved",Toast.LENGTH_LONG).show();
                                    Log.d("Password_is" , password);
                                }
                            });

//                            startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                        }



                        SendTokenParams();


                    }
                }
            });
        }





        else
        {
            pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            RequestBody formBody = new FormBody.Builder().add("IMEI", abc).add("Password", password).build();

            final Request request = new Request.Builder()
                    .url(urls+"register?IMEI="+abc+"&Password="+password)
                    .post(formBody) .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Cant Connect To Server2")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                            _passwordText.setText("");
                                            _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();
                        }
                    });


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    String responses = response.body().string();
                    Log.e("response", "onResponse(): " + responses);


                    if (response.code() == 500)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            value2 = jsonObject.getString("exceptionMessage");
                            Log.d("Exception_Message", value2);



                            if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run() {

//                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                            }


                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                                .setHeaderDrawable(R.drawable.header)
                                                .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                .withDialogAnimation(true)
                                                .setTitle("Error Message")
                                                .setDescription(value2)
                                                .setPositiveText("OK")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                                                    }
                                                });
                                        dialogHeader_3.show();
                                    }
                                });
                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    else
                    {

                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            String value = jsonObject.getString("employeeNo");
                            String valu2 = jsonObject.getString("employeeName");
                            String value3 = jsonObject.getString("department");
                            String value4 = jsonObject.getString("designation");
                            String value5 = jsonObject.getString("active");
                            String value6 = jsonObject.getString("imei");
                            String value7 = jsonObject.getString("androidPwd");
                            value8 = jsonObject.getString("isApprover");


                            Log.d("Value123", value + valu2 + value3 + value4 + value5 + value6 + value7 + value8);


                            long id= objDBHelper2.insertProfileData(value,valu2,value4,value3,value8);
                            if(id<0) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Unsuccefull", Toast.LENGTH_SHORT).show();
                                        Log.e("UnSuccessfull", "unsuc");
                                    }
                                });

                            }else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("NSuccessfull", "succ");
                                    }
                                });
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if(passwordsave.equals(""))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("IMEI", IMEI);
                            editor.putString("Pass", password);
                            editor.apply();

//                        Log.e("Password", "pehle se save nh tha");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Password Saved",Toast.LENGTH_LONG).show();
                                    Log.d("Password_is" , password);
                                }
                            });

//                            startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                        }



                        SendTokenParams();


                    }
                }
            });
        }



    }



    private void SendTokenParams()
    {
        String password= _passwordText.getText().toString();
        String IMEIS= IMEI;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            }
        });


        if (abc==null || abc.equals(""))
        {

//            pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

            RequestBody formBody = new FormBody.Builder().add("UserName", IMEI).add("Password", password)
                    .add("grant_type", "password").build();

            Request request = new Request.Builder()
                    .url(urls+"token")
                    .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                    e.printStackTrace();


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Cant Connect To Server3")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                            _passwordText.setText("");
                                            _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();
                        }
                    });


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    responses = response.body().string();
                    Log.e("responsetoken", responses);


                    if (response.code() == 404) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                        .setHeaderDrawable(R.drawable.header)
                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                        .withDialogAnimation(true)
                                        .setTitle("Error Message")
                                        .setDescription("Incorrect Url")
                                        .setPositiveText("OK")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                                                _passwordText.setText("");

                                            }
                                        });
                                dialogHeader_3.show();
                            }

                        });
                    } else if (response.code() == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            value2 = jsonObject.getString("exceptionMessage");
                            Log.d("Exception_Message", value2);




                            if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run() {

//                                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                            }


                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                                .setHeaderDrawable(R.drawable.header)
                                                .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                .withDialogAnimation(true)
                                                .setTitle("Error Message")
                                                .setDescription(value2)
                                                .setPositiveText("OK")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                                                        _passwordText.setText("");
                                                    }
                                                });
                                        dialogHeader_3.show();
                                    }
                                });
                            }


//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                            .withDialogAnimation(true)
//                                            .setTitle("Error Message")
//                                            .setDescription(value2)
//                                            .setPositiveText("OK")
//                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                @Override
//                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                                    _passwordText.setText("");
//                                                }
//                                            });
//                                    dialogHeader_3.show();
//                                }
//                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {


                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            value = jsonObject.getString("access_token");
                            Log.d("token123", value);


                            Intent intent = new Intent(LoginActivity.this, Home_Screen.class);
                            intent.putExtra("Access_Token", value);
                            startActivity(intent);



//                            long id= objDBHelper2.insertAccessToken(value);
//                            if(id<0) {
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "Unsuccefull token ", Toast.LENGTH_SHORT).show();
//                                        Log.e("UnSuccessfull", "unsuc");
//                                    }
//                                });
//
//                            }else {
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
//                                        Log.e("NSuccessfull", "succ");
//                                    }
//                                });
//                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });
        }





        else
        {
            RequestBody formBody = new FormBody.Builder().add("UserName", abc).add("Password", password)
                    .add("grant_type", "password").build();

            Request request = new Request.Builder()
                    .url(urls+"token")
                    .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                    e.printStackTrace();


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Cant Connect To Server4")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                            _passwordText.setText("");
                                            _loginButton.setEnabled(true);
                                        }
                                    });
                            dialogHeader_3.show();
                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {



                    String responses = response.body().string();
                    Log.e("responsetoken", responses);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    try {
                        JSONObject jsonObject = new JSONObject(responses);
                        value = jsonObject.getString("access_token");
                        Log.d("token123", value);

                        Intent intent = new Intent(LoginActivity.this, Home_Screen.class);
                        intent.putExtra("Access_Token", value);
                        startActivity(intent);


//                        long id= objDBHelper2.insertAccessToken(value);
//                        if(id<0) {
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(getApplicationContext(), "Unsuccefull token ", Toast.LENGTH_SHORT).show();
//                                    Log.e("UnSuccessfull", "unsuc");
//                                }
//                            });
//
//                        }else {
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
//                                    Log.e("NSuccessfull", "succ");
//                                }
//                            });
//                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {

                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            getDeviceImei();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getDeviceImei();
//                    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//
                } else {

                    Toast.makeText(LoginActivity.this, "You have Denied the Permission", Toast.LENGTH_SHORT).show();
                }









                return;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getDeviceImei() {


        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        IMEI = mTelephonyManager.getDeviceId();

        if (IMEI.equals("")) {
            Toast.makeText(getApplicationContext(), "Unable to Get IMEI or Restriction on Mobile to get IMEI", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(this, "IMEI IS:-" + IMEI, Toast.LENGTH_SHORT).show();
            Log.d("DeviceIMEI", IMEI);
        }
    }





    //  CHECK FOR LOCATION PERMISSION
    public  boolean checkPermission(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }


    //REQUEST FOR PERMISSSION



    public void alert() {
        try {
            final Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.prompt, (ViewGroup) findViewById(R.id.layout_root));
            final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
            Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
//            EditText ed_employee_number = (EditText) layout.findViewById(R.id.ed_employee_number);
            Button btnOk = (Button) layout.findViewById(R.id.btnOk);

            final EditText ed_employee_number = layout.findViewById(R.id.ed_employee_number);
            ed_employee_number.setText(IMEI);
            final EditText ed_old_password = layout.findViewById(R.id.ed_old_password);
            final EditText ed_new_password = layout.findViewById(R.id.ed_new_password);
//            final EditText ed_token= layout.findViewById(R.id.ed_token);

            AlertDialog.Builder aDialog = new AlertDialog.Builder(LoginActivity.this);
            aDialog.setView(layout);
            final AlertDialog ad = aDialog.create();
            listOfDialogs.add(ad);
            ad.show();

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String b = ed_old_password.getText().toString();
                    String c = ed_new_password.getText().toString();

                    if (b.equals("") && c.equals(""))
                    {
//                        ed_employee_number.setError("Employee Number Cannot Be Empty..!!");
                        ed_old_password.setError("Password Cannot Be Empty..!!");
                        ed_new_password.setError("Password Cannot Be Empty..!!");
                    }

                    else if (b.isEmpty() || b.length() < 0)
                    {
                        ed_old_password.setError("Password Cannot Be Empty..!!");
                        ed_new_password.setError("Password Cannot Be Empty..!!");


                    } else if (c.isEmpty() || c.length() < 0) {
                        ed_old_password.setError("Password Cannot Be Empty..!!");
                    } else if (c.isEmpty() || c.length() < 0) {
                        ed_new_password.setError("Password Cannot Be Empty..!!");
                    } else {
                        Toast.makeText(mContext, "Functionality will be available soon..!!", Toast.LENGTH_SHORT).show();
                        ad.cancel();
                    }
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ad.cancel();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {

        //Display alert message when back button has been pressed
        backButtonHandler();
    }

    public void backButtonHandler() {

        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Warning Message");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        dialog.dismiss();
//                       finish();

                        dialog.dismiss();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}



























//package com.example.zain.project.Login;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.telephony.SignalStrength;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
//import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
//
//import com.example.zain.project.BaseActivity;
//import com.example.zain.project.DBHelper;
//import com.example.zain.project.Home_Screen;
//import com.example.zain.project.Profile.Profile;
//import com.example.zain.project.R;
//import com.example.zain.project.Settings.Settings_Activity;
//import com.example.zain.project.Splash.SplashActivity;
//import com.example.zain.project.Utilss;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
//import com.google.gson.JsonArray;
//import com.mikepenz.iconics.IconicsDrawable;
//import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
//import com.rey.material.widget.ProgressView;
////import com.roger.catloadinglibrary.CatLoadingView;
//// import com.wdullaer.materialdatetimepicker.Utils;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//
//import okhttp3.Response;
//
//import static com.example.zain.project.Settings.Settings_Activity.urls;
//
//
//public class LoginActivity  extends BaseActivity {
//
//    public static EditText _passwordText;
//    Button _loginButton;
//    Cursor cursor;
//    String deviceName;
////    CatLoadingView mView;
//
//    ProgressView circularProgressBar;
//
//    TextView change_Imei;
//    String password;
//    public static String value, value2;
//    String responses;
//    MaterialStyledDialog.Builder dialogHeader_3;
//    private TelephonyManager mTelephonyManager;
//    String IMEI, IMEIS;
//    String p;
//    OkHttpClient client;
//    public static String value8;
//
////    public static String urls="http://192.168.5.48/tisapi/";
//    public static EditText eturl;
//    String abc;
//    DBHelper objDBHelper;
//
//    static final Integer PHONESTATS = 0x1;
//    ProgressDialog progressDialog;
//    TextView change_settings;
//
//
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @SuppressLint("HardwareIds")
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        setContentView(R.layout.activity_login);
//
//
//        objDBHelper = new DBHelper(this);
//
////        mView = new CatLoadingView();
//
//        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
//        circularProgressBar.setVisibility(View.GONE);
//
//
//
//
////        mView.setBackgroundColor(Color.parseColor("#000000"));
//
//        change_settings = (TextView) findViewById(R.id.change_settings);
//
//        change_settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(LoginActivity.this, Settings_Activity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        getUrl();
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(true);
//
//        askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);
//
//        _passwordText = (EditText) findViewById(R.id.input_password);
//        _loginButton = (Button) findViewById(R.id.singupbtn);
////        change_Imei = (TextView) findViewById(R.id.change_Imei);
//
//
//        _loginButton.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                password = _passwordText.getText().toString();
//
//                client = new OkHttpClient();
//
//                if (password.length() == 0) {
//
//
////                    new TTFancyGifDialog.Builder(LoginActivity.this)
////                            .setTitle("Error Message")
////                            .setMessage("Password Is Required")
////                            .setPositiveBtnBackground("#f4de15")
////                            .setPositiveBtnText("Ok")
////                            .setGifResource(R.drawable.g)
////                            .isCancellable(true)
////                            .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                @Override
////                                public void OnClick() {
////                                    _passwordText.setText("");
////                                    _loginButton.setEnabled(true);
////                                }
////                            })
////                            .build();
//
//
//                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Error Message")
//                            .setDescription("Password Is Required")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                {
//                                    _passwordText.setText("");
//                                    _loginButton.setEnabled(true);
//                                }
//                            });
//                    dialogHeader_3.show();
//                } else {
//
////                        pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
////                        if (IMEI.equals(""))
////                        {
////                            new TTFancyGifDialog.Builder(LoginActivity.this)
////                                    .setTitle("Error Message")
////                                    .setMessage("IMEI Cant Be Empty")
////                                    .setPositiveBtnBackground("#f4de15")
////                                    .setPositiveBtnText("Ok")
////                                    .setGifResource(R.drawable.g)
////                                    .isCancellable(true)
////                                    .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                        @Override
////                                        public void OnClick() {
////                                        }
////                                    })
////                                    .build();
////                        }
//
//
//                    if (isNetAvailable()) {
//                        PostParamsToServer();
//                    } else {
////                        new TTFancyGifDialog.Builder(LoginActivity.this)
////                                .setTitle("Error Message")
////                                .setMessage("Network Required..!!")
////                                .setPositiveBtnBackground("#f4de15")
////                                .setPositiveBtnText("OK")
////                                .setGifResource(R.drawable.g)
////                                .isCancellable(true)
////                                .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                    @Override
////                                    public void OnClick() {
////                                    }
////
////                                })
////                                .build();
//
//
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Network Required..!!")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                    {
////                                        _passwordText.setText("");
//                                    }
//                                });
//                        dialogHeader_3.show();
//
//
//
//                    }
//                }
//
//
//            }
//        });
//
//
////        change_Imei.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                alert2();
////            }
////        });
//
//
//    }
//
//
//
//
//    private void getUrl()
//    {
//        try
//        {
//            objDBHelper = new DBHelper(this);
//            String query = "Select * from LogHistory";
//
//            cursor = objDBHelper.getCursor(query);
//            if (cursor.getCount() > 0)
//            {
//                if (cursor.moveToFirst())
//                {
//                    do
//                    {
//                        urls = cursor.getString(cursor.getColumnIndex("TISURL"));
//                        Log.d("TISURL" , urls);
//                    }
//
//                    while (cursor.moveToNext());
//                }
//            }
//
//            else
//            {
//                Toast.makeText(getApplicationContext(), "No Data To Show", Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//        catch (Exception ex)
//        {
//            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public  boolean isNetAvailable()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null && networkInfo.isConnected();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void askForPermission(String permission, Integer requestCode) {
//        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should show an explanation
//            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {
//
//                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
//
//            } else {
//
//                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
//            }
//        } else {
//            getDeviceImei();
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    getDeviceImei();
//
//                } else {
//
//                    Toast.makeText(LoginActivity.this, "You have Denied the Permission", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//        }
//    }
//
//
//
//        private void PostParamsToServer () {
//
//            String password = _passwordText.getText().toString();
//
//            if (abc == null || abc.equals("")) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
////                        pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//                        circularProgressBar.setVisibility(View.VISIBLE);
//                        circularProgressBar.start();
//
//
//                    }
//                });
//                RequestBody formBody = new FormBody.Builder().add("IMEI", IMEI).add("Password", password).build();
//
//                final Request request = new Request.Builder()
////                        .url("http://192.168.5.48/tisapi/" + "register?IMEI=" + IMEI + "&Password=" + password)
//
//                        .url(urls + "register?IMEI=" + IMEI + "&Password=" + password)
//                        .post(formBody).addHeader("Cache-Control", "no-cache")
//                        .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
//                            }
//                        });
//
////                        showDialogs();
//
//                        Log.e("HttpService", "onFailure() Request was: " + call);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
////                                showDialogs();
//                            }
//                        });
//
//
//                        String responses = response.body().string();
//                        Log.e("response", "onResponse(): " + responses);
//
//
//                        if (response.code() == 404) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    Utilss.hideSweetLoader(pDialog);
//
//                                    circularProgressBar.setVisibility(View.GONE);
//                                    circularProgressBar.stop();
//
////                                    showDialogs();
//                                }
//                            });
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    new TTFancyGifDialog.Builder(LoginActivity.this)
////                                            .setTitle("Error Message")
////                                            .setMessage("Incorrect Url")
////                                            .setPositiveBtnBackground("#f4de15")
////                                            .setPositiveBtnText("Ok")
////                                            .setGifResource(R.drawable.g)
////                                            .isCancellable(true)
////                                            .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                @Override
////                                                public void OnClick() {
////                                                    _passwordText.setText("");
////                                                }
////                                            })
////                                            .build();
//
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                            .withDialogAnimation(true)
//                                            .setTitle("Incorrect Url")
//                                            .setDescription(value2)
//                                            .setPositiveText("OK")
//                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                @Override
//                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                {
//                                                    _passwordText.setText("");
//                                                }
//                                            });
//                                    dialogHeader_3.show();
//                                }
//
//                            });
//                        } else if (response.code() == 500) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    Utilss.hideSweetLoader(pDialog);
//
//                                    circularProgressBar.setVisibility(View.GONE);
//                                    circularProgressBar.stop();
//
////                                    showDialogs();
//                                }
//                            });
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value2 = jsonObject.getString("exceptionMessage");
//                                Log.d("Exception_Message", value2);
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
////                                        new TTFancyGifDialog.Builder(LoginActivity.this)
////                                                .setTitle("Error Message")
////                                                .setMessage(value2)
////                                                .setPositiveBtnBackground("#f4de15")
////                                                .setPositiveBtnText("Ok")
////                                                .setGifResource(R.drawable.g)
////                                                .isCancellable(true)
////                                                .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                    @Override
////                                                    public void OnClick() {
////                                                        _passwordText.setText("");
////                                                    }
////                                                })
////                                                .build();
//
//
//                                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                                .setHeaderDrawable(R.drawable.header)
//                                                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                .withDialogAnimation(true)
//                                                .setTitle("Error Message")
//                                                .setDescription(value2)
//                                                .setPositiveText("OK")
//                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                    {
//                                                        _passwordText.setText("");
//                                                    }
//                                                });
//                                        dialogHeader_3.show();
//                                    }
//                                });
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                String value = jsonObject.getString("employeeNo");
//                                String valu2 = jsonObject.getString("employeeName");
//                                String value3 = jsonObject.getString("department");
//                                String value4 = jsonObject.getString("designation");
//                                String value5 = jsonObject.getString("active");
//                                String value6 = jsonObject.getString("imei");
//                                String value7 = jsonObject.getString("androidPwd");
//                                value8 = jsonObject.getString("isApprover");
//
//
//                                Log.d("Value123", value + valu2 + value3 + value4 + value5 + value6 + value7 + value8);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            SendTokenParams();
//
//
//                        }
//                    }
//                });
//            } else {
//
//                circularProgressBar.setVisibility(View.VISIBLE);
//                circularProgressBar.start();
////                pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//                RequestBody formBody = new FormBody.Builder().add("IMEI", abc).add("Password", password).build();
//
//                final Request request = new Request.Builder()
//                        .url(urls+"register?IMEI="+abc+"&Password="+password)
//                        .post(formBody).addHeader("Cache-Control", "no-cache")
//                        .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
////                                showDialogs();
//                            }
//                        });
//
//                        Log.e("HttpService", "onFailure() Request was: " + call);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
////                                showDialogs();
//                            }
//                        });
//
//
//                        String responses = response.body().string();
//                        Log.e("response", "onResponse(): " + responses);
//
//
//                        if (response.code() == 500) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    Utilss.hideSweetLoader(pDialog);
//
//                                    circularProgressBar.setVisibility(View.GONE);
//                                    circularProgressBar.stop();
////                                    showDialogs();
//                                }
//                            });
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value2 = jsonObject.getString("exceptionMessage");
//                                Log.d("Exception_Message", value2);
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
////                                        new TTFancyGifDialog.Builder(LoginActivity.this)
////                                                .setTitle("Error Message")
////                                                .setMessage(value2)
////                                                .setPositiveBtnBackground("#f4de15")
////                                                .setPositiveBtnText("Ok")
////                                                .setGifResource(R.drawable.g)
////                                                .isCancellable(true)
////                                                .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                    @Override
////                                                    public void OnClick() {
////                                                        _passwordText.setText("");
////                                                    }
////                                                })
////                                                .build();
//
//
//
//                                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                                .setHeaderDrawable(R.drawable.header)
//                                                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                .withDialogAnimation(true)
//                                                .setTitle("Error Message")
//                                                .setDescription(value2)
//                                                .setPositiveText("OK")
//                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                    {
//                                                        _passwordText.setText("");
//                                                    }
//                                                });
//                                        dialogHeader_3.show();
//
//
//                                    }
//                                });
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                String value = jsonObject.getString("employeeNo");
//                                String valu2 = jsonObject.getString("employeeName");
//                                String value3 = jsonObject.getString("department");
//                                String value4 = jsonObject.getString("designation");
//                                String value5 = jsonObject.getString("active");
//                                String value6 = jsonObject.getString("imei");
//                                String value7 = jsonObject.getString("androidPwd");
//                                value8 = jsonObject.getString("isApprover");
//
//
//                                Log.d("Value123", value + valu2 + value3 + value4 + value5 + value6 + value7 + value8);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            SendTokenParams();
//
//
//                        }
//                    }
//                });
//            }
//
//        }
//
////    public void showDialogs() {
////        mView.show(getSupportFragmentManager(), "");
////    }
//
//
//
//    private void SendTokenParams () {
//            String password = _passwordText.getText().toString();
//            String IMEIS = IMEI;
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//                    circularProgressBar.setVisibility(View.VISIBLE);
//                    circularProgressBar.start();
//
////                    showDialogs();
//                }
//            });
//
//
//            if (abc == null || abc.equals("")) {
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
////                        pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//                        circularProgressBar.setVisibility(View.VISIBLE);
//                        circularProgressBar.start();
//
//                    }
//                });
//
//                RequestBody formBody = new FormBody.Builder().add("UserName", IMEI).add("Password", password)
//                        .add("grant_type", "password").build();
//
//                Request request = new Request.Builder()
//                        .url(urls+"token")
//                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                        .addHeader("Cache-Control", "no-cache")
//                        .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
//
////                                showDialogs();
//                            }
//                        });
//
//                        Log.e("HttpService", "onFailure() Request was: " + call);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        responses = response.body().string();
//                        Log.e("responsetoken", responses);
//
//
//                        if (response.code() == 404) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    Utilss.hideSweetLoader(pDialog);
//
//                                    circularProgressBar.setVisibility(View.GONE);
//                                    circularProgressBar.stop();
////                                    showDialogs();
//                                }
//                            });
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    new TTFancyGifDialog.Builder(LoginActivity.this)
////                                            .setTitle("Error Message")
////                                            .setMessage("Incorrect Url")
////                                            .setPositiveBtnBackground("#f4de15")
////                                            .setPositiveBtnText("Ok")
////                                            .setGifResource(R.drawable.g)
////                                            .isCancellable(true)
////                                            .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                @Override
////                                                public void OnClick() {
////                                                    _passwordText.setText("");
////                                                }
////                                            })
////                                            .build();
//
//
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                            .withDialogAnimation(true)
//                                            .setTitle("Error Message")
//                                            .setDescription("Incorrect Url")
//                                            .setPositiveText("OK")
//                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                @Override
//                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                {
//                                                    _passwordText.setText("");
//                                                }
//                                            });
//                                    dialogHeader_3.show();
//
//
//                                }
//
//                            });
//                        } else if (response.code() == 500) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                    Utilss.hideSweetLoader(pDialog);
//
//                                    circularProgressBar.setVisibility(View.GONE);
//                                    circularProgressBar.stop();
////                                    showDialogs();
//                                }
//                            });
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value2 = jsonObject.getString("exceptionMessage");
//                                Log.d("Exception_Message", value2);
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
////                                        new TTFancyGifDialog.Builder(LoginActivity.this)
////                                                .setTitle("Error Message")
////                                                .setMessage(value2)
////                                                .setPositiveBtnBackground("#f4de15")
////                                                .setPositiveBtnText("Ok")
////                                                .setGifResource(R.drawable.g)
////                                                .isCancellable(true)
////                                                .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                    @Override
////                                                    public void OnClick() {
////                                                        _passwordText.setText("");
////                                                    }
////                                                })
////                                                .build();
//
//
//                                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                                .setHeaderDrawable(R.drawable.header)
//                                                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                .withDialogAnimation(true)
//                                                .setTitle("Error Message")
//                                                .setDescription(value2)
//                                                .setPositiveText("OK")
//                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                                    {
//                                                        _passwordText.setText("");
//                                                    }
//                                                });
//                                        dialogHeader_3.show();
//                                    }
//                                });
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(responses);
//                                value = jsonObject.getString("access_token");
//                                Log.d("token123", value);
//
//
//
//                                Intent intent = new Intent(LoginActivity.this, Home_Screen.class);
////                        intent.putExtra("Access_Token", value);
//                                startActivity(intent);
///*
//                                Intent intent = new Intent(LoginActivity.this, Profile.class);
//                                intent.putExtra("Access_Token", value);
////                        intent.putExtra("Service_Url", urls);
//
//////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//*/
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                });
//            } else {
//                RequestBody formBody = new FormBody.Builder().add("UserName", abc).add("Password", password)
//                        .add("grant_type", "password").build();
//
//                Request request = new Request.Builder()
//                        .url(urls+"token")
//                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                        .addHeader("Cache-Control", "no-cache")
//                        .addHeader("Postman-Token", "510b77b4-3dc4-4ab1-9c61-08ec67b82873")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
////                                showDialogs();
//                            }
//                        });
//
//                        Log.e("HttpService", "onFailure() Request was: " + call);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//
//                        String responses = response.body().string();
//                        Log.e("responsetoken", responses);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Utilss.hideSweetLoader(pDialog);
//
//                                circularProgressBar.setVisibility(View.GONE);
//                                circularProgressBar.stop();
////                                showDialogs();
//                            }
//                        });
//
//                        Intent intent = new Intent(LoginActivity.this, Home_Screen.class);
////                        intent.putExtra("Access_Token", value);
//                        startActivity(intent);
////                        try {
//////                            JSONObject jsonObject = new JSONObject(responses);
//////                            value = jsonObject.getString("access_token");
//////                            Log.d("token123", value);
////
////                            Intent intent = new Intent(LoginActivity.this, Home_Screen.class);
////                            intent.putExtra("Access_Token", value);
////////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////////                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////
////
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
//                    }
//                });
//            }
//        }
//
//
//
//
//
//
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void getDeviceImei() {
//
//
//        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        IMEI = mTelephonyManager.getDeviceId();
//
//        if (IMEI.equals("")) {
//            Toast.makeText(getApplicationContext(), "Unable to Get IMEI or Restriction on Mobile to get IMEI", Toast.LENGTH_LONG).show();
//        } else {
////            Toast.makeText(this, "IMEI IS:-" + IMEI, Toast.LENGTH_SHORT).show();
//            Log.d("DeviceIMEI", IMEI);
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
