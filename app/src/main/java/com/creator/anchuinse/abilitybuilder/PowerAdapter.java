package com.creator.anchuinse.abilitybuilder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;

/**
 * Created by Matt on 5/28/18.
 */

public class PowerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the aspects of a single power

    Context context;
    ArrayList<Aspect> aspects;

    public PowerAdapter(Context context, ArrayList<Aspect> aspects){
        this.context = context;
        this.aspects = aspects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.aspect_row,parent,false);
        ViewHolder item = new ViewHolder(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //important one to change how things are viewed along with ViewHolder object
        ((ViewHolder)holder).textView.setText(aspects.get(position).getName());

        if(aspects.get(position) instanceof ComplexAspect) {
            ((ViewHolder)holder).selected.setText(aspects.get(position).getSelected().getName());
        }
        else {
            ((ViewHolder)holder).selected.setText(aspects.get(position).getSelected().getName() + ":");
        }
        ((ViewHolder)holder).cost.setText(String.valueOf(aspects.get(position).getSelected().getCost()));

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
                intent.putExtra("aspect",aspects.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return aspects.size();
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

