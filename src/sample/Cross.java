package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Cross extends Obstacle{
    Cross(double x,double y){
        setXpos(x);
        setYpos(y);
        sample.Shape shape1= new Pentagon(x+42.5,y-42.5,Color.web("#35e2f2"),0);
        sample.Shape shape2= new Pentagon(x+42.5,y+42.5,Color.web("#f6df0e"),90);
        sample.Shape shape3= new Pentagon(x-42.5,y+42.5,Color.web("#8c13fb"),180);
        sample.Shape shape4= new Pentagon(x-42.5,y-42.5,Color.web("#ff0080"),270);
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        setObstacle(new Group(shape1,shape2,shape3,shape4));
    }
}
