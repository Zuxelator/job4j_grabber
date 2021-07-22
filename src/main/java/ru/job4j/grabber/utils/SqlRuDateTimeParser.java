package ru.job4j.grabber.utils;

import ru.job4j.grabber.utils.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, Integer> MONTHS = Map.ofEntries(
            entry("янв", 1),
            entry("фев", 2),
            entry("мар", 3),
            entry("апр", 4),
            entry("май", 5),
            entry("июн", 6),
            entry("июл", 7),
            entry("авг", 8),
            entry("сен", 9),
            entry("окт", 10),
            entry("ноя", 11),
            entry("дек", 12)
    );

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
            month = MONTHS.get(parts[1]);
            dayOfMonth = Integer.parseInt(parts[0]);
            rsl = LocalDate.of(year, month, dayOfMonth);
        } else {
            LocalDateTime ldt = date[0].equals("сегодня") ? LocalDateTime.now() : LocalDateTime.now().minusDays(1);
            rsl = ldt.toLocalDate();
        }
        return rsl;
    }
}