package org.example.app;

import org.example.collectors.AuthorsDataCollector;
import org.example.collectors.CompaniesNamesCollector;
import org.example.collectors.FranchisesDataCollector;
import org.example.models.User;
import org.example.utility.AuthorAchievementComparator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //AuthorAchievementComparator authorAchievementComparator = new AuthorAchievementComparator("5088");
//        User user = new User();
//        FranchisesDataCollector franchisesDataCollector = new FranchisesDataCollector(user);
//        System.out.println(franchisesDataCollector.getListOfFranchises());
//        System.out.println(franchisesDataCollector);
        CompaniesNamesCollector companiesNamesCollector = new CompaniesNamesCollector();
    }
}