package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.LinkedList;
import java.util.List;

public class SurroundingShoot implements Strategy {
    private int shootnum = 20;
    private int power = 20;
    private int direction = 1;
    @Override
    public List<BaseBullet> shootStrategy(AbstractAircraft abstractAircraft) {
        if (abstractAircraft instanceof HeroAircraft){
            direction = -1;
            power = 30;
        }
        List<BaseBullet> res = new LinkedList<>();
        if(abstractAircraft instanceof BossEnemy || abstractAircraft instanceof HeroAircraft){
            int x = abstractAircraft.getLocationX();
            int y = abstractAircraft.getLocationY() + direction * 2;
            double speedX = 0;
            double speedY = abstractAircraft.getSpeedY() + direction * 5;
            int r = 200;
            BaseBullet bullet;
            if (abstractAircraft instanceof BossEnemy){
                for (int i = 0; i < shootnum; i++) {
                    bullet = new EnemyBullet((int)(x + r * Math.cos(Math.PI / 10 * i)), (int)(y + r * Math.sin(Math.PI / 10 * i)), (int)(Math.cos(Math.PI / 10 * i) * 3), (int)(Math.sin(Math.PI / 10 * i) * 3), power);
                    res.add(bullet);
                }
            } else {
                for (int i = 0; i < shootnum; i++) {
                    bullet = new HeroBullet((int) (x + r * Math.cos(Math.PI / 10 * i)), (int) (y + r * Math.sin(Math.PI / 10 * i)), (int)(Math.cos(Math.PI / 10 * i) * 3), (int)(Math.sin(Math.PI / 10 * i) * 3), power);
                    res.add(bullet);
                }
            }
        }
        return res;
    }
}
