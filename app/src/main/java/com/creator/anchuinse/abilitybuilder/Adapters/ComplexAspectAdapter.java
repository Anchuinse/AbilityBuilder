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

import com.creator.anchuinse.abilitybuilder.Activities.AspectActivity;
import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.R;

import java.util.ArrayList;

/**
 * Created by Matt on 6/21/18.
 */

public class ComplexAspectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the sub_aspects of a single power

    int powerset_number;
    int power_number;
    int aspect_number;

    Context context;
    ArrayList<Aspect> sub_aspects;

    public ComplexAspectAdapter(Context context, int powerset_number, int power_number, int aspect_number, ArrayList<Aspect> aspects){
        this.context = context;
        this.powerset_number = powerset_number;
        this.power_number = power_number;
        this.aspect_number = aspect_number;
        this.sub_aspects = aspects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.aspect_row,parent,false);
        ComplexAspectAdapter.ViewHolder item = new ComplexAspectAdapter.ViewHolder(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //important one to change how things are viewed along with ViewHolder object
        ((ViewHolder)holder).textView.setText(sub_aspects.get(position).getName());
        ((ViewHolder)holder).selected.setText(sub_aspects.get(position).getSelected().getName() + "  ");
        ((ViewHolder)holder).cost.setText(Integer.toString(sub_aspects.get(position).getCost()) + " ");

        //----------

        if(position%2 == 1){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.recycler_color1));
        }

        //----------

        ((ViewHolder)holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use this to make changes, possible redundant version of this deleted from above
                Intent intent = new Intent(context, AspectActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("powerset_number",powerset_number);
                extras.putInt("power_number",power_number);
                extras.putInt("aspect_number",aspect_number);
                extras.putInt("sub_aspect_number",position);
                extras.putBoolean("is_complex",true);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sub_aspects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //use this to change how things are viewed at a later date along with onBindViewHolder
        TextView textView;                                                                          //assign items and such to the ViewHolder
        TextView selected;
        TextView cost;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.aspect_item);
            selected = itemView.findViewById(R.id.selected_display);
            cost = itemView.findViewById(R.id.cost_display);
            parentLayout = itemView.findViewById(R.id.aspect_row_layout);
        }
    }
}