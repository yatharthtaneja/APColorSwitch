package sample.SceneElement;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.SceneElement.SceneElements;

import java.io.Serializable;

public class Ball implements SceneElements, Serializable {
    private static final long serialVersionUID= 5l;
    private double Xpos;
    private double Ypos;
    private Circle Ball;
    public Ball(double x,double y,double radius){
        Ball=new javafx.scene.shape.Circle(x,y,radius);
        Xpos=x;Ypos=y;
    }
    public Ball(double x, double y, double radius, Color col){
        Ball=new javafx.scene.shape.Circle(x,y,radius,col);
        Xpos=x;Ypos=y;
    }
    @Override
    public double getXpos(){
        return Xpos;
    }
    @Override
    public void setXpos(double x){
        if (Ball!=null)
            Ball.setCenterX(x);
        Xpos=x;
    }
    @Override
    public double getYpos(){
        return Ypos;
    }
    @Override
    public void setYpos(double y){
        if (Ball!=null)
            Ball.setCenterY(y);
        Ypos=y;
    }
    public Circle getBall(){ return Ball; }
}
