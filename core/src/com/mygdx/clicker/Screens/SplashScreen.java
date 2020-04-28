package com.mygdx.clicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.IRequestCallback;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg,noInternetImg;
    private boolean showError = false;

    public SplashScreen(final ClickerGame game) {
        super(game);
        init();
    }

    protected void init() {
        //todo implement better assets loading when game grows
        splashImg = new Texture("splash.png");
        noInternetImg = new Texture("nointernet.png");

        game.getFeatureFlagService().makeFlagsRequest(new IRequestCallback() {
            @Override
            public void onSucceed() {
                showError = false;

                game.getBalanceService().makeBalanceServiceRequest(new IRequestCallback() {
                    @Override
                    public void onSucceed() {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                // process the result, e.g. add it to an Array<Result> field of the ApplicationListener.
                                game.setScreen(new GameplayScreen(game));
                            }
                        });
                    }

                    @Override
                    public void onError() {
                        showError = true;
                    }
                });

            }

            @Override
            public void onError() {
                showError = true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        if (showError){
            spriteBatch.draw(noInternetImg,0,0);
        }else {
            spriteBatch.draw(splashImg,0,0);
        }
        spriteBatch.end();
    }
}
