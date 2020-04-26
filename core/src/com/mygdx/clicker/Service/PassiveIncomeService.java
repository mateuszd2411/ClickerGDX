package com.mygdx.clicker.Service;

import com.badlogic.gdx.utils.Timer;

public class PassiveIncomeService {

    private ScoreService scoreService;

    private final static int INFINITE = 100_000_000;

    public PassiveIncomeService(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    public void start(){

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                scoreService.addPoints(scoreService.getPassiveIncome());
            }
        },1,1,INFINITE);

    }
}
