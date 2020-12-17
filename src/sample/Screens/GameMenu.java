package sample.Screens;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameMenu implements Initializable {
    @FXML private AnchorPane menuAnchor;
    @FXML private Group ring;
    @FXML private Group ring2;
    @FXML private Group ring11;
    @FXML private Arc topleftcurve;
    @FXML private Arc topleftcurve11;
    @FXML private Arc topleftcurve1;
    @FXML private Button LoadButton;
    @FXML private Button SettingsButton;
    @FXML private Button ExitButton;
    @FXML private Button StatisticsButton;
    @FXML private Shape PlayButton;
    @FXML private Text text;
    @FXML private Text text1;
    private Stage Currentstage;
    private static boolean DarkTheme=true;
    static boolean SoundOn=true;
    private static AudioClip Background;
    public void setStage(Stage stage){
        this.Currentstage = stage;
    }
    public void setTheme(boolean darktheme){
        DarkTheme=darktheme;
    }
    public void setSoundOn(boolean sound){
        SoundOn=sound;
    }
    public void setBackground(AudioClip background){
        if(Background==null)
            Background=background;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRotate(ring,0,360,3,topleftcurve.getLayoutX(),topleftcurve.getLayoutY());
        setRotate(ring11,0,360,5,topleftcurve11.getLayoutX(),topleftcurve11.getLayoutY());
        setRotate(ring2,360,0,4,topleftcurve1.getLayoutX(),topleftcurve1.getLayoutY());
        addShadow(LoadButton);addShadow(StatisticsButton);addShadow(ExitButton);addShadow(SettingsButton);addShadowPlay(PlayButton);
        if(!DarkTheme){
            menuAnchor.setStyle("-fx-background-color: #FFFFF0");
            text.setFill(Color.valueOf("#141518"));text1.setFill(Color.valueOf("#141518"));
            PlayButton.setFill(Color.valueOf("#141518"));
        }
        else{
            menuAnchor.setStyle("-fx-background-color: #141518");
            text.setFill(Color.valueOf("#FFFFFF"));text1.setFill(Color.valueOf("#FFFFFF"));
            PlayButton.setFill(Color.valueOf("#FFFFFF"));
        }
        try {
            ThemeChanger("FXML/Stats.fxml");
            ThemeChanger("FXML/LoadGame.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }

        SettingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                LoadButton("FXML/GameSettings.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        PlayButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                Game game = new Game();
                game.setStage(Currentstage);
                game.setTheme(DarkTheme);
                game.start(Currentstage);
                game.setSoundOn(SoundOn);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        StatisticsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                LoadButton("FXML/Stats.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        LoadButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                LoadButton("FXML/LoadGame.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            ButtonSound();
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
            if(!DarkTheme)
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
    public void LoadButton(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        if(s.equals("FXML/GameSettings.fxml")){
            GameSettings controller = (GameSettings) loader.getController();
            controller.setStage(this.Currentstage);
            controller.setBackground(Background);
        }
        else if(s.equals("FXML/LoadGame.fxml")){
            LoadGame controller = (LoadGame) loader.getController();
            controller.setStage(this.Currentstage);
        }
        else if(s.equals("FXML/Stats.fxml")){
            Stats controller = (Stats) loader.getController();
            controller.setStage(this.Currentstage);
        }
        Scene scene = new Scene(root,450,800);
        Currentstage.setScene(scene);
        Currentstage.setResizable(false);
        Currentstage.show();
    }
    public void ThemeChanger(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        if(s.equals("FXML/LoadGame.fxml")){
            LoadGame controller = (LoadGame) loader.getController();
            controller.setTheme(DarkTheme);
            controller.setSoundOn(SoundOn);
        }
        else if(s.equals("FXML/Stats.fxml")){
            Stats controller = (Stats) loader.getController();
            controller.setTheme(DarkTheme);
            controller.setSoundOn(SoundOn);
        }
    }
    private void ButtonSound(){
        if (SoundOn){
            AudioClip Button=new AudioClip(this.getClass().getResource("Audio/Button.wav").toString());
            Button.play();
        }
    }
    private void setRotate(Group a1, int start, int end, int duration , double centerx , double centery) {
        Rotate rt2 = new Rotate(0,centerx,centery);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
                new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
