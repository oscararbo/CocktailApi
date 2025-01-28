package com.oscararbo.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("filter.php")
    Call<CocktailResponse> getCocktailsByIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Call<CocktailDetailsResponse> getCocktailDetails(@Query("i") String id);
}
