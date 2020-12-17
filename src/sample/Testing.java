package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.SceneElement.Obstacles.Cross;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class Testing extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root=new Group();
        Scene scene = new Scene(root,450,800);
        Cross cross=new Cross(225,400);
        cross.Move();
        Button button= new Button();
        button.setPrefHeight(50);
        button.setPrefWidth(100);
        button.setLayoutX(175);
        button.setLayoutY(650);
        button.setText("Push");
        AtomicInteger i= new AtomicInteger();
        Duration dur=new Duration(2000);
        double x;
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e3)->{
            if(i.get() %3==0)
                cross.Transition.pause();
            else if(i.get()%3==1) {
                cross.Transition.jumpTo(dur);
            }
            else
                cross.Transition.play();
            i.incrementAndGet();
        });
        root.getChildren().addAll(cross.getObstacle(),button);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Player P=new Player();
        P.setSaveGame(false);
        try {
            ColorSwitch.Serialise(P,"1.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ColorSwitch.Serialise(P,"2.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ColorSwitch.Serialise(P,"3.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ColorSwitch.Serialise(P,"4.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
