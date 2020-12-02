package sample;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class DoubleRing extends Obstacle{
    public DoubleRing(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        Group A=new Group();
        Shape s1=new Arc(x-90,y,0,70,90, Color.web("#35e2f2"));
        Shape s2=new Arc(x-90,y,90,70,90,Color.web("#f6df0e"));
        Shape s3=new Arc(x-90,y,180,70,90,Color.web("#8c13fb"));
        Shape s4=new Arc(x-90,y,270,70,90,Color.web("#ff0080"));
        AddShape(s1);AddShape(s2);AddShape(s3);AddShape(s4);
        A.getChildren().add(s1.getShape());A.getChildren().add(s2.getShape());A.getChildren().add(s3.getShape());A.getChildren().add(s4.getShape());

        Group B=new Group();
        Shape s5=new Arc(x+90,y,180,70,90, Color.web("#35e2f2"));
        Shape s6=new Arc(x+90,y,270,70,90,Color.web("#f6df0e"));
        Shape s7=new Arc(x+90,y,0,70,90,Color.web("#8c13fb"));
        Shape s8=new Arc(x+90,y,90,70,90,Color.web("#ff0080"));
        AddShape(s5);AddShape(s6);AddShape(s7);AddShape(s8);
        B.getChildren().add(s5.getShape());B.getChildren().add(s6.getShape());B.getChildren().add(s7.getShape());B.getChildren().add(s8.getShape());

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
