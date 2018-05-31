package com.example.android.bakingguide.model;

/**@Parcel(Parcel.Serialization.BEAN)
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
}**/



