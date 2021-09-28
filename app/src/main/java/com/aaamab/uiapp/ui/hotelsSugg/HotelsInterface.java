package com.aaamab.uiapp.ui.hotelsSugg;

import com.aaamab.uiapp.data.SuggestionsObj;

import java.util.ArrayList;

public interface HotelsInterface {

    void onSuccessfully(ArrayList<SuggestionsObj> data);
    void onEmptyFields();
}
