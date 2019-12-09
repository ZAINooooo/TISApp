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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.tisapp.Login.LoginActivity;
import com.example.tisapp.Profile.Profile;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.util.ArrayList;
import java.util.List;

public class OfflineDataListAdapter extends RecyclerView.Adapter<OfflineDataListAdapter.ViewHolder> {

    private Context mContext ;
    MaterialStyledDialog.Builder dialogHeader_3;
    Offline offline;

    public ArrayList<Offline> offlineDataList;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView offline_data_list_item_date;
        public TextView offline_data_list_item_type;
        Button delete_entry;
        public TextView offline_data_list_item_status,offline_data_list_item_status3;
        public TextView offline_data_list_item_reason;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            offline_data_list_item_date = itemView.findViewById(R.id.offline_data_list_item_date4);
            delete_entry = itemView.findViewById(R.id.delete_entry);

            offline_data_list_item_type = itemView.findViewById(R.id.offline_data_list_item_type2);
            offline_data_list_item_status = itemView.findViewById(R.id.offline_data_list_item_status2);
            offline_data_list_item_status3 = itemView.findViewById(R.id.offline_data_list_item_status3);

//            offline_data_list_item_reason = itemView.findViewById(R.id.offline_data_list_item_reason);
        }
    }

    public OfflineDataListAdapter(Context mContext) {
        this.mContext = mContext;
//        this.mData = mData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_offline_data_list_manual, viewGroup, false);
        return new ViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Offline offline = offlineDataList.get(i);
        viewHolder.offline_data_list_item_date.setText(offline.getDate());
        viewHolder.offline_data_list_item_type.setText(offline.getType());
        viewHolder.offline_data_list_item_status.setText(offline.getStatus());
        viewHolder.offline_data_list_item_status3.setText(offline.getReason());
        viewHolder.delete_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dialogHeader_3 = new MaterialStyledDialog.Builder(mContext)
                        .setHeaderDrawable(R.drawable.header)
                        .setIcon(new IconicsDrawable(mContext).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                        .withDialogAnimation(true)
                        .setTitle("Error Message")
                        .setDescription("Do you want to delete this entry ?")
                        .setPositiveText("OK")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                                int f = offline.getID();
                                Log.d("IdIs " , ""+f );


                                databaseHelper.delete(offline.getID());
                                offlineDataList.remove(i);
                                notifyDataSetChanged();

                                mContext.startActivity(new Intent(mContext, Home_Screen.class));



//                            _passwordText.setText("");
//                                        _loginButton.setEnabled(true);
                            }
                        }).setNegativeText("No");
                dialogHeader_3.show();




            }
        });
    }
    public void addOfflineDataList(ArrayList<Offline> offlineDataList){
        this.offlineDataList = offlineDataList;
    }

    @Override
    public int getItemCount() {
        return this.offlineDataList.size();
    }
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//         offline = offlineDataList.get(i);
//
////        viewHolder.offline_data_list_item_date.setText(offline.);
//
////if (offline.getDate().equals(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN))
////{
////    viewHolder.offline_data_list_item_date.setText(offline.getDate());
////}
////
////else
////{
////    viewHolder.offline_data_list_item_date.setText(offline.getDate());
////}
//
//
//        viewHolder.offline_data_list_item_date.setText(offline.getDate());
//        viewHolder.offline_data_list_item_type.setText(offline.getType());
//        viewHolder.offline_data_list_item_status.setText(offline.getStatus());
//        viewHolder.offline_data_list_item_status3.setText(offline.getReason());
//
//
//
//
//
//
//
//
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
//                                com.example.tisapp.DatabaseHelper databaseHelper = new com.example.tisapp.DatabaseHelper(mContext);
//
//                                int f = offline.getID();
//                                Log.d("IdIs " , ""+f );
//
//                                databaseHelper.delete(offline.getID());
//                                offlineDataList.remove(i);
//                                notifyDataSetChanged();
//
//                                mContext.startActivity(new Intent(mContext , Home_Screen.class));
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
//
//
//
//
////        viewHolder.offline_data_list_item_reason.setText(offline.getReason());
//    }
//    public void addOfflineDataList(ArrayList<Offline> offlineDataList){
//        this.offlineDataList = offlineDataList;
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.offlineDataList.size();
//    }
}
