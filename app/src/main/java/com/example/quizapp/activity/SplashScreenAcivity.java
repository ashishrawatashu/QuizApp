package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quizapp.Methods;
import com.example.quizapp.R;

public class SplashScreenAcivity extends AppCompatActivity {
    Animation animation;
    ImageView splash_logo;
    Methods methods;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_acivity);
        sharedPreferences = this.getSharedPreferences("DataBase", 0);

        splash_logo = (ImageView) findViewById(R.id.splashlogo);

        animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        splash_logo.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((sharedPreferences.getString("isLoggedIn","").equals("true"))){
                    Intent intent = new Intent(SplashScreenAcivity.this, MenuActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashScreenAcivity.this, SignInActivity.class);
                    startActivity(intent);
                    SharedPreferences sharedPreferences= SplashScreenAcivity.this.getSharedPreferences("DataBase", 0);
                    editor = sharedPreferences.edit();
                    editor.putString("timeInQuiz", "30000");
                    editor.putString("quizLevel", "1");
                    editor.putString("scoreEachQuestion", "5");
                    editor.commit();
                }

            }
        }, 2500);
    }
}