package com.example.fruitapp;

import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<FruitItem> data;


    public RecyclerAdapter(ArrayList<FruitItem> data) {
        super();
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d("fruit", "Bind a view for pos" + position);
        viewHolder.nameTV.setText("Name: " + data.get(position).getName());
        viewHolder.familyTV.setText("Family: " + data.get(position).getFamily());
        viewHolder.caloriesTV.setText("Calories: " + Integer.toString(data.get(position).getCalories()));
        viewHolder.sugarTV.setText("Sugar: " + Integer.toString(data.get(position).getSugar()));
        viewHolder.carbohydratesTV.setText("Carbohydrates: " + Integer.toString(data.get(position).getCarbohydrates()));
    }


        //a class declared in a method (so called local or anonymous class can only access the method's local variables if they are declared final (1.8 or are effectively final)
        //this has to do with Java closures
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView nameTV;
        public TextView familyTV;
        public TextView caloriesTV;
        public TextView sugarTV;
        public TextView carbohydratesTV;


        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTV = itemView.findViewById(R.id.name_id);
            familyTV = itemView.findViewById(R.id.family_id);
            caloriesTV = itemView.findViewById(R.id.calories_id);
            sugarTV = itemView.findViewById(R.id.sugar_id);
            carbohydratesTV = itemView.findViewById(R.id.carbohydrates_id);
        }
    }
}

