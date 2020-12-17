package sample.SceneElement.Obstacles;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import sample.Shapes.Pentagon;
import sample.Shapes.Shape;

import java.util.ArrayList;

public class Cross extends sample.SceneElement.Obstacles.Obstacle {
    public RotateTransition Transition;
    public Cross(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Shape shape1= new Pentagon(x+32.5,y-162,0,Color.web("#35e2f2"));
        Shape shape2= new Pentagon(x+32.5,y-77.5,90,Color.web("#f6df0e"));
        Shape shape3= new Pentagon(x-52.5,y-77.5,180,Color.web("#8c13fb"));
        Shape shape4= new Pentagon(x-52.5,y-162,270,Color.web("#ff0080"));
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
