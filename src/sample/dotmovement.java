import javafx.animation.*;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.scene.shape.*;

import javafx.stage.Stage;
import javafx.util.Duration;
import sample.MyObstacle;
import sample.myShape;

public class dotmovement extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Circle



        Group g1 = rectangle2(300,300);
        Scene scene = new Scene(g1, 600, 600);
        scene.setFill(Color.BLUE);
        //Setting title to the Stage
        move(g1);
        stage.setTitle("Path transition example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Creating a scene object


        //Displaying the contents of the stage
        stage.show();
    }
    public Group rectangle2(double x, double y){
        Group obstacle = new Group();
        myShape shape= new myShape();
        for (int i =0;i<9;i++){
            obstacle.getChildren().add(shape.MakeCircle("#35e2f2",x-135,(y-105)+(i*30)));

        }
        for (int i =0;i<9;i++){
            obstacle.getChildren().add(shape.MakeCircle("#f6df0e",(x-105)+(i*30),y+135));
        }
        for (int i =0;i<9;i++){
            obstacle.getChildren().add(shape.MakeCircle("#8c13fb",x+135,(y+105)-(i*30)));
        }
        for (int i =0;i<9;i++){
            obstacle.getChildren().add(shape.MakeCircle("#ff0080",(x+105)-(i*30),y-135));
        }
        return obstacle;
    }
    public void move(Group obstacle){
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        KeyFrame Moveball = new KeyFrame(Duration.seconds(0.09), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Shape temp3=(Shape)obstacle.getChildren().get(0);
                Color c1= (Color) temp3.getFill();
                for(int i=0;i<36;i++){
                    Shape temp=(Shape)obstacle.getChildren().get(i);

                    if(i==35){
                        temp.setFill(c1);
                    }
                    else {
                        Shape temp2 = (Shape) obstacle.getChildren().get(i + 1);
                        temp.setFill(temp2.getFill());

                    }
                    obstacle.getChildren().remove(i);
                    obstacle.getChildren().add(i,temp);
                }
            }
        });
        t1.getKeyFrames().add(Moveball);
        t1.play();
    }
    public static void main(String args[]){
        launch(args);
    }
} 