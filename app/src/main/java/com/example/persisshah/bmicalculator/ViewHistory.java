package com.example.persisshah.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewHistory extends AppCompatActivity {

    TextView tvViewHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        tvViewHistory = (TextView)findViewById(R.id.tvViewHistory);

        DatabaseHandler dbH = new DatabaseHandler(this);
        String ab =dbH.getHistory();
        tvViewHistory.setText(ab);



    }
}
