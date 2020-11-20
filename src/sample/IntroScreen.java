package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class IntroScreen implements Initializable {
    @FXML
    private Group ring;
    @FXML
    private Arc topleftcurve;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//    rotateTransition(ring,4);
    setRotate(ring,0,360,4,topleftcurve.getLayoutX(),topleftcurve.getLayoutY());
    }

    public void rotateTransition(Group g1,int duration){
        RotateTransition rt = new RotateTransition();
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setDuration(Duration.seconds(duration));
        rt.setNode(g1);
        rt.play();
    }
    public void setRotate(Group a1, int start, int end, int duration , double centerx , double centery) {
        System.out.println("Center x: "+ centerx+" Center Y:" + centery);
        Rotate rt2 = new Rotate(0,centerx,centery);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
