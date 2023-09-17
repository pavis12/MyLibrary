package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class wantoread extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantoread);

        RecyclerView wtrrecview=findViewById(R.id.wtrrecview);

        sabRecyclerViewAdapter adapt= new sabRecyclerViewAdapter(this,"wanttoread");
        wtrrecview.setAdapter(adapt);
        wtrrecview.setLayoutManager(new LinearLayoutManager(this));

        adapt.setBooks(Utils.getInstance(this).getWanttoraedbooks());
    }
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}