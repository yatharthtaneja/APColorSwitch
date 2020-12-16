package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Ring extends Obstacle{
    Timeline Timeline;
    public Ring(double x,double y){
        Timeline=new Timeline();
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        sample.Shape s1=new Arc(x,y,0, Color.web("#35e2f2"));
        sample.Shape s2=new Arc(x,y,90, Color.web("#f6df0e"));
        sample.Shape s3=new Arc(x,y,180,Color.web("#8c13fb"));
        sample.Shape s4=new Arc(x,y,270,Color.web("#ff0080"));
        AddShape(s1);AddShape(s2);AddShape(s3);AddShape(s4);
        setObstacle(new Group(s1.getShape(),s2.getShape(),s3.getShape(),s4.getShape()));
        Rotate rt2 = new Rotate(0,getXpos(),getYpos());
        getObstacle().getTransforms().add(rt2);

        Timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),360)),
                new KeyFrame(Duration.seconds(4), new KeyValue(rt2.angleProperty(),0)));
        Timeline.setCycleCount(Animation.INDEFINITE);
    }
    @Override
    public void Move(){
        Timeline.play();
    }

    @Override
    public void Play() {
        Timeline.play();
    }

    @Override
    public void Pause() {
        Timeline.pause();
    }

    @Override
    public double getCurrentTime() {
        return Timeline.getCurrentTime().toMillis();
    }
    @Override
    public void setCurrentTime(double millis) {
        Timeline.play();
        Timeline.pause();
        Timeline.jumpTo(new Duration(millis));
    }
}