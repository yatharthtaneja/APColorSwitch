package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class DoubleCross extends Obstacle{
    public DoubleCross(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        sample.Shape shape1= new Pentagon((x-100)+32.5,y-162,0, Color.web("#35e2f2"));
        sample.Shape shape2= new Pentagon((x-100)+32.5,y-77.5,90,Color.web("#f6df0e"));
        sample.Shape shape3= new Pentagon((x-100)-52.5,y-77.5,180,Color.web("#8c13fb"));
        sample.Shape shape4= new Pentagon((x-100)-52.5,y-162,270,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        Group A=new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape());

        sample.Shape shape5= new Pentagon((x+100)+32.5,y-162,0, Color.web("#8c13fb"));
        sample.Shape shape6= new Pentagon((x+100)+32.5,y-77.5,90,Color.web("#ff0080"));
        sample.Shape shape7= new Pentagon((x+100)-52.5,y-77.5,180,Color.web("#35e2f2"));
        sample.Shape shape8= new Pentagon((x+100)-52.5,y-162,270,Color.web("#f6df0e"));
        AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);
        Group B=new Group(shape5.getShape(),shape6.getShape(),shape7.getShape(),shape8.getShape());

        obstacle.getChildren().add(A);obstacle.getChildren().add(B);
        setObstacle(obstacle);
    }
    @Override
    public void Move() {
        RotateTransition rt1 = new RotateTransition();
        rt1.setAxis(Rotate.Z_AXIS);
        rt1.setByAngle(360);
        rt1.setCycleCount(Animation.INDEFINITE);
        rt1.setDuration(Duration.seconds(4));
        rt1.setInterpolator(Interpolator.LINEAR);
        rt1.setNode(getObstacle().getChildren().get(0));
        rt1.play();

        RotateTransition rt2 = new RotateTransition();
        rt2.setAxis(Rotate.Z_AXIS);
        rt2.setByAngle(360);
        rt2.setCycleCount(Animation.INDEFINITE);
        rt2.setDuration(Duration.seconds(4));
        rt2.setInterpolator(Interpolator.LINEAR);
        rt2.setNode(getObstacle().getChildren().get(1));
        rt2.play();
    }
}
