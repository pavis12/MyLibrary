package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnseeallbooks,btncurent,btnalready,btnwishlist,btnabout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnseeallbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SeeAllBooks.class);
                startActivity(intent);

            }
        });

        btnalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,alredyreadbooks.class);
                startActivity(intent);
            }
        });
        btncurent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,currentlyReadingbooks.class);
                startActivity(intent);
            }
        });
        btnwishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FavBooks.class);
                startActivity(intent);
            }
        });
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,alredyreadbooks.class);
                startActivity(intent);
            }
        });*/
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and developed by pavis");

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,Webactivity.class);
                        intent.putExtra("url","https://google.com/");
                        startActivity(intent);

                    }
                });
                builder.create().show();
            }
        });

        Utils.getInstance(this);
    }

    private void initViews() {
        btnseeallbooks=findViewById(R.id.btnseeallbooks);
        btncurent=findViewById(R.id.btncurent);
        btnalready=findViewById(R.id.btnalready);
        btnwishlist=findViewById(R.id.btnwishlist);
        btnabout=findViewById(R.id.btnabout);
    }
}