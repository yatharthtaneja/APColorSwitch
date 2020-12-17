package sample.Shapes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Trilateral extends sample.SceneElement.Obstacles.Obstacle {
    Timeline Timeline;
    public Trilateral(double x,double y){
        Timeline=new Timeline();
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        for (int i =0;i<8;i++){
            Circle circle;
            if (i<6)
                circle=new Circle(x+(i*15),y-138.564+(i*25.9807),15, Color.web("#35e2f2"));
            else
                circle=new Circle(x+(i*15),y-138.564+(i*25.9807),15, Color.web("#f6df0e"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<8;i++){
            Circle circle;
            if (i<4)
                circle=new Circle(x+120-(i*30),y+69.381,15, Color.web("#f6df0e"));
            else
                circle=new Circle(x+120-(i*30),y+69.281,15, Color.web("#8c13fb"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<8;i++){
            Circle circle;
            if (i<2)
                circle=new Circle(x-120+(i*15),y+69.381-(i*25.9807),15, Color.web("#8c13fb"));
            else
                circle=new Circle(x-120+(i*15),y+69.381-(i*25.9807),15, Color.web("#ff0080"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        setObstacle(obstacle);

        Timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame Moveball = new KeyFrame(Duration.seconds(0.09), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.scene.shape.Shape temp3=(javafx.scene.shape.Shape) getObstacle().getChildren().get(23);
                Color c1= (Color) temp3.getFill();
                for(int i=23;i>=0;i--){
                    javafx.scene.shape.Shape temp=(javafx.scene.shape.Shape)getObstacle().getChildren().get(i);

                    if(i==0){
                        temp.setFill(c1);
                        temp.setStroke(c1);
                    }
                    else {
                        javafx.scene.shape.Shape temp2 = (javafx.scene.shape.Shape) getObstacle().getChildren().get(i - 1);
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