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
    private int height=800;
    private int width=450;
    private Scene MainScene;
    private Stage MainStage;
    private Timeline Timer;
    private Circle Ball;
    private ArrayList<Obstacle> Obstacles;
    private ArrayList<Powerups> Powerups;
    private ArrayList<Group> ringobstacles;
    private Label l1,l2,score;
    private boolean GameOver,ScoreIncrease;
    private Button Restart;
    private Group Root;
    private double ticks=0,Gravity;
    private Color Colors[]={Color.web("#35e2f2"),Color.web("#f6df0e"),Color.web("#8c13fb"),Color.web("#ff0080")};

    public void setStage(Stage stage){
        this.MainStage=stage;
    }
    private static boolean lightmode;
    public void setTheme(boolean s){
        this.lightmode=s;
    }

    public void AddObj2(){
        MyObstacle obj= new MyObstacle();
//        Group curr0= obj.MakeringObstacle(225, -ringobstacles.size() *250);
//        ringobstacles.add(curr0);
        Group curr= obj.MakeringObstacle(225, -ringobstacles.size() * 450);
        ringobstacles.add(curr);
        Group curr2= obj.MakeCross(100, -ringobstacles.size() * 450);
        ringobstacles.add(curr2);
        Group curr3 =obj.MakeSquareTrap(225, (-ringobstacles.size())* 450);
        ringobstacles.add(curr3);
        Group curr4 =obj.MakeLine2a(-ringobstacles.size() * 450);
        ringobstacles.add(curr4);
        obj.rotateTransition(curr,4);
//        obj.rotateTransition(curr0,4);

        obj.MoveLine2(curr4,4,-450);
        obj.rotateTransition(curr2,4);
        obj.rotateTransition(curr3,5);

    }

    public void CheckRingCollision(){
        for (int j=0;j<3;j++){
            for(int i=0;i<ringobstacles.get(j).getChildren().size();i++){
                Shape s1= (Shape)ringobstacles.get(j).getChildren().get(i);
                if(Shape.intersect(Ball,s1).getBoundsInLocal().getWidth()!=-1&&(!s1.getFill().equals(Ball.getFill()))){
                    System.out.println("BAll color :"+ Ball.getFill()+ "Ring Color"+s1.getFill());
                    GameOver=true;
                }
            }
            if(Ball.getCenterY()>height-10||Ball.getCenterY()<10)
                GameOver=true;
            if(GameOver){
                Ball.setCenterY(height-Ball.getRadius());
                l2.setText("Game Over");
                l2.setLayoutX(MainStage.getWidth()/2-35);
                l2.setLayoutY(MainStage.getHeight()/2-50);
                if(lightmode)
                    l2.setTextFill(Color.valueOf("#141518"));
                else
                    l2.setTextFill(Color.WHITESMOKE);
                l2.setScaleY(4);
                l2.setScaleX(4);
            }
        }
    }
    public void Jump(){
        if (!GameOver){
            if (Gravity>0)
                Gravity=0;
            Gravity-=9;
        }
    }


    public void StartGame2(){
        Ball.setCenterX(width/2);
        Ball.setCenterY(2*height/3);
        GameOver=false;
        Gravity=0;
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
        if(lightmode)
            l1.setTextFill(Color.valueOf("#141518"));
        else
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        Menu controller = (Menu) loader.getController();
        controller.setStage(this.MainStage);
        Scene scene = new Scene(root,450,800);
        MainStage.setScene(scene);
        MainStage.setResizable(false);
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
        if(lightmode)
            addImage(pauseButton,"sample/Assets/pause.png");
        else
            addImage(pauseButton,"sample/Assets/pause_white.png");

        pauseButton.setId("pauseButton");
        Root.getStylesheets().add("sample/button.css");
        Root.getChildren().add(pauseButton);


        score = new Label("0");
        score.setLayoutX(10);
        score.setLayoutY(15);
        score.prefHeight(50);
        score.prefHeight(50);
        score.setFont(new Font("Cambria", 36));

        if(lightmode)
            score.setTextFill(Color.valueOf("#141518"));

        else
            score.setTextFill(Color.WHITESMOKE);


        Root.getChildren().add(score);
        MainStage.setResizable(false);

        Ball=new Circle();
        Ball.setRadius(12);
        Ball.setFill(Colors[(int)Math.random()*4]);

        ringobstacles=new ArrayList<>();
        Timer=new Timeline();
        Timer.setCycleCount(Animation.INDEFINITE);

        Restart=new javafx.scene.control.Button("Restart Game");
        Restart.setId("restartButton");
        Restart.setTranslateX(120);
        Restart.setTranslateY(height/2+20);
        Restart.setPrefSize(200,50);

        GameOver=false;
        Gravity=0;
        ticks=0;

        l1=new javafx.scene.control.Label();l2=new Label();

        KeyFrame KF1=new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ticks++;
                if(ticks%2==0 && Gravity<15){
                    Gravity+=2;
                }
                double y=Ball.getCenterY()+Gravity;
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
                if(Ball.getCenterY()<390) {
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
        if(lightmode)
            MainScene.setFill(Color.valueOf("#fffff0"));
        else
            MainScene.setFill(Color.valueOf("#141518"));
        StartGame2();
        MainStage.setScene(MainScene);
        MainStage.show();

        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e2)->{
            Scene currScene = MainStage.getScene();
            Button resumeButton = MakeButton(67,227,113,332,"Resume","ResumeButton");
            Button saveButton = MakeButton(67,227,113,447,"Save Game","SaveGame");
            Button HomeButton= MakeButton(50,50,35,100,"","pauseButton");
            Timer.pause();
            if(lightmode)
                addImage(HomeButton,"sample/Assets/home.png");
            else
                addImage(HomeButton,"sample/Assets/home_white.png");

            Label l3= new Label();
            l3.setText("Pause Menu");
            l3.setFont(Font.font("Futura Light BT"));
            l3.setLayoutX(MainStage.getWidth()/2-35);
            l3.setLayoutY(120);
            if(lightmode)
                l3.setTextFill(Color.valueOf("#141518"));
            else
                l3.setTextFill(Color.WHITESMOKE);
            l3.setScaleY(4);
            l3.setScaleX(4);
            Group PauseMenu = new Group(resumeButton,l3,saveButton,HomeButton);
            PauseMenu.getStylesheets().add("sample/button.css");

            Scene PauseScene = new Scene(PauseMenu,450,800);
            if(lightmode)
                PauseScene.setFill(Color.valueOf("#fffff0"));
            else
                PauseScene.setFill(Color.valueOf("#141518"));

            MainStage.setScene(PauseScene);
            resumeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e3)->{
                MainStage.setScene(currScene);
                Timer.play();

            });
            HomeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e4)->{
                try {
                    loadButton("menu.fxml");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

        });
    }

    public Button MakeButton(double h,double w,double x, double y,String text,String ID){
        Button button= new Button();
        button.setPrefHeight(h);
        button.setPrefWidth(w);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setText(text);
        button.setId(ID);
        return button;
    }
    /*@Override
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

        Label score = new Label("0");
        score.setLayoutX(10);
        score.setLayoutY(15);
        score.prefHeight(50);
        score.prefHeight(50);
        score.setTextFill(Color.WHITESMOKE);
        score.setFont(new Font("Cambria", 36));
        Root.getChildren().add(score);

        Ball=new Circle();
        Ball.setRadius(12);
        Ball.setFill(Colors[(int)Math.random()*4]);

        Obstacles=new ArrayList<>();
        Powerups=new ArrayList<>();
        Timer=new Timeline();
        Timer.setCycleCount(Animation.INDEFINITE);

        Restart=new javafx.scene.control.Button("Restart Game");
        Restart.setId("restartButton");
        Restart.setTranslateX(120);
        Restart.setTranslateY(height/2+20);
        Restart.setPrefSize(200,50);

        GameOver=false;
        Gravity=0;
        ticks=0;
        l1=new javafx.scene.control.Label();l2=new javafx.scene.control.Label();

        KeyFrame KF1=new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ticks++;
                if(ticks%2==0 && Gravity<20){
                    Gravity+=2;ticks=0;
                }
                double y=Ball.getCenterY()+Gravity;
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
                CheckCollision();
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
        }
        KeyFrame KF2 =new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Ball.getCenterY()<425) {
                    int i=0;j=0;
                    while(i<Obstacles.size()||j<Powerups.size()){
                        if(i<Obstacles.size()){
                            Obstacles.get(i).setY(Obstacles.get(i).getY()+4);
                            i++;
                        }
                        if(j<Powerups.size()){
                            Powerups.get(i).setY(Powerups.get(i).getY()+4);
                            j++;
                        }
                    }
                }
                if(Obstacles.get(0).getY()>height) {
                    Obstacles.remove(0);
                    if(Obstacles.size()<3)
                        AddObstacle();
                }
            }
        });
        Timer.getKeyFrames().addAll(KF1,KF2);
        Root.getChildren().addAll(Ball);
        MainScene=new Scene(Root,450,800);
        MainScene.setFill(Color.valueOf("#141518"));
        BeginGame();
        MainStage.setScene(MainScene);
        MainStage.show();
    }*/
    public void CheckCollision(){
        if(Shape.intersect(Ball,Powerups.get(0).getObject()).getBoundsInLocal().getWidth()!=-1){
            Powerups.get(0).Collide();
            if(Powerups.get(0).getClass()==ColourBooster.class){
                int i=(int)Math.random()*4;
                while(Ball.getFill()==Colors[i]){
                    i=(int)Math.random()*4;
                }
                Ball.setFill(Colors[i]);
            }
            else{
                score.setText(Integer.toString(Integer.parseInt(score.getText())+1));
            }
            Powerups.remove(0);
        }
        for (int j=0;j<3;j++){
            Group CurrObstacle=Obstacles.get(j).getObstacle();
            for(int i=0;i<CurrObstacle.getChildren().size();i++){
                Shape s1= (Shape)CurrObstacle.getChildren().get(i);
                if(Shape.intersect(Ball,s1).getBoundsInLocal().getWidth()!=-1&&(!s1.getFill().equals(Ball.getFill()))){
                    GameOver=true;
                }
            }
            if(Ball.getCenterY()>height-12||Ball.getCenterY()<12)
                GameOver=true;
            if(GameOver){
                Ball.setCenterY(height-Ball.getRadius());
                l2.setText("Game Over");
                l2.setLayoutX(MainStage.getWidth()/2-35);
                l2.setLayoutY(MainStage.getHeight()/2-50);
                if(lightmode)
                    l2.setTextFill(Color.valueOf("#141518"));
                else
                    l2.setTextFill(Color.WHITESMOKE);

                l2.setScaleY(4);
                l2.setScaleX(4);
            }
        }
    }
    public void BeginGame(){
        Ball.setCenterX(width/2);
        Ball.setCenterY(2*height/3);
        GameOver=false;
        Gravity=0;
        Root.getChildren().remove(Restart);
        Root.getChildren().removeAll(Obstacles);
        Obstacles.clear();
        for(int i=0;i<3;i++)
            AddObstacle();
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
                for(int i=0;i<Obstacles.size();i++){
                    Root.getChildren().add(Obstacles.get(i).getObstacle());
                }
                Root.getChildren().remove(l1);
                Root.getChildren().remove(l2);
                Timer.play();
            }
        });
    }
    public void AddObstacle(){
        int index=(int)(Math.random()*5);
        if (index==0){
            if(Obstacles.size()==0){
                Ring ring=new Ring(225,20);
            }
            else{

            }
        }
        else if(index==1){
            if(Obstacles.size()==0){

            }
            else{

            }
        }
        else if(index==2){
            if(Obstacles.size()==0){

            }
            else{

            }
        }
        else if(index==3){
            if(Obstacles.size()==0){

            }
            else{

            }
        }
        else if(index==4){
            if(Obstacles.size()==0){

            }
            else{

            }
        }
    }
}