package org.example.collectors;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

@Getter
public class FranchisesDataCollector {
    List<String> listOfFranchises = new ArrayList<>();
    Map<String, List<Integer>> requirements = new HashMap<>();

    public FranchisesDataCollector() {
        getLinks();
        getRequirements();
    }

    private void getLinks() {
        try {
            List<String> parse;
            Document document = Jsoup
                    .connect("https://shikimori.me/ArthurChocolate/achievements/franchise")
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.select("a[data-tipsy-size]");
            parse = temp.eachAttr("href");
            for (int i = 0; i < parse.size() ; i++) {
                listOfFranchises.add(i, parse.get(i).substring(parse.get(i).indexOf("franchise/") + 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getRequirements() {
        try {
            for (String listOfFranchise : listOfFranchises) {
                Document document = Jsoup
                        .connect("https://shikimori.me/achievements/franchise/" + listOfFranchise)
                        .userAgent("Chrome/113.0.0.0 Safari/537.36")
                        .get();
                Elements temp = document.getElementsByAttribute("data-track_user_rate");
                List<String> str = new ArrayList<>(temp.eachAttr("data-track_user_rate"));
                List<Integer> input = new ArrayList<>();
                for (String s : str) {
                    String[] split = s.split(":");
                    input.add(Integer.parseInt(split[2]));
                }
                requirements.put(listOfFranchise, input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
