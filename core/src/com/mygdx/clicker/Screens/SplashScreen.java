package com.mygdx.clicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.clicker.ClickerGame;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(ClickerGame game) {
        super(game);
        init();
    }

    private void init() {
        //todo implement better assets loading when game grows
        splashImg = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg,0,0);
        spriteBatch.end();
    }
}
