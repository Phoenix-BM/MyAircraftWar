package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.HeroBullet;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    private static edu.hitsz.aircraft.HeroAircraft instance = null;
    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.strategy = new DirectShoot();
    }


    // DCL
    public static edu.hitsz.aircraft.HeroAircraft getInstance() {
        if (instance == null)
            synchronized (edu.hitsz.aircraft.HeroAircraft.class) {
                if (instance == null) {
                    instance = new edu.hitsz.aircraft.HeroAircraft(Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                            0, 0, 100000);
                }
            }
        return instance;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        double speedX = 0;
        double speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
    public void drawHpBar(Graphics g) {
        int x = getLocationX() - getImage().getWidth() / 2;
        int y = getLocationY() - getImage().getHeight() / 2 - 10; // 血条在飞机上方
        int width = getImage().getWidth();
        int height = 5; // 血条高度

        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);

        g.setColor(Color.GREEN);
        int hpBarWidth = (int) (width * ((double) getHp() / getMaxHp()));
        g.fillRect(x, y, hpBarWidth, height);
    }
}
