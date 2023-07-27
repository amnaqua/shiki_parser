package org.example.collectors;

import lombok.Getter;
import org.example.models.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class AuthorsDataCollector {
    List<String> listOfAuthors = new ArrayList<>();
    Map<String, List<Integer>> requirements = new HashMap<>();

    public AuthorsDataCollector(User user) {
        getLinks(user);
        System.out.println(listOfAuthors);
    }

    private void getLinks(User user) {
        try {
            List<String> parse;
            Document document = Jsoup
                    .connect("https://shikimori.me/" + user.getUserName() + "/achievements/author")
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.select("a[href]");
            parse = temp.eachAttr("href");
            for (int i = 0; i < parse.size() ; i++) {
                listOfAuthors.add(i, parse.get(i).substring(parse.get(i).indexOf("author/") + 7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
