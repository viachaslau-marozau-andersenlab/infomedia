package com.andersenlab.infomedia.service.impl;

import com.andersenlab.infomedia.service.TennisMatchService;

public class TennisMatchServiceImpl implements TennisMatchService {

    private int firstPlayerSetScore = 0;
    private int secondPlayerSetScore = 0;

    private int firstPlayerGameScore = 0;
    private int secondPlayerGameScore = 0;

    private String firstPlayerName;
    private String secondPlayerName;

    public TennisMatchServiceImpl(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    @Override
    public boolean isSetFinished() {
        return firstPlayerSetScore == 6 || secondPlayerSetScore == 6;
    }

    @Override
    public void wonPoint(String playerName) {
        if (isSetFinished()) {
            throw new IllegalStateException("Set already finished");
        }

        if (firstPlayerName.equalsIgnoreCase(playerName)) {
            firstPlayerGameScore++;
            if (isGameFinished()) {
                firstPlayerSetScore++;
                firstPlayerGameScore = 0;
                secondPlayerGameScore = 0;
            }
        } else {
            secondPlayerGameScore++;
            if (isGameFinished()) {
                secondPlayerSetScore++;
                firstPlayerGameScore = 0;
                secondPlayerGameScore = 0;
            }
        }
    }

    @Override
    public String getScore() {
        return String.format("%d - %d, %s", firstPlayerSetScore, secondPlayerSetScore, getGameScore());
    }

    @Override
    public String toString() {
        return this.getScore();
    }

    private String getGameScore() {

        if (isSetFinished()) {
            return getPlayerWithHighScore() + " wins!";
        }
        if (isAdvantage()) {
            return "Advantage " + getPlayerWithHighScore();
        }
        if (isDeuce()) {
            return "Deuce";
        }
        return convertToGameFormat(firstPlayerGameScore) + " - " + convertToGameFormat(secondPlayerGameScore);
    }

    private boolean isDeuce() {
        return firstPlayerGameScore >= 3 && secondPlayerGameScore == firstPlayerGameScore;
    }


    private boolean isGameFinished() {

        if (firstPlayerGameScore >= 4 && firstPlayerGameScore >= secondPlayerGameScore + 2) {
            return true;
        }
        if (secondPlayerGameScore >= 4 && secondPlayerGameScore >= firstPlayerGameScore + 2) {
            return true;
        }
        return false;
    }

    private String getPlayerWithHighScore() {
        if (firstPlayerSetScore > secondPlayerSetScore) {
            return firstPlayerName;
        }
        if (secondPlayerSetScore > firstPlayerSetScore) {
            return secondPlayerName;
        }
        if (firstPlayerGameScore > secondPlayerGameScore) {
            return firstPlayerName;
        } else {
            return secondPlayerName;
        }
    }

    private boolean isAdvantage() {

        if (firstPlayerGameScore >= 4 && firstPlayerGameScore == secondPlayerGameScore + 1) {
            return true;
        }
        if (secondPlayerGameScore >= 4 && secondPlayerGameScore == firstPlayerGameScore + 1) {
            return true;
        }
        return false;
    }

    private String convertToGameFormat(int gameScore) {
        switch (gameScore) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                throw new IllegalArgumentException("Illegal game score value: " + gameScore);
        }
    }
}
