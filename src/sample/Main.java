package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Arc arc = new Arc();
//        arc.setRadiusX(100.0f);
//        arc.setRadiusY(100.0f);
//        arc.setStartAngle(90.0f);
//        arc.setType(ArcType.ROUND);
////        arc.setLayoutX(54.0f);
////        arc.setLayoutY(74.0f);
//        arc.setCenterX(200);
//        arc.setCenterY(200);
//        arc.setFill(Color.BLUE);
//        arc.setLength(90.0f);
//        arc.setStroke(Color.valueOf("#35e2f2"));
//        arc.setStrokeLineCap(StrokeLineCap.BUTT);
//        arc.setStrokeMiterLimit(0.0f);
//        arc.setStrokeWidth(15.0f);

        primaryStage.setTitle("ColorSwitch");
        Parent root =FXMLLoader.load(getClass().getResource("IntroScreen.fxml"));
//        Group root= new Group(arc);
        Scene scene = new Scene(root,450,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
