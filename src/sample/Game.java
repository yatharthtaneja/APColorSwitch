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
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Game extends Application{
    private Scene MainScene;
    private Stage MainStage;
    private Timeline Timer;
    private Ball Ball;
    private ArrayList<Obstacle> Obstacles;
    private ArrayList<Powerups> Powerups;
    int SaveLocation=0;
    boolean SavedGame;
    private Group Root;
    private double Gravity;
    private long Ticks;
    private Color Colors[]={Color.web("#35e2f2"),Color.web("#f6df0e"),Color.web("#8c13fb"),Color.web("#ff0080")};
    private Label StartGameLabel,GameOverLabel,ScoreLabel,ColorSwitchLabel,TotalStarLabel,star_3;
    private static boolean DarkTheme=true;
    private player CurrentPlayer;
    private Score Score= new Score();
    private boolean GameOver,ScoreUpdated;
    private Button Restart;
    private Button Reincarnate;
    private double reviveX;
    private double reviveY;
    private static boolean SoundOn=true;
    public void setStage(Stage stage){ this.MainStage=stage; }
    public void setTheme(boolean darktheme){ DarkTheme=darktheme;}
    public void setSoundOn(boolean sound){
        SoundOn=sound;
    }
    public void setSaveLocation(int loc){
        this.SaveLocation=loc;
        this.SavedGame=true;
    }
    public void setCurrentPlayer(player Player){
        this.CurrentPlayer=Player;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Root=new Group();
        Button PauseButton= new Button();
        PauseButton.setPrefHeight(50);PauseButton.setPrefWidth(50);
        PauseButton.setLayoutX(375);PauseButton.setLayoutY(10);
        if(!DarkTheme)
            AddImage(PauseButton,"sample/Assets/pause.png");
        else
            AddImage(PauseButton,"sample/Assets/pause_white.png");
        PauseButton.setId("pauseButton");
        PauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e2)->{
            PlaySound("Button.wav");
            PauseMenu();
        });
        Root.getStylesheets().add("sample/button.css");
        Root.getChildren().add(PauseButton);
        ScoreLabel = new Label("0");
        ScoreLabel.prefHeight(50);ScoreLabel.prefHeight(50);
        ScoreLabel.setLayoutX(10);ScoreLabel.setLayoutY(15);
        if(!DarkTheme)
            ScoreLabel.setTextFill(Color.valueOf("#141518"));
        else
            ScoreLabel.setTextFill(Color.WHITESMOKE);
        ScoreLabel.setFont(new Font("Cambria", 36));
        Root.getChildren().add(ScoreLabel);
        Ball=new Ball(225,535,12,Colors[(int)Math.random()*4]);
        Obstacles=new ArrayList<>();Powerups=new ArrayList<>();
        Timer=new Timeline();
        Timer.setCycleCount(Animation.INDEFINITE);
        Restart=new javafx.scene.control.Button("Restart Game");
        Restart.setId("restartButton");
        Restart.setTranslateX(120);Restart.setTranslateY(420);
        Restart.setPrefSize(200,50);
        Reincarnate=new javafx.scene.control.Button("Revive");
        Reincarnate.setId("reviveButton");
        Reincarnate.setTranslateX(120);Reincarnate.setTranslateY(490);
        Reincarnate.setPrefSize(200,50);
        GameOver=false;
        Gravity=0;Ticks=0;
        StartGameLabel=new Label();GameOverLabel=new Label();ColorSwitchLabel= new Label();TotalStarLabel= new Label(); star_3= new Label();
        Image stars = new Image("sample/Assets/star_3_yellow.png");
        ImageView view = new ImageView(stars);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        star_3.setGraphic(view);

        KeyFrame KF1=new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Ticks++;
                if(Ticks%2==0 && Gravity<15){
                    Gravity+=2;
                }
                Ball.setYpos(Ball.getYpos()+Gravity);
                MainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        String code=keyEvent.getCode().toString();
                        if(code.equals("UP")){
                            MoveAhead();
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
                    for (int i=0;i<Obstacles.size();i++){
                        Obstacles.get(i).Pause();
                    }
                    if(!Root.getChildren().contains(GameOverLabel)) {
                        Root.getChildren().removeAll();
                        Root.getChildren().addAll(GameOverLabel,Restart,Reincarnate,TotalStarLabel,star_3);
                    }
                    if(!ScoreUpdated) {
                        Score updateScore= new Score();
                        updateScore.writeStats(Integer.parseInt(ScoreLabel.getText()));
                        ScoreUpdated = true;
                    }
                    Restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            PlaySound("Button.wav");
                            Root.getChildren().removeAll(Restart,GameOverLabel,Reincarnate);
                            StartGame();
                        }
                    });
                    Reincarnate.setOnMouseClicked(new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            PlaySound("Button.wav");
                            Root.getChildren().remove(Restart);
                            Root.getChildren().remove(GameOverLabel);
                            Root.getChildren().remove(Reincarnate);
                            Reincarnate();
                        }
                    });
                }
            }
        });
        KeyFrame KF2 =new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Ball.getYpos()<390) {
                    for (int i=0;i<Obstacles.size();i++){
                        Obstacles.get(i).incrementYpos(3.5);
                    }
                    for (int i=0;i<Powerups.size();i++){
                        Powerups.get(i).incrementYpos(3.5);
                    }
                    reviveY+=3.5;
                    ColorSwitchLabel.setLayoutY(ColorSwitchLabel.getLayoutY()+3.5);
                }
                if(Obstacles.get(0).getYpos()>950) {
                    Root.getChildren().remove(Obstacles.get(0).getObstacle());
                    Obstacles.remove(0);
                    if(Obstacles.size()<4)
                        AddObstacleandPowerup();
                }
                if(ColorSwitchLabel.getLayoutY()>950){
                    Root.getChildren().remove(ColorSwitchLabel);
                }
            }
        });
        Timer.getKeyFrames().addAll(KF1,KF2);
        Root.getChildren().add(Ball.getBall());
        MainScene=new Scene(Root,450,800);
        if(!DarkTheme)
            MainScene.setFill(Color.valueOf("#fffff0"));
        else
            MainScene.setFill(Color.valueOf("#141518"));
        if(CurrentPlayer==null)
            StartGame();
        else
            ResumeGame();
        MainStage.setScene(MainScene);
        MainStage.show();
    }
    public void StartGame(){//Used for New Games
        ScoreUpdated = false;
        Ball.setXpos(225);Ball.setYpos(535);
        reviveX = 225;reviveY = 535;
        GameOver = false;
        Gravity = 0;Ticks = 0;
        Root.getChildren().removeAll(Restart,Reincarnate,TotalStarLabel,star_3);
        int count1 = Obstacles.size(), count2 = Powerups.size();
        if(SavedGame){
            ReorderGameData();
            SaveLocation=0;
            SavedGame=false;
        }
        for (int i = 0; i < count1; i++)
            Root.getChildren().remove(Obstacles.get(i).getObstacle());
        for (int i = 0; i < count2; i++)
            Root.getChildren().remove(Powerups.get(i).getObject());
        Obstacles.clear();
        Powerups.clear();
        ScoreLabel.setText("0");
        for (int i = 0; i < 4; i++)
            AddObstacleandPowerup();
        StartGameLabel.setText("Press Up key to start");
        StartGameLabel.setScaleX(2);StartGameLabel.setScaleY(2);
        StartGameLabel.setLayoutX(MainStage.getWidth() / 2 - 55);StartGameLabel.setLayoutY(MainStage.getHeight() / 2 - 50);
        ColorSwitchLabel.setText(("COLOR\nSWITCH"));
        ColorSwitchLabel.setTextAlignment(TextAlignment.CENTER);
        ColorSwitchLabel.setFont(Font.font("Futura Light BT"));
        ColorSwitchLabel.setLayoutX(MainStage.getWidth() / 2 - 30);ColorSwitchLabel.setLayoutY(MainStage.getHeight() / 2+180);
        ColorSwitchLabel.setScaleX(4);ColorSwitchLabel.setScaleY(4);
        if (!DarkTheme) {
            StartGameLabel.setTextFill(Color.valueOf("#141518"));
            ColorSwitchLabel.setTextFill(Color.valueOf("#141518"));
        } else {
            StartGameLabel.setTextFill(Color.WHITESMOKE);
            ColorSwitchLabel.setTextFill(Color.WHITESMOKE);
        }
        Timer.pause();
        if (!Root.getChildren().contains(StartGameLabel) && !Root.getChildren().contains(ColorSwitchLabel))
           Root.getChildren().addAll(StartGameLabel,ColorSwitchLabel);

        MainScene.setOnKeyReleased(keyEvent -> {
            String code=keyEvent.getCode().toString();
            if(code.equals("UP")){
                Root.getChildren().remove(StartGameLabel);
                Timer.play();
            }
            else if(code.equals("P")||code.equals("p"))
                PauseMenu();
        });
    }
    public void ResumeGame(){//Used for Saved Games
        ScoreUpdated= false;
        Ball.setXpos(CurrentPlayer.getBallX());Ball.setYpos(CurrentPlayer.getBallY());
        reviveY=CurrentPlayer.getBallY();reviveX=CurrentPlayer.getBallX();
        GameOver=false;
        Gravity=0;Ticks=0;
        Obstacles.clear();Powerups.clear();
        for(int i=0;i<CurrentPlayer.ObstacleType.size();i++){
            int index=CurrentPlayer.ObstacleType.get(i);
            Obstacle obstacle = null;
            if (index==0){//SingleRing Anticlockwise
                obstacle=new Ring(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==1){//Unidirectional Line Right
                obstacle=new UnidirectionalLine(CurrentPlayer.ObsatcleYcord.get(i),false);
            }
            else if(index==2){//Unidirectional Line Left
                obstacle=new UnidirectionalLine(CurrentPlayer.ObsatcleYcord.get(i),true);
            }
            else if(index==3){//Cross Clockwise
                obstacle=new Cross(125,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==4){//SquareTrap Clockwise
                obstacle=new SquareTrap(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==5){//DoubleCross
                obstacle=new DoubleCross(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==6){//RectangleOfDots
                obstacle=new RectangleOfDots(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index ==7){//Bidirectional Line
                obstacle=new BidirectionalLine(CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==8){//DiamondOfDots
                obstacle=new DiamondOfDots(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            else if(index==9){//Horizontal DoubleRing
                obstacle=new DoubleRing(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            if (index==10){//Trilateral
                obstacle=new Trilateral(225,CurrentPlayer.ObsatcleYcord.get(i));
            }
            obstacle.setCurrentTime(CurrentPlayer.CurrentTime.get(i));
            Obstacles.add(obstacle);
            Root.getChildren().add(obstacle.getObstacle());
        }
        for (int i=0;i<CurrentPlayer.PowerupType.size();i++){
            int index=CurrentPlayer.PowerupType.get(i);
            Powerups powerup = null;
            if (index==1){
                powerup=new Star(CurrentPlayer.PowerupYcord.get(i),DarkTheme);
            }
            else if(index==2){
                powerup=new ColourBooster(CurrentPlayer.PowerupYcord.get(i));
            }
            Powerups.add(powerup);
            Root.getChildren().add(powerup.getObject());
        }
        ScoreLabel.setText(Integer.toString(CurrentPlayer.getcurrScore()));
        StartGameLabel.setText("Press Up key to start");
        StartGameLabel.setScaleX(2);
        StartGameLabel.setScaleY(2);
        StartGameLabel.setLayoutX(MainStage.getWidth()/2-55);
        StartGameLabel.setLayoutY(MainStage.getHeight()/2-50);
        if(!DarkTheme)
            StartGameLabel.setTextFill(Color.valueOf("#141518"));
        else
            StartGameLabel.setTextFill(Color.WHITESMOKE);
        Timer.pause();
        if(!Root.getChildren().contains(StartGameLabel))
            Root.getChildren().add(StartGameLabel);
        MainScene.setOnKeyReleased(keyEvent -> {
            String code=keyEvent.getCode().toString();
            if(code.equals("UP")){
                Root.getChildren().remove(StartGameLabel);
                for (int i=0;i<Obstacles.size();i++)
                    Obstacles.get(i).Move();
                Timer.play();
            }
            else if(code.equals("P")||code.equals("p"))
                PauseMenu();
        });
    }

    public void Reincarnate(){//Used for Reincarnated Gamed through coins
        Score updateScore= new Score();
        int available=Integer.parseInt(updateScore.getStar());
        if(available>=100) {
            updateScore.updateStars(-100);
            for (int i = 0; i < Obstacles.size(); i++)
                Obstacles.get(i).Move();

            updateScore.writeStats(-Integer.parseInt(ScoreLabel.getText()));//Subtracting current stars from total
            ScoreUpdated = false;
            Ball.setXpos(reviveX);
            if (reviveY > 725)
                reviveY = 725;
            Ball.setYpos(reviveY);
            GameOver = false;
            Gravity = 0;
            Ticks = 0;
            Root.getChildren().removeAll(Restart, Reincarnate, TotalStarLabel, star_3);
            StartGameLabel.setText("Press Up key to start");
            StartGameLabel.setScaleX(2);
            StartGameLabel.setScaleY(2);
            StartGameLabel.setLayoutX(MainStage.getWidth() / 2 - 55);
            StartGameLabel.setLayoutY(MainStage.getHeight() / 2 - 50);
            if (!DarkTheme)
                StartGameLabel.setTextFill(Color.valueOf("#141518"));
            else
                StartGameLabel.setTextFill(Color.WHITESMOKE);
            Timer.pause();
            if (!Root.getChildren().contains(StartGameLabel))
                Root.getChildren().add(StartGameLabel);
            MainScene.setOnKeyReleased(keyEvent -> {
                String code = keyEvent.getCode().toString();
                if (code.equals("UP")) {
                    Root.getChildren().remove(StartGameLabel);
                    Timer.play();
                } else if (code.equals("P") || code.equals("p"))
                    PauseMenu();
            });
        }
        else {
            System.out.println("No funds");
        }
    }
    private void MoveAhead() {
        if (!GameOver) {
            if (Gravity > 0)
                Gravity = 0;
            Gravity -= 9;
        }
    }
    public void CheckObstacleCollision(){
        outer:for (int j=0;j<3;j++) {
            if(Obstacles.size()>j) {
                for (int i = 0; i < Obstacles.get(j).getListOfShapes().size(); i++) {
                    Shape s1 = (Shape) Obstacles.get(j).getListOfShapes().get(i).getShape();
                    if (Shape.intersect(Ball.getBall(), s1).getBoundsInLocal().getWidth() != -1 && (!(s1.getFill().equals(Ball.getBall().getFill())))) {
                        GameOver = true;
                        break outer;
                    }
                }
            }
        }
        if(Ball.getYpos()>788||Ball.getYpos()<12)
            GameOver=true;
        if(GameOver){
            PlaySound("GameOver.wav");
            Root.getChildren().remove(ColorSwitchLabel);
            Timer.pause();
            GameOverLabel.setText("Game Over");
            GameOverLabel.setLayoutX(MainStage.getWidth()/2-35);GameOverLabel.setLayoutY(MainStage.getHeight()/2-50);
            TotalStarLabel.setText(Integer.toString(Integer.parseInt(Score.getStar())+Integer.parseInt(ScoreLabel.getText())));
            TotalStarLabel.setLayoutX(MainStage.getWidth()/2-20);TotalStarLabel.setLayoutY(MainStage.getHeight()/2+250);
            star_3.setLayoutX(MainStage.getWidth()/2-50); star_3.setLayoutY(MainStage.getHeight()/2+160);
            if(!DarkTheme) {
                GameOverLabel.setTextFill(Color.valueOf("#141518"));
                TotalStarLabel.setTextFill(Color.valueOf("#141518"));
            }
            else {
                GameOverLabel.setTextFill(Color.WHITESMOKE);
                TotalStarLabel.setTextFill(Color.WHITESMOKE);
            }
            GameOverLabel.setScaleY(4);GameOverLabel.setScaleX(4);
            TotalStarLabel.setScaleX(5);TotalStarLabel.setScaleY(5);
        }
    }
    public void CheckPowerupCollision(){
        if(Powerups.size()>0){
            Shape s1=Powerups.get(0).getObject();
            if (Shape.intersect(Ball.getBall(), s1).getBoundsInLocal().getWidth()!=-1){
                Powerups.get(0).Collide();
                if (Powerups.get(0).getClass()==ColourBooster.class){
                    int index=(int)(Math.random()*4);
                    while(Ball.getBall().getFill()==Colors[index]){//Checking if same colour is alotted again
                        index=(int)(Math.random()*4);
                    }
                    Ball.getBall().setFill(Colors[index]);Ball.getBall().setStroke(Colors[index]);
                }
                else if(Powerups.get(0).getClass()==Star.class){
                    ScoreLabel.setText(Integer.toString(Integer.parseInt(ScoreLabel.getText())+1));
                    reviveX=Ball.getXpos();
                    reviveY=Ball.getYpos();
                }
                Root.getChildren().remove(Powerups.get(0).getObject());
                Powerups.remove(0);
            }
        }
    }
    private void AddObstacleandPowerup(){
        int maxindex=3*((Integer.parseInt(ScoreLabel.getText())/4)+1);
        if(maxindex>11)
            maxindex=11;
        int index= (int)(Math.random()*maxindex);
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
            star=new Star(y,DarkTheme);
            colourbooster=new ColourBooster(y-200);
            obstacle=new Ring(225,y);
        }
        else if(index==1){//Unidirectional Line Right
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,DarkTheme);
            colourbooster=new ColourBooster(y-125);
            obstacle=new UnidirectionalLine(y,false);
        }
        else if(index==2){//Unidirectional Line Left
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,DarkTheme);
            colourbooster=new ColourBooster(y-125);
            obstacle=new UnidirectionalLine(y,true);
        }
        else if(index==3){//Cross Clockwise
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,DarkTheme);
            colourbooster=new ColourBooster(y-180);
            obstacle=new Cross(125,y);
        }
        else if(index==4){//SquareTrap Clockwise
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-500;
            }
            star=new Star(y,DarkTheme);
            colourbooster=new ColourBooster(y-225);
            obstacle=new SquareTrap(225,y);
        }
        else if(index==5){//DoubleCross
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,DarkTheme);
            colourbooster=new ColourBooster(y-180);
            obstacle=new DoubleCross(225,y);
        }
        else if(index==6){//RectangleOfDots
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-500;
            }
            star=new Star(y,DarkTheme);
            colourbooster=new ColourBooster(y-250);
            obstacle=new RectangleOfDots(225,y);
        }
        else if(index ==7){//Bidirectional Line
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-250;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-350;
            }
            star=new Star(y-65,DarkTheme);
            colourbooster=new ColourBooster(y-125);
            obstacle=new BidirectionalLine(y);
        }
        else if(index==8){//DiamondOfDots
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-600;
            }
            star=new Star(y,DarkTheme);
            colourbooster=new ColourBooster(y-250);
            obstacle=new DiamondOfDots(225,y);
        }
        else if(index==9){//Horizontal DoubleRing
            if(Obstacles.size()!=0){
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y-100,DarkTheme);
            colourbooster=new ColourBooster(y-180);
            obstacle=new DoubleRing(225,y);
        }
        else if (index==10){//Trilateral
            if(Obstacles.size()!=0) {
                if (Obstacles.get(Obstacles.size()-1) instanceof Line)
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-300;
                else
                    y=Obstacles.get(Obstacles.size()-1).getYpos()-400;
            }
            star=new Star(y,DarkTheme);
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
    private void PauseMenu(){
        for (int i=0;i<Obstacles.size();i++){
            Obstacles.get(i).Pause();
        }
        Scene CurrentScene =MainStage.getScene();
        Button ResumeButton = MakeButton(67,227,113,332,"Resume","ResumeButton");
        Button SaveButton = MakeButton(67,227,113,447,"Save Game","SaveGame");
        Button HomeButton= MakeButton(50,50,35,100,"","pauseButton");
        Timer.pause();
        if(!DarkTheme)
            AddImage(HomeButton,"sample/Assets/home.png");
        else
            AddImage(HomeButton,"sample/Assets/home_white.png");
        Label PauseLabel= new Label();
        PauseLabel.setText("Pause Menu");
        PauseLabel.setFont(Font.font("Futura Light BT"));
        PauseLabel.setLayoutX(MainStage.getWidth()/2-35);
        PauseLabel.setLayoutY(120);
        if(!DarkTheme)
            PauseLabel.setTextFill(Color.valueOf("#141518"));
        else
            PauseLabel.setTextFill(Color.WHITESMOKE);
        PauseLabel.setScaleY(4);
        PauseLabel.setScaleX(4);
        Group PauseMenu = new Group(ResumeButton,PauseLabel,SaveButton,HomeButton);

        if(GameOver) {
            // if game over then you cannot save a game
            PauseMenu = new Group(ResumeButton, PauseLabel, HomeButton);
        }
        PauseMenu.getStylesheets().add("sample/button.css");
        Scene PauseScene = new Scene(PauseMenu,450,800);
        if(!DarkTheme)
            PauseScene.setFill(Color.valueOf("#fffff0"));
        else
            PauseScene.setFill(Color.valueOf("#141518"));
        MainStage.setScene(PauseScene);
        ResumeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e3)->{
            PlaySound("Button.wav");
            MainStage.setScene(CurrentScene);
            for (int i=0;i<Obstacles.size();i++){
                Obstacles.get(i).Play();
            }
            Timer.play();
        });
        HomeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e4)->{
            PlaySound("Button.wav");
            try {
                LoadButton("GameMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        SaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e5)->{
            PlaySound("Button.wav");
            SaveCurrentGame();
            try {
                LoadButton("GameMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    private void LoadButton(String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        Parent root = loader.load();
        GameMenu controller = (GameMenu) loader.getController();
        controller.setStage(this.MainStage);
        Scene scene = new Scene(root,450,800);
        MainStage.setScene(scene);
        MainStage.setResizable(false);
        MainStage.show();
    }
    private Button MakeButton(double h,double w,double x, double y,String text,String ID){
        Button button= new Button();
        button.setPrefHeight(h);
        button.setPrefWidth(w);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setText(text);
        button.setId(ID);
        return button;
    }
    private void PlaySound(String link){
        if(SoundOn) {
            AudioClip sound = new AudioClip(this.getClass().getResource(link).toString());
            sound.play();
        }
    }
    private void AddImage(Button b1,String path){
        javafx.scene.image.Image img = new Image(path);
        ImageView view = new ImageView(img);
        view.setFitHeight(45);
        view.setPreserveRatio(true);
        b1.setGraphic(view);
    }
    private void SaveCurrentGame(){
        if(!GameOver){
            player P = new player();
            P.setScore(Integer.parseInt(ScoreLabel.getText()));
            P.setBallX(Ball.getXpos());
            P.setBallY(Ball.getYpos());
            P.SaveGame=true;
            P.DateTime=new Date();
            for(int i =0 ;i< Obstacles.size();i++){
                Obstacle obs = Obstacles.get(i);
                P.addType(getTypeofObstacle(obs));
                P.addXcord(obs.getXpos());
                P.addYcord(obs.getYpos());
                P.CurrentTime.add(obs.getCurrentTime());
            }
            for(int i =0 ;i< Powerups.size();i++){
                sample.Powerups pow=Powerups.get(i);
                if(pow.getClass()==Star.class)
                    P.PowerupType.add(1);
                else
                    P.PowerupType.add(2);
                P.PowerupYcord.add(pow.getYpos());
            }
            player temp=null;
            if(SavedGame&&SaveLocation==1){
                try {
                    resourceManager.save(P,"1.save");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                int i;
                if(SavedGame)
                    i=SaveLocation-1;
                else
                    i=3;
                for(;i>=1;i--){
                    try {
                        temp = (player) resourceManager.loadData(i+".save");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        resourceManager.save(temp,(i+1)+".save");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    resourceManager.save(P,"1.save");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void ReorderGameData(){
        player temp=null;
        for(int i=SaveLocation+1;i<=4;i++){
            try {
                temp = (player) resourceManager.loadData(i+".save");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resourceManager.save(temp,(i-1)+".save");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        temp=new player();
        temp.SaveGame=false;
        try {
            resourceManager.save(temp,"4.save");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getTypeofObstacle(Obstacle obs){
        int type =-1;
        if(obs.getClass()==Ring.class)
            type=0;
        else if(obs.getClass()== UnidirectionalLine.class && !((UnidirectionalLine) obs).getLeft())
            type=1;
        else if(obs.getClass()== UnidirectionalLine.class && ((UnidirectionalLine) obs).getLeft())
            type=2;
        else if(obs instanceof Cross)
            type=3;
        else if(obs.getClass()==SquareTrap.class)
            type=4;
        else if(obs instanceof DoubleCross)
            type=5;
        else if (obs instanceof RectangleOfDots)
            type=6;
        else if(obs instanceof BidirectionalLine)
            type=7;
        else if(obs instanceof DiamondOfDots)
            type=8;
        else if(obs instanceof DoubleRing)
            type=9;
        else if(obs instanceof Trilateral)
            type=10;
        return type;
    }
}
