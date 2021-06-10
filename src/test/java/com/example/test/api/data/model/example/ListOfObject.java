package com.example.test.api.data.model.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ListOfObject implements Serializable {

    @SerializedName("property")
    @Expose
    private String property;
    @SerializedName("value")
    @Expose
    private Boolean value;

    public ListOfObject() {
    }

    public ListOfObject(String property, Boolean value) {
        super();
        this.property = property;
        this.value = value;
    }


    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("property", property).append("value", value).toString();
    }

}
