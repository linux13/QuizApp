package com.m.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
        private ProgressBar progressBar;
        private  int ij;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_screen);



        progressBar=(ProgressBar) findViewById(R.id.progressbar);

        Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                chng();

            }
        });
        t.start();


    }
    public void chng() {

        for (ij = 10; ij <=100; ij+=10) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(ij);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Intent intent =new Intent(WelcomeScreen.this,Categories.class);
        startActivity(intent);
        finish();
    }
}
