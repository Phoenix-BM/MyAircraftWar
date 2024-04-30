package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface Strategy {
    public List<BaseBullet> shootStrategy(AbstractAircraft abstractAircraft);
}
