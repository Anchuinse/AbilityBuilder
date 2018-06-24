package com.creator.anchuinse.abilitybuilder.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Adapters.MasterAdapter;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.R;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.master_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_powerset:
                Toast.makeText(this, "add powerset clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.master_help:
                Toast.makeText(this, "master help clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
