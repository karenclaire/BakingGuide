package com.example.android.bakingguide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.adapters.InstructionsAdapter;
import com.example.android.bakingguide.adapters.RecipeDetailAdapter;
import com.example.android.bakingguide.fragments.IngredientsFragment;
import com.example.android.bakingguide.fragments.InstructionsDetailsFragment;
import com.example.android.bakingguide.fragments.InstructionsFragment;
import com.example.android.bakingguide.interfaces.IngredientsModelInterface;
import com.example.android.bakingguide.interfaces.InstructionsModelInterface;
import com.example.android.bakingguide.interfaces.RecipeModelInterface;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity  implements InstructionsAdapter.OnClickHandler,
        RecipeDetailAdapter.RecipeDetailOnClickListener {

        static final String CURRENT_INDEX_BUNDLE_KEY = "RECIPE_CURRENT_INDEX";
        static final String RECIPES_BUNDLE_KEY = "RECIPES";
     static final String BUNDLE_DATA_KEY = "RECIPE_STEPS_DATA";
    public static final String STEPS_INDEX = "RECIPE_STEP_INDEX";

    InstructionsDetailsFragment mInstructionsDetailsFragment;
    ArrayList<RecipeModelInterface> mRecipes;
    ArrayList<InstructionsModelInterface> mInstructions;
    ArrayList<IngredientsModelInterface> mIngredients;

    //recipe_details view
    @BindView(R.id.ingredients_instructions_view)
    ScrollView mScrollView;

    String recipeName;
    int currentPosition;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(RECIPES_BUNDLE_KEY, Parcels.wrap(mRecipes));
        outState.putInt(CURRENT_INDEX_BUNDLE_KEY, currentPosition);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            currentPosition= savedInstanceState.getInt(CURRENT_INDEX_BUNDLE_KEY);
            mRecipes = Parcels.unwrap(savedInstanceState.getParcelable(RECIPES_BUNDLE_KEY));
        } else {
            Intent intent = getIntent();
            currentPosition = intent.getIntExtra(Intent.EXTRA_INDEX, 0);
            mRecipes = Parcels.unwrap(intent.getParcelableExtra(Intent.EXTRA_INTENT));
        }

        RecipeModelInterface recipeModelInterface = mRecipes.get(currentPosition);

        setup(recipeModelInterface);

    }
    private void setup(RecipeModelInterface recipeModelInterface) {
        mScrollView.scrollTo(0, 0);

        FragmentManager fragmentManager = getSupportFragmentManager();



        setTitle(recipeModelInterface.getName());

        // Ingredient list fragment
        ArrayList<IngredientsModelInterface> ingredients = (ArrayList<IngredientsModelInterface>) recipeModelInterface.getIngredients();

        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ingredients_fragment_container, ingredientsFragment, IngredientsFragment.class.toString())
                .commit();

        ingredientsFragment.setIngredients(ingredients);

        // Instructions list fragment
        mInstructions = (ArrayList< InstructionsModelInterface>) recipeModelInterface.getInstructions();
        InstructionsFragment instructionsFragment = new InstructionsFragment();
        InstructionsFragment.setOnInstructionsClickHandler(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.instructions__fragment_container, instructionsFragment)
                .commit();

        InstructionsFragment.setInstructions(mInstructions);

        if (findViewById(R.id.instructions__fragment_container)  != null) {
            setupInstructionsDetailsFragment(mInstructions.get(0));
        }
    }

    private void setupInstructionsDetailsFragment(InstructionsModelInterface instructions) {
        InstructionsDetailsFragment instructionsDetailsFragment = new InstructionsDetailsFragment();
        instructionsDetailsFragment.setInstructions(mInstructions);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.instructions__fragment_container, instructionsDetailsFragment)
                .commit();
    }


    @Override
    public void onInstructionsClicked(int position, InstructionsModelInterface instructions) {

        final InstructionsDetailsFragment instructionsDetailsFragment = new InstructionsDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(recipeName);

        Bundle stepBundle = new Bundle();
        stepBundle.putParcelable(BUNDLE_DATA_KEY, (Parcelable) instructions);
        stepBundle.putInt(STEPS_INDEX,currentPosition);
        stepBundle.putString("Title",recipeName);
        instructionsDetailsFragment.setArguments(stepBundle);

        if (findViewById(R.id.recipe_linear_layout).getTag()!=null && findViewById(R.id.recipe_linear_layout).getTag().equals("tablet-land")) {
            fragmentManager.beginTransaction()
                    .replace(R.id.instructions__fragment_container, instructionsDetailsFragment).addToBackStack(BUNDLE_DATA_KEY)
                    .commit();

        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.instructions__fragment_container, instructionsDetailsFragment).addToBackStack(BUNDLE_DATA_KEY)
                    .commit();
        }




    }


    @Override
    public void onDetailListItemClick(InstructionsModelInterface clickedItemIndex) {

    }
}