package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemySubject;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class PropBomb extends BaseProp {
    public PropBomb (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int effect(List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets){

        int bombScore = 0;

        EnemySubject enemySubject = new EnemySubject();

        for(AbstractEnemy enemyAircraft : enemyAircrafts){
            enemySubject.addEnemy(enemyAircraft);
        }
        for(BaseBullet bullet : enemyBullets){
            enemySubject.addBullet(bullet);
        }
        bombScore += enemySubject.notifyAll(bombScore);

        System.out.println("BombSupply active!");

        return bombScore;
    }
}

