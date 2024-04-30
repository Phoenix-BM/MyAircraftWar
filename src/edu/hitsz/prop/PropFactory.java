package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;

public interface PropFactory {
    static final int OFFSET_X = 30; // 横向偏移量
    static final int OFFSET_Y = 30; // 纵向偏移量
    public abstract BaseProp createProp(AbstractEnemy abstractEnemy);
    int getOffsetX();
    int getOffsetY();
}
