package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private static boolean lightmode=false;
@FXML
private Text text;
    @FXML
    private Label text3;
    @FXML
    private Label text2;

private Stage stage;
public void setStage(Stage stage){
    this.stage=stage;
}
public void  setTheme(boolean s){
    this.lightmode=s;
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu obj = new Menu();
        obj.addShadow(ResetButton);
        obj.addShadow(LightMode);
        obj.addShadow(HowButton);
        obj.addShadow(DeveloperButton);
        addImage(hbutton,"sample/Assets/home_white.png");
        addImage(sbutton,"sample/Assets/volumeOn_white.png");
        addImage(mbutton,"sample/Assets/volumeOn_white.png");
        if(lightmode){
            LightMode.setText("Dark Mode");
            settingsPane.setStyle("-fx-background-color: #FFFFF0");
            addImage(hbutton,"sample/Assets/home.png");
            addImage(sbutton,"sample/Assets/volumeOn.png");
            addImage(mbutton,"sample/Assets/volumeOn.png");
            text.setFill(Color.valueOf("#141518"));
            text2.setTextFill(Color.valueOf("#141518"));
            text3.setTextFill(Color.valueOf("#141518"));

        }
        else{
            LightMode.setText("Light Mode");
            settingsPane.setStyle("-fx-background-color: #141518");
            addImage(hbutton,"sample/Assets/home_white.png");
            addImage(sbutton,"sample/Assets/volumeOn_white.png");
            addImage(mbutton,"sample/Assets/volumeOn_white.png");
            text.setFill(Color.valueOf("#FFFFFF"));
            text2.setTextFill(Color.valueOf("#FFFFFF"));
            text3.setTextFill(Color.valueOf("#FFFFFF"));
        }
        hbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("menu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        DeveloperButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("Developers.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        ResetButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
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
                loadButton("HowToplay.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        LightMode.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            if(lightmode){
                setTheme(false);
            }
            else
            setTheme(true);
            if(lightmode){
                LightMode.setText("Dark Mode");
                settingsPane.setStyle("-fx-background-color: #FFFFF0");
                addImage(hbutton,"sample/Assets/home.png");
                addImage(sbutton,"sample/Assets/volumeOn.png");
                addImage(mbutton,"sample/Assets/volumeOn.png");
                text.setFill(Color.valueOf("#141518"));
                text2.setTextFill(Color.valueOf("#141518"));
                text3.setTextFill(Color.valueOf("#141518"));
                try {
                    themeChanger("HowToplay.fxml");
                    themeChanger("Developers.fxml");
                    themeChanger("menu.fxml");


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
                text.setFill(Color.valueOf("#FFFFFF"));
                text2.setTextFill(Color.valueOf("#FFFFFF"));
                text3.setTextFill(Color.valueOf("#FFFFFF"));
                try {
                    themeChanger("HowToplay.fxml");
                    themeChanger("Developers.fxml");
                    themeChanger("menu.fxml");

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
        if(s.equals("menu.fxml")){
            Menu controller = (Menu) loader.getController();
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
        if(s.equals("menu.fxml")){
            Menu controller = (Menu) loader.getController();
            controller.setTheme(lightmode);

        }
        else if(s.equals("HowToplay.fxml")){
            HowToplay controller = (HowToplay) loader.getController();
            controller.setTheme(lightmode);
//            System.out.println(lightmode);

        }
        else if(s.equals("Developers.fxml")){
            Developers controller = (Developers) loader.getController();
            controller.setTheme(lightmode);


        }

    }
}
