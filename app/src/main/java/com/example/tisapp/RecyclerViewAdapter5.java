package com.example.tisapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

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


public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.MyViewHolder> {

    private Context mContext ;
    public static List<PendingLeaveManual> mData ;
    String responses;

    MaterialStyledDialog.Builder dialogHeader_3;
    OkHttpClient client;


    public RecyclerViewAdapter5(Context mContext, List<PendingLeaveManual> mData) {
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


        if (mData.get(position).getLeaveCode().equals("") || mData.get(position).getLeaveCode().length() <0 )
        {
            holder.leave_code.setText("N/A");
        }

        else
        {
            holder.leave_code.setText(mData.get(position).getLeaveCode());
        }

        if (mData.get(position).getEmployeeName().equals("") || mData.get(position).getEmployeeName().length() <0 )
        {
            holder.emp_name.setText("N/A");
        }

        else
        {
            holder.emp_name.setText(mData.get(position).getEmployeeName());
        }

        if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() <0)
        {
            holder.status.setText("N/A");
        }

        else
        {
            holder.status.setText(mData.get(position).getStatus());
        }


//        if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() <0)
//        {
//            holder.reason.setText("N/A");
//        }
//
//        else
//        {
//            holder.reason.setText(mData.get(position).getReason());
//        }




        holder.table_one.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.dialog_change_password3, null );
                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();


                TextView  start_date_is= (TextView) layout.findViewById(R.id.date_in_is);
                TextView  end_in_is= (TextView) layout.findViewById(R.id.date_out_is);
                TextView time_in_is= (TextView) layout.findViewById(R.id.time_in_is);

                Button delete_main = (Button) layout.findViewById(R.id.delete_main);

                TextView no_of_day= (TextView) layout.findViewById(R.id.no_of_days);
//                TextView status_is= (TextView) layout.findViewById(R.id.status_is);
                TextView leaveCode= (TextView) layout.findViewById(R.id.leave_code);
                TextView status= (TextView) layout.findViewById(R.id.statuse);
                TextView reasonss= (TextView) layout.findViewById(R.id.reason_is);




                String start= mData.get(position).getStartDate();
                String end=mData.get(position).getEndDate();
                String no_of_days=mData.get(position).getNoofdays();
                String reasons=mData.get(position).getReason();
                String leaves=mData.get(position).getLeaveCode();
                String statuss=mData.get(position).getStatus();
                String reasonsssss=mData.get(position).getReason();







                if (mData.get(position).getStartDate().equals("") || mData.get(position).getStartDate().length() <0)
                {
                    start_date_is.setText("N/A");
                }
                else
                {
                    start_date_is.setText(start);
                }





                if (mData.get(position).getEndDate().equals("") || mData.get(position).getEndDate().length() <0)
                {
                    end_in_is.setText("N/A");
                }
                else
                {
                    end_in_is.setText(end);
                }



                if (mData.get(position).getNoofdays().equals("") || mData.get(position).getNoofdays().length() <0)
                {
                    no_of_day.setText("N/A");
                }
                else
                {
                    no_of_day.setText(no_of_days);
                }





                if (mData.get(position).getReason().equals("") || mData.get(position).getReason().length() <0)
                {
                    reasonss.setText("N/A");
                }
                else
                {
                    reasonss.setText(reasons);
                }



                if (mData.get(position).getLeaveCode().equals("") || mData.get(position).getLeaveCode().length() <0)
                {
                    leaveCode.setText("N/A");
                }
                else
                {
                    leaveCode.setText(leaves);
                }







                if (mData.get(position).getStatus().equals("") || mData.get(position).getStatus().length() <0)
                {
                    status.setText("N/A");
                }
                else
                {
                    status.setText(statuss);
                }


                AlertDialog.Builder aDialog = new AlertDialog.Builder(mContext);
                aDialog.setView(layout);
                final AlertDialog ad = aDialog.create();
//            listOfDialogs.add(ad);
                ad.show();
















                delete_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

ad.cancel();
                        dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                                .setHeaderDrawable(R.drawable.header)
                                .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                                .withDialogAnimation(true)
                                .setTitle("Do You Want To Delete?")
                                .setPositiveText("OK")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                                    {


//                                        pDialog = Utilss.showSweetLoader(LeaveManualResultActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
                                        final Request request = new Request.Builder()
                                                .url(urls+"api/Leave/DeleteLeaveRequest?RequestID=" + mData.get(position).getRequestID())
                                                .get().addHeader("Postman-Token", "147d359d-85b1-4784-b7ae-a5f91b3e5d87")
                                                .addHeader("Authorization", " Bearer " + LoginActivity.value)
                                                .addHeader("Cache-Control", "no-cache")
                                                .build();

                                        client = new OkHttpClient();
                                        Call call = client.newCall(request);
                                        call.enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {


//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run()
//                                                    {
//                                                        Utilss.hideSweetLoader(pDialog);
//                                                    }
//                                                });

                                                Log.e("HttpService", "onFailure() Request was: " + call);
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {

                                                responses = response.body().string();
                                                Log.e("response", "onResponse(): " + responses);

//                                                runOnUiThread(new Runnable() {
//                                                    @Override
//                                                    public void run()
//                                                    {
//                                                        Utilss.hideSweetLoader(pDialog);
//                                                    }
//                                                });

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        Toast.makeText(LeaveManualResultActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                                                        Intent intent = new Intent(LeaveManualResultActivity.this, Leave_Form.class);
//                                                        LeaveManualResultActivity.this.startActivity(intent);


//                                                        responses = response.body().string();
                                                        Log.e("response", "onResponse(): " + responses);

//                                                        ad.cancel();
                                                        Intent intent = new Intent(mContext, Home_Screen.class);
                                                        mContext.startActivity(intent);

                                                    }
                                                });


                                            }
                                        });






                                    }
                                }).setNegativeText("Not now");
                        dialogHeader_3.show();
                    }



                });

















//                if (mData.get(position).getStartDate().equals("") || mData.get(position).getStartDate().length() <0)
//                {
//                    start_date_is.setText("N/A");
//                }
//                else
//                {
//                    start_date_is.setText(start);
//                }


//                int id = Integer.parseInt(mData
// .get(position).getRequestID());
//
//                Intent intent = new Intent(mContext,LeaveManualResultActivity.class);
//                intent.putExtra("requestID", id);
//                intent.putExtra("leaveCode", mData.get(position).getLeaveCode());
//                intent.putExtra("employeeNo", mData.get(position).getEmployeeNo());
//                intent.putExtra("employeeName", mData.get(position).getEmployeeName());
//                intent.putExtra("reason", mData.get(position).getReason());
//                intent.putExtra("startDate", mData.get(position).getStartDate());
//                intent.putExtra("endDate", mData.get(position).getEndDate());
//                intent.putExtra("noofdays", mData.get(position).getNoofdays());
//                intent.putExtra("status", mData.get(position).getStatus());
//
//
//
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


//    public boolean isNetAvailable()
//    {
//        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null && networkInfo.isConnected();
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView delete;
        TextView emp_name,leave_code,status,no_of_days,reason;
        LinearLayout view_container;
        OkHttpClient client;
        String responses;
//        Button google_map;
        RelativeLayout table_one;
        private ConstraintLayout view;
        private int backgroundIndex = 0;





        public MyViewHolder(View itemView, int backgroundIndex) {
            super(itemView);



            client = new OkHttpClient();
//            this.backgroundIndex = backgroundIndex;


//            view = (ConstraintLayout) itemView.findViewById(R.id.item2);

            emp_name = itemView.findViewById(R.id.emp_name);
            leave_code= itemView.findViewById(R.id.leave_code);
//            reason = itemView.findViewById(R.id.reason);
            table_one= (RelativeLayout) itemView.findViewById(R.id.view_foreground);
            status = itemView.findViewById(R.id.status);
//            no_of_days = itemView.findViewById(R.id.no_of_days);

        }
    }

}
