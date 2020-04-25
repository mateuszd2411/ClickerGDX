package com.mygdx.clicker.Screens;

import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.Entities.Player;
import com.mygdx.clicker.Ui.IClickCallback;
import com.mygdx.clicker.Ui.PlayerButton;
import com.mygdx.clicker.Ui.ResetScoreButton;
import com.mygdx.clicker.Ui.ScoreLabel;

public class GameplayScreen extends AbstractScreen{

    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private ScoreLabel scoreLabel;

    public GameplayScreen(ClickerGame game) {
        super(game);
    }

    protected void init() {
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
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

        System.out.println("Points " + game.getPoints());

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();
    }
}

