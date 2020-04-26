package com.mygdx.clicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.Entities.FlyingObject;
import com.mygdx.clicker.Entities.FlyingObject.FlyingObjectType;

public class FlyingObjectController {

    private int spawnTime;

    public FlyingObjectController(ClickerGame game, Stage stage){
        init(game,stage);
    }

    private void init(final ClickerGame game, final Stage stage) {
        randomizeSpawnTime();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {

                FlyingObject flyingObject = null;

                if (MathUtils.randomBoolean()){
                    flyingObject = new FlyingObject(FlyingObjectType.MONEY,game);
                }else {
                    flyingObject = new FlyingObject(FlyingObjectType.PASSIVE,game);
                }

                stage.addActor(flyingObject);
                flyingObject.flylikeHell();

                randomizeSpawnTime();
            }
        },spawnTime,spawnTime);
    }

    private void randomizeSpawnTime(){
        spawnTime = MathUtils.random(5,10);
    }


}
