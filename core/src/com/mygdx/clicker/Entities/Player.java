package com.mygdx.clicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {

    private final static int WIDHT = 77;
    private final static int HEIGHT = 152;

    private final static int STARTING_X = 200;
    private final static int STARTING_Y = 300;

    public Player(){
        super(new Texture("player.png"));


         this.setOrigin(WIDHT/2, HEIGHT/2);
        this.setSize(WIDHT,HEIGHT);
  // starting position
        this.setPosition(STARTING_X,STARTING_Y);
    }

    public void reactOnClick() {
        int xmoveAmount = MathUtils.random(-130,130);
        int ymoveAmount = 10;
        float moveActionTime = 0.30f;
        Action moveAction = Actions.sequence(
                Actions.moveBy(xmoveAmount,ymoveAmount,moveActionTime, Interpolation.circle),
                Actions.moveBy(-xmoveAmount,-ymoveAmount,moveActionTime, Interpolation.circle)
        );

        int xgrowAmount = MathUtils.random(-30,100);
        int ygrowAmount = 20;
        float growActionTime = 0.2f;
        Action growAction = Actions.sequence(
                Actions.sizeBy(xgrowAmount,ygrowAmount,growActionTime, Interpolation.circle),
                Actions.sizeBy(-xgrowAmount,-ygrowAmount,growActionTime, Interpolation.circle)
        );

    this.addAction(moveAction);
    this.addAction(growAction);

    if (this.getHeight() > 170){
        this.addAction(Actions.rotateBy(MathUtils.randomSign() * 360,0.4f));
    }

    }
}
