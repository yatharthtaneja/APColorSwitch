package sample;

import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Player implements Serializable {
    private static final long serialVersionUID= 1l;
    private boolean SaveGame;
    private int score;
    private double ballX;
    private double ballY;
    private int balance;
    private Date DateTime;

    public ArrayList<Integer> ObstacleType= new ArrayList<>();
    public ArrayList<Double> ObsatcleXcord = new ArrayList<>();
    public ArrayList<Double> ObsatcleYcord = new ArrayList<>();
    public ArrayList<Integer> PowerupType= new ArrayList<>();
    public ArrayList<Double> PowerupYcord = new ArrayList<>();
    public ArrayList<Double> CurrentTime =new ArrayList<>();
    public int getBalance(){ return balance; }
    public void setBalance(int Balance){this.balance= Balance;}
    public double getBallX() {
        return ballX;
    }
    public void setBallX(double ballX) {
        this.ballX = ballX;
    }
    public double getBallY() {
        return ballY;
    }
    public void setBallY(double ballY) {
        this.ballY = ballY;
    }
    public int getScore() { return score; }
    public void setScore(int score) {
        this.score = score;
    }
    public int getObsType(int i){
        return ObstacleType.get(i);
    }
    public void setObsType(int type){
        this.ObstacleType.add(type);
    }
    public void setXcord(double type){
        this.ObsatcleXcord.add(type);
    }
    public void setYcord(double type){
        this.ObsatcleYcord.add(type);
    }
    public double getXcord(int i){
        return ObsatcleXcord.get(i);
    }
    public double getYcord(int i){
        return ObsatcleYcord.get(i);
    }
    public int getSize(){
        return ObsatcleXcord.size();
    }
    public boolean getSaveGame(){ return SaveGame;}
    public void setSaveGame(boolean saveGame){ SaveGame=saveGame; }
    public Date getDateTime(){return DateTime;}
    public void setDateTime(Date datetime){ DateTime=datetime; }
}
