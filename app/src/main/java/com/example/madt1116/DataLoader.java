package com.example.madt1116;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class DataLoader extends AsyncTask<String, Void, ArrayList<String>> {

    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        try {
            for(int i = 0; i< params.length;i++) {
                stringArrayList.add(DataManager.getRateFromECB(params[i]));
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return stringArrayList;
        }
        return stringArrayList;
    }
}