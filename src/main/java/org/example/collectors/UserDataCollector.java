package org.example.collectors;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

@Getter
public class UserDataCollector {
    private String userName;
    private final Map<String, Integer> userListCounts = new HashMap<>();
    private final List<Integer> userCompletes = new ArrayList<>();
    private final List<Integer> userDrops = new ArrayList<>();
    private final List<Integer> userOnHold = new ArrayList<>();
    private final List<Integer> userPlanning = new ArrayList<>();
    private final List<Integer> userWatching = new ArrayList<>();
    private final List<Integer> userRewatching = new ArrayList<>();

    public UserDataCollector() {
        userNameCollector();
        numbersCollector();
        getListOfCompletes();
        getListOfWatching();
        getListOfDrops();
        getListOfRewatch();
        getListOfOnHold();
        getListOfPlanning();
    }

    private void userNameCollector() {
        try {
            System.out.println("Input user name: ");
            Scanner input = new Scanner(System.in);
            userName = input.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNumberOf(String dataType) {
        try {
            Document document = Jsoup
                    .connect("https://shikimori.me/" + userName + "/list/anime/mylist/" + dataType + "/order-by/rate_score")
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.select("span.action");
            if (temp.html().contains("(")) {
                userListCounts.put(dataType, Integer.parseInt(temp.html().substring(temp.html().indexOf("(") + 1, temp.html().indexOf(")"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getNumberOfPages(int size) {
        try {
            int numberOfPages = size / 400;
            if (size % 400 != 0) {
                numberOfPages += 1;
            }
            return numberOfPages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void getListOf(int numberOfPages, List<Integer> userDrops, String page) throws IOException {
        List<String> str = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            Document document = Jsoup
                    .connect("https://shikimori.me/" + userName + "/list/anime/mylist/"
                            + page + "/order-by/rate_score/page/" + i)
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.getElementsByAttribute("data-target_id");
            str.addAll(temp.eachAttr("data-target_id"));
        }
        for (int i = 0; i < str.size(); i++) {
            userDrops.add(i, Integer.parseInt(str.get(i)));
        }
    }

    private void getListOfCompletes() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("completed"));
            getListOf(numberOfPages, userCompletes, "completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListOfWatching() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("watching"));
            getListOf(numberOfPages, userWatching, "watching");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListOfDrops() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("dropped"));
            getListOf(numberOfPages, userDrops, "dropped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListOfRewatch() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("rewatching"));
            getListOf(numberOfPages, userRewatching, "rewatching");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListOfOnHold() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("on_hold"));
            getListOf(numberOfPages, userOnHold, "on_hold");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListOfPlanning() {
        try {
            int numberOfPages = getNumberOfPages(userListCounts.get("planned"));
            getListOf(numberOfPages, userPlanning, "planned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void numbersCollector() {
        try {
            zeroesFill();
            getNumberOf("planned");
            getNumberOf("watching");
            getNumberOf("completed");
            getNumberOf("on_hold");
            getNumberOf("dropped");
            getNumberOf("rewatching");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zeroesFill() {
        try {
            userListCounts.put("planned", 0);
            userListCounts.put("watching", 0);
            userListCounts.put("completed", 0);
            userListCounts.put("on_hold", 0);
            userListCounts.put("dropped", 0);
            userListCounts.put("rewatching", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
