package ru.job4j.grabber;

import ru.job4j.grabber.utils.SqlRuDateTimeParser;
import ru.job4j.html.SqlRuParse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        InputStream in = PsqlStore.class.getClassLoader()
                .getResourceAsStream("app.properties");
        properties.load(in);
        PsqlStore store = new PsqlStore(properties);
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> list = sqlRuParse.list("https://www.sql.ru/forum/job-offers/");
        store.save(list.get(4));
        store.save(list.get(5));
        store.save(list.get(6));
        System.out.println(store.findById(4));
        System.out.println(store.findById(5));
        System.out.println(store.findById(6));
        System.out.println(store.getAll());
    }
}
