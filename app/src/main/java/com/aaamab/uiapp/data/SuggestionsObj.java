package com.aaamab.uiapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SuggestionsObj {
    @SerializedName("group")
    String group ;

    @SerializedName("entities")
    ArrayList<EntitiesObj> entities ;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<EntitiesObj> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<EntitiesObj> entities) {
        this.entities = entities;
    }
}
