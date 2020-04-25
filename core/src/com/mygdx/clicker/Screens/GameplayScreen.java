package com.mygdx.clicker.Screens;

import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.Entities.Player;

public class GameplayScreen extends AbstractScreen{

    private Player player;

    public GameplayScreen(ClickerGame game) {
        super(game);
    }

    protected void init() {
        initPlayer();
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
        stage.act();
    }
}

