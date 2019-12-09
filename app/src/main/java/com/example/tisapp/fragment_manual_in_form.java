package com.example.tisapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
//import com.example.tisapp.Receivers.NetworkChangeReceiver;
import com.example.tisapp.services.LocationMonitoringService;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;
import static com.google.android.gms.internal.zzahn.runOnUiThread;

import static com.google.android.gms.internal.zzahn.runOnUiThread;
import static com.google.android.gms.internal.zzahn.zzag;

//import com.google.android.gms.internal.zzagz;

//implements OnMapReadyCallback
public class fragment_manual_in_form extends BaseActivity {


    public static boolean isNetAvailable;
    TextView txtName, designation, tvTodayTime, tvTodayDate, tvTodayTimeAMPM;
    String weekday_name2;
    static DatabaseHelper mDatabaseHelper;

    String Latitude, Longitude;
    String datetime;
    static String responses;
    static String value2;
    static String datetime33;
    static String datetime2;
    static EditText customer_info;
    static EditText reason;
    Button btnLeaveSubmit;
    ImageView iv_back;
    static OkHttpClient client;
    protected GoogleMap mGoogleMap;

    //protected GoogleMap mGoogleMaps;
    boolean loaded = false;
    double lat, longl;
    SweetAlertDialog pDialog;

    Context context;
    String latitude, longitude;
    GoogleApiClient mGoogleApiClient;
    ProgressView circularProgressBar;

    //    NetworkChangeReceiver networkChangeReceiver;
    LocationManager locationManager;
    MaterialStyledDialog.Builder dialogHeader_3;
    private boolean mAlreadyStartedService = false;

    public fragment_manual_in_form() {

        // Required empty public constructor
    }

//    public static Fragment newInstance(int position) {
//        fragment_manual_in_form f = new fragment_manual_in_form();
//        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        return f;
//    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_apply_manual_time_in);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);
//        isLocationEnabled();


//        pDialog = Utilss.showSweetLoader(fragment_manual_in_form.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

        if (urls2 == null) {

        } else {
            client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                    .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

        }


        startStep3();


        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
                        longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);

                        if (latitude != null && longitude != null) {
//                            mMsgView.setText(getString(R.string.msg_location_service_started) + "\n Latitude : " + latitude + "\n Longitude: " + longitude);

//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });

                            Latitude = latitude;
                            Longitude = longitude;

                            Log.d("LatitudeIs", "" + Latitude);
                            Log.d("LongitudeeIs", "" + Longitude);

                        }
                    }
                }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST)
        );


        context = getApplicationContext();
//        networkChangeReceiver = new NetworkChangeReceiver();
        mDatabaseHelper = new DatabaseHelper(this);
//        registerNetworkBroadcastForNougat();


//        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
//        if (mapFrag != null) {
//            mapFrag.getMapAsync(this);
//        }


        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(GONE);
        reason = (EditText) findViewById(R.id.customer_info2);
        customer_info = (EditText) findViewById(R.id.customer_info);
        btnLeaveSubmit = (Button) findViewById(R.id.btnLeaveSubmit);


        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Calendar c2 = Calendar.getInstance(); //Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
        datetime = dateformat.format(c2.getTime());
        System.out.println(datetime);


        Calendar c3 = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        datetime33 = date.format(c3.getTime());
        Log.d("DateH", datetime33);
        System.out.println(datetime33);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat time2 = new SimpleDateFormat("hh:mm");
        datetime2 = time2.format(c.getTime());
        Log.d("TimeH", datetime2);

        btnLeaveSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customer_info.getText().toString().equals("")) {
//                    customer_info.setError("Info Is Required");
                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Info Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                }
                            });
                    dialogHeader_3.show();

                } else if (reason.getText().toString().equals("")) {

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Reason Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                }
                            });
                    dialogHeader_3.show();

                } else {


//                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Network Required..!!")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                        customer_info.setText("");
////                                        reason.setText("");
//                                    }
//                                });
//                        dialogHeader_3.show();


                    AddData();


                }

            }

        });

    }

//    boolean insertData = false;
//
//    public void AddDatas() {
//
//        if (!mDatabaseHelper.IsDataExist(datetime33) && !mDatabaseHelper.IsDataExist2(datetime33) && !mDatabaseHelper.IsDataExistAttendance(datetime33)) {
//            insertData = mDatabaseHelper.addData("", datetime33, datetime2, "", "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");
//
//            if (insertData) {
//
//
//                Toast.makeText(getApplicationContext(), "Data Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//
//                final DatabaseHelper dbHelper = new DatabaseHelper(context);
//                Log.e("NetworkChangeRerciver", "Online Connect Intenet ");
//                Boolean request_result = true;
//
//                Cursor timeInRequestOfflineDatas = dbHelper.getOfflineData();
//
//                Cursor timeInRequestOfflineData11 = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_IN);
//
//                if (timeInRequestOfflineData11.getCount() > 0) {
//                    Toast.makeText(context, "Error In Sending To Server..!!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//
//
//                    final int ID = timeInRequestOfflineDatas.getInt(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL1));
//                    String GetType = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL10));
//
//                    String COL2 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL2));
//                    String COL3 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL3));
//                    String COL6 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL6));
//                    String COL7 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL7));
//                    String COL8 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL8));
//                    String COL9 = timeInRequestOfflineDatas.getString(timeInRequestOfflineData11.getColumnIndex(DatabaseHelper.COL9));
//
//                    if (GetType.equals(DatabaseHelper.TYPE_MANUAl_TIME_IN)) {
////                            Toast.makeText(context, "Sync1", Toast.LENGTH_SHORT).show();
//
//
//                        if (timeInRequestOfflineDatas.moveToFirst()) {
//
//                            while (timeInRequestOfflineDatas.moveToNext()) {
//
//
//                                if (request_result) {
//
//
//                                    RequestBody formBody = new FormBody.Builder().add("DateIn", COL2).add("TimeIn", COL3)
//                                            .add("Reason", COL6)
//                                            .add("CustomerInfo", COL7)
//                                            .add("xCoordinates", COL8)
//                                            .add("YCoordinates", COL9).build();
//
//
//                                    Request request = new Request.Builder()
//                                            .url(urls + "api/Manual/ManualInRequest?DateIn=" + COL2 + "&TimeIn=" + COL3 + "&Reason=" + COL6 + "&CustomerInfo=" + COL7 + "&xCoordinates=" + COL8 + "&YCoordinates=" + COL9)
//                                            .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                            .addHeader("Cache-Control", "no-cache")
//                                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                            .addHeader("Postman-Token", "059d8930-4241-43fa-a61d-c3162b9a60c4")
//                                            .build();
//
//
//                                    Call call = client.newCall(request);
//                                    Log.d("testing", "2");
//                                    call.enqueue(new Callback() {
//                                        @Override
//                                        public void onFailure(Call call, IOException e) {
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////                                        circularProgressBar.setVisibility(View.GONE);
////                                        circularProgressBar.stop();
//                                                }
//                                            });
//
//
//                                            Log.e("HttpService", "onFailure() Request was: " + call);
//                                            e.printStackTrace();
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                            .setHeaderDrawable(R.drawable.header)
//                                                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                            .withDialogAnimation(true)
//                                                            .setTitle("Error Message")
//                                                            .setDescription("Cant Connect To Server")
//                                                            .setPositiveText("OK")
//                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                @Override
//                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                }
//                                                            });
//                                                    dialogHeader_3.show();
//                                                }
//                                            });
//                                        }
//
////                                    }
//
//                                        @Override
//                                        public void onResponse(Call call, Response response) throws IOException {
//
//                                            responses = response.body().string();
//                                            Log.e("response", "onResponse(): " + responses);
//
//                                            if (response.code() == 500) {
//                                                dbHelper.updateStatus(ID, "true");
//                                                Utilss.hideSweetLoader(pDialog);
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                    }
//                                                });
//
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(responses);
//                                                    value2 = jsonObject.getString("exceptionMessage");
//                                                    Log.d("ValueResponse", value2);
//
//
//                                                    Log.d("msgError", "" + value2);
////                                                Toast.makeText(context, "msgError" + value2, Toast.LENGTH_SHORT).show();
//                                                    runOnUiThread(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//
//
//                                                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                                    .setHeaderDrawable(R.drawable.header)
//                                                                    .setIcon(new IconicsDrawable(Objects.requireNonNull(fragment_manual_in_form.this)).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                    .withDialogAnimation(true)
//                                                                    .setTitle("Error Message")
//                                                                    .setDescription(value2)
//                                                                    .setPositiveText("OK")
//                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                        @Override
//                                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                        }
//                                                                    });
//                                                            dialogHeader_3.show();
//                                                        }
//                                                    });
//
//
////
//
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//
//
//                                            } else {
//                                                dbHelper.updateStatus(ID, "true");
//                                                Utilss.hideSweetLoader(pDialog);
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
////
//
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                    }
//                                                });
//
//// runOnUiThread();
//
//
//                                                Log.d("confirmmsg", "" + responses);
//
////                                            Toast.makeText(context, "msg" + responses, Toast.LENGTH_SHORT).show();
////                                            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
////                                                    .setHeaderDrawable(R.drawable.header)
////                                                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                    .withDialogAnimation(true)
////                                                    .setTitle("Confirmation Message")
////                                                    .setDescription(responses)
////                                                    .setPositiveText("OK")
////                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                        @Override
////                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                            customer_info.setText("");
////                                                            reason.setText("");
////
////
////                                                            Intent intent = new Intent(context, MainActivity.class);
////                                                            context.startActivity(intent);
////                                                        }
////                                                    });
////                                            dialogHeader_3.show();
//
//
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//
//
//                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                                .setHeaderDrawable(R.drawable.header)
//                                                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                .withDialogAnimation(true)
//                                                                .setTitle("Confirmation Message")
//                                                                .setDescription(responses)
//                                                                .setPositiveText("OK")
//                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                    @Override
//                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                    }
//                                                                });
//                                                        dialogHeader_3.show();
//                                                    }
//                                                });
//
////                                            unregisterNetworkChanges();
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
////
////
////                                                }
////                                            });
//                                            }
//                                        }
//                                    });
//
//
//                                }
////                            Log.e("timeInRequestOffline", "data>>>> " + COL4);
////                } while (timeInRequestOfflineData.moveToLast());
////                }
//
//
//                            }
//                            timeInRequestOfflineDatas.close();
//                        }
//
//
//                    }
//
//
//                }
//
//
//
////                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                        .setHeaderDrawable(R.drawable.header)
////                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                        .withDialogAnimation(true)
////                        .setTitle("Confirmation Message")
////                        .setDescription("Data Inserted Successfully..!!")
////                        .setPositiveText("OK")
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                customer_info.setText("");
////                                reason.setText("");
////
////
////                                startActivity(new Intent(fragment_manual_in_form.this, Home_Screen.class));
////
////                            }
////                        });
////                dialogHeader_3.show();
//        } else {
//
//            Toast.makeText(context, "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//
//        else
//        {
//            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Error Message")
//                    .setDescription("Data Already Exist..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        }
//                    });
//            dialogHeader_3.show();
////
//        }
//    }

















    private void startStep3() {

        //And it will be keep running until you close the entire application from task manager.
        //This method will executed only once.
        pDialog = Utilss.showSweetLoader(fragment_manual_in_form.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


        if (!mAlreadyStartedService )
        {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utilss.hideSweetLoader(pDialog);

                }
            });


//            mMsgView.setText(R.string.msg_location_service_started);



            //Start location sharing service to app server.........
            Intent intent = new Intent(this, LocationMonitoringService.class);
            startService(intent);

            mAlreadyStartedService = true;
            //Ends................................................
        }
    }












//    protected void onResume(){
//        super.onResume();
//        isLocationEnabled();
//    }
//
//    private void isLocationEnabled() {
//
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            AlertDialog.Builder alertDialog=new AlertDialog.Builder(fragment_manual_in_form.this);
//            alertDialog.setTitle("Enable Location");
//            alertDialog.setMessage("Your locations setting is not enabled. Please enabled it in settings menu.");
//            alertDialog.setPositiveButton("Location Settings", new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivity(intent);
//                }
//            });
//            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alert=alertDialog.create();
//            alert.show();
//        }
////        else{
////            AlertDialog.Builder alertDialog=new AlertDialog.Builder(fragment_manual_in_form.this);
////            alertDialog.setTitle("Confirm Location");
////            alertDialog.setMessage("Your Location is enabled, please enjoy");
////            alertDialog.setNegativeButton("Back to interface",new DialogInterface.OnClickListener(){
////                public void onClick(DialogInterface dialog, int which){
////                    dialog.cancel();
////                }
////            });
////            AlertDialog alert=alertDialog.create();
////            alert.show();
////        }
//    }
//}









//    LocationListener locationListenerGPS=new LocationListener() {
//        @Override
//        public void onLocationChanged(android.location.Location location) {
//             latitude=location.getLatitude();
//             longitude=location.getLongitude();
//            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
//            Toast.makeText(fragment_manual_in_form.this,msg,Toast.LENGTH_LONG).show();
//
//            Log.d("Latitude" , ""+latitude);
//            Log.d("Longitude" , ""+longitude);
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };



    boolean insertDatas = false;
    public void AddData() {

        if(!mDatabaseHelper.IsDataExist(datetime33) &&!mDatabaseHelper.IsDataExist2(datetime33) && !mDatabaseHelper.IsDataExistAttendance(datetime33))
        {
            insertDatas = mDatabaseHelper.addData("",datetime33, datetime2,"" , "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");


//            public boolean addData(String AttendanceDate, String date_in,String time_in,                             String date_out,String time_out,String reason , String customer_info ,String xcordinate,String ycordinate ,String type,String issync,String reponse) {

            if (insertDatas) {


                Toast.makeText(getApplicationContext(), "Data Inserted Successfully..!!", Toast.LENGTH_SHORT).show();

                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
                        .setHeaderDrawable(R.drawable.header)
                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                        .withDialogAnimation(true)
                        .setTitle("Confirmation Message")
                        .setDescription("Data Inserted Successfully..!!")
                        .setPositiveText("OK")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                customer_info.setText("");
                                reason.setText("");


                                startActivity(new Intent(fragment_manual_in_form.this, Home_Screen.class));

                            }
                        });
                dialogHeader_3.show();
            }

            else {

                Toast.makeText(context, "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
            }

        }


        else
        {
//            Toast.makeText(context, "Date Already available..!!", Toast.LENGTH_SHORT).show();


            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Data Already Exist..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        }
                    });
            dialogHeader_3.show();
//
        }



//        Cursor timeRequestOfflineData = mDatabaseHelper.getData();
//        if (timeRequestOfflineData.moveToFirst()) {
//            do {
//                String date_ins2 = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4));
//
//                if (date_ins2.equals("")) {
//                    insertData = mDatabaseHelper.addData(datetime3, "", datetime2, "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");
//                } else {
//
//                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Error Message")
//                            .setDescription("Data Already Exist..!!")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                }
//                            });
//                    dialogHeader_3.show();
//
//
////            Toast.makeText(getApplicationContext(), "Data is already exists", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//            while (timeRequestOfflineData.moveToNext());



//
    }


    //}



    // }


//    }


    public boolean isNetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

//    private void registerNetworkBroadcastForNougat() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        } else {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//    }


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mGoogleMap = googleMap;
//
//
//        if (ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        } else {
//
//        }
//
//        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mGoogleMap.setMyLocationEnabled(true);
//
//        mGoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
////
////                    }
////                });
//
//
//                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
//                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
//                mGoogleMap.clear();
//
//                MarkerOptions mp = new MarkerOptions();
////                mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
//
//
//                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
//                        .title("You Are Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//
//                CircleOptions circleOptions = new CircleOptions();
//                circleOptions.center(new LatLng(location.getLatitude(), location.getLongitude()));
//                circleOptions.radius(500);
//                circleOptions.fillColor(Color.TRANSPARENT);
//                circleOptions.strokeWidth(3);
//
//                mGoogleMap.addCircle(circleOptions);
//
//
//                lat = location.getLatitude();
//                longl = location.getLongitude();
//
//                Log.d("LatIs11", "" + lat);
//                Log.d("LongIs22", "" + longl);
//
////                mp.title("my position");
//
////                googleMap.addMarker(mp);
//                mGoogleMap.moveCamera(center);
//                mGoogleMap.animateCamera(zoom);
//
//            }
//        });
//    }

//    boolean IsSyncRunning = false;
//
//    public void dialog(boolean value) {
//        isNetAvailable = value;
//        Log.e("Main Activity dialog", "Status >>>" + value);
//
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Utilss.hideSweetLoader(pDialog);
//            }
//        });
//
//
////        if (reason.getText().toString().equals("") && customer_info.getText().toString().equals("")) {
////        } else
////        } else
//
//        {
//
//
//            if (value) {
//                IsSyncRunning = true;
//                final DatabaseHelper dbHelper = new DatabaseHelper(context);
//                final boolean request_result = true;
//
//
////            //Manual Time Entry(3)
////            Cursor timeRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);
////            Log.e("timeRequestOffline", "COUNT >>>>> " + timeRequestOfflineData.getColumnCount());
//
//
//                //Manual Time In(1)
//                //Cursor isSynced = dbHelper.getUpdatedStatus();
//
//
//                Cursor timeInRequestOfflineData = dbHelper.getFilteredOfflineData(DatabaseHelper.TYPE_MANUAl_TIME_IN);
//
//
//                if                (timeInRequestOfflineData.getCount() > 0){
////                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                            .setHeaderDrawable(R.drawable.header)
////                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                            .withDialogAnimation(true)
////                            .setTitle("Info")
////                            .setDescription("Data already Synched")
////                            .setPositiveText("OK")
////                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                @Override
////                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                }
////                            });
////                    dialogHeader_3.show();
////
////                    Toast.makeText(fragment_manual_in_form.this, "Data already Synched", Toast.LENGTH_SHORT).show();
////                }else {
//                    if (timeInRequestOfflineData.moveToFirst()) {
//
//                        while (timeInRequestOfflineData.moveToNext()) {
//                            //TODO Make Server Manual Time In Request Here
//                            final int ID = timeInRequestOfflineData.getInt(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL1));
//                            String COL2 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL2));
//                            String COL3 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL3));
//
////                            String COL4 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4));
////                            String COL5 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL5));
//
////                            String COL4 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4));
//                            String COL6 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL6));
//                            String COL7 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL7));
//                            String COL8 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL8));
//                            String COL9 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL9));
//
//
//                            if (request_result) {
//
//
//                                RequestBody formBody = new FormBody.Builder().add("DateIn", COL2).add("TimeIn", COL3)
//                                        .add("Reason", COL6)
//                                        .add("CustomerInfo", COL7)
//                                        .add("xCoordinates", COL8)
//                                        .add("YCoordinates", COL9).build();
//
//
////                                Request request = new Request.Builder()
////                                        .url(urls + "api/Manual/ManualInRequest?DateIn=" + COL2 + "&TimeIn=" + COL3 + "&Reason=" + COL6 + "&CustomerInfo=" + COL7 + "&xCoordinates=" + COL8 + "&YCoordinates=" + COL9)
////                                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                                        .addHeader("Cache-Control", "no-cache")
////                                        .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                                        .addHeader("Postman-Token", "201ec739-0b6d-4f37-85cc-7ce003be58d2")
////                                        .build();
//
//                                Request request = new Request.Builder()
//                                        .url(urls + "api/Manual/ManualInRequest?DateIn=" + COL2 + "&TimeIn=" + COL3 + "&Reason=" + COL6 + "&CustomerInfo=" + COL7 + "&xCoordinates=" + COL8 + "&YCoordinates=" + COL9)
//                                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                        .addHeader("Cache-Control", "no-cache")
//                                        .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                        .addHeader("Postman-Token", "059d8930-4241-43fa-a61d-c3162b9a60c4")
//                                        .build();
//
//
//                                Call call = client.newCall(request);
//                                Log.d("testing", "2");
//                                call.enqueue(new Callback() {
//                                    @Override
//                                    public void onFailure(Call call, IOException e) {
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
////                                        circularProgressBar.setVisibility(View.GONE);
////                                        circularProgressBar.stop();
//                                            }
//                                        });
//
//
//                                        Log.e("HttpService", "onFailure() Request was: " + call);
//                                        e.printStackTrace();
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//                                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                        .setHeaderDrawable(R.drawable.header)
//                                                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                        .withDialogAnimation(true)
//                                                        .setTitle("Error Message")
//                                                        .setDescription("Cant Connect To Server")
//                                                        .setPositiveText("OK")
//                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                            @Override
//                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                            }
//                                                        });
//                                                dialogHeader_3.show();
//                                            }
//                                        });
//                                    }
//
////                                    }
//
//                                    @Override
//                                    public void onResponse(Call call, Response response) throws IOException {
//
//                                        responses = response.body().string();
//                                        Log.e("response", "onResponse(): " + responses);
//
//                                        if (response.code() == 500) {
//                                            dbHelper.updateStatus(ID, "true");
//                                            Utilss.hideSweetLoader(pDialog);
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                }
//                                            });
//
//                                            try {
//                                                JSONObject jsonObject = new JSONObject(responses);
//                                                value2 = jsonObject.getString("exceptionMessage");
//                                                Log.d("ValueResponse", value2);
//
//
//                                                Log.d("msgError", "" + value2);
////                                                Toast.makeText(context, "msgError" + value2, Toast.LENGTH_SHORT).show();
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//
//
//                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                                .setHeaderDrawable(R.drawable.header)
//                                                                .setIcon(new IconicsDrawable(Objects.requireNonNull(fragment_manual_in_form.this)).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                .withDialogAnimation(true)
//                                                                .setTitle("Error Message")
//                                                                .setDescription(value2)
//                                                                .setPositiveText("OK")
//                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                    @Override
//                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                    }
//                                                                });
//                                                        dialogHeader_3.show();
//                                                    }
//                                                });
//
//
////
//
//
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//
//                                        } else {
//                                            dbHelper.updateStatus(ID, "true");
//                                            Utilss.hideSweetLoader(pDialog);
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////
//
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                }
//                                            });
//
//// runOnUiThread();
//
//
//                                            Log.d("confirmmsg", "" + responses);
//
////                                            Toast.makeText(context, "msg" + responses, Toast.LENGTH_SHORT).show();
////                                            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
////                                                    .setHeaderDrawable(R.drawable.header)
////                                                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                    .withDialogAnimation(true)
////                                                    .setTitle("Confirmation Message")
////                                                    .setDescription(responses)
////                                                    .setPositiveText("OK")
////                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                        @Override
////                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                            customer_info.setText("");
////                                                            reason.setText("");
////
////
////                                                            Intent intent = new Intent(context, MainActivity.class);
////                                                            context.startActivity(intent);
////                                                        }
////                                                    });
////                                            dialogHeader_3.show();
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//
//                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                            .setHeaderDrawable(R.drawable.header)
//                                                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                            .withDialogAnimation(true)
//                                                            .setTitle("Confirmation Message")
//                                                            .setDescription(responses)
//                                                            .setPositiveText("OK")
//                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                @Override
//                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                }
//                                                            });
//                                                    dialogHeader_3.show();
//                                                }
//                                            });
//
////                                            unregisterNetworkChanges();
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
////
////
////                                                }
////                                            });
//                                        }
//                                    }
//                                });
//
//
//                            }
////                            Log.e("timeInRequestOffline", "data>>>> " + COL4);
////                } while (timeInRequestOfflineData.moveToLast());
////                }
//
//
//                        }
//                        timeInRequestOfflineData.close();
//                    }
//
//                }
//                IsSyncRunning =false;



//                if (isSynced.equals("true")) {
//
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Info")
//                                    .setDescription("Data already Synched")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                        }
//                                    });
//                            dialogHeader_3.show();
//                        }
//                    });
//
////
////            } else {
//
//
////            }
//                } else
//
//                {
//                    Utilss.hideSweetLoader(pDialog);
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                    }
////                });
//                }
//            }

//        }


//    }

    //    protected void unregisterNetworkChanges() {
//        try {
//            unregisterReceiver(networkChangeReceiver);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterNetworkChanges();
        stopService(new Intent(this, LocationMonitoringService.class));
//        mAlreadyStartedService = false;
    }




//    public class NetworkChangeReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//
//                if(!IsSyncRunning) {
//                    if (isOnline(context)) {
//                        dialog(true);
//                        Log.e("NetworkChangeRerciver", "Online Connect Intenet - IN ");
//                    } else {
////                        dialog(false);
//                        Log.e("NetworkChangeRerciver", "Conectivity Failure !!! ");
//                    }
//                }else{
//                    Log.e("NetworkChangeRerciver", "Data Sync is already in running ");
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private boolean isOnline(Context context) {
//            try {
//                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo netInfo = cm.getActiveNetworkInfo();
//                //should check null because in airplane mode it will be null
//                return (netInfo != null && netInfo.isConnected());
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//
//
//
//    }



    @Override
    protected void onResume() {
        super.onResume();
        customer_info.setText("");
        reason.setText("");
    }


}












//package com.example.tisapp;
//
//import android.annotation.SuppressLint;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.location.Location;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.example.tisapp.Login.LoginActivity;
////import com.example.tisapp.Receivers.NetworkChangeReceiver;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CircleOptions;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.mikepenz.iconics.IconicsDrawable;
//import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
//import com.rey.material.widget.ProgressView;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import static android.view.View.GONE;
//import static android.view.View.VISIBLE;
//import static com.example.tisapp.Settings.Settings_Activity.urls;
//import static com.example.tisapp.Settings.Settings_Activity.urls2;
//import static com.google.android.gms.internal.zzahn.runOnUiThread;
//
//import static com.google.android.gms.internal.zzahn.runOnUiThread;
//import static com.google.android.gms.internal.zzahn.zzag;
//
////import com.google.android.gms.internal.zzagz;
//
//public class fragment_manual_in_form extends BaseActivity implements OnMapReadyCallback {
//
//
//    public static boolean isNetAvailable;
//    TextView txtName, designation, tvTodayTime, tvTodayDate, tvTodayTimeAMPM;
//    String weekday_name2;
//    static com.example.tisapp.DatabaseHelper mDatabaseHelper;
//
//    String datetime;
//    static String responses;
//    static String value2;
//    static String datetime3;
//    static String datetime2;
//    static EditText customer_info;
//    static EditText reason;
//    Button btnLeaveSubmitbtnLeaveSubmit;
//    static OkHttpClient client;
//    protected GoogleMap mGoogleMap;
//
//    //protected GoogleMap mGoogleMaps;
//    boolean loaded = false;
//    double lat, longl;
//    SweetAlertDialog pDialog;
//
//    Context context;
//
//    GoogleApiClient mGoogleApiClient;
//    ProgressView circularProgressBar;
//
//    NetworkChangeReceiver networkChangeReceiver;
//
//    MaterialStyledDialog.Builder dialogHeader_3;
//
//    public fragment_manual_in_form() {
//
//        // Required empty public constructor
//    }
//
////    public static Fragment newInstance(int position) {
////        fragment_manual_in_form f = new fragment_manual_in_form();
////        // Supply num input as an argument.
////        Bundle args = new Bundle();
////        return f;
////    }
//
//    @SuppressLint("MissingSuperCall")
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.dialog_apply_manual_time_out);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//        pDialog = Utilss.showSweetLoader(fragment_manual_in_form.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
//                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();
//
//
//        context = getApplicationContext();
//        networkChangeReceiver = new NetworkChangeReceiver();
//        mDatabaseHelper = new com.example.tisapp.DatabaseHelper(this);
//        registerNetworkBroadcastForNougat();
//
//
//        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
//        if (mapFrag != null) {
//            mapFrag.getMapAsync(this);
//        }
//
//
//        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
//        circularProgressBar.setVisibility(GONE);
//        reason = (EditText) findViewById(R.id.customer_info2);
//        customer_info = (EditText) findViewById(R.id.customer_info);
//        btnLeaveSubmit = (Button) findViewById(R.id.btnLeaveSubmit);
//
//        Calendar c2 = Calendar.getInstance(); //Calendar.getInstance();
//        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
//        datetime = dateformat.format(c2.getTime());
//        System.out.println(datetime);
//
//
//        Calendar c3 = Calendar.getInstance();
//        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
//        datetime3 = date.format(c3.getTime());
//        Log.d("DateH", datetime3);
//        System.out.println(datetime3);
//
//
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat time2 = new SimpleDateFormat("hh:mm");
//        datetime2 = time2.format(c.getTime());
//        Log.d("TimeH", datetime2);
//
//        btnLeaveSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (customer_info.getText().toString().equals("")) {
////                    customer_info.setError("Info Is Required");
//
//
//                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Error Message")
//                            .setDescription("Info Is Required")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                }
//                            });
//                    dialogHeader_3.show();
//
//                } else if (reason.getText().toString().equals("")) {
//
//                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                            .setHeaderDrawable(R.drawable.header)
//                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                            .withDialogAnimation(true)
//                            .setTitle("Error Message")
//                            .setDescription("Reason Is Required")
//                            .setPositiveText("OK")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                }
//                            });
//                    dialogHeader_3.show();
//
//                } else {
//
//                    if (isNetAvailable()) {
//
//                        circularProgressBar.setVisibility(VISIBLE);
//                        circularProgressBar.start();
//
//
//                        RequestBody formBody = new FormBody.Builder().add("DateOut", datetime3).add("TimeOut", datetime2).add("Reason", reason.getText().toString())
//                                .add("CustomerInfo", customer_info.getText().toString()).add("xCoordinates", String.valueOf(lat))
//                                .add("YCoordinates", String.valueOf(longl)).build();
//
//                        Request request = new Request.Builder()
//                                .url(urls + "api/Manual/ManualOutRequest?DateOut=" + datetime3 + "&TimeOut=" + datetime2 + "&Reason=" + reason.getText().toString() + "&CustomerInfo=" + customer_info.getText().toString() + "&xCoordinates=" + String.valueOf(lat) + "&YCoordinates=" + String.valueOf(longl))
//                                .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                .addHeader("Cache-Control", "no-cache")
//                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                .addHeader("Postman-Token", "059d8930-4241-43fa-a61d-c3162b9a60c4")
//                                .build();
//
//
//                        Call call = client.newCall(request);
//                        call.enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//
//
////                                runOnUiThread(new Runnable() {
////                                    @Override
////                                    public void run() {
//////                                            Utilss.hideSweetLoader(pDialog);
////
////                                        circularProgressBar.setVisibility(GONE);
////                                        circularProgressBar.stop();
////                                    }
////                                });
//
//                                Log.e("HttpService", "onFailure() Request was: " + call);
//                                e.printStackTrace();
//
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                .setHeaderDrawable(R.drawable.header)
//                                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                .withDialogAnimation(true)
//                                                .setTitle("Error Message")
//                                                .setDescription("Cant Connect To Server")
//                                                .setPositiveText("OK")
//                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    }
//                                                });
//                                        dialogHeader_3.show();
//                                    }
//                                });
//
//
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//
//                                responses = response.body().string();
//                                Log.e("response", "onResponse(): " + responses);
//
//                                if (response.code() == 500) {
//
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                            circularProgressBar.setVisibility(GONE);
//                                            circularProgressBar.stop();
//                                        }
//                                    });
//
//                                    try {
//                                        JSONObject jsonObject = new JSONObject(responses);
//                                        value2 = jsonObject.getString("exceptionMessage");
//                                        Log.d("ValueResponse", value2);
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//                                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                        .setHeaderDrawable(R.drawable.header)
//                                                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                        .withDialogAnimation(true)
//                                                        .setTitle("Error Message")
//                                                        .setDescription(value2)
//                                                        .setPositiveText("OK")
//                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                            @Override
//                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                customer_info.setText("");
//                                                                reason.setText("");
//                                                            }
//                                                        });
//                                                dialogHeader_3.show();
//
//                                            }
//                                        });
//
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//
//                                } else {
//
//
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
////                                            Utilss.hideSweetLoader(pDialog);
//
//                                            circularProgressBar.setVisibility(GONE);
//                                            circularProgressBar.stop();
//                                        }
//                                    });
//
//
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                    .setHeaderDrawable(R.drawable.header)
//                                                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                    .withDialogAnimation(true)
//                                                    .setTitle("Confirmation Message")
//                                                    .setDescription(responses)
//                                                    .setPositiveText("OK")
//                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                        @Override
//                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                            customer_info.setText("");
//                                                            reason.setText("");
//
//                                                            Intent intent = new Intent(fragment_manual_in_form.this, Home_Screen.class);
//                                                            fragment_manual_in_form.this.startActivity(intent);
//                                                        }
//                                                    });
//                                            dialogHeader_3.show();
//                                        }
//                                    });
//                                }
//                            }
//                        });
//
//
//                    } else {
//
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this.this)
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Error Message")
////                                .setDescription("Network Required..!!")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                        customer_info.setText("");
//////                                        reason.setText("");
////                                    }
////                                });
////                        dialogHeader_3.show();
//                        AddData();
//                    }
//
//                }
//            }
//        });
//
//    }
//
//    public void AddData() {
//        boolean insertData = mDatabaseHelper.addData("", datetime3, "", datetime2, reason.getText().toString(), customer_info.getText().toString(), 0.00, 0.00, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT, "false", "");
//        if (insertData) {
//
//
//            Toast.makeText(getApplicationContext(), "Data Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Confirmation Message")
//                    .setDescription("Data Inserted Successfully..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
////                            context.startActivity(new Intent(context, MainActivity.class));
////                            finish();
//
////                            _passwordText.setText("");
////                                        _loginButton.setEnabled(true);
//                        }
//                    });
//            dialogHeader_3.show();
//        }
//
//
//
////            Toast.makeText(context, "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
////
//        else {
////            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//
////            runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
//
//
////                    dialogHeader_3 = new MaterialStyledDialog.Builder(context)
////                            .setHeaderDrawable(R.drawable.header)
////                            .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                            .withDialogAnimation(true)
////                            .setTitle("Error Message")
////                            .setDescription("Something went wrong..!!")
////                            .setPositiveText("OK")
////                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                @Override
////                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                }
////                            });
////                    dialogHeader_3.show();
//
//            Toast.makeText(context, "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//        }
////            });
//
//
//
//    }
//
//
////    }
//
//
//    public boolean isNetAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return networkInfo != null && networkInfo.isConnected();
//    }
//
//    private void registerNetworkBroadcastForNougat() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        } else {
////            Objects.requireNonNull(getActivity()).registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//            this.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mGoogleMap = googleMap;
//
//
//        if (ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        } else {
//
//        }
//
//        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mGoogleMap.setMyLocationEnabled(true);
//
//        mGoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
////
////                    }
////                });
//
//
//                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
//                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
//                mGoogleMap.clear();
//
//                MarkerOptions mp = new MarkerOptions();
////                mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
//
//
//                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
//                        .title("You Are Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//
//                CircleOptions circleOptions = new CircleOptions();
//                circleOptions.center(new LatLng(location.getLatitude(), location.getLongitude()));
//                circleOptions.radius(500);
//                circleOptions.fillColor(Color.TRANSPARENT);
//                circleOptions.strokeWidth(3);
//
//                mGoogleMap.addCircle(circleOptions);
//
//
//                lat = location.getLatitude();
//                longl = location.getLongitude();
//
//                Log.d("LatIs11", "" + lat);
//                Log.d("LongIs22", "" + longl);
//
////                mp.title("my position");
//
////                googleMap.addMarker(mp);
//                mGoogleMap.moveCamera(center);
//                mGoogleMap.animateCamera(zoom);
//
//            }
//        });
//    }
//
//
//    public void dialog(boolean value) {
//        isNetAvailable = value;
//        Log.e("Main Activity dialog", "Status >>>" + value);
//
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Utilss.hideSweetLoader(pDialog);
//            }
//        });
//
//
//        if (reason.getText().toString().equals("") && customer_info.getText().toString().equals("")) {
//        } else
//
//        {
//
//
//            if (value) {
//                final com.example.tisapp.DatabaseHelper dbHelper = new com.example.tisapp.DatabaseHelper(context);
//                final boolean request_result = true;
//
//
////            //Manual Time Entry(3)
////            Cursor timeRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);
////            Log.e("timeRequestOffline", "COUNT >>>>> " + timeRequestOfflineData.getColumnCount());
//
//
//                //Manual Time In(1)
//                Cursor isSynced = dbHelper.getUpdatedStatus();
//
//
//                Cursor timeInRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT);
//                Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineData.getColumnCount());
//
//                if (isSynced.moveToLast()) {
//                    String updated = isSynced.getString(isSynced.getColumnIndex(com.example.tisapp.DatabaseHelper.COL11));
//
//                    if (updated.equals("true")) {
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(Objects.requireNonNull(context)).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Info")
//                                .setDescription("Data already Synched")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                    }
//                                });
//                        dialogHeader_3.show();
//
//                        Toast.makeText(context, "Data already Synched", Toast.LENGTH_SHORT).show();
//                    } else {
//                        if (timeInRequestOfflineData.moveToLast()) {
//
//
////                while (timeInRequestOfflineData.moveToLast()) {
//                            //TODO Make Server Manual Time In Request Here
//                            final int ID = timeInRequestOfflineData.getInt(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1));
////                            String COL2 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2));
//
////                            String COL3 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL));
//
//                            String COL4 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4));
//                            String COL5 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL5));
//                            String COL6 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL6));
//                            String COL7 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL7));
//                            String COL8 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL8));
//                            String COL9 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL9));
//
//
//                            if (request_result) {
//
//
////                                RequestBody formBody = new FormBody.Builder().add("DateIn", COL2).add("TimeIn", COL4)
////                                        .add("Reason", COL6)
////                                        .add("CustomerInfo", COL7)
////                                        .add("xCoordinates", COL8)
////                                        .add("YCoordinates", COL9).build();
////
////
////                                Request request = new Request.Builder()
////                                        .url(urls + "api/Manual/ManualInRequest?DateIn=" + COL2 + "&TimeIn=" + COL4 + "&Reason=" + COL6 + "&CustomerInfo=" + COL7 + "&xCoordinates=" + COL8 + "&YCoordinates=" + COL9)
////                                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                                        .addHeader("Cache-Control", "no-cache")
////                                        .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                                        .addHeader("Postman-Token", "201ec739-0b6d-4f37-85cc-7ce003be58d2")
////                                        .build();
//
//
//
//                                RequestBody formBody = new FormBody.Builder().add("DateOut", COL4).add("TimeOut", COL5).add("Reason", COL6)
//                                        .add("CustomerInfo", COL7).add("xCoordinates", COL8)
//                                        .add("YCoordinates", COL9).build();
//
//                                Request request = new Request.Builder()
//                                        .url(urls + "api/Manual/ManualOutRequest?DateOut=" + COL4 + "&TimeOut=" + COL5 + "&Reason=" + COL6 + "&CustomerInfo=" + COL7 + "&xCoordinates=" + COL8 + "&YCoordinates=" + COL9)
//                                        .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
//                                        .addHeader("Cache-Control", "no-cache")
//                                        .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                        .addHeader("Postman-Token", "059d8930-4241-43fa-a61d-c3162b9a60c4")
//                                        .build();
//
//
//
//                                Call call = client.newCall(request);
//                                Log.d("testing", "2");
//                                call.enqueue(new Callback() {
//                                    @Override
//                                    public void onFailure(Call call, IOException e) {
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
////                                        circularProgressBar.setVisibility(View.GONE);
////                                        circularProgressBar.stop();
//                                            }
//                                        });
//
//
//                                        Log.e("HttpService", "onFailure() Request was: " + call);
//                                        e.printStackTrace();
//
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//                                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                        .setHeaderDrawable(R.drawable.header)
//                                                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                        .withDialogAnimation(true)
//                                                        .setTitle("Error Message")
//                                                        .setDescription("Cant Connect To Server")
//                                                        .setPositiveText("OK")
//                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                            @Override
//                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                            }
//                                                        });
//                                                dialogHeader_3.show();
//                                            }
//                                        });
//                                    }
//
////                                    }
//
//                                    @Override
//                                    public void onResponse(Call call, Response response) throws IOException {
//
//                                        responses = response.body().string();
//                                        Log.e("response", "onResponse(): " + responses);
//
//                                        if (response.code() == 500) {
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                }
//                                            });
//
//                                            try {
//                                                JSONObject jsonObject = new JSONObject(responses);
//                                                value2 = jsonObject.getString("exceptionMessage");
//                                                Log.d("ValueResponse", value2);
//
//
//                                                Log.d("msgError", "" + value2);
////                                                Toast.makeText(context, "msgError" + value2, Toast.LENGTH_SHORT).show();
//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//
//
//                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                                .setHeaderDrawable(R.drawable.header)
//                                                                .setIcon(new IconicsDrawable(Objects.requireNonNull(fragment_manual_in_form.this)).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                .withDialogAnimation(true)
//                                                                .setTitle("Error Message")
//                                                                .setDescription(value2)
//                                                                .setPositiveText("OK")
//                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                    @Override
//                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                                                        Intent intent = new Intent(fragment_manual_in_form.this, Home_Screen.class);
//                                                                        fragment_manual_in_form.this.startActivity(intent);
//                                                                    }
//                                                                });
//                                                        dialogHeader_3.show();
//                                                    }
//                                                });
//
//
////
//
//
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//
//                                        } else {
//
//                                            unregisterNetworkChanges();
//
//                                            dbHelper.updateStatus(ID, "true");
//                                            Utilss.hideSweetLoader(pDialog);
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
////
//
////                                            circularProgressBar.setVisibility(View.GONE);
////                                            circularProgressBar.stop();
//                                                }
//                                            });
//
//// runOnUiThread();
//
//
//                                            Log.d("confirmmsg", "" + responses);
//
////                                            Toast.makeText(context, "msg" + responses, Toast.LENGTH_SHORT).show();
////                                            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
////                                                    .setHeaderDrawable(R.drawable.header)
////                                                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                    .withDialogAnimation(true)
////                                                    .setTitle("Confirmation Message")
////                                                    .setDescription(responses)
////                                                    .setPositiveText("OK")
////                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                        @Override
////                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                            customer_info.setText("");
////                                                            reason.setText("");
////
////
////                                                            Intent intent = new Intent(context, MainActivity.class);
////                                                            context.startActivity(intent);
////                                                        }
////                                                    });
////                                            dialogHeader_3.show();
//
//
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//
//                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                                            .setHeaderDrawable(R.drawable.header)
//                                                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                            .withDialogAnimation(true)
//                                                            .setTitle("Confirmation Message")
//                                                            .setDescription(responses)
//                                                            .setPositiveText("OK")
//                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                @Override
//                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                                }
//                                                            });
//                                                    dialogHeader_3.show();
//                                                }
//                                            });
//
//
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
////
////
////                                                }
////                                            });
//                                        }
//                                    }
//                                });
//
//
//                            }
//                            Log.e("timeInRequestOffline", "data>>>> " + COL4);
////                } while (timeInRequestOfflineData.moveToLast());
////                }
//
//                            timeInRequestOfflineData.close();
//                        }
//                    }
//
//                }
//
//                if (isSynced.equals("true")) {
//
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(Objects.requireNonNull(fragment_manual_in_form.this)).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Info")
//                                    .setDescription("Data already Synched")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                        }
//                                    });
//                            dialogHeader_3.show();
//                        }
//                    });
//
////
////            } else {
//
//
////            }
//                } else
//
//                {
//                    Utilss.hideSweetLoader(pDialog);
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                    }
////                });
//                }
//            }
//
//        }
//
//
//    }
//
//    protected void unregisterNetworkChanges() {
//        try {
//            unregisterReceiver(networkChangeReceiver);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterNetworkChanges();
//    }
//
//
//
//    public class NetworkChangeReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//
//
//                if (isOnline(context)) {
//                    dialog(true);
//                    Log.e("NetworkChangeRerciver", "Online Connect Intenet ");
//                } else {
//                    dialog(false);
//                    Log.e("NetworkChangeRerciver", "Conectivity Failure !!! ");
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private boolean isOnline(Context context) {
//            try {
//                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo netInfo = cm.getActiveNetworkInfo();
//                //should check null because in airplane mode it will be null
//                return (netInfo != null && netInfo.isConnected());
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//                return false;
//            }
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
//
//
//
//
//
//
////package com.example.tisapp;
////
////import android.annotation.SuppressLint;
////import android.content.Context;
////import android.content.Intent;
////import android.content.pm.PackageManager;
////import android.graphics.Color;
////import android.location.Location;
////import android.net.ConnectivityManager;
////import android.net.NetworkInfo;
////import android.os.Bundle;
////import android.support.annotation.NonNull;
////import android.support.v4.app.ActivityCompat;
////import android.support.v4.app.Fragment;
////import android.support.v7.app.AppCompatActivity;
////import android.util.Log;
////
////import android.view.View;
////import android.view.WindowManager;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////
////import com.afollestad.materialdialogs.DialogAction;
////import com.afollestad.materialdialogs.MaterialDialog;
////import com.example.tisapp.Login.LoginActivity;
////import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
////import com.google.android.gms.common.api.GoogleApiClient;
////import com.google.android.gms.maps.CameraUpdate;
////import com.google.android.gms.maps.CameraUpdateFactory;
////import com.google.android.gms.maps.GoogleMap;
////import com.google.android.gms.maps.MapFragment;
////import com.google.android.gms.maps.OnMapReadyCallback;
////import com.google.android.gms.maps.SupportMapFragment;
////import com.google.android.gms.maps.model.BitmapDescriptorFactory;
////import com.google.android.gms.maps.model.CircleOptions;
////import com.google.android.gms.maps.model.LatLng;
////import com.google.android.gms.maps.model.MarkerOptions;
////import com.mikepenz.iconics.IconicsDrawable;
////import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
////import com.rey.material.widget.ProgressView;
////
////import org.json.JSONException;
////import org.json.JSONObject;
////
////import java.io.IOException;
////import java.text.SimpleDateFormat;
////import java.util.Calendar;
////import java.util.Objects;
////import java.util.concurrent.TimeUnit;
////
////import cn.pedant.SweetAlert.SweetAlertDialog;
////import okhttp3.Call;
////import okhttp3.Callback;
////import okhttp3.FormBody;
////import okhttp3.OkHttpClient;
////import okhttp3.Request;
////import okhttp3.RequestBody;
////import okhttp3.Response;
////
////import static android.view.View.GONE;
////import static android.view.View.VISIBLE;
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
////import static com.example.tisapp.Settings.Settings_Activity.urls;
////import static com.example.tisapp.Settings.Settings_Activity.urls2;
////import static com.google.android.gms.internal.zzahn.runOnUiThread;
////
//////import com.google.android.gms.internal.zzagz;
////
////public class fragment_manual_in_form extends AppCompatActivity implements OnMapReadyCallback {
////
////
////    TextView txtName, designation, tvTodayTime, tvTodayDate, tvTodayTimeAMPM;
////    String weekday_name2, datetime, responses, value2, datetime3, datetime2;
////    EditText customer_info, reason;
////    Button btnLeaveSubmit;
////    OkHttpClient client;
////    protected GoogleMap mGoogleMap;
////
////    //protected GoogleMap mGoogleMaps;
////    boolean loaded=false;
////    double lat,longl ;
////    SweetAlertDialog pDialog;
////
////
////    GoogleApiClient mGoogleApiClient;
////    ProgressView circularProgressBar;
////
////    MaterialStyledDialog.Builder dialogHeader_3;
////
////    public fragment_manual_in_form() {
////
////        // Required empty public constructor
////    }
////
//////    public static Fragment newInstance(int position) {
//////        fragment_manual_in_form f = new fragment_manual_in_form();
//////        // Supply num input as an argument.
//////        Bundle args = new Bundle();
//////        return f;
//////    }
////
////    @SuppressLint("MissingSuperCall")
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        setContentView(R.layout.dialog_apply_manual_time_out);
////        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
////
////        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
////                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();
////
////
//////        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
//////        mapFragment.getMapAsync(this);
////
////       SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
////        if (mapFrag != null)
////        {
////            mapFrag.getMapAsync(this);
////        }
////
////
////        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
////        circularProgressBar.setVisibility(GONE);
////        reason = (EditText) findViewById(R.id.customer_info2);
////        customer_info = (EditText) findViewById(R.id.customer_info);
////        btnLeaveSubmit = (Button) findViewById(R.id.btnLeaveSubmit);
////
////        Calendar c2 = Calendar.getInstance(); //Calendar.getInstance();
////        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
////        datetime = dateformat.format(c2.getTime());
////        System.out.println(datetime);
////
////
////        Calendar c3 = Calendar.getInstance();
////        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
////        datetime3 = date.format(c3.getTime());
////        Log.d("DateH", datetime3);
////        System.out.println(datetime3);
////
////
////        Calendar c = Calendar.getInstance();
////        SimpleDateFormat time2 = new SimpleDateFormat("hh:mm");
////        datetime2 = time2.format(c.getTime());
////        Log.d("TimeH", datetime2);
////
////        btnLeaveSubmit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////                if (customer_info.getText().toString().equals("")) {
//////                    customer_info.setError("Info Is Required");
////
////
////                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                            .setHeaderDrawable(R.drawable.header)
////                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                            .withDialogAnimation(true)
////                            .setTitle("Error Message")
////                            .setDescription("Info Is Required")
////                            .setPositiveText("OK")
////                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                @Override
////                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                }
////                            });
////                    dialogHeader_3.show();
////
////                } else if (reason.getText().toString().equals("")) {
////
////                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                            .setHeaderDrawable(R.drawable.header)
////                            .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                            .withDialogAnimation(true)
////                            .setTitle("Error Message")
////                            .setDescription("Reason Is Required")
////                            .setPositiveText("OK")
////                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                @Override
////                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                }
////                            });
////                    dialogHeader_3.show();
////
////                } else {
////
////                    if (isNetAvailable()) {
////
////                        circularProgressBar.setVisibility(VISIBLE);
////                        circularProgressBar.start();
////
////
////                        RequestBody formBody = new FormBody.Builder().add("DateOut", datetime3).add("TimeOut", datetime2).add("Reason", reason.getText().toString())
////                                .add("CustomerInfo", customer_info.getText().toString()).add("xCoordinates", String.valueOf(lat))
////                                .add("YCoordinates", String.valueOf(longl)).build();
////
////                        Request request = new Request.Builder()
////                                .url(urls + "api/Manual/ManualOutRequest?DateOut=" + datetime3 + "&TimeOut=" + datetime2 + "&Reason=" + reason.getText().toString() + "&CustomerInfo=" + customer_info.getText().toString() + "&xCoordinates=" + String.valueOf(lat) + "&YCoordinates=" + String.valueOf(longl))
////                                .post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                                .addHeader("Cache-Control", "no-cache")
////                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                                .addHeader("Postman-Token", "059d8930-4241-43fa-a61d-c3162b9a60c4")
////                                .build();
////
////
////                        Call call = client.newCall(request);
////                        call.enqueue(new Callback() {
////                            @Override
////                            public void onFailure(Call call, IOException e) {
////
////
//////                                runOnUiThread(new Runnable() {
//////                                    @Override
//////                                    public void run() {
////////                                            Utilss.hideSweetLoader(pDialog);
//////
//////                                        circularProgressBar.setVisibility(GONE);
//////                                        circularProgressBar.stop();
//////                                    }
//////                                });
////
////                                Log.e("HttpService", "onFailure() Request was: " + call);
////                                e.printStackTrace();
////
////
////                                runOnUiThread(new Runnable() {
////                                    @Override
////                                    public void run() {
////
////                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                                                .setHeaderDrawable(R.drawable.header)
////                                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                .withDialogAnimation(true)
////                                                .setTitle("Error Message")
////                                                .setDescription("Cant Connect To Server")
////                                                .setPositiveText("OK")
////                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                    @Override
////                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    }
////                                                });
////                                        dialogHeader_3.show();
////                                    }
////                                });
////
////
////                            }
////
////                            @Override
////                            public void onResponse(Call call, Response response) throws IOException {
////
////                                responses = response.body().string();
////                                Log.e("response", "onResponse(): " + responses);
////
////                                if (response.code() == 500) {
////
////                                    runOnUiThread(new Runnable() {
////                                        @Override
////                                        public void run() {
////
////                                            circularProgressBar.setVisibility(GONE);
////                                            circularProgressBar.stop();
////                                        }
////                                    });
////
////                                    try {
////                                        JSONObject jsonObject = new JSONObject(responses);
////                                        value2 = jsonObject.getString("exceptionMessage");
////                                        Log.d("ValueResponse", value2);
////
////
////                                        runOnUiThread(new Runnable() {
////                                            @Override
////                                            public void run() {
////
////                                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                                                        .setHeaderDrawable(R.drawable.header)
////                                                        .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                        .withDialogAnimation(true)
////                                                        .setTitle("Error Message")
////                                                        .setDescription(value2)
////                                                        .setPositiveText("OK")
////                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                            @Override
////                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                                customer_info.setText("");
////                                                                reason.setText("");
////                                                            }
////                                                        });
////                                                dialogHeader_3.show();
////
////                                            }
////                                        });
////
////
////                                    } catch (JSONException e) {
////                                        e.printStackTrace();
////                                    }
////
////
////                                } else {
////
////
////                                    runOnUiThread(new Runnable() {
////                                        @Override
////                                        public void run() {
//////                                            Utilss.hideSweetLoader(pDialog);
////
////                                            circularProgressBar.setVisibility(GONE);
////                                            circularProgressBar.stop();
////                                        }
////                                    });
////
////
////                                    runOnUiThread(new Runnable() {
////                                        @Override
////                                        public void run() {
////
////                                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                                                    .setHeaderDrawable(R.drawable.header)
////                                                    .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                    .withDialogAnimation(true)
////                                                    .setTitle("Confirmation Message")
////                                                    .setDescription(responses)
////                                                    .setPositiveText("OK")
////                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                        @Override
////                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                            customer_info.setText("");
////                                                            reason.setText("");
////
////                                                            Intent intent = new Intent(fragment_manual_in_form.this, Home_Screen.class);
////                                                            fragment_manual_in_form.this.startActivity(intent);
////                                                        }
////                                                    });
////                                            dialogHeader_3.show();
////                                        }
////                                    });
////                                }
////                            }
////                        });
////
////
////
////                    } else {
////
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_in_form.this)
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(fragment_manual_in_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Error Message")
////                                .setDescription("Network Required..!!")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                        customer_info.setText("");
//////                                        reason.setText("");
////                                    }
////                                });
////                        dialogHeader_3.show();
////
////                    }
////
////                }
////            }
////        });
////
////                    }
////
////
////               // }
////                //}
////
////
////
////
////
////    public boolean isNetAvailable() {
////        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
////        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
////        return networkInfo != null && networkInfo.isConnected();
////    }
////
////
////
////
////    @Override
////    public void onMapReady(GoogleMap googleMap) {
////        mGoogleMap = googleMap;
////
////
////        if (ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(fragment_manual_in_form.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
////        {
////            return;
////        }
////
////        else
////        {
////
////        }
////
////        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
////        mGoogleMap.setMyLocationEnabled(true);
////
////        mGoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
////            @Override
////            public void onMyLocationChange(Location location) {
////
////
//////                runOnUiThread(new Runnable() {
//////                    @Override
//////                    public void run() {
//////                        Utilss.hideSweetLoader(pDialog);
//////
//////                    }
//////                });
////
////
////                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
////                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
////                mGoogleMap.clear();
////
////                MarkerOptions mp = new MarkerOptions();
//////                mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
////
////
////                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
////                        .title("You Are Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
////
////                CircleOptions circleOptions = new CircleOptions();
////                circleOptions.center(new LatLng(location.getLatitude(),location.getLongitude()));
////                circleOptions.radius(500);
////                circleOptions.fillColor(Color.TRANSPARENT);
////                circleOptions.strokeWidth(3);
////
////                mGoogleMap.addCircle(circleOptions);
////
////
////                lat = location.getLatitude();
////                longl = location.getLongitude();
////
////                Log.d("LatIs11" , ""+lat);
////                Log.d("LongIs22" , ""+longl);
////
//////                mp.title("my position");
////
//////                googleMap.addMarker(mp);
////                mGoogleMap.moveCamera(center);
////                mGoogleMap.animateCamera(zoom);
////
////            }
////        });}
////
////
////
////
////
////
////}
