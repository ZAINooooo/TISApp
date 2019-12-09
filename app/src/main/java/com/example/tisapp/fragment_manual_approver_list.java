package com.example.tisapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.OkHttpClient;


public class fragment_manual_approver_list extends BaseActivity {


//    CardView card;
//    OkHttpClient client;
//
//    private final String JSON_URL = LoginActivity.urls+"api/Manual/GetPendingManualRequests_Employee";
//    private JsonArrayRequest request;
//    private RequestQueue requestQueue;
//    private List<PendingManualRequests_Employee> lstAnime;
//    private RecyclerView recyclerView;
//    String value2;
//    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;


    JSONObject jsonObject ;
    private final String JSON_URL = "api/Manual/GetPendingManualRequests_Approver";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<PendingApproverRequests_Employee> lstAnime;
    private List<PendingApproverRequests_Employee> lstAnime2;

    private RecyclerView recyclerView;
    String value2;
    OkHttpClient client;
    TextView textEmployeeName,textEmployeeDateOfEntry,textDepartment;
    LinearLayout layNoInternet;


    private SwipeRefreshLayout mSwipeRefreshLayout;

    RecyclerViewAdapter3 myadapter;
    ProgressView circularProgressBar;




//    textEmployeeName.setText(valu2);
//                                textEmployeeDateOfEntry.setText(value4);
//                                textDepartment.setText(value3);



    MaterialStyledDialog.Builder dialogHeader_3;




//    public fragment_manual_approver_list() {
//        // Required empty public constructor
//    }


//    public static fragment_manual_approver_list newInstance(String param1, String param2) {
//        fragment_manual_approver_list fragment = new fragment_manual_approver_list();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

//    public static Fragment newInstance(int position) {
//        fragment_manual_approver_list f = new fragment_manual_approver_list();
//        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        return f;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_fragment_manual_approver_list);

        client = new OkHttpClient();

        circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
        circularProgressBar.setVisibility(View.GONE);



        lstAnime = new ArrayList<>();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReport);


//        recyclerView = view.findViewById(R.id.recyclerViewReport);


        layNoInternet = findViewById(R.id.layNoInternet);
//        alter_txt.setVisibility(View.GONE);


//        textEmployeeName = (TextView) view.findViewById(R.id.textEmployeeName) ;
//        textEmployeeDateOfEntry = (TextView) view.findViewById(R.id.textEmployeeDateOfEntry) ;
//        textDepartment = (TextView) view.findViewById(R.id.textDepartment) ;

//                                textEmployeeDateOfEntry.setText(value4);
//                                textDepartment.setText(value3);



//        GetProfileParams();
        jsonrequest();




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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.activity_manual_list, container, false);
//
//        View view= inflater.inflate(R.layout.fragment_fragment_manual_approver_list, container, false);
//
//
//
//
//
////        card= (CardView) view.findViewById(R.id.card);
////
////        card.setCardBackgroundColor(getResources().getColor(R.color.black));
//        return view;
//    }

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
                        PendingApproverRequests_Employee anime = new PendingApproverRequests_Employee();

                        anime.setRequestID(jsonObject.getString("requestID"));
                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
                        anime.setEmployeeName(jsonObject.getString("employeeName"));
                        anime.setAttendanceDate(jsonObject.getString("attendanceDate"));
                        anime.setDateIn(jsonObject.getString("dateIn"));
                        anime.setTimeIn(jsonObject.getString("timeIn"));
                        anime.setDateOut(jsonObject.getString("dateOut"));
                        anime.setTimeOut(jsonObject.getString("timeOut"));
                        anime.setStatus(jsonObject.getString("status"));
                        anime.setReason(jsonObject.getString("reason"));
                        anime.setCustomerInfo(jsonObject.getString("customerInfo"));




                        anime.setApprovalStatus1(jsonObject.getString("approvalStatus1"));
                        anime.setRemarks1(jsonObject.getString("remarks1"));
                        anime.setApprovalStatus2(jsonObject.getString("approvalStatus2"));
                        anime.setRemarks2(jsonObject.getString("remarks2"));
                        anime.setApprovalStatus3(jsonObject.getString("approvalStatus3"));
                        anime.setRemarks3(jsonObject.getString("remarks3"));
                        anime.setApprovalStatus4(jsonObject.getString("approvalStatus4"));
                        anime.setRemarks4(jsonObject.getString("remarks4"));
                        anime.setApprovalDate(jsonObject.getString("approvalDate"));
                        anime.setUpdateDateTime(jsonObject.getString("updateDateTime"));
                        anime.setPosted(jsonObject.getString("posted"));

                        anime.setXcordinates(jsonObject.getDouble("xCoordinates"));
                        anime.setYcordinates(jsonObject.getDouble("yCoordinates"));

//                        anime.setChkOut(jsonObject.getString("chkOut"));
//                        anime.setGpsCoordinates(jsonObject.getString("gpsCoordinates"));

                        anime.setApproverField(jsonObject.getString("approverField"));

                        lstAnime2.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }



                myadapter = new RecyclerViewAdapter3(fragment_manual_approver_list.this,lstAnime2) ;
                recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_approver_list.this));
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

        requestQueue = Volley.newRequestQueue(fragment_manual_approver_list.this);
        requestQueue.add(request);


        mSwipeRefreshLayout.setRefreshing(false);
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
//                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
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
//                                                                    dialogHeader_3 = new MaterialStyledDialog.Builder(getActivity())
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(getActivity()).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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
//                                }
//                            });
//                        }
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
////                                dialogHeader_3 = new MaterialStyledDialog.Builder(getActivity())
////                                        .setHeaderDrawable(R.drawable.header)
////                                        .setIcon(new IconicsDrawable(getActivity()).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
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


        circularProgressBar.setVisibility(View.VISIBLE);
        circularProgressBar.start();


//        pDialog = Utilss.showSweetLoader(ManualApprover.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
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



                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
//                        Utilss.hideSweetLoader(pDialog);

                        circularProgressBar.setVisibility(View.GONE);
                        circularProgressBar.stop();
                    }
                });


//                if(response.length()== 0)
//
//                {
//
//                    alter_txt.setVisibility(View.VISIBLE);
//                }

//                else
//                {
//                    alter_txt.setVisibility(View.GONE);
//                }



//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });


                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        PendingApproverRequests_Employee anime = new PendingApproverRequests_Employee();

                        anime.setRequestID(jsonObject.getString("requestID"));
                        anime.setEmployeeNo(jsonObject.getString("employeeNo"));
                        anime.setEmployeeName(jsonObject.getString("employeeName"));
                        anime.setAttendanceDate(jsonObject.getString("attendanceDate"));
                        anime.setDateIn(jsonObject.getString("dateIn"));
                        anime.setTimeIn(jsonObject.getString("timeIn"));
                        anime.setDateOut(jsonObject.getString("dateOut"));
                        anime.setTimeOut(jsonObject.getString("timeOut"));
                        anime.setStatus(jsonObject.getString("status"));
                        anime.setReason(jsonObject.getString("reason"));
                        anime.setCustomerInfo(jsonObject.getString("customerInfo"));




                        anime.setApprovalStatus1(jsonObject.getString("approvalStatus1"));
                        anime.setRemarks1(jsonObject.getString("remarks1"));
                        anime.setApprovalStatus2(jsonObject.getString("approvalStatus2"));
                        anime.setRemarks2(jsonObject.getString("remarks2"));
                        anime.setApprovalStatus3(jsonObject.getString("approvalStatus3"));
                        anime.setRemarks3(jsonObject.getString("remarks3"));
                        anime.setApprovalStatus4(jsonObject.getString("approvalStatus4"));
                        anime.setRemarks4(jsonObject.getString("remarks4"));
                        anime.setApprovalDate(jsonObject.getString("approvalDate"));
                        anime.setUpdateDateTime(jsonObject.getString("updateDateTime"));
                        anime.setPosted(jsonObject.getString("posted"));

                        anime.setXcordinates(jsonObject.getDouble("xCoordinates"));
                        anime.setYcordinates(jsonObject.getDouble("yCoordinates"));

//                        anime.setChkOut(jsonObject.getString("chkOut"));
//                        anime.setGpsCoordinates(jsonObject.getString("gpsCoordinates"));

                        anime.setApproverField(jsonObject.getString("approverField"));

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


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

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


        requestQueue = Volley.newRequestQueue(fragment_manual_approver_list.this);
        requestQueue.add(request);


    }

//    private void setuprecyclerview(List<PendingApproverRequests_Employee> lstAnime) {
//
//
//        RecyclerViewAdapter3 myadapter = new RecyclerViewAdapter3(getActivity(), lstAnime);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(myadapter);
//
//        myadapter.notifyDataSetChanged();
//        recyclerView.scheduleLayoutAnimation();
//
//    }




    private void setuprecyclerview(List<PendingApproverRequests_Employee> lstAnime) {

         myadapter = new RecyclerViewAdapter3(fragment_manual_approver_list.this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment_manual_approver_list.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(fragment_manual_approver_list.this), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
















//package com.example.zain.project;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//public class fragment_manual_approver_list extends Fragment {
//
//
//    public fragment_manual_approver_list() {
//        // Required empty public constructor
//    }
//
//    public static fragment_manual_approver_list newInstance(String param1, String param2) {
//        fragment_manual_approver_list fragment = new fragment_manual_approver_list();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_fragment_manual_approver_list, container, false);
//    }
//}
