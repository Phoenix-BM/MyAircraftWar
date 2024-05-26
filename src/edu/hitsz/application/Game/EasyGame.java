package edu.hitsz.application.Game;

import edu.hitsz.DAO.DAOPatternDemo;
import edu.hitsz.application.ImageManager;

import java.util.concurrent.TimeUnit;

public class EasyGame extends Game {
    public EasyGame(boolean musicState) {
        super(musicState);
        this.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_EASYGAME);
    }

    @Override
    protected void initDifficultyParameters() {
        this.enemyMaxNumber = 5;
        this.cycleDuration = 600;
        this.timeInterval = 40;

        // 设置其他参数，如敌机属性、英雄机和敌机射击周期等
    }
    protected void gameCreateEnemy(){
        if (timeCountAndNewCycleJudge()) {
            System.out.println(time);
            // 新敌机产生

            if (enemyAircrafts.size() < enemyMaxNumber) {
                enemyFactoryDemo.createEasyEnemyDemo(enemyAircrafts, score, enemyHpIncrease, enemySpeedIncrease, enemyPowerIncrease, elitePlusEnemyProbability);
            }
            // 飞机射出子弹
            shootAction();
        }
    }

    @Override
    protected void difficultyIncreasesWithTime(){}
}


