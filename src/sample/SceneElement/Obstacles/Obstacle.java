package sample.SceneElement.Obstacles;

import javafx.scene.Group;
import sample.SceneElement.SceneElements;
import sample.Shapes.Shape;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Obstacle implements SceneElements, Serializable {
    private static final long serialVersionUID= 2l;
    private double Xpos;
    private double Ypos;
    private ArrayList<Shape> ListOfShapes;
    private Group Obstacle;
    @Override
    public double getXpos(){
        return Xpos;
    }
    @Override
    public void setXpos(double x){
        Xpos=x;
    }
    @Override
    public double getYpos(){
        return Ypos;
    }
    @Override
    public void setYpos(double y){
        Ypos=y;
    }
    public ArrayList<Shape> getListOfShapes(){
        return ListOfShapes;
    }
    public void setListOfShapes(ArrayList<Shape> list){
        ListOfShapes=list;
    }
    public Group getObstacle(){
        return Obstacle;
    }
    public void setObstacle(Group group){
        Obstacle=group;
    }
    public void AddShape(Shape shape){
        if (ListOfShapes!=null)
            ListOfShapes.add(shape);
    }
    public void incrementYpos(double change){
        if(Obstacle!=null)
            Obstacle.setLayoutY(Obstacle.getLayoutY()+change);
        Ypos+=change;
    }
    public abstract void Move();
    public abstract void Play();
    public abstract void Pause();
    public abstract double getCurrentTime();
    public abstract void setCurrentTime(double millis);
}