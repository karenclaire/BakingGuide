package com.example.android.bakingguide.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.fragments.RecipeListFragment;
import com.example.android.bakingguide.interfaces.RecipeModelInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.bakingguide.R.layout.activity_recipe;

/**
 * Created by Karen Claire Ulmer
 *
 * References for coding corrections and guidance :
 * http://parceler.org/
 * https://stackoverflow.com/questions/46704251/error-android-view-inflateexception-binary-xml-file-line-8-binary-xml-file-l
 * https://github.com/djkovrik/BakingApp
 * https://github.com/nikosvaggalis/Udacity-Advanced-Developer-Nanodegree-Baking-App-2017
 * https://github.com/tonynguyen0523/Baking-App
 *https://github.com/udacity/AdvancedAndroid_ClassicalMusicQuiz
 */

public class RecipeActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();
    static final String CURRENT_INDEX_BUNDLE_KEY = "RECIPE_CURRENT_INDEX";
    static final String RECIPES_BUNDLE_KEY = "RECIPES";
    int mCurrentPosition;
    ArrayList<RecipeModelInterface> mRecipes;
    Context mContext;

    String recipeName;

    @BindView(R.id.recipe_list_fragment_container)
    FrameLayout recipeListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(activity_recipe);
        ButterKnife.bind(this);


        recipeListContainer = (FrameLayout) findViewById(R.id.recipe_list_fragment_container);

        RecipeListFragment recipeListFragment = new RecipeListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.recipe_list_fragment_container, recipeListFragment,RECIPES_BUNDLE_KEY)
                .commit();


        /**Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking Guide");**/

    }

        @Override
        protected void onRestart() {
            super.onRestart();
            // Refresh recipe list in case database was updated
            RecipeListFragment recipeListFragment = (RecipeListFragment) getSupportFragmentManager().
                    findFragmentById(R.id.recipe_list_fragment_container);
            recipeListFragment.refreshList();
        }
      @Override
      public void onSaveInstanceState(Bundle outState) {
          super.onSaveInstanceState(outState);
          outState.putString("Title",recipeName);
      }


  }

