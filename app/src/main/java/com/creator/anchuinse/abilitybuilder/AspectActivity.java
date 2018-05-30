package com.creator.anchuinse.abilitybuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.Pieces.PiecePart;

import java.util.ArrayList;

/**
 * Created by Matt on 5/29/18.
 */

public class AspectActivity extends AppCompatActivity {

    ArrayList<PiecePart> pieces = new ArrayList<PiecePart>();
    ArrayList<Button> buttons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);

        Aspect example = Aspect.Physical_Delay_Time();

        for (int i = 0; i < example.getPiece_parts().size(); i++){
            pieces.add(example.getPiece_parts().get(i));
        }

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

        for (int i = 0; i < buttons.size(); i++) {
            if (i < pieces.size()) {
                buttons.get(i).setVisibility(View.VISIBLE);
                buttons.get(i).setText(pieces.get(i).getName() + ": " + Integer.toString(pieces.get(i).getCost()));
            }
            else {
                buttons.get(i).setVisibility(View.INVISIBLE);
            }
        }

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
            }
        });
    }
}
