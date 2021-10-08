package com.projectname.api.client.utils;

import java.time.*;
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

    public static LocalDateTime parseMillisecondsToLocalDateTime(Long dateInMillis) {

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateInMillis), ZoneId.systemDefault());
    }

    public static LocalDateTime parseToSimpleDateTimeFormat(LocalDateTime dateTime) {

        LocalDate date = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return LocalDateTime.of(date, time);
    }

    public static LocalDateTime parseToDateTimeFormat(LocalDateTime dateTime) {

        LocalDate date = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
        return LocalDateTime.of(date, time);
    }

    public static LocalDateTime getCurrentDateTimeInQuinyxTimeZone() {

        return ZonedDateTime.now(ZoneId.of("Europe/Stockholm")).toLocalDateTime();
    }
}
