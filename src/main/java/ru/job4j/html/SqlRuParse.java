package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
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