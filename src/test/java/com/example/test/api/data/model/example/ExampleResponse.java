package com.example.test.api.data.model.example;

import com.example.test.api.annotations.ResponseRequiredField;
import com.example.test.api.data.model.common.ResponseHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Model of response created with online tool http://www.jsonschema2pojo.org/
//Always main model should implement ResponseHelper in order to be able to know that serialization failed
public class ExampleResponse implements Serializable {

    @SerializedName("stringValue")
    // our custom annotation to mark this field as required - used for Integration tests
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private String stringValue;
    @SerializedName("booleanValue")
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private Boolean booleanValue;
    @SerializedName("intValue")
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private Integer intValue;
    @SerializedName("listValue")
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private List<String> listValue = null;
    @SerializedName("objectValue")
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private ObjectValue objectValue;
    @SerializedName("listOfObjects")
    @ResponseRequiredField(canBeEmpty = false)
    @Expose
    private List<ListOfObject> listOfObjects = null;

    public ExampleResponse() {

    }

    public ExampleResponse(String stringValue, Boolean booleanValue, Integer intValue, List<String> listValue, ObjectValue objectValue, List<ListOfObject> listOfObjects) {
        super();
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.listValue = listValue;
        this.objectValue = objectValue;
        this.listOfObjects = listOfObjects;
    }

    public void parseResponse(ExampleRequest exampleRequest) {
        this.setStringValue(exampleRequest.getStringValue());
        this.setBooleanValue(exampleRequest.getBooleanValue());
        this.setIntValue(exampleRequest.getIntValue());
        this.setListValue(exampleRequest.getListValue());
        ObjectValue objectValue = new ObjectValue();
        objectValue.setObjectProperty(exampleRequest.getObjectValue().getObjectProperty());
        this.setObjectValue(objectValue);
        List<ListOfObject> objectList = new ArrayList<>();
        for (int i = 0; i < objectList.size(); i++) {
            ListOfObject listOfObject = new ListOfObject();
            listOfObject.setProperty(exampleRequest.getObjectValue().getObjectProperty());
            listOfObject.setValue(true);
            objectList.add(listOfObject);
        }
        this.setListOfObjects(objectList);
    }

    public void parseResponse(String exampleRequest) {
        //fetch data by this exampleRequest from another ep that you already confirmed and use those data to set expected
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

    public List<ListOfObject> getListOfObjects() {
        return listOfObjects;
    }

    public void setListOfObjects(List<ListOfObject> listOfObjects) {
        this.listOfObjects = listOfObjects;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("stringValue", stringValue).append("booleanValue", booleanValue).
                append("intValue", intValue).append("listValue", listValue).append("objectValue", objectValue).
                append("listOfObjects", listOfObjects).toString();
    }
}
