package com.creator.anchuinse.abilitybuilder.Activities;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Adapters.PowerAdapter;
import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.Dialogs.PowerNameDialog;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;
import com.creator.anchuinse.abilitybuilder.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerActivity extends AppCompatActivity implements PowerNameDialog.PowerNameDialogListener {

    SharedPreferences data;
    ArrayList<Powerset> powersets = new ArrayList<Powerset>();
    int powerset_number;
    int power_number;

    RecyclerView recyclerView;
    EditText editText;
    Power current_power;
    ArrayList<Aspect> aspects = new ArrayList<Aspect>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadData();

        processIntent();

        getSupportActionBar().setTitle(current_power.getName());

        setDisplayedDescription(powersets.get(powerset_number).getPowers().get(power_number).getDescription());
        initiateRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        resetRecyclerView();
        saveData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        processIntent();
        loadData();

        resetRecyclerView();
        getWindow().getDecorView().findViewById(R.id.power_cost).invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.power_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.power_help:
                Toast.makeText(this, "power help clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.power_save:
                editText = findViewById(R.id.power_description);
                String description = editText.getText().toString();
                current_power.setDescription(description);
                powersets.get(powerset_number).getPowers().get(power_number).overwritePowerWith(current_power);
                saveData();
                loadData();
                return true;
            case R.id.change_power_name:
                openDialog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applyText(String input_name) {
        String new_name = input_name;
        powersets.get(powerset_number).getPowers().get(power_number).setName(new_name);
        saveData();
        loadData();
        getSupportActionBar().setTitle(current_power.getName());
    }

    public void openDialog() {
        PowerNameDialog dialog = new PowerNameDialog();
        dialog.show(getSupportFragmentManager(),"power name dialog");
    }

    private void saveData() {
        data = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(powersets);
        editor.putString("data",json);
        editor.apply();
    }

    private void loadData() {
        data = getSharedPreferences("data",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = data.getString("data",null);
        Type type = new TypeToken<ArrayList<Powerset>>() {}.getType();
        powersets = gson.fromJson(json,type);

        aspects = powersets.get(powerset_number).getPowers().get(power_number).getAspects();
        current_power = powersets.get(powerset_number).getPowers().get(power_number);
        current_power.refreshCurrentCost();
        powersets.get(powerset_number).getPowers().get(power_number).overwritePowerWith(current_power);
        powersets.get(powerset_number).refreshCurrentCost();
        setDisplayedCurrentCost(current_power.getCurrent_cost());
    }

    private void initiateRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.power_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowerAdapter(this, powerset_number, power_number, aspects));                           //passes the Items to the adapter
    }

    private void resetRecyclerView() {
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowerAdapter(this,powerset_number, power_number, aspects));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void processIntent() {
        //.hasExtra("name") is a boolean to see if the intent had extras with the label "name"
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        assert extras != null;


        //from PowersetActivity


        if(extras.containsKey("powerset_number")){
            powerset_number = extras.getInt("powerset_number");
            if(extras.containsKey("power_number")){
                power_number = extras.getInt("power_number");
                current_power = powersets.get(powerset_number).getPowers().get(power_number);

                String name = current_power.getName();
                setDisplayedDescription(name);

                int cost = current_power.getCurrent_cost();
                setDisplayedCurrentCost(cost);

                aspects = current_power.getAspects();
            }
        }

        //from AspectActivity


        if(getIntent().hasExtra("aspect")){
            Aspect changed = getIntent().getParcelableExtra("aspect");
            for (int i = 0; i < current_power.getAspects().size(); i++){
                if (current_power.getAspects().get(i).getName().equals(changed.getName())){
                    current_power.getAspects().get(i).setSelected(changed.getSelected());
                }
            }
            powersets.get(powerset_number).getPowers().get(power_number).overwritePowerWith(current_power);
            powersets.get(powerset_number).refreshCurrentCost();
            saveData();
            loadData();
            Toast.makeText(this, String.valueOf(powersets.get(powerset_number).getCurrentCost()), Toast.LENGTH_SHORT).show();
        }
    }

    private void setDisplayedDescription(String wanted_description) {

        EditText description = findViewById(R.id.power_description);
        description.setText(wanted_description);
    }

    private void setDisplayedCurrentCost(int new_current_cost) {

        TextView current_cost = findViewById(R.id.power_cost);
        String display = Integer.toString(new_current_cost);
        current_cost.setText("Cost: " + display);
    }
}