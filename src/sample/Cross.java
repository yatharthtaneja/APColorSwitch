package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cross extends Obstacle{
    public RotateTransition Transition;
    Cross(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        sample.Shape shape1= new Pentagon(x+32.5,y-162,0,Color.web("#35e2f2"));
        sample.Shape shape2= new Pentagon(x+32.5,y-77.5,90,Color.web("#f6df0e"));
        sample.Shape shape3= new Pentagon(x-52.5,y-77.5,180,Color.web("#8c13fb"));
        sample.Shape shape4= new Pentagon(x-52.5,y-162,270,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()));
        Transition=new RotateTransition();
        Transition.setAxis(Rotate.Z_AXIS);
        Transition.setByAngle(360);
        Transition.setCycleCount(Animation.INDEFINITE);
        Transition.setDuration(Duration.seconds(4));
        Transition.setInterpolator(Interpolator.LINEAR);
        Transition.setNode(getObstacle());
    }
    @Override
    public void Move(){
        Play();
    }
    @Override
    public void Play() {
        Transition.play();
    }
    @Override
    public void Pause() {
        Transition.pause();
    }

    @Override
    public double getCurrentTime() {
        return Transition.getCurrentTime().toMillis();
    }

    @Override
    public void setCurrentTime(double millis) {
        Play();
        Pause();
        Transition.jumpTo(new Duration(millis));
    }
}
