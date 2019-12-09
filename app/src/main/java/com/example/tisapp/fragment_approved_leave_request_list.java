package com.example.tisapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.example.tisapp.Settings.Settings_Activity.urls2;


public class fragment_approved_leave_request_list extends AppCompatActivity {


    JSONObject jsonObject ;
    private final String JSON_URL = urls+"api/Leave/GetPendingLeaveRequests_Approver";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<PendingLeaveApprover> lstAnime;

    private List<PendingLeaveApprover> lstAnime2;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    OkHttpClient client;
String value2;

    MaterialStyledDialog.Builder dialogHeader_3;

    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;

    LinearLayout layNoInternet;

    ProgressView circularProgressBar;


    RecyclerViewAdapter7 myadapter;

//    public fragment_approved_leave_request_list() {
//        // Required empty public constructor
//    }
//
//    public static fragment_approved_leave_request_list newInstance(String param1, String param2) {
//        fragment_approved_leave_request_list fragment = new fragment_approved_leave_request_list();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_fragment_manual_leave_request_list);


        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();



        circularProgressBar = (ProgressView)findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(View.GONE);

//        alter_txt = view.findViewById(R.id.tv_altText);
        lstAnime = new ArrayList<>();

        lstAnime2 = new ArrayList<>();


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

//        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReport);

//        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
//        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
//        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;

        layNoInternet = findViewById(R.id.layNoInternet);


        if (isNetAvailable())
        {
//            GetProfileParams();
            jsonrequest();
        }



        else
        {
            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_approved_leave_request_list.this)
                    .setHeaderDrawable(R.drawable.header)
                    .setIcon(new IconicsDrawable(fragment_approved_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                    .withDialogAnimation(true)
                    .setTitle("Error Message")
                    .setDescription("No Internet Connected")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        }
                    });
            dialogHeader_3.show();
        }




        // Set a Refresh Listener for the SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the data
                // Calls setRefreshing(false) when it is finish
                updateOperation();
            }
        });
        
        
        
        
        
        
        
        
        


    }


    protected void updateOperation()
    {

        lstAnime2= new ArrayList<>();

//        myadapter.clear();
//        myadapter.addAll(lstAnime);
//        // Stop progress indicator when update finish

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if(response.length()== 0)
                {

//                    layNoInternet.setVisibility(View.VISIBLE);
//                    alterText.setVisibility(View.VISIBLE);

                    layNoInternet.setVisibility(View.VISIBLE);


                }
                else
                {
//                    layNoInternet.setVisibility(View.GONE);
//                    alterText.setVisibility(View.GONE);
                    layNoInternet.setVisibility(View.GONE);

                }






                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        PendingLeaveApprover anime = new PendingLeaveApprover();

                        anime.setRequestID(jsonObject.getString("requestID"));
                        anime.setLeaveCode(jsonObject.getString("leaveCode"));
                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
                        anime.setEmployeeName(jsonObject.getString("empName"));
                        anime.setReason(jsonObject.getString("reason"));
                        anime.setStartDate(jsonObject.getString("startDate"));
                        anime.setEndDate(jsonObject.getString("endDate"));
                        anime.setNoofdays(jsonObject.getString("noOfDays"));
                        anime.setStatus(jsonObject.getString("status"));


                        lstAnime2.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                 myadapter = new RecyclerViewAdapter7(fragment_approved_leave_request_list.this, lstAnime2);
                recyclerView.setLayoutManager(new LinearLayoutManager(fragment_approved_leave_request_list.this));
                recyclerView.setAdapter(myadapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

        requestQueue = Volley.newRequestQueue(fragment_approved_leave_request_list.this);
        requestQueue.add(request);


        mSwipeRefreshLayout.setRefreshing(false);
    }












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
//        circularProgressBar.setVisibility(View.VISIBLE);
//        circularProgressBar.start();
//
//
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
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
//
//                        circularProgressBar.setVisibility(View.GONE);
//                        circularProgressBar.stop();
//                    }
//                });
//
//                Log.e("HttpService", "onFailure() Request was: " + call);
//                e.printStackTrace();
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_approved_leave_request_list.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(fragment_approved_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Cant Connect To Server")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                        Intent intent = new Intent(fragment_approved_leave_request_list.this , LoginActivity.class);
//                                        startActivity(intent);
//                                    }
//                                });
//                        dialogHeader_3.show();
//                    }
//                });
//
//
//
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        Utilss.hideSweetLoader(pDialog);
//
//                        circularProgressBar.setVisibility(View.GONE);
//                        circularProgressBar.stop();
//                    }
//
//                });
//
//                String responses = response.body().string();
//                Log.e("response", "onResponse(): " + responses);
//
//
//                if (response.code() == 500) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
////                            Utilss.hideSweetLoader(pDialog);
//
//                            circularProgressBar.setVisibility(View.GONE);
//                            circularProgressBar.stop();
//                        }
//                    });
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
//                                    Intent intent = new Intent(fragment_approved_leave_request_list.this, LoginActivity.class);
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
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_approved_leave_request_list.this)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(fragment_approved_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                            .withDialogAnimation(true)
//                                            .setTitle("Error Message")
//                                            .setDescription(value2)
//                                            .setPositiveText("OK")
//                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                @Override
//                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                                }
//                                            });
//                                    dialogHeader_3.show();
//                                }
//                            });
//                        }
//
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
//////                                new TTFancyGifDialog.Builder(Home_Screen.this)
//////                                        .setTitle("Error Message")
//////                                        .setMessage(value2)
//////                                        .setPositiveBtnBackground("#f4de15")
//////                                        .setPositiveBtnText("Ok")
//////                                        .setGifResource(R.drawable.g)
//////                                        .isCancellable(true)
//////                                        .OnPositiveClicked(new TTFancyGifDialogListener() {
//////                                            @Override
//////                                            public void OnClick() {
//////                                            }
//////                                        })
//////                                        .build();
////
////                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_approved_leave_request_list.this)
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(fragment_approved_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                        .withDialogAnimation(true)
////                                        .setTitle("Error Message")
////                                        .setDescription(value2)
////                                        .setPositiveText("OK")
////                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                            @Override
////                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//////                                                    input_date5.setText("");
////
//////                                                moveTaskToBack(true);
//////                                                android.os.Process.killProcess(android.os.Process.myPid());
//////                                                System.exit(1);
////                                            }
////                                        });
////                                dialogHeader_3.show();
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























    private void jsonrequest() {

//        pDialog = Utilss.showSweetLoader(LeaveApprover.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

        circularProgressBar.setVisibility(View.VISIBLE);
        circularProgressBar.start();

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if(response.length()== 0)
                {
                    layNoInternet.setVisibility(View.VISIBLE);
                }

                else
                {
                    layNoInternet.setVisibility(View.GONE);
                }
//



                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
//                        Utilss.hideSweetLoader(pDialog);

                        circularProgressBar.setVisibility(View.GONE);
                        circularProgressBar.stop();
                    }
                });


                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        PendingLeaveApprover anime = new PendingLeaveApprover();

                        anime.setRequestID(jsonObject.getString("requestID"));
                        anime.setLeaveCode(jsonObject.getString("leaveCode"));
                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
                        anime.setEmployeeName(jsonObject.getString("empName"));
                        anime.setReason(jsonObject.getString("reason"));
                        anime.setStartDate(jsonObject.getString("startDate"));
                        anime.setEndDate(jsonObject.getString("endDate"));
                        anime.setNoofdays(jsonObject.getString("noOfDays"));
                        anime.setStatus(jsonObject.getString("status"));



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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
//                        Utilss.hideSweetLoader(pDialog);

                        circularProgressBar.setVisibility(View.GONE);
                        circularProgressBar.stop();
                    }
                });

                Log.d("Error12", error.toString());
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


        requestQueue = Volley.newRequestQueue(fragment_approved_leave_request_list.this);
        requestQueue.add(request);


    }











    private void setuprecyclerview(List<PendingLeaveApprover> lstAnime) {


         myadapter = new RecyclerViewAdapter7(fragment_approved_leave_request_list.this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_approved_leave_request_list.this));
        recyclerView.setAdapter(myadapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(fragment_approved_leave_request_list.this, DividerItemDecoration.VERTICAL));

        myadapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

}
