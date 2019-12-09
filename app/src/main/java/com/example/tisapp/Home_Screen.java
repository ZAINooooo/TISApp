package com.example.tisapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
//import com.example.zain.project.GPSLocation.GpsTracker;
//import com.example.zain.project.Login.LoginActivity;
//import com.example.zain.project.Profile.Profile;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.Profile.Profile;
import com.example.tisapp.Receivers.NetworkChangeReceiver;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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

import static com.example.tisapp.Login.LoginActivity.IMEI;
import static com.example.tisapp.Login.LoginActivity.passwordsave;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
//
//import static com.example.zain.project.Settings.Settings_Activity.urls;
//import static com.example.zain.project.Settings.Settings_Activity.urls2;


//public class Home_Screen extends BaseActivity implements DrawerLayout.DrawerListener
public class Home_Screen extends BaseActivity   {

    private NetworkChangeReceiver networkChangeReceiver;

    LinearLayout ivMainCheckin;
    private Handler mHandler;

    DatabaseHelper objDBHelper2;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    String datetime;
    TextView txtName, designation, tvTodayTime, tvTodayDate, tvTodayTimeAMPM;
    public static int navItemIndex = 0;

    String value5;
    Uri tempUri;


    String mCurrentPhotoPath;
    //    private GpsTracker gpsTracker;
//    public static double latitude;
//    public static double longitude;
    ImageView img_logout;


    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    JSONObject jsonObject;

    private static final int CAMERA_REQUEST = 1888;
    //    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    Fragment frag = null;
    Bundle bundle;

    private Fragment fragment;


    File finalFile;

    TextView tvUserNameProfile, tvDesigProfile, tvTodayCheckInTime, tvTodayCheckOutTime, tvDepartment, tvUserEmailProfile, tvUserSkypePr, tvUserContactProofilefile, tvUserNameHeader;

    String responses, value2, value3;
    private Toolbar toolbar;
    MaterialStyledDialog.Builder dialogHeader_3;

    OkHttpClient client;
    String weekday_name2, weekday_name;
    String value6;

    Fragment finalFrag;


    CardView lay_add_person, nav_about_us, manual_entry, leave_module, report_module;
    boolean status = false;
    public static TextView tvToolbarTitle;
    String yesterdayAsString;
    String value8;

    CircularImageView ivUserProfile, ivUserProfiles;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_GALLAREY = 0;

    File imgFile;
    Uri selectedImage;
    public String mImageUri, mImageUri2;

    String tag = "image",value89,value345;

    boolean request_result,request_resultss;



    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private boolean mAlreadyStartedService = false;
    private TextView mMsgView;
    public ArrayList<SpinnerData> lstAnime = new ArrayList<>();
//    android.os.Handler customHandler;

    String latitude,longitude;



    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

        objDBHelper2=new DatabaseHelper(this);
        lstAnime = new ArrayList<>();

//        customHandler = new android.os.Handler();
//        customHandler.postDelayed(updateTimerThread, 3000);


        img_logout = (ImageView) toolbar.findViewById(R.id.img_logout);






        mHandler = new Handler();



        if (checkPermissions())
        {

            if (isGooglePlayServicesAvailable()) {


//                pDialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


                //Passing null to indicate that it is executing for the first time.
//                startStep3();

            } else {
                Toast.makeText(getApplicationContext(), "No Google Play Service Installed", Toast.LENGTH_LONG).show();
            }

        }
        else
        {  //No user has not granted the permissions yet. Request now.
            requestPermissions();
        }



//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        tvToolbarTitle = toolbar.findViewById(R.id.tv_toolbar_title);


//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }


        tvUserNameProfile = (TextView) findViewById(R.id.tvUserNameProfile);
        tvDesigProfile = (TextView) findViewById(R.id.tvDesigProfile);



        ivUserProfile = (CircularImageView) findViewById(R.id.ivUserProfile);



//        tvTodayDate = (TextView) findViewById(R.id.tvTodayDate);
//        String datesssss = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
//        Log.d("Date_is", datesssss);
//        tvTodayDate.setText(datesssss);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mImageUri = preferences.getString("image", null);

//        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
//        mImageUri2 = preferences2.getString("image_capture", null);




        if (mImageUri != null && tag.equals("image")) {
//            ivUserProfile.setImageResource(R.mipmap.image_face);

//            Glide.with(Home_Screen.this).load(R.mipmap.image_face).into(ivUserProfile);

            Glide.with(Home_Screen.this).load(mImageUri).asBitmap().override(200, 200).into(ivUserProfile);
        } else if (mImageUri == null && tag.equals("image")) {
            Glide.with(Home_Screen.this).load(R.mipmap.image_face).into(ivUserProfile);
        }


        ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.custom_dialog, null);
                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();

                AlertDialog.Builder aDialog = new AlertDialog.Builder(Home_Screen.this);

                Button imageSelector = layout.findViewById(R.id.gallery);
                Button camera = layout.findViewById(R.id.camera);
                final AlertDialog dialog = aDialog.create();

                aDialog.setView(layout);
                final AlertDialog ad = aDialog.create();
                ad.show();


                imageSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        galleryIntent();
                        ad.dismiss();
                    }
                });


                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dispatchTakePictureIntent();
                        ad.dismiss();
                    }
                });
            }
        });


        tvDepartment = (TextView) findViewById(R.id.department);

        lay_add_person = (CardView) findViewById(R.id.lay_add_person);
        nav_about_us = (CardView) findViewById(R.id.nav_about_us);
        manual_entry = (CardView) findViewById(R.id.manual_entry);
        leave_module = (CardView) findViewById(R.id.leave_module);
        report_module = (CardView) findViewById(R.id.reports_module);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                        .setHeaderDrawable(R.drawable.header)
                        .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                        .withDialogAnimation(true)
                        .setTitle("LogOut?")
                        .setDescription("Would You Like To Be Logged Out ?")
                        .setPositiveText("YES")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                if (urls == null || urls.equals("")) {
                                    dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
                                            .setHeaderDrawable(R.drawable.header)
                                            .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                            .withDialogAnimation(true)
                                            .setTitle("Error Message")
                                            .setDescription("Url Can't Be Empty")
                                            .setPositiveText("OK")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                                    startActivity(new Intent(Home_Screen.this, LoginActivity.class));
                                                    finish();
//
//
////                            _passwordText.setText("");
////                                        _loginButton.setEnabled(true);
                                                }
                                            });
                                    dialogHeader_3.show();

                                }


                                else
                                {

                                    pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");



//                                                                      final Request request = new Request.Builder()
//                                                                              .url(urls + "api/Account/Logout")
//                                                                              .get().addHeader("Cache-Control", "no-cache")
//                                                                              .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                                                                              .addHeader("Postman-Token", "3a80b1ef-cb95-4422-abd6-a9fc593213f2")
//                                                                              .build();
//
//                                                                      Call call = client.newCall(request);
//                                                                      call.enqueue(new Callback() {
//                                                                          @Override
//                                                                          public void onFailure(Call call, IOException e) {
//                                                                              runOnUiThread(new Runnable() {
//                                                                                  @Override
//                                                                                  public void run() {
//                                                                                      Utilss.hideSweetLoader(pDialog);
//                                                                                  }
//                                                                              });
//
//                                                                              runOnUiThread(new Runnable() {
//                                                                                  @Override
//                                                                                  public void run() {
//
//                                                                                      dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
//                                                                                              .setHeaderDrawable(R.drawable.header)
//                                                                                              .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                                              .withDialogAnimation(true)
//                                                                                              .setTitle("Error Message")
//                                                                                              .setDescription("Cant Connect To Server")
//                                                                                              .setPositiveText("OK")
//                                                                                              .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                                                  @Override
//                                                                                                  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                                                                                  Intent intent = new Intent(Home_Screen.this , LoginActivity.class);
//////                                                                                                  startActivity(intent);
//                                                                                                  }
//                                                                                              });
//                                                                                      dialogHeader_3.show();
//                                                                                  }
//                                                                              });
//
//
//                                                                          }
//
//
//                                                                          @Override
//                                                                          public void onResponse(Call call, Response response) throws IOException {
//                                                                              runOnUiThread(new Runnable() {
//                                                                                  @Override
//                                                                                  public void run() {
//                                                                                      Utilss.hideSweetLoader(pDialog);
//                                                                                  }
//                                                                              });
//
//                                                                              responses = response.toString();
//                                                                              Log.e("responseLogOut", responses);
//
//                                                                              if (response.code() == 500) {
//                                                                                  runOnUiThread(new Runnable() {
//                                                                                      @Override
//                                                                                      public void run() {
//                                                                                          Utilss.hideSweetLoader(pDialog);
//                                                                                      }
//                                                                                  });
//
//
//                                                                                  runOnUiThread(new Runnable() {
//                                                                                      @Override
//                                                                                      public void run() {
//
//
//                                                                                          dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                                                                                  .setHeaderDrawable(R.drawable.header)
//                                                                                                  .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                                                                  .withDialogAnimation(true)
//                                                                                                  .setTitle("Error Message")
//                                                                                                  .setDescription("Error In LogOut..")
//                                                                                                  .setPositiveText("OK")
//                                                                                                  .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                                                                      @Override
//                                                                                                      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                                                                          startActivity(new Intent(Home_Screen.this, LoginActivity.class));
//                                                                                                      }
//                                                                                                  });
//                                                                                          dialogHeader_3.show();
//
//                                                                                      }
//                                                                                  });
//                                                                              } else if (response.code() == 200) {
//
//
//                                                                                  runOnUiThread(new Runnable() {
//                                                                                      @Override
//                                                                                      public void run() {
//                                                                                          Utilss.hideSweetLoader(pDialog);
//
//
//                                                                                      }
//                                                                                  });
//
//
//                                                                                  runOnUiThread(new Runnable() {
//
//                                                                                      @Override
//                                                                                      public void run() {
//
//
//                                                                                          Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
//                                                                                          startActivity(intent);
//
////                                                                                          dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
////                                                                                                  .setHeaderDrawable(R.drawable.header)
////                                                                                                  .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                                                                  .withDialogAnimation(true)
////                                                                                                  .setTitle("Confirmation Message")
////                                                                                                  .setDescription(responses)
////                                                                                                  .setPositiveText("OK")
////                                                                                                  .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                                                                      @Override
////                                                                                                      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////
////
////                                                                                                      }
////                                                                                                  });
//                                                                                      }
//                                                                                  });
//                                                                              }
//                                                                          }
//                                                                      });
//                                                                  }
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
//                                                                  else
//                                                                  {
//
//                                                                      runOnUiThread(new Runnable() {
//                                                                          @Override
//                                                                          public void run() {
//
//
////                                                                                      Intent intents = new Intent("android.location.GPS_ENABLED_CHANGE");
////                                                                                      intents.putExtra("enabled" , false);
////                                                                                      sendBroadcast(intents);
//
////
////                                                                              Cursor timeInRequestOfflineData = objDBHelper2.getToken();
////                                                                              Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineData.getColumnCount());
////
////
////                                                                              if (timeInRequestOfflineData.moveToLast()) {
////                                                                                  String COLTOKEN = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COLTOKEN));
////                                                                                  Log.d("Token_Is", COLTOKEN);
////
////                                                                                  String token_value = String.valueOf(objDBHelper2.DeleteToken(COLTOKEN));
////                                                                                  Toast.makeText(context, "Deleted" + token_value, Toast.LENGTH_SHORT).show();
////
////                                                                                  Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
////                                                                                  startActivity(intent);
////
////                                                                                  timeInRequestOfflineData.close();
////
////                                                                              }
//
//
//                                                                          }
//                                                                      });



                                                                  unregisterNetworkChanges();

                                    Intent intent = new Intent(Home_Screen.this , LoginActivity.class);
                                    startActivity(intent);
                                }





                            }

                        }) .setNegativeText("Not now");;



                dialogHeader_3.show();
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
            }

        });










        lay_add_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Home_Screen.this, Profile.class);

                //For Image Gallery
                if (selectedImage != null && mImageUri != null && mImageUri2 == null && finalFile == null)

                {
                    intent.putExtra("mImageUri", selectedImage.toString());
                } else if (finalFile != null && selectedImage != null && mImageUri != null && mImageUri2 == null)

                {
                    intent.putExtra("mImageUriCamera", finalFile.toString());
                } else if (selectedImage != null && mImageUri == null)

                {
                    intent.putExtra("mImageUri", selectedImage.toString());
                } else if (selectedImage != null && mImageUri != null && mImageUri2 == null)

                {
                    intent.putExtra("mImageUri", selectedImage.toString());
                } else if (selectedImage == null && mImageUri != null && finalFile == null && mImageUri2 == null)

                {
                    intent.putExtra("mImageUri", mImageUri);
                } else if (finalFile != null && mImageUri2 == null) {
                    intent.putExtra("mImageUriCamera", finalFile.toString());
                }

                //For Image Capture


//                intent.putExtra("mImageUriCamera" , imgFile.toString());

                startActivity(intent);
            }
        });


        nav_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert();
            }
        });


        manual_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                getGpsCoordinates();

//                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    getGpsCoordinates();


                Intent intent = new Intent(Home_Screen.this, ManualEntryModule.class);
                startActivity(intent);

                if (isNetAvailable()) {


//                    } else {
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
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                    }
//                                });
//                        dialogHeader_3.show();

                }
            }
        });



        report_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home_Screen.this, ReportsModule.class);
                startActivity(intent);
            }
        });


        leave_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                getGpsCoordinates();


                Intent intent = new Intent(Home_Screen.this, LeaveModule.class);
                startActivity(intent);

//                    if (isNetAvailable()) {
//
////                                                                startActivity(new Intent(Home_Screen.this, ManualEntryModule.class));
//                        Intent intent = new Intent(Home_Screen.this, LeaveModule.class);
//                        startActivity(intent);
//                    } else {
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
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                    }
//                                });
//                        dialogHeader_3.show();
//
//
//                    }

//                    intent.putExtra("Service_Url", service_url);


            }
        });


        String weekday_names = new SimpleDateFormat("EEEE, dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
//        tvTodayDate.setText(yesterdayAsString);
        Log.d("Date_Full2", weekday_names);
//        tvTodayDate.setText(weekday_names);


        weekday_name = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_Full3", weekday_name);


        String sDate = weekday_name;

//         weekday_name = new SimpleDateFormat("EEEE, dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        Date date;
        try {
            date = dateFormat.parse(sDate);

            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            yesterdayAsString = dateFormat.format(calendar.getTime());

//            tvTodayDate.setText(yesterdayAsString);
            Log.d("Date_Full", yesterdayAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        weekday_name2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
//        tvTodayDate.setText(weekday_name2);

        Log.d("Today_Time", weekday_name2);


        if (isNetAvailable()) {
            GetProfileParams();
            retrieveJSON();

        } else {
//            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Error Message")
//                    .setDescription("Network Required..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        }
//                    });
//            dialogHeader_3.show();

            Cursor timeInRequestOfflineData = objDBHelper2.getDataProfile();


//            String data= objDBHelper2.getDataProfile();
//            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();


            if (timeInRequestOfflineData.moveToLast())
            {

//                String COL13 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL13));

                String COL14 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL14));
                String COL15 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL15));
                String COL16 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL16));
                String COL17 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(DatabaseHelper.COL17));


//                tvUserNameProfile.setText(COL14);
//                tvDesigProfile.setText(COL15);
//                tvUserEmailProfile.setText(COL14);
//                tvUserSkypePr.setText(COL16);
//                tvUserContactProofilefile.setText(COL13);
//                tvUsernameProfile.setText(COL15);


                tvUserNameProfile.setText(COL14);
                tvDesigProfile.setText(COL16);
                tvDepartment.setText(COL15);

                Log.d("IsApprover" , ""+COL17);


                timeInRequestOfflineData.close();

            }







        }


//        getcheck_in_time();

    }












   // }




















    //}





    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }



    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(networkChangeReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void retrieveJSON()
    {
        request = new JsonArrayRequest(urls + "api/Leave/GetEntitledLeaveCodes", new com.android.volley.Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {

                for (int i = 0; i < response.length(); i++)
                {

                    try {
                        jsonObject = response.getJSONObject(i);

                        String levName=jsonObject.getString("leaveName");
                        String levCode=jsonObject.getString("leaveCode");

                        Log.d("LeaveNamee" , levName);
                        Log.d("LeaveCode2" , levCode);

                        lstAnime.add(new SpinnerData(levCode,levName));


                        ArrayList<SpinnerData> timeRequestOfflineData = objDBHelper2.getSpinnerData();

                        if (timeRequestOfflineData.isEmpty())
                        {

                            objDBHelper2.insertleaveentitled(lstAnime);
                            Log.d("InsertedInLeave" , "NAME" + levCode    + "CODE" + levName);
//                            Toast.makeText(context, "No offline data available", Toast.LENGTH_SHORT).show();
//                            layNoInternet.setVisibility(View.VISIBLE);
                        }

                        else
                        {
//                            db.DeleteLeavesTable(levName , levCode);

                            objDBHelper2.delAllRecord();
                            objDBHelper2.insertleaveentitled(lstAnime);
//                            Log.d("InsertedInLeave" , "NAME" + levCode    + "CODE" + levName);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    adapter = new fragment_leave_form.CustomAdapter(getApplicationContext(), R.layout.spinner_rows,lstAnime );
//                    level_code.setAdapter(adapter);
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error12", error.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Error Message")
                                .setDescription("Cant Connect To Server")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                                        Intent intent = new Intent(fragment_leave_form.this , LoginActivity.class);
//                                        startActivity(intent);
                                    }
                                });
                        dialogHeader_3.show();
                    }
                });


            }
        })

        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", " Bearer " + LoginActivity.value);
                params.put("Cache-Control", "no-cache");
                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
                Log.d("AuthKey", LoginActivity.value);

                return params;
            }
        };


        int socketTimeout = 3000;//3 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);

        requestQueue = Volley.newRequestQueue(Home_Screen.this);
        requestQueue.add(request);

    }















    private void requestPermissions() {

        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        boolean shouldProvideRationale2 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);


        // Provide an additional rationale to the img_user. This would happen if the img_user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale || shouldProvideRationale2) {
            Log.i("\"Permission\"", "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request permission
                    ActivityCompat.requestPermissions(Home_Screen.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_PERMISSIONS_REQUEST_CODE);
                }
            });
        } else {
            Log.i("Permission", "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the img_user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(Home_Screen.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }







    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }





    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }



    private boolean checkPermissions() {
        int permissionState1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionState2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState1 == PackageManager.PERMISSION_GRANTED && permissionState2 == PackageManager.PERMISSION_GRANTED;

    }


    public void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//
//                Log.d("File_Path" , ""+photoFile);
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                System.out.println("File Couldn't be able to create: " + ex.getLocalizedMessage());
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(Home_Screen.this, "com.example.zain.project.fileprovider", photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }

        if (ActivityCompat.checkSelfPermission(Home_Screen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Home_Screen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Home_Screen.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 22);
        } else {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Home_Screen.this.startActivityForResult(camera, REQUEST_TAKE_PHOTO);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }



        else  if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If img_user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i("interaction", "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Log.i("Permission granted", "Permission granted, updates requested, starting location updates");
//                startStep3();

            } else {
                // Permission denied.


                showSnackbar(R.string.permission_denied_explanation,
                        R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }


    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
//        {
//
//            Toast.makeText(this, "Clicked.!!", Toast.LENGTH_SHORT).show();
//
//
////            Bitmap photo = (Bitmap) data.getExtras().get("data");
////            imageView.setImageBitmap(photo);
//        }
//    }


    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void galleryIntent() {
        Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
    }


//    private void getlasttimeout() {
//
//        if (urls == null || urls.equals("")) {
//            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Error Message")
//                    .setDescription("Url Can't Be Empty")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                            startActivity(new Intent(Home_Screen.this, LoginActivity.class));
//                            finish();
//
//
////                            _passwordText.setText("");
////                                        _loginButton.setEnabled(true);
//                        }
//                    });
//            dialogHeader_3.show();
//
//        } else {
//            final Request request = new Request.Builder()
//                    .url(urls + "api/Attendance/GetDailyAttendance?AttendanceDate=" + yesterdayAsString)
//                    .get().addHeader("Cache-Control", "no-cache")
//                    .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                    .addHeader("Postman-Token", "5ea4af10-9c0e-4a5c-ba17-b4e92dea176e")
//                    .build();
//
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                    Log.e("HttpService", "onFailure() Request was: " + call);
//                    e.printStackTrace();
//
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Cant Connect To Server")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                            Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    });
//                            dialogHeader_3.show();
//                        }
//                    });
//
//
//                }
//
//                @SuppressLint("LongLogTag")
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                    String responses = response.body().string();
//                    Log.e("response", "onResponse(): " + responses);
//
//                    if (response.code() == 500) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(responses);
//                            value5 = jsonObject.getString("exceptionMessage");
//                            Log.d("Exception_Message_Time_Out", value5);
//
//                            runOnUiThread(new Runnable() {
//
//
//                                @Override
//                                public void run() {
//
//
////                                    dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
////                                            .setHeaderDrawable(R.drawable.header)
////                                            .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                            .withDialogAnimation(true)
////                                            .setTitle("Error Message")
////                                            .setDescription(value3)
////                                            .setPositiveText("OK")
////                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                @Override
////                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                }
////                                            });
////                                    dialogHeader_3.show();
//                                }
//                            });
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (response.code() == 200) {
//
//                        try {
//
//                            Log.d("ResponseISthis", responses);
//                            jsonObject = new JSONObject(responses);
////                            value6 = jsonObject.getString("timeIn");
//                            value8 = jsonObject.getString("timeOut");
//
//                            Log.d("TimeOits", value8);
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    tvTodayCheckOutTime.setText(value8);
//                                }
//                            });
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
////                                        shadow.setText("");
//                                }
//                            });
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//
//        }
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // register connection status listener
//        MyApplication.getInstance().setConnectivityListener(this);
//    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
//    @Override
//    public void onNetworkConnectionChanged(boolean isConnected) {
//        showSnack(isConnected);
//    }





    @SuppressLint({"StaticFieldLeak", "CommitPrefEdits", "ApplySharedPref"})
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {

//        For Camera option
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
//                Log.i("onActivityResult", "in camera activity");
//                final Bitmap[] myBitmap = new Bitmap[1];
//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... voids) {
//
//                         imgFile = new File(mCurrentPhotoPath);
//
//                        Log.d("ImageCapture" , ""+imgFile);


                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ivUserProfile.setImageBitmap(photo);


                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), photo);


                Log.d("Capture_Uri", "" + tempUri);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                finalFile = new File(getRealPathFromURI(tempUri));
                Log.d("FilePath", "" + finalFile);


                // Saves image URI as string to Default Shared Preferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home_Screen.this);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("image", finalFile.toString());
                editor.commit();
            }
        }

        Log.i("selector", "before result");


        if (requestCode == REQUEST_IMAGE_GALLAREY) {
            if (resultCode == RESULT_OK) {
                Log.i("selector", "in result");
                Log.i("onActivityResult", "in gallery activity");
                selectedImage = data.getData();

                Log.d("SelectedPath", selectedImage.toString());
                try {

                    if (selectedImage != null) {
                        this.getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }

                    Bitmap myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    tempUri = selectedImage;

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("image", String.valueOf(selectedImage));
                    editor.commit();


                    Log.d("Selected_Image", "" + tempUri);
                    ivUserProfile.setImageBitmap(myBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

//    private void getGpsCoordinates()
//    {
//        gpsTracker = new GpsTracker(Home_Screen.this);
//        if (gpsTracker.canGetLocation()) {
//            latitude = gpsTracker.getLatitude();
//            longitude = gpsTracker.getLongitude();
//
//            Log.d("Latidue1", String.valueOf(latitude));
//            Log.d("Longitude2", String.valueOf(longitude));
//
//            SharedPreferences.Editor editor = getSharedPreferences("Data", Context.MODE_PRIVATE).edit();
//            editor.putString("Longitude", String.valueOf(latitude));
//            editor.putString("Latitude", String.valueOf(longitude));
//            editor.commit();
//
//
//        } else {
//            gpsTracker.showSettingsAlert();
//        }
//    }

//    private void showGPSDisabledAlertToUser()
//    {
//        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                .setHeaderDrawable(R.drawable.header)
//                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                .withDialogAnimation(true)
//                .setTitle("Error Message")
//                .setDescription("GPS/Internet is required..!!")
//                .setPositiveText("OK")
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                      Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                      startActivity(callGPSSettingIntent);
//                    }
//                });
//        dialogHeader_3.show();
//    }




    private void GetProfileParams() {

        pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");



        if (urls==null || urls.equals(""))
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utilss.hideSweetLoader(pDialog);
                }

            });



            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Url Can't Be Empty")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            startActivity(new Intent(Home_Screen.this, LoginActivity.class));
                            finish();


//                            _passwordText.setText("");
//                                        _loginButton.setEnabled(true);
                        }
                    });
            dialogHeader_3.show();

        }





        else
        {



            Request request = new Request.Builder()
                    .url(urls + "api/Employee/GetEmployeeInfo")
                    .get().addHeader("Cache-Control", "no-cache")
                    .addHeader("Authorization", " Bearer " + LoginActivity.value)
                    .addHeader("Postman-Token", "4c066075-1378-4e06-9ee9-98fd460924f9")
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


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("Cant Connect To Server")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

//                                        Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
//                                        startActivity(intent);

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


                    if (response.code() == 500) {
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

                                        Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }


                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                                                .setHeaderDrawable(R.drawable.header)
                                                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                .withDialogAnimation(true)
                                                .setTitle("Error Message")
                                                .setDescription(value2)
                                                .setPositiveText("OK")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                                    }
                                                });
                                        dialogHeader_3.show();
                                    }
                                });
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {


                        try {
                            JSONObject jsonObject = new JSONObject(responses);
                            final String valu2 = jsonObject.getString("employeeName");
                            final String value3 = jsonObject.getString("department");
                            final String value4 = jsonObject.getString("designation");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    tvUserNameProfile.setText(valu2);
                                    tvDesigProfile.setText(value4);
                                    tvDepartment.setText(value3);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }




    private void alert()
    {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.activity_about, (ViewGroup) findViewById(R.id.about));

        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
        Button btnOk = (Button) layout.findViewById(R.id.btnabout);


        AlertDialog.Builder aDialog = new AlertDialog.Builder(Home_Screen.this);
        aDialog.setView(layout);
        final AlertDialog ad = aDialog.create();
        ad.show();


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.cancel();
            }
        });
    }


    private void alert2() {
        dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                .setHeaderDrawable(R.drawable.header)
                .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                .withDialogAnimation(true)
                .setTitle("LogOut?")
                .setDescription("Would You Like To Be Logged Out ?")
                .setPositiveText("YES")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (isNetAvailable()) {
                            pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                            final Request request = new Request.Builder()
                                    .url("http://192.168.5.48/tisapi/api/Account/Logout")
                                    .get().addHeader("Cache-Control", "no-cache")
                                    .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                    .addHeader("Postman-Token", "3a80b1ef-cb95-4422-abd6-a9fc593213f2")
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


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
                                                    .setHeaderDrawable(R.drawable.header)
                                                    .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                    .withDialogAnimation(true)
                                                    .setTitle("Error Message")
                                                    .setDescription("Cant Connect To Server")
                                                    .setPositiveText("OK")
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
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

                                    responses = response.toString();
                                    Log.e("responseLogOut", responses);

                                    if (response.code() == 500) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                dialogHeader_3 = new MaterialStyledDialog.Builder(context)
                                                        .setHeaderDrawable(R.drawable.header)
                                                        .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                        .withDialogAnimation(true)
                                                        .setTitle("Error Message")
                                                        .setDescription("Error In LogOut..")
                                                        .setPositiveText("OK")
                                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");
                                                            }
                                                        });
                                                dialogHeader_3.show();
                                            }


                                        });
                                    } else {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(Home_Screen.this, LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                            });
                        }

                        else
                        {
//
//                            Cursor timeInRequestOfflineData = objDBHelper2.getToken();
//                            Log.e("timeInRequestOffline", "COUNT >>>>> " + timeInRequestOfflineData.getColumnCount());
//
//
//                            if (timeInRequestOfflineData.moveToLast())
//                            {
//                                String COLTOKEN = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COLTOKEN));
//                                Log.d("Token_Is" ,COLTOKEN);
//
//                                String token_value = String.valueOf(objDBHelper2.DeleteToken(COLTOKEN));
//                                Toast.makeText(context, "Deleted" + token_value, Toast.LENGTH_SHORT).show();
//
//                                timeInRequestOfflineData.close();
//
//                            }







//                            dialogHeader_3 = new MaterialStyledDialog.Builder(context)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(context).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Network Required..!!")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    input_date5.setText("");
//                                        }
//                                    });
//                            dialogHeader_3.show();
                        }
                    }




                } ).setNegativeText("Not now");
    }

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }



    @Override
    public void onBackPressed() {
        backButtonHandler();
    }

    public void backButtonHandler() {

        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Home_Screen.this);
        builder.setTitle("Warning Message");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
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
//}
