package com.andersenlab.infomedia;

import com.andersenlab.infomedia.service.TennisMatchService;
import com.andersenlab.infomedia.service.impl.TennisMatchServiceImpl;

import java.util.Random;

public class Application {

    public static void main(String[] args) {
        String firstPlayerName = "Alien";
        String secondPlayerName = "Predator";
        if (args.length == 2) {
            firstPlayerName = args[0];
            secondPlayerName = args[1];
        }

        TennisMatchService tennisMatchService = new TennisMatchServiceImpl(firstPlayerName, secondPlayerName);
        Random random = new Random();

        System.out.printf("%s vs %s\n\nStart set\n\n", firstPlayerName, secondPlayerName);
        do {
            int result = random.nextInt(10);
            if (result > 4) {
                tennisMatchService.wonPoint(firstPlayerName);
            } else {
                tennisMatchService.wonPoint(secondPlayerName);
            }
            System.out.println(tennisMatchService.getScore());
        } while (!tennisMatchService.isSetFinished());

        System.out.println("\nSet ends.");
    }
}
