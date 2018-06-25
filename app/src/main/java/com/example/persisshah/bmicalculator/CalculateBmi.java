package com.example.persisshah.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class CalculateBmi extends AppCompatActivity {


    Spinner spnFeet, spnInch;
    Button btnHistory, btnCalculate;
    EditText etWeight;
    int Inch1, Feet1;
    TextView tvWelcome;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
        spnFeet = (Spinner) findViewById(R.id.spnFeet);
        spnInch = (Spinner) findViewById(R.id.spnInch);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        etWeight = (EditText) findViewById(R.id.etWeight);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);


        sp1 = getSharedPreferences("MyP1", MODE_PRIVATE);
        String name = sp1.getString("name", "");
        String msg = "WELCOME " + name;
        tvWelcome.setText(msg);


        final ArrayList<Integer> Feet = new ArrayList<Integer>();
        Feet.add(1);
        Feet.add(2);
        Feet.add(3);
        Feet.add(4);
        Feet.add(5);
        Feet.add(6);

        final ArrayList<Integer> Inch = new ArrayList<Integer>();
        Inch.add(0);
        Inch.add(1);
        Inch.add(2);
        Inch.add(3);
        Inch.add(4);
        Inch.add(5);
        Inch.add(6);
        Inch.add(7);
        Inch.add(8);
        Inch.add(9);
        Inch.add(10);
        Inch.add(11);


        ArrayAdapter<Integer> FeetAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Feet);
        ArrayAdapter<Integer> InchAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Inch);
        spnFeet.setAdapter(FeetAdapter);
        spnInch.setAdapter(InchAdapter);

        spnFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dataFeet = adapterView.getItemAtPosition(i).toString();
                Feet1 = Integer.parseInt(dataFeet);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spnInch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dataInch = adapterView.getItemAtPosition(i).toString();
                Inch1 = Integer.parseInt(dataInch);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalculateBmi.this, ViewHistory.class);
                startActivity(i);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Weight = etWeight.getText().toString();
                int Weight1 = Integer.parseInt(Weight);

                if (Weight.length() == 0) {
                    etWeight.setError("Weight is Empty");
                    etWeight.requestFocus();
                    return;
                }
                double fr = Feet1*0.3048;
                double ir = Inch1*0.0254;
                double w = Weight1;
                double res = fr+ir;
                double bmi = w / (res * res);
                Toast.makeText(CalculateBmi.this, "BMI Recorded", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CalculateBmi.this, DisplayBmi.class);
                i.putExtra("bmi", bmi);
                startActivity(i);


            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        if (item.getItemId() == R.id.about)

        {
            Toast.makeText(this, "App by Persis", Toast.LENGTH_SHORT).show();
            //Snackbar.make(findViewById(android.R.id.content), "App by Persis", Snackbar.lENGTH_LONG).show();
        }

        if (item.getItemId() == R.id.website)
        {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https:"+"//www.google.com"));
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }



    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this Application?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.setTitle("exit");
        a.show();
    }



}

