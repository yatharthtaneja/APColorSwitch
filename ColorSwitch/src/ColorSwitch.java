package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class ColorSwitch extends Application {
    GameMenu GameMenu;
    public static void main(String args[]){
        launch(args);
    }
    public void Serialize(){};
    public void DeSerialize(){};
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
class MyException extends Exception{

}
class GameEndException extends MyException{

}
class GamePauseException extends MyException{

}

class CollectStarException extends MyException{

}
class SceneElements implements Serializable {
    private double Xpos;
    private double Ypos;
    public double getXpos() {
        return Xpos;
    }
    public void setXpos(double xpos) { Xpos = xpos; }
    public double getYpos() {
        return Ypos;
    }
    public void setYpos(double ypos) {
        Ypos = ypos;
    }
}
abstract class Obstacle extends SceneElements{
    private ArrayList<Shape> ListOfShapes=new ArrayList<>();
    private Double Speed;
    int Type;
    public Double getSpeed() {
        return Speed;
    }
    public void setSpeed(Double speed) {
        Speed = speed;
    }
    public int getType() { return Type; }
    public void setType(int type) { Type = type; }
    public abstract void Move();
}
class Ring extends Obstacle{
    public void Move(){};
}
class Line extends Obstacle{
    public void Move(){};
}
class Cross extends Obstacle{
    public void Move(){};
}
class Trilateral extends Obstacle{
    public void Move(){};
}
class Ball extends SceneElements{
    public boolean CheckCollision(SceneElements sceneElements){return true;}
    public boolean CheckCollision(Obstacle obstacle){
        return true;
    }
    public boolean CheckCollision(Powerups powerups){return true;}
}
abstract class Powerups extends SceneElements{
    public abstract void Collide();
}
class Star extends Powerups{
    public void Explode(){};
    public void Collide(){};
}
class ColorBoost extends Powerups{
    public void Collide(){};
}
class Shape implements Serializable{
    private double Xpos;
    private double Ypos;
    private Color Colour;
    private double TimeSinceLastColourChange;

    public double getXpos() {
        return Xpos;
    }
    public void setXpos(double xpos) {
        Xpos = xpos;
    }
    public double getYpos() {
        return Ypos;
    }
    public void setYpos(double ypos) {
        Ypos = ypos;
    }
    public Color getColour() {
        return Colour;
    }
    public void setColour(Color colour) {
        Colour = colour;
    }
}
class Arc extends Shape{
    private double Angle;

    public double getAngle() {
        return Angle;
    }
    public void setAngle(int angle) {
        Angle = angle;
    }
}
class Rectangle extends Shape{
    private double Angle;

    public double getAngle() {
        return Angle;
    }
    public void setAngle(int angle) {
        Angle = angle;
    }
}
class Circle extends Shape{
}
class Triangle extends Shape{
    private double Angle;

    public double getAngle() {
        return Angle;
    }
    public void setAngle(int angle) {
        Angle = angle;
    }
}
class Pentagon extends Shape{
    private double Angle;

    public double getAngle() {
        return Angle;
    }
    public void setAngle(int angle) {
        Angle = angle;
    }
}
class GameMenu implements Serializable{
    private ArrayList<Player>SavedPlayers;
    private Settings MySettings;
    private Player CurrentPlayer;

    public ArrayList<Player> getSavedPlayers() {
        return SavedPlayers;
    }
    public void SavePlayer(Player player){ SavedPlayers.add(player);}
    public Settings getMySettings() {
        return MySettings;
    }
    public void setMySettings(Settings mySettings) {
        MySettings = mySettings;
    }
    public Player getCurrentPlayer() { return CurrentPlayer;}
    public void setCurrentPlayer(Player currentPlayer) { CurrentPlayer = currentPlayer; }
    public void StartGame(){};
    public void LoadGame(){};
    public void Exit(){};
    public void ChangeSetting(){};

}
class Game implements Serializable{
    ArrayList<Obstacle> Obstacles;
    Ball Ball;
    private int CurrentStars;
    Player Player;
    GameMenu GameMenu;

    public void StartGame()throws GamePauseException,GameEndException{};
    public void PauseGame(){};
    public void EndGame()throws CollectStarException,GameEndException{};
    public void UpdateStars(){};
    public void MoveAhead(){};
    public void Reincarnate(){};
}

class Player implements Serializable{
    private String Name;
    private ArrayList<Game> SavedGames;
    Game CurrentGame;
    private int TotalStars;
    private int CurrentStars;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Game getCurrentGame() { return CurrentGame; }
    public void setCurrentGame(Game currentGame) { CurrentGame = currentGame; }
    public int getTotalStars() {
        return TotalStars;
    }
    public void incrementTotalStars(){
        TotalStars++;
    }
    public void incrementCurrentStars(){
        CurrentStars++;
    }
    public void setTotalStars(int totalStars) {
        TotalStars = totalStars;
    }
    public int getCurrentStars() {
        return CurrentStars;
    }
    public void setCurrentStars(int currentStars) {
        CurrentStars = currentStars;
    }
}
class Settings{
    private String About;
    private String Credits;
    private int SoundLevel;
    private int MusicLevel;
    private boolean DarkTheme;

    public String getAbout() {
        return About;
    }
    public void setAbout(String about) {
        About = about;
    }
    public String getCredits() {
        return Credits;
    }
    public void setCredits(String credits) {
        Credits = credits;
    }
    public int getSoundLevel() {
        return SoundLevel;
    }
    public void setSoundLevel(int soundLevel) {
        SoundLevel = soundLevel;
    }
    public int getMusicLevel() {
        return MusicLevel;
    }
    public void setMusicLevel(int musicLevel) {
        MusicLevel = musicLevel;
    }
    public boolean isDarkTheme() {
        return DarkTheme;
    }
    public void setDarkTheme(boolean darkTheme) {
        DarkTheme = darkTheme;
    }
    public void SetToDefault(){};
}
