package com.example.test.api.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dates {

    public static LocalDateTime parseToClientFormatDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDate parseToClientFormatDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String parseClientDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static List<String> createDateListForSelectedPeriod(int numberOfDaysInPast) {
        LocalDate dateTo = LocalDate.now();
        List<String> listOfDates = new ArrayList<>();
        listOfDates.add(dateTo.toString());
        for (int i = 1; i <= numberOfDaysInPast; i++) {
            listOfDates.add(dateTo.minusDays(i).toString());
        }
        return listOfDates;
    }


    public static LocalDate generateDateInRecentPast(int numberOfDaysInPast) {
        LocalDate date = LocalDate.now();
        return date.minusDays(numberOfDaysInPast);
    }

    public static List<String> listOfFutureDatesAsString(int numberOfFutureDays) {
        List<String> dates = new ArrayList<>();
        dates.add(LocalDate.now().toString());
        for (int i = 1; i < numberOfFutureDays; i++) {
            dates.add(LocalDate.now().plusDays(i).toString());
        }
        return dates;
    }

    public static List<LocalDate> listOfFutureDates(int numberOfFutureDays) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.now());
        for (int i = 1; i < numberOfFutureDays; i++) {
            dates.add(LocalDate.now().plusDays(i));
        }
        return dates;
    }

}
