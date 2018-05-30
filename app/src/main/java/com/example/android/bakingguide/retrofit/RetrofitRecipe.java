package com.example.android.bakingguide.retrofit;

import com.example.android.bakingguide.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitRecipe {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}

