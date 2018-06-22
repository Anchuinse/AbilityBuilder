package com.creator.anchuinse.abilitybuilder;

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
                one.setEnabled(false);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(0));

                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(false);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(1));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(false);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(2));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(false);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(3));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(false);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(4));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(false);
                seven.setEnabled(true);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(5));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(false);
                eight.setEnabled(true);

                displayed_aspect.setSelected(pieces.get(6));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(false);

                displayed_aspect.setSelected(pieces.get(7));
                Intent intent = new Intent(context, PowerActivity.class);
                intent.putExtra("aspect",displayed_aspect);

                context.startActivity(intent);
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

        if(extras.containsKey("powerset_number")) {
            powerset_number = extras.getInt("powerset_number");
            if (extras.containsKey("power_number")) {
                power_number = extras.getInt("power_number");
                if (extras.containsKey("aspect_number")) {
                    aspect_number = extras.getInt("aspect_number");

                    displayed_aspect = powersets.get(powerset_number).getPowers().get(power_number).getAspects().get(aspect_number);
                    pieces = displayed_aspect.getPiece_parts();
                }
            }
        }
    }

}
