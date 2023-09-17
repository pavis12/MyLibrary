package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class SeeAllBooks extends AppCompatActivity {

    private RecyclerView seeallbooksrecyclerView;
    private sabRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seeallbooksrecyclerView=findViewById(R.id.seeallbooksrecyclerView);
        adapter=new sabRecyclerViewAdapter(this,"allbooks");
        seeallbooksrecyclerView.setAdapter(adapter);
        seeallbooksrecyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter.setBooks(Utils.getInstance(this).getAllbooks());

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}