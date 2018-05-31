package com.example.android.bakingguide.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.adapters.RecipeAdapter;
import com.example.android.bakingguide.adapters.RecipeAdapter.RecipeOnClickListener;
import com.example.android.bakingguide.model.Ingredients;
import com.example.android.bakingguide.model.Recipe;
import com.example.android.bakingguide.retrofit.RecipeBuilder;
import com.example.android.bakingguide.ui.RecipeDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListFragment extends Fragment implements RecipeAdapter.RecipeOnClickListener {

    private static final String LOG_TAG = RecipeListFragment.class.getSimpleName();
    static final String RECIPES_BUNDLE_KEY = "RECIPES";


    ArrayList<Recipe> mRecipeList;
    RecipeAdapter mRecipeAdapter;
    ArrayList<Recipe> recipes;

    String mRecipeName;
    String mIngredientName;
    String mInstructionText;
    Context mContext;
    List<Ingredients> mIngrdientsList;
    RecipeOnClickListener mRecipeOnClickListener;


    @BindView(R.id.recipe_recycler_view)
    RecyclerView mRecyclerView;


    RecipeBuilder builder;


    public RecipeListFragment() {
        builder = new RecipeBuilder();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.recipe_list_fragment, container, false);
        ButterKnife.bind(this, rootView);

       assert mRecyclerView != null;

        if (getResources().getBoolean(R.bool.tablet_mode)) {
            final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
            mRecyclerView.setHasFixedSize(true);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            //mRecipeAdapter.setOnItemClickListener(mRecipeOnClickListener);
            mRecipeAdapter.setRecipes(recipes);
            mRecyclerView.setAdapter(mRecipeAdapter);
            mRecyclerView.setLayoutManager(layoutManager);

        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setAdapter(mRecipeAdapter);
            //mRecipeAdapter.setOnItemClickListener(mRecipeOnClickListener);
            mRecipeAdapter = new RecipeAdapter(mContext, mRecipeList, mRecipeOnClickListener);
            mRecipeAdapter.setRecipes(recipes);
            mRecyclerView.setAdapter(mRecipeAdapter);

        }
        builder.getRecipe().enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                recipes = new ArrayList<Recipe>();

                for (Recipe recipe : response.body()) {
                    //recipes.add(new RecipeModel(recipe));
                    Bundle recipesBundle = new Bundle();
                    recipesBundle.putParcelable(RECIPES_BUNDLE_KEY, (Parcelable) mRecipeList);
                }

                mRecipeAdapter.setRecipes(recipes);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });


        mRecipeAdapter.setOnItemClickListener(mRecipeOnClickListener);
        return rootView;
    }


    @Override
    public void onListItemClick(int position, Recipe recipes) {
        Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, Parcels.wrap(recipes));
        intent.putExtra(Intent.EXTRA_INDEX, position);

        startActivity(intent);
    }


    public void refreshList() {
        if (mRecipeAdapter != null) {
            mRecipeAdapter.notifyDataSetChanged();
        }
    }
}
