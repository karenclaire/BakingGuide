package com.example.android.bakingguide.model;

import android.net.Uri;
import android.text.TextUtils;

import com.example.android.bakingguide.interfaces.IngredientsModelInterface;
import com.example.android.bakingguide.interfaces.InstructionsModelInterface;
import com.example.android.bakingguide.interfaces.RecipeModelInterface;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel
public class RecipeModel implements RecipeModelInterface {
        public Recipe recipe;
        public ArrayList<IngredientsModel> mIngredients;
        public ArrayList<InstructionsModel> mInstructions;

        @ParcelConstructor
        public RecipeModel(Recipe recipe) {
            this.recipe = recipe;

            this.mIngredients = new ArrayList<>();
            this.mInstructions= new ArrayList<>();

            for (Ingredients ingredient: recipe.ingredients) {
                this.mIngredients.add(new IngredientsModel(ingredient));
            }

            for (Instructions instruction: recipe.instructions) {
                this.mInstructions.add(new InstructionsModel(instruction));
            }
        }

        public String getName() {
            return this.recipe.name;
        }

        public String getRecipeCountText() {
            return mInstructions.size() + " Instructions";
        }

        public Boolean hasImage() {
            return !TextUtils.isEmpty(recipe.imageURL);
        }

        public Uri getImageUri() {
            if (TextUtils.isEmpty(recipe.imageURL)) {
                return null;
            }

            return Uri.parse(recipe.imageURL);
        }

        public ArrayList<? extends IngredientsModelInterface> getIngredients() {
            return this.mIngredients;
        }

    @Override
    public ArrayList<? extends InstructionsModelInterface> getInstructions() {
        return this.mInstructions;
    }

    }


