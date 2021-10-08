package com.projectname.api.client.utils;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.projectname.api.client.annotations.ResponseRequiredField;
import groovy.util.MapEntry;
import io.restassured.response.Response;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;

// Common methods that use Gson library
public class GsonFunctions {

    static List<String> unknownFields = new ArrayList<>();
    static List<String> wrongTypeFields = new ArrayList<>();
    static List<String> classRequiredFields = new ArrayList<>();
    static List<String> requiredFieldMissingValue = new ArrayList<>();
    static ResponseValidation responseValidation = new ResponseValidation();

    //    method that clears Integration test data from memory, invoked as AfterMethod in TestBase
    public static void revalidateResponseValidationList() {
        unknownFields = new ArrayList<>();
        wrongTypeFields = new ArrayList<>();
        classRequiredFields = new ArrayList<>();
        requiredFieldMissingValue = new ArrayList<>();
        responseValidation = new ResponseValidation();
    }

    //    Verify response of deserialized json object into gson model
    public static ResponseValidation verifyResponse(Response jsonResponse, Class modeledClass){
        ResponseValidation responseValidation = new ResponseValidation();
        String json = jsonResponse.body().asString();
        JsonElement jsonElement = new JsonParser().parse(json);
        if (jsonElement.isJsonArray()) {
            GsonFunctions.parseSuccessResponseAsListToModel(jsonResponse, modeledClass);
            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                ResponseValidation responseValidationTemp = GsonFunctions.verifyResponse(jsonArray.get(i).getAsJsonObject(), modeledClass.getComponentType());
                responseValidation.getClassRequiredFields().addAll(responseValidationTemp.getClassRequiredFields());
                responseValidation.getRequiredFieldMissingValue().addAll(responseValidationTemp.getRequiredFieldMissingValue());
                responseValidation.getUnknownFields().addAll(responseValidationTemp.getUnknownFields());
                responseValidation.getWrongTypeFields().addAll(responseValidationTemp.getWrongTypeFields());
            }
        } else {
            GsonFunctions.parseSuccessResponseToModel(jsonResponse, modeledClass);
            responseValidation = GsonFunctions.verifyResponse(jsonElement.getAsJsonObject(), modeledClass);
        }
        return responseValidation.removeDuplicates();
    }

    private static ResponseValidation verifyResponse(JsonObject jsonResponse, Class modeledClass) {
        Set<String> classFields = new HashSet<>();
        List<String> reqFieldMissingValue = new ArrayList<>();

        List<Field> fieldList = new ArrayList<>();

        Class<?> modClass = modeledClass;
        while (modClass != null && modClass != Object.class) {
            Field[] fields = modClass.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                if (!Modifier.isStatic(fields[j].getModifiers()) && !Modifier.isFinal(fields[j].getModifiers())
                        && !modClass.getSimpleName().equals("ErrResponse") && fields[j].isAnnotationPresent(SerializedName.class)) {
                    Collections.addAll(fieldList, fields[j]);
                }
            }
            modClass = modClass.getSuperclass();
        }

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(ResponseRequiredField.class)) {
                if (!field.getAnnotation(ResponseRequiredField.class).canBeEmpty()) {
                    reqFieldMissingValue.add(field.getAnnotation(SerializedName.class).value());
                }
                classRequiredFields.add(field.getAnnotation(SerializedName.class).value());
            }
            classFields.add(field.getAnnotation(SerializedName.class).value());
        }

        Field field = null;
        try {
            for (Map.Entry<String,  JsonElement> property : jsonResponse.entrySet()) {
                if (!classFields.contains(property.getKey())) {
                    unknownFields.add("Model " + modeledClass.getCanonicalName() + " does not contain property " + property.getKey());
                } else {
                    for (Field f : fieldList) {
                        if (f.getAnnotation(SerializedName.class).value().equals(property.getKey())) {
                            field = f;
                            break;
                        }
                    }
                    Class fieldTypeClass = field.getType();
                    if (!fieldTypeClass.isPrimitive() && (property.getValue().isJsonObject())) {
                        if (fieldTypeClass.equals(Map.class)) {
                            Class classOfKey = (Class<?>) (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
                            wrongTypeFields.addAll(verifyType(property, classOfKey, field.getType()));
                            Type mapValueType = (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1]);
                            if (mapValueType.getTypeName().contains("java.util.List<")) {
                                fieldTypeClass = (Class<?>) (((ParameterizedType) mapValueType).getActualTypeArguments()[0]);
                            } else {
                                fieldTypeClass = (Class<?>) mapValueType;
                            }
                            for (Map.Entry<String, JsonElement> entry : property.getValue().getAsJsonObject().entrySet()) {
                                for (JsonElement json : entry.getValue().getAsJsonArray()) {
                                    verifyResponse(json.getAsJsonObject(), fieldTypeClass);
                                }
                            }
                        } else {
                            verifyResponse(property.getValue().getAsJsonObject(), fieldTypeClass);
                        }
                    } else {
                        if (!property.getValue().isJsonNull()) {
                            if (property.getValue().isJsonArray()) {
                                Class classOfGeneric = (Class<?>) (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
                                wrongTypeFields.addAll(verifyType(property, classOfGeneric, modeledClass));
                            } else {
                                wrongTypeFields.addAll(verifyType(property, fieldTypeClass, modeledClass));
                            }
                        }
                        verifyRequiredFieldsValue(property, modeledClass, reqFieldMissingValue);
                    }
                }
                classRequiredFields.remove(property.getKey());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        responseValidation.setUnknownFields(Functions.removeDuplicates(unknownFields));
        responseValidation.setWrongTypeFields(Functions.removeDuplicates(wrongTypeFields));
        responseValidation.setClassRequiredFields(Functions.removeDuplicates(classRequiredFields));
        responseValidation.setRequiredFieldMissingValue(Functions.removeDuplicates(requiredFieldMissingValue));
        return responseValidation;
    }

    //    Verify if required fields exist in Json schema
    private static List<String> verifyRequiredFieldsValue(Map.Entry<String, JsonElement> property, Class modeledClass, List<String> reqFieldMissingValue){
        if (reqFieldMissingValue.contains(property.getKey())) {
            if (property.getValue().isJsonNull()) {
                requiredFieldMissingValue.add("Required field " + modeledClass.getCanonicalName() + "::" + property.getKey() + " is null");
            } else if (property.getValue().toString().equals("[]") || property.getValue().toString().equals("{}")) {
                requiredFieldMissingValue.add("Required field doesn't have value " + modeledClass.getCanonicalName() + "::" + property.getKey() + " is " + property.getValue().toString());
            }
        }
        return requiredFieldMissingValue;
    }

    //    Verify that json values match the type in model class
    private static List<String> verifyType(Map.Entry<String, JsonElement> property, Class modeledClass, Class rootClass) throws NoSuchFieldException {
        List<String> wrongTypeFields = new ArrayList<>();
        if (property.getValue().isJsonArray()) {
            JsonArray jsonArray = property.getValue().getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                if (jsonElement.isJsonObject()) {
                    verifyResponse(jsonElement.getAsJsonObject(), modeledClass);
                }
                else {
                    Map.Entry<String, JsonElement> childProperty = new MapEntry(property.getKey(), jsonElement);
                    verifyType(childProperty, modeledClass, rootClass);
                }
            }
        } else if (rootClass.equals(Map.class)) {
            property.getValue().getAsJsonObject().keySet().forEach(keyStr ->
            {
                Map.Entry<String, JsonElement> childProperty = new MapEntry(keyStr, new JsonPrimitive(keyStr));
                wrongTypeFields.addAll(checkType(childProperty, modeledClass, rootClass));
            });
        } else {
            wrongTypeFields.addAll(checkType(property, modeledClass, rootClass));
        }
        return wrongTypeFields;
    }

    //    Check primitive json types
    private static List<String> checkType(Map.Entry<String, JsonElement> property, Class fieldTypeClass, Class rootClass) {
        List<String> wrongTypeFields = new ArrayList<>();
        JsonPrimitive jsonPrimitive = ((JsonPrimitive) property.getValue());
        if (jsonPrimitive.isString()) {
            if (fieldTypeClass != String.class) {
                wrongTypeFields.add(rootClass.getCanonicalName() + "::" + property.getKey() + "[value " + property.getValue() + " is not of expected type " + fieldTypeClass + "]");
            }
        } else if (jsonPrimitive.isNumber()) {
            String num = jsonPrimitive.getAsString();
            if (fieldTypeClass == Integer.class || fieldTypeClass == Long.class || fieldTypeClass == BigInteger.class) {
                if (num.indexOf(".") > 0) {
                    wrongTypeFields.add(rootClass.getCanonicalName() + "::" + property.getKey() + "[value " + property.getValue() + " is not of expected type " + fieldTypeClass + "]");
                }
            } else if (fieldTypeClass != Double.class && fieldTypeClass != Float.class) {
                wrongTypeFields.add(rootClass.getCanonicalName() + "::" + property.getKey() + "[value " + property.getValue() + " is not of expected type " + fieldTypeClass + "]");
            }
        } else if (jsonPrimitive.isBoolean()) {
            if (fieldTypeClass != Boolean.class) {
                wrongTypeFields.add(rootClass.getCanonicalName() + "::" + property.getKey() + "[value " + property.getValue() + " is not of expected type " + fieldTypeClass + "]");
            }
        }
        return  wrongTypeFields;
    }

    public static <T> T parseSuccessResponseToModel(Response jsonResponse, Class<T> classOfT) {
        String json = jsonResponse.body().asString();
        String prettyJsonString = "";
        try {
            prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
            if (jsonResponse.getStatusCode() >= 400 && jsonResponse.getStatusCode() < 600) {
                Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString);
            } else {
                return new Gson().fromJson(prettyJsonString, (Type) classOfT);
            }
        } catch (JsonSyntaxException|IllegalStateException e) {
            Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString
                    + "\n serialization exception error: " + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> parseSuccessResponseAsListToModel(Response jsonResponse, Class<T[]> classOfT) {
        String json = jsonResponse.body().asString();
        String prettyJsonString = "";
        try {
            prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
            if (jsonResponse.getStatusCode() >= 400 && jsonResponse.getStatusCode() < 600) {
                Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString);
            } else {
                return Arrays.asList(new Gson().fromJson(prettyJsonString, (Type) classOfT));
            }
        } catch (JsonSyntaxException|IllegalStateException e) {
            Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString +
                    "\n serialization exception error: " + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> parseErrorResponseListToModel(Response jsonResponse, Class<T[]> classOfT) {
        String prettyJsonString = "";
        String json = jsonResponse.body().asString();
        try {
            prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
            if (jsonResponse.getStatusCode() < 400) {
                Assert.fail("Endpoint for processing class " + classOfT + " didn't return error: " + "data[" +
                        prettyJsonString + "]");
            } else {
                return Arrays.asList(new Gson().fromJson(prettyJsonString, (Type) classOfT));
            }
        } catch (IllegalStateException | JsonSyntaxException exception) {
            Assert.fail("Detailed message: " + exception.getMessage() + ", Invalid json format: " + prettyJsonString +
                    " for processing class" + classOfT);
        }
        return null;
    }

    public static <T> T parseErrorResponseToModel(Response jsonResponse, Class<T> classOfT){
        String json = jsonResponse.body().asString();
        String prettyJsonString = "";
        try {
            prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
            if (jsonResponse.getStatusCode() < 400) {
                Assert.fail("Endpoint for processing class " + classOfT + " didn't return error: " + "data[" +
                        prettyJsonString + "]");
            } else {
                return new Gson().fromJson(prettyJsonString, (Type) classOfT);
            }
        } catch (IllegalStateException | JsonSyntaxException exception) {
            Assert.fail("Detailed message: " + exception.getMessage() + ", Invalid json format: " + prettyJsonString +
                    " for processing class" + classOfT);
        }
        return null;
    }

}
