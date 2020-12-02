package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;

public class RectangleOfDots extends Obstacle{
    public RectangleOfDots(double x,double y){
        setXpos(x);setYpos(y);
        setListOfShapes(new ArrayList<>());
        Group obstacle=new Group();
        for (int i =0;i<9;i++){
            Circle circle=new Circle((x-135),(y-105)+(i*30),15, Color.web("#35e2f2"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<9;i++){
            Circle circle=new Circle((x-105)+(i*30),(y+135),15, Color.web("#f6df0e"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<9;i++){
            Circle circle=new Circle((x+135),(y+105)-(i*30),15, Color.web("#8c13fb"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        for (int i =0;i<9;i++){
            Circle circle=new Circle((x+105)-(i*30),(y-135),15, Color.web("#ff0080"));
            AddShape(circle);
            obstacle.getChildren().add(circle.getShape());
        }
        setObstacle(obstacle);
    }
    @Override
    public void Move() {
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        KeyFrame Moveball = new KeyFrame(Duration.seconds(0.09), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.scene.shape.Shape temp3=(javafx.scene.shape.Shape) getObstacle().getChildren().get(0);
                Color c1= (Color) temp3.getFill();
                for(int i=0;i<36;i++){
                    javafx.scene.shape.Shape temp=(javafx.scene.shape.Shape)getObstacle().getChildren().get(i);

                    if(i==35){
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
        t1.getKeyFrames().add(Moveball);
        t1.play();
    }
}
