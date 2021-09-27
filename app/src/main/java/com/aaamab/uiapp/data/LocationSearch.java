package com.aaamab.uiapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationSearch {

    @SerializedName("term")
    String term ;

    @SerializedName("moresuggestions")
    int moreSuggestions ;

    @SerializedName("geocodeFallback")
    boolean geocodeFallback ;

    @SerializedName("suggestions")
    ArrayList<SuggestionsObj> suggestions ;

    @SerializedName("trackingID")
    String trackingID ;

    public ArrayList<SuggestionsObj> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<SuggestionsObj> suggestions) {
        this.suggestions = suggestions;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getMoreSuggestions() {
        return moreSuggestions;
    }

    public void setMoreSuggestions(int moreSuggestions) {
        this.moreSuggestions = moreSuggestions;
    }

    public boolean isGeocodeFallback() {
        return geocodeFallback;
    }

    public void setGeocodeFallback(boolean geocodeFallback) {
        this.geocodeFallback = geocodeFallback;
    }
}
