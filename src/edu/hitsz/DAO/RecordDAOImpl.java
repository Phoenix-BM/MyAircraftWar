package edu.hitsz.DAO;

import java.io.BufferedReader;
import java.io.*;

import java.util.*;

public class RecordDAOImpl implements RecordDAO{
    private List<Record> records;
    private int score;
    public RecordDAOImpl(int score){
        records = new LinkedList<Record>();
        this.score = score;
    }
    @Override
    public List<Record> getRecords(){
        return records;
    }
    @Override
    public void add(Record record){
        records.add(record);
    }
    // 比较器，用于指定按照 score 降序排序
    @Override
    public void remove(int rank) {
        Iterator<Record> iterator = records.iterator();
        if (rank >= 0 && rank < records.size()) {
            records.remove(rank); // 根据排名删除玩家
        }
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return Integer.compare(r2.getScore(), r1.getScore());
            }
        });
    }
    public int getScore(){return this.score;}
    @Override
    public void update(Record record) {
        records.add(record);

        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return Integer.compare(r2.getScore(), r1.getScore());
            }
        });
        writeToTextFile("records.txt");
    }

    @Override
    public void readFromTextFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String userID = data[1].trim();
                    int score = Integer.parseInt(data[2].trim());
                    String timeStamp = data[3].trim();
                    records.add(new Record(userID, score, timeStamp));
                }
            }
            System.out.println("成功从文件读取玩家列表：" + fileName);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }
    // 将所有记录重新排序后写入文件，会覆盖掉原有文件
    public void writeToTextFile(String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int existingRecords = records.size();
            for (int i = 0; i < existingRecords; i++) {
                // 写入记录时保持之前的名次状态
                writer.println((i + 1) + "," + records.get(i).getUserID() + "," + records.get(i).getScore() + "," + records.get(i).getTimeStamp());
            }
            System.out.println("玩家列表已成功写入文件：" + fileName);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }
    public void printRecord(){
        int rank = 1;
        for (Record record : records) {
            System.out.println(rank + "," + record.getUserID() + "," + record.getScore() + "," + record.getTimeStamp());
            rank++;
        }
    }
}
