package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.Strategy;

import java.util.LinkedList;
import java.util.List;

public class ScatteringShoot implements Strategy {
    private int shootnum = 3;
    private int power = 20;
    private int direction = 1;
    @Override
    public List<BaseBullet> shootStrategy(AbstractAircraft abstractAircraft) {
        if (abstractAircraft instanceof HeroAircraft){
            direction = -1;
            power = 30;
        }
        List<BaseBullet> res = new LinkedList<>();
        if (abstractAircraft instanceof ElitePlusEnemy || abstractAircraft instanceof HeroAircraft) {
            int x = abstractAircraft.getLocationX();
            int y = abstractAircraft.getLocationY() + direction * 2;
            int speedX = direction * 2;
            int speedY = abstractAircraft.getSpeedY() + direction * 5;
            BaseBullet bullet;
            if (abstractAircraft instanceof ElitePlusEnemy) {
                for (int i = 0; i < shootnum; i++) {
                    bullet = new EnemyBullet(x + (i * 2 - shootnum + 1) * 10, y, speedX * (i * 2 - shootnum + 1), speedY, power);
                    res.add(bullet);
                }
            } else {
                for (int i = 0; i < shootnum; i++) {
                    bullet = new HeroBullet(x + (i * 2 - shootnum + 1) * 10, y, speedX * (shootnum - i * 2 - 1), speedY, power);
                    res.add(bullet);
                }
            }
        }
        return res;
    }
}
