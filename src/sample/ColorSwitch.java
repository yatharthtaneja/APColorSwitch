package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ColorSwitch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("ColorSwitch");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IntroScreen.fxml"));
        Parent root =loader.load();
        IntroScreen controller = (IntroScreen) loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(root,450,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        AudioClip Background=new AudioClip(this.getClass().getResource("Background.wav").toString());
        Background.setCycleCount(AudioClip.INDEFINITE);
        Background.setVolume(0.5);
        Background.play();
        controller.setBackground(Background);
    }
    public static void Serialise(){

    }
    public static void Deserialise(){

    }
    public static void main(String[] args) {
        launch(args);
    }
}