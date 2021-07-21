package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SqlRuDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        LocalDate date = getLocalDate(parse);
        LocalTime time = getLocalTime(parse);
        return LocalDateTime.of(date, time);
    }

    private LocalTime getLocalTime(String parse) {
        int hour, minute;
        String[] date = parse.split(", ");
        String[] time = date[1].split(":");
        hour = Integer.parseInt(time[0]);
        minute = Integer.parseInt(time[1]);
        return LocalTime.of(hour, minute);
    }

    private LocalDate getLocalDate(String parse) {
        int year, month, dayOfMonth;
        LocalDate rsl;
        String[] date = parse.split(", ");
        if (parse.matches("^\\d{1,2}.*")) {
            String[] parts = date[0].split(" ");
            year = Integer.parseInt(parts[2]) + 2000;
            month = getMonth(parts[1]);
            dayOfMonth = Integer.parseInt(parts[0]);
            rsl = LocalDate.of(year, month, dayOfMonth);
        } else {
            LocalDateTime ldt = date[0].equals("сегодня") ? LocalDateTime.now() : LocalDateTime.now().minusDays(1);
            rsl = ldt.toLocalDate();
        }
        return rsl;
    }

    private int getMonth(String month) {
        int rsl;
         switch (month) {
             case "янв" : rsl = 1;
             break;
             case "фев" : rsl = 2;
             break;
             case "мар" : rsl = 3;
             break;
             case "апр" : rsl = 4;
             break;
             case "май" : rsl = 5;
             break;
             case "июн" : rsl = 6;
             break;
             case "июл" : rsl = 7;
             break;
             case "авг" : rsl = 8;
             break;
             case "сен" : rsl = 9;
             break;
             case "окт" : rsl = 10;
             break;
             case "ноя" : rsl = 11;
             break;
             case "дек" : rsl = 12;
             break;
             default : rsl = 0;
        }
        return rsl;
    }
}