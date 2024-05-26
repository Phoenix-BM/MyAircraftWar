package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.ScatteringShoot;

import java.util.List;

public class PropBullet extends BaseProp {
    private static final int EFFECT_DURATION_MS = 5000;
    public PropBullet (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int effect(List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets){

        Runnable r = () -> { try {
            HeroAircraft.getInstance().setStrategy(new ScatteringShoot());

            Thread.sleep(EFFECT_DURATION_MS);

            HeroAircraft.getInstance().setStrategy(new DirectShoot());

        } catch (InterruptedException e) {
            e.printStackTrace(); }
        };
        new Thread(r, "Thread1").start();

        System.out.println("FireSupply active!");

        return 0;
    }
}

