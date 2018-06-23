package com.creator.anchuinse.abilitybuilder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Matt on 6/21/18.
 */

public class ComplexAspectActivity extends AppCompatActivity {

    SharedPreferences data;
    ArrayList<Powerset> powersets = new ArrayList<Powerset>();
    int powerset_number;
    int power_number;
    int aspect_number;

    ArrayList<Aspect> sub_aspects = new ArrayList<Aspect>();
    RecyclerView recyclerView;
    Aspect displayed_aspect;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadData();

        processIntent();

        getSupportActionBar().setTitle(displayed_aspect.getName());

        initiateRecyclerView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
        resetRecyclerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        processIntent();
        loadData();

        resetRecyclerView();
        displayed_aspect.refreshCost();
        getWindow().getDecorView().findViewById(R.id.complex_cost).invalidate();
        setDisplayedCost(displayed_aspect.getCost());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.aspect_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.aspect_help:
                Toast.makeText(this, "aspect help clicked", Toast.LENGTH_SHORT).show();
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
    }

    private void resetRecyclerView() {
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ComplexAspectAdapter(this,powerset_number, power_number, aspect_number, sub_aspects));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void processIntent() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        assert extras != null;

        //from PowerActivity

        if(extras.containsKey("powerset_number")) {
            powerset_number = extras.getInt("powerset_number");
            if (extras.containsKey("power_number")) {
                power_number = extras.getInt("power_number");
                if (extras.containsKey("aspect_number")) {
                    aspect_number = extras.getInt("aspect_number");
                    displayed_aspect = powersets.get(powerset_number).getPowers().get(power_number).getAspects().get(aspect_number);
                    sub_aspects = displayed_aspect.getSubAspects();

                    setDisplayedCost(displayed_aspect.getCost());
                }
            }
        }

        //from AspectActivity

        if(getIntent().hasExtra("aspect")){
            Aspect changed = getIntent().getParcelableExtra("aspect");
            for (int i = 0; i < displayed_aspect.getSubAspects().size(); i++){
                if (displayed_aspect.getSubAspects().get(i).getName().equals(changed.getName())){
                    displayed_aspect.getSubAspects().get(i).setSelected(changed.getSelected());
                }
            }
            displayed_aspect.refreshCost();
            setDisplayedCost(displayed_aspect.getCost());

            powersets.get(powerset_number).getPowers().get(power_number).getAspects().get(aspect_number).overwriteAspectWith(displayed_aspect);
            powersets.get(powerset_number).getPowers().get(power_number).refreshCurrentCost();
            saveData();
        }
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.complex_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ComplexAspectAdapter(this, powerset_number, power_number, aspect_number, sub_aspects));                           //passes the Items to the adapter
    }

    private void setDisplayedCost(int new_cost){

        TextView current_cost = findViewById(R.id.complex_cost);
        String display = Integer.toString(new_cost);
        current_cost.setText("Cost: " + display);
    }
}
