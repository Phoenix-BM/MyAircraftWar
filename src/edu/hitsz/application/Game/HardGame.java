package edu.hitsz.application.Game;

import edu.hitsz.DAO.DAOPatternDemo;
import edu.hitsz.application.Game.Game;
import edu.hitsz.application.ImageManager;

import java.util.concurrent.TimeUnit;

public class HardGame extends Game {
    public HardGame(boolean musicState){
        super(musicState);
        this.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_HARDGAME);
    }

    @Override
    protected void initDifficultyParameters() {
        this.enemyMaxNumber = 10;
        this.cycleDuration = 400;
        this.timeInterval = 35;

        // 设置其他参数，如敌机属性、英雄机和敌机射击周期等
    }

    @Override
    public void gameCreateEnemy() {
        if (timeCountAndNewCycleJudge()) {
            System.out.println(time);
            // 新敌机产生

            if (enemyAircrafts.size() < enemyMaxNumber) {
                enemyFactoryDemo.createHardEnemyDemo(enemyAircrafts, score, enemyHpIncrease, enemySpeedIncrease, enemyPowerIncrease, elitePlusEnemyProbability);
            }
            // 飞机射出子弹
            shootAction();
        }
    }

    public void difficultyIncreasesWithTime(){
        // 定期增加难度的任务
        executorService.scheduleWithFixedDelay(this::increaseDifficulty, difficultyIncreaseInterval, difficultyIncreaseInterval, TimeUnit.MILLISECONDS);
    }
}

