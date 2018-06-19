package com.creator.anchuinse.abilitybuilder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.PowerTypes.Power;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowersetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the powers of a single powerset

    int powerset_number;

    Context context;
    ArrayList<Power> powers;

    public PowersetAdapter(Context context, int powerset_number, ArrayList<Power> powers){
        this.context = context;
        this.powerset_number = powerset_number;
        this.powers = powers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.power_row,parent,false);
        ViewHolder item = new ViewHolder(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //important one to change how things are viewed along with ViewHolder object
        ((ViewHolder)holder).textView.setText(powers.get(position).getName());                                 //set items and such to their place in the ViewHolder

        ((ViewHolder) holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use this to change what happens when things are clicked

                Intent intent = new Intent(context, PowerActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("powerset_number",powerset_number);
                extras.putInt("power_number",position);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        if(position%2 == 1){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.recycler_color1));
        }
    }

    @Override
    public int getItemCount() {
        return powers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //use this to change how things are viewed at a later date along with onBindViewHolder
        TextView textView;                                                                          //assign items and such to the ViewHolder
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.power_item);
            parentLayout = itemView.findViewById(R.id.power_row_layout);
        }
    }
}
