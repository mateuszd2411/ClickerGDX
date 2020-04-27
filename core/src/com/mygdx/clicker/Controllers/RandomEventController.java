package com.mygdx.clicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.clicker.ClickerGame;

public class RandomEventController {

    private final int RANDOM_TICK_INTERVAL = 5;     /// todo change after initial implementation

    private ClickerGame game;
    private Stage stage;

    public RandomEventController(ClickerGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        init();
    }

    private void init(){

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (MathUtils.randomBoolean()){
                    triggerRandomEvent();
                }
            }
        },RANDOM_TICK_INTERVAL,RANDOM_TICK_INTERVAL);

    }

    private void triggerRandomEvent() {
        int randomNumber = MathUtils.random(1,3);
        switch (randomNumber){
            case 1:
                gainMoneyEvent();
                break;
            case 2:
                loseMoneyEvent();
                break;
            case 3:
                gainPassiveIncome();
                default:
                    break;
        }

    }

    private void gainPassiveIncome() {
        game.getScoreService().addPassiveIncome();
    }

    private void gainMoneyEvent(){
        game.getScoreService().addPoints(123);
    }

    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-123);
    }
}
