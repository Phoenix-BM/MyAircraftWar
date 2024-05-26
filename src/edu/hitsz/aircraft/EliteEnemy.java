package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EliteEnemy extends AbstractEnemy {
    private int shootnum = 1;
    private int power = 20;
    private int direction = 1;
    private int eliteEnemyScore = 30;

    public EliteEnemy(int locationX, int locationY, double speedX, double speedY, int hp, int score, int power) {
        super(locationX, locationY, speedX, speedY, hp, score, power);
        this.score = eliteEnemyScore;
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT)
            vanish();
    }

    @Override
    public List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy eliteEnemy) {

        PropFactory propFactory;
        List<PropFactory> propFactories;
        BaseProp baseProp;

        Random rand = new Random();

        double chance = 0.3;
        boolean shouldDropProp = rand.nextDouble() < chance;

        propFactories = new ArrayList<>();
        propFactories.add(new PropBloodFactory());
        propFactories.add(new PropBombFactory());
        propFactories.add(new PropBulletFactory());
        propFactories.add(new PropBulletPlusFactory());

        if (shouldDropProp) {
            int propIndex = rand.nextInt(propFactories.size());

            propFactory = propFactories.get(propIndex);
            baseProp = propFactory.createProp(eliteEnemy);

            props.add(baseProp);
        }

        return props;
    }
    public List<BaseBullet> executeStrategy(AbstractAircraft abstractAircraft){
        abstractAircraft.setStrategy(new DirectShoot());
        return strategy.shootStrategy(abstractAircraft);
    }

    @Override
    public List<BaseBullet> shoot(){
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        double speedX = 0;
        double speedY = this.getSpeedY() + direction * 5;
        BaseBullet bullet;
        for(int i=0; i<shootnum; i++){
            bullet = new EnemyBullet(x + (i * 2 - shootnum + 1) * 10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
    @Override
    public int bombUpdate(){
        this.vanish();
        return this.score;
    }

}
