package com.projectname.api.client.utils;

import java.util.ArrayList;
import java.util.List;

public class ResponseValidation {

    List<String> unknownFields = new ArrayList<>();
    List<String> wrongTypeFields = new ArrayList<>();
    List<String> classRequiredFields = new ArrayList<>();
    List<String> requiredFieldMissingValue = new ArrayList<>();

    public List<String> getUnknownFields() {
        return unknownFields;
    }

    public void setUnknownFields(List<String> unknownFields) {
        this.unknownFields = unknownFields;
    }

    public List<String> getWrongTypeFields() {
        return wrongTypeFields;
    }

    public void setWrongTypeFields(List<String> wrongTypeFields) {
        this.wrongTypeFields = wrongTypeFields;
    }

    public List<String> getRequiredFieldMissingValue() {
        return requiredFieldMissingValue;
    }

    public void setRequiredFieldMissingValue(List<String> requiredFieldMissingValue) {
        this.requiredFieldMissingValue = requiredFieldMissingValue;
    }

    public List<String> getClassRequiredFields() {
        return classRequiredFields;
    }

    public void setClassRequiredFields(List<String> classRequiredFields) {
        this.classRequiredFields = classRequiredFields;
    }

    public ResponseValidation removeDuplicates(){
        Functions.removeDuplicates(this.getClassRequiredFields());
        Functions.removeDuplicates(this.getRequiredFieldMissingValue());
        Functions.removeDuplicates(this.getUnknownFields());
        Functions.removeDuplicates(this.getWrongTypeFields());
        return this;
    }
}
