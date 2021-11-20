package com.example.madt1116;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvContent;
    ListView listContent;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvContent = findViewById(R.id.tvContent);
        this.listContent = findViewById(R.id.listContent);
    }

    public void onBtnDownloadClick(View view) {
        this.tvContent.setText(Constants.load_str);
        new DataLoader(){
            @Override
            public void onPostExecute(ArrayList<String> result)
            {
                adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, result);
                tvContent.setText(Constants.loading_results);
                listContent.setAdapter(adapter);
            }
        }.execute(Constants.nominals);
    }
}