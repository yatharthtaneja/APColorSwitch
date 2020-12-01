package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cross extends Obstacle{
    Cross(double x,double y){
        setListOfShapes(new ArrayList<>());
        setXpos(x);setYpos(y);
        sample.Shape shape1= new Pentagon(x+32.5,y-162,0,Color.web("#35e2f2"));
        sample.Shape shape2= new Pentagon(x+32.5,y-77.5,90,Color.web("#f6df0e"));
        sample.Shape shape3= new Pentagon(x-52.5,y-77.5,180,Color.web("#8c13fb"));
        sample.Shape shape4= new Pentagon(x-52.5,y-162,270,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()));
    }
    @Override
    public void Move(){
        RotateTransition rt = new RotateTransition();
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setDuration(Duration.seconds(4));
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setNode(getObstacle());
        rt.play();
    }
}
