package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Line extends Obstacle{
    TranslateTransition Transition = new TranslateTransition();
    public Line(){
        setListOfShapes(new ArrayList<>());
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
class UnidirectionalLine extends sample.Line{
    boolean Left;
    UnidirectionalLine(double y,boolean left){
        Left=left;
        setXpos(0);setYpos(y);
        if(left){
            Shape shape1 = new Rectangle( 0,y-6.25,Color.web("#35e2f2"));
            Shape shape2 = new Rectangle( 112.5,y-6.25,Color.web("#f6df0e"));
            Shape shape3 = new Rectangle( 225,y-6.25,Color.web("#8c13fb"));
            Shape shape4 = new Rectangle( 337.5,y-6.25,Color.web("#ff0080"));
            Shape shape5 = new Rectangle( 450,y-6.25,Color.web("#35e2f2"));
            Shape shape6 = new Rectangle( 562.5,y-6.25,Color.web("#f6df0e"));
            Shape shape7 = new Rectangle( 675,y-6.25,Color.web("#8c13fb"));
            Shape shape8 = new Rectangle( 787.5,y-6.25,Color.web("#ff0080"));
            AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);
            setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()
                    ,shape5.getShape(),shape6.getShape(),shape7.getShape(),shape8.getShape()));
        }
        else{
            Shape shape1 = new Rectangle( -450,y-6.25,Color.web("#35e2f2"));
            Shape shape2 = new Rectangle( -337.5,y-6.25,Color.web("#f6df0e"));
            Shape shape3 = new Rectangle( -225,y-6.25,Color.web("#8c13fb"));
            Shape shape4 = new Rectangle( -112.5,y-6.25,Color.web("#ff0080"));
            Shape shape5 = new Rectangle( 0,y-6.25,Color.web("#35e2f2"));
            Shape shape6 = new Rectangle( 112.5,y-6.25,Color.web("#f6df0e"));
            Shape shape7 = new Rectangle( 225,y-6.25,Color.web("#8c13fb"));
            Shape shape8 = new Rectangle( 337.5,y-6.25,Color.web("#ff0080"));
            AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);
            setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()
                    ,shape5.getShape(),shape6.getShape(),shape7.getShape(),shape8.getShape()));
        }
        Transition = new TranslateTransition();
        Transition.setInterpolator(Interpolator.LINEAR);
        Transition.setDuration(Duration.seconds(4));
        Transition.setNode(getObstacle());
        if(Left)
            Transition.setByX(-450);
        else
            Transition.setByX(450);
        Transition.setCycleCount(Animation.INDEFINITE);
        Transition.setAutoReverse(false);
    }
    public boolean getLeft(){
        return Left;
    }
}
class BidirectionalLine extends sample.Line {
    BidirectionalLine(double y){
        setXpos(0);setYpos(y);

        Shape shape1 = new Rectangle( -450,y-6.25,Color.web("#35e2f2"));
        Shape shape2 = new Rectangle( -337.5,y-6.25,Color.web("#f6df0e"));
        Shape shape3 = new Rectangle( -225,y-6.25,Color.web("#8c13fb"));
        Shape shape4 = new Rectangle( -112.5,y-6.25,Color.web("#ff0080"));
        Shape shape5 = new Rectangle( 0,y-6.25,Color.web("#35e2f2"));
        Shape shape6 = new Rectangle( 112.5,y-6.25,Color.web("#f6df0e"));
        Shape shape7 = new Rectangle( 225,y-6.25,Color.web("#8c13fb"));
        Shape shape8 = new Rectangle( 337.5,y-6.25,Color.web("#ff0080"));
        Shape shape9 = new Rectangle( 450,y-6.25,Color.web("#35e2f2"));
        Shape shape10 = new Rectangle( 562.5,y-6.25,Color.web("#f6df0e"));
        Shape shape11 = new Rectangle( 675,y-6.25,Color.web("#8c13fb"));
        Shape shape12 = new Rectangle( 787.5,y-6.25,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);AddShape(shape9);AddShape(shape10);AddShape(shape11);AddShape(shape12);
        setObstacle(new Group(shape1.getShape(),shape2.getShape(),shape3.getShape(),shape4.getShape()
                ,shape5.getShape(),shape6.getShape(),shape7.getShape(),shape8.getShape()
                ,shape9.getShape(),shape10.getShape(),shape11.getShape(),shape12.getShape()));

        Transition = new TranslateTransition();
        Transition.setDuration(Duration.seconds(3));
        Transition.setNode(getObstacle());
        Transition.setByX(450);
        Transition.setCycleCount(Animation.INDEFINITE);
        Transition.setAutoReverse(true);
    }
}