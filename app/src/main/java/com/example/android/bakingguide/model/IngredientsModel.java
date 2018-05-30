package com.example.android.bakingguide.model;

import com.example.android.bakingguide.interfaces.IngredientsModelInterface;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel(Parcel.Serialization.BEAN)
    public class IngredientsModel implements IngredientsModelInterface {
    @ParcelProperty ("ingredients")
        public final Ingredients mIngredients;


    @ParcelConstructor
    public IngredientsModel( @ParcelProperty ("ingredients")Ingredients ingredients) {
        this.mIngredients = ingredients;
    }


    @Override
    public String getIngredientDetail() {
        return String.format("%s %s of %s", mIngredients.quantity, mIngredients.measure, mIngredients.name);
    }
}



