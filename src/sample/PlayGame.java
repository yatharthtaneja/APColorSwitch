package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

public class PlayGame extends Application {
    private int height=800;
    private int width=450;
    private Scene MainScene;
    private Stage MainStage;
    private Timeline Timer;
    private javafx.scene.shape.Circle Ball;
    private boolean alreadyExecuted= false;
    private ArrayList<Obstacle> Obstacles;
    private ArrayList<Powerups> Powerups;
    private Label l1,l2,score;
    private boolean GameOver;
    private Button Restart;
    private Button Revive;
    private double reviveX;
    private double reviveY;
    private Group Root;
    private double Gravity;
    private long ticks;
    private Color Colors[]={Color.web("#35e2f2"),Color.web("#f6df0e"),Color.web("#8c13fb"),Color.web("#ff0080")};
    private static boolean lightmode;

    public void setStage(Stage stage){
        this.MainStage=stage;
    }
    public void setTheme(boolean s){
        this.lightmode=s;
    }

    public void Jump(){
        if (!GameOver){
            if (Gravity>0)
                Gravity=0;
            Gravity-=9.5;
        }
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
        if(lightmode)
            score.setTextFill(Color.valueOf("#141518"));
        else
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

        Revive=new javafx.scene.control.Button("Revive");
        Revive.setId("reviveButton");
        Revive.setTranslateX(120);
        Revive.setTranslateY(height/2+70);
        Revive.setPrefSize(200,50);

        GameOver=false;
        Gravity=0;
        ticks=0;
        l1=new Label();l2=new Label();

        KeyFrame KF1=new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ticks++;
                if(ticks%2==0 && Gravity<15){
                    Gravity+=2;
                }
                Ball.setCenterY(Ball.getCenterY()+Gravity);
                MainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        String code=keyEvent.getCode().toString();
                        if(code.equals("UP")){
                            Jump();
                            PlaySound("Jumping.wav");
                        }
                        else if(code.equals("P")||code.equals("p")){
                            PauseMenu();
                        }
                    }
                });
                CheckObstacleCollision();
                CheckPowerupCollision();
                if(GameOver){
                    if(!Root.getChildren().contains(l2))
                        Root.getChildren().addAll(l2,Restart,Revive);
                    if(!alreadyExecuted) {
                        Score updatescore= new Score();
                        updatescore.writeStats(Integer.parseInt(score.getText()));
                        alreadyExecuted = true;
                        SaveCurrentGame();
                    }
                    Restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Root.getChildren().remove(Restart);
                            Root.getChildren().remove(l2);
                            Root.getChildren().remove(Revive);
                            BeginGame();
                        }
                    });
                    Revive.setOnMouseClicked(new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Root.getChildren().remove(Restart);
                            Root.getChildren().remove(l2);
                            Root.getChildren().remove(Revive);
                            ReviveGame();
                        }
                    });
                }
            }
        });
        KeyFrame KF2 =new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Ball.getCenterY()<390) {
                    for (int i=0;i<Obstacles.size();i++){
                        Obstacles.get(i).incrementYpos(4);
                    }
                    for (int i=0;i<Powerups.size();i++){
                        Powerups.get(i).incrementYpos(4);
                    }
                    reviveY+=4;
                }
                if(Obstacles.get(0).getYpos()>950) {
                    Root.getChildren().remove(Obstacles.get(0).getObstacle());
                    Obstacles.remove(0);
                    if(Obstacles.size()<4)
                        AddObstacleandPowerup();
                }
            }
        });
        Timer.getKeyFrames().addAll(KF1,KF2);
        Root.getChildren().add(Ball);
        MainScene=new Scene(Root,450,800);
        if(lightmode)
            MainScene.setFill(Color.valueOf("#fffff0"));
        else
            MainScene.setFill(Color.valueOf("#141518"));
        BeginGame();
        MainStage.setScene(MainScene);
        MainStage.show();

        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e2)->{
            PauseMenu();
        });
    }
    public void CheckObstacleCollision(){
        outer:for (int j=0;j<3;j++) {
            if(Obstacles.size()>j) {
                for (int i = 0; i < Obstacles.get(j).getListOfShapes().size(); i++) {
                    Shape s1 = (Shape) Obstacles.get(j).getListOfShapes().get(i).getShape();
                    if (Shape.intersect(Ball, s1).getBoundsInLocal().getWidth() != -1 && (!(s1.getFill().equals(Ball.getFill())))) {
                        GameOver = true;
                        break outer;
                    }
                }
            }
        }
        if(Ball.getCenterY()>height-12||Ball.getCenterY()<12)
            GameOver=true;
        if(GameOver){
            PlaySound("GameOver.wav");
            Timer.pause();
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
    public void CheckPowerupCollision(){
        if(Powerups.size()>0){
            Shape s1=Powerups.get(0).getObject();
            if (Shape.intersect(Ball, s1).getBoundsInLocal().getWidth()!=-1){
                Powerups.get(0).Collide();
                if (Powerups.get(0).getClass()==ColourBooster.class){
                    int index=(int)(Math.random()*4);
                    while(Ball.getFill()==Colors[index]){
                        index=(int)(Math.random()*4);
                    }
                    Ball.setFill(Colors[index]);
                    Ball.setStroke(Colors[index]);

                }
                else if(Powerups.get(0).getClass()==Star.class){
                    score.setText(Integer.toString(Integer.parseInt(score.getText())+1));
                    reviveX=Ball.getCenterX();
                    reviveY=Ball.getCenterY();
                }

                Root.getChildren().remove(Powerups.get(0).getObject());
                Powerups.remove(0);
            }
        }
    }
    public void AddObstacleandPowerup(){
        int index= (int)(Math.random()*11);
        double y=50;
        Star star = null;
        ColourBooster colourbooster=null;
        Obstacle obstacle=null;
        if (index==0){//SingleRing Anticlockwise
            if(Obstacles.size()!=0) {
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y,lightmode);
            colourbooster=new ColourBooster(y-200);
            obstacle=new Ring(225,y);
        }
        else if(index==1){//SquareTrap Clockwise
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-500;
            }
            star=new Star(y,lightmode);
            colourbooster=new ColourBooster(y-225);
            obstacle=new SquareTrap(225,y);
        }
        else if(index==2){//Cross Clockwise
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,lightmode);
            colourbooster=new ColourBooster(y-180);
            obstacle=new Cross(125,y);
        }
        else if(index==3){//Unidirectional Line Right
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,lightmode);
            colourbooster=new ColourBooster(y-125);
            obstacle=new UnidirectionalLine(y,false);
        }
        else if(index==4){//Unidirectional Line Left
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,lightmode);
            colourbooster=new ColourBooster(y-125);
            obstacle=new UnidirectionalLine(y,true);
        }
        else if(index ==5){//Bidirectional Line
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,lightmode);
            colourbooster=new ColourBooster(y-125);
            obstacle=new BidirectionalLine(y);
        }
        else if(index==6){//RectangleOfDots
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-500;
            }
            star=new Star(y,lightmode);
            colourbooster=new ColourBooster(y-250);
            obstacle=new RectangleOfDots(225,y);
        }
        else if(index==7){//Horizontal DoubleRing
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,lightmode);
            colourbooster=new ColourBooster(y-180);
            obstacle=new DoubleRing(225,y);
        }
        else if(index==8){//DoubleCross
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,lightmode);
            colourbooster=new ColourBooster(y-180);
            obstacle=new DoubleCross(225,y);
        }
        else if(index==9){//DiamondOfDots
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-600;
            }
            star=new Star(y,lightmode);
            colourbooster=new ColourBooster(y-250);
            obstacle=new DiamondOfDots(225,y);
        }
        if (index==10){//Trilateral
            if(Obstacles.size()!=0) {
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y,lightmode);
            colourbooster=new ColourBooster(y-200);
            obstacle=new Trilateral(225,y);
        }
        obstacle.Move();
        Obstacles.add(obstacle);
        Root.getChildren().add(obstacle.getObstacle());
        Powerups.add(star);
        Root.getChildren().add(star.getObject());
        Powerups.add(colourbooster);
        Root.getChildren().add(colourbooster.getObject());
    }
    public void BeginGame(){
        alreadyExecuted= false;
        Ball.setCenterX(width/2);
        Ball.setCenterY(2*height/3);
        reviveY=2*height/3;
        reviveX=width/2;
        GameOver=false;
        Gravity=0;
        Root.getChildren().remove(Restart);
        int count1=Obstacles.size(),count2=Powerups.size();
        for (int i=0;i<count1;i++){
            Root.getChildren().remove(Obstacles.get(i).getObstacle());
        }
        for (int i=0;i<count2;i++){
            Root.getChildren().remove(Powerups.get(i).getObject());
        }
        Obstacles.clear();Powerups.clear();
        for(int i=0;i<4;i++)
            AddObstacleandPowerup();
        score.setText("0");
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
        if(!Root.getChildren().contains(l1))
            Root.getChildren().add(l1);
        MainScene.setOnKeyReleased(keyEvent -> {
            String code=keyEvent.getCode().toString();
            if(code.equals("UP")){
                Root.getChildren().remove(l1);
                Timer.play();
            }
        });
    }
    public void ReviveGame(){
        player p2 = null;
        try {
            p2 = (player) resourceManager.loadData("1.save");
        } catch (Exception e) {
            e.printStackTrace();
        }
        alreadyExecuted= false;
//        Ball.setCenterX(p2.getBallX());
//        Ball.setCenterY(p2.getBallY());
        Ball.setCenterX(reviveX);
        Ball.setCenterY(reviveY);
        GameOver=false;
        Gravity=0;
        Root.getChildren().remove(Restart);
        Root.getChildren().remove(Revive);
        int count1=Obstacles.size(),count2=Powerups.size();
        for (int i=0;i<count1;i++){
            Root.getChildren().remove(Obstacles.get(i).getObstacle());
        }
        for (int i=0;i<count2;i++){
            Root.getChildren().remove(Powerups.get(i).getObject());
        }
        Obstacles.clear();Powerups.clear();
        for(int i=0;i<p2.getSize();i++)
        {
            Obstacle obstacle= null;
            int index = p2.getObsType(i);
            if(index==0)
                obstacle=new Ring(p2.getObsX(i),p2.getObsY(i));
            else if(index==1)
                obstacle=new SquareTrap(p2.getObsX(i),p2.getObsY(i));
            else if(index==2)
                obstacle=new Cross(p2.getObsX(i),p2.getObsY(i));
            else if(index==3)
                obstacle=new UnidirectionalLine(p2.getObsY(i),false);
            else if(index==4)
                obstacle=new UnidirectionalLine(p2.getObsY(i),true);
            else if(index ==5 )
                obstacle=new BidirectionalLine(p2.getObsY(i));
            else if(index ==6 )
                obstacle=new RectangleOfDots(p2.getObsX(i),p2.getObsY(i));
            else if(index ==7 )
                obstacle=new DoubleRing(p2.getObsX(i),p2.getObsY(i));
            else if(index ==8 )
                obstacle=new DoubleCross(p2.getObsX(i),p2.getObsY(i));
            else if(index ==9 )
                obstacle=new DiamondOfDots(p2.getObsX(i),p2.getObsY(i));
            else if(index ==10 )
                obstacle=new Trilateral(p2.getObsX(i),p2.getObsY(i));
            obstacle.Move();
            Obstacles.add(obstacle);
            Root.getChildren().add(obstacle.getObstacle());

//
        }
        score.setText(Integer.toString(p2.getcurrScore()));
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
        if(!Root.getChildren().contains(l1))
            Root.getChildren().add(l1);
        MainScene.setOnKeyReleased(keyEvent -> {
            String code=keyEvent.getCode().toString();
            if(code.equals("UP")){
                Root.getChildren().remove(l1);
                Timer.play();
            }
        });
    }
    public int getTypeofObstacle(Obstacle obs){
        int type =-1;
        if(obs.getClass()==Ring.class)
            type=0;
        else if(obs.getClass()==SquareTrap.class)
            type=1;
        else if(obs instanceof Cross)
            type=2;
        else if(obs.getClass()== UnidirectionalLine.class && !((UnidirectionalLine) obs).getLeft())
            type=3;
        else if(obs.getClass()== UnidirectionalLine.class && ((UnidirectionalLine) obs).getLeft())
            type=4;
        else if(obs instanceof BidirectionalLine)
            type=5;
        else if (obs instanceof RectangleOfDots)
            type=6;
        else if(obs instanceof DoubleRing)
            type=7;
        else if(obs instanceof DoubleCross)
            type=8;
        else if(obs instanceof DiamondOfDots)
            type=9;
        else if(obs instanceof Trilateral)
            type=10;
        return type;
    }
    public void SaveCurrentGame(){
        player p1 = new player();
        p1.setScore(Integer.parseInt(score.getText()));
        p1.setBallX(Ball.getCenterX());
        p1.setBallY(Ball.getCenterY());
        for(int i =0 ;i< Obstacles.size();i++){
            Obstacle obs = Obstacles.get(i);
            p1.addType(getTypeofObstacle(obs));
            p1.addXcord(obs.getXpos());
            p1.addYcord(obs.getYpos());
        }
        try {
            resourceManager.save(p1,"1.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("saved");
        player p2 = null;
        try {
             p2 = (player) resourceManager.loadData("1.save");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("score: "+ p2.getcurrScore()+" Ball x and y"+p2.getBallX()+" "+p2.getBallY());
        for(int k=0;k<p2.getSize();k++){
            System.out.println("Type "+ p2.getObsType(k)+" X "+ p2.getObsX(k)+" Y "+p2.getObsY(k));
        }
    }
    private void PlaySound(String link){
        AudioClip sound = new AudioClip(this.getClass().getResource(link).toString());
        sound.play();
    }
    private void PauseMenu(){
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
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e5)->{
            SaveCurrentGame();
        });
    }
}