package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;

import java.util.List;

public abstract class AbstractEnemy extends AbstractFlyingObject {

    protected int hp;
    protected int score;

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.score = score;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getScore(){
        return score;
    }

    public abstract List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy eliteEnemy);

    public abstract List<BaseBullet> shoot();


}
