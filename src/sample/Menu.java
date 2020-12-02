package sample;

import com.sun.source.util.ParameterNameProvider;
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
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
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
    private Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML
    private Text text;
    private static boolean lightmode;
    public void setTheme(boolean s){
        this.lightmode=s;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    IntroScreen obj = new IntroScreen();

        obj.setRotate(ring,0,360,3,topleftcurve.getLayoutX(),topleftcurve.getLayoutY());
        obj.setRotate(ring11,0,360,5,topleftcurve11.getLayoutX(),topleftcurve11.getLayoutY());
        obj.setRotate(ring2,360,0,4,topleftcurve1.getLayoutX(),topleftcurve1.getLayoutY());
//        obj.setRotate(Cross,360,0,3,crossC.getCenterX(),crossC.getCenterY());
        addShadow(LoadButton);
        addShadow(StatisticsButton);
        addShadow(ExitButton);
        addShadow(SettingsButton);
        addShadowPlay(PlayButton);
        if(lightmode){
            menuAnchor.setStyle("-fx-background-color: #FFFFF0");
            text.setFill(Color.valueOf("#141518"));
            PlayButton.setFill(Color.valueOf("#141518"));
        }
        else{
            menuAnchor.setStyle("-fx-background-color: #141518");
            text.setFill(Color.valueOf("#FFFFFF"));
            PlayButton.setFill(Color.valueOf("#FFFFFF"));
        }
        try {
            themeChanger("Stats.fxml");
            themeChanger("LoadGame.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }

        SettingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("GameSettings.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        PlayButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
//                loadButton("play.fxml");
                PlayGame game = new PlayGame();
                game.setStage(stage);
                game.setTheme(lightmode);
                game.start(stage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        StatisticsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("Stats.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        LoadButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("LoadGame.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Stage window = (Stage) menuAnchor.getScene().getWindow();
            window.close();

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
            if(lightmode)
                s1.setFill(Color.valueOf("#141518"));
            else
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
    public void loadButton(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        if(s.equals("GameSettings.fxml")){
            GameSettings controller = (GameSettings) loader.getController();
            controller.setStage(this.stage);
        }
        else if(s.equals("LoadGame.fxml")){
            LoadGame controller = (LoadGame) loader.getController();
            controller.setStage(this.stage);
        }
        else if(s.equals("Stats.fxml")){
            Stats controller = (Stats) loader.getController();
            controller.setStage(this.stage);
        }


        Scene scene = new Scene(root,450,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void themeChanger(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();

        if(s.equals("LoadGame.fxml")){
            LoadGame controller = (LoadGame) loader.getController();
            controller.setTheme(lightmode);

        }
        else if(s.equals("Stats.fxml")){
            Stats controller = (Stats) loader.getController();
            controller.setTheme(lightmode);

        }

    }

}
