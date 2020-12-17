package sample.Screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ColorSwitch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("ColorSwitch");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/IntroScreen.fxml"));
        Parent root =loader.load();
        IntroScreen controller = (IntroScreen) loader.getController();
        controller.setStage(primaryStage);
        AudioClip Background=new AudioClip(this.getClass().getResource("Audio/Background.wav").toString());
        Background.setCycleCount(AudioClip.INDEFINITE);
        Background.setVolume(0.5);
        Background.play();
        controller.setBackground(Background);
        Scene scene = new Scene(root,450,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void Serialise(Serializable data, String filename) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            oos.writeObject(data);
        }
    }
    public static Object Deseriallise(String filename) throws Exception{
        try(ObjectInputStream ois= new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            return ois.readObject();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}