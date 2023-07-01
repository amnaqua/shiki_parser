package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.example.collectors.UserDataCollector;
import org.example.interfaces.Achievement;

import java.util.*;

@Getter
@Setter
public class User {
    private final String userName;

    private List<Achievement> achievementsList;

    private final Integer userNumberOfAnimeCompletes;
    private final Integer userNumberOfAnimeDrops;
    private final Integer userNumberOfAnimeRewatch;
    private final Integer userNumberOfAnimeWatching;
    private final Integer userNumberOfAnimePlanned;

    private final Map<String, List<Integer>> list = new HashMap<>();

    public User() {
        UserDataCollector userDataCollector = new UserDataCollector();
        userName = userDataCollector.getUserName();
        userNumberOfAnimeCompletes = userDataCollector.getUserCompletes().size();
        userNumberOfAnimeDrops = userDataCollector.getUserDrops().size();
        userNumberOfAnimeRewatch = userDataCollector.getUserRewatching().size();
        userNumberOfAnimeWatching = userDataCollector.getUserWatching().size();
        userNumberOfAnimePlanned = userDataCollector.getUserPlanning().size();
        fillTheList(userDataCollector);
    }

    private void fillTheList(UserDataCollector userDataCollector) {
        list.put("planned", userDataCollector.getUserPlanning());
        list.put("watching", userDataCollector.getUserWatching());
        list.put("completed", userDataCollector.getUserCompletes());
        list.put("dropped", userDataCollector.getUserDrops());
        list.put("rewatching", userDataCollector.getUserRewatching());
        list.put("on_hold", userDataCollector.getUserOnHold());
    }
}
