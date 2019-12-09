
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
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class fragment_manual_leave_request_list extends AppCompatActivity {


    CardView card;
    OkHttpClient client;
    private LeaveOfflineDataListAdapter offlineDataListAdapter;


    private RecyclerView recyclerView;
    String value2;

    Cursor timeRequestOfflineData;
ImageView iv_back;
    DatabaseHelper dbHelper;
    public ArrayList<LeaveOffline> offlineDataList;
    public ArrayList<LeaveOffline> offlineDataList2;

    TextView layNoInternet;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    ProgressView circularProgressBar;
    MaterialStyledDialog.Builder dialogHeader_3;


    public fragment_manual_leave_request_list() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_leave_list);

        dbHelper  = new DatabaseHelper(this);
        offlineDataList = new ArrayList<>();
        offlineDataList2= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewReport);

        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        layNoInternet = (TextView) findViewById(R.id.no_data_found);

        offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
        offlineDataListAdapter.addOfflineDataList(offlineDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_leave_request_list.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(offlineDataListAdapter);


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

                offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
                offlineDataListAdapter.addOfflineDataList(offlineDataList2);

                updateOperation();
            }
        });

    }

    public void getdatafromdb()
    {
        LeaveOfflineDataListAdapter offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
         timeRequestOfflineData = dbHelper.getLeaveFormData();

        if (timeRequestOfflineData.getCount() <= 0 )
        {
            layNoInternet.setVisibility(View.VISIBLE);
        }

        else
        {

            layNoInternet.setVisibility(View.GONE);
            if (timeRequestOfflineData.moveToFirst())
            {
                do
                {
                    LeaveOffline offline = new LeaveOffline();


//                    String zz = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL19));
//                    Log.d("SALUE" , zz);

                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL1)));
                    offline.setFromDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL19)));
                    offline.setLeaveCode(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL18)));
                    offline.setToDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL20)));
                    offline.setSyncStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL222)));
                    offline.setRemarks(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL22)));


                    offlineDataList.add(offline);

                }while(timeRequestOfflineData.moveToNext());
            }

            timeRequestOfflineData.close();
            offlineDataListAdapter.addOfflineDataList(offlineDataList);
            offlineDataListAdapter.notifyDataSetChanged();
        }

    }



    protected void updateOperation() {
        LeaveOfflineDataListAdapter offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
        Cursor timeRequestOfflineData = dbHelper.getLeaveFormData();

        if (timeRequestOfflineData.getCount() <= 0)
        {
            mSwipeRefreshLayout.setRefreshing(false);
            layNoInternet.setVisibility(View.VISIBLE);
        } else {

//            mSwipeRefreshLayout.setRefreshing(false);
            layNoInternet.setVisibility(View.GONE);


            if (timeRequestOfflineData.moveToFirst()) {
                do {
                    LeaveOffline offline = new LeaveOffline();

                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL1)));
                    offline.setFromDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL19)));
                    offline.setLeaveCode(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL18)));
                    offline.setToDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL20)));
                    offline.setRemarks(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL22)));
                    offline.setSyncStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL222)));

                    offlineDataList2.add(offline);

                } while (timeRequestOfflineData.moveToNext());
            }
            timeRequestOfflineData.close();
            offlineDataListAdapter.addOfflineDataList(offlineDataList2);
            offlineDataListAdapter.notifyDataSetChanged();

            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}















































//package com.example.tisapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//
//import com.example.tisapp.Login.LoginActivity;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
//import com.mikepenz.iconics.IconicsDrawable;
//import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
//import com.rey.material.widget.ProgressView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//
//import static com.example.tisapp.Settings.Settings_Activity.urls;
//import static com.example.tisapp.Settings.Settings_Activity.urls2;
//
//
//public class fragment_manual_leave_request_list extends AppCompatActivity {
//
//
//
//    JSONObject jsonObject ;
//    TextView layNoInternet;
//
//
////    private final String JSON_URL = "api/Leave/GetPendingLeaveRequests_Employee";
//
////    private final String JSON_URL = urls+"api/Leave/GetPendingLeaveRequests_Employee";
////    private JsonArrayRequest request;
////    private RequestQueue requestQueue;
////    private List<PendingLeaveManual> lstAnime;
////    private List<PendingLeaveManual> lstAnime2;
//
//
////    RecyclerViewAdapter5 myadapter;
//    private RecyclerView recyclerView;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//
//    public ArrayList<LeaveOffline> offlineDataList;
//    private LeaveOfflineDataListAdapter myadapter;
//
//
//    //    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;
//      String value2;
////    LinearLayout layNoInternet;
////    OkHttpClient client;
//
//com.example.tisapp.DatabaseHelper dbHelper;
//    MaterialStyledDialog.Builder dialogHeader_3;
//    ProgressView circularProgressBar;
//
//
//
////    public fragment_manual_leave_request_list() {
////        // Required empty public constructor
////    }
////
////    // TODO: Rename and change types and number of parameters
////    public static fragment_manual_leave_request_list newInstance(String param1, String param2) {
////        fragment_manual_leave_request_list fragment = new fragment_manual_leave_request_list();
////        Bundle args = new Bundle();
////        fragment.setArguments(args);
////        return fragment;
////    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_fragment_manual_leave_request_list);
//
//
//
//        dbHelper  = new com.example.tisapp.DatabaseHelper(this);
//        offlineDataList = new ArrayList<>();
//
//
//
//
////        client = new OkHttpClient.Builder().connectTimeout(Long.parseLong(urls2), TimeUnit.SECONDS)
////                .writeTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).readTimeout(Long.parseLong(urls2), TimeUnit.SECONDS).build();
//
//
//        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
//        circularProgressBar.setVisibility(View.GONE);
//
//
//
//////        alter_txt = view.findViewById(R.id.tv_altText);
////        lstAnime = new ArrayList<>();
////
////        lstAnime2 = new ArrayList<>();
//
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
//        layNoInternet = (TextView) findViewById(R.id.no_data_found);
//
////        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReport);
//
////        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
////        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
////        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;
//
//
//
//
//
//
//        myadapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
//        myadapter.addOfflineDataList(offlineDataList);
////        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_leave_request_list.this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(myadapter);
//        loadRemainingData();
//
//
//
//
////        if (isNetAvailable())
////        {
//////            GetProfileParams();
////            jsonrequest();
////        }
////        else
////        {
////            dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_leave_request_list.this)
////                    .setHeaderDrawable(R.drawable.header)
////                    .setIcon(new IconicsDrawable(fragment_manual_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                    .withDialogAnimation(true)
////                    .setTitle("Error Message")
////                    .setDescription("No Internet Connected")
////                    .setPositiveText("OK")
////                    .onPositive(new MaterialDialog.SingleButtonCallback() {
////                        @Override
////                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                        }
////                    });
////            dialogHeader_3.show();
////        }
//
//
//
//
//        // Set a Refresh Listener for the SwipeRefreshLayout
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Refresh the data
//                // Calls setRefreshing(false) when it is finish
//                updateOperation();
//            }
//        });
//
//
//
//
//
//    }
//
//    public void loadRemainingData()
//    {
//
////        Cursor OffliceData = mDatabaseHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN);
////        if (OffliceData.getCount() <= 0) {
////            Toast.makeText(context, "No offline data available", Toast.LENGTH_SHORT).show();
////        }
//
//
//        LeaveOfflineDataListAdapter offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
////        com.example.tisapp.DatabaseHelper dbHelper  = new com.example.tisapp.DatabaseHelper(view.getContext());
//        Cursor timeRequestOfflineData = dbHelper.getLeaveFormData();
//
//
//        Log.d("DataAll" , ""+timeRequestOfflineData);
//
//
//        if (timeRequestOfflineData.getCount() <= 0)
//        {
////            Toast.makeText(getApplicationContext(), "No offline data available", Toast.LENGTH_SHORT).show();
//            layNoInternet.setVisibility(View.VISIBLE);
//        }
//
//        else
//        {
//
//            layNoInternet.setVisibility(View.GONE);
//
//            if (timeRequestOfflineData.moveToFirst()){
//                do{
//                    LeaveOffline offline = new LeaveOffline();
//
//
//                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL188)));
//                    offline.setFromDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL19)));
//                    offline.setToDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL20)));
//                    offline.setLeaveCode(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL18)));
//                    offline.setRemarks(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL22)));
//                    offline.setSyncStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL222)));
////                    offline.setResponse(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2233)));
//                    offlineDataList.add(offline);
//                }while(timeRequestOfflineData.moveToNext());
//            }
//            timeRequestOfflineData.close();
//            offlineDataListAdapter.addOfflineDataList(offlineDataList);
//            offlineDataListAdapter.notifyDataSetChanged();
//        }
//
//
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Refresh the data
//                // Calls setRefreshing(false) when it is finish
//                updateOperation();
//            }
//        });
//
//    }
//
//    private void updateOperation()
//    {
//
//        LeaveOfflineDataListAdapter offlineDataListAdapter = new LeaveOfflineDataListAdapter(fragment_manual_leave_request_list.this);
////        com.example.tisapp.DatabaseHelper dbHelper  = new com.example.tisapp.DatabaseHelper(view.getContext());
//        Cursor timeRequestOfflineData = dbHelper.getLeaveFormData();
////        Cursor timeRequestOfflineData2 = dbHelper.getDataOut();
//
//
//        if (timeRequestOfflineData.getCount() <= 0) {
////            Toast.makeText(fragment_manual_leave_request_list.this, "No offline data available", Toast.LENGTH_SHORT).show();
//            layNoInternet.setVisibility(View.VISIBLE);
//        } else {
//
//            layNoInternet.setVisibility(View.GONE);
//
//
//            if (timeRequestOfflineData.moveToFirst()) {
//                do {
//                    LeaveOffline offline = new LeaveOffline();
//
//
////                    offline.setID(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2)));
//                    offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL188)));
//                    offline.setFromDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL19)));
//                    offline.setToDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL20)));
//                    offline.setLeaveCode(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL18)));
//                    offline.setRemarks(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL22)));
//                    offline.setSyncStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL222)));
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
//
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
//
//
//
////        loadRemainingData();
////        mSwipeRefreshLayout.setRefreshing(false);
//    }
//
//
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
//////        return inflater.inflate(R.layout.fragment_fragment_manual_leave_request_list, container, false);
////
//////        View view = inflater.inflate(R.layout.fragment_fragment_manual_leave_request_list, container, false);
////
////
////
////
////
////
////
////        return view;
////    }
//
//    // Set a Refresh Listener for the SwipeRefreshLayout
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
//    public  boolean isNetAvailable()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) fragment_manual_leave_request_list.this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null && networkInfo.isConnected();
//    }
//
////    private void GetProfileParams() {
////
//////        pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
////
////        circularProgressBar.setVisibility(View.VISIBLE);
////        circularProgressBar.start();
////
////
////
////        Request request = new Request.Builder()
////                .url(urls + "api/Employee/GetEmployeeInfo")
////                .get().addHeader("Cache-Control", "no-cache")
////                .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                .addHeader("Postman-Token", "4c066075-1378-4e06-9ee9-98fd460924f9")
////                .build();
////
////        Call call = client.newCall(request);
////        call.enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
//////                        Utilss.hideSweetLoader(pDialog);
////
////                        circularProgressBar.setVisibility(View.GONE);
////                        circularProgressBar.stop();
////                    }
////                });
////
////                Log.e("HttpService", "onFailure() Request was: " + call);
////                e.printStackTrace();
////
////
////
////
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_leave_request_list.this)
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(fragment_manual_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Error Message")
////                                .setDescription("Cant Connect To Server")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
////                                        Intent intent = new Intent(fragment_manual_leave_request_list.this , LoginActivity.class);
////                                        startActivity(intent);
////                                    }
////                                });
////                        dialogHeader_3.show();
////                    }
////                });
////
////            }
////
////            @Override
////            public void onResponse(Call call, okhttp3.Response response) throws IOException {
////
////
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
//////                        Utilss.hideSweetLoader(pDialog);
////
////                        circularProgressBar.setVisibility(View.GONE);
////                        circularProgressBar.stop();
////                    }
////
////                });
////
////                String responses = response.body().string();
////                Log.e("response", "onResponse(): " + responses);
////
////
////                if (response.code() == 500) {
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
//////                            Utilss.hideSweetLoader(pDialog);
////
////                            circularProgressBar.setVisibility(View.GONE);
////                            circularProgressBar.stop();
////                        }
////                    });
////
////                    try {
////                        JSONObject jsonObject = new JSONObject(responses);
////                        value2 = jsonObject.getString("exceptionMessage");
////                        Log.d("Exception_Message", value2);
////
////
////                        if (jsonObject.getString("exceptionMessage").equals("No user mapped with current token!")) {
////                            runOnUiThread(new Runnable()
////                            {
////                                @Override
////                                public void run() {
////
////                                    Intent intent = new Intent(fragment_manual_leave_request_list.this, LoginActivity.class);
////                                    startActivity(intent);
////                                }
////                            });
////                        }
////
////
////                        else
////                        {
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////
////                                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_leave_request_list.this)
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(fragment_manual_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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
////                                }
////                            });
////                        }
////
////
////
////
////
////
////
////
//////                        runOnUiThread(new Runnable() {
//////                            @Override
//////                            public void run() {
//////
//////                                dialogHeader_3 = new MaterialStyledDialog.Builder(fragment_manual_leave_request_list.this)
//////                                        .setHeaderDrawable(R.drawable.header)
//////                                        .setIcon(new IconicsDrawable(fragment_manual_leave_request_list.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//////                                        .withDialogAnimation(true)
//////                                        .setTitle("Error Message")
//////                                        .setDescription(value2)
//////                                        .setPositiveText("OK")
//////                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//////                                            @Override
//////                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////////                                                    input_date5.setText("");
//////
////////                                                moveTaskToBack(true);
////////                                                android.os.Process.killProcess(android.os.Process.myPid());
////////                                                System.exit(1);
//////                                            }
//////                                        });
//////                                dialogHeader_3.show();
//////                            }
//////                        });
////
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                } else {
////
////
////                    try {
////                        JSONObject jsonObject = new JSONObject(responses);
//////                        final String value = jsonObject.getString("employeeNo");
////                        final String valu2 = jsonObject.getString("employeeName");
////                        final String value3 = jsonObject.getString("department");
////                        final String value4 = jsonObject.getString("designation");
////
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////
////                                textEmployeeName.setText(valu2);
////                                textEmployeeDateOfEntry.setText(value4);
////                                textDepartment.setText(value3);
////
////                            }
////                        });
////
//////                        Log.d("Value3", "name is" + valu2 + "employee number is " + value + "designtion is" + value3 + "department is" + value4);
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        });
////    }
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
////    private void jsonrequest() {
////
////
////        circularProgressBar.setVisibility(View.VISIBLE);
////        circularProgressBar.start();
////
//////        pDialog = Utilss.showSweetLoader(LeaveManualApprover.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
////        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
////            @Override
////            public void onResponse(JSONArray response) {
////
////
////                if(response.length()== 0)
////                {
////                    layNoInternet.setVisibility(View.VISIBLE);
////                }
////
////                else
////                {
////                    layNoInternet.setVisibility(View.GONE);
////                }
////
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run()
////                    {
//////                        Utilss.hideSweetLoader(pDialog);
////
////                        circularProgressBar.setVisibility(View.GONE);
////                        circularProgressBar.stop();
////                    }
////                });
////
////
////                JSONObject jsonObject = null;
////
////                for (int i = 0; i < response.length(); i++) {
////
////
////                    try {
////                        jsonObject = response.getJSONObject(i);
////                        PendingLeaveManual anime = new PendingLeaveManual();
////
////                        anime.setRequestID(jsonObject.getString("requestID"));
////                        anime.setLeaveCode(jsonObject.getString("leaveCode"));
////                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
////                        anime.setEmployeeName(jsonObject.getString("empName"));
////                        anime.setReason(jsonObject.getString("reason"));
////                        anime.setStartDate(jsonObject.getString("startDate"));
////                        anime.setEndDate(jsonObject.getString("endDate"));
////                        anime.setNoofdays(jsonObject.getString("noOfDays"));
////                        anime.setStatus(jsonObject.getString("status"));
////
////                        lstAnime.add(anime);
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                }
////
////                setuprecyclerview(lstAnime);
////
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run()
////                    {
//////                        Utilss.hideSweetLoader(pDialog);
////
////                        circularProgressBar.setVisibility(View.GONE);
////                        circularProgressBar.stop();
////                    }
////                });
////
////                Log.d("Error12", error.toString());
////            }
////        })
////
////        {
////            @Override
////            public Map<String, String> getHeaders() {
////                Map<String, String> params = new HashMap<>();
////                params.put("Authorization", " Bearer " + LoginActivity.value);
////                params.put("Cache-Control", "no-cache");
////                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
////                Log.d("AuthKey", LoginActivity.value);
////
////                return params;
////            }
////        };
////
////
////        requestQueue = Volley.newRequestQueue(fragment_manual_leave_request_list.this);
////        requestQueue.add(request);
////
////
////    }
////
////    private void setuprecyclerview(List<PendingLeaveManual> lstAnime) {
////
////
////       myadapter = new RecyclerViewAdapter5(fragment_manual_leave_request_list.this, lstAnime);
////        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_leave_request_list.this));
////        recyclerView.setAdapter(myadapter);
////
////        myadapter.notifyDataSetChanged();
////        recyclerView.scheduleLayoutAnimation();
////
////    }
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
//}
