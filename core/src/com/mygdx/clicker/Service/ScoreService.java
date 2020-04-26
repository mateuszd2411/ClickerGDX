package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreService {

    public final  static  String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final  static  String GAME_SCORE = "com.mygdx.clicker.prefs.score";
    public final  static  String GAME_PASSIVE_INCOME = "com.mygdx.clicker.prefs.passiveincome";

    private Preferences prefs;

    private int points;
    private int passiveIncome;

    public ScoreService(){
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
        loadPassiveIncome();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

    private void loadPassiveIncome(){
        passiveIncome = prefs.getInteger(GAME_PASSIVE_INCOME);
    }

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
        updateSavedScoreAndPassiceIncomeInPrefs();
    }

    public void addPoint(){
        points++;
        updateSavedScoreAndPassiceIncomeInPrefs();
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
        updateSavedScoreAndPassiceIncomeInPrefs();
    }

    private void updateSavedScoreAndPassiceIncomeInPrefs() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.putInteger(GAME_PASSIVE_INCOME,passiveIncome);
        prefs.flush();
    }

    public void addPassiveIncome() {
       passiveIncome++;
       updateSavedScoreAndPassiceIncomeInPrefs();
    }

    public int getPoints() {
        return points;
    }

    public int getPassiveIncome() {
        return passiveIncome;
    }
}
