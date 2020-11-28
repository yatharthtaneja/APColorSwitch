package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Obstacle implements SceneElements {
    private double Xpos;
    private double Ypos;
    ArrayList<Shape> ListOfShapes;
    Group Obstacle;
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
    public void AddShape(Shape shape){
        ListOfShapes.add(shape);
    }
    public Group getObstacle(){
        return Obstacle;
    }
    public void setObstacle(Group group){
        Obstacle=group;
    }
}