package edu.hitsz.DAO;

import java.io.IOException;
import java.util.List;

public interface RecordDAO {
    List<Record> getRecords();
    void add(Record record);
    void remove(int rank);
    void update(Record record);
    void readFromTextFile(String fileName);
    void writeToTextFile(String fileName);
    void printRecord();
}
