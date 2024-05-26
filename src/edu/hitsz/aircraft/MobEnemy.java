package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy {
    private int mobEnemyScore = 10;
    private int power = 0;
    public MobEnemy(int locationX, int locationY, double speedX, double speedY, int hp, int score, int power) {
        super(locationX, locationY, speedX, speedY, hp, score, power);
        this.score = mobEnemyScore;
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy eliteEnemy){
        return new LinkedList<>();
    }
    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public List<BaseBullet> executeStrategy(AbstractAircraft abstractAircraft){
        return new LinkedList<>();
    }

    @Override
    public int bombUpdate(){
        this.vanish();
        return this.score;
    }
}
