package com.creator.anchuinse.abilitybuilder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Matt on 5/22/18.
 */

public class MasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //adapter to hold all the powersets of a single user

    Context context;
    ArrayList<String> items;

    public MasterAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
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
        ((ViewHolder)holder).textView.setText(items.get(position));                                 //set items and such to their place in the ViewHolder

        ((ViewHolder) holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use this to change what happens when things are clicked
                Toast.makeText(context, items.get(position),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PowersetActivity.class);
                intent.putExtra("powerset_name", items.get(position));                        //attach extra stuff to the intent to specify which item we clicked
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
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
