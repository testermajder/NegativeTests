package com.example.test.api.data.model.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ObjectValue implements Serializable {

    @SerializedName("objectProperty")
    @Expose
    private String objectProperty;

    public ObjectValue() {

    }

    public ObjectValue(String objectProperty) {
        super();
        this.objectProperty = objectProperty;
    }

    public String getObjectProperty() {
        return objectProperty;
    }

    public void setObjectProperty(String objectProperty) {
        this.objectProperty = objectProperty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("objectProperty", objectProperty).toString();
    }

}

