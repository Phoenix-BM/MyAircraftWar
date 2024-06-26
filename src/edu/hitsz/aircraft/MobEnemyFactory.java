package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;


public class MobEnemyFactory implements EnemyFactory{
    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
    private int speedX = 0;
    private int speedY = 10;
    private int hp = 20;
    private int score = 10;
    private int power = 0;
    @Override
    public AbstractEnemy createEnemy(){
        return new MobEnemy(this.locationX, this.locationY,
                this.speedX, this.speedY, this.hp, this.score, this.power
        );
    }

    public int getLocationX(){return this.locationX;}
    public int getLocationY(){return  this.locationY;}
    public int getSpeedX(){return this.speedX;}
    public int getSpeedY(){return this.speedY;}
    public int getHp(){return this.hp;}
}