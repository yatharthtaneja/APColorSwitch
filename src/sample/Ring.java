package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Ring extends Obstacle{
    public Ring(double x,double y){
        setXpos(x);setYpos(y);
        sample.Shape s1=new Arc(x,y, Color.web("#35e2f2"),0);
        sample.Shape s2=new Arc(x,y, Color.web("#f6df0e"),90);
        sample.Shape s3=new Arc(x,y, Color.web("#8c13fb"),180);
        sample.Shape s4=new Arc(x,y, Color.web("#ff0080"),270);
        AddShape(s1);AddShape(s2);AddShape(s3);AddShape(s4);
        setObstacle(new Group(s1,s2,s3,s4));
    }

}
