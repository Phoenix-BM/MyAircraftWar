package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BaseProp;

import java.awt.*;
import java.util.List;

public abstract class AbstractEnemy extends AbstractAircraft {

    protected int score;
    protected int power;

    public AbstractEnemy(int locationX, int locationY, double speedX, double speedY, int hp, int score, int power) {
        super(locationX, locationY, speedX, speedY, hp);
        this.score = score;
        this.power = power;
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
    public abstract int bombUpdate();
    public void increaseAttributes(int hpIncrease, double speedIncrease, int powerIncrease) {
        this.hp += hpIncrease;
        this.speedX *= (1 + speedIncrease);
        this.speedY *= (1 + speedIncrease);
        this.power += powerIncrease;
    }
    public void drawHpBar(Graphics g) {
        int x = getLocationX() - getImage().getWidth() / 2;
        int y = getLocationY() - getImage().getHeight() / 2 - 10; // 血条在飞机上方
        int width = getImage().getWidth();
        int height = 5; // 血条高度
        int baseHp1 = 30;
        int baseHp2 = 45;
        int baseHp3 = 60;
        int hpBarWidth;

        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);

        g.setColor(Color.GREEN);
        if(getHp() <= baseHp1)
            hpBarWidth = (int) (width * ((double) getHp() / baseHp1));
        else if(getHp() <= baseHp2)
            hpBarWidth = (int) (width * ((double) getHp() / baseHp2));
        else
            hpBarWidth = (int) (width * ((double) getHp() / baseHp3));
        g.fillRect(x, y, hpBarWidth, height);
    }
}
