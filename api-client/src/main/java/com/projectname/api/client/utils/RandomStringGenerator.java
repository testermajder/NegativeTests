package com.projectname.api.client.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomStringGenerator {

    public static int createRandomWholeNumber(final int minValue, final int maxValue) {
        return RandomUtils.nextInt(minValue, maxValue);
    }

    public static Integer createRandomWholeNumberInteger(final int minValue, final int maxValue) {

        return RandomUtils.nextInt(minValue, maxValue);
    }

    public static int createNegativeAndPositiveRandomWholeNumber(final int minValue, final int maxValue, String sign) {
        if (sign.equals("-")) {
            return RandomUtils.nextInt(minValue, maxValue) * (-1);
        } else {
            return RandomUtils.nextInt(minValue, maxValue);
        }
    }

    public static Double createRandomDecimalNumber(final double minValue, final double maxValue) {
        return RandomUtils.nextDouble(minValue, maxValue);
    }

    public static Double createRandomDecimalNumberRounded(final double minValue, final double maxValue,
                                                          final int numOfDecimals) {
        Double doubleNumber = RandomUtils.nextDouble(minValue, maxValue);
        return Math.round(doubleNumber * Math.pow(10, numOfDecimals)) / Math.pow(10, numOfDecimals);
    }

    public static String createRandomStringWithLen(final int len) {
        return RandomStringUtils.random(len);
    }

    public static String createRandomStringAlphanumericWithLen(final int len) {
        return RandomStringUtils.randomAlphanumeric(len);
    }

    public static String createRandomStringAlphabeticWithLen(final int len) {
        return RandomStringUtils.randomAlphabetic(len);
    }

    public static String createRandomStringNumericWithLen(final int len) {
        return RandomStringUtils.randomNumeric(len);
    }

    public static String createRandomEmailWithPrefixLen(final int len) {
        final StringBuilder stringBuilder = new StringBuilder(createRandomStringAlphabeticWithLen(len));
        stringBuilder.append("@");
        stringBuilder.append(createRandomStringAlphabeticWithLen(10));
        stringBuilder.append(".");
        stringBuilder.append(createRandomStringAlphabeticWithLen(3));
        return stringBuilder.toString();
    }

}

