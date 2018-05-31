package com.example.android.bakingguide.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.model.Ingredients;
import com.example.android.bakingguide.model.Instructions;

import java.util.ArrayList;

import butterknife.BindView;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder>{


    ArrayList<Ingredients> mIngredients;
    ArrayList<Instructions> mInstructions;


    Context mContext;
    RecipeDetailOnClickListener mRecipeDetailOnClickListener;
    int mCurrentPosition;


    public RecipeDetailAdapter(android.content.Context context, ArrayList<Ingredients>
            ingredients, ArrayList<Instructions> instructions){
        this.mContext = context;
        this.mIngredients = ingredients;
        this.mInstructions= instructions;
    }


    @Override
    public RecipeDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.recipe_detail;
        android.view.LayoutInflater inflater = android.view.LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutId, parent, false);
        RecipeDetailViewHolder viewHolder = new RecipeDetailViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailViewHolder holder, int position) {

        //Ingredients List
        String ingredients =mIngredients.get(position).getIngredients();
        //Instructions List
        String instructions = mInstructions.get(position).getShortDescription();

        holder.mIngredientsTextView.setText(ingredients);
        holder.mInstructionTextView.setText(instructions);



    }

    public interface RecipeDetailOnClickListener {
        void onDetailListItemClick(Instructions clickedItemIndex);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size() &  mInstructions.size();
    }



    public class RecipeDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredients_name)
        TextView mIngredientsTextView;
        @BindView(R.id.ingredients_measure )
        TextView measureTextView;
        @BindView(R.id.ingredients_quantity)
        TextView quantityTextView;

        @BindView(R.id.instruction_text)
            TextView mInstructionTextView;

            public RecipeDetailViewHolder(View view) {
                super(view);
                butterknife.ButterKnife.bind(this, view);
                view.setOnClickListener((View.OnClickListener) this);
            }

                public void onClick(View v) {
                    int position = getAdapterPosition();

                    mRecipeDetailOnClickListener.onDetailListItemClick(mInstructions.get(position));
            }


        }
}




