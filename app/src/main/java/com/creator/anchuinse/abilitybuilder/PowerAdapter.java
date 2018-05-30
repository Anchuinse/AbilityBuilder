package com.creator.anchuinse.abilitybuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.creator.anchuinse.abilitybuilder.R.color.recycler_color1;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the categories of a single power

    Context context;
    ArrayList<PieceCategory> categories;

    public PowerAdapter(Context context, ArrayList<PieceCategory> categories){
        this.context = context;
        this.categories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.category_row,parent,false);
        ViewHolder item = new ViewHolder(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //important one to change how things are viewed along with ViewHolder object
        ((ViewHolder)holder).textView.setText(categories.get(position).getName());

        if(categories.get(position) instanceof ComplexCategory) {
            ((ViewHolder)holder).selected.setText(categories.get(position).getSelected().getName());
        }
        else {
            ((ViewHolder)holder).selected.setText(categories.get(position).getSelected().getName() + ":");
        }
        ((ViewHolder)holder).cost.setText(String.valueOf(categories.get(position).getSelected().getCost()));

        ((ViewHolder) holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use this to change what happens when things are clicked
                Toast.makeText(context, categories.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        //-------

        if(position%2 == 1){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.recycler_color1));
        }

        //-------

        ((ViewHolder)holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "nailed it",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, CategoryActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //use this to change how things are viewed at a later date along with onBindViewHolder
        TextView textView;                                                                          //assign items and such to the ViewHolder
        TextView selected;
        TextView cost;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category_item);
            selected = itemView.findViewById(R.id.selected_display);
            cost = itemView.findViewById(R.id.cost_display);
            parentLayout = itemView.findViewById(R.id.category_row_layout);
        }
    }
}

