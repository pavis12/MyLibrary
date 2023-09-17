package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class alredyreadbooks extends AppCompatActivity {


    private RecyclerView arbrecview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alredyreadbooks);
        arbrecview=findViewById(R.id.arbrecview);

        sabRecyclerViewAdapter adapt= new sabRecyclerViewAdapter(this,"alreadyread");
        arbrecview.setAdapter(adapt);
        arbrecview.setLayoutManager(new LinearLayoutManager(this));

        adapt.setBooks(Utils.getInstance(this).getAlreadyreadbooks());



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}