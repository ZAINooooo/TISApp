package com.example.tisapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.services.LocationMonitoringService;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
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
import java.util.Date;
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

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;
import static com.example.tisapp.fragment_manual_Out_form.datetime3;
import static com.example.tisapp.fragment_manual_in_form.datetime33;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzahf.runOnUiThread;

// implements OnMapReadyCallback
public class fragment_manual_entry_form extends AppCompatActivity {


    public static Button input_date11;
    public static Button input_date1;
    public static Button input_date3;
    String latitude,longitude;
    String Latitude , Longitude;

    String attendence_date,dates_in,dats_out,customer_infos, reasons,times_in,times_out;

    public static Button input_date2;
    public static Button input_date4;
    private boolean mAlreadyStartedService = false;

    MaterialStyledDialog.Builder dialogHeader_3;
    String responses,value2;
    //    protected GoogleMap mGoogleMap;
    OkHttpClient client;
    static EditText customer_info;
    ImageView iv_back;
    static EditText reason;
    Button form_button;

    Boolean isFromClicked;
    ProgressView circularProgressBar;

    SweetAlertDialog pDialog;
    double lat,long1;
    static DatabaseHelper mDatabaseHelper;


    String datesssss,datesssss3,datesssss2,datetime,datetime2, dates;



//    public fragment_manual_entry_form() {
//        // Required empty public constructor
//    }
//
//    public static Fragment newInstance(int position) {
//        fragment_manual_entry_form f = new fragment_manual_entry_form();
//        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        return f;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_apply_manual_request);



        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();



        startStep3();


        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
                        longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);

                        if (latitude != null && longitude != null)
                        {
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

                            Log.d("LatitudeIs" , ""+Latitude);
                            Log.d("LongitudeeIs" , ""+Longitude);

                        }
                    }
                }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST)
        );





        mDatabaseHelper = new DatabaseHelper(this);



//        pDialog = Utilss.showSweetLoader(this, SweetAlertDialog.PROGRESS_TYPE, "Initializing Map...");
//        pDialog.setCancelable(true);


        String dates = getIntent().getStringExtra("Attendance_Date");

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
//        mapFragment.getMapAsync(this);


        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(View.GONE);








        input_date11 = (Button) findViewById(R.id.input_date11);
        input_date11.setInputType(InputType.TYPE_NULL);









        input_date1 = (Button)findViewById(R.id.input_date1);
        input_date1.setInputType(InputType.TYPE_NULL);

//        datesssss2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());

        Calendar c3 = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        datesssss2 = date.format(c3.getTime());
        Log.d("DateH", datesssss2);
        input_date11.setText(datesssss2);

//
//        Calendar c3 = Calendar.getInstance();
//        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
//        datetime3 = date.format(c3.getTime());
//        Log.d("DateH", datetime3);
//        System.out.println(datetime3);






        input_date2 = (Button) findViewById(R.id.input_date2);
        input_date2.setInputType(InputType.TYPE_NULL);

//        datesssss3 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());

        Calendar c5 = Calendar.getInstance();
        SimpleDateFormat dateS = new SimpleDateFormat("dd-MMM-yyyy");
        datesssss3 = date.format(c5.getTime());
        Log.d("DateH", datesssss3);
        input_date11.setText(datesssss3);


        input_date2.setText(datesssss3);




        input_date3 = (Button) findViewById(R.id.input_date3);
        input_date3.setInputType(InputType.TYPE_NULL);


        Calendar c2 = Calendar.getInstance(); //Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");
        datetime = dateformat.format(c2.getTime());
        input_date3.setText(datetime);




        input_date4 = (Button)findViewById(R.id.input_date4);
        input_date4.setInputType(InputType.TYPE_NULL);


        Calendar c = Calendar.getInstance(); //Calendar.getInstance();
        SimpleDateFormat dateformat2 = new SimpleDateFormat("hh:mm");
        datetime2 = dateformat.format(c.getTime());
        input_date4.setText(datetime2);



        reason = (EditText) findViewById(R.id.reason);
        customer_info = (EditText)findViewById(R.id.customer_info);


        form_button = (Button) findViewById(R.id.form_button);

        form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(fragment_manual_entry_form.this, "Clicked..!", Toast.LENGTH_SHORT).show();

                attendence_date = input_date11.getText().toString();
                Log.d("AttendanceDates" , attendence_date);
                dates_in = input_date1.getText().toString();
                Log.d("AttendanceDates2" , dates_in);

                dats_out = input_date2.getText().toString();
                Log.d("AttendanceDates3" , dats_out);

                customer_infos = customer_info.getText().toString();
                Log.d("AttendanceDates4" , customer_infos);

                reasons = reason.getText().toString();
                Log.d("AttendanceDates5" , reasons);

                times_in = input_date3.getText().toString();
                Log.d("AttendanceDates6" , times_in);

                times_out = input_date4.getText().toString();
                Log.d("AttendanceDates7" , times_out);




                if (attendence_date.equals("")) {
                    input_date11.setError("Attendence Date Is Required");

                } else if (dates_in.equals("")) {
                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Date In Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {

                                }
                            });
                    dialogHeader_3.show();

                } else if (dats_out.equals("")) {

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Date Out Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {
                                }
                            });
                    dialogHeader_3.show();


                } else if (customer_infos.equals("")) {

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Info Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {
//                                            input_dates.setText("");
//                                            input_dates2.setText("");
                                }
                            });
                    dialogHeader_3.show();


                } else if (reasons.equals(""))

                {


                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Reason Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {
                                }
                            });
                    dialogHeader_3.show();

                } else if (times_in.equals("")) {


                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Time In Is Requiredd")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {
//                                            input_dates.setText("");
//                                            input_dates2.setText("");
                                }
                            });
                    dialogHeader_3.show();

                } else if (times_out.equals("")) {

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Time Out Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                {
                                }
                            });
                    dialogHeader_3.show();
                }




                else {

                    AddData();
                }
            }
        });









        iv_back =(ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });







        if (dates!=null)
        {
            input_date11.setText(dates);
            input_date1.setText(dates);
            input_date2.setText(dates);

            Log.d("Dateis", dates);
        }

        else {


//            input_date1.setText("1-Jan-2019");
//            input_date2.setText("1-Jan-2019");
//            input_date11.setText("2017-06-30");
//            input_date3.setText("11:50");
//            input_date4.setText("11:50");


//            input_date11.setText("1-Jan-2019");
////            input_date1.setText("1-Jan-2019");
////            input_date2.setText("1-Jan-2019");

            datesssss = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
            input_date11.setText(datesssss);


            datesssss2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
            input_date1.setText(datesssss2);


            datesssss3 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
            input_date2.setText(datesssss3);

//            Log.d("Dateis", date_is);
        }



        input_date11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment newFragment = new selected_date_fragment1();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });




        input_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment2();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        input_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment3();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });





        input_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePicker = new TimePickerFragment(input_date3, getApplicationContext());
                timePicker.show(getSupportFragmentManager(), "Start Time");
                isFromClicked = false;
            }
        });




        input_date4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerFragment2 timePicker = new TimePickerFragment2(input_date4, getApplicationContext());
                timePicker.show(getSupportFragmentManager(), "Start Time");






//                form_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                         attendence_date = input_date11.getText().toString();
//                         Log.d("AttendanceDates" , attendence_date);
//                         dates_in = input_date1.getText().toString();
//                         dats_out = input_date2.getText().toString();
//                         customer_infos = customer_info.getText().toString();
//                         reasons = reason.getText().toString();
//                         times_in = input_date3.getText().toString();
//                         times_out = input_date4.getText().toString();
//
//                        if (attendence_date.equals("")) {
//                            input_date11.setError("Attendence Date Is Required");
//
//                        } else if (dates_in.equals("")) {
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Date In Is Required")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
//
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
//                        } else if (dats_out.equals("")) {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Date Out Is Required")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
//
//                        } else if (customer_infos.equals("")) {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Info Is Required")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
////                                            input_dates.setText("");
////                                            input_dates2.setText("");
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
//
//                        } else if (reasons.equals(""))
//
//                        {
//
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Reason Is Required")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
//                        } else if (times_in.equals("")) {
//
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Time In Is Requiredd")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
////                                            input_dates.setText("");
////                                            input_dates2.setText("");
//                                        }
//                                    });
//                            dialogHeader_3.show();
//
//                        } else if (times_out.equals("")) {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Time Out Is Required")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                                        {
//                                        }
//                                    });
//                            dialogHeader_3.show();
//                        }
//
//
//
//
//                        else {
//
//
//
//                            AddData();
//
//
////                            if (isNetAvailable())
////                            {
////
////                                circularProgressBar.setVisibility(View.VISIBLE);
////                                circularProgressBar.start();
////
////                                String Reason = reason.getText().toString();
////                                String Customer_Info = customer_info.getText().toString();
////
////                                RequestBody formBody = new FormBody.Builder().add("AttendanceDate", input_date11.getText().toString())
////
////                                        .add("DateIn", input_date1.getText().toString())
////                                        .add("TimeIn", input_date3.getText().toString())
////                                        .add("DateOut", input_date2.getText().toString())
////                                        .add("TimeOut", input_date4.getText().toString())
////                                        .add("Reason", Reason)
////                                        .add("CustomerInfo", Customer_Info)
////                                        .add("xCoordinates", "0.00")
////                                        .add("YCoordinates", "0.00")
////                                        .build();
////
////                                Request request = new Request.Builder()
////                                        .url(urls+"api/Manual/ManualEntryRequest?AttendanceDate=" + input_date11.getText().toString() + "&DateIn=" + input_date1.getText().toString() + "&TimeIn=" + input_date3.getText().toString() + "&DateOut=" + input_date2.getText().toString() + "&TimeOut=" + input_date4.getText().toString() + "&Reason=" + Reason
////                                                + "&CustomerInfo=" + Customer_Info + "&xCoordinates=" + "0.00" + "&YCoordinates=" + "0.00").post(formBody).addHeader("Content-Type", "application/x-www-form-urlencoded")
////                                        .addHeader("Cache-Control", "no-cache")
////                                        .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                                        .addHeader("Postman-Token", "fa210baa-8bf8-4e33-8e21-9ad0ef4a7932")
////                                        .build();
////
////                                Call call = client.newCall(request);
////                                call.enqueue(new Callback() {
////                                    @Override
////                                    public void onFailure(Call call, IOException e) {
////
////
////                                        runOnUiThread(new Runnable() {
////                                            @Override
////                                            public void run() {
////
////                                                circularProgressBar.setVisibility(View.GONE);
////                                                circularProgressBar.stop();
////                                            }
////                                        });
////
////
////                                        Log.e("HttpService", "onFailure() Request was: " + call);
////                                        e.printStackTrace();
////
////
////                                        runOnUiThread(new Runnable() {
////                                            @Override
////                                            public void run() {
////
////                                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
////                                                        .setHeaderDrawable(R.drawable.header)
////                                                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                        .withDialogAnimation(true)
////                                                        .setTitle("Error Message")
////                                                        .setDescription("Cant Connect To Server")
////                                                        .setPositiveText("OK")
////                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                            @Override
////                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
//////                                                        Intent intent = new Intent(fragment_manual_entry_form.this , LoginActivity.class);
//////                                                        startActivity(intent);
////                                                            }
////                                                        });
////                                                dialogHeader_3.show();
////                                            }
////                                        });
////
////
////
////
////                                    }
////
////                                    @Override
////                                    public void onResponse(Call call, Response response) throws IOException {
////
////                                        responses = response.body().string();
////
////                                        if (response.code() == 500) {
////
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
//////                                            Utilss.hideSweetLoader(pDialog);
////
////                                                    circularProgressBar.setVisibility(View.GONE);
////                                                    circularProgressBar.stop();
////                                                }
////                                            });
////
////                                            try {
////                                                JSONObject jsonObject = new JSONObject(responses);
////                                                value2 = jsonObject.getString("exceptionMessage");
////                                                Log.d("ValueResponse", value2);
////
////
////                                                runOnUiThread(new Runnable() {
////                                                    @Override
////                                                    public void run() {
////
////
////
////                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
////                                                                .setHeaderDrawable(R.drawable.header)
////                                                                .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                                .withDialogAnimation(true)
////                                                                .setTitle("Error Message")
////                                                                .setDescription(value2)
////                                                                .setPositiveText("OK")
////                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                                    @Override
////                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                                    input_date5.setText("");
////
////                                                                        customer_info.setText("");
////                                                                        reason.setText("");
////
////                                                                        input_date1.setText(datesssss2);
////                                                                        input_date2.setText(datesssss3);
////                                                                        input_date11.setText(datesssss);
////                                                                        input_date3.setText(datetime);
////                                                                        input_date4.setText(datetime);
////                                                                    }
////                                                                });
////                                                        dialogHeader_3.show();
////
////
////
////                         /*                       new TTFancyGifDialog.Builder(fragment_manual_entry_form.this)
////                                                        .setTitle("Error Message")
////                                                        .setMessage(value2)
////                                                        .setPositiveBtnBackground("#f4de15")
////                                                        .setPositiveBtnText("Ok")
////                                                        .setGifResource(R.drawable.g)
////                                                        .isCancellable(true)
////                                                        .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                                            @Override
////                                                            public void OnClick() {
////
////                                                                customer_info.setText("");
////                                                                reason.setText("");
////
////                                                                input_date1.setText("1-Jan-2019");
////                                                                input_date2.setText("1-Jan-2019");
////                                                                input_date11.setText("2017-06-30");
////                                                                input_date3.setText("11:50");
////                                                                input_date4.setText("11:50");
////                                                            }
////                                                        })
////                                                        .build();*/
////                                                    }
////
////                                                });
////
////
////                                            } catch (JSONException e) {
////                                                e.printStackTrace();
////                                            }
////
////
////                                        } else {
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
//////                                            Utilss.hideSweetLoader(pDialog);
////
////                                                    circularProgressBar.setVisibility(View.GONE);
////                                                    circularProgressBar.stop();
////                                                }
////                                            });
////
////
////                                            runOnUiThread(new Runnable() {
////                                                @Override
////                                                public void run() {
////
////                                                    runOnUiThread(new Runnable() {
////                                                        @Override
////                                                        public void run() {
////
////                                                            circularProgressBar.setVisibility(View.GONE);
////                                                            circularProgressBar.stop();
////                                                        }
////                                                    });
////
////
////                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
////                                                            .setHeaderDrawable(R.drawable.header)
////                                                            .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                            .withDialogAnimation(true)
////                                                            .setTitle("Confirmation Message")
////                                                            .setDescription(responses)
////                                                            .setPositiveText("OK")
////                                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                                @Override
////                                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                                    input_date5.setText("");
////
////                                                                    customer_info.setText("");
////                                                                    reason.setText("");
////
////                                                                    input_date1.setText(datesssss2);
////                                                                    input_date2.setText(datesssss3);
////                                                                    input_date11.setText(datesssss);
////                                                                    input_date3.setText(datetime);
////                                                                    input_date4.setText(datetime);
////
////
////                                                                    Intent intent = new Intent(fragment_manual_entry_form.this, Home_Screen.class);
////                                                                    fragment_manual_entry_form.this.startActivity(intent);
////                                                                }
////                                                            });
////                                                    dialogHeader_3.show();
////                                                }
////                                            });
////                                        }
////                                    }
////
////                                    ;
////                                });
////                            }
////                            else {
////
////                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                        .withDialogAnimation(true)
////                                        .setTitle("Error Message")
////                                        .setDescription("Network Required..!!")
////                                        .setPositiveText("OK")
////                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                            @Override
////                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                                    input_date5.setText("");
////                                            }
////                                        });
////                                dialogHeader_3.show();
////                            }
//                        }
//                    }
//
//                    ;
//                });
            }
        });

    }

    private void startStep3() {

        //And it will be keep running until you close the entire application from task manager.
        //This method will executed only once.
        pDialog = Utilss.showSweetLoader(fragment_manual_entry_form.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


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



    boolean insertData = false;
    public void AddData()
    {



        if(mDatabaseHelper.IsDataExist(dates_in))
        {
//            insertData = mDatabaseHelper.addData(datetime3, "", datetime2, "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");

//            insertData = mDatabaseHelper.addData("", "","",datetime3 ,datetime2,reason.getText().toString(),customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT, "false", "");
//
//            Toast.makeText(this, "already presists", Toast.LENGTH_SHORT).show();



            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Entry Already Exist..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

//                                customer_info.setText("");
//                                reason.setText("");

//                                startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));

                        }
                    });
            dialogHeader_3.show();



//            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");

//            public boolean addData(String AttendanceDate, String date_in,String time_in,                             String date_out,String time_out,String reason , String customer_info ,String xcordinate,String ycordinate ,String type,String issync,String reponse) {

//            if (insertData) {
//
//
////                Toast.makeText(getApplicationContext(), "Date Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                        .setHeaderDrawable(R.drawable.header)
//                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                        .withDialogAnimation(true)
//                        .setTitle("Confirmation Message")
//                        .setDescription("Data Inserted Successfully..!!")
//                        .setPositiveText("OK")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                customer_info.setText("");
//                                reason.setText("");
//
//
//                                startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));
//
//                            }
//                        });
//                dialogHeader_3.show();
//            }
//
//            else {
//
//                Toast.makeText(getApplicationContext(), "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//            }

        }







        else if(mDatabaseHelper.IsDataExist2(dates_in))
        {
//            insertData = mDatabaseHelper.addData(datetime3, "", datetime2, "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");

//            insertData = mDatabaseHelper.addData("", "","",datetime3 ,datetime2,reason.getText().toString(),customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT, "false", "");
//
//            Toast.makeText(this, "already presists", Toast.LENGTH_SHORT).show();



            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Eror Message")
                    .setDescription("Entry Already Exist..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            customer_info.setText("");
                            reason.setText("");

//                            startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));

                        }
                    });
            dialogHeader_3.show();



//            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");

//            public boolean addData(String AttendanceDate, String date_in,String time_in,                             String date_out,String time_out,String reason , String customer_info ,String xcordinate,String ycordinate ,String type,String issync,String reponse) {

//            if (insertData) {
//
//
////                Toast.makeText(getApplicationContext(), "Date Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                        .setHeaderDrawable(R.drawable.header)
//                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                        .withDialogAnimation(true)
//                        .setTitle("Confirmation Message")
//                        .setDescription("Data Inserted Successfully..!!")
//                        .setPositiveText("OK")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                customer_info.setText("");
//                                reason.setText("");
//
//
//                                startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));
//
//                            }
//                        });
//                dialogHeader_3.show();
//            }
//
//            else {
//
//                Toast.makeText(getApplicationContext(), "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//            }

        }







        else if(mDatabaseHelper.IsDataExistAttendance(dates_in))
        {
//            insertData = mDatabaseHelper.addData(datetime3, "", datetime2, "", reason.getText().toString(), customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN, "false", "");

//            insertData = mDatabaseHelper.addData("", "","",datetime3 ,datetime2,reason.getText().toString(),customer_info.getText().toString(), latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT, "false", "");
//
//            Toast.makeText(this, "already presists", Toast.LENGTH_SHORT).show();



            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Confirmation Message")
                    .setDescription("Entry Already Exist..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            customer_info.setText("");
                            reason.setText("");

                            startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));

                        }
                    });
            dialogHeader_3.show();



//            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");

//            public boolean addData(String AttendanceDate, String date_in,String time_in,                             String date_out,String time_out,String reason , String customer_info ,String xcordinate,String ycordinate ,String type,String issync,String reponse) {

//            if (insertData) {
//
//
////                Toast.makeText(getApplicationContext(), "Date Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                        .setHeaderDrawable(R.drawable.header)
//                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                        .withDialogAnimation(true)
//                        .setTitle("Confirmation Message")
//                        .setDescription("Data Inserted Successfully..!!")
//                        .setPositiveText("OK")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                customer_info.setText("");
//                                reason.setText("");
//
//
//                                startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));
//
//                            }
//                        });
//                dialogHeader_3.show();
//            }
//
//            else {
//
//                Toast.makeText(getApplicationContext(), "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//            }

        }






        else
        {
            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, DatabaseHelper.TYPE_MANUAl_TIME_REQUEST, "false", "");


            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Confirmation Message")
                    .setDescription("Entry Inserted Successfully..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            customer_info.setText("");
                            reason.setText("");

                            startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));

                        }
                    });
            dialogHeader_3.show();



//            Toast.makeText(this, "INSERTED..!!", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "Date Already available..!!", Toast.LENGTH_SHORT).show();

//            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Error Message")
//                    .setDescription("Data Already Exist On Same Date..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        }
//                    });
//            dialogHeader_3.show();
//
        }

    }





//        Cursor timeRequestOfflineData = mDatabaseHelper.getData();
//
//
//            if ((timeRequestOfflineData.getCount() > 0))
//            {
//                timeRequestOfflineData.moveToFirst();
//                do {
//
//
//                    String date = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL222222));// size=1 , size=0
//
//                    if (date.equals(""))
//                    {
//                        if (!mDatabaseHelper.IsDataExist(datesssss2) && !mDatabaseHelper.IsDataExist2(datesssss2))
//                        {
//                            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");
//                        }
//
//                        else
//                        {
//                            Toast.makeText(this, "Already exist..!!", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//
//                    else if (!date.equals(""))
//                    {
//                        if (!date.equals(attendence_date) &&  !mDatabaseHelper.IsDataExist(datesssss2) && !mDatabaseHelper.IsDataExist2(datesssss2))
//                        {
//                            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");
//                        }
//
//                        else
//                        {
//                            Toast.makeText(this, "Already exist..!!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//
//
//
//
//                }while (timeRequestOfflineData.moveToNext());
//
//            }else{
//                insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");
//            }
//
//
//
////        if(!mDatabaseHelper.IsDataExist("") &&!mDatabaseHelper.IsDataExist2(""))
////        {
////            insertData = mDatabaseHelper.addData(attendence_date,dates_in,times_in,dats_out,times_out,reasons,customer_infos, latitude, longitude, com.example.tisapp.DatabaseHelper.TYPE_MANUAL_ENTRY, "false", "");
//
//            if (insertData) {
//
//
//                Toast.makeText(getApplicationContext(), "Data Inserted Successfully..!!", Toast.LENGTH_SHORT).show();
//
//                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                        .setHeaderDrawable(R.drawable.header)
//                        .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                        .withDialogAnimation(true)
//                        .setTitle("Confirmation Message")
//                        .setDescription("Data Inserted Successfully..!!")
//                        .setPositiveText("OK")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                customer_info.setText("");
//                                reason.setText("");
//
//
//                                startActivity(new Intent(fragment_manual_entry_form.this, Home_Screen.class));
//
//                            }
//                        });
//                dialogHeader_3.show();
//            }







//            else {
//
//                Toast.makeText(fragment_manual_entry_form.this, "\"Something went wrong..!!", Toast.LENGTH_SHORT).show();
//            }

//        }


//        else
//        {
////            Toast.makeText(context, "Date Already available..!!", Toast.LENGTH_SHORT).show();
//
//
//            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_entry_form.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(fragment_manual_entry_form.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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
    //  }

















//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.dialog_apply_manual_request, container, false);
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
//        return  view;
//    }

//    public  boolean isNetAvailable()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null && networkInfo.isConnected();
//    }


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mGoogleMap = googleMap;
//
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Objects.requireNonNull(fragment_manual_entry_form.this), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            return;
//        }
//
//        else
//        {
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
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });
//
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
//                circleOptions.center(new LatLng(location.getLatitude(),location.getLongitude()));
//                circleOptions.radius(500);
//                circleOptions.fillColor(Color.TRANSPARENT);
//                circleOptions.strokeWidth(3);
//
//                mGoogleMap.addCircle(circleOptions);
//
//                lat = location.getLatitude();
//                long1 = location.getLongitude();
//
//                Log.d("LatIs11" , ""+lat);
//                Log.d("LongIs22" , ""+long1);
//
//
////                mp.title("my position");
//
////                googleMap.addMarker(mp);
//                mGoogleMap.moveCamera(center);
//                mGoogleMap.animateCamera(zoom);
//
//            }
//        });}


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        stopService(new Intent(this, LocationMonitoringService.class));
        mAlreadyStartedService = false;
    }

}



//}
