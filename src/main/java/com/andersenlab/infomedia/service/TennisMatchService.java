package com.andersenlab.infomedia.service;

public interface TennisMatchService {

    boolean isSetFinished();

    void wonPoint(String playerName);

    String getScore();
}
