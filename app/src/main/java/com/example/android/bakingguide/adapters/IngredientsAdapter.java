package com.example.android.bakingguide.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.model.Ingredients;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private ArrayList<Ingredients> mIngredients;
    Context mContext;

    public IngredientsAdapter() {
        this.mIngredients = new ArrayList<>();
    }

    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.ingredients;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredientsNameTextView.setText(mIngredients.get(position).getIngredients());
        holder.measureTextView.setText(mIngredients.get(position).getMeasure());
        holder.quantityTextView.setText(mIngredients.get(position).getMeasure());
    }


    @Override
    public int getItemCount() {
        return mIngredients.size();
    }


    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.mIngredients = ingredients;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredients_name)
        TextView ingredientsNameTextView;
        @BindView(R.id.ingredients_measure )
        TextView measureTextView;
        @BindView(R.id.ingredients_quantity)
        TextView quantityTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        }
    }
