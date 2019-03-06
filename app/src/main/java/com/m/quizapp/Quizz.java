package com.m.quizapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static com.m.quizapp.Categories.idd;

public class Quizz extends AppCompatActivity {

    Set<Integer> originall = new HashSet<>();
    Iterator<Integer> itr = originall.iterator();
    int rnd[] = new int[25];
    Boolean aBoolean = false;
    String voice, shafi;
    Context context;
    EditText edt;
    Button skip;
    TextToSpeech mtts;
    RadioButton op1, op2, op3, op4, op5;
    TextView qus, sco, timee;
    Button ans;
    RadioGroup radioGroup;
    Random r = new Random();
    Questions questions = new Questions();
    private int ln = questions.correctAns.length;
    private long timeleft = 240000;
    int i = 0, sc = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_quizz);

        aBoolean = false;
        //time display
        // idd=(int)Integer.parseInt(getIntent().getStringExtra("Id"));

        shafi = "";
        showtime();

        timeleft = 90000;

        i = 0;
        qus = findViewById(R.id.question);
        ans = findViewById(R.id.confirm);
        skip = findViewById(R.id.skip);
        radioGroup = findViewById(R.id.readiogroup);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        op5 = findViewById(R.id.op5);
        sco = findViewById(R.id.scoree);
        timee = findViewById(R.id.timee);

        randomfgenerator();

        showQuestion(rnd[i], idd);
/*
        if(questions.correctAns[i].equals(questions.op3(i))){
            Toast.makeText(this, "c "+questions.aQuestion[i], Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "w "+questions.op3(i), Toast.LENGTH_SHORT).show();
*/
        //Toast.makeText(this, questions.getQuestion(0), Toast.LENGTH_SHORT).show();/*

        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conf();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skiee();
            }
        });


    }

    private void showtime() {

        countDownTimer = new CountDownTimer(timeleft, 1000) {


            @Override
            public void onTick(long l) {

                timeleft = l;
                int min = (int) timeleft / 60000;
                int sec = (int) timeleft % 60000 / 1000;
                String tt = "";
                tt += min;
                tt += ":";
                if (sec < 10) tt += "0";
                tt += sec;
                timee.setText(tt);
            }

            @Override
            public void onFinish() {
                // aBoolean=true;
                if (mtts != null) {
                    mtts.stop();
                    mtts.shutdown();
                }
                Toast.makeText(Quizz.this, " Time is Up!! " + " \nFinal score is " + sc, Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                Intent intent = new Intent(Quizz.this, Categories.class);
                startActivity(intent);
            }
        }.start();
    }


    private void showQuestion(int i, int idd) {
        //  Toast.makeText(this, "questions", Toast.LENGTH_SHORT).show();
        qus.setText(questions.getQuestion(i, idd));
        op1.setText(questions.op1(i, idd));
        op2.setText(questions.op2(i, idd));
        op3.setText(questions.op3(i, idd));
        op4.setText(questions.op4(i, idd));
        op5.setText(questions.op5(i, idd));
        shafi = questions.corctans(i, idd);
        voice = "";
        voice += questions.getQuestion(i, idd);
        voice += " , ";
        voice += questions.op1(i, idd);
        voice += " , ";
        voice += questions.op2(i, idd);
        voice += " , ";
        voice += questions.op3(i, idd);
        voice += " , ";
        voice += questions.op4(i, idd);
        voice += " , ";
        voice += questions.op5(i, idd);

        click();
    }

    private boolean chk(RadioButton bt, String s) {
        String string = (String) bt.getText().toString();
        if (string.equals(s)) {
            Toast.makeText(this, "Wohoo!! Correct answer", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "ohho Wrong!!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void click() {
        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int res = mtts.setLanguage(Locale.US);
                    if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(Quizz.this, "Not Supported", Toast.LENGTH_SHORT).show();
                    } else {
                        speak();
                    }
                } else
                    Toast.makeText(Quizz.this, "Initialization Failed", Toast.LENGTH_SHORT).show();

            }
        });
        // Log.e("key","This is working");


    }

    public void speak() {

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);

        mtts.setPitch(1.1f);
        mtts.setSpeechRate(.8f);
        mtts.speak(voice, TextToSpeech.QUEUE_FLUSH, null, null);
        // Log.e("mey","This is joking");

    }

    @Override
    protected void onDestroy() {
        if (mtts != null) {

            mtts.stop();
            mtts.shutdown();
        }

        super.onDestroy();
    }

    public void conf() {
        if (mtts != null) {
            mtts.stop();
            mtts.shutdown();
        }

        int ide = radioGroup.getCheckedRadioButtonId();
        if (ide != -1) {
            RadioButton btn = findViewById(ide);
            boolean bl = chk(btn, shafi);

            if (bl == true)
                ++sc;
            sco.setText("Score : " + sc);
            radioGroup.clearCheck();
            if (i == 19) {
                if (mtts != null) {
                    mtts.stop();
                    mtts.shutdown();
                }
                i = 0;
                Toast.makeText(Quizz.this, "Final Score is " + sc, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Quizz.this, Categories.class);
                startActivity(intent);
            } else {
                if (i == 18) ans.setText("Finish");

                showQuestion(rnd[++i], idd);
            }

            //ontakia nisi
        } else {
            Toast.makeText(Quizz.this, "Please Select an answer", Toast.LENGTH_SHORT).show();
        }

    }

    public void skiee() {
        radioGroup.clearCheck();
        if (mtts != null) {
            mtts.stop();
            mtts.shutdown();
        }
        if (i == 18) ans.setText("Finish");
        if (i <= 18)
            showQuestion(rnd[++i], idd);
        else {
            // Toast.makeText(context, "Question finished", Toast.LENGTH_SHORT).show();
            Toast.makeText(Quizz.this, "Final Score is " + sc, Toast.LENGTH_SHORT).show();
            i = 0;
            Intent intent = new Intent(Quizz.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void randomfgenerator() {


        while (originall.size() != 20) {
            originall.add(r.nextInt(20));
            //  Log.e("ahaa",""+originall.size());
        }
        int j = 0;
        String s = "";
        for (Integer stock : originall) {
            rnd[j] = stock;
             Log.e("keyy ",""+rnd[j]);
            ++j;

        }


    }

}
