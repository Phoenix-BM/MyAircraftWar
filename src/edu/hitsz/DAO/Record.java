package edu.hitsz.DAO;

public class Record {
    private String userID;
    private int score;
    private String timeStamp;
    Record(String userID, int score, String timeStamp){
        this.userID = userID;
        this.score = score;
        this.timeStamp = timeStamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
