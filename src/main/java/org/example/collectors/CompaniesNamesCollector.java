package org.example.collectors;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CompaniesNamesCollector {
    private final LinkedHashMap<Integer, String> names = new LinkedHashMap<>();
    private final LinkedList<Integer> skipped = new LinkedList<>();

    public CompaniesNamesCollector() {
        getNames();
    }

    private void getNames() {
        try {
            for (int i = 1; i < 2800; i++) {
                try {
                    Document document = Jsoup
                            .connect("https://myanimelist.net/anime/producer/" + i)
                            .userAgent("Chrome/113.0.0.0 Safari/537.36")
                            .get();
                    Elements temp = document.select("h1");
                    names.put(i, temp.html());
                } catch (HttpStatusException e) {
                    skipped.add(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
