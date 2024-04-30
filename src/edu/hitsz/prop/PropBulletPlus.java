package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.SurroundingShoot;

public class PropBulletPlus extends BaseProp {
    public PropBulletPlus (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect(){
        HeroAircraft.getInstance().setStrategy(new SurroundingShoot());
        System.out.println("FirePlusSupply active!");
    }
}
