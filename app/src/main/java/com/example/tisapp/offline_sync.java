package com.example.tisapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;


public class offline_sync extends AppCompatActivity {
    RecyclerView offline_data_list;
    View view;
    public ArrayList<Offline> offlineDataList;
    private OfflineDataListAdapter offlineDataListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_offline_sync);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        offlineDataList = new ArrayList<>();
        offline_data_list = findViewById(R.id.offline_data_list);


        offlineDataListAdapter = new OfflineDataListAdapter(offline_sync.this);
        offlineDataListAdapter.addOfflineDataList(offlineDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(offline_sync.this);
        offline_data_list.setLayoutManager(mLayoutManager);
        offline_data_list.setItemAnimator(new DefaultItemAnimator());
        offline_data_list.setAdapter(offlineDataListAdapter);

        loadRemainingData();

    }



    public void loadRemainingData(){
        OfflineDataListAdapter offlineDataListAdapter = new OfflineDataListAdapter(offline_sync.this);
        DatabaseHelper dbHelper  = new DatabaseHelper(offline_sync.this);
        Cursor timeRequestOfflineData = dbHelper.getData();
        if (timeRequestOfflineData.moveToFirst()){
            do{
                Offline offline = new Offline();
                offline.setID(timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL1)));






                if (DatabaseHelper.COL10.equals(DatabaseHelper.TYPE_MANUAl_TIME_IN))
                {
                    offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL2)));
                }

                else
                {
                    offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL4)));
                }



//                offline.setDate(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2)));
                offline.setStatus(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL11)));
                offline.setReason(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL6)));
                offline.setType(timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(DatabaseHelper.COL10)));
                offlineDataList.add(offline);
            }while(timeRequestOfflineData.moveToNext());
        }
        timeRequestOfflineData.close();
        offlineDataListAdapter.addOfflineDataList(offlineDataList);
        offlineDataListAdapter.notifyDataSetChanged();
    }


//    public class sync_with_server extends AsyncTask<Void,Void,Void>{
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            com.example.tisapp.DatabaseHelper dbHelper  = new com.example.tisapp.DatabaseHelper(view.getContext());
//            final boolean request_result = true;
//            Cursor timeRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_REQUEST);
//            Log.e("timeRequestOffline","COUNT >>>>> "+timeRequestOfflineData.getColumnCount());
//            if (timeRequestOfflineData.moveToFirst()){
//                do{
//                    //TODO Make Server Manual Time Request Here
//                    int ID = timeRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1));
//                    String COL2 = timeRequestOfflineData.getString(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2));
//                    Log.e("timeRequestOffline","data>>>> "+COL2);
//                }while(timeRequestOfflineData.moveToNext());
//            }
//            timeRequestOfflineData.close();
//
//            Cursor timeInRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_IN);
//            Log.e("timeInRequestOffline","COUNT >>>>> "+timeRequestOfflineData.getColumnCount());
//            if (timeInRequestOfflineData.moveToFirst()){
//                do{
//                    //TODO Make Server Manual Time In Request Here
//                    int ID = timeInRequestOfflineData.getInt(timeRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1));
//                    String COL2 = timeInRequestOfflineData.getString(timeInRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2));
//                    Log.e("timeInRequestOffline","data>>>> "+COL2);
//                }while(timeInRequestOfflineData.moveToNext());
//            }
//            timeInRequestOfflineData.close();
//
//            Cursor timeOutRequestOfflineData = dbHelper.getFilteredOfflineData(com.example.tisapp.DatabaseHelper.TYPE_MANUAl_TIME_OUT);
//            Log.e("timeOutRequestOffline","COUNT >>>>> "+timeRequestOfflineData.getColumnCount());
//            if (timeOutRequestOfflineData != null && timeOutRequestOfflineData.moveToFirst()){
//                do{
//                    //TODO Make Server Manual Time Out Request Here
//                    int ID = timeOutRequestOfflineData.getInt(timeOutRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL1));
//                    String COL2 = timeOutRequestOfflineData.getString(timeOutRequestOfflineData.getColumnIndex(com.example.tisapp.DatabaseHelper.COL2));
//                    Log.e("timeOutRequestOffline","data>>>> "+COL2);
//                }while(timeOutRequestOfflineData.moveToNext());
//            }
//            timeOutRequestOfflineData.close();
//            return null;
//        }
//    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//    }
}
