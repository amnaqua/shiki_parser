package org.example.parsers;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedList;

@Getter
@Setter
public class HtmlParser {
    private LinkedList<Elements> links = new LinkedList<>();
    private Integer numberOfIter;

    public HtmlParser() {

    }

    private void getStudio() {

    }

    private void getArticles() {
        numberOfIter();
        try {
            for (int i = 1; i <= numberOfIter; i++) {
                Document document = Jsoup
                        .connect("https://shikimori.me/animes/studio/13-Studio-4-C/order-by/aired_on/page/" + i)
                        .userAgent("Chrome/113.0.0.0 Safari/537.36")
                        .get();
                addArticles(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addArticles(Document doc) {
        Elements temp = doc.select("article");
        links.add(temp);
    }

    private void numberOfIter() {
        try {
            Document document = Jsoup
                    .connect("https://shikimori.me/animes/studio/13-Studio-4-C/order-by/aired_on/")
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.select("span.link-total");
            String str = temp.text();
            numberOfIter = (int) str.charAt(0) - (int) '0';
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}