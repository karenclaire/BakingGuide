package com.example.android.bakingguide.retrofit;

import com.example.android.bakingguide.model.Recipe;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RecipeBuilder {

    private static final String LOG_TAG = RecipeBuilder.class.getSimpleName();

    static RetrofitRecipe retrofitRecipe;

    private static RetrofitRecipe Build() {

        //Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        return new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(httpClientBuilder.build())
                .build().create(RetrofitRecipe.class);

        //return retrofitRecipe;
    }
    public Call<ArrayList<Recipe>> getRecipe() {
        return Build().getRecipe();
    }


}
