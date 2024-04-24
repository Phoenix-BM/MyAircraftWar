package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BossEnemy extends AbstractEnemy {
    private int shootnum = 20;
    private int power = 20;
    private int direction = 1;
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score) {
        super(locationX, locationY, speedX, speedY, hp, score);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT)
            vanish();
    }

    @Override
    public List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy bossEnemy) {

        PropFactory propFactory;
        List<PropFactory> propFactories;
        BaseProp baseProp;

        Random rand = new Random();

        double chance = 0.7;

        int numPropsToDrop = rand.nextInt(4);
        
        int propOffsetX = 0;
        int propOffsetY = 0;

        propFactories = new ArrayList<>();
        propFactories.add(new PropBloodFactory());
        propFactories.add(new PropBombFactory());
        propFactories.add(new PropBulletFactory());

        for(int i=0; i<numPropsToDrop; i++) {
            boolean shouldDropProp = rand.nextDouble() < chance;
            if (shouldDropProp) {
                int propIndex = rand.nextInt(propFactories.size());

                propFactory = propFactories.get(propIndex);
                baseProp = propFactory.createProp(bossEnemy);

                baseProp.setLocation(baseProp.getLocationX() + propOffsetX, baseProp.getLocationY() + propOffsetY);

                props.add(baseProp);

                // 更新偏移量
                propOffsetX += ((PropFactory) propFactory).getOffsetX();
                propOffsetY += ((PropFactory) propFactory).getOffsetY();
            }
        }

        return props;
    }

    @Override
    public List<BaseBullet> shoot(){
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 5;
        int r = 200;
        BaseBullet bullet;
        for(int i=0; i<shootnum; i++){
            bullet = new EnemyBullet((int)(x + r * Math.cos(Math.PI / 10 * i)), (int)(y + r * Math.sin(Math.PI / 10 * i)), speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}
