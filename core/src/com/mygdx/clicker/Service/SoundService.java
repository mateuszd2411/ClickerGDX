package com.mygdx.clicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundService {

    private Sound moneySound;

    private Music music;

    public SoundService() {
        init();
    }

    private void init() {
        moneySound = Gdx.audio.newSound(Gdx.files.internal("sound/money1.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));
    }

    public void playMoneySound() {
        moneySound.play();
    }

    public void startPlayingMusic(boolean looped){
        music.setVolume(0.1f);
        music.play();
        music.setLooping(looped);
    }
}
