package com.example.tisapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.util.ArrayList;
import java.util.List;

public class LeaveOfflineDataListAdapter extends RecyclerView.Adapter<LeaveOfflineDataListAdapter.ViewHolder> {

    private Context mContext ;
    MaterialStyledDialog.Builder dialogHeader_3;
    LeaveOffline offline;

    public ArrayList<LeaveOffline> offlineDataList;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView offline_data_list_item_date2;
        public TextView offline_data_list_item_date4;
        Button delete_entry;
        public TextView offline_data_list_item_type2,offline_data_list_item_status2;
        public TextView offline_data_list_item_status3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            offline_data_list_item_date2 = itemView.findViewById(R.id.offline_data_list_item_date2);
            delete_entry = itemView.findViewById(R.id.delete_entry);
            offline_data_list_item_date4 = itemView.findViewById(R.id.offline_data_list_item_date4);
            offline_data_list_item_type2 = itemView.findViewById(R.id.offline_data_list_item_type2);
            offline_data_list_item_status2 = itemView.findViewById(R.id.offline_data_list_item_status2);
            offline_data_list_item_status3 = itemView.findViewById(R.id.offline_data_list_item_status3);
        }
    }

    public LeaveOfflineDataListAdapter(Context mContext) {
        this.mContext = mContext;
//        this.mData = mData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_offline_data_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        offline = offlineDataList.get(i);

//        viewHolder.offline_data_list_item_date.setText(offline.);

//if (offline.getDate().equals(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN))
//{
//    viewHolder.offline_data_list_item_date.setText(offline.getDate());
//}
//
//else
//{
//    viewHolder.offline_data_list_item_date.setText(offline.getDate());
//}


        viewHolder.offline_data_list_item_date2.setText(offline.getFromDate());
        viewHolder.offline_data_list_item_date4.setText(offline.getToDate());
        viewHolder.offline_data_list_item_type2.setText(offline.getLeaveCode());
        viewHolder.offline_data_list_item_status2.setText(offline.getSyncStatus());
        viewHolder.offline_data_list_item_status3.setText(offline.getRemarks());




//        viewHolder.delete_entry.setText("DELETE ENTRY "+offline.getID());



        viewHolder.delete_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                                int Id = offlineDataList.get(i).getID();
                                Toast.makeText(mContext,"Delete "+Id,Toast.LENGTH_LONG).show();
                                Log.d("IdToDelete" , ""+Id);
                                offlineDataList.remove(i);
                                databaseHelper.deleteleave(Id);
                                notifyDataSetChanged();


//                                mContext.startActivity(new Intent(mContext, Home_Screen.class));


                            }
                        }).setNegativeText("Not now");
                dialogHeader_3.show();


            }


        });







//        viewHolder.delete_entry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//
//
//                dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
//                        .setHeaderDrawable(R.drawable.header)
//                        .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                        .withDialogAnimation(true)
//                        .setTitle("Do You Want To Delete?")
//                        .setPositiveText("OK")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
//                            {
////                                com.example.tisapp.DatabaseHelper databaseHelper = new com.example.tisapp.DatabaseHelper(mContext);
////                                databaseHelper.
////                                (offline.getID());
////                                offlineDataList.remove(i);
////                                notifyDataSetChanged();
//
//
//
////                                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
////                                int f = offline.getID();
////                                Log.d("IdIs " , ""+f );
////
////                                databaseHelper.deleteleave(offline.getID());
////                                offlineDataList.remove(i);
////                                notifyDataSetChanged();
//
//                                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
//                                Toast.makeText(mContext,"Delete "+offline.getID(),Toast.LENGTH_LONG).show();
//                                offlineDataList.remove(i);
//                                databaseHelper.deleteleave(offline.getID());
//                                notifyDataSetChanged();
//
//
//                                mContext.startActivity(new Intent(mContext, Home_Screen.class));
//
////                                mContext.startActivity(new Intent(mContext , Home_Screen.class));
//
//                            }
//                        }).setNegativeText("Not now");
//                dialogHeader_3.show();
//
//
//            }
//
//
//
//        });




//        viewHolder.offline_data_list_item_reason.setText(offline.getReason());
    }
    public void addOfflineDataList(ArrayList<LeaveOffline> offlineDataList){
        this.offlineDataList = offlineDataList;
    }

    @Override
    public int getItemCount() {
        return this.offlineDataList.size();
    }
}
