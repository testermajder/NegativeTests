package com.example.test.api.data.model.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

//Model of request created with online tool http://www.jsonschema2pojo.org/
public class ExampleRequest {

    @SerializedName("stringValue")
    @Expose
    private String stringValue;
    @SerializedName("booleanValue")
    @Expose
    private Boolean booleanValue;
    @SerializedName("intValue")
    @Expose
    private Integer intValue;
    @SerializedName("listValue")
    @Expose
    private List<String> listValue = null;
    @SerializedName("objectValue")
    @Expose
    private ObjectValue objectValue;

    ExampleRequest() {
    }

    public ExampleRequest(String stringValue, Boolean booleanValue, Integer intValue, List<String> listValue, ObjectValue objectValue) {
        super();
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.listValue = listValue;
        this.objectValue = objectValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public List<String> getListValue() {
        return listValue;
    }

    public void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }

    public ObjectValue getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(ObjectValue objectValue) {
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("stringValue", stringValue).append("booleanValue", booleanValue).append("intValue", intValue).append("listValue", listValue).append("objectValue", objectValue).toString();
    }

}
