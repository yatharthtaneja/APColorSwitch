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

public class Stats implements Initializable {
    @FXML
    private Button hbutton;
    @FXML
    private AnchorPane statsPane;
    private Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    private Text text;
    @FXML
    private Label starlabel;
    @FXML
    private Label highlabel;

    private static boolean lightmode;
    public void setTheme(boolean s){
        this.lightmode=s;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Score score= new Score();
        highlabel.setText(score.getScore());
        starlabel.setText(score.getStar());
        if(lightmode){
            statsPane.setStyle("-fx-background-color: #FFFFF0");
            addImage(hbutton,"sample/Assets/home.png");
            text.setFill(Color.valueOf("#141518"));
        }
        else{
            statsPane.setStyle("-fx-background-color: #141518");
            addImage(hbutton,"sample/Assets/home_white.png");
            text.setFill(Color.valueOf("#FFFFFF"));
        }
        hbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("menu.fxml");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        Menu controller = (Menu) loader.getController();
        controller.setStage(this.stage);
        Scene scene = new Scene(root,450,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
