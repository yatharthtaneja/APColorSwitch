package sample.SceneElement.Obstacles;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Shapes.Rectangle;
import sample.Shapes.Shape;

public class UnidirectionalLine extends Line {
    boolean Left;
    public UnidirectionalLine(double y,boolean left){
        Left=left;
        setXpos(0);setYpos(y);
        if(left){
            Shape shape1 = new Rectangle( 0,y-6.25, Color.web("#35e2f2"));
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