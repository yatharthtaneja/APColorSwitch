package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameSettings implements Initializable {
    @FXML
    private Button ResetButton;
    @FXML
    private Button LightMode;
    @FXML
    private Button DeveloperButton;
    @FXML
    private Button HowButton;
    @FXML
    private Button hbutton;
    @FXML
    private Button sbutton;
    @FXML
    private Button mbutton;
    @FXML
    private AnchorPane settingsPane;
    private static boolean DarkTheme=true;
@FXML
private Text text;
    @FXML
    private Label Music;
    @FXML
    private Label Sound;
    static boolean SoundOn =true;
    static boolean MusicOn =true;
    static private AudioClip Background;
    public void setBackground(AudioClip background){
        Background=background;
    }
private Stage stage;

public void setStage(Stage stage){
    this.stage=stage;
}
public void  setTheme(boolean darktheme){
    DarkTheme=darktheme;
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addShadow(ResetButton);
        addShadow(LightMode);
        addShadow(HowButton);
        addShadow(DeveloperButton);
        if(!DarkTheme){
            LightMode.setText("Dark Mode");
            settingsPane.setStyle("-fx-background-color: #FFFFF0");
            addImage(hbutton,"sample/Assets/home.png");
            if(SoundOn)
                addImage(sbutton,"sample/Assets/volumeOn.png");
            else
                addImage(sbutton,"sample/Assets/mute.png");
            if(MusicOn)
                addImage(mbutton,"sample/Assets/volumeOn.png");
            else
                addImage(mbutton,"sample/Assets/mute.png");
            text.setFill(Color.valueOf("#141518"));
            Sound.setTextFill(Color.valueOf("#141518"));
            Music.setTextFill(Color.valueOf("#141518"));
        }
        else{
            LightMode.setText("Light Mode");
            settingsPane.setStyle("-fx-background-color: #141518");
            addImage(hbutton,"sample/Assets/home_white.png");
            if(SoundOn)
                addImage(sbutton,"sample/Assets/volumeOn_white.png");
            else
                addImage(sbutton,"sample/Assets/mute_white.png");
            if(MusicOn)
                addImage(mbutton,"sample/Assets/volumeOn_white.png");
            else
                addImage(mbutton,"sample/Assets/mute_white.png");
            text.setFill(Color.valueOf("#FFFFFF"));
            Sound.setTextFill(Color.valueOf("#FFFFFF"));
            Music.setTextFill(Color.valueOf("#FFFFFF"));
        }
        sbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            ButtonSound();
            if(SoundOn){
                if(DarkTheme)
                    addImage(sbutton,"sample/Assets/mute_white.png");
                else
                    addImage(sbutton,"sample/Assets/mute.png");
            }
            else{
                if(DarkTheme)
                    addImage(sbutton,"sample/Assets/volumeOn_white.png");
                else
                    addImage(sbutton,"sample/Assets/volumeOn.png");
            }
            SoundOn=!SoundOn;
            try {
                Powerups.setSoundOn(SoundOn);
                themeChanger("HowToplay.fxml");
                themeChanger("Developers.fxml");
                themeChanger("GameMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            ButtonSound();
            if(MusicOn){
                Background.stop();
                if(DarkTheme)
                    addImage(mbutton,"sample/Assets/mute_white.png");
                else
                    addImage(mbutton,"sample/Assets/mute.png");
            }
            else{
                Background.play();
                if(DarkTheme)
                    addImage(mbutton,"sample/Assets/volumeOn_white.png");
                else
                    addImage(mbutton,"sample/Assets/volumeOn.png");
            }
            MusicOn=!MusicOn;
            try {
                themeChanger("HowToplay.fxml");
                themeChanger("Developers.fxml");
                themeChanger("GameMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        hbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                loadButton("GameMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        DeveloperButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                loadButton("Developers.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        ResetButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            ButtonSound();
            Score obj2= new Score();
            obj2.reset();
            player p1 = new player();
            p1.SaveGame=false;
            for(int i=0;i<=4;i++){
                try {
                    resourceManager.save(p1,i+".save");
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        });
        HowButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                ButtonSound();
                loadButton("HowToplay.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        LightMode.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            ButtonSound();
            if(DarkTheme)
                setTheme(false);
            else
                setTheme(true);
            if(!DarkTheme){
                LightMode.setText("Dark Mode");
                settingsPane.setStyle("-fx-background-color: #FFFFF0");
                addImage(hbutton,"sample/Assets/home.png");
                if(SoundOn)
                    addImage(sbutton,"sample/Assets/volumeOn.png");
                else
                    addImage(sbutton,"sample/Assets/mute.png");
                if(MusicOn)
                    addImage(mbutton,"sample/Assets/volumeOn.png");
                else
                    addImage(mbutton,"sample/Assets/mute.png");
                text.setFill(Color.valueOf("#141518"));
                Music.setTextFill(Color.valueOf("#141518"));
                Sound.setTextFill(Color.valueOf("#141518"));
                try {
                    themeChanger("HowToplay.fxml");
                    themeChanger("Developers.fxml");
                    themeChanger("GameMenu.fxml");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            else{
                LightMode.setText("Light Mode");
                settingsPane.setStyle("-fx-background-color: #141518");
                addImage(hbutton,"sample/Assets/home_white.png");
                addImage(sbutton,"sample/Assets/volumeOn_white.png");
                addImage(mbutton,"sample/Assets/volumeOn_white.png");
                if(SoundOn)
                    addImage(sbutton,"sample/Assets/volumeOn_white.png");
                else
                    addImage(mbutton,"sample/Assets/mute_white.png");
                if(MusicOn)
                    addImage(mbutton,"sample/Assets/volumeOn_white.png");
                else
                    addImage(mbutton,"sample/Assets/mute_white.png");
                text.setFill(Color.valueOf("#FFFFFF"));
                Sound.setTextFill(Color.valueOf("#FFFFFF"));
                Music.setTextFill(Color.valueOf("#FFFFFF"));
                try {
                    themeChanger("HowToplay.fxml");
                    themeChanger("Developers.fxml");
                    themeChanger("GameMenu.fxml");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
    public void addImage(Button b1,String path){
        Image img = new Image(path);
        ImageView view = new ImageView(img);
        view.setFitHeight(60);
        view.setPreserveRatio(true);
        b1.setGraphic(view);

    }
    public void loadButton(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        if(s.equals("GameMenu.fxml")){
            GameMenu controller = (GameMenu) loader.getController();
            controller.setStage(this.stage);
        }
        else if(s.equals("HowToplay.fxml")){
            HowToplay controller = (HowToplay) loader.getController();
            controller.setStage(this.stage);
        }
        else if(s.equals("Developers.fxml")){
            Developers controller = (Developers) loader.getController();
            controller.setStage(this.stage);
        }
        Scene scene = new Scene(root,450,800);
        scene.setFill(Color.WHITESMOKE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void themeChanger(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        if(s.equals("GameMenu.fxml")){
            GameMenu controller = (GameMenu) loader.getController();
            controller.setTheme(DarkTheme);
            controller.setSoundOn(SoundOn);
        }
        else if(s.equals("HowToplay.fxml")){
            HowToplay controller = (HowToplay) loader.getController();
            controller.setTheme(DarkTheme);
            controller.setSoundOn(SoundOn);
        }
        else if(s.equals("Developers.fxml")){
            Developers controller = (Developers) loader.getController();
            controller.setTheme(DarkTheme);
            controller.setSoundOn(SoundOn);
        }
    }
    private void ButtonSound(){
        if (SoundOn){
            AudioClip Button=new AudioClip(this.getClass().getResource("Button.wav").toString());
            Button.play();
        }
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
}
