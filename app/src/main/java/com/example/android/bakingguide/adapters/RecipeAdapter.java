package com.example.android.bakingguide.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.interfaces.RecipeModelInterface;
import com.example.android.bakingguide.model.Ingredients;
import com.example.android.bakingguide.model.Instructions;
import com.example.android.bakingguide.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder> {

    private static final String LOG_TAG = RecipeAdapter.class.getSimpleName();


    private List<Recipe> mRecipeList;
    Context mContext;
    private String mRecipeName;
    private String mIngredientName;
    private String mInstructionText;
    private RecipeOnClickListener mRecipeOnClickListener;
    private ArrayList<RecipeModelInterface> recipes;
    private List<Ingredients> mIngredientsList;
    private List<Instructions> mInstructionsList;


    public RecipeAdapter(Context context, List <Recipe> recipeList){
        this.mContext = context;
        this.mRecipeList = recipeList;
    }


    public void setRecipes(ArrayList<RecipeModelInterface> recipes) {
        mRecipeName=recipes.get(0).getName();
        this.recipes = recipes;
        notifyDataSetChanged();
    }



    @Override
    public RecipeAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.recipe_list_card_view;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecyclerViewHolder holder, int position) {
        holder.recipeNameTextView.setText(recipes.get(position).getName());


    }
    public interface RecipeOnClickListener {
        void onListItemClick(int position, RecipeModelInterface recipeModelInterface );

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

        class RecyclerViewHolder extends ViewHolder implements View.OnClickListener {
            @BindView(R.id.recipe_cardView)
            CardView recipeCardView;
            @BindView(R.id.recipe_name)
            TextView recipeNameTextView;
            int position;
            RecipeModelInterface recipeModelInterface;

            public RecyclerViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                 mRecipeOnClickListener.onListItemClick(position, recipeModelInterface);}
        }





    /**
     * Custom click listener
     */
    public void setOnItemClickListener(RecipeOnClickListener listener){this.mRecipeOnClickListener = listener;}
}


/**
}**/
