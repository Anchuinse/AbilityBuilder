package com.creator.anchuinse.abilitybuilder;

import android.content.SharedPreferences;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MasterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Powerset> powersets;                                              //make sure to initiate lists used in RecyclerView right away
    SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        loadData();
        saveData(); //make into a button later

        initiateRecyclerView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
        saveData();
        resetRecyclerView();
    }

    private void saveData(){
        data = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(powersets);
        editor.putString("data",json);
        editor.apply();
    }

    private void loadData(){
        data = getSharedPreferences("data",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = data.getString("data",null);
        Type type = new TypeToken<ArrayList<Powerset>>() {}.getType();
        powersets = gson.fromJson(json,type);

        if (powersets == null) {
            powersets = new ArrayList<Powerset>();
            powersets.add(Powerset.examplePowerset());
            Toast.makeText(this, "generating examples", Toast.LENGTH_SHORT).show();
        }
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.master_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MasterAdapter(this,powersets));                           //passes the Items to the adapter
    }

    private void resetRecyclerView() {
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MasterAdapter(this,powersets));
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
