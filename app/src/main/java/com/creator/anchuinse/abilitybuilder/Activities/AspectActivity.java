package com.creator.anchuinse.abilitybuilder.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.Pieces.PiecePart;
import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Matt on 5/29/18.
 */

public class AspectActivity extends AppCompatActivity {

    SharedPreferences data;
    ArrayList<Powerset> powersets = new ArrayList<Powerset>();
    int powerset_number;
    int power_number;
    int aspect_number;
    int sub_aspect_number;
    boolean is_complex = false;

    int current_cost;
    int max_cost;
    PiecePart selected;

    ArrayList<PiecePart> pieces = new ArrayList<PiecePart>();
    ArrayList<Button> buttons = new ArrayList<Button>();
    Aspect displayed_aspect = new Aspect("example");
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);
        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadData();

        getIncomingIntent();

        getSupportActionBar().setTitle(displayed_aspect.getName());

        //--------

        final Button one = findViewById(R.id.button1);
        buttons.add(one);
        final Button two = findViewById(R.id.button2);
        buttons.add(two);
        final Button three = findViewById(R.id.button3);
        buttons.add(three);
        final Button four = findViewById(R.id.button4);
        buttons.add(four);
        final Button five = findViewById(R.id.button5);
        buttons.add(five);
        final Button six = findViewById(R.id.button6);
        buttons.add(six);
        final Button seven = findViewById(R.id.button7);
        buttons.add(seven);
        final Button eight = findViewById(R.id.button8);
        buttons.add(eight);

        //--------

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(0).getCost()-selected.getCost()) {
                    one.setEnabled(false);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(0));

                    createIntent();
                }
                else {
                    Toast.makeText(context, String.valueOf(max_cost) + String.valueOf(current_cost) + String.valueOf(pieces.get(0).getCost()) + String.valueOf(selected.getCost()), Toast.LENGTH_SHORT).show();
                }

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(1).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(false);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(1));
                    createIntent();
                }
                else {
                    Toast.makeText(context, String.valueOf(max_cost) + String.valueOf(current_cost) + String.valueOf(pieces.get(1).getCost()) + String.valueOf(selected.getCost()), Toast.LENGTH_SHORT).show();
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(2).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(false);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(2));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(3).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(false);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(3));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(4).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(false);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(4));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(5).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(false);
                    seven.setEnabled(true);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(5));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(6).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(false);
                    eight.setEnabled(true);

                    displayed_aspect.setSelected(pieces.get(6));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (max_cost-current_cost >= pieces.get(7).getCost()-selected.getCost()) {
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                    four.setEnabled(true);
                    five.setEnabled(true);
                    six.setEnabled(true);
                    seven.setEnabled(true);
                    eight.setEnabled(false);

                    displayed_aspect.setSelected(pieces.get(7));
                    createIntent();
                }
                else {
                    Toast.makeText(context, "Exceeds Max Cost", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setButtons();
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

    private void setButtons(){
        for (int i = 0; i < buttons.size(); i++) {
            if (i < pieces.size()) {
                buttons.get(i).setVisibility(View.VISIBLE);
                buttons.get(i).setText(pieces.get(i).getName() + ": " + Integer.toString(pieces.get(i).getCost()));
                if (displayed_aspect.getPiece_parts().get(i).getName().equals(displayed_aspect.getSelected().getName())){
                    buttons.get(i).setEnabled(false);
                }
            }
            else {
                buttons.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    private void getIncomingIntent() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        assert extras != null;

        if(extras.containsKey("is_complex")) {
            is_complex = true;
        }

        if(extras.containsKey("powerset_number")) {
            powerset_number = extras.getInt("powerset_number");
            if (extras.containsKey("power_number")) {
                power_number = extras.getInt("power_number");
                if (extras.containsKey("aspect_number")) {
                    aspect_number = extras.getInt("aspect_number");
                    if (extras.containsKey("sub_aspect_number")) {
                        sub_aspect_number = extras.getInt("sub_aspect_number");

                        displayed_aspect = powersets.get(powerset_number).getPowers().get(power_number).getAspects().get(aspect_number).getSubAspects().get(sub_aspect_number);
                        current_cost = powersets.get(powerset_number).getCurrentCost();
                        max_cost = powersets.get(powerset_number).getMaxCost();
                        selected = displayed_aspect.getSelected();
                        Toast.makeText(context, String.valueOf(current_cost) + " sub", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        displayed_aspect = powersets.get(powerset_number).getPowers().get(power_number).getAspects().get(aspect_number);
                        current_cost = powersets.get(powerset_number).getCurrentCost();
                        max_cost = powersets.get(powerset_number).getMaxCost();
                        selected = displayed_aspect.getSelected();
                        Toast.makeText(context, String.valueOf(current_cost) + " basic", Toast.LENGTH_SHORT).show();
                    }
                    pieces = displayed_aspect.getPiece_parts();
                }
            }
        }
    }

    private void createIntent(){
        if (is_complex == false) {
            Intent intent = new Intent(context, PowerActivity.class);
            intent.putExtra("aspect",displayed_aspect);

            context.startActivity(intent);
        }
        if (is_complex == true) {
            Intent intent = new Intent(context, ComplexAspectActivity.class);
            intent.putExtra("aspect",displayed_aspect);

            context.startActivity(intent);
        }
    }

}
