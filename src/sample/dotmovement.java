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
        Group obstacle = new Group();
        myShape shape= new myShape();
        for (int i =0;i<5;i++){
            obstacle.getChildren().add(shape.MakeCircle("#35e2f2",35,65+(i*30)));

        }
        for (int i =0;i<5;i++){
            obstacle.getChildren().add(shape.MakeCircle("#f6df0e",65+(i*30),185));
        }
        for (int i =0;i<5;i++){
            obstacle.getChildren().add(shape.MakeCircle("#8c13fb",185,155-(i*30)));
        }
        for (int i =0;i<5;i++){
            obstacle.getChildren().add(shape.MakeCircle("#ff0080",155-(i*30),35));
        }

        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        KeyFrame Moveball = new KeyFrame(Duration.seconds(0.09), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Shape temp3=(Shape)obstacle.getChildren().get(0);
                Color c1= (Color) temp3.getFill();
                for(int i=0;i<20;i++){
                    Shape temp=(Shape)obstacle.getChildren().get(i);

                    if(i==19){
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
        Scene scene = new Scene(obstacle, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Path transition example");

        //Adding scene to the stage
        stage.setScene(scene);
        t1.getKeyFrames().add(Moveball);
        t1.play();
        //Creating a scene object


        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
} 