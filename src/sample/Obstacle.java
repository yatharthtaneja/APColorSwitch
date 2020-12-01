package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Obstacle implements SceneElements {
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
        if(Obstacle!=null)
            Obstacle.setLayoutX(x);
        Xpos=x;
    }
    @Override
    public double getYpos(){
        return Ypos;
    }
    @Override
    public void setYpos(double y){
        if(Obstacle!=null)
            Obstacle.setLayoutY(y);
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
}