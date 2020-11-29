package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Cross extends Obstacle{
    Cross(double x,double y){
        setXpos(x);setYpos(y);
        sample.Shape shape1= new Pentagon(x+42.5,y-42.5,Color.web("#35e2f2"),0);
        sample.Shape shape2= new Pentagon(x+42.5,y+42.5,Color.web("#f6df0e"),90);
        sample.Shape shape3= new Pentagon(x-42.5,y+42.5,Color.web("#8c13fb"),180);
        sample.Shape shape4= new Pentagon(x-42.5,y-42.5,Color.web("#ff0080"),270);
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        setObstacle(new Group(shape1,shape2,shape3,shape4));
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
