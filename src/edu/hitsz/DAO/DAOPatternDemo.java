package edu.hitsz.DAO;

import edu.hitsz.application.Main;
import edu.hitsz.layout.PlayerScore;

import javax.swing.*;
import java.awt.*;

public class DAOPatternDemo {
    public static final CardLayout cardLayout = new CardLayout(0,0);
    public static final JPanel cardPanel = new JPanel(cardLayout);
    public void DAODemo(int score) {
        RecordDAOImpl recordDAOImpl = new RecordDAOImpl(score);
        recordDAOImpl.readFromTextFile("records.txt");

//        Scanner scanner = new Scanner(System.in);

//        System.out.println("你要存储记录吗？（y/n）");
//        String choice = scanner.nextLine();
//        if(Objects.equals(choice, "y")){
//            System.out.println("请输入玩家名：");
//            String userID = scanner.nextLine();

            // 获取当前时间
//            Date currentTime = new Date();
//
//            // 设置日期格式
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            // 格式化当前时间为指定格式的字符串
//            String formattedTime = dateFormat.format(currentTime);
//
//            Record record = new Record(userID, score, formattedTime);
//
//            recordDAOImpl.readFromTextFile("records.txt");
//            recordDAOImpl.update(record);

//        JFrame frame = new JFrame("My AircraftWar");
//        frame.setSize(800, 1024);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.add(cardPanel);

        PlayerScore playerScore = new PlayerScore(recordDAOImpl);
        Main.cardPanel.add(playerScore.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
        playerScore.showInputDialog();
//        cardPanel.add(playerScore.getMainPanel());
//        frame.setVisible(true);

        for(int i=0; i<30; i++)
            System.out.print("*");
        System.out.print("\n");
        for(int i=0; i<10; i++)
            System.out.print(" ");
        System.out.println("得分排行榜");
        for(int i=0; i<30; i++)
            System.out.print("*");
        System.out.print("\n");

        recordDAOImpl.printRecord();
    }
}



