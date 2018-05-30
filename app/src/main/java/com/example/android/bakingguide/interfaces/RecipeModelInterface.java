package com.example.android.bakingguide.interfaces;

import android.net.Uri;

import java.util.ArrayList;

public interface RecipeModelInterface {
    String getName();
    String getRecipeCountText();
    Boolean hasImage();
    Uri getImageUri();
    ArrayList<? extends IngredientsModelInterface> getIngredients();
    ArrayList<? extends InstructionsModelInterface> getInstructions();
}

