package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;

public interface PropFactory {
    public abstract BaseProp createProp(AbstractEnemy abstractEnemy);
    int getOffsetX();
    int getOffsetY();
}
