package edu.hitsz.application;

import edu.hitsz.music.MusicThread;
import edu.hitsz.music.SoundThread;


public class MusicManager {

    // Define sound variables
    public static String BACKGROUND_MUSIC;
    public static String BULLET_HIT_SOUND;
    public static String SUPPLY_EFFECT_SOUND;
    public static String BOMB_EXPLOSION_SOUND;
    public static String GAME_OVER_SOUND;
    public static String BOSS_MUSIC;

    static {

        BACKGROUND_MUSIC = "src/videos/bgm.wav";
        BULLET_HIT_SOUND = "src/videos/bullet_hit.wav";
        SUPPLY_EFFECT_SOUND = "src/videos/get_supply.wav";
        BOMB_EXPLOSION_SOUND = "src/videos/bomb_explosion.wav";
        GAME_OVER_SOUND = "src/videos/game_over.wav";
        BOSS_MUSIC="src/videos/bgm_boss.wav";

    }

    private final MusicThread gameBGM;

    public MusicManager() {
        gameBGM = new MusicThread(MusicManager.BACKGROUND_MUSIC);
        // bossMusic在Boss敌机中实现
    }
    public void startGameBGM() {
        gameBGM.start();
    }

    public void startBulletHitSound() {
        MusicThread bulletHitSound = new SoundThread(MusicManager.BULLET_HIT_SOUND);
        bulletHitSound.start();
    }

    public void startBombExplosionSound() {
        MusicThread bombExplosionSound = new SoundThread(MusicManager.BOMB_EXPLOSION_SOUND);
        bombExplosionSound.start();
    }

    public void startSupplyEffectSound() {
        MusicThread supplyEffectSound = new SoundThread(MusicManager.SUPPLY_EFFECT_SOUND);
        supplyEffectSound.start();
    }

    public void startPlayGameOverSound() {
        MusicThread playGameOverSound = new SoundThread(MusicManager.GAME_OVER_SOUND);
        playGameOverSound.start();
    }

    public MusicThread getGameBGM() {
        return gameBGM;
    }

}
