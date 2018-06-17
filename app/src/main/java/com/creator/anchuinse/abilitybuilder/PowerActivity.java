package com.creator.anchuinse.abilitybuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.PowerTypes.PhysicalPower;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerActivity extends AppCompatActivity {

    RecyclerView recyclerView;                                              //make sure to initiate lists used in RecyclerView right away
    ArrayList<Aspect> aspects = new ArrayList<Aspect>();
    Power example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        example = new PhysicalPower();

        for (int i = 0; i < example.getAspects().size(); i++){
            aspects.add(example.getAspects().get(i));
        }

        processIntent();

        initiateRecyclerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        processIntent();

        recyclerView.getAdapter().notifyDataSetChanged();
        example.refreshCurrentCost();
        getWindow().getDecorView().findViewById(R.id.power_cost).invalidate();
        setCurrentCost(example.getCurrent_cost());
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.power_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowerAdapter(this, aspects));                           //passes the Items to the adapter
    }

    private void processIntent(){
        //.hasExtra("name") is a boolean to see if the intent had extras with the label "name"
        if(getIntent().hasExtra("power_name")){
            String name = getIntent().getStringExtra("power_name");

            setDescription(name);
        }
        if(getIntent().hasExtra("power_cost")){
            int cost = getIntent().getIntExtra("power_cost",999);

            setCurrentCost(cost);
        }
        if(getIntent().hasExtra("aspect")){
            Aspect changed = getIntent().getParcelableExtra("aspect");
            for (int i = 0; i < example.getAspects().size(); i++){
                if (example.getAspects().get(i).getName().equals(changed.getName())){
                    example.getAspects().get(i).setSelected(changed.getSelected());
                }
            }
        }
    }

    private void setDescription(String wanted_description){

        EditText description = findViewById(R.id.power_description);
        description.setText(wanted_description);
    }

    private void setCurrentCost(int new_current_cost){

        TextView current_cost = findViewById(R.id.power_cost);
        String display = Integer.toString(new_current_cost);
        current_cost.setText("Cost: " + display);
    }

}