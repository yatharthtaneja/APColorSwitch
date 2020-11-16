package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Arc bottomrightcurve;
    @FXML
    private Arc toprightcurve;
    @FXML
    private Arc bottomleftcurve;
    @FXML
    private Arc topleftcurve;


    @FXML
    private Arc bottomrightcurve1;
    @FXML
    private Arc toprightcurve1;
    @FXML
    private Arc bottomleftcurve1;
    @FXML
    private Arc topleftcurve1;

    @FXML
    private Button play;
 @FXML
    private Polygon p1;
    @FXML
    private Polygon p2;
    @FXML
    private Polygon p3;
    @FXML
    private Polygon p4;
    @FXML
    private Rectangle r1;
    @FXML
    private Rectangle r2;
    @FXML
    private Rectangle r3;
    @FXML
    private Rectangle r4;
    @FXML
    private Text status;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
PathTransition transition = new PathTransition();
transition.setNode(r1);
transition.setNode(r2);
Circle circle= new Circle(50);
transition.setDuration(Duration.seconds(5));
transition.setPath(circle);
transition.setCycleCount(PathTransition.INDEFINITE);
transition.play();


    }
    @FXML
    public void displayPosition(MouseEvent event){
        status.setText("x = " + event.getScreenX()+ " y = " + event.getScreenY() );
    }
    @FXML
    private void play(ActionEvent event) {
        setRotate(topleftcurve,0,360,5);
        setRotate(toprightcurve,0,360,5);
        setRotate(bottomleftcurve,0,360,5);
        setRotate(bottomrightcurve,0,360,5);
        setRotate(topleftcurve1,360,0,7);
        setRotate(toprightcurve1,360,0,7);
        setRotate(bottomleftcurve1,360,0,7);
        setRotate(bottomrightcurve1,360,0,7);
        setRotate2(p1,360,0,7);
        setRotate2(p2,360,0,7);
        setRotate2(p3,360,0,7);
        setRotate2(p4,360,0,7);
//        setRotate3(r1,360,0,7);
//        setRotate3(r2,360,0,7);
//        setRotate3(r3,360,0,7);
//        setRotate3(r4,360,0,7);




    }

    public void setRotate(Shape a1, int start,int end, int duration) {

        Rotate rt2 = new Rotate(0,0,0);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                                        new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.setAutoReverse(true);
        timeline.play();
    }
    public void setRotate3(Shape a1, int start,int end, int duration) {

        Rotate rt2 = new Rotate(100,77,77);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.setAutoReverse(true);
        timeline.play();
    }
    public void setRotate2(Shape a1, int start,int end, int duration) { //for the plus traps
        Rotate rt2 = new Rotate(0,-38, 26.800018310546875);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.setAutoReverse(true);
        timeline.play();
    }
}
