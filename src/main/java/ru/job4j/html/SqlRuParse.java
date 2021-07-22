package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> list = sqlRuParse.list("https://www.sql.ru/forum/job-offers/");
    }

    public static Map<String, String> getDetails(String url) throws IOException {
        Map<String, String> map = new HashMap<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.select(".msgTable");
        Element table = tables.first();
        Elements rows = table.select("tr");
        String description = rows.get(1).child(1).text();
        Elements date = doc.select(".msgFooter");
        String created = date.get(0).text().split(" \\[")[0];
        String title = rows.get(0).child(0).text();
        map.put("description", description);
        map.put("created", created);
        map.put("title", title);
        return map;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements table = doc.select(".forumTable");
            Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements tds = row.select("td");
                if (tds.size() > 0) {
                    rsl.add(detail(tds.get(1).child(0).attr("href")));
                }
            }
        }
        return rsl;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        try {
            Map<String, String> details = getDetails(link);
            LocalDateTime created = dateTimeParser.parse(details.get("created"));
            String title = details.get("title");
            String description = details.get("description");
            post = new Post(title, link, description, created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}