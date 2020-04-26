package com.mygdx.clicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.clicker.ClickerGame;

public class FlyingObject extends Image {

    public enum  FlyingObjectType{
        MONEY,PASSIVE
    }

    public final static String MONEY = "img/money.png";
    public final static String BOOKS = "img/books.png";

    private  final  static int WIDHT = 80;
    private  final  static int HEIGHT = 80;

    private final static int STARTING_X_1 = 0;
    private final static int STARTING_X_2 = ClickerGame.WIDTH;
    private final static int STARTING_Y = -100;

    private int startingX;

    private ClickerGame game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, ClickerGame game){
        super(new Texture(getTextTextureString(type)));

        this.type = type;

        this.game = game;

        this.setOrigin(WIDHT/2, HEIGHT/2);
        this.setSize(WIDHT,HEIGHT);

        playSpawnSound();

        //starting position

//        if (MathUtils.randomBoolean()){
//            startingX = STARTING_X_1;
//        }else {
//            startingX = STARTING_X_2;
//        }


        //// or   ? STARTING_X_1 : STARTING_X_2

        //short if
        startingX = MathUtils.randomBoolean() ? STARTING_X_1 : STARTING_X_2;

        this.setPosition(startingX,STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                reactOnClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    private void playSpawnSound() {
        if (FlyingObjectType.MONEY.equals(type)){
            game.getSoundService().playMoneySound();

        }
    }

    private void reactOnClick() {

        if (FlyingObjectType.MONEY.equals(type)){
            game.getScoreService().addPoints(50);
        } else if (FlyingObjectType.PASSIVE.equals(type)){
            game.getScoreService().addPassiveIncome();
        }

        FlyingObject.this.remove();
    }

    private static String getTextTextureString(FlyingObjectType type) {
        if (FlyingObjectType.MONEY.equals(type)){
            return MONEY;
        } else if (FlyingObjectType.PASSIVE.equals(type)){
            return BOOKS;
        }
        return "";
    }

    public void flylikeHell(){

        int xSign = 0;
        if (startingX == STARTING_X_1){
            xSign = 1;
        }else {
            xSign = -1;
        }

        int time1 = MathUtils.random(1,6);
        int time2 = MathUtils.random(1,6);

        int randomYEffect = MathUtils.random(-100,500);

        Action a = Actions.parallel(
                Actions.moveBy(xSign * 300 + (MathUtils.random(-200,200 + randomYEffect)),200,time1),
                Actions.rotateBy(360,time1)
        );

        Action b = Actions.parallel(
                Actions.moveBy(xSign * -500 + (MathUtils.random(-200,200)),900,time2),
                Actions.rotateBy(360,time2)
        );

        Action c = Actions.run(new Runnable() {
            @Override
            public void run() {
                FlyingObject.this.remove();
            }
        });

        this.addAction(Actions.sequence(a,b,c));


    }

}
