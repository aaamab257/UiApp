package com.aaamab.uiapp.data;

import com.google.gson.annotations.SerializedName;

public class EntitiesObj {
    @SerializedName("name")
    String name ;

    @SerializedName("type")
    String type ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
