package com.projectname.api.client.utils;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

// Various utility function
public class Functions {

    public static List<Integer> convertListOfStringsToListOfIntegers(List<String> listString) {
        List<Integer> listInteger = new ArrayList<Integer>();
        for (String item : listString) {
            listInteger.add(Integer.parseInt(item));
        }
        return listInteger;
    }

    public static List<String> convertStringWithBracketsToListOfString(String stringWithBrackets) {
        String stringWithoutBrackets = stringWithBrackets.replaceAll("\\[|\\]", "");
        List<String> stringList = new ArrayList<String>(Arrays.asList(stringWithoutBrackets.split(", ")));
        return stringList;
    }

    public static String getRandomValueFromListOfStrings(List<String> stringList) {
        int length = stringList.size();
        return stringList.get(RandomUtils.nextInt(0, length - 1));
    }

    public static Integer getRandomValueFromListOfIntegers(List<Integer> stringList) {
        int length = stringList.size();
        return stringList.get(RandomUtils.nextInt(0, length - 1));
    }

    public static Boolean TrueOrFalseChooser() {

        List<Boolean> trueOrFalse = new ArrayList<Boolean>();
        trueOrFalse.add(true);
        trueOrFalse.add(false);

        int length = trueOrFalse.size();
        return trueOrFalse.get(RandomUtils.nextInt(0, length - 1));

    }

    public static String extractResponse(String path, Response method) {
        Response response = method;
        String value = response.path(path);
        return value;
    }

    public static <T> List<T> removeDuplicates(List<T> list)
    {
        Set<T> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
