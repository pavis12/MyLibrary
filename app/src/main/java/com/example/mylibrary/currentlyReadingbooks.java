package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class currentlyReadingbooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_readingbooks);

        RecyclerView crv=findViewById(R.id.crv);
        sabRecyclerViewAdapter adapt= new sabRecyclerViewAdapter(this,"currentlyreading");
        crv.setAdapter(adapt);
        crv.setLayoutManager(new LinearLayoutManager(this));

        adapt.setBooks(Utils.getInstance(this).getCurrentlyreadingbooks());
    }

    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}