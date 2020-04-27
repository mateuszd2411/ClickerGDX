package com.mygdx.clicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.clicker.ClickerGame;
import com.mygdx.clicker.Controllers.FlyingObjectController;
import com.mygdx.clicker.Controllers.RandomEventController;
import com.mygdx.clicker.Entities.Player;
import com.mygdx.clicker.Service.FeatureFlagService;
import com.mygdx.clicker.Service.PassiveIncomeService;
import com.mygdx.clicker.Ui.BasicDialog;
import com.mygdx.clicker.Ui.IClickCallback;
import com.mygdx.clicker.Ui.PlayerButton;
import com.mygdx.clicker.Ui.ResetScoreButton;
import com.mygdx.clicker.Ui.GameLabel;


public class GameplayScreen extends AbstractScreen {

    private Image bgImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private GameLabel scoreLabel;
    private FlyingObjectController flyingObjectController;
    private PassiveIncomeService passiveIncomeService;
    private RandomEventController randomEventController;

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
        initPassiveIncomeService();
        initPassiveIncomeInfoDialog();
        initRandomEventController();
        startShop();
    }

    private void startShop() {
        if (game.getFeatureFlagService().hasFeature(FeatureFlagService.FEATURE_SHOP)){
            game.getShopService().dummyMethod();
        }
    }

    private void initRandomEventController() {
        randomEventController = new RandomEventController(game,stage);
    }

    private void initPassiveIncomeInfoDialog() {
        if (passiveIncomeService.getPointsToAdd() > 0){
            BasicDialog basicDialog = new BasicDialog();
            basicDialog.showDialog(stage,"Passive Income gained: " + passiveIncomeService.getPointsToAdd());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGamestate();
    }

    private void update() {
        scoreLabel.setText("Score: " + game.getScoreService().getPoints());
        stage.act();
    }

    private void initPassiveIncomeService() {
        passiveIncomeService = new PassiveIncomeService(game.getScoreService());
        passiveIncomeService.start();
    }

    private void startTheMisuc() {
        game.getSoundService().startPlayingMusic(true);
    }


    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void initBg() {
        bgImage = new Image(new Texture("bg.png"));
        stage.addActor(bgImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().resetGameScore();
            }
        });


        stage.addActor(resetScoreButton);
    }

    private void initScoreLabel() {
        scoreLabel = new GameLabel();
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
            }
        });

        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

}

