package com.creator.anchuinse.abilitybuilder;

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
import android.widget.Toast;

import com.creator.anchuinse.abilitybuilder.Pieces.ComplexAspect;
import com.creator.anchuinse.abilitybuilder.Pieces.Aspect;
import com.creator.anchuinse.abilitybuilder.Pieces.PiecePart;

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the sub_aspects of a single power

    int powerset_number;
    int power_number;

    private static final int ASPECT_TYPE_SIMPLE = 145;
    private static final int ASPECT_TYPE_COMPLEX = 541;

    Context context;
    ArrayList<Aspect> aspects;

    public PowerAdapter(Context context, int powerset_number, int power_number, ArrayList<Aspect> aspects){
        this.context = context;
        this.powerset_number = powerset_number;
        this.power_number = power_number;
        this.aspects = aspects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == ASPECT_TYPE_COMPLEX){
            View row = inflater.inflate(R.layout.aspect_row,parent,false);
            return new ComplexViewHolder(row);
        }
        else{
            View row = inflater.inflate(R.layout.aspect_row,parent,false);
            return new SimpleViewHolder(row);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //important one to change how things are viewed along with ViewHolder object
        if (holder instanceof ComplexViewHolder){
            ((ComplexViewHolder)holder).textView.setText(aspects.get(position).getName());

            ((ComplexViewHolder)holder).selected.setText(aspects.get(position).getSelected().getName() + "  ");
            ((ComplexViewHolder)holder).cost.setText(String.valueOf(aspects.get(position).getCost()) + " ");

            ((ComplexViewHolder)holder).parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //use this to make changes, possible redundant version of this deleted from above
                    Intent intent = new Intent(context, ComplexAspectActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("powerset_number",powerset_number);
                    extras.putInt("power_number",power_number);
                    extras.putInt("aspect_number",position);
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            });
        }
        else{
            ((SimpleViewHolder)holder).textView.setText(aspects.get(position).getName());

            ((SimpleViewHolder)holder).selected.setText(aspects.get(position).getSelected().getName() + "  ");
            ((SimpleViewHolder)holder).cost.setText(Integer.toString(aspects.get(position).getCost()) + " ");

            ((SimpleViewHolder)holder).parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //use this to make changes, possible redundant version of this deleted from above
                    Intent intent = new Intent(context, AspectActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("powerset_number",powerset_number);
                    extras.putInt("power_number",power_number);
                    extras.putInt("aspect_number",position);
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            });
        }

        //----------

        if(position%2 == 1){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.recycler_color1));
        }
    }

    @Override
    public int getItemCount() {
        return aspects.size();
    }

    @Override   //this is where the problem is
    public int getItemViewType(int position) {
        if(aspects.get(position).isComplex() == true){
            return ASPECT_TYPE_COMPLEX;
        }
        else {
            return ASPECT_TYPE_SIMPLE;
        }
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder{
        TextView textView;                                                                          //assign items and such to the ViewHolder
        TextView selected;
        TextView cost;
        LinearLayout parentLayout;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.aspect_item);
            selected = itemView.findViewById(R.id.selected_display);
            cost = itemView.findViewById(R.id.cost_display);
            parentLayout = itemView.findViewById(R.id.aspect_row_layout);
        }
    }

    private static class ComplexViewHolder extends RecyclerView.ViewHolder{
        TextView textView;                                                                          //assign items and such to the ViewHolder
        TextView selected;
        TextView cost;
        LinearLayout parentLayout;

        public ComplexViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.aspect_item);
            selected = itemView.findViewById(R.id.selected_display);
            cost = itemView.findViewById(R.id.cost_display);
            parentLayout = itemView.findViewById(R.id.aspect_row_layout);
        }
    }
}

