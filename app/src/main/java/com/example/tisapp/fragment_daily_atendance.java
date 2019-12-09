package com.example.tisapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.dd.ShadowLayout;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tisapp.Settings.Settings_Activity.urls;

public class fragment_daily_atendance extends AppCompatActivity {


    public static EditText shadow;
    String value3, dateis, valu1, valu2, valu13, valu4, value5, value6, value7, value8, value9, value10, value11, value2;
    OkHttpClient client;
    MaterialStyledDialog.Builder dialogHeader_3;
ImageView iv_back;
    protected SweetAlertDialog pDialog;

//    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;

    LinearLayout daily_attendance, layNoInternet;
    JSONObject jsonObject;
    TextView time_in_is, time_out_is, date_in_is, date_out_is, work_hours, status_is, remarks, shift_timing;
    Button singupbtn, singupbtn2;

    Button manual_enter;


//    public fragment_daily_atendance() {
//        // Required empty public constructor
//    }
//
//    public static fragment_daily_atendance newInstance(String param1, String param2) {
//        fragment_daily_atendance fragment = new fragment_daily_atendance();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_fragment_daily_atendance);


        client = new OkHttpClient();
        shadow = findViewById(R.id.input_date);
        shadow.setInputType(InputType.TYPE_NULL);


//        manual_enter = (Button) view.findViewById(R.id.manual_enter);
//        manual_enter.setVisibility(View.VISIBLE);



        iv_back =(ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



        if (isNetAvailable())
        {
//            GetProfileParams();
        }
        else
            {
            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Internet Required..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                            Intent intent = new Intent(fragment_daily_atendance.this, LoginActivity.class);
//                            startActivity(intent);
                        }
                    });
            dialogHeader_3.show();
        }


//        TextView time_in_is,time_out_is,date_in_is,date_out_is,work_hours_status_is,remarks,shift_timing;
//

//        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
//        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
//        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;


        daily_attendance = (LinearLayout) findViewById(R.id.daily_attendance);
        layNoInternet = (LinearLayout) findViewById(R.id.layNoInternet);


        time_in_is = findViewById(R.id.time_in_is);
        time_out_is = findViewById(R.id.time_out_is);
        date_in_is = findViewById(R.id.date_in_is);
        date_out_is = findViewById(R.id.date_out_is);
        work_hours = findViewById(R.id.work_hours);
        status_is = findViewById(R.id.status_is);
        remarks = findViewById(R.id.remarks);
        shift_timing = findViewById(R.id.shift_timing);
        singupbtn = findViewById(R.id.singupbtn);

        singupbtn2 = findViewById(R.id.singupbtn2);
        singupbtn2.setVisibility(View.GONE);


        shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment5();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pDialog = Utilss.showSweetLoader(fragment_daily_atendance.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

//                Intent intent = new Intent(fragment_daily_atendance.this , ManualEntryModule.class);
//                startActivity(intent);


                if (shadow.getText().toString().equals("")) {
//                    shadow.setError("Date Can't Be Empty..!!");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Select Date Please")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

                                    shadow.setText("");
                                }
                            });
                    dialogHeader_3.show();
                } else {


                    new LoginActivity();
                    new selected_date_fragment5();
                    dateis = selected_date_fragment5.date_conversion;
                    Log.d("Staticis", dateis);

                    final Request request = new Request.Builder()
                            .url(urls + "api/Attendance/GetDailyAttendance?AttendanceDate=" + dateis)
                            .get().addHeader("Cache-Control", "no-cache")
                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
                            .addHeader("Postman-Token", "5ea4af10-9c0e-4a5c-ba17-b4e92dea176e")
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

                            Log.e("HttpService", "onFailure() Request was: " + call);
                            e.printStackTrace();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
                                            .setHeaderDrawable(R.drawable.header)
                                            .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                            .withDialogAnimation(true)
                                            .setTitle("Error Message")
                                            .setDescription("Cant Connect To Server")
                                            .setPositiveText("OK")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                                                    Intent intent = new Intent(fragment_daily_atendance.this , LoginActivity.class);
//                                                    startActivity(intent);
                                                }
                                            });
                                    dialogHeader_3.show();
                                }
                            });


                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String responses = response.body().string();
                            Log.e("response", "onResponse(): " + responses);

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


                                        daily_attendance.setVisibility(View.GONE);
                                        layNoInternet.setVisibility(View.VISIBLE);

                                    }
                                });

                                try {
                                    JSONObject jsonObject = new JSONObject(responses);
                                    value3 = jsonObject.getString("exceptionMessage");
                                    Log.d("Exception_Message", value3);

                                    runOnUiThread(new Runnable() {


                                        @Override
                                        public void run() {


                                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
                                                    .setHeaderDrawable(R.drawable.header)
                                                    .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                    .withDialogAnimation(true)
                                                    .setTitle("Error Message")
                                                    .setDescription(value3)
                                                    .setPositiveText("OK")
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");

                                                            shadow.setText("");
                                                        }
                                                    });
                                            dialogHeader_3.show();


                                        }
                                    });


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (response.code() == 200) {


                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        daily_attendance.setVisibility(View.VISIBLE);
                                        layNoInternet.setVisibility(View.GONE);
                                    }
                                });


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

//                            responses = response.body().string();
//                            Log.e("response", "onResponse(): " + responses);

//
                                try {

                                    Log.d("ResponseISthis", responses);
                                    jsonObject = new JSONObject(responses);
//                             String value = jsonObject.getString("employeeNo");

                                    valu1 = jsonObject.getString("employeeNo");
                                    valu2 = jsonObject.getString("employeeName");
                                    valu13 = jsonObject.getString("workHours");

                                    Log.d("Value_13", valu13);

                                    valu4 = jsonObject.getString("attendanceDate");

                                    Log.d("Value_4", valu4);
                                    value5 = jsonObject.getString("dateIn");
                                    value6 = jsonObject.getString("timeIn");

                                    value7 = jsonObject.getString("dateOut");
                                    value8 = jsonObject.getString("timeOut");
                                    value9 = jsonObject.getString("shift");
                                    value10 = jsonObject.getString("shiftTiming");
                                    value11 = jsonObject.getString("remarks");


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {


//                                            final String attendance = getIntent().getStringExtra("attendanceDate");
//                                            Log.d("Attenance_Date" , attendance);

                                            try {
                                                if (jsonObject.getString("timeIn").equals("")) {
                                                    time_in_is.setText("N/A");
                                                } else {
                                                    time_in_is.setText(value6);

                                                }


                                                if (jsonObject.getString("timeOut").equals("")) {
                                                    time_out_is.setText("N/A");
                                                } else {
                                                    time_out_is.setText(value8);

                                                }


                                                if (jsonObject.getString("dateIn").equals("")) {
                                                    date_in_is.setText("N/A");
                                                } else {
                                                    date_in_is.setText(value5);

                                                }


                                                if (jsonObject.getString("dateOut").equals("")) {
                                                    date_out_is.setText("N/A");
                                                } else {
                                                    date_out_is.setText(value7);

                                                }


                                                if (jsonObject.getString("workHours").equals("")) {
                                                    work_hours.setText("N/A");
                                                } else {
                                                    work_hours.setText(valu13);

                                                }


                                                if (jsonObject.getString("shift").equals("")) {
                                                    status_is.setText("N/A");
                                                } else {
                                                    status_is.setText(value9);

                                                }


                                                if (jsonObject.getString("remarks").equals("")) {
                                                    remarks.setText("N/A");
                                                } else {
                                                    remarks.setText(value11);

                                                }


                                                if (jsonObject.getString("remarks").equals("Absent")) {
                                                    singupbtn2.setVisibility(View.VISIBLE);
                                                } else {
                                                    singupbtn2.setVisibility(View.GONE);
//                                                    remarks.setText(value11);

                                                }


                                                if (jsonObject.getString("shiftTiming").equals("")) {
                                                    shift_timing.setText("N/A");
                                                } else {
                                                    shift_timing.setText(value10);

                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


//                                            time_out_is.setText(value8);
//                                            date_in_is.setText(value5);


//                                            date_out_is.setText(value7);
//                                            work_hours.setText(valu3);
//                                            status_is.setText(value9);
//                                            remarks.setText(value11);
//                                            shift_timing.setText(value10);


                                            Log.d("AttendaceValue", valu1 + valu2 + valu13 + valu4 + value5 + value6 + value7 + value8 + value9 + value10 + value11);
                                        }
                                    });


//                            Intent intent = new Intent(Daily_Attendance.this, DailyAttendance_Result.class);


//                            intent.putExtra("employeeNo", valu1);
//                            intent.putExtra("employeeName", valu2);
//                            intent.putExtra("workHours", valu3);
//
//                            intent.putExtra("attendanceDate", valu4);
//                            intent.putExtra("dateIn", value5);
//                            intent.putExtra("timeIn", value6);
//                            intent.putExtra("dateOut", value7);
//                            intent.putExtra("timeOut", value8);
//                            intent.putExtra("shift", value9);
//                            intent.putExtra("shiftTiming", value10);
//                            intent.putExtra("remarks", value11);
//
//                            intent.putExtra("dateIn", dateis);


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
//                                        shadow.setText("");
                                        }
                                    });

//                            startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

//                                               else {
//        new TTFancyGifDialog.Builder(Daily_Attendance.this)
//                .setTitle("Error Message")
//                .setMessage("Network Required..!!")
//                .setPositiveBtnBackground("#f4de15")
//                .setPositiveBtnText("OK")
//                .setGifResource(R.drawable.g)
//                .isCancellable(true)
//                .OnPositiveClicked(new TTFancyGifDialogListener() {
//                    @Override
//                    public void OnClick() {
////                                                                   Intent intent = new Intent(Manual_Check_Entry_Screen.this, LoginActivity.class);
////                                                                   startActivity(intent);
//                    }
//                })
//                .build();


//
//            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
//                    .setHeaderDrawable(R.drawable.header)
//                    .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                    .withDialogAnimation(true)
//                    .setTitle("Network Required..!!")
//                    .setPositiveText("OK")
//                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    input_date5.setText("");
//
//                            shadow.setText("");
//                        }
//                    });
//            dialogHeader_3.show();
//
//            }
//        });


//        if (!(shadow.getText().toString().equals("")))
//        {
//
////
//
//        }


//                }


//            shadow.setError("Date Can't Be Empty..!!");

//
//        else
//        {
//
//        }

                    /**/

//                manual_enter.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                    }
//                });


                    singupbtn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(fragment_daily_atendance.this, ManualEntryModule.class);
                            intent.putExtra("Attendance_Date", valu4);
                            Log.d("Attendancesss", valu4);
                            startActivity(intent);
                        }
                    });

                }

            }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
////        View view = inflater.inflate(R.layout.fragment_fragment_daily_atendance, container, false);
//
//
//            }
//
//
//
//
//        });
//
//        return view;
//    }

            public boolean isNetAvailable() {
                ConnectivityManager connectivityManager = (ConnectivityManager) fragment_daily_atendance.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }


//    private void GetProfileParams() {
//
////        pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//        Request request = new Request.Builder()
//                .url(urls + "api/Employee/GetEmployeeInfo")
//                .get().addHeader("Cache-Control", "no-cache")
//                .addHeader("Authorization", " Bearer " + LoginActivity.value)
//                .addHeader("Postman-Token", "4c066075-1378-4e06-9ee9-98fd460924f9")
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
////                    }
////
////                });
//
//                Log.e("HttpService", "onFailure() Request was: " + call);
//                e.printStackTrace();
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Cant Connect To Server")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                        Intent intent = new Intent(fragment_daily_atendance.this , LoginActivity.class);
//                                        startActivity(intent);
//                                    }
//                                });
//                        dialogHeader_3.show();
//                    }
//                });
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
////                    }
////
////                });
//
//                String responses = response.body().string();
//                Log.e("response", "onResponse(): " + responses);
//
//
//                if (response.code() == 500) {
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            Utilss.hideSweetLoader(pDialog);
////                        }
////                    });
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(responses);
//                        value2 = jsonObject.getString("exceptionMessage");
//                        Log.d("Exception_Message", value2);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                new TTFancyGifDialog.Builder(Home_Screen.this)
////                                        .setTitle("Error Message")
////                                        .setMessage(value2)
////                                        .setPositiveBtnBackground("#f4de15")
////                                        .setPositiveBtnText("Ok")
////                                        .setGifResource(R.drawable.g)
////                                        .isCancellable(true)
////                                        .OnPositiveClicked(new TTFancyGifDialogListener() {
////                                            @Override
////                                            public void OnClick() {
////                                            }
////                                        })
////                                        .build();
//
//                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_daily_atendance.this)
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(fragment_daily_atendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                        .withDialogAnimation(true)
//                                        .setTitle("Error Message")
//                                        .setDescription(value2)
//                                        .setPositiveText("OK")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    input_date5.setText("");
//
////                                                moveTaskToBack(true);
////                                                android.os.Process.killProcess(android.os.Process.myPid());
////                                                System.exit(1);
//                                            }
//                                        });
//                                dialogHeader_3.show();
//                            }
//                        });
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(responses);
////                        final String value = jsonObject.getString("employeeNo");
//                        final String valu2 = jsonObject.getString("employeeName");
//                        final String value3 = jsonObject.getString("department");
//                        final String value4 = jsonObject.getString("designation");
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                textEmployeeName.setText(valu2);
//                                textEmployeeDateOfEntry.setText(value4);
//                                textDepartment.setText(value3);
//
//                            }
//                        });
//
////                        Log.d("Value3", "name is" + valu2 + "employee number is " + value + "designtion is" + value3 + "department is" + value4);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }


        });
    }

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }
}

