package edu.hitsz.application.Game;

import edu.hitsz.DAO.DAOPatternDemo;
import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.music.MusicThread;
import edu.hitsz.prop.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {
    public static boolean openMusic = false;
    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    protected final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<AbstractEnemy> enemyAircrafts;
    protected final EnemyFactoryDemo enemyFactoryDemo;
    protected final List<BaseBullet> heroBullets;
    protected final List<BaseBullet> enemyBullets;
    protected final List<BaseProp> props;
    protected MusicManager musicManager;
    protected MusicThread musicThread;

    /**
     * 屏幕中出现的敌机最大数量
     */
    int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    protected int score = 0;
    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    int cycleDuration = 600;
    int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    protected boolean gameOverFlag = false;

    protected int elitePlusEnemyProbability = 10; // 初始精英敌机的概率
    protected int cycleDurationReduction = 5; // 每次难度增加时减少的周期时间
    protected int difficultyIncreaseInterval = 5000; // 每5秒增加一次难度
    protected int enemyHpIncrease = 10; // 每次增加难度时敌机Hp的增加量
    protected double enemySpeedIncrease = 0.02; // 每次增加难度时敌机Speed的增加量
    protected int enemyPowerIncrease = 10; // 每次增加难度时敌机Power的增加量


    public Game(boolean musicState) {
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        enemyFactoryDemo = new EnemyFactoryDemo();

        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();

        props = new LinkedList<>();

        musicManager = new MusicManager();
        musicThread = null;

        openMusic = musicState;

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

        initDifficultyParameters();
    }
    protected abstract void initDifficultyParameters();

    /**
     * 游戏启动入口，执行游戏逻辑
     */

    protected abstract void gameCreateEnemy();

    public void checkHeroAircraftAlive() {
        if (heroAircraft.getHp() <= 0) {
            // 游戏结束
            executorService.shutdown();
            gameOverFlag = true;
            System.out.println("Game Over!");
            if(this.openMusic) {
//                playGameOverSound = new MusicThread("src/videos/game_over.wav");
//                playGameOverSound = musicManager.getPlayGameOverSound();
//                playGameOverSound.start();
                musicManager.startPlayGameOverSound();
            }
            if(gameOverFlag){
                if(this.openMusic){
                    this.musicThread.stopMusic();
                }
            }

            DAOPatternDemo daoPatternDemo = new DAOPatternDemo();
            daoPatternDemo.DAODemo(score);
        }
    }
    // 定期增加难度的任务
    protected abstract void difficultyIncreasesWithTime();

    public final void action() {
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            gameCreateEnemy();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            checkHeroAircraftAlive();
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

        difficultyIncreasesWithTime();
    }

    protected void increaseDifficulty() {
        int tempEnemyHpIncrease = 5;
        double tempEnemySpeedIncrease = 0.02;
        int tempEnemyPowerIncrease = 5;
        int tempElitePlusEnemyProbability = 5;

        cycleDuration = Math.max(cycleDuration - cycleDurationReduction, 200); // 保证周期时间不会过小
        elitePlusEnemyProbability = Math.min(elitePlusEnemyProbability + tempElitePlusEnemyProbability, 100); // 保证概率不超过100%
        enemyHpIncrease += tempEnemyHpIncrease;
        enemySpeedIncrease += tempEnemySpeedIncrease;
        enemyPowerIncrease += tempEnemyPowerIncrease;

        // 输出当前难度参数
        System.out.println("Difficulty increased: cycleDuration=" + cycleDuration +
                ", elitePlusEnemyProbability=" + elitePlusEnemyProbability +
                ", enemyHpIncrease=" + enemyHpIncrease +
                ", enemySpeedIncrease=" + enemySpeedIncrease +
                ", enemyPowerIncrease=" + enemyPowerIncrease);
    }

    //***********************
    //      Action 各部分
    //***********************

    protected boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    protected void shootAction() {
        // TODO 敌机射击
        for(AbstractEnemy abstractEnemy : enemyAircrafts) {
            enemyBullets.addAll(abstractEnemy.executeStrategy(abstractEnemy));
        }
        // 英雄射击
        //heroAircraft.setStrategy(new DirectShoot());
        heroBullets.addAll(heroAircraft.executeStrategy(heroAircraft));

        //heroBullets.addAll(heroAircraft.shoot());
    }

    protected void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    protected void aircraftsMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    protected void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (heroAircraft.notValid()) {
                    continue;
                }
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    if(enemyAircraft instanceof BossEnemy){
                        ((BossEnemy) enemyAircraft).endBossMusic();
                    }
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给

                        // 击毁敌机获得相应分数
                        int tmpScore = enemyAircraft.getScore();
                        score += tmpScore;

                        enemyAircraft.propDrop(props, enemyAircraft);

                        if(this.openMusic) {
//                            bulletHitSound = new MusicThread("src/videos/bullet_hit.wav");
//                            bulletHitSound = musicManager.getBulletHitSound();
//                            bulletHitSound.start();
                            musicManager.startBulletHitSound();
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(BaseProp prop : props){
            prop.forward();

            if(heroAircraft.crash(prop) || prop.crash(heroAircraft)){

                score += prop.effect(enemyAircrafts, enemyBullets);

                if(prop instanceof PropBullet || prop instanceof PropBulletPlus){
                    heroBullets.addAll(heroAircraft.executeStrategy(heroAircraft));
                }

                if(prop instanceof PropBomb &&  this.openMusic){
//                    bombExplosionSound = new MusicThread("src/videos/bomb_explosion.wav");
//                    bombExplosionSound = musicManager.getBombExplosionSound();
//                    bombExplosionSound.start();
                    musicManager.startBombExplosionSound();
                }
                else if(this.openMusic){
//                    supplyEffectSound = new MusicThread("src/videos/get_supply.wav");
//                    supplyEffectSound = musicManager.getSupplyEffectSound();
//                    supplyEffectSound.start();
                    musicManager.startSupplyEffectSound();
                }
                prop.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    protected void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    private Image backgroundImage;
    public void setBackgroundImage(Image backgroundImage){
        this.backgroundImage = backgroundImage;
    }
    public Image getBackgroundImage(){
        return this.backgroundImage;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(getBackgroundImage(), 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(getBackgroundImage(), 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        // 绘制敌机血条
        for (AbstractEnemy enemy : enemyAircrafts) {
            enemy.drawHpBar(g);
        }

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        // 绘制道具
        paintImageWithPositionRevised(g, props);

        // 绘制英雄机血条
        heroAircraft.drawHpBar(g);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }
    public void startMusic() {
        if (this.musicThread == null || !this.musicThread.isAlive()){
//            this.musicThread = new MusicThread("src/videos/bgm.wav");
//            this.musicThread = musicManager.getGameBGM();
//            this.musicThread.start();
                this.musicThread = musicManager.getGameBGM();
                musicManager.startGameBGM();
        }
    }
    public void endMusic() {
        if (this.musicThread != null) {
            this.musicThread.stopMusic();
        }
    }

}
