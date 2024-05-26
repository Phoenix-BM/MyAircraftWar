package edu.hitsz.aircraft;

import edu.hitsz.application.Game.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.SurroundingShoot;
import edu.hitsz.music.MusicThread;
import edu.hitsz.prop.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BossEnemy extends AbstractEnemy {
    private int shootnum = 20;
    private int power = 20;
    private int direction = 1;
    private int bossScore = 200;
    private int hp = 180;
    private MusicThread bossMusicThread;
    public BossEnemy(int locationX, int locationY, double speedX, double speedY, int hp, int score, int power) {
        super(locationX, locationY, speedX, speedY, hp, score, power);
        this.hp = hp;
        this.score = bossScore;
        startBossMusic();
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT)
            vanish();
    }
    public void startBossMusic(){
        if(Game.openMusic){
            this.bossMusicThread =new MusicThread(MusicManager.BOSS_MUSIC);
            this.bossMusicThread.start();
        }
        else {
            this.bossMusicThread = null;
        }
    }
    public void endBossMusic(){
        if(this.bossMusicThread != null)
            this.bossMusicThread.stopMusic();
    }
    @Override
    public List<BaseProp> propDrop(List<BaseProp> props, AbstractEnemy bossEnemy) {

        PropFactory propFactory;
        List<PropFactory> propFactories;
        BaseProp baseProp;

        Random rand = new Random();

        double chance = 0.7;
        
        int propOffsetX = 0;
        int propOffsetY = 0;

        int maxNumPropsToDrop = 3;
        int numPropsToDrop = rand.nextInt(maxNumPropsToDrop + 1);

        propFactories = new ArrayList<>();
        propFactories.add(new PropBloodFactory());
        propFactories.add(new PropBombFactory());
        propFactories.add(new PropBulletFactory());
        propFactories.add(new PropBulletPlusFactory());

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
    public List<BaseBullet> executeStrategy(AbstractAircraft abstractAircraft){
        abstractAircraft.setStrategy(new SurroundingShoot());
        return strategy.shootStrategy(abstractAircraft);
    }

    @Override
    public List<BaseBullet> shoot(){
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        double speedX = 0;
        double speedY = this.getSpeedY() + direction * 5;
        int r = 200;
        BaseBullet bullet;
        for(int i=0; i<shootnum; i++){
            bullet = new EnemyBullet((int)(x + r * Math.cos(Math.PI / 10 * i)), (int)(y + r * Math.sin(Math.PI / 10 * i)), speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
    @Override
    public int bombUpdate(){
        return 0;
    }
    @Override
    public void drawHpBar(Graphics g) {
        int x = getLocationX() - getImage().getWidth() / 2;
        int y = getLocationY() - getImage().getHeight() / 2 - 10; // 血条在飞机上方
        int width = getImage().getWidth();
        int height = 5; // 血条高度

        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);

        g.setColor(Color.GREEN);
        int hpBarWidth = (int) (width * ((double) getHp() / getMaxHp()));
        g.fillRect(x, y, hpBarWidth, height);
    }
}
