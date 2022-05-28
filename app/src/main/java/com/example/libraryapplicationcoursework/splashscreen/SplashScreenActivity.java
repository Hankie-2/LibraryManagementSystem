package com.example.libraryapplicationcoursework.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.authentication.LoginActivity;
import com.example.libraryapplicationcoursework.database.AppDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Timer timer;
    private int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppDatabase database = AppDatabase.getInstance(this);
        database.userDAO().deleteAllUsers();


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i<500){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                } else{
                    timer.cancel();
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },0,5);


    }
}