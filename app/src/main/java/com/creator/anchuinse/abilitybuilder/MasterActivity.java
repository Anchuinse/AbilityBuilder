package com.creator.anchuinse.abilitybuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MasterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> Items = new ArrayList<String>();                                              //make sure to initiate lists used in RecyclerView right away

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        Items.add("Powerset 1");
        Items.add("Powerset 2");
        Items.add("Powerset 3");
        Items.add("Powerset 4");
        Items.add("Powerset 5");
        Items.add("Powerset 6");
        Items.add("Powerset 7");
        Items.add("Powerset 8");

        initiateRecyclerView();
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.master_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MasterAdapter(this,Items));                           //passes the Items to the adapter
    }
}
