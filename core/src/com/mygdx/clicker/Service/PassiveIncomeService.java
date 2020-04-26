package com.mygdx.clicker.Service;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.concurrent.TimeUnit;

public class PassiveIncomeService {

    private ScoreService scoreService;

    private final static int INFINITE = 100_000_000;

    public PassiveIncomeService(ScoreService scoreService){
        this.scoreService = scoreService;
        calculateGainedPassiveIncome();
    }

    public void start(){

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                scoreService.addPoints(scoreService.getPassiveIncome());
            }
        },1,1,INFINITE);

    }

    private void calculateGainedPassiveIncome() {
        long savedTimestamp = scoreService.getSavedTimestamp();
        if (savedTimestamp > 0){
            long millisPass = TimeUtils.timeSinceMillis(savedTimestamp);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisPass);
            addPointsBasedOnPassedTime(seconds);
        }else {
            // do nothing
        }
    }

    private void addPointsBasedOnPassedTime(long seconds){
        if(seconds > 0){
            int points = (int) (seconds * scoreService.getPassiveIncome());
            points = points / 5;
            scoreService.addPoints(points);
        }
    }


}
