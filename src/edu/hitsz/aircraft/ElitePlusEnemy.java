package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ElitePlusEnemy extends AbstractEnemy{
    private int shootnum = 3;
    private int power = 20;
    private int direction = 1;


    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score) {
        super(locationX, locationY, speedX, speedY, hp, score);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT)
            vanish();
    }

    @Override
    public List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy elitePlusEnemy) {

        PropFactory propFactory;
        List<PropFactory> propFactories;
        BaseProp baseProp;

        Random rand = new Random();

        double chance = 0.5;
        boolean shouldDropProp = rand.nextDouble() < chance;

        propFactories = new ArrayList<>();
        propFactories.add(new PropBloodFactory());
        propFactories.add(new PropBombFactory());
        propFactories.add(new PropBulletFactory());
        propFactories.add(new PropBulletPlusFactory());

        if (shouldDropProp) {
            int propIndex = rand.nextInt(propFactories.size());

            propFactory = propFactories.get(propIndex);
            baseProp = propFactory.createProp(elitePlusEnemy);

            props.add(baseProp);
        }

        return props;
    }
    @Override
    public List<BaseBullet> shoot(){
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = direction * 2;
        int speedY = this.getSpeedY() + direction * 5;
        BaseBullet bullet;
        for(int i=0; i<shootnum; i++){
            bullet = new EnemyBullet(x + (i * 2 - shootnum + 1) * 10, y, speedX * (i * 2 - shootnum + 1), speedY, power);
            res.add(bullet);
        }
        return res;
    }

}

