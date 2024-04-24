package edu.hitsz.prop;

public class PropBomb extends BaseProp {
    public PropBomb (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void effect(){
        System.out.println("BombSupply active!");
    }
}

