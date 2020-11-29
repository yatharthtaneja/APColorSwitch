package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Line extends Obstacle{
    @Override
    public void Move() {
        ;
    }
}
class UnidirectionalLine extends Line{
    boolean Left;
    UnidirectionalLine(double y,boolean left){
        Left=left;
        setXpos(0);setYpos(y);
        if(left){
            Shape shape1 = new Rectangle( 0,y,Color.web("#35e2f2"));
            Shape shape2 = new Rectangle( 112.5,y,Color.web("#f6df0e"));
            Shape shape3 = new Rectangle( 225,y,Color.web("#8c13fb"));
            Shape shape4 = new Rectangle( 337.5,y,Color.web("#ff0080"));
            Shape shape5 = new Rectangle( 450,y,Color.web("#35e2f2"));
            Shape shape6 = new Rectangle( 562.5,y,Color.web("#f6df0e"));
            Shape shape7 = new Rectangle( 675,y,Color.web("#8c13fb"));
            Shape shape8 = new Rectangle( 787.5,y,Color.web("#ff0080"));
            AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);
            setObstacle(new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8));
        }
        else{
            Shape shape1 = new Rectangle( -450,y,Color.web("#35e2f2"));
            Shape shape2 = new Rectangle( -337.5,y,Color.web("#f6df0e"));
            Shape shape3 = new Rectangle( -225,y,Color.web("#8c13fb"));
            Shape shape4 = new Rectangle( -112.5,y,Color.web("#ff0080"));
            Shape shape5 = new Rectangle( 0,y,Color.web("#35e2f2"));
            Shape shape6 = new Rectangle( 112.5,y,Color.web("#f6df0e"));
            Shape shape7 = new Rectangle( 225,y,Color.web("#8c13fb"));
            Shape shape8 = new Rectangle( 337.5,y,Color.web("#ff0080"));
            AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);
            setObstacle(new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8));
        }
    }
    @Override
    public void Move(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setDuration(Duration.seconds(4));
        translateTransition.setNode(getObstacle());
        if(Left)
            translateTransition.setByX(-450);
        else
            translateTransition.setByX(450);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
}
class BidirectionalLine extends Line {
    BidirectionalLine(double y){
        setXpos(0);setYpos(y);
        Shape shape1 = new Rectangle( -450,y,Color.web("#35e2f2"));
        Shape shape2 = new Rectangle( -337.5,y,Color.web("#f6df0e"));
        Shape shape3 = new Rectangle( -225,y,Color.web("#8c13fb"));
        Shape shape4 = new Rectangle( -112.5,y,Color.web("#ff0080"));
        Shape shape5 = new Rectangle( 0,y,Color.web("#35e2f2"));
        Shape shape6 = new Rectangle( 112.5,y,Color.web("#f6df0e"));
        Shape shape7 = new Rectangle( 225,y,Color.web("#8c13fb"));
        Shape shape8 = new Rectangle( 337.5,y,Color.web("#ff0080"));
        Shape shape9 = new Rectangle( 450,y,Color.web("#35e2f2"));
        Shape shape10 = new Rectangle( 562.5,y,Color.web("#f6df0e"));
        Shape shape11 = new Rectangle( 675,y,Color.web("#8c13fb"));
        Shape shape12 = new Rectangle( 787.5,y,Color.web("#ff0080"));
        AddShape(shape1);AddShape(shape2);AddShape(shape3);AddShape(shape4);AddShape(shape5);AddShape(shape6);AddShape(shape7);AddShape(shape8);AddShape(shape9);AddShape(shape10);AddShape(shape11);AddShape(shape12);
        setObstacle(new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8,shape9,shape10,shape11,shape12));
    }
    @Override
    public void Move(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(4));
        translateTransition.setNode(getObstacle());
        translateTransition.setByX(450);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }
}
