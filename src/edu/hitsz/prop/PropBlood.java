package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class PropBlood extends BaseProp {
    private final int hp;
    public PropBlood (int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }

    @Override
    public int effect(List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets){
        HeroAircraft.getInstance().increaseHp(getHp());
        System.out.println("HpSupply active!");
        return 0;
    }
}
