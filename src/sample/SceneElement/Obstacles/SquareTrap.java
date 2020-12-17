package sample.SceneElement.Obstacles;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import sample.Shapes.Rectangle;
import sample.Shapes.Shape;

import java.util.ArrayList;


public class SquareTrap extends sample.SceneElement.Obstacles.Obstacle {
    RotateTransition Transition;
    public SquareTrap(double x,double y){
        Transition = new RotateTransition();
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Shape shape1= new Rectangle(x+77.5,y-87.5,17.5,175,0,Color.web("#35e2f2"));
        Shape shape2= new Rectangle(x-10,y,17.5,175,90,Color.web("#f6df0e"));
        Shape shape3= new Rectangle(x-97.5,y-87.5,17.5,175,0,Color.web("#8c13fb"));
        Shape shape4= new Rectangle(x-10,y-175,17.5,175,90,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);
        setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()));

        Transition.setAxis(Rotate.Z_AXIS);
        Transition.setByAngle(360);
        Transition.setCycleCount(Animation.INDEFINITE);
        Transition.setDuration(Duration.seconds(5));
        Transition.setInterpolator(Interpolator.LINEAR);
        Transition.setNode(getObstacle());
    }
    @Override
    public void Move() {
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
