package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    @FXML
    private AnchorPane menuAnchor;
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
    @FXML
    private Button SettingsButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button StatisticsButton;
    @FXML
    private Shape PlayButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    IntroScreen obj = new IntroScreen();

    obj.setRotate(ring,0,360,3,topleftcurve.getLayoutX(),topleftcurve.getLayoutY());
        obj.setRotate(ring11,0,360,3,topleftcurve11.getLayoutX(),topleftcurve11.getLayoutY());
        obj.setRotate(ring2,360,0,3,topleftcurve1.getLayoutX(),topleftcurve1.getLayoutY());
        addShadow(LoadButton);
        addShadow(StatisticsButton);
        addShadow(ExitButton);
        addShadow(SettingsButton);
        addShadowPlay(PlayButton);
        SettingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadSettingsButton();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        topleftcurve.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            System.out.println(topleftcurve.getLayoutX()+" "+topleftcurve.getLayoutY());

        });
    }
    public void addShadowPlay(Shape s1){
        DropShadow shadow= new DropShadow();
        s1.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
            s1.setFill(Color.valueOf("#8E2DE2"));
            s1.setEffect(shadow);
        });
        s1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{
            s1.setEffect(null);
            s1.setFill(Color.valueOf("#ffffff"));
        });
    }
    public void addShadow(Button button3){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#B5EDD0"));
        button3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            button3.setEffect(shadow);
            button3.setTextFill(Color.valueOf("#ffffff"));
        });

        button3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            button3.setEffect(null);
            button3.setTextFill(Color.valueOf("#141518"));
        });
    }
    @FXML
    public void loadSettingsButton() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("GameSettings.fxml"));
        Scene menuScene = new Scene(pane);
        Stage window = (Stage) menuAnchor.getScene().getWindow();

        window.setScene(menuScene);
        window.show();
    }
}
