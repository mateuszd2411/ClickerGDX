package com.mygdx.clicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.Controllers.FlyingObjectController;
import com.mygdx.clicker.Entities.Player;
import com.mygdx.clicker.Ui.IClickCallback;
import com.mygdx.clicker.Ui.PlayerButton;
import com.mygdx.clicker.Ui.ResetScoreButton;
import com.mygdx.clicker.Ui.ScoreLabel;


public class GameplayScreen extends AbstractScreen{

    private Image bgImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private ScoreLabel scoreLabel;
    private FlyingObjectController flyingObjectController;

    public GameplayScreen(ClickerGame game) {
        super(game);
    }

    protected void init() {
        initBg();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
        initFlyingStuffController();
        startTheMisuc();
    }

    private void startTheMisuc() {
        game.getSoundService().startPlayingMusic(true);
    }


    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game,stage);
    }

    private void initBg() {
        bgImage = new Image(new Texture("bg.png"));
        stage.addActor(bgImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.resetGameScore();
            }
        });


        stage.addActor(resetScoreButton);
    }

    private void initScoreLabel() {
        scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
            }
        });

        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();
    }
}

