package com.mygdx.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.clicker.Screens.SplashScreen;
import com.mygdx.clicker.Service.ScoreService;
import com.mygdx.clicker.Service.SoundService;

public class ClickerGame extends Game {

	public static  String GAME_NAME = "Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private SoundService soundService;
	private ScoreService scoreService;

	private boolean paused;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		initSoundService();
		initScoreService();
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

}
