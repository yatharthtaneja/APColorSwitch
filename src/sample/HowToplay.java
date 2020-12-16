package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class HowToplay implements Initializable {

    @FXML
    private Button hbutton;
    @FXML
    private AnchorPane howPane;
    @FXML
    private Text text;
    private static boolean lightmode;
    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    public void setTheme(boolean s){
        this.lightmode=s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addImage(hbutton,"sample/Assets/back_white.png");
        hbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("GameSettings.fxml");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        if(lightmode){

            howPane.setStyle("-fx-background-color: #FFFFF0");
            addImage(hbutton,"sample/Assets/back-arrow.png");
            text.setFill(Color.valueOf("#141518"));


        }
        else{

            howPane.setStyle("-fx-background-color: #141518");
            addImage(hbutton,"sample/Assets/back_white.png");
            text.setFill(Color.valueOf("#FFFFFF"));


        }
    }
    public void addImage(Button b1,String path){
        Image img = new Image(path);
        ImageView view = new ImageView(img);
        view.setFitHeight(60);
        view.setPreserveRatio(true);
        b1.setGraphic(view);

    }
    public void loadButton(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameSettings.fxml"));
        Parent root =loader.load();
        GameSettings controller = (GameSettings) loader.getController();
        controller.setStage(stage);
        controller.setTheme(lightmode);
        Scene scene = new Scene(root,450,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
