/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.rogergcc.bookuiproject.api;


import com.rogergcc.bookuiproject.model.GBook;
import com.rogergcc.bookuiproject.model.GBookRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {

    //https://www.googleapis.com/books/v1/volumes?q=developer&orderBy=newest

    //https://www.themealdb.com/api/json/v2/1/latest.php
//    @GET("latest.php")
//    Call<Meals> getMeal();

//    @GET("categories.php")
//    Call<Categories> getCategories();

    //example https://themealdb.com/api/json/v2/1/filter.php?c=Beef
    @GET("volumes")
    Call<GBookRequest> getBookByVolumens(@Query("q") String volumen, @Query("orderBy") String orderby);

    //TODO #3 Call the search.php with query string the meal name @GET("search.php)

//    @GET("search.php")
//    Call<Meals> getMealByName(@Query("s") String mealName);
}
