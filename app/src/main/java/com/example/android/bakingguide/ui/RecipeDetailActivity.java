package com.example.android.bakingguide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ScrollView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.adapters.InstructionsAdapter;
import com.example.android.bakingguide.adapters.RecipeDetailAdapter;
import com.example.android.bakingguide.fragments.IngredientsFragment;
import com.example.android.bakingguide.fragments.InstructionsDetailsFragment;
import com.example.android.bakingguide.fragments.InstructionsFragment;
import com.example.android.bakingguide.model.Ingredients;
import com.example.android.bakingguide.model.Instructions;
import com.example.android.bakingguide.model.Recipe;

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

    public static final String DEBUG_TAG = "DebugStuff";

    InstructionsDetailsFragment mInstructionsDetailsFragment;
    ArrayList<Recipe> mRecipes;
    ArrayList<Instructions> mInstructions;
    ArrayList<Ingredients> mIngredients;

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
        Log.d(DEBUG_TAG, "RecipeDetailActivity onCreate");
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

        Recipe recipes = mRecipes.get(currentPosition);

        setup(recipes);

    }
    private void setup(Recipe recipes) {
        Log.d(DEBUG_TAG, "RecycleDetailActivitx setup Recipe");
        mScrollView.scrollTo(0, 0);

        FragmentManager fragmentManager = getSupportFragmentManager();



        setTitle(recipes.getName());

        // Ingredient list fragment
        ArrayList<Ingredients> ingredients = (ArrayList<Ingredients>) recipes.getIngredients();

        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ingredients_fragment_container, ingredientsFragment, IngredientsFragment.class.toString())
                .commit();

        ingredientsFragment.setIngredients(mIngredients);

        // Instructions list fragment
        mInstructions = (ArrayList< Instructions>) recipes.getInstructions();
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

    private void setupInstructionsDetailsFragment(Instructions instructions) {
        InstructionsDetailsFragment instructionsDetailsFragment = new InstructionsDetailsFragment();
        instructionsDetailsFragment.setInstructions(mInstructions);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.instructions__fragment_container, instructionsDetailsFragment)
                .commit();
    }


    @Override
    public void onInstructionsClicked(int position, Instructions instructions) {

        final InstructionsDetailsFragment instructionsDetailsFragment = new InstructionsDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(recipeName);

        Bundle stepBundle = new Bundle();
        stepBundle.putParcelable(BUNDLE_DATA_KEY, instructions);
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
    public void onDetailListItemClick(Instructions clickedItemIndex) {

    }
}