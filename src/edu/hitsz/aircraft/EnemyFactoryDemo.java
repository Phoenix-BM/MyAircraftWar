package edu.hitsz.aircraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactoryDemo {
    private EnemyFactory enemyFactory;
    private AbstractEnemy abstractEnemy;
    static int lastScore = 0;
    public void createEnemyDemo (List<AbstractEnemy> enemyAircrafts, int score) {

        Random rand = new Random();
        List<EnemyFactory> enemyFactories = new ArrayList<>();

        // 添加不同类型的敌机工厂和它们的权重
        List<WeightedEnemyFactory> weightedFactories = new ArrayList<>();
        weightedFactories.add(new WeightedEnemyFactory(new MobEnemyFactory(), 50));  // 高概率
        weightedFactories.add(new WeightedEnemyFactory(new EliteEnemyFactory(), 30)); // 中等概率
        weightedFactories.add(new WeightedEnemyFactory(new ElitePlusEnemyFactory(), 20)); // 低概率

        if(score >= 100 && score / 100 > lastScore / 100){
            enemyFactory = new BossEnemyFactory();
            lastScore = score;
        } else{
            enemyFactory = getWeightedEnemyFactory(weightedFactories);
        }

        abstractEnemy = enemyFactory.createEnemy();
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
