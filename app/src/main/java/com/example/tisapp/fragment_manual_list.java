package com.example.tisapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
//import static com.google.android.gms.internal.zzagz.runOnUiThread;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzahf.runOnUiThread;

public class fragment_manual_list extends AppCompatActivity {


    CardView card;
    OkHttpClient client;

    private final String JSON_URL = urls + "api/Manual/GetPendingManualRequests_Employee";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<PendingManualRequests_Employee> lstAnime;
    private List<PendingManualRequests_Employee> lstAnime2;

    private OfflineDataListAdapter offlineDataListAdapter;


    private RecyclerView recyclerView;
    String value2;
    ImageView iv_back;



    DatabaseHelper dbHelper;
    public ArrayList<Offline> offlineDataList;
    public ArrayList<Offline> offlineDataList2;

//    LinearLayout layNoInternet;


//    RecyclerViewAdapter2 myadapter;
    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;

    TextView layNoInternet;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    ProgressView circularProgressBar;
    MaterialStyledDialog.Builder dialogHeader_3;


    public fragment_manual_list() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_list);

        dbHelper  = new DatabaseHelper(this);
        offlineDataList = new ArrayList<>();
        offlineDataList2= new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewReport);




        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(GONE);
//        lstAnime = new ArrayList<>();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        layNoInternet = (TextView) findViewById(R.id.no_data_found);
//
//        if (isNetAvailable())
//        {
////            GetProfileParams();
//            jsonrequest();
//        }
//
//        else
//        {
//
//        }


        offlineDataListAdapter = new OfflineDataListAdapter(fragment_manual_list.this);
        offlineDataListAdapter.addOfflineDataList(offlineDataList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_list.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(offlineDataListAdapter);
//        loadRemainingData();

        iv_back =(ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




           getdatafromdb();



        // Set a Refresh Listener for the SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the data
                // Calls setRefreshing(false) when it is finish

                offlineDataListAdapter = new OfflineDataListAdapter(fragment_manual_list.this);
                offlineDataListAdapter.addOfflineDataList(offlineDataList2);

                updateOperation();
            }
        });
    }

    public void getdatafromdb()
    {

//        Cursor OffliceData = mDatabaseHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN);
//        if (OffliceData.getCount() <= 0) {
//            Toast.makeText(context, "No offline data available", Toast.LENGTH_SHORT).show();
//        }


        OfflineDataListAdapter offlineDataListAdapter = new OfflineDataListAdapter(fragment_manual_list.this);
//        com.example.tisapp.DatabaseHelper dbHelper  = new com.example.tisapp.DatabaseHelper(view.getContext());
        Cursor timeRequestOfflineData = dbHelper.getData();
//        Cursor timeRequestOfflineData2 = dbHelper.getDataOut();


        if (timeRequestOfflineData.getCount() <= 0 )
        {
//            Toast.makeText(fragment_manual_list.this, "No offline data available", Toast.LENGTH_SHORT).show();
            layNoInternet.setVisibility(View.VISIBLE);
//            offlineDataList.removeAll(offlineDataList);
        }

        else
        {

            layNoInternet.setVisibility(View.GONE);


            if (timeRequestOfflineData.moveToFirst())
            {
                do
                {
                    Offline offline = new Offline();


                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL1)));
                    String date_ins = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2));
                    String date_ins2 = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL4));

                    if (date_ins.equals(""))
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL4)));
                    }

                    else    if (date_ins2.equals(""))
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2)));
                    }

                    else
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2)));
                    }



                    offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL11)));
                    offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL6)));
                    offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL10)));

                    offlineDataList.add(offline);

                }while(timeRequestOfflineData.moveToNext());
            }












//           if (timeRequestOfflineData2.moveToFirst())
//            {
//
//                do
//                {
//                    Offline offline = new Offline();
//
//                    offline.setDate(timeRequestOfflineData2.getString(timeRequestOfflineData2.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4)));
//                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1)));
//                    offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL11)));
//                    offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL6)));
//                    offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL10)));
//                    offlineDataList.add(offline);
//                }while(timeRequestOfflineData.moveToNext());
//            }



            timeRequestOfflineData.close();
//            timeRequestOfflineData2.close();
            offlineDataListAdapter.addOfflineDataList(offlineDataList);
            offlineDataListAdapter.notifyDataSetChanged();
        }

    }
























    protected void updateOperation() {
        OfflineDataListAdapter offlineDataListAdapter = new OfflineDataListAdapter(fragment_manual_list.this);
//        com.example.tisapp.DatabaseHelper dbHelper  = new com.example.tisapp.DatabaseHelper(view.getContext());
        Cursor timeRequestOfflineData = dbHelper.getData();
//        Cursor timeRequestOfflineData2 = dbHelper.getDataOut();


        if (timeRequestOfflineData.getCount() <= 0) {
//            Toast.makeText(fragment_manual_list.this, "No offline data available", Toast.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
            layNoInternet.setVisibility(View.VISIBLE);
        } else {

            layNoInternet.setVisibility(View.GONE);


            if (timeRequestOfflineData.moveToFirst()) {
                do {
//                    Offline offline = new Offline();
//
//
//                    offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2)));
//                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1)));
//                    offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL11)));
//                    offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL6)));
//                    offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL10)));
//                    offlineDataList.add(offline);
//
//                } while (timeRequestOfflineData.moveToNext());
//            }
//
//
////           if (timeRequestOfflineData2.moveToFirst())
////            {
////
////                do
////                {
////                    Offline offline = new Offline();
////
////                    offline.setDate(timeRequestOfflineData2.getString(timeRequestOfflineData2.getColumnIndex(com.example.tisapp.DatabaseHelper.COL4)));
////                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1)));
////                    offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL11)));
////                    offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL6)));
////                    offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL10)));
////                    offlineDataList.add(offline);
////                }while(timeRequestOfflineData.moveToNext());
////            }
//
//
//            timeRequestOfflineData.close();
////            timeRequestOfflineData2.close();
//            offlineDataListAdapter.addOfflineDataList(offlineDataList);
//            offlineDataListAdapter.notifyDataSetChanged();



                    Offline offline = new Offline();


                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL1)));
                    String date_ins = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2));
                    String date_ins2 = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL4));

                    if (date_ins.equals(""))
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL4)));
                    }

                    else    if (date_ins2.equals(""))
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2)));
                    }

                    else
                    {
                        offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2)));
                    }



                    offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL11)));
                    offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL6)));
                    offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL10)));
                    offlineDataList2.add(offline);
                }while(timeRequestOfflineData.moveToNext());
            }


            timeRequestOfflineData.close();
            offlineDataListAdapter.addOfflineDataList(offlineDataList2);
            offlineDataListAdapter.notifyDataSetChanged();

            mSwipeRefreshLayout.setRefreshing(false);
        }


    }




















//    public  boolean isNetAvailable()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) fragment_manual_list.this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null && networkInfo.isConnected();
//    }





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
//                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_list.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(fragment_manual_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Error Message")
//                                .setDescription("Cant Connect To Server")
//                                .setPositiveText("OK")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                        Intent intent = new Intent(fragment_manual_list.this , LoginActivity.class);
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
//
//
//
//                        if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
//                            runOnUiThread(new Runnable()
//                            {
//                                @Override
//                                public void run() {
//
//                                    Intent intent = new Intent(fragment_manual_list.this, LoginActivity.class);
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
//                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_list.this)
//                                            .setHeaderDrawable(R.drawable.header)
//                                            .setIcon(new IconicsDrawable(fragment_manual_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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
////                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_list.this)
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(fragment_manual_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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





























//    private void jsonrequest() {
//
//
//        circularProgressBar.setVisibility(VISIBLE);
//        circularProgressBar.start();
//
//        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//
//                if(response.length()== 0)
//                {
//
////                    layNoInternet.setVisibility(VISIBLE);
////                    alterText.setVisibility(VISIBLE);
//
//                    layNoInternet.setVisibility(VISIBLE);
//
//
//                }
//                else
//                {
////                    layNoInternet.setVisibility(GONE);
////                    alterText.setVisibility(GONE);
//                    layNoInternet.setVisibility(GONE);
//
//                }
//
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
////                        Utilss.hideSweetLoader(pDialog);
//
//                        circularProgressBar.setVisibility(GONE);
//                        circularProgressBar.stop();
//                    }
//                });
//
//
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run()
////                    {
////                        Utilss.hideSweetLoader(pDialog);
////                    }
////                });
//
//                JSONObject jsonObject = null;
//
//                for (int i = 0; i < response.length(); i++) {
//
//
//                    try {
//                        jsonObject = response.getJSONObject(i);
//
//                        PendingManualRequests_Employee anime = new PendingManualRequests_Employee();
//                        anime.setRequestID(jsonObject.getString("requestID"));
//                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
//                        anime.setEmployeeName(jsonObject.getString("employeeName"));
//                        anime.setTimeIn(jsonObject.getString("timeIn"));
//                        anime.setTimeOut(jsonObject.getString("timeOut"));
//                        anime.setDateIn(jsonObject.getString("dateIn"));
//                        anime.setDateOut(jsonObject.getString("dateOut"));
//                        anime.setAttendanceDate(jsonObject.getString("attendanceDate"));
//                        anime.setStatus(jsonObject.getString("status"));
//                        anime.setReason(jsonObject.getString("reason"));
//                        anime.setApprovalStatus1(jsonObject.getString("approvalStatus1"));
//                        anime.setRemarks1(jsonObject.getString("remarks1"));
//                        anime.setApprovalStatus2(jsonObject.getString("approvalStatus2"));
//                        anime.setRemarks2(jsonObject.getString("remarks2"));
//                        anime.setApprovalStatus3(jsonObject.getString("approvalStatus3"));
//                        anime.setRemarks3(jsonObject.getString("remarks3"));
//                        anime.setApprovalStatus4(jsonObject.getString("approvalStatus4"));
//                        anime.setRemarks4(jsonObject.getString("remarks4"));
//                        anime.setApprovalDate(jsonObject.getString("approvalDate"));
//                        anime.setUpdateDateTime(jsonObject.getString("updateDateTime"));
//                        anime.setPosted(jsonObject.getString("posted"));
//                        anime.setGpsCoordinates(jsonObject.getString("gpsCoordinates"));
//                        anime.setCustomerInfo(jsonObject.getString("customerInfo"));
//                        anime.setApproverField(jsonObject.getString("approverField"));
//
//                        lstAnime.add(anime);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//                setuprecyclerview(lstAnime);
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run()
////                    {
////                        Utilss.hideSweetLoader(pDialog);
////                    }
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
////                        Utilss.hideSweetLoader(pDialog);
//
//                        circularProgressBar.setVisibility(GONE);
//                        circularProgressBar.stop();
//                    }
//                });
//
//
////                });
//
//
//
//                Log.d("Error12", error.toString());
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_list.this)
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(fragment_manual_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Error Message")
////                                .setDescription("Cant Connect To Server")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
////                                        Intent intent = new Intent(fragment_manual_list.this , LoginActivity.class);
////                                        startActivity(intent);
////                                    }
////                                });
////                        dialogHeader_3.show();
////                    }
////                });
//
//
//
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                params.put("Authorization", " Bearer " + LoginActivity.value);
//                params.put("Cache-Control", "no-cache");
//                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
//                Log.d("AuthKey", LoginActivity.value);
//
//                return params;
//            }
//        };
//
////        int socketTimeout = 3000;//3 seconds - change to what you want
////        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
////        request.setRetryPolicy(policy);
//
//        requestQueue = Volley.newRequestQueue(fragment_manual_list.this);
//        requestQueue.add(request);
//
//
//    }

//    private void setuprecyclerview(List<PendingManualRequests_Employee> lstAnime) {
//
//         myadapter = new RecyclerViewAdapter2(fragment_manual_list.this,lstAnime) ;
//
//        myadapter = new RecyclerViewAdapter2(fragment_manual_list.this,lstAnime) ;
//        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_list.this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(fragment_manual_list.this), DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(myadapter);
//        myadapter.notifyDataSetChanged();
//        recyclerView.scheduleLayoutAnimation();
//
//    }


}
