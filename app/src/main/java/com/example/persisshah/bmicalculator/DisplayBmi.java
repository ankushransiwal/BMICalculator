package com.example.persisshah.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class DisplayBmi extends AppCompatActivity {
    Button btnSave, btnShare , btnBack;
    TextView tvResult, tvRange1,tvRange2, tvRange3,tvRange4;
    SharedPreferences sp1;
    String msg;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bmi);


        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShare = (Button) findViewById(R.id.btnShare);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvRange1 = (TextView) findViewById(R.id.tvRange1);
        tvRange2 = (TextView) findViewById(R.id.tvRange2);
        tvRange3 = (TextView) findViewById(R.id.tvRange3);
        tvRange4 = (TextView) findViewById(R.id.tvRange4);
        sp1 = getSharedPreferences("MyP1",MODE_PRIVATE);

        Intent i = getIntent();
        final Double bmi1 = i.getDoubleExtra("bmi",0.0);


        if (bmi1<18.5)
        {
            msg = "Under Weight";
            tvRange1.setTextColor(Color.parseColor("#ff0000"));
        }
        else if (bmi1>=18.5 && bmi1<25)
        {
            msg = "Normal";
            tvRange2.setTextColor(Color.parseColor("#ff0000"));
        }
        else if (bmi1>=25 && bmi1<30)
        {
            msg = "Over Weight";
            tvRange3.setTextColor(Color.parseColor("#ff0000"));
        }
        else if (bmi1>=30)
        {
            msg = "Obese";
            tvRange4.setTextColor(Color.parseColor("#ff0000"));
        }

        String Result = "Your BMI is "+bmi1+" You are "+msg;
        tvResult.setText(Result);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new DatabaseHandler(getApplicationContext()).addHistory(new Date()+" "+ "My BMI is "+bmi1+"Result: "+msg);

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = sp1.getString("name","");
                String phone = sp1.getString("phone","");
                String age = sp1.getString("age","");
                String data = "Name: "+name+"\n Phone: "+phone+"\n Age: "+age+"\n Your BMI: "+bmi1;
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,data);
                startActivity(i);

            }
        });


    }

}
