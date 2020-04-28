package com.mygdx.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.clicker.Screens.SplashScreen;
import com.mygdx.clicker.Service.BalanceService;
import com.mygdx.clicker.Service.FeatureFlagService;
import com.mygdx.clicker.Service.ScoreService;
import com.mygdx.clicker.Service.ShopService;
import com.mygdx.clicker.Service.SoundService;

public class ClickerGame extends Game {

	public static  String GAME_NAME = "Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private SoundService soundService;
	private ScoreService scoreService;
	private FeatureFlagService featureFlagService;
	private BalanceService balanceService;
	private ShopService shopService;

	private boolean paused;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		initSoundService();
		initScoreService();
		initfeatureFlagService();
		initShopService();
		initBalanceService();
	}

	private void initBalanceService() {
		balanceService = new BalanceService();
	}

	private void initShopService() {
		shopService = new ShopService();
	}

	private void initfeatureFlagService() {
        featureFlagService = new FeatureFlagService();
    }

    private void initScoreService() {
		scoreService = new ScoreService();
	}

	private void initSoundService() {
		soundService = new SoundService();
	}


	/**
	 * getters and setters
	 */

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

    public SoundService getSoundService() {
        return soundService;
    }

	public ScoreService getScoreService() {
		return scoreService;
	}

    public FeatureFlagService getFeatureFlagService() {
        return featureFlagService;
    }

	public ShopService getShopService() {
		return shopService;
	}

	public BalanceService getBalanceService() {
		return balanceService;
	}
}
