package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.ScatteringShoot;
import edu.hitsz.bullet.SurroundingShoot;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PropBulletPlus extends BaseProp {
    private static final int EFFECT_DURATION_MS = 5000;
    public PropBulletPlus (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int effect(List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets){

        Runnable r = () -> { try {
            HeroAircraft.getInstance().setStrategy(new SurroundingShoot());

            Thread.sleep(EFFECT_DURATION_MS);

            HeroAircraft.getInstance().setStrategy(new DirectShoot());

        } catch (InterruptedException e) {
            e.printStackTrace(); }
        };
        new Thread(r, "Thread2").start();

        System.out.println("FirePlusSupply active!");

        return 0;
    }
}
