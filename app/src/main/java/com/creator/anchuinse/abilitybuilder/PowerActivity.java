package com.creator.anchuinse.abilitybuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.PowerTypes.PhysicalPower;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerActivity extends AppCompatActivity {

    RecyclerView recyclerView;                                              //make sure to initiate lists used in RecyclerView right away
    ArrayList<PieceCategory> categories = new ArrayList<PieceCategory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        PhysicalPower example = new PhysicalPower();

        for (int i=0; i < example.getCategories().size(); i++){
            categories.add(example.getCategories().get(i));
        }

        getIncomingIntent();

        initiateRecyclerView();
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.power_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowerAdapter(this,categories));                           //passes the Items to the adapter
    }

    private void getIncomingIntent(){
        //.hasExtra("name") is a boolean to see if the intent had extras with the label "name"
        if(getIntent().hasExtra("power_name")){
            String name = getIntent().getStringExtra("power_name");

            setDescription(name);
        }
        if(getIntent().hasExtra("power_cost")){
            int cost = getIntent().getIntExtra("power_cost",999);

            setCurrentCost(cost);
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