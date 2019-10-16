package com.andersenlab.infomedia.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisMatchServiceImplTest {

    private final String firstPlayerName = "first player";
    private final String secondPlayerName = "second player";

    private TennisMatchServiceImpl tennisMatchService;

    @BeforeEach
    public void init() {
        tennisMatchService = new TennisMatchServiceImpl(firstPlayerName, secondPlayerName);
    }

    @Test
    public void firstPlayerWonPoint() {
        final String expected = "0 - 0, 15 - 0";
        tennisMatchService.wonPoint(firstPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void secondPlayerWonPoint() {
        final String expected = "0 - 0, 0 - 15";
        tennisMatchService.wonPoint(secondPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void firstPlayerWonGame() {
        final String expected = "1 - 0, 0 - 0";
        for (int i = 0; i < 4; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
        }
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void secondPlayerWonGame() {
        final String expected = "0 - 1, 0 - 0";
        for (int i = 0; i < 4; i++) {
            tennisMatchService.wonPoint(secondPlayerName);
        }
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void firstPlayerWonSet() {
        final String expected = "6 - 0, " + firstPlayerName + " wins!";
        for (int i = 0; i < 24; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
        }
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void secondPlayerWonSet() {
        final String expected = "0 - 6, " + secondPlayerName + " wins!";
        for (int i = 0; i < 24; i++) {
            tennisMatchService.wonPoint(secondPlayerName);
        }
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void firstPlayerAdvantage() {
        final String expected = "0 - 0, Advantage " + firstPlayerName;
        for (int i = 0; i < 5; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
            tennisMatchService.wonPoint(secondPlayerName);
        }
        tennisMatchService.wonPoint(firstPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void secondPlayerAdvantage() {
        final String expected = "0 - 0, Advantage " + secondPlayerName;
        for (int i = 0; i < 5; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
            tennisMatchService.wonPoint(secondPlayerName);
        }
        tennisMatchService.wonPoint(secondPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void firstPlayerWonPointWithAdvantage() {
        final String expected = "1 - 0, 0 - 0";
        for (int i = 0; i < 5; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
            tennisMatchService.wonPoint(secondPlayerName);
        }
        tennisMatchService.wonPoint(firstPlayerName);
        tennisMatchService.wonPoint(firstPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void secondPlayerWonPointWithAdvantage() {
        final String expected = "0 - 1, 0 - 0";
        for (int i = 0; i < 5; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
            tennisMatchService.wonPoint(secondPlayerName);
        }
        tennisMatchService.wonPoint(secondPlayerName);
        tennisMatchService.wonPoint(secondPlayerName);
        assertEquals(expected, tennisMatchService.getScore());
    }

    @Test
    public void deuce() {
        final String expected = "0 - 0, Deuce";
        for (int i = 0; i < 5; i++) {
            tennisMatchService.wonPoint(firstPlayerName);
            tennisMatchService.wonPoint(secondPlayerName);
        }
        assertEquals(expected, tennisMatchService.getScore());
    }
}
