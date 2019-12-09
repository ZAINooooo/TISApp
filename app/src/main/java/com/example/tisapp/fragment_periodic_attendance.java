package com.example.tisapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.OkHttpClient;

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;


public class fragment_periodic_attendance extends BaseActivity {


    Button form_button;
    public static Button input_date;
    public static Button input_datee2;
    OkHttpClient client;
    MaterialStyledDialog.Builder dialogHeader_3;
    String value2;


    protected SweetAlertDialog pDialog;

//    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;


    //    private final String JSON_URL = LoginActivity.urls+"api/Attendance/GetPeriodicalAttendanceDetail?StartDate="+selected_date_fragment6.date_conversion+"&EndDate="+selected_date_fragment7.date_conversion;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Periodic> lstAnime;
    private RecyclerView recyclerView;

    LinearLayout layNoInternet;
    String datesssss2, datesssss;


//    public fragment_periodic_attendance() {
//        // Required empty public constructor
//    }
//
//
//    public static fragment_periodic_attendance newInstance(String param1, String param2) {
//        fragment_periodic_attendance fragment = new fragment_periodic_attendance();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_fragment_periodic_attendance);

        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();

//        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
//        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
//        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;

        form_button = (Button) findViewById(R.id.form_button);


        lstAnime = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_listview_data);


        input_date = (Button) findViewById(R.id.input_date);
        input_date.setInputType(InputType.TYPE_NULL);

        datesssss = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        input_date.setText(datesssss);


        input_datee2 = (Button) findViewById(R.id.input_date2);
        input_datee2.setInputType(InputType.TYPE_NULL);

        datesssss2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        input_datee2.setText(datesssss2);


        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment6();
                newFragment.show(getSupportFragmentManager(), "date picker");

            }
        });


        input_datee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment7();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        if (isNetAvailable()) {
//            GetProfileParams();
        } else {
            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("Internet Required..!!")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


//                            Intent intent = new Intent(fragment_periodic_attendance.this, LoginActivity.class);
//                            startActivity(intent);
                        }
                    });
            dialogHeader_3.show();
        }


        layNoInternet = findViewById(R.id.layNoInternet);
//        jsonrequest();


        form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pDialog = Utilss.showSweetLoader(fragment_periodic_attendance.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


                if (input_date.getText().toString().equals("")) {
//                        input_date.setError("Start Date Is Required..!");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("Start Date Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                            input_dates.setText("");
//                                            input_dates2.setText("");
                                }
                            });
                    dialogHeader_3.show();


                } else if (input_datee2.getText().toString().equals("")) {
//                        input_datee2.setError(" End Date Is Required");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
                            .setHeaderDrawable(R.drawable.header)
                            .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                            .withDialogAnimation(true)
                            .setTitle("Error Message")
                            .setDescription("End Date Is Required")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                            input_dates.setText("");
//                                            input_dates2.setText("");
                                }
                            });
                    dialogHeader_3.show();


                } else {


                    Calendar date1Calendar = Calendar.getInstance();
                    try {
                        date1Calendar.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(input_date.getText().toString()));
                        Calendar date2Calendar = Calendar.getInstance();
                        date2Calendar.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(input_datee2.getText().toString()));

                        long test = date2Calendar.getTime().getTime() - date1Calendar.getTime().getTime();
                        int To_From = Integer.parseInt(TimeUnit.DAYS.convert(test, TimeUnit.MILLISECONDS) + "");

                        Log.d("DayLeft", "" + To_From);

                        if (To_From < 0) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Utilss.hideSweetLoader(pDialog);
                                        }
                                    });

                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
                                            .setHeaderDrawable(R.drawable.header)
                                            .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                            .withDialogAnimation(true)
                                            .setTitle("Error Message")
                                            .setDescription("From Date Should Be smaller then To date")
                                            .setPositiveText("OK")
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                }
                                            });
                                    dialogHeader_3.show();

                                }
                            });
                        } else {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Utilss.hideSweetLoader(pDialog);
                                }
                            });

                            RecyclerViewAdapter myadapter = new RecyclerViewAdapter(fragment_periodic_attendance.this, lstAnime);
                            myadapter.clear();
                            jsonrequest();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


            }


        });
    }

    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        final View view= inflater.inflate(R.layout.fragment_fragment_periodic_attendance, container, false);
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
//            }
//        });
//        return view;
//    }

        private void jsonrequest () {

            pDialog = Utilss.showSweetLoader(fragment_periodic_attendance.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


            request = new JsonArrayRequest(urls + "api/Attendance/GetPeriodicalAttendanceDetail?StartDate=" + input_date.getText().toString() + "&EndDate=" + input_datee2.getText().toString(), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    if (response.length() == 0)
                    {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }

                        });

                        layNoInternet.setVisibility(View.VISIBLE);
                        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(fragment_periodic_attendance.this, lstAnime);
                        myadapter.clear();


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }

                                });

                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
                                        .setHeaderDrawable(R.drawable.header)
                                        .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                        .withDialogAnimation(true)
                                        .setTitle("Error Message")
                                        .setDescription("No Data Found")
                                        .setPositiveText("OK")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            }
                                        });
                                dialogHeader_3.show();
                            }
                        });


                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }

                        });


                        layNoInternet.setVisibility(View.GONE);
                    }

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
//                        }
//                    });

                    JSONObject jsonObject = null;

                    for (int i = 0; i < response.length(); i++) {


                        try {
                            jsonObject = response.getJSONObject(i);
                            Periodic anime = new Periodic();
                            anime.setEmployeeNo(jsonObject.getString("employeeNo"));
                            anime.setEmployeeName(jsonObject.getString("employeeName"));
                            anime.setWorkHours(jsonObject.getString("workHours"));

                            anime.setAttendanceDate(jsonObject.getString("attendanceDate"));
                            anime.setDateIn(jsonObject.getString("dateIn"));
                            anime.setDateOut(jsonObject.getString("dateOut"));
                            anime.setTimeIn(jsonObject.getString("timeIn"));
                            anime.setTimeOut(jsonObject.getString("timeOut"));

                            anime.setShift(jsonObject.getString("shift"));
                            anime.setShiftTiming(jsonObject.getString("shiftTiming"));
                            anime.setRemarks(jsonObject.getString("remarks"));

                            lstAnime.add(anime);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    setuprecyclerview(lstAnime);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("Error12", error.toString());


//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_periodic_attendance.this)
//                                    .setHeaderDrawable(R.drawable.header)
//                                    .setIcon(new IconicsDrawable(fragment_periodic_attendance.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                    .withDialogAnimation(true)
//                                    .setTitle("Error Message")
//                                    .setDescription("Cant Connect To Server")
//                                    .setPositiveText("OK")
//                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                        @Override
//                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
////                                        Intent intent = new Intent(fragment_periodic_attendance.this , LoginActivity.class);
////                                        startActivity(intent);
//                                        }
//                                    });
//                            dialogHeader_3.show();
//                        }
//                    });


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

            int socketTimeout = 3000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);

            requestQueue = Volley.newRequestQueue(fragment_periodic_attendance.this);
            requestQueue.add(request);


        }


        private void setuprecyclerview (List < Periodic > lstAnime) {


            RecyclerViewAdapter myadapter = new RecyclerViewAdapter(fragment_periodic_attendance.this, lstAnime);
            recyclerView.setLayoutManager(new LinearLayoutManager(fragment_periodic_attendance.this));

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(fragment_periodic_attendance.this), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(myadapter);

            myadapter.notifyDataSetChanged();
//        recyclerView.scheduleLayoutAnimation();

        }

    }






























