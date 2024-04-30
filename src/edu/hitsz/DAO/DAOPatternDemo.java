package edu.hitsz.DAO;

import edu.hitsz.application.Game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class DAOPatternDemo {
    public void DAODemo(int score) {
        RecordDAO recordDAO = new RecordDAOImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("你要存储记录吗？（y/n）");
        String choice = scanner.nextLine();
        if(Objects.equals(choice, "y")){
            System.out.println("请输入玩家名：");
            String userID = scanner.nextLine();

            // 获取当前时间
            Date currentTime = new Date();

            // 设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化当前时间为指定格式的字符串
            String formattedTime = dateFormat.format(currentTime);

            Record record = new Record(userID, score, formattedTime);

            recordDAO.add(record);
            recordDAO.readFromTextFile("records.txt");

            System.out.print("你要删除记录吗？（y/n）");
            String chooseDelete = scanner.nextLine();

            if(Objects.equals(chooseDelete, "y")){
                System.out.print("请输入玩家名：");
                String playerDeleteName = scanner.nextLine();
                recordDAO.remove(playerDeleteName);
            }
            recordDAO.writeToTextFile("records.txt");
            for(int i=0; i<30; i++)
                System.out.print("*");
            System.out.print("\n");
            for(int i=0; i<10; i++)
                System.out.print(" ");
            System.out.println("得分排行榜");
            for(int i=0; i<30; i++)
                System.out.print("*");
            System.out.print("\n");
            recordDAO.printRecord();
        }
    }
}
