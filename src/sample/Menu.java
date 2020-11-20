package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    private Group ring;
    @FXML
    private Group ring2;
    @FXML
    private Group ring11;
    @FXML
    private Arc topleftcurve;
    @FXML
    private Arc topleftcurve11;
    @FXML
    private Arc topleftcurve1;
@FXML
private Button LoadButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    IntroScreen obj = new IntroScreen();

    obj.setRotate(ring,0,360,3,topleftcurve.getLayoutX(),topleftcurve.getLayoutY());
        obj.setRotate(ring11,0,360,3,topleftcurve11.getLayoutX(),topleftcurve11.getLayoutY());
        obj.setRotate(ring2,360,0,3,topleftcurve1.getLayoutX(),topleftcurve1.getLayoutY());
    addShadow(LoadButton);

    }
    public void addShadow(Button button3){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#B5EDD0"));
        button3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            button3.setEffect(shadow);
            button3.setTextFill(Color.valueOf("#ffffff"));
        });

//Removing the shadow when the mouse cursor is off
        button3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            button3.setEffect(null);
            button3.setTextFill(Color.valueOf("#141518"));


        });
    }
}
