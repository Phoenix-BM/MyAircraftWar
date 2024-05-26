package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

public class EnemySubject {
    private List<AbstractEnemy> enemies;
    private List<BaseBullet> enemyBullets;
    public EnemySubject(){
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
    }
    public void addEnemy(AbstractEnemy abstractEnemy){
        enemies.add(abstractEnemy);
    }
    public void addBullet(BaseBullet baseBullet){
        enemyBullets.add(baseBullet);
    }

    public void removeEnemy(AbstractEnemy abstractEnemy){
        enemies.remove(abstractEnemy);
    }

    public int notifyAll(int score){
        for(AbstractEnemy abstractEnemy : enemies){
            score += abstractEnemy.bombUpdate();
        }
        for(BaseBullet baseBullet : enemyBullets){
            baseBullet.bombUpdate();
        }
        return score;
    }
}
