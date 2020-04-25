package com.mygdx.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.clicker.Screens.SplashScreen;

public class ClickerGame extends Game {

	public static  String GAME_NAME = "Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private boolean paused;

	private int points;


	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}

	public void addPoint(){
		points++;
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
