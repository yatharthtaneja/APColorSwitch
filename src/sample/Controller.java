package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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


    }

    public void setRotate(Arc a1, int start,int end, int duration) {


        Rotate rt2 = new Rotate(0,0,0);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                                        new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.setAutoReverse(true);
        timeline.play();
    }
}
