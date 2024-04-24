package junit.demo;

import edu.hitsz.aircraft.HeroAircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class HeroAircraftTest {
    private HeroAircraft heroAircraft;
    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        heroAircraft = HeroAircraft.getInstance();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        heroAircraft = null;
    }

    @DisplayName("Test decreaseHp method")
    @Test
    void decreaseHp(){
        heroAircraft.decreaseHp(100);
        assertEquals(900, heroAircraft.getHp());
        heroAircraft.decreaseHp(100);
        assertEquals(800, heroAircraft.getHp());
        heroAircraft.decreaseHp(1000);
        assertEquals(0, heroAircraft.getHp());
        System.out.println("**--- Test decreaseHp method executed ---**");
    }

    @DisplayName("Test getInstance method")
    @Test
    void getInstance(){
        HeroAircraft instance1 = HeroAircraft.getInstance();
        HeroAircraft instance2 = HeroAircraft.getInstance();
        assertEquals(instance1, instance2);
        System.out.println("**--- Test getInstance method executed ---**");
    }

    @DisplayName("Test shoot method")
    @Test
    void shoot(){
        List<BaseBullet> bullets = heroAircraft.shoot();
        assertEquals(1, bullets.size());
        BaseBullet bullet = bullets.get(0);
        assertTrue(bullet instanceof HeroBullet);
        assertEquals(heroAircraft.getLocationX(), bullet.getLocationX());
        assertEquals(heroAircraft.getLocationY() + heroAircraft.getDirection()*2, bullet.getLocationY());
        System.out.println("**--- Test shoot method executed ---**");
    }

}
