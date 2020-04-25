package com.mygdx.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.clicker.Screens.SplashScreen;

public class ClickerGame extends Game {

	public final  static  String GAME_PREFS = "com.mygdx.clicker.prefs";
	public final  static  String GAME_SCORE = "com.mygdx.clicker.prefs.score";

	public static  String GAME_NAME = "Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private boolean paused;

	private Preferences prefs;

	private int points;


	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		prefs = Gdx.app.getPreferences(GAME_PREFS);
		loadScore();
	}

	private void loadScore() {
		points = prefs.getInteger(GAME_SCORE);
	}

	public void addPoint(){
		points++;
		updateSavedScoreInPrefs();
	}

	public void resetGameScore() {
		points = 0;
		updateSavedScoreInPrefs();
	}

	private void updateSavedScoreInPrefs() {
		prefs.putInteger(GAME_SCORE, points);
		prefs.flush();
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

	public int getPoints() {
		return points;
	}


}
