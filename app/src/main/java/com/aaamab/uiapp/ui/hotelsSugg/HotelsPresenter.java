package com.aaamab.uiapp.ui.hotelsSugg;

import android.content.Context;
import android.widget.Toast;

import com.aaamab.uiapp.data.LocationSearch;
import com.aaamab.uiapp.network.ApiClient;
import com.aaamab.uiapp.network.MainApiInterface;
import com.aaamab.uiapp.utils.StaticsMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsPresenter {

    HotelsInterface hotels ;
    MainApiInterface apiInterface ;
    Context context ;

    public HotelsPresenter(HotelsInterface hotels , Context context) {
        this.hotels = hotels;
        this.context = context ;

    }

    public void getData(String query , String locale){
        apiInterface = ApiClient.getClient().create(MainApiInterface.class);
        Call<LocationSearch> call = apiInterface.getSuggestions(query , locale , StaticsMethod.HOST , StaticsMethod.KEY);

        call.enqueue(new Callback<LocationSearch>() {
            @Override
            public void onResponse(Call<LocationSearch> call, Response<LocationSearch> response) {
                if (response.isSuccessful()){
                    hotels.onSuccessfully(response.body().getTrackingID() ,response.body().getMoreSuggestions());
                }
            }

            @Override
            public void onFailure(Call<LocationSearch> call, Throwable t) {
                Toast.makeText(context, "Error please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
