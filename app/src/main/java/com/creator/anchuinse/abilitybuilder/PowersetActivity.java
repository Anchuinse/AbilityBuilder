package com.creator.anchuinse.abilitybuilder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.MasterData;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.PowerTypes.PhysicalPower;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowersetActivity extends AppCompatActivity {

    RecyclerView recyclerView;                                             //make sure to initiate lists used in RecyclerView right away
    ArrayList<Power> powers = new ArrayList<Power>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerset);

        getIncomingIntent();

        initiateRecyclerView();
    }

    private void initiateRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.powerset_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowersetAdapter(this,powers));                           //passes the Items to the adapter specified (Make sure it's the right one)
    }

    private void getIncomingIntent(){
        //.hasExtra("name") is a boolean to see if the intent had extras with the label "name"
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //MODIFY THIS PLACE AFTER PERSISTENCE
        assert extras != null;
        Powerset sent = extras.getParcelable("powerset");

        if(extras.containsKey("powerset")){
            Toast.makeText(this, "powerset", Toast.LENGTH_SHORT).show();
            powers.addAll(sent.getPowers());
            setDescription(sent.getDescription());
        }

    }

    private void setDescription(String wanted_description){

        EditText description = findViewById(R.id.powerset_description);
        description.setText(wanted_description);
    }

}
