package edu.hitsz.aircraft;

import edu.hitsz.application.Game.Game;
import edu.hitsz.application.Game.NormalGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactoryDemo {
    private EnemyFactory enemyFactory;
    private AbstractEnemy abstractEnemy;
    static int lastScore = 0;
    int mobEnemyWeight = 60;
    int eliteEnemyWeight = 30;
    int bossCount = 0;
    public void createEasyEnemyDemo (List<AbstractEnemy> enemyAircrafts, int score, int enemyHpIncrease, double enemySpeedIncrease, int enemyPowerIncrease, int elitePlusEnemyProbability) {
        Random rand = new Random();
        List<EnemyFactory> enemyFactories = new ArrayList<>();
        List<WeightedEnemyFactory> weightedFactories = new ArrayList<>();


        weightedFactories.add(new WeightedEnemyFactory(new MobEnemyFactory(), mobEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new EliteEnemyFactory(), eliteEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new ElitePlusEnemyFactory(), elitePlusEnemyProbability));

        enemyFactory = getWeightedEnemyFactory(weightedFactories);

        abstractEnemy = enemyFactory.createEnemy();
        enemyAircrafts.add(abstractEnemy);
    }
    public void createNormalEnemyDemo (List<AbstractEnemy> enemyAircrafts, int score, int enemyHpIncrease, double enemySpeedIncrease, int enemyPowerIncrease, int elitePlusEnemyProbability) {

        Random rand = new Random();
        List<EnemyFactory> enemyFactories = new ArrayList<>();
        int bossValidScore = 500;

        // 添加不同类型的敌机工厂和它们的权重
        List<WeightedEnemyFactory> weightedFactories = new ArrayList<>();
        weightedFactories.add(new WeightedEnemyFactory(new MobEnemyFactory(), mobEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new EliteEnemyFactory(), eliteEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new ElitePlusEnemyFactory(), elitePlusEnemyProbability));

        if(score >= bossValidScore && score / bossValidScore > lastScore / bossValidScore){
            enemyFactory = new BossEnemyFactory();
            lastScore = score;
        } else{
            enemyFactory = getWeightedEnemyFactory(weightedFactories);
        }

        abstractEnemy = enemyFactory.createEnemy();
        abstractEnemy.increaseAttributes(enemyHpIncrease, enemySpeedIncrease, enemyPowerIncrease);
        enemyAircrafts.add(abstractEnemy);
    }
    public void createHardEnemyDemo (List<AbstractEnemy> enemyAircrafts, int score, int enemyHpIncrease, double enemySpeedIncrease, int enemyPowerIncrease, int elitePlusEnemyProbability) {

        int bossValidScore = 500;
        int bossHpIncrease = 180;


        // 添加不同类型的敌机工厂和它们的权重
        List<WeightedEnemyFactory> weightedFactories = new ArrayList<>();
        weightedFactories.add(new WeightedEnemyFactory(new MobEnemyFactory(), mobEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new EliteEnemyFactory(), eliteEnemyWeight));
        weightedFactories.add(new WeightedEnemyFactory(new ElitePlusEnemyFactory(), elitePlusEnemyProbability));

        if(score >= bossValidScore && score / bossValidScore > lastScore / bossValidScore){
            enemyFactory = new BossEnemyFactory();

            AbstractEnemy bossEnemy = enemyFactory.createEnemy();
            int bossHp = bossEnemy.getHp();
            bossEnemy.setHp(bossHp + bossHpIncrease * bossCount);
            bossCount++;

            System.out.println("BossHp: " + bossEnemy.getHp());
            lastScore = score;
        } else{
            enemyFactory = getWeightedEnemyFactory(weightedFactories);
        }

        abstractEnemy = enemyFactory.createEnemy();
        abstractEnemy.increaseAttributes(enemyHpIncrease, enemySpeedIncrease, enemyPowerIncrease);
        enemyAircrafts.add(abstractEnemy);
    }

    // 通过权重调整敌机出现的几率
    private class WeightedEnemyFactory {
        EnemyFactory factory;
        int weight;
        public WeightedEnemyFactory(EnemyFactory factory, int weight) {
            this.factory = factory;
            this.weight = weight;
        }
    }
    private EnemyFactory getWeightedEnemyFactory(List<WeightedEnemyFactory> weightedFactories) {
        int totalWeight = weightedFactories.stream().mapToInt(f -> f.weight).sum();
        int randomValue = new Random().nextInt(totalWeight) + 1;
        int weightSum = 0;

        for (WeightedEnemyFactory wef : weightedFactories) {
            weightSum += wef.weight;
            if (randomValue <= weightSum) {
                return wef.factory;
            }
        }
        return null;
    }
}
