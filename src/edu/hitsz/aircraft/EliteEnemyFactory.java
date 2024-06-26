package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory implements EnemyFactory{
    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
    private double speedX = 0;
    private double speedY = 10;
    private int hp = 20;
    private int score = 50;
    private int power = 20;
    @Override
    public AbstractEnemy createEnemy(){
        return new EliteEnemy(this.locationX, this.locationY,
                this.speedX, this.speedY, this.hp, this.score, this.power
        );
    }
    public int getLocationX() {return this.locationX;}
    public int getLocationY() {return  this.locationY;}
    public double getSpeedX() {return this.speedX;}
    public double getSpeedY() {return this.speedY;}
    public int getHp() {return this.hp;}
}
