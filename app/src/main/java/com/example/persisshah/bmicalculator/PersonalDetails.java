package com.example.persisshah.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalDetails extends AppCompatActivity {


    EditText etName, etPhone, etAge;
    RadioGroup rgGender;
    TextView tvPersonal;
    Button btnRegister;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etAge = (EditText) findViewById(R.id.etAge);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        sp1 = getSharedPreferences("MyP1", MODE_PRIVATE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String age = etAge.getText().toString();

                if (name.length()== 0)
                {
                    Toast.makeText(PersonalDetails.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;

                }
                if (phone.length()!= 10)
                {
                    Toast.makeText(PersonalDetails.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;

                }
                if (age.length()== 0)
                {
                    Toast.makeText(PersonalDetails.this, "Invalid Age", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;

                }


                int s1 = rgGender.getCheckedRadioButtonId();
                RadioButton rgGender = (RadioButton) findViewById(s1);

                SharedPreferences.Editor Editor = sp1.edit();
                Editor.putString("name", name);
                Editor.putString("age",age);
                Editor.putString("phone", phone);
                Editor.putInt("gender",s1);
                Editor.commit();


                Toast.makeText(PersonalDetails.this, "Data Saved", Toast.LENGTH_SHORT).show();

                etName.setText("");
                etAge.setText("");
                etPhone.setText("");

                Intent i = new Intent(PersonalDetails.this,CalculateBmi.class);

                startActivity(i);
                finish();
            }
        });
    }
}
