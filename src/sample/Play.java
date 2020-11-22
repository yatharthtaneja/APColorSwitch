package sample;

import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Play implements Initializable {
    @FXML
    private AnchorPane playPane;
    @FXML
    private Pane rootPane;
    @FXML
    private Circle circle;
    @FXML
    private Group group1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = "M 375.6 185.6 c 37.2 38 55.2 71.6 94 71.6 s 70 -31.2 70 -70 s -31.2 -70.4 -70 -70 c -38.8 0.4 -53.2 28.8 -88.4 68.4 c -35.6 35.2 -62.8 71.6 -101.6 71.6 s -70 -31.2 -70 -70 s 31.2 -70 70 -70 S 344.8 154.4 375.6 185.6 z";
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
        Group ring2 = new Group(arc,arc2);
        Rectangle r1= makeRect(0,440);
        Rectangle r2= makeRect(90,440);
        Rectangle r3= makeRect(180,440);
        Rectangle r4= makeRect(270,440);
        Rectangle r5= makeRect(360,440);
        r2.setFill(Color.PURPLE);
        r4.setFill(Color.PURPLE);


        Group ring = new Group(r1,r2,r3,r4,r5);

//        System.out.println("arc1 X:"+);
//        SVGPath infinity= new SVGPath();
//        infinity.setContent(path);
//        infinity.setLayoutX(-400);
//        infinity.setLayoutY(-300);
//        PathTransition transition= new PathTransition();
//        transition.setPath(infinity);
//        transition.setNode(group1);
//        transition.setDuration(Duration.seconds(6));
//        transition.setCycleCount(PathTransition.INDEFINITE);
//        transition.play();
//        rootPane.getChildren().setAll(ring);
         rootPane.getChildren().add(ring);
//        FillTransition fill = new FillTransition();
//        fill.setShape(r3);
//        fill.setDuration(Duration.seconds(4));
//        fill.setFromValue(Color.CRIMSON);
//        fill.setToValue(Color.ROYALBLUE);
//        fill.setCycleCount(FillTransition.INDEFINITE);
//        fill.play();
        

//        rootPane.getChildren().add(ring2);

    }
    public Rectangle makeRect(double x, double y){
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(30);
        rect.setWidth(90);
        rect.setFill(Color.BLUE);
        return rect;
    }
}
