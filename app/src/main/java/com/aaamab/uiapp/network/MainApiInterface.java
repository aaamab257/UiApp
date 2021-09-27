package com.aaamab.uiapp.network;

import com.aaamab.uiapp.data.LocationSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MainApiInterface {

    @GET("locations/search")
    Call<LocationSearch> getSuggestions(@Query("query") String query , @Query("locale") String locale ,
                                        @Header("x-rapidapi-host") String host ,
                                        @Header("x-rapidapi-key") String key
                                        );



    //GET
    // 1 - Body --> No Parameter
    // 2 - param -- > yes
    // 3 - headers --> yes



    //POST
    // 1 - Body --> yes
    // 2 - param -- > yes
    // 3 - headers --> yes


}
