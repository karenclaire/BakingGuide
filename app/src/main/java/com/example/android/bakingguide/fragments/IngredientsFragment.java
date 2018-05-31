package com.example.android.bakingguide.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.adapters.IngredientsAdapter;
import com.example.android.bakingguide.model.Ingredients;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsFragment  extends android.support.v4.app.Fragment{

    private static final String LOG_TAG = IngredientsFragment.class.getSimpleName();

    public static final String BUNDLE_DATA_KEY = "INGREDIENTS_DATA";

    @BindView(R.id.ingredients_recycler)
    RecyclerView ingredientsRecyclerView;

    ArrayList<Ingredients> mIngredients;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_DATA_KEY, Parcels.wrap(mIngredients));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mIngredients = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_DATA_KEY));
        }

        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false );

        ButterKnife.bind(this, rootView);

        IngredientsAdapter adapter = new IngredientsAdapter();
        adapter.setIngredients(mIngredients);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        ingredientsRecyclerView.setAdapter(adapter);
        ingredientsRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.mIngredients = ingredients;
    }
}
