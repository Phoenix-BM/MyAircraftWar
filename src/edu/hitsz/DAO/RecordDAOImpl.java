package edu.hitsz.DAO;

import java.io.BufferedReader;
import java.io.*;

import java.util.*;

public class RecordDAOImpl implements RecordDAO{
    private List<Record> records;
    public RecordDAOImpl(){
        records = new LinkedList<Record>();
    }
    @Override
    public List<Record> display(){
        return records;
    }
    @Override
    public void add(Record record){
        records.add(record);
    }
    @Override
    public void remove(String userID) {
        Iterator<Record> iterator = records.iterator();
        while (iterator.hasNext()) {
            Record record = iterator.next();
            if (record.getUserID().equalsIgnoreCase(userID)) {
                iterator.remove();
                System.out.println("已删除记录：" + record);
                return;
            }
        }
        System.out.println("未找到名为 " + userID + " 的玩家，无法删除。");
    }
    @Override
    public void readFromTextFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String userID = data[0].trim();
                    int score = Integer.parseInt(data[1].trim());
                    String timeStamp = data[2].trim();
                    records.add(new Record(userID, score, timeStamp));
                }
            }
            System.out.println("成功从文件读取玩家列表：" + fileName);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }
    @Override
    public void writeToTextFile(String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Record record : records) {
                writer.println(record.getUserID() + "," + record.getScore() + "," + record.getTimeStamp());
            }
            System.out.println("玩家列表已成功写入文件：" + fileName);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }
    @Override
    public void printRecord(){
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return Integer.compare(r2.getScore(), r1.getScore());
            }
        });
        int cnt=0;
        for (Record record : records) {
            cnt++;
            System.out.println("rank" + cnt + " " + record.getUserID()+" " + record.getScore()+" "+ record.getTimeStamp());
        }
    }
}
