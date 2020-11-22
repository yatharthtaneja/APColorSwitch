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
        HowButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            try {
                loadButton("HowToplay.fxml");
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
        Parent pane = FXMLLoader.load(getClass().getResource(s));
        Scene menuScene = new Scene(pane);
        Stage window = (Stage) settingsPane.getScene().getWindow();
//        System.out.println(window.getMaxHeight());
        window.setScene(menuScene);
        window.show();
    }
}
