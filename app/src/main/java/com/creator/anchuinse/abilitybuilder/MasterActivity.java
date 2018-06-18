package com.creator.anchuinse.abilitybuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creator.anchuinse.abilitybuilder.Pieces.MasterData;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;

import java.util.ArrayList;

public class MasterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Powerset> powersets;                                              //make sure to initiate lists used in RecyclerView right away

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        powersets = new ArrayList<Powerset>();
        powersets.add(Powerset.examplePowerset());

        initiateRecyclerView();
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.master_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MasterAdapter(this,powersets));                           //passes the Items to the adapter
    }
}
