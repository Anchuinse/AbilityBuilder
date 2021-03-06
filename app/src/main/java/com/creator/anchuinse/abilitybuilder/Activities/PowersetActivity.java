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

import com.creator.anchuinse.abilitybuilder.Adapters.PowersetAdapter;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;
import com.creator.anchuinse.abilitybuilder.Dialogs.PowersetNameDialog;
import com.creator.anchuinse.abilitybuilder.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowersetActivity extends AppCompatActivity implements PowersetNameDialog.PowersetNameDialogListener {

    RecyclerView recyclerView;                                             //make sure to initiate lists used in RecyclerView right away
    ArrayList<Powerset> powersets = new ArrayList<Powerset>();
    int powerset_number;
    ArrayList<Power> powers = new ArrayList<Power>();
    SharedPreferences data;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerset);
        loadData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getIncomingIntent();

        getSupportActionBar().setTitle(powersets.get(powerset_number).getName());

        setDescription(powersets.get(powerset_number).getDescription());
        setCosts(powersets.get(powerset_number).getCurrentCost(),powersets.get(powerset_number).getMaxCost());
        initiateRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        resetRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.powerset_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_power:
                Toast.makeText(this, "add power clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.powerset_help:
                Toast.makeText(this, "powerset help clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.powerset_save:
                editText = findViewById(R.id.powerset_description);
                String description = editText.getText().toString();
                powersets.get(powerset_number).setDescription(description);
                saveData();
                Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
                loadData();
                return true;
            case R.id.change_powerset_name:
                openDialog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applyText(String input_name) {
        String new_name = input_name;
        powersets.get(powerset_number).setName(new_name);
        saveData();
        loadData();
        getSupportActionBar().setTitle(powersets.get(powerset_number).getName());
    }

    public void openDialog() {
        PowersetNameDialog dialog = new PowersetNameDialog();
        dialog.show(getSupportFragmentManager(),"powerset name dialog");
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

        powers = powersets.get(powerset_number).getPowers();
        powersets.get(powerset_number).refreshCurrentCost();
        setCosts(powersets.get(powerset_number).getCurrentCost(),powersets.get(powerset_number).getMaxCost());
    }

    private void initiateRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.powerset_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowersetAdapter(this, powerset_number, powers));                           //passes the Items to the adapter specified (Make sure it's the right one)
    }

    private void resetRecyclerView() {
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PowersetAdapter(this,powerset_number, powers));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void getIncomingIntent(){
        //.hasExtra("name") is a boolean to see if the intent had extras with the label "name"
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        assert extras != null;

        if(extras.containsKey("powerset_number")){
            //Toast.makeText(this, "powerset", Toast.LENGTH_SHORT).show();
            powerset_number = extras.getInt("powerset_number");
            powers.addAll(powersets.get(powerset_number).getPowers());
            setDescription(powersets.get(powerset_number).getDescription());
        }

    }

    private void setDescription(String input_description){

        EditText description = findViewById(R.id.powerset_description);
        description.setText(input_description);
    }

    private void setCosts(int new_current, int new_total) {
        TextView cost = findViewById(R.id.powerset_costs);
        cost.setText(String.valueOf(new_current) + "/" + String.valueOf(new_total));
    }

}
