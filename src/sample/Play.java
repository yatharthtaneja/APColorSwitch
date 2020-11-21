package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Play implements Initializable {
    @FXML
    private AnchorPane playPane;
    @FXML
    private Pane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Arc arc = new Arc();
        arc.setRadiusX(100.0f);
        arc.setRadiusY(100.0f);
        arc.setStartAngle(90.0f);
        arc.setType(ArcType.OPEN);
//        arc.setLayoutX(54.0f);
//        arc.setLayoutY(74.0f);
        arc.setCenterX(200.0f);
        arc.setCenterY(200.0f);
        arc.setLength(90.0f);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.valueOf("#35e2f2"));
        arc.setStrokeLineCap(StrokeLineCap.BUTT);
        arc.setStrokeMiterLimit(0.0f);
        arc.setStrokeWidth(15.0f);

        Arc arc2 = new Arc();
        arc2.setRadiusX(100.0f);
        arc2.setRadiusY(100.0f);
        arc2.setStartAngle(180.0f);
        arc2.setType(ArcType.OPEN);
//        arc2.setLayoutX(54.0f);
//        arc2.setLayoutY(74.0f);
        arc2.setCenterX(200.0f);
        arc2.setCenterY(300.0f);
        arc2.setLength(90.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.valueOf("#35e2f2"));
        arc2.setStrokeLineCap(StrokeLineCap.BUTT);
        arc2.setStrokeMiterLimit(0.0f);
        arc2.setStrokeWidth(15.0f);
        Group ring = new Group(arc,arc2);
//        System.out.println("arc1 X:"+);


        for(int i=0;i<100;i++) {
            arc.setCenterY(200+i);
//            ring.get

            rootPane.getChildren().setAll(ring);
        }
    }
}
