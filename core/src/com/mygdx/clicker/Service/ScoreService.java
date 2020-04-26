package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public class ScoreService {

    public final  static  String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final  static  String GAME_SCORE = "com.mygdx.clicker.prefs.score";
    public final  static  String GAME_PASSIVE_INCOME = "com.mygdx.clicker.prefs.passiveincome";
    public final  static  String GAME_SAVE_TIMESTAMP = "com.mygdx.clicker.prefs.savedtimestamp";

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
        calculateGainedPassiveIncome();
    }

    private void calculateGainedPassiveIncome() {
        long savedTimestamp = getSavedTimestamp();
        if (getSavedTimestamp() > 0){
            long millisPass = TimeUtils.timeSinceMillis(savedTimestamp);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisPass);
            System.out.println("Passed seconds: " + seconds);
        }else {
            // do nothing
        }
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

    public long getSavedTimestamp(){
        return prefs.getLong(GAME_SAVE_TIMESTAMP);
    }

    public void saveCurrentTimestamp() {
        prefs.putLong(GAME_SAVE_TIMESTAMP,TimeUtils.millis());
        prefs.flush();

    }
}
