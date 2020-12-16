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
    RotateTransition Transition1,Transition2;

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

        Transition1=new RotateTransition();Transition2=new RotateTransition();
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
