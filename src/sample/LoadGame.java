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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addImage(hbutton,"sample/Assets/home_white.png");
        addShadow(l5);
        addShadow(l4);
        addShadow(l3);
        addShadow(l2);
        hbutton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("menu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
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
        Parent pane = FXMLLoader.load(getClass().getResource(s));
        Scene menuScene = new Scene(pane);
        Stage window = (Stage) loadPane.getScene().getWindow();
//        System.out.println(window.getMaxHeight());
        window.setScene(menuScene);
        window.show();
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
