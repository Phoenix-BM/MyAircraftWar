package edu.hitsz.DAO;

import java.io.IOException;
import java.util.List;

public interface RecordDAO {
    List<Record> display();
    void add(Record record);
    void remove(String userID);
    void readFromTextFile(String fileName);
    void writeToTextFile(String fileName);
    void printRecord();
}
