package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreService {

    public final  static  String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final  static  String GAME_SCORE = "com.mygdx.clicker.prefs.score";

    private Preferences prefs;

    private int points;

    public ScoreService(){
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    public void addPoint(){
        points++;
        updateSavedScoreInPrefs();
    }

    public void resetGameScore() {
        points = 0;
        updateSavedScoreInPrefs();
    }

    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.flush();
    }

    public void addPassiveIncome() {
        //todo implement
        System.out.println("passive income type");
    }

    public int getPoints() {
        return points;
    }

}
