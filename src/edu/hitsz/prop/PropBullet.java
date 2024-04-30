package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.ScatteringShoot;

public class PropBullet extends BaseProp {
    public PropBullet (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect(){
        HeroAircraft.getInstance().setStrategy(new ScatteringShoot());
        System.out.println("FireSupply active!");
    }
}

