package com.aaamab.uiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.data.SuggestionsObj;
import com.aaamab.uiapp.ui.entities.EntitiesScreen;
import com.aaamab.uiapp.utils.StaticsMethod;

import java.util.ArrayList;

public class SuggAdapter extends RecyclerView.Adapter<SuggAdapter.MyViewHolder> {

    ArrayList<SuggestionsObj> suggestions ;
    Context context ;

    public SuggAdapter(ArrayList<SuggestionsObj> suggestions, Context context) {
        this.suggestions = suggestions;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.sugg_item, parent, false);
        return new SuggAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.group.setText(suggestions.get(position).getGroup());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StaticsMethod.entities = suggestions.get(position).getEntities();

                Intent goToEntities = new Intent(context , EntitiesScreen.class);
                context.startActivity(goToEntities);

            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView group ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.txtGroupName);
        }
    }
}
