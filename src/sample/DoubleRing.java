package sample;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class DoubleRing extends Obstacle{
    RotateTransition Transition1,Transition2;
    public DoubleRing(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        Group A=new Group();
        Shape s1=new Arc(x-110,y,0,95,110, Color.web("#35e2f2"));
        Shape s2=new Arc(x-110,y,90,95,110,Color.web("#f6df0e"));
        Shape s3=new Arc(x-110,y,180,95,110,Color.web("#8c13fb"));
        Shape s4=new Arc(x-110,y,270,95,110,Color.web("#ff0080"));
        AddShape(s1);AddShape(s2);AddShape(s3);AddShape(s4);
        A.getChildren().add(s1.getShape());A.getChildren().add(s2.getShape());A.getChildren().add(s3.getShape());A.getChildren().add(s4.getShape());

        Group B=new Group();
        Shape s5=new Arc(x+110,y,180,95,110, Color.web("#35e2f2"));
        Shape s6=new Arc(x+110,y,270,95,110,Color.web("#f6df0e"));
        Shape s7=new Arc(x+110,y,0,95,110,Color.web("#8c13fb"));
        Shape s8=new Arc(x+110,y,90,95,110,Color.web("#ff0080"));
        AddShape(s5);AddShape(s6);AddShape(s7);AddShape(s8);
        B.getChildren().add(s5.getShape());B.getChildren().add(s6.getShape());B.getChildren().add(s7.getShape());B.getChildren().add(s8.getShape());

        obstacle.getChildren().add(A);obstacle.getChildren().add(B);
        setObstacle(obstacle);

        Transition1=new RotateTransition();
        Transition2=new RotateTransition();
        Transition1 = new RotateTransition();
        Transition1.setAxis(Rotate.Z_AXIS);
        Transition1.setByAngle(360);
        Transition1.setCycleCount(Animation.INDEFINITE);
        Transition1.setDuration(Duration.seconds(4));
        Transition1.setInterpolator(Interpolator.LINEAR);
        Transition1.setNode(getObstacle().getChildren().get(0));

        Transition2 = new RotateTransition();
        Transition2.setAxis(Rotate.Z_AXIS);
        Transition2.setByAngle(360);
        Transition2.setCycleCount(Animation.INDEFINITE);
        Transition2.setDuration(Duration.seconds(4));
        Transition2.setInterpolator(Interpolator.LINEAR);
        Transition2.setNode(getObstacle().getChildren().get(1));
    }
    @Override
    public void Move() {
        Play();
    }

    @Override
    public void Play() {
        Transition1.play();Transition2.play();
    }
    @Override
    public void Pause() {
        Transition1.pause();Transition2.pause();
    }

    @Override
    public double getCurrentTime() {
        return Transition1.getCurrentTime().toMillis();
    }
    @Override
    public void setCurrentTime(double millis) {
        Play();
        Pause();
        Transition1.jumpTo(new Duration(millis));
        Transition2.jumpTo(new Duration(millis));
    }
}
