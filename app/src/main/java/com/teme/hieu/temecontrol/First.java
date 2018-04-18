package com.teme.hieu.temecontrol;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class First extends AppCompatActivity {
    ImageView lamboo;
    Animation fade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Anh xa
        lamboo = findViewById(R.id.imgLambo);
        fade = AnimationUtils.loadAnimation(First.this,R.anim.fade);

        final Intent go = new Intent(First.this,Second.class);

        CountDownTimer countDownTimer = new CountDownTimer(3200,3100) {
            @Override
            public void onTick(long millisUntilFinished) {
                lamboo.startAnimation(fade);

            }

            @Override
            public void onFinish() {
                  startActivity(go);
            }
        }.start();




    }



}
