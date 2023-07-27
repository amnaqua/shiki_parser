package org.example.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class AuthorAchievementComparator {
    String authorId;

    List<Integer> authorAnimeIds = new ArrayList<>();
    List<Integer> achievementIds = new ArrayList<>();
    List<Integer> difference = new ArrayList<>();

    Map<String, String> authorsNames = new HashMap<>();

    public AuthorAchievementComparator(String id) {
        this.authorId = id;
        fillAuthorsNames();
        getAuthorIds();
        getAchievementIds();
        comparator();
        System.out.println(difference);
    }

    private void comparator() {
        Collections.sort(achievementIds);
        Collections.sort(authorAnimeIds);
        for (Integer authorAnimeId : authorAnimeIds) {
            for (int j = 0; j < achievementIds.size(); j++) {
                if (!achievementIds.contains(authorAnimeId)) {
                    difference.add(authorAnimeId);
                    break;
                }
            }
        }
    }

    private void getAuthorIds() {
        try {
            Document document = Jsoup
                    .connect("https://shikimori.me/people/" + authorId + "/works?order_by=date&type=Anime")
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.getElementsByAttribute("data-track_user_rate");
            List<String> str = new ArrayList<>(temp.eachAttr("data-track_user_rate"));
            List<Integer> input = new ArrayList<>();
            for (String s : str) {
                String[] split = s.split(":");
                input.add(Integer.parseInt(split[2]));
            }
            authorAnimeIds = input;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAchievementIds() {
        try {
            Document document = Jsoup
                    .connect("https://shikimori.me/achievements/author/" + authorsNames.get(authorId))
                    .userAgent("Chrome/113.0.0.0 Safari/537.36")
                    .get();
            Elements temp = document.getElementsByAttribute("data-track_user_rate");
            List<String> str = new ArrayList<>(temp.eachAttr("data-track_user_rate"));
            List<Integer> input = new ArrayList<>();
            for (String s : str) {
                String[] split = s.split(":");
                input.add(Integer.parseInt(split[2]));
            }
            achievementIds = input;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillAuthorsNames() {
        try {
            authorsNames.put("5088", "tetsurou_araki");
            authorsNames.put("2762", "tensai_okamura");
            authorsNames.put("1117", "makoto_shinkai");
            authorsNames.put("1870", "hayao_miyazaki");
            authorsNames.put("2349", "hiroyuki_imaishi");
            authorsNames.put("2009", "shinichiro_watanabe");
            authorsNames.put("6369", "hiroshi_hamasaki");
            authorsNames.put("10308", "gen_urobuchi");
            authorsNames.put("5111", "hideaki_anno");
            authorsNames.put("1938", "osamu_tezuka");
            authorsNames.put("2582", "type_moon");
            authorsNames.put("5579", "isao_takahata");
            authorsNames.put("5066", "kouji_morimoto");
            authorsNames.put("5068", "masaaki_yuasa");
            authorsNames.put("5921", "morio_asaka");
            authorsNames.put("4580", "satoshi_kon");
            authorsNames.put("4097", "mamoru_oshii");
            authorsNames.put("2609", "masamune_shirow");
            authorsNames.put("7454", "shinji_aramaki");
            authorsNames.put("5812", "yoshiaki_kawajiri");
            authorsNames.put("5348", "kenji_kamiyama");
            authorsNames.put("1877", "clamp");
            authorsNames.put("2992", "go_nagai");
            authorsNames.put("1872", "katsuhiro_otomo");
            authorsNames.put("2967", "yoshitaka_amano");
            authorsNames.put("5393", "osamu_dezaki");
            authorsNames.put("2597", "leiji_matsumoto");
            authorsNames.put("7305", "rintaro");
            authorsNames.put("1891", "rumiko_takahashi");
            authorsNames.put("5094", "kouichi_mashimo");
            authorsNames.put("5089", "akiyuki_shinbou");
            authorsNames.put("6831", "chiaki_kon");
            authorsNames.put("5067", "mamoru_hosoda");
            authorsNames.put("5957", "ryousuke_takahashi");
            authorsNames.put("6771", "yasuhiro_takemoto");
            authorsNames.put("2885", "key");
            authorsNames.put("6414", "toshio_maeda");
            authorsNames.put("6671", "takahiro_oomori");
            authorsNames.put("2501", "shoji_kawamori");
            authorsNames.put("5978", "mari_okada");
            authorsNames.put("2337", "yoshiyuki_tomino");
            authorsNames.put("4002", "junichi_satou");
            authorsNames.put("7217", "kenji_nakamura");
            authorsNames.put("5082", "kunihiko_ikuhara");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
