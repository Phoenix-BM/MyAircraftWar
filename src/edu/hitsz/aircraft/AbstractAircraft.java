package edu.hitsz.aircraft;

import edu.hitsz.bullet.*;
import edu.hitsz.basic.AbstractFlyingObject;

import java.awt.*;
import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    protected Strategy strategy;

    public AbstractAircraft(int locationX, int locationY, double speedX, double speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        hp += increase;
        if(hp >= maxHp){
            hp = maxHp;
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }
    public int getMaxHp(){
        return maxHp;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }


    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

    public List<BaseBullet> executeStrategy(AbstractAircraft abstractAircraft){

//        if(abstractAircraft instanceof EliteEnemy){
//            abstractAircraft.setStrategy(new DirectShoot());
//        }
//        else if(abstractAircraft instanceof ElitePlusEnemy){
//            abstractAircraft.setStrategy(new ScatteringShoot());
//        }
//        else if(abstractAircraft instanceof BossEnemy){
//            abstractAircraft.setStrategy(new SurroundingShoot());
//        }
        return strategy.shootStrategy(abstractAircraft);
    }
    // 在 HeroAircraft 类和 AbstractEnemy 类中添加以下方法

}


