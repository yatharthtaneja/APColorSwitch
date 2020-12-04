package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadGame implements Initializable {
    @FXML
    private Button hbutton;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private Shape l5;
    @FXML
    private Shape l2;
    @FXML
    private Shape l3;
    @FXML
    private Shape l4;
    private Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    private Text text;
    private static boolean lightmode;
    public void setTheme(boolean s){
        this.lightmode=s;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addImage(hbutton,"sample/Assets/home_white.png");
        addShadow(l5);
        addShadow(l4);
        addShadow(l3);
        addShadow(l2);
        if(lightmode){
            loadPane.setStyle("-fx-background-color: #FFFFF0");
            addImage(hbutton,"sample/Assets/home.png");
            text.setFill(Color.valueOf("#141518"));
        }
        else{
            loadPane.setStyle("-fx-background-color: #141518");
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
        l5.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                player p2 = null;
                try {
                    p2 = (player) resourceManager.loadData("1.save");
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
                PlayGame game = new PlayGame();
                game.setCurrentPlayer(p2);
                game.setStage(stage);
                game.setTheme(lightmode);
                game.start(stage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
    public void addImage(Button b1, String path){
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
    public void addShadow(Shape s1) {
        DropShadow shadow = new DropShadow();
        s1.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            s1.setFill(Color.valueOf("#8E2DE2"));
            s1.setEffect(shadow);
            shadow.setColor(Color.valueOf("#B5EDD0"));
        });
        s1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            s1.setEffect(null);
            s1.setFill(Color.valueOf("#d41e8d"));
        });
    }
}
