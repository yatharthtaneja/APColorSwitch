package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Ring extends Obstacle{
    public Ring(double x,double y){
        setXpos(x);setYpos(y);
        sample.Shape s1=new Arc(x,y, Color.web("#35e2f2"),0);
        sample.Shape s2=new Arc(x,y, Color.web("#f6df0e"),90);
        sample.Shape s3=new Arc(x,y, Color.web("#8c13fb"),180);
        sample.Shape s4=new Arc(x,y, Color.web("#ff0080"),270);
        AddShape(s1);AddShape(s2);AddShape(s3);AddShape(s4);
        setObstacle(new Group(s1,s2,s3,s4));
    }
    @Override
    public void Move(){
        Rotate rt2 = new Rotate(0,getXpos(),getYpos());
        getObstacle().getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),360)),
                new KeyFrame(Duration.seconds(4), new KeyValue(rt2.angleProperty(),0)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
