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
        return switch (month) {
            case "янв" -> 1;
            case "фев" -> 2;
            case "мар" -> 3;
            case "апр" -> 4;
            case "май" -> 5;
            case "июн" -> 6;
            case "июл" -> 7;
            case "авг" -> 8;
            case "сен" -> 9;
            case "окт" -> 10;
            case "ноя" -> 11;
            case "дек" -> 12;
            default -> 0;
        };
    }
}