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
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzagz.runOnUiThread;
//import static com.google.android.gms.internal.zzahf.runOnUiThread;


public class fragment_periodic_attendance_summary extends BaseActivity {


    Button form_button;
    public static Button  input_dates;
    public static Button  input_dates2;
    OkHttpClient client;
    String ed1,ed2,datetime,responses,value2 ;
    //    TextView manual_date;
    protected SweetAlertDialog pDialog;

    MaterialStyledDialog.Builder dialogHeader_3;

    String valu1,valu11,value22,value3,value4,value5,value6,value8,value13;
    TextView lateHr,earlyHr,breakHr,overtimeHr,offDay,workHr,physicalPresent,absent,halfDay,lateIn,earlyOut,totalPresent,notSwappedOut,onTimes,onLeaves,gazDay,gazPresent;                ;

    String datesssss,datesssss2;

    LinearLayout daily_attendance,layNoInternet;

    int                        value10,value14;
//    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;

    String value9,value11,value12,value15,value16,value17,value18,value19,value20;




//    public fragment_periodic_attendance_summary() {
//        // Required empty public constructor
//    }
//
//
//    public static fragment_periodic_attendance_summary newInstance(String param1, String param2) {
//        fragment_periodic_attendance_summary fragment = new fragment_periodic_attendance_summary();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_periodic_attendance_summary);


        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

        daily_attendance= (LinearLayout) findViewById(R.id.daily_attendance);
        layNoInternet= (LinearLayout) findViewById(R.id.layNoInternet);



//        TextView lateHr,earlyHr,breakHr,overtimeHr,workHr,physicalPresent,absent,halfDay,lateIn,earlyOut,totalPresent,notSwappedOut,onTimes,onLeaves,gazDay,gazPresent;                ;


        lateHr = (TextView) findViewById(R.id.lateHrs);
        earlyHr = (TextView) findViewById(R.id.earlyHrs);
        breakHr = (TextView)findViewById(R.id.breakHrs);
        overtimeHr = (TextView) findViewById(R.id.overtimeHrs);
        workHr = (TextView) findViewById(R.id.workHrs);

        physicalPresent = (TextView) findViewById(R.id.physicalPresents);
        absent = (TextView) findViewById(R.id.absents);
        halfDay = (TextView) findViewById(R.id.halfDays);
        lateIn = (TextView) findViewById(R.id.lateIns);
        earlyOut = (TextView) findViewById(R.id.earlyOuts);
        totalPresent = (TextView) findViewById(R.id.totalPresents);
        notSwappedOut = (TextView) findViewById(R.id.notSwappedOuts);
        onTimes = (TextView) findViewById(R.id.onTime);
        onLeaves = (TextView) findViewById(R.id.onLeave);
        onLeaves = (TextView) findViewById(R.id.onLeave);

        gazDay = (TextView) findViewById(R.id.gazDays);


        offDay = (TextView) findViewById(R.id.offDays);
        gazPresent = (TextView) findViewById(R.id.gazPresents);


//
//        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
//        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
//        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;


        form_button = (Button) findViewById(R.id.form_button);

        input_dates = (Button) findViewById(R.id.input_date);
        input_dates.setInputType(InputType.TYPE_NULL);
        input_dates2 = (Button) findViewById(R.id.input_date2);
        input_dates2.setInputType(InputType.TYPE_NULL);


        datesssss = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        input_dates.setText(datesssss);






        datesssss2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        input_dates2.setText(datesssss2);




        ed1= input_dates.getText().toString();
        ed2= input_dates2.getText().toString();


        input_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment8();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        input_dates2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment9();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });




        if (isNetAvailable())
        {
//            GetProfileParams();
        }

        else {
            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Internet Required..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                            Intent intent = new Intent(fragment_periodic_attendance_summary.this, LoginActivity.class);
//                            startActivity(intent);
                        }
                    });
            dialogHeader_3.show();
        }









        form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                pDialog = Utilss.showSweetLoader(fragment_periodic_attendance_summary.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                if (input_dates.getText().toString().equals(""))
                {
//                            input_dates.setError("Start Date Is Required..!");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Start Date Is Required")
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

                }

                else if (input_dates2.getText().toString().equals(""))
                {
//                            input_dates2.setError(" End Date Is Required");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("End Date Is Required")
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
                }

                else
                {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


//                            new LoginActivity();
//                            new selected_date_fragment8();
//                            final String dateis = selected_date_fragment8.date_conversion;
//
//                            if (dateis==null || dateis.equals(""))
//                            {
//                                input_dates.setText(datesssss);
//                            }
//
//                            else
//                            {
//                                Log.d("Staticis", dateis);
//                            }
//
//
//
//
//
//                            new LoginActivity();
//                            new selected_date_fragment9();
//                            final String dateout = selected_date_fragment9.date_conversion;
//
//                            if (dateout==null || dateout.equals(""))
//                            {
//                                input_dates2.setText(datesssss2);
//                            }
//
//                            else
//                            {
//                                Log.d("Staticis", dateout);
//                            }











                    Calendar date1Calendar = Calendar.getInstance();
                    try {
                        date1Calendar.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(input_dates.getText().toString()));
                        Calendar date2Calendar = Calendar.getInstance();
                        date2Calendar.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(input_dates2.getText().toString()));




                        long test = date2Calendar.getTime().getTime() - date1Calendar.getTime().getTime();
                        int To_From = Integer.parseInt(TimeUnit.DAYS.convert(test, TimeUnit.MILLISECONDS)+"");

                        Log.d("To_From" , ""+To_From);

                        if (To_From < 0)  {


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Utilss.hideSweetLoader(pDialog);
                                }
                            });


//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Utilss.hideSweetLoader(pDialog);
//
//                                            new TTFancyGifDialog.Builder(Periodic_Attendance_Summary.this)
//                                                    .setTitle("Error Message")
//                                                    .setMessage("From Date Should Be smaller then To date")
//                                                    .setPositiveBtnBackground("#f4de15")
//                                                    .setPositiveBtnText("Ok")
//                                                    .setGifResource(R.drawable.g)
//                                                    .isCancellable(true)
//                                                    .OnPositiveClicked(new TTFancyGifDialogListener() {
//                                                        @Override
//                                                        public void OnClick() {
//
//                                                            input_dates.setText("");
//                                                            input_dates2.setText("");
//                                                        }
//                                                    })
//                                                    .build();
//                                        }
//                                    });




                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                                    .setHeaderDrawable(R.drawable.header)
                                    .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                    .withDialogAnimation(true)
                                    .setTitle("Error Message")
                                    .setDescription("From Date Should Be smaller then To date")
                                    .setPositiveText("OK")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                        {
//                                                            input_dates.setText("");
//                                                            input_dates2.setText("");
                                        }
                                    });
                            dialogHeader_3.show();







                        } else {

//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Utilss.hideSweetLoader(pDialog);
//                                        }
//                                    });


                            Request request = new Request.Builder().url(urls + "api/Attendance/GetPeriodicalAttendanceSummary?StartDate=" + input_dates.getText().toString() + "+&EndDate=" + input_dates2.getText().toString())
                                    .get()
                                    .addHeader("Cache-Control", "no-cache").addHeader("Authorization", " Bearer " + LoginActivity.value)
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

                                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                                                    .setHeaderDrawable(R.drawable.header)
                                                    .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                    .withDialogAnimation(true)
                                                    .setTitle("Error Message")
                                                    .setDescription("Cant Connect To Server")
                                                    .setPositiveText("OK")
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                                                                    Intent intent = new Intent(fragment_periodic_attendance_summary.this , LoginActivity.class);
//                                                                    startActivity(intent);
                                                        }
                                                    });
                                            dialogHeader_3.show();
                                        }
                                    });

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {

                                    responses = response.body().string();
                                    Log.e("response", "onResponse(): " + responses);

                                    if (response.code() == 200) {


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
//                                                        Utilss.hideSweetLoader(pDialog);

                                                daily_attendance.setVisibility(View.VISIBLE);
                                                layNoInternet.setVisibility(View.GONE);
                                            }
                                        });


                                        try {

                                            JSONArray jsonArray = new JSONArray(responses);
                                            JSONObject jsonObject;

                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                jsonObject = jsonArray.getJSONObject(i);

                                                if (jsonArray == null) {
                                                    Toast.makeText(fragment_periodic_attendance_summary.this, "No data found..!1", Toast.LENGTH_SHORT).show();
                                                    Log.d("ResponseIS", responses);
                                                } else {

                                                    valu1 = jsonObject.getString("employeeNo");
                                                    valu11 = jsonObject.getString("employeeName");

                                                    value2 = jsonObject.getString("lateHrs");
                                                    value3 = jsonObject.getString("earlyHrs");
                                                    value4 = jsonObject.getString("breakHrs");
                                                    value5 = jsonObject.getString("overtimeHrs");
                                                    value6 = jsonObject.getString("workHrs");




                                                    value8 = jsonObject.getString("physicalPresents");
                                                    value13 = jsonObject.getString("totalPresents");



                                                    value9 = jsonObject.getString("absents");
                                                    value10 = jsonObject.getInt("halfDays");
                                                    value11 = jsonObject.getString("lateIns");
                                                    value12 = jsonObject.getString("earlyOuts");
                                                    value14 = jsonObject.getInt("notSwappedOuts");
                                                    value15 = jsonObject.getString("onTime");
                                                    value16 = jsonObject.getString("onLeave");
                                                    value17 = jsonObject.getString("gazDays");
                                                    value18 = jsonObject.getString("offDays");
                                                    value19 = jsonObject.getString("gazPresents");
                                                    value20 = jsonObject.getString("offPresents");


                                                    Log.d("AttendaceValue", valu1 + value2 + value3 + value4 + value5 + value6 + value8 +
                                                            value9 + value10 + value11 + value12 + value13 + value14 + value15 +
                                                            value16 + value17 + value18 + value19 + value20 + valu11);


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run()
                                                        {
                                                            lateHr.setText(value2);
                                                            earlyHr.setText(value3);
                                                            breakHr.setText(value4);
                                                            overtimeHr.setText(value5);
                                                            workHr.setText(value6);
                                                            physicalPresent.setText(value8);


                                                            absent.setText(""+value9);
                                                            halfDay.setText(""+value10);
                                                            lateIn.setText(""+value11);
                                                            earlyOut.setText(""+value12);


                                                            totalPresent.setText(value13);
                                                            notSwappedOut.setText(""+value14);
                                                            onTimes.setText(""+value15);
                                                            onLeaves.setText(""+value16);
                                                            gazDay.setText(""+value17);
                                                            offDay.setText(""+value18);
                                                            gazPresent.setText(""+value19);
                                                        }
                                                    });









//                                                            runOnUiThread(new Runnable() {
//                                                                @Override
//                                                                public void run() {
//
//                                                                    input_dates.setText("");
//                                                                    input_dates2.setText("");
//                                                                }
//                                                            });

//                                                            Intent intent = new Intent(Periodic_Attendance_Summary.this, Periodic_attendance_Summary_result.class);
//
//
//                                                            intent.putExtra("employeeNo", valu1);
//                                                            intent.putExtra("employeeName", valu11);
//
//                                                            intent.putExtra("lateHrs", value2);
//                                                            intent.putExtra("earlyHrs", value3);
//                                                            intent.putExtra("breakHrs", value4);
//                                                            intent.putExtra("overtimeHrs", value5);
//                                                            intent.putExtra("workHrs", value6);
//                                                            intent.putExtra("physicalPresents", value8);
//                                                            intent.putExtra("absents", value9);
//                                                            intent.putExtra("date_in", dateis);
//                                                            intent.putExtra("date_out", dateout);
//                                                            intent.putExtra("halfDays", value10);
//                                                            intent.putExtra("lateIns", value11);
//                                                            intent.putExtra("earlyOuts", value12);
//                                                            intent.putExtra("totalPresents", value13);
//                                                            intent.putExtra("notSwappedOuts", value14);
//                                                            intent.putExtra("onTime", value15);
//                                                            intent.putExtra("onLeave", value16);
//                                                            intent.putExtra("gazDays", value17);
//                                                            intent.putExtra("offDays", value18);
//                                                            intent.putExtra("gazPresents", value19);
//                                                            intent.putExtra("offPresents", value20);
//
//                                                            startActivity(intent);
                                                }
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }



                                    else if (response.code() == 500)
                                    {


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
//                                                        Utilss.hideSweetLoader(pDialog);

                                                daily_attendance.setVisibility(View.GONE);
                                                layNoInternet.setVisibility(View.VISIBLE);
                                            }
                                        });


//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        Utilss.hideSweetLoader(pDialog);
//                                                    }
//                                                });

                                        JSONObject jsonObject;
                                        try {
                                            jsonObject = new JSONObject(responses);
                                            value2 = jsonObject.getString("exceptionMessage");
                                            Log.d("Exception_Message", value2);


                                            if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
                                                runOnUiThread(new Runnable()
                                                {
                                                    @Override
                                                    public void run() {

                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Error Message")
                                                                .setDescription("No user mapped with current token!")
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                                                    {
                                                                        Intent intent = new Intent(fragment_periodic_attendance_summary.this, LoginActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }


                                            else
                                            {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Error Message")
                                                                .setDescription(value2)
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                                                                        input_dates.setText("");
//                                                                        input_dates2.setText("");
                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }






                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//
//                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
////                                                                .setHeaderDrawable(R.drawable.header)
////                                                                .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                                                .withDialogAnimation(true)
////                                                                .setTitle("Error Message")
////                                                                .setDescription(value2)
////                                                                .setPositiveText("OK")
////                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                                                    @Override
////                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
////                                                                        input_dates.setText("");
////                                                                        input_dates2.setText("");
////                                                                    }
////                                                                });
////                                                        dialogHeader_3.show();
//                                                    }
//                                                });


                                    }


                                    else
                                    {
                                        //Do Nothing..!!
                                    }
                                }

                            });
                        }
                    }

                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                }



            }






        });


        
        
        
        
        
        
        
        
        
        
        
        



    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view= inflater.inflate(R.layout.fragment_fragment_periodic_attendance_summary, container, false);
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
//        return view;
//    }

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
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
//
//
//
//                        if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                            runOnUiThread(new Runnable()
//                            {
//                                @Override
//                                public void run() {
//
//                                    Intent intent = new Intent(fragment_periodic_attendance_summary.this, LoginActivity.class);
//                                    startActivity(intent);
//                                }
//                            });
//                        }
//
//
//                        else
//                        {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance_summary.this)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(fragment_periodic_attendance_summary.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                            .withDialogAnimation(true)
//                                            .setTitle("Error Message")
//                                            .setDescription(value2)
//                                            .setPositiveText("OK")
//                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                @Override
//                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                                    input_date5.setText("");
//
////                                                moveTaskToBack(true);
////                                                android.os.Process.killProcess(android.os.Process.myPid());
////                                                System.exit(1);
//                                                }
//                                            });
//                                    dialogHeader_3.show();
//                                }
//                            });
//                        }
//
//
//
//
//
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
////
////                            }
////                        });
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





}
