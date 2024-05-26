package edu.hitsz.layout;

import edu.hitsz.DAO.RecordDAOImpl;
import edu.hitsz.DAO.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlayerScore {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JButton deleteButton;
    private JLabel rankingList;
    private JTable rankTable;
    private JScrollPane rankScrollPane;
    private List<Record> records;
    private RecordDAOImpl recordDAOImpl;

    public PlayerScore(RecordDAOImpl recordDAOImpl){
        this.recordDAOImpl =recordDAOImpl;

        String[] columnName = {"名次", "玩家名", "得分", "记录时间"};
        String[][] tableData= readDataFromFile("records.txt");

        records = recordDAOImpl.getRecords();
        printTableData(records);
//        showInputDialog();
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = rankTable.getSelectedRow();
                if(row != -1){
                    int result = JOptionPane.showConfirmDialog(deleteButton,
                            "是否确定删除选中的玩家？","选择一个选项",JOptionPane.YES_NO_OPTION);
                    System.out.println(row + 1);
                    if(result == JOptionPane.YES_OPTION) {
                        recordDAOImpl.remove(row);
                        recordDAOImpl.writeToTextFile("records.txt");
                        records = recordDAOImpl.getRecords();

                        printTableData(records);
                    }
                }
            }
        });
    }
    public void showInputDialog() {
        int score = recordDAOImpl.getScore();

        // 弹出输入对话框
        String userID = JOptionPane.showInputDialog(TopPanel, "游戏结束，你的得分为" + score + "\n请输入名字记录得分：");
        if (userID != null && !userID.isEmpty()) {
            // 在控制台输出玩家姓名
            System.out.println("玩家姓名：" + userID);
            // 添加玩家姓名到排行榜表格中

            // 获取当前时间
            Date currentTime = new Date();

            // 设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化当前时间为指定格式的字符串
            String formattedTime = dateFormat.format(currentTime);

            Record record = new Record(userID, recordDAOImpl.getScore(), formattedTime);

            // 在数据库中更新记录，写入新记录
            recordDAOImpl.update(record);

            records = recordDAOImpl.getRecords();

            printTableData(records);
        }
        else {
            records = recordDAOImpl.getRecords();
            printTableData(records);
        }
    }

    private void removeRecordFromFile(String[] recordData) {
        try {
            File file = new File("records.txt");
            File tempFile = new File("tempRecords.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = String.join(",", recordData);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();

            // 删除原始文件并将临时文件重命名为原始文件名
            if (!file.delete()) {
                System.err.println("无法删除原始文件");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.err.println("无法将临时文件重命名为原始文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String[][] readDataFromFile(String filename) {
        String[][] tableData = new String[0][];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rowCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData.length == 4) { // 确保每行有四个字段
                    if (rowCount >= tableData.length) {
                        String[][] temp = new String[rowCount + 1][4];
                        System.arraycopy(tableData, 0, temp, 0, tableData.length);
                        tableData = temp;
                    }
                    tableData[rowCount] = rowData;
                    rowCount++;
                } else {
                    System.err.println("Invalid data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableData;
    }
    public void printTableData(List<Record> records) {
        String[] columnName = {"名次","玩家名","得分","记录时间"};

        String[][] tableData = new String[records.size()][4]; // 假设有4列数据

        // 将玩家数据写入到tableData中
        for (int i = 0; i < records.size(); i++) {
            Record rocordCopy = records.get(i);
            tableData[i][0] = String.valueOf(i+1);// 第一列是名次
            tableData[i][1] = rocordCopy.getUserID(); // 第二列是玩家名
            tableData[i][2] = String.valueOf(rocordCopy.getScore()); // 第三列是得分
            tableData[i][3] = rocordCopy.getTimeStamp(); // 第四列是记录时间
        }

        DefaultTableModel model = new DefaultTableModel(tableData, columnName){

            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        rankTable.setModel(model);
        rankScrollPane.setViewportView(rankTable);
    }
    public JPanel getMainPanel() {
        return this.MainPanel;
    }
}
