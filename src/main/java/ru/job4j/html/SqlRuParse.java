package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
        Elements table = doc.select(".forumTable");
        Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements tds = row.select("td");
                if (tds.size() > 0) {
                    System.out.println(tds.get(1).child(0).attr("href"));
                    System.out.println(row.child(1).text());
                    System.out.println(row.child(5).text());
                }
            }
        }
    }
    public static Map<String, String> getDetails(String url) throws IOException {
        Map<String, String> map = new HashMap<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.select(".msgTable");
        Element table = tables.first();
        Elements rows = table.select("tr");
        String description = rows.get(1).child(1).text();
        String[] split = rows.get(2).child(0).text().split(" \\[");
        String created = split[0];
        map.put("description", description);
        map.put("created", created);
        return map;
    }

}