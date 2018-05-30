package com.creator.anchuinse.abilitybuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

/**
 * Created by Matt on 5/29/18.
 */

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final Button one = findViewById(R.id.button1);
        final Button two = findViewById(R.id.button2);
        final Button three = findViewById(R.id.button3);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(false);
                two.setEnabled(true);
                three.setEnabled(true);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(false);
                three.setEnabled(true);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(false);
            }
        });
    }
}
