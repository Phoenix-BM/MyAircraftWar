package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements EnemyFactory{
    private int biasLocationY = 150;
    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth()));
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05) + biasLocationY;
    private double speedX = 5;
    private double speedY = 0;
    private int hp = 180;
    private int score = 200;
    private int power = 20;
    @Override
    public AbstractEnemy createEnemy(){
        return new BossEnemy(this.locationX, this.locationY,
                this.speedX, this.speedY, this.hp, this.score, this.power
        );
    }
    public int getLocationX() {return this.locationX;}
    public int getLocationY() {return  this.locationY;}
    public double getSpeedX() {return this.speedX;}
    public double getSpeedY() {return this.speedY;}
    public int getHp() {return this.hp;}
    public void setHp(int hp){
        this.hp = hp;
    }
}
