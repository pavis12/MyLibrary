package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_books);

        RecyclerView fb=findViewById(R.id.frv);


        sabRecyclerViewAdapter adapt= new sabRecyclerViewAdapter(this,"favbooks");
        fb.setAdapter(adapt);
        fb.setLayoutManager(new LinearLayoutManager(this));

        adapt.setBooks(Utils.getInstance(this).getFavouritebooks());
    }
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}