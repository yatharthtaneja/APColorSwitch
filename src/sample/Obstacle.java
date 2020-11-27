package sample;

import java.util.ArrayList;

public abstract class Obstacle implements SceneElements {
    private double Xpos;
    private double Ypos;
    public double getXpos(){
        return Xpos;
    }
    public void setXpos(double x){
        Xpos=x;
    }
    public double getYpos(){
        return Ypos;
    }
    public void setYpos(double y){
        Ypos=y;
    }

}