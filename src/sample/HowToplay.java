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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HowToplay implements Initializable {

    @FXML
    private Button hbutton;
    @FXML
    private AnchorPane howPane;

    private Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
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
        Scene scene = new Scene(root,450,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
