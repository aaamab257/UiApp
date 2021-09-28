package com.aaamab.uiapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.data.EntitiesObj;

import java.util.ArrayList;

public class EntitiesAdapter extends RecyclerView.Adapter<EntitiesAdapter.MyViewHolder> {
    ArrayList<EntitiesObj> entities;
    Context context;

    public EntitiesAdapter(ArrayList<EntitiesObj> entities, Context context) {
        this.entities = entities;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.entities_item, parent, false);
        return new EntitiesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(entities.get(position).getName());
        holder.type.setText(entities.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            type = itemView.findViewById(R.id.txtType);

        }
    }
}
