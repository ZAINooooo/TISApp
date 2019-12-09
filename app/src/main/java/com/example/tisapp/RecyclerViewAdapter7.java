package com.example.tisapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.tisapp.Settings.Settings_Activity.urls;
import static com.google.android.gms.internal.zzahn.runOnUiThread;

//import com.example.zain.project.ApproverResultActivity;
//import com.example.zain.project.ManualEntry.RecyclerViewAdapter3;
//import com.example.zain.project.MapsActivity;
//import static com.google.android.gms.internal.zzagz.runOnUiThread;
//import static com.google.android.gms.internal.zzagy.runOnUiThread;
//import static com.google.android.gms.internal.zzahf.runOnUiThread;

public class RecyclerViewAdapter7 extends RecyclerView.Adapter<RecyclerViewAdapter7.MyViewHolder> {

    private Context mContext ;
    public static List<PendingLeaveApprover> mData ;
    String responses;
    MaterialStyledDialog.Builder dialogHeader_3;

    String value2;
    OkHttpClient client;

    String abc;

    protected SweetAlertDialog pDialog;
    View layout;


    public RecyclerViewAdapter7(Context mContext, List<PendingLeaveApprover> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int index = (int) (Math.random() * 3);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_row_item7, parent, false), index);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        if (mData.get(position).getLeaveCode().equals("") || mData.get(position).getLeaveCode().length() < 0) {
            holder.leave_code.setText("N/A");
        } else {
            holder.leave_code.setText(mData.get(position).getLeaveCode());
        }

        if (mData.get(position).getEmployeeName().equals("") || mData.get(position).getEmployeeName().length() < 0) {
            holder.emp_name.setText("N/A");
        } else {
            holder.emp_name.setText(mData.get(position).getEmployeeName());
        }

        if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() < 0) {
            holder.status.setText("N/A");
        } else {
            holder.status.setText(mData.get(position).getStatus());
        }



//        if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() < 0) {
//            holder.reason.setText("N/A");
//        } else {
//            holder.reason.setText(mData.get(position).getReason());
//        }


//        if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() <0)
//        {
//            holder.reason.setText("N/A");
//        }
//
//        else
//        {
//            holder.reason.setText(mData.get(position).getReason());
//        }


        holder.table_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Clicked..!", Toast.LENGTH_SHORT).show();


                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.dialog_change_password8, null);
                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();


                TextView start_date_is = (TextView) layout.findViewById(R.id.no_of_days);
                TextView date_in_is = (TextView) layout.findViewById(R.id.date_in_is);
                TextView date_out_is = (TextView) layout.findViewById(R.id.date_out_is);


                TextView reason_is = (TextView) layout.findViewById(R.id.reason_is);
                TextView leave_code = (TextView) layout.findViewById(R.id.leave_code);
                TextView statuse = (TextView) layout.findViewById(R.id.statuse);


                Button accept_btn = (Button) layout.findViewById(R.id.accept_btn);
                Button deny_btn = (Button) layout.findViewById(R.id.deny_btn);


                if (mData.get(position).getStartDate().equals("") || mData.get(position).getStartDate().length() < 0) {
                    date_in_is.setText("N/A");
                } else {
                    date_in_is.setText(mData.get(position).getStartDate());
                }


                if (mData.get(position).getEndDate().equals("") || mData.get(position).getEndDate().length() < 0) {
                    date_out_is.setText("N/A");
                } else {
                    date_out_is.setText(mData.get(position).getEndDate());
                }


                if (mData.get(position).getNoofdays().equals("") || mData.get(position).getNoofdays().length() < 0) {
                    start_date_is.setText("N/A");
                } else {
                    start_date_is.setText(mData.get(position).getNoofdays());
                }


                if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() < 0) {
                    reason_is.setText("N/A");
                } else {
                    reason_is.setText(mData.get(position).getReason());
                }


                if (mData.get(position).getLeaveCode().equals("") || mData.get(position).getLeaveCode().length() < 0) {
                    leave_code.setText("N/A");
                } else {
                    leave_code.setText(mData.get(position).getLeaveCode());
                }


                if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() < 0) {
                    statuse.setText("N/A");
                } else {
                    statuse.setText(mData.get(position).getStatus());
                }


                AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                aDialog.setView(layout);
                final AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
                ad.show();


                accept_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ad.cancel();

                        pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                        final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout = inflater.inflate(R.layout.prompt4, null);
                        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
                        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
                        Button btnOk = (Button) layout.findViewById(R.id.btnOk);

                        final EditText ed_employee_number = layout.findViewById(R.id.ed_remarks);

                        AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                        aDialog.setView(layout);
                        final AlertDialog ad = aDialog.create();
                        listOfDialogs.add(ad);
                        ad.show();

                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//                            pDialog = Utilss.showSweetLoader(LeaveApproverResultActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");


                                abc = ed_employee_number.getText().toString();


                                if (abc.equals("")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                        }
                                    });

                                    ed_employee_number.setError("Remarks Cant Be Empty..!!");
                                } else {


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Utilss.hideSweetLoader(pDialog);
                                        }
                                    });



//                        api/Leave/AcceptLeaveRequest?RequestID=121&Remarks=Accepted by id3
                                    final Request request = new Request.Builder()
                                            .url(urls + "api/Leave/AcceptLeaveRequest?RequestID=" + mData.get(position).getRequestID() + "&Remarks=" + ed_employee_number.getText().toString())
                                            .get().addHeader("Cache-Control", "no-cache")
                                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                            .addHeader("Postman-Token", "9c139266-f725-49a2-a13b-4f310d93742c")
                                            .build();


                                    client=new OkHttpClient();
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
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pDialog);
                                                }
                                            });


                                            responses = response.body().string();
                                            Log.e("response", "onResponse(): " + responses);


                                            if (response.code()==500)
                                            {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(responses);
                                                    value2 = jsonObject.getString("exceptionMessage");
                                                    Log.d("ValueResponse", value2);


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
//

                                                            dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                    .setHeaderDrawable(R.drawable.header)
                                                                    .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                    .withDialogAnimation(true)
                                                                    .setTitle("Error Message")
                                                                    .setDescription(value2)
                                                                    .setPositiveText("OK")
                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                        @Override
                                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");


                                                                            Intent intent = new Intent(mContext, Home_Screen.class);
                                                                           mContext.startActivity(intent);

                                                                        }
                                                                    });
                                                            dialogHeader_3.show();
                                                        }
                                                    });
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }










                                            }


                                            else if (response.code()==200)
                                            {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

//                                                new TTFancyGifDialog.Builder(mContext)
//                                                        .setTitle("Response Message")
//                                                        .setMessage(responses)
//                                                        .setPositiveBtnBackground("#f4de15")
//                                                        .setPositiveBtnText("OK")
//                                                        .setGifResource(R.drawable.g)
//                                                        .isCancellable(true)
//                                                        .OnPositiveClicked(new TTFancyGifDialogListener() {
//                                                            @Override
//                                                            public void OnClick() {
//
//                                                                Intent intent = new Intent(mContext, Home_Screen.class);
//                                                                LeaveApproverResultActivity.this.startActivity(intent);
//                                                            }
//                                                        })
//                                                        .build();


                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Response Message")
                                                                .setDescription(responses)
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                                                        Intent intent = new Intent(mContext, Home_Screen.class);
                                                                        mContext.startActivity(intent);

                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }


                                            ad.cancel();
                                        }
                                    });

                                }


                            }
                        });


                      btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                ad.cancel();
                            }
                        });
                    }
                });


                deny_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ad.cancel();

                        pDialog = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

//                        final Context mContext = getApplicationContext();
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout = inflater.inflate(R.layout.prompt4, null);
                        final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();
                        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
                        Button btnOk = (Button) layout.findViewById(R.id.btnOk);

                        final EditText ed_employee_number = layout.findViewById(R.id.ed_remarks);

                        AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                        aDialog.setView(layout);
                        final AlertDialog ad = aDialog.create();
                        listOfDialogs.add(ad);
                        ad.show();

                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                abc = ed_employee_number.getText().toString();

                                if (abc.equals("")) {
                                    ed_employee_number.setError("Remarks Cant Be Empty..!!");
                                } else {

                                    final Request request = new Request.Builder()
                                            .url(urls + "api/Leave/DenyLeaveRequest?RequestID=" + mData.get(position).getRequestID() + "&Remarks=" + ed_employee_number.getText().toString())
                                            .get().addHeader("Cache-Control", "no-cache")
                                            .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                            .addHeader("Postman-Token", "9c139266-f725-49a2-a13b-4f310d93742c")
                                            .build();

                                    client=new OkHttpClient();
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
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {

                                            responses = response.body().string();
                                            Log.e("response", "onResponse(): " + responses);




                                            if (response.code()==500)
                                            {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(responses);
                                                    value2 = jsonObject.getString("exceptionMessage");
                                                    Log.d("ValueResponse", value2);


                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
//

                                                            dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                    .setHeaderDrawable(R.drawable.header)
                                                                    .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                    .withDialogAnimation(true)
                                                                    .setTitle("Error Message")
                                                                    .setDescription(value2)
                                                                    .setPositiveText("OK")
                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                        @Override
                                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                    input_date5.setText("");


                                                                            Intent intent = new Intent(mContext, Home_Screen.class);
                                                                            mContext.startActivity(intent);


                                                                        }
                                                                    });
                                                            dialogHeader_3.show();

                                                        }
                                                    });
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                            else if (response.code()==200)
                                            {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                                                .setHeaderDrawable(R.drawable.header)
                                                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                                                .withDialogAnimation(true)
                                                                .setTitle("Response Message")
                                                                .setDescription(responses)
                                                                .setPositiveText("OK")
                                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                    @Override
                                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                                                        Intent intent = new Intent(mContext, Home_Screen.class);
                                                                        mContext.startActivity(intent);

                                                                    }
                                                                });
                                                        dialogHeader_3.show();
                                                    }
                                                });
                                            }

                                            ad.cancel();
                                        }
                                    });
                                }
                            }
                        });

                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                ad.cancel();
                            }
                        });
                    }
                });

            }
        });
    }




//
//
//
//                                android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(mContext);
//                                aDialog.setView(layout);
//                                final android.app.AlertDialog ad = aDialog.create();
////            listOfDialogs.add(ad);
//                                ad.show();
//
//
////                delete_main.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                        ad.cancel();
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Do You Want To Delete?")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
////                                    {
////
////
//////                                        pDialog = Utilss.showSweetLoader(LeaveManualResultActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
////                                        final Request request = new Request.Builder()
////                                                .url(urls+"api/Leave/DeleteLeaveRequest?RequestID=" + mData.get(position).getRequestID())
////                                                .get().addHeader("Postman-Token", "147d359d-85b1-4784-b7ae-a5f91b3e5d87")
////                                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
////                                                .addHeader("Cache-Control", "no-cache")
////                                                .build();
////
////                                        client= new OkHttpClient();
////
////                                        Call call = client.newCall(request);
////                                        call.enqueue(new Callback() {
////                                            @Override
////                                            public void onFailure(Call call, IOException e) {
////
////
//////                                                runOnUiThread(new Runnable() {
//////                                                    @Override
//////                                                    public void run()
//////                                                    {
//////                                                        Utilss.hideSweetLoader(pDialog);
//////                                                    }
//////                                                });
////
////                                                Log.e("HttpService", "onFailure() Request was: " + call);
////                                                e.printStackTrace();
////                                            }
////
////                                            @Override
////                                            public void onResponse(Call call, Response response) throws IOException {
////
////                                                responses = response.body().string();
////                                                Log.e("response", "onResponse(): " + responses);
////
//////                                                runOnUiThread(new Runnable() {
//////                                                    @Override
//////                                                    public void run()
//////                                                    {
//////                                                        Utilss.hideSweetLoader(pDialog);
//////                                                    }
//////                                                });
////
////                                                runOnUiThread(new Runnable() {
////                                                    @Override
////                                                    public void run() {
//////                                                        Toast.makeText(LeaveManualResultActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//////                                                        Intent intent = new Intent(LeaveManualResultActivity.this, Leave_Form.class);
//////                                                        LeaveManualResultActivity.this.startActivity(intent);
////
////
//////                                                        responses = response.body().string();
////                                                        Log.e("response", "onResponse(): " + responses);
////
//////                                                        ad.cancel();
////                                                        Intent intent = new Intent(mContext, Home_Screen.class);
////                                                        mContext.startActivity(intent);
////
////                                                    }
////                                                });
////
////
////                                            }
////                                        });
////
////
////
////
////
////
////                                    }
////                                }).setNegativeText("Not now");
////                        dialogHeader_3.show();
////                    }
////
////
////
////                });
//
//
////                int id = Integer.parseInt(mData.get(position).getRequestID());
////
////                Intent intent = new Intent(mContext,LeaveApproverResultActivity.class);
////                intent.putExtra("requestID", id);
////                intent.putExtra("leaveCode", mData.get(position).getLeaveCode());
////                intent.putExtra("employeeNo", mData.get(position).getEmployeeNo());
////                intent.putExtra("employeeName", mData.get(position).getEmployeeName());
////                intent.putExtra("reason", mData.get(position).getReason());
////                intent.putExtra("startDate", mData.get(position).getStartDate());
////                intent.putExtra("endDate", mData.get(position).getEndDate());
////                intent.putExtra("noofdays", mData.get(position).getNoofdays());
////                intent.putExtra("status", mData.get(position).getStatus());
////
////
////
////                mContext.startActivity(intent);
//
//
//                            }
//                        });
//                    }
//                });



//
//
//
//
//
//
//
//
//
//                        btnCancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run()
//                                    {
////                                        Utilss.hideSweetLoader(pDialog);
//                                    }
//                                });
//
//                                ad.cancel();
//                            }
//                        });
//
//
//
//                    }
//                });
//            }
//        });



    @Override
    public int getItemCount() {
        return mData.size();
    }


    public boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout view;
        private int backgroundIndex = 0;

        ImageView delete;
        TextView emp_name,leave_code,status,reason;
        LinearLayout view_container;
        OkHttpClient client;
        String responses;
        Button google_map;
        RelativeLayout table_one;





        public MyViewHolder(View itemView, int backgroundIndex) {
            super(itemView);



            client = new OkHttpClient();

            this.backgroundIndex = backgroundIndex;
//            view = (ConstraintLayout) itemView.findViewById(R.id.item2);

            emp_name = itemView.findViewById(R.id.emp_name);
            leave_code= itemView.findViewById(R.id.leave_code);
//            reason = itemView.findViewById(R.id.reason);
            table_one= itemView.findViewById(R.id.view_foreground);
            status = itemView.findViewById(R.id.status);
//            reason = itemView.findViewById(R.id.reason);

//            no_of_days = itemView.findViewById(R.id.no_of_days);

        }
    }

}
