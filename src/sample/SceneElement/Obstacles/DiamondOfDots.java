package sample.SceneElement.Obstacles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Shapes.Circle;

import java.util.ArrayList;

public class DiamondOfDots extends sample.SceneElement.Obstacles.Obstacle {
    Timeline Timeline;
    public DiamondOfDots(double x,double y){
        Timeline=new Timeline();
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        for (int i =0;i<6;i++){
            Circle circle=new Circle(x+(i*20),(y-180)+(i*30),15, Color.web("#35e2f2"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<6;i++){
            Circle circle=new Circle(x+120-(i*20),y+(i*30),15, Color.web("#f6df0e"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<6;i++){
            Circle circle=new Circle(x-(i*20),(y+180)-(i*30),15, Color.web("#8c13fb"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<6;i++){
            Circle circle=new Circle(x-120+(i*20),(y-(i*30)),15, Color.web("#ff0080"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        setObstacle(obstacle);

        Timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame Moveball = new KeyFrame(Duration.seconds(0.14), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.scene.shape.Shape temp3=(javafx.scene.shape.Shape) getObstacle().getChildren().get(0);
                Color c1= (Color) temp3.getFill();
                for(int i=0;i<24;i++){
                    javafx.scene.shape.Shape temp=(javafx.scene.shape.Shape)getObstacle().getChildren().get(i);

                    if(i==23){
                        temp.setFill(c1);
                        temp.setStroke(c1);
                    }
                    else {
                        javafx.scene.shape.Shape temp2 = (javafx.scene.shape.Shape) getObstacle().getChildren().get(i + 1);
                        temp.setFill(temp2.getFill());
                        temp.setStroke(temp2.getStroke());
                    }
                    getObstacle().getChildren().remove(i);
                    getObstacle().getChildren().add(i,temp);
                }
            }
        });
        Timeline.getKeyFrames().add(Moveball);
    }
    @Override
    public void Move() {
        Play();
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
        Play();
        Pause();
        Timeline.jumpTo(new Duration(millis));
    }
}
