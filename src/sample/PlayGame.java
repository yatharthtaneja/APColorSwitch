package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayGame extends Application {
    int height=800;
    int width=450;
    Scene MainScene;
    Timeline Timer;
    Circle Ball;
    ArrayList<Rectangle> Obstacles;
    ArrayList<Group> ringobstacles;
    Label l1,l2;
    boolean GameOver;
    Button Restart;
    Group Root;
    int ticks=0,ymotion;
    @FXML
    private Stage MainStage;

    public void setStage(Stage stage){
        this.MainStage=stage;
    }

    public void AddObj2(){
        MyObstacle obj= new MyObstacle();
        Group curr0= obj.MakeringObstacle(225, 250);
        ringobstacles.add(curr0);
        Group curr= obj.MakeringObstacle(225, -ringobstacles.size() * 250);
        ringobstacles.add(curr);
//        Group curr2= obj.MakeCross(100, -ringobstacles.size() * 300);
//        ringobstacles.add(curr2);
        Group curr3 =obj.MakeSquareTrap(225, (-ringobstacles.size() +1)* 225);
        ringobstacles.add(curr3);
        Group curr4 =obj.MakeLine2a(-ringobstacles.size() * 250);
        ringobstacles.add(curr4);
        obj.rotateTransition(curr,4);
        obj.rotateTransition(curr0,4);

        obj.MoveLine2(curr4,4,-450);
//        obj.rotateTransition(curr2,4);
        obj.rotateTransition(curr3,5);

    }

    public void CheckRingCollision(){
        for (int j=0;j<3;j++){
            for(int i=0;i<ringobstacles.get(j).getChildren().size();i++){
                Shape s1= (Shape)ringobstacles.get(j).getChildren().get(i);
                if(Shape.intersect(Ball,s1).getBoundsInLocal().getWidth()!=-1&&(!s1.getFill().equals(Ball.getFill()))){
//                    if(!s1.getFill().equals(Ball.getFill())){
                    System.out.println("BAll color :"+ Ball.getFill()+ "Ring Color"+s1.getFill());
                    GameOver=true;
//                    }
                }
            }
            if(Ball.getCenterY()>height-10||Ball.getCenterY()<10)
                GameOver=true;
            if(GameOver){
                Ball.setCenterY(height-Ball.getRadius());
                l2.setText("Game Over");
                l2.setLayoutX(MainStage.getWidth()/2-35);
                l2.setLayoutY(MainStage.getHeight()/2-50);
                l2.setTextFill(Color.WHITESMOKE);
                l2.setScaleY(4);
                l2.setScaleX(4);
            }
        }
    }
    public void Jump(){
        if (!GameOver){
            if (ymotion>0)
                ymotion=0;
            ymotion-=8;
        }
    }


    public void StartGame2(){
        Ball.setCenterX(width/2);
        Ball.setCenterY(2*height/3);
        GameOver=false;
        ymotion=0;
        Root.getChildren().remove(Restart);
        Root.getChildren().removeAll(ringobstacles);
        ringobstacles.clear();
        for(int i=0;i<2;i++)
            AddObj2();
        l1.setText("Press Up key to start");
        l1.setScaleX(2);
        l1.setScaleY(2);

        l1.setLayoutX(MainStage.getWidth()/2-55);
        l1.setLayoutY(MainStage.getHeight()/2-50);
        l1.setTextFill(Color.WHITESMOKE);
        Timer.pause();
        Root.getChildren().add(l1);
        MainScene.setOnKeyReleased(keyEvent -> {
            String code=keyEvent.getCode().toString();
            if(code.equals("UP")){
                Root.getChildren().addAll(ringobstacles);
                Root.getChildren().remove(l1);
                Root.getChildren().remove(l2);
                Timer.play();
            }
        });
    }


    public void addImage(Button b1,String path){
        javafx.scene.image.Image img = new Image(path);
        ImageView view = new ImageView(img);
        view.setFitHeight(45);
        view.setPreserveRatio(true);
        b1.setGraphic(view);

    }

    public void loadButton(String s) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(s));
        Scene menuScene = new Scene(pane);
        MainStage.setScene(menuScene);
        MainStage.show();
    }

    @Override
    public void start(Stage MainStage) throws Exception {

        Root=new Group();
        Button pauseButton= new Button();
        pauseButton.setPrefHeight(50);
        pauseButton.setPrefWidth(50);
        pauseButton.setLayoutX(375);
        pauseButton.setLayoutY(10);
        addImage(pauseButton,"sample/Assets/pause_white.png");
        pauseButton.setId("pauseButton");
        Root.getStylesheets().add("sample/button.css");
        Root.getChildren().add(pauseButton);
        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e2)->{
            Parent pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("pause.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene menuScene = new Scene(pane);
            MainStage.setScene(menuScene);
            MainStage.show();
        });

        Label score = new Label("0");
        score.setLayoutX(10);
        score.setLayoutY(15);
        score.prefHeight(50);
        score.prefHeight(50);
        score.setTextFill(Color.WHITESMOKE);
        score.setFont(new Font("Cambria", 36));
        Root.getChildren().add(score);
        MainStage.setResizable(false);

        Ball=new Circle();
        Ball.setRadius(8);
        Ball.setCenterX(width/2);
        Ball.setCenterY(2*height/3);
        Ball.setFill(Color.valueOf("#35e2f2"));

        ringobstacles=new ArrayList<>();
        Timer=new Timeline();
        Timer.setCycleCount(Animation.INDEFINITE);

        Restart=new javafx.scene.control.Button("Restart Game");
        Restart.setId("restartButton");
        Restart.setTranslateX(120);
        Restart.setTranslateY(height/2+20);
        Restart.setPrefSize(200,50);

        GameOver=false;
        ymotion=0;
        ticks=0;

        l1=new javafx.scene.control.Label();l2=new Label();

        KeyFrame KF1=new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ticks++;
                if(ticks%2==0 && ymotion<20){
                    ymotion+=2;
                }
                int y=(int)Ball.getCenterY()+ymotion;
                Ball.setCenterY(y);
                MainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        String code=keyEvent.getCode().toString();
                        if(code.equals("UP")){
                            Jump();
                        }
                    }
                });
                CheckRingCollision();
                if(GameOver){
                    if(!Root.getChildren().contains(l2))
                        Root.getChildren().addAll(l2,Restart);
                    Restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Root.getChildren().remove(Restart);
                            Root.getChildren().remove(l2);
                            StartGame2();
                        }
                    });
                }
            }
        });

        KeyFrame KF2 =new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Ball.getCenterY()<height/2) {
                    for (int i=0;i<ringobstacles.size();i++){
                        Group obj=ringobstacles.get(i);
                        obj.setLayoutY(obj.getLayoutY() + 5);
                    }
                }
                if(ringobstacles.get(0).getLayoutY()>height) {
                    ringobstacles.remove(0);
                    if(ringobstacles.size()<3)
                        AddObj2();
                }
            }
        });
        Timer.getKeyFrames().addAll(KF1,KF2);
        Root.getChildren().addAll(Ball);
        MainScene=new Scene(Root,450,800);
        MainScene.setFill(Color.valueOf("#141518"));
        StartGame2();
        MainStage.setScene(MainScene);
        MainStage.show();
    }
}