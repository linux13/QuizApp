package com.m.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categories extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    public static int idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idd=1;
                    Intent intent=new Intent(Categories.this,MainActivity.class);
                  //  intent.putExtra("Id","1");
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idd=2;
                Intent intent=new Intent(Categories.this,MainActivity.class);
               // intent.putExtra("Id","2");
                startActivity(intent);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                idd=3;
                Intent intent=new Intent(Categories.this,MainActivity.class);
              //  intent.putExtra("Id","3");
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idd=4;
                Intent intent=new Intent(Categories.this,MainActivity.class);
               // intent.putExtra("Id","4");
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return super.onOptionsItemSelected(item);

        }
        else if(id==R.id.about){
            Dialogue dialogue = new Dialogue();
            dialogue.show(getSupportFragmentManager(),"About");
        }
        else if(id==R.id.Exit){
           moveTaskToBack(true);
           android.os.Process.killProcess(android.os.Process.myPid());
           System.exit(1);
        }


        return true;
    }

}
