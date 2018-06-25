package com.example.persisshah.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    TextView tvAppName;
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAppName = (TextView)findViewById(R.id.tvAppName);
        ivSplash = (ImageView)findViewById(R.id.ivSplash);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a1);
        ivSplash.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(),PersonalDetails.class);
                startActivity(i);
                finish();
            }
        }).start();

    }
}
