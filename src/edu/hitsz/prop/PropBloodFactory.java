package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class PropBloodFactory implements PropFactory {
    private int locationX ;
    private int locationY ;
    private int speedX = 0;
    private int speedY = 2;
    private int hp = 20;
    private static final int OFFSET_X = 30; // 横向偏移量
    private static final int OFFSET_Y = 30; // 纵向偏移量
    @Override
    public BaseProp createProp(AbstractEnemy abstractEnemy){

        this.locationX = abstractEnemy.getLocationX() - ImageManager.PROP_BLOOD_IMAGE.getWidth() - OFFSET_X;
        this.locationY = abstractEnemy.getLocationY() - ImageManager.PROP_BLOOD_IMAGE.getHeight() - OFFSET_Y;

        return new PropBlood(this.locationX, this.locationY, this.speedX, this.speedY, this.hp);
    }
    public int getLocationX() {return this.locationX;}
    public int getLocationY() {return  this.locationY;}
    public int getSpeedX() {return this.speedX;}
    public int getSpeedY() {return this.speedY;}
    public int getHp() {return this.hp;}
    public int getOffsetX() {return OFFSET_X;}
    public int getOffsetY() {return OFFSET_Y;}

}
