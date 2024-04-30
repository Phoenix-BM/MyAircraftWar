package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.application.ImageManager;

public class PropBulletPlusFactory implements PropFactory {
    private int locationX ;
    private int locationY ;
    private int speedX = 0;
    private int speedY = 2;
    private static final int OFFSET_X = 30; // 横向偏移量
    private static final int OFFSET_Y = 30; // 纵向偏移量
    @Override
    public BaseProp createProp(AbstractEnemy abstractEnemy){

        this.locationX = abstractEnemy.getLocationX() - ImageManager.PROP_BULLET_PLUS_IMAGE.getWidth() + OFFSET_X;
        this.locationY = abstractEnemy.getLocationY() - ImageManager.PROP_BULLET_PLUS_IMAGE.getHeight() + OFFSET_Y;

        return new PropBulletPlus(this.locationX, this.locationY, this.speedX, this.speedY);
    }
    public int getLocationX() {return this.locationX;}
    public int getLocationY() {return this.locationY;}
    public int getSpeedX() {return this.speedX;}
    public int getSpeedY() {return this.speedY;}
    public int getOffsetX() {return OFFSET_X;}
    public int getOffsetY() {return OFFSET_Y;}
}

