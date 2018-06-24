package com.creator.anchuinse.abilitybuilder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creator.anchuinse.abilitybuilder.Pieces.Powerset;
import com.creator.anchuinse.abilitybuilder.Activities.PowersetActivity;
import com.creator.anchuinse.abilitybuilder.R;

import java.util.ArrayList;

/**
 * Created by Matt on 5/22/18.
 */

public class MasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the powersets of a single user

    Context context;
    ArrayList<Powerset> powersets;

    public MasterAdapter(Context context, ArrayList<Powerset> powersets){
        this.context = context;
        this.powersets = powersets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.powerset_row,parent,false);
        ViewHolder item = new ViewHolder(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                                                                                                    //important one to change how things are viewed along with ViewHolder object
        ((ViewHolder)holder).textView.setText(powersets.get(position).getName());                                 //set items and such to their place in the ViewHolder

        ((ViewHolder) holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use this to change what happens when things are clicked

                //MODIFY THIS PLACE AFTER PERSISTENCE
                Intent intent = new Intent(context, PowersetActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("powerset_number", position);                        //attach extra stuff to the intent to specify which item we clicked
                //extras.putParcelable("master",master);
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
        return powersets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
                                                                                                    //use this to change how things are viewed at a later date along with onBindViewHolder
        TextView textView;                                                                          //assign items and such to the ViewHolder
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.powerset_item);
            parentLayout = itemView.findViewById(R.id.powerset_row_layout);
        }
    }
}
