package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBlood extends BaseProp {
    private final int hp;
    public PropBlood (int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }

    @Override
    public void effect(){
        HeroAircraft.getInstance().increaseHp(getHp());
        System.out.println("HpSupply active!");
    }
}
